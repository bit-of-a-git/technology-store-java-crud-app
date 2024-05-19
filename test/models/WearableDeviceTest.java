package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WearableDeviceTest {
    WearableDevice goodDevice, badDevice;
    @BeforeEach
    void setUp() {
        goodDevice = new SmartBand("Medium", 30.0, "SAMSUNG", "Silicone", "SmartBandModel", "SB123456", true);
        badDevice = new SmartBand("Medium12346", 10.0, "SAMSUNGNOT", "Silicone1234567890123", "ThisNameisMuchLongerThan30Chars", "SB12345678EverythingFromHereShouldBeTruncated", true);

    }

    @AfterEach
    void tearDown() {
        goodDevice=badDevice=null;
    }


    @Test
    void testGoodDeviceAttributes() {
        assertEquals("SmartBandModel", goodDevice.getModelName());
        assertEquals(30.0, goodDevice.getPrice());
        assertEquals("SAMSUNG", goodDevice.getManufacturerName());
        assertEquals("SB123456", goodDevice.getId());
        assertEquals("Medium", goodDevice.getSize());
        assertEquals("Silicone", goodDevice.getMaterial());
    }

    @Test
    void testBadDeviceAttributes() {
        assertEquals("ThisNameisMuchLongerThan30Char", badDevice.getModelName()); // Should be truncated
        assertEquals(20.0, badDevice.getPrice()); // Should be set to default
        assertEquals("unknown", badDevice.getManufacturerName()); // Should not be set
        assertEquals("SB12345678", badDevice.getId()); // Should be truncated
        assertEquals("Medium1234", badDevice.getSize()); // Should be truncated
        assertEquals("Silicone123456789012", badDevice.getMaterial()); // Should be truncated
    }

    @Test
    void testSetAndGetModelName() {
        goodDevice.setModelName("NewModelName");
        assertEquals("NewModelName", goodDevice.getModelName());

        goodDevice.setModelName("A very long model name that exceeds thirty characters");
        assertEquals("NewModelName", goodDevice.getModelName());
    }

    @Test
    void testSetAndGetPrice() {
        goodDevice.setPrice(50.0);
        assertEquals(50.0, goodDevice.getPrice());

        goodDevice.setPrice(10.0); // Should not update as it's below the minimum
        assertEquals(50.0, goodDevice.getPrice());
    }

    @Test
    void testSetAndGetManufacturer() {
        goodDevice.setManufacturerName("NewManufacturer");
        assertEquals("SAMSUNG", goodDevice.getManufacturerName());
        goodDevice.setManufacturerName("APPLE");
        assertEquals("APPLE", goodDevice.getManufacturerName());
        goodDevice.setManufacturerName("Garmin");
        assertEquals("Garmin", goodDevice.getManufacturerName());
        goodDevice.setManufacturerName("FitBit");
        assertEquals("FitBit", goodDevice.getManufacturerName());
        goodDevice.setManufacturerName("Whoop");
        assertEquals("Whoop", goodDevice.getManufacturerName());
    }

    @Test
    void testSetAndGetId() {
        goodDevice.setId("NewId");
        assertEquals("NewId", goodDevice.getId());

        goodDevice.setId("A very long id that exceeds ten characters");
        assertEquals("NewId", goodDevice.getId()); // Should not update as it's too long
    }

    @Test
    void testSetAndGetSize() {
        goodDevice.setSize("Small");
        assertEquals("Small", goodDevice.getSize());

        goodDevice.setSize("A very long size that exceeds ten characters");
        assertEquals("Small", goodDevice.getSize()); // Should not update as it's too long
    }

    @Test
    void testSetAndGetMaterial() {
        goodDevice.setMaterial("Leather");
        assertEquals("Leather", goodDevice.getMaterial());

        goodDevice.setMaterial("A very long material that exceeds twenty characters");
        assertEquals("Leather", goodDevice.getMaterial()); // Should not update as it's too long
    }



    @Test
    void testGoodDeviceToString() {
        assertTrue(goodDevice.toString().contains("SmartBandModel"));
        assertTrue(goodDevice.toString().contains("SAMSUNG"));
        assertTrue(goodDevice.toString().contains("SB123456"));
        assertTrue(goodDevice.toString().contains("Silicone"));
    }

    @Test
    void testBadDeviceToString() {

        assertTrue(badDevice.toString().contains("ThisNameisMuchLongerThan30Char"));
        assertTrue(badDevice.toString().contains("unknown"));
        assertTrue(badDevice.toString().contains("Medium1234"));
        assertTrue(badDevice.toString().contains("Silicone123456789012"));
    }
}