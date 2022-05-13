package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;

  private TorpedoStore pts = mock(TorpedoStore.class);
  private TorpedoStore sts = mock(TorpedoStore.class);
  @BeforeEach
  public void init(){
    this.ship = new GT4500(pts,sts);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    ship.setWPFL(false);
    when(pts.fire(1)).thenReturn(true);
    when(sts.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(pts,times(1)).fire(1);
    verify(sts,times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    ship.setWPFL(false);
    when(pts.fire(1)).thenReturn(true);
    when(sts.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
    verify(pts,times(1)).fire(1);
    verify(sts,times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_All_firstError(){
    // Arrange
    ship.setWPFL(false);
    when(pts.fire(1)).thenReturn(false);
    when(sts.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(false, result);
    verify(pts,times(1)).fire(1);
    verify(sts,times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_All_secondError(){
    // Arrange
    ship.setWPFL(false);
    when(pts.fire(1)).thenReturn(true);
    when(sts.fire(1)).thenReturn(false);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(false, result);
    verify(pts,times(1)).fire(1);
    verify(sts,times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_All_bothError(){
    // Arrange
    ship.setWPFL(false);
    when(pts.fire(1)).thenReturn(false);
    when(sts.fire(1)).thenReturn(false);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(false, result);
    verify(pts,times(1)).fire(1);
    verify(sts,times(0)).fire(1);
  } 

  @Test
  public void fireTorpedo_All_bothEmpty(){
    // Arrange
    ship.setWPFL(false);
    when(pts.fire(1)).thenReturn(false);
    when(sts.fire(1)).thenReturn(false);

    when(pts.isEmpty()).thenReturn(true);
    when(sts.isEmpty()).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(false, result);
    verify(pts,times(0)).fire(1);
    verify(sts,times(0)).fire(1);
  } 

  @Test
  public void fireTorpedo_All_FirstEmpty(){
    // Arrange
    ship.setWPFL(false);
    when(pts.fire(1)).thenReturn(false);
    when(sts.fire(1)).thenReturn(false);

    when(pts.isEmpty()).thenReturn(true);
    when(sts.isEmpty()).thenReturn(false);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(false, result);
    verify(pts,times(0)).fire(1);
    verify(sts,times(0)).fire(1);
  } 

  @Test
  public void fireTorpedo_All_SecondEmpty(){
    // Arrange
    ship.setWPFL(false);
    when(pts.fire(1)).thenReturn(false);
    when(sts.fire(1)).thenReturn(false);

    when(pts.isEmpty()).thenReturn(false);
    when(sts.isEmpty()).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(false, result);
    verify(pts,times(0)).fire(1);
    verify(sts,times(0)).fire(1);
  } 

  @Test
  public void fireTorpedo_Single_firstError(){
    // Arrange
    ship.setWPFL(false);
    when(pts.fire(1)).thenReturn(false);
    when(sts.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(pts,times(1)).fire(1);
    verify(sts,times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_firstError_primaryLast(){
    // Arrange
    ship.setWPFL(true);
    when(pts.fire(1)).thenReturn(false);
    when(sts.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(pts,times(0)).fire(1);
    verify(sts,times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_secondError(){
    // Arrange
    ship.setWPFL(false);
    when(pts.fire(1)).thenReturn(true);
    when(sts.fire(1)).thenReturn(false);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(pts,times(1)).fire(1);
    verify(sts,times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_secondError_lastPrimary(){
    // Arrange
    ship.setWPFL(true);
    when(pts.fire(1)).thenReturn(true);
    when(sts.fire(1)).thenReturn(false);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(pts,times(1)).fire(1);
    verify(sts,times(1)).fire(1);
  }

  

}
