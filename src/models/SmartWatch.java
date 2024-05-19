package models;

import utils.DisplayTypeUtility;
import utils.Utilities;

import java.util.Objects;

public class SmartWatch extends WearableDevice {
    private String displayType = "LCD";

    public SmartWatch(String size, double price, String manufacturerName, String material, String modelName, String id, String displayType) {
        super(size, price, manufacturerName, material, modelName, id);
        if (DisplayTypeUtility.isValidDisplayType(displayType)) {
            this.displayType = displayType;
        }
    }

    public String getDisplayType() {
        return displayType;
    }

    public void setDisplayType(String displayType) {
        if (DisplayTypeUtility.isValidDisplayType(displayType)) {
            this.displayType = displayType;
        }
    }

    public double getInsurancePremium() {
        return Utilities.toTwoDecimalPlaces(getPrice() * .06);
    }

    public String connectToInternet() {
        return "Connects to the internet via bluetooth";
    }

    public String toString() {
        String str = super.toString();

        str += "Display type: " + displayType + ". Insurance: €" + String.format("%.2f", getInsurancePremium()) + " per year. " +
                connectToInternet() + ".";
        return str;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SmartWatch that = (SmartWatch) o;
        return Objects.equals(displayType, that.displayType);
    }
}
