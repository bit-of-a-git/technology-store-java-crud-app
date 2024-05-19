package models;

import utils.Utilities;

public class SmartBand extends WearableDevice {
    private boolean heartRateMonitor;

    public SmartBand(String size, double price, String manufacturerName, String material, String modelName, String id, boolean heartRateMonitor) {
        super(size, price, manufacturerName, material, modelName, id);
        this.heartRateMonitor = heartRateMonitor;
    }

    public boolean isHeartRateMonitor() {
        return heartRateMonitor;
    }

    public void setHeartRateMonitor(boolean heartRateMonitor) {
        this.heartRateMonitor = heartRateMonitor;
    }

    public double getInsurancePremium() {
        return Utilities.toTwoDecimalPlaces(getPrice() * .07);
    }

    public String connectToInternet() {
        return "Connects to the internet via Companion App";
    }

    public String toString() {
        String str = super.toString();

        if (isHeartRateMonitor()) {
            str += "Includes Heart Rate Monitor. ";
        } else {
            str += "No Heart Rate Monitor included. ";
        }
        str += "Insurance: €" + String.format("%.2f", getInsurancePremium()) + " per year. " + connectToInternet() + ".";
        return str;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SmartBand smartBand = (SmartBand) o;
        return heartRateMonitor == smartBand.heartRateMonitor;
    }
}
