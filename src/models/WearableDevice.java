package models;

import utils.ManufacturerNameUtility;
import utils.Utilities;

import java.util.Objects;

public abstract class WearableDevice {

    // Setting defaults as specified
    private String size;
    private double price = 20;

    // manufacturerName was updated to default to unknown, as the original specification was updated
    private String manufacturerName = "unknown";
    private String material;
    private String modelName;
    private String id = "unknown";

    // The constructor truncates some fields and validates others, setting to the default if they do not pass.
    public WearableDevice(String size, double price, String manufacturerName, String material, String modelName, String id) {
        this.size = Utilities.truncateString(size, 10);
        this.material = Utilities.truncateString(material, 20);
        this.modelName = Utilities.truncateString(modelName, 30);
        this.id = Utilities.truncateString(id, 10);

        if (Utilities.checkAboveMinimum(price, 20)) {
            this.price = price;
        }
        if (ManufacturerNameUtility.isValidManuName(manufacturerName)) {
            this.manufacturerName = manufacturerName;
        }
    }

    // The setters only validate, meaning that unless strings confirm to the constraints they will not be updated

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        if (Utilities.validStringlength(size, 10)) {
            this.size = size;
        }
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (Utilities.checkAboveMinimum(price, 20)) {
            this.price = price;
        }
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        if (ManufacturerNameUtility.isValidManuName(manufacturerName)) {
            this.manufacturerName = manufacturerName;
        }
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        if (Utilities.validStringlength(material, 20)) {
            this.material = material;
        }
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        if (Utilities.validStringlength(modelName, 30)) {
            this.modelName = modelName;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (Utilities.validStringlength(id, 10)) {
            this.id = id;
        }
    }

    public abstract double getInsurancePremium();

    public abstract String connectToInternet();

    // The toString also adds the class name (e.g. SmartBand vs SmartWatch) and formats the price to two zeros.

    // https://stackoverflow.com/questions/6271417/java-get-the-current-class-name
    // https://mkyong.com/java/java-display-double-in-2-decimal-points/
    @Override
    public String toString() {
        return "ID " + id + ", " +
                manufacturerName + " " +
                modelName + " " + this.getClass().getSimpleName() +
                ". Size " +
                size + ", made of " +
                material + ". Cost: " +
                "€" + String.format("%.2f", price) + ". ";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WearableDevice that = (WearableDevice) o;
        return Double.compare(price, that.price) == 0 && Objects.equals(size, that.size) && Objects.equals(manufacturerName, that.manufacturerName) && Objects.equals(material, that.material) && Objects.equals(modelName, that.modelName) && Objects.equals(id, that.id);
    }
}
