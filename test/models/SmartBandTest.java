package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SmartBandTest {
    SmartBand trueDevice, falseDevice;
    @BeforeEach
    void setUp() {
        trueDevice = new SmartBand("SmartBandModel", 30.0, "SAMSUNG", "SB123456", "Medium", "Silicone", true);
        falseDevice = new SmartBand("SmartBandModel", 30.0, "APPLE", "SB123456", "Medium", "Silicone", false);
    }

    @AfterEach
    void tearDown() {
        trueDevice=falseDevice=null;
    }
    @Test
    void testTrueDeviceAttributes() {
        assertTrue(trueDevice.isHeartRateMonitor());
        trueDevice.setHeartRateMonitor(false);
        assertFalse(trueDevice.isHeartRateMonitor());
    }
    @Test
    void testFalseDeviceAttributes() {
        assertFalse(falseDevice.isHeartRateMonitor());
        falseDevice.setHeartRateMonitor(true);
        assertTrue(falseDevice.isHeartRateMonitor());
    }
    @Test
    void testConnectToInternet() {
        assertEquals("Connects to the internet via Companion App", trueDevice.connectToInternet());
    }

    @Test
    void testInsurancePremium() {
        assertEquals(2.1, trueDevice.getInsurancePremium(), 0.001);
    }

    @Test
    void testTrueDeviceToString() {
        assertTrue(trueDevice.toString().contains("Includes Heart Rate Monitor"));
        trueDevice.setHeartRateMonitor(false);
        assertTrue(trueDevice.toString().contains("No Heart Rate Monitor included"));

    }
    @Test
    void testFalseDeviceToString() {
        assertTrue(falseDevice.toString().contains("No Heart Rate Monitor included"));
        falseDevice.setHeartRateMonitor(true);
        assertTrue(falseDevice.toString().contains("Includes Heart Rate Monitor"));

    }
}