package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SmartWatchTest {
    SmartWatch goodWatch, badWatch;
    @BeforeEach
    void setUp() {
        goodWatch = new SmartWatch("SAMSUNG 123", 25.0, "SAMSUNG", "SW1234", "Small", "Plastic", "AMOLED");
        badWatch = new SmartWatch("SAMSUNG 123", 25.0, "SAMSUNG", "SW1234", "Small", "Plastic", "NOTLCD");
    }

    @AfterEach
    void tearDown() {
        badWatch=goodWatch=null;
    }
    @Test
    void testGoodDeviceAttributes() {
        assertEquals("AMOLED", goodWatch.getDisplayType());
    }

    @Test
    void testBadDeviceAttributes() {
        assertEquals("LCD", badWatch.getDisplayType());
    }

    @Test
    void testDisplayType() {
        assertEquals("AMOLED", goodWatch.getDisplayType());
        goodWatch.setDisplayType("NOT RIGHT");
        assertEquals("AMOLED", goodWatch.getDisplayType());
        goodWatch.setDisplayType("LCD");
        assertEquals("LCD", goodWatch.getDisplayType());
        goodWatch.setDisplayType("LED");
        assertEquals("LED", goodWatch.getDisplayType());
        goodWatch.setDisplayType("TFT");
        assertEquals("TFT", goodWatch.getDisplayType());
    }
    @Test
    void testConnectToInternet() {
        assertEquals("Connects to the internet via bluetooth", goodWatch.connectToInternet());
    }

    @Test
    void testInsurancePremium() {
        assertEquals(1.5, badWatch.getInsurancePremium(), 0.001);
    }
    @Test
    void testGoodDeviceToString() {
        assertTrue(goodWatch.toString().contains("AMOLED"));
        goodWatch.setDisplayType("LED");
        assertTrue(goodWatch.toString().contains("LED"));
    }
    @Test
    void testBadDeviceToString() {
        assertTrue(badWatch.toString().contains("LCD"));
        goodWatch.setDisplayType("TFT");
        assertTrue(goodWatch.toString().contains("TFT"));
    }
}