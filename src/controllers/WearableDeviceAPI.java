package controllers;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import models.SmartBand;
import models.SmartWatch;
import models.WearableDevice;
import utils.ISerializer;
import utils.Utilities;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The WearableDeviceAPI class provides a collection of methods to manage a list of WearableDevice objects.
 * It supports adding, reading, updating, and deleting WearableDevice objects, as well as loading and saving the list to a file.
 */
public class WearableDeviceAPI implements ISerializer {
    private List<WearableDevice> wearableList;
    private final File file;

    /**
     * Constructs a WearableDeviceAPI with an empty list of WearableDevices and sets the file to use to persist data.
     */
    public WearableDeviceAPI() {
        wearableList = new ArrayList<WearableDevice>();
        file = new File("wearabledevices.xml");
    }

    // Add/create methods

    /**
     * Adds a WearableDevice to the list.
     *
     * @param wearableDevice the WearableDevice to add.
     * @return true if the WearableDevice was added, false otherwise.
     */
    public boolean addWearableDeviceDevice(WearableDevice wearableDevice) {
        return wearableList.add(wearableDevice);
    }

    // Delete methods

    /**
     * Deletes a WearableDevice from the list using its index.
     *
     * @param index the index of the WearableDevice to delete.
     * @return the deleted WearableDevice, or null if the index is invalid.
     */
    public WearableDevice deleteWearableDeviceByIndex(int index) {
        if (Utilities.isValidIndex(wearableList, index)) {
            return wearableList.remove(index);
        }
        return null;
    }

    /**
     * Deletes a WearableDevice from the list using its ID.
     *
     * @param id the ID of the WearableDevice to delete.
     * @return the deleted WearableDevice, or null if no WearableDevice with the given ID is found.
     */
    public WearableDevice deleteWearableDeviceById(String id) {
        /* To safely remove from a collection while iterating over it, you should use an iterator.
        https://stackoverflow.com/questions/1196586/calling-remove-in-foreach-loop-in-java */
        Iterator<WearableDevice> iterator = wearableList.iterator();
        while (iterator.hasNext()) {
            WearableDevice techDev = iterator.next();
            if (techDev.getId().equalsIgnoreCase(id)) {
                iterator.remove();
                return techDev;
            }
        }
        return null;
    }

    // Get methods

    /**
     * Retrieves a WearableDevice from the list using its index.
     *
     * @param index the index of the WearableDevice to retrieve.
     * @return the WearableDevice at the specified index, or null if the index is invalid.
     */
    public WearableDevice getWearableDeviceByIndex(int index) {
        if (Utilities.isValidIndex(wearableList, index)) {
            return wearableList.get(index);
        }
        return null;
    }

    /**
     * Retrieves a WearableDevice from the list using its ID.
     *
     * @param id the ID of the WearableDevice to retrieve.
     * @return the WearableDevice with the specified ID, or null if no such WearableDevice is found.
     */
    public WearableDevice getWearableDeviceById(String id) {
        for (WearableDevice techDev : wearableList) {
            if (techDev.getId().equalsIgnoreCase(id)) {
                return techDev;
            }
        }
        return null;
    }

    /**
     * Gets the name of the file used to persist data.
     *
     * @return the name of the file.
     */
    public String fileName() {
        return file.getName();
    }

    // Read/reporting/list methods

    /**
     * Lists all WearableDevices in the list.
     *
     * @return a string representation of all WearableDevices or a message indicating the list is empty.
     */
    public String listAllWearableDevices() {
        if (wearableList.isEmpty()) {
            return "No WearableDevice Devices";
        } else {
            String listOfWearableDevices = "";
            for (int i = 0; i < wearableList.size(); i++) {
                listOfWearableDevices += i + ": " + wearableList.get(i) + "\n";
            }
            return listOfWearableDevices;
        }
    }

    /**
     * Lists all SmartBands in the list.
     *
     * @return a string representation of all SmartBands, a message indicating the list is empty, or a message indicating the list contains no SmartBands.
     */
    public String listAllSmartBands() {
        if (wearableList.isEmpty()) {
            return "No WearableDevice Devices";
        } else {
            String str = "";
            for (WearableDevice techdev : wearableList) {
                if (techdev instanceof SmartBand) {
                    str += wearableList.indexOf(techdev) + ": " + techdev + "\n";
                }
            }
            if (str.isEmpty()) {
                return "No Smart Bands";
            } else {
                return str;
            }
        }
    }

    /**
     * Lists all SmartWatches in the list.
     *
     * @return a string representation of all SmartWatches, a message indicating the list is empty, or a message indicating the list contains no SmartWatches.
     */
    public String listAllSmartWatches() {
        if (wearableList.isEmpty()) {
            return "No WearableDevice Devices";
        } else {
            String str = "";
            for (WearableDevice techdev : wearableList) {
                if (techdev instanceof SmartWatch) {
                    str += wearableList.indexOf(techdev) + ": " + techdev + "\n";
                }
            }
            if (str.isEmpty()) {
                return "No Smart Watches";
            } else {
                return str;
            }
        }
    }

    /**
     * Lists all WearableDevices with a price above the specified value.
     *
     * @param price the price threshold.
     * @return a string representation of WearableDevices with a price above the specified value, a message indicating the list is empty, or a message indicating that the list contains no WearableDevices over that price.
     */
    public String listAllWearableDeviceAbovePrice(double price) {
        if (wearableList.isEmpty()) {
            return "No WearableDevice Devices";
        } else {
            String str = "";
            for (int i = 0; i < wearableList.size(); i++) {
                if (wearableList.get(i).getPrice() > price)
                    str += i + ": " + wearableList.get(i) + "\n";
            }
            if (str.isEmpty()) {
                return "No WearableDevice more expensive than: " + price;
            } else {
                return str;
            }
        }
    }

    /**
     * Lists all WearableDevices with a price below the specified value.
     *
     * @param price the price threshold.
     * @return a string representation of WearableDevices with a price below the specified value, a message indicating the list is empty, or a message indicating that the list contains no WearableDevices under that price.
     */
    public String listAllWearableDeviceBelowPrice(double price) {
        if (wearableList.isEmpty()) {
            return "No WearableDevice Devices";
        } else {
            String str = "";
            for (int i = 0; i < wearableList.size(); i++) {
                if (wearableList.get(i).getPrice() < price)
                    str += i + ": " + wearableList.get(i) + "\n";
            }
            if (str.isEmpty()) {
                return "No WearableDevice cheaper than: " + price;
            } else {
                return str;
            }
        }
    }

    /**
     * Lists all WearableDevices by the specified manufacturer.
     *
     * @param manufacturerName the name of the manufacturer.
     * @return a string representation of all WearableDevices by the specified manufacturer, a message indicating the list is empty, or a message indicating the list contains no WearableDevices by that manufacturer.
     */
    public String listAllTechDevicesByChosenManufacturer(String manufacturerName) {
        if (wearableList.isEmpty()) {
            return "No WearableDevice Devices";
        } else {
            String str = "";
            for (WearableDevice techdev : wearableList) {
                if (techdev.getManufacturerName().equalsIgnoreCase(manufacturerName)) {
                    str += techdev + "\n";
                }
            }
            if (str.isEmpty()) {
                return "No WearableDevices made by: " + manufacturerName;
            } else {
                return str;
            }
        }
    }

    // Number methods

    /**
     * Gets the total number of WearableDevices in the list.
     *
     * @return the total number of WearableDevices.
     */
    public int numberOfWearableDevices() {
        return wearableList.size();
    }

    /**
     * Gets the total number of SmartBands in the list.
     *
     * @return the total number of SmartBands.
     */
    public int numberOfSmartBands() {
        int count = 0;
        for (WearableDevice techdev : wearableList) {
            if (techdev instanceof SmartBand) {
                count += 1;
            }
        }
        return count;
    }

    /**
     * Gets the total number of SmartWatches in the list.
     *
     * @return the total number of SmartWatches.
     */
    public int numberOfSmartWatches() {
        int count = 0;
        for (WearableDevice techdev : wearableList) {
            if (techdev instanceof SmartWatch) {
                count += 1;
            }
        }
        return count;
    }

    /**
     * Gets the total number of WearableDevices by the specified manufacturer.
     *
     * @param manufacturerName the name of the manufacturer.
     * @return the total number of WearableDevices by the specified manufacturer.
     */
    public int numberOfWearableDeviceByManufacturer(String manufacturerName) {
        int count = 0;
        for (WearableDevice techdev : wearableList) {
            if (techdev.getManufacturerName().equalsIgnoreCase(manufacturerName)) {
                count++;
            }
        }
        return count;
    }

    // Update methods

    /**
     * Takes an id, updates the item's details if the ID is present and the item is a SmartWatch.
     *
     * @param id the ID of the SmartWatch to update.
     * @param updatedDetails the new details of the SmartWatch.
     * @return true if the SmartWatch was updated, false otherwise.
     */
    public boolean updateSmartWatch(String id, SmartWatch updatedDetails) {
        for (WearableDevice techDev : wearableList) {
            if (techDev instanceof SmartWatch && techDev.getId().equalsIgnoreCase(id)) {
                techDev.setSize(updatedDetails.getSize());
                techDev.setPrice(updatedDetails.getPrice());
                techDev.setManufacturerName(updatedDetails.getManufacturerName());
                techDev.setMaterial(updatedDetails.getMaterial());
                techDev.setModelName(updatedDetails.getModelName());
                techDev.setId(updatedDetails.getId());
                ((SmartWatch) techDev).setDisplayType(updatedDetails.getDisplayType());
                return true;
            }
        }
        return false;
    }

    /**
     * Takes an id, updates the item's details if the ID is present and the item is a SmartBand.
     *
     * @param id the ID of the SmartBand to update.
     * @param updatedDetails the new details of the SmartBand.
     * @return true if the SmartBand was updated, false otherwise.
     */
    public boolean updateSmartBand(String id, SmartBand updatedDetails) {
        for (WearableDevice techDev : wearableList) {
            if (techDev instanceof SmartBand && techDev.getId().equalsIgnoreCase(id)) {
                techDev.setSize(updatedDetails.getSize());
                techDev.setPrice(updatedDetails.getPrice());
                techDev.setManufacturerName(updatedDetails.getManufacturerName());
                techDev.setMaterial(updatedDetails.getMaterial());
                techDev.setModelName(updatedDetails.getModelName());
                techDev.setId(updatedDetails.getId());
                ((SmartBand) techDev).setHeartRateMonitor(updatedDetails.isHeartRateMonitor());
                return true;
            }
        }
        return false;
    }

    // Validation Methods

    /**
     * Checks if a given ID is valid. If already used by a WearableDevice in the list, an ID is not valid.
     * Can also be used to check if an ID exists in the list.
     *
     * @param id the ID to check.
     * @return true if the ID is valid, false otherwise.
     */
    public boolean isValidId(String id) {
        for (WearableDevice techDev : wearableList) {
            // Changed to ignore case as specified on Tutors
            if (techDev.getId().equalsIgnoreCase(id))
                return false;
        }
        return true;
    }

    // Sorting Methods

    // https://www.upgrad.com/tutorials/software-engineering/java-tutorial/selection-sort-java/

    /**
     * Sorts the list of WearableDevices by price in ascending order.
     */
    public void sortByPriceAscending() {
        for (int i = 0; i < wearableList.size() - 1; i++) {
            int lowestIndex = i;
            for (int j = i + 1; j < wearableList.size(); j++) {
                if (wearableList.get(j).getPrice() < wearableList.get(lowestIndex).getPrice()) {
                    lowestIndex = j;
                }
            }
            swapWearableDevice(wearableList, lowestIndex, i);
        }
    }

    /**
     * Sorts the list of WearableDevices by price in descending order.
     */
    public void sortByPriceDescending() {
        for (int i = 0; i < wearableList.size() - 1; i++) {
            int highestIndex = i;
            for (int j = i + 1; j < wearableList.size(); j++) {
                if (wearableList.get(j).getPrice() > wearableList.get(highestIndex).getPrice()) {
                    highestIndex = j;
                }
            }
            swapWearableDevice(wearableList, highestIndex, i);
        }
    }

    // Top 5 methods

    /**
     * Gets the top five most expensive WearableDevices.
     *
     * @return a list of the top five most expensive WearableDevices or null if the list is empty.
     */
    public List<WearableDevice> topFiveMostExpensiveWearableDevice() {
        if (!wearableList.isEmpty()) {
            sortByPriceDescending();
            if (wearableList.size() <= 5) {
                return wearableList;
            }
            return wearableList.subList(0, 5);
        } else {
            return null;
        }
    }

    /**
     * Gets the top five most expensive SmartWatches.
     *
     * @return a list of the top five most expensive SmartWatches or null if the list is empty.
     */
    public List<WearableDevice> topFiveMostExpensiveSmartWatch() {
        if (!wearableList.isEmpty()) {
            sortByPriceDescending();
            List<WearableDevice> topFiveMostExpensiveSmartWatches = new ArrayList<>();
            for (WearableDevice techDev : wearableList) {
                if (techDev instanceof SmartWatch) {
                    topFiveMostExpensiveSmartWatches.add(techDev);
                }
                if (topFiveMostExpensiveSmartWatches.size() == 5) {
                    break;
                }
            }
            return topFiveMostExpensiveSmartWatches;
        } else {
            return null;
        }
    }

    /**
     * Gets the top five most expensive SmartBands.
     *
     * @return a list of the top five most expensive SmartBands or null if the list is empty.
     */
    public List<WearableDevice> topFiveMostExpensiveSmartBand() {
        if (!wearableList.isEmpty()) {
            sortByPriceDescending();
            List<WearableDevice> topFiveMostExpensiveSmartBands = new ArrayList<>();
            for (WearableDevice techDev : wearableList) {
                if (techDev instanceof SmartBand) {
                    topFiveMostExpensiveSmartBands.add(techDev);
                }
                if (topFiveMostExpensiveSmartBands.size() == 5) {
                    break;
                }
            }
            return topFiveMostExpensiveSmartBands;
        } else {
            return null;
        }
    }

    // Search methods

    /**
     * Searches for SmartWatches with the specified screen type.
     *
     * @param screenType the screen type to search for.
     * @return a string representation of the matching SmartWatches, or a message indicating no matches were found.
     */
    public String searchByScreenType(String screenType) {
        String matchingModels = "";
        for (WearableDevice techDev : wearableList) {
            if (techDev instanceof SmartWatch) {
                if (((SmartWatch) techDev).getDisplayType().equalsIgnoreCase(screenType)) {
                    matchingModels += wearableList.indexOf(techDev) + ": " + techDev + "\n";
                }
            }
        }
        if (matchingModels.equals("")) {
            return "No SmartWatches match your search";
        } else {
            return matchingModels;
        }
    }

    /**
     * Searches for SmartBands with heart monitor functionalities.
     *
     * @return a string containing matching WearableDevices, or a message indicating no matches were found.
     */
    public String searchForHeartMonitors() {
        String matchingModels = "";
        for (WearableDevice techDev : wearableList) {
            if (techDev instanceof SmartBand) {
                if (((SmartBand) techDev).isHeartRateMonitor()) {
                    matchingModels += wearableList.indexOf(techDev) + ": " + techDev + "\n";
                }
            }
        }
        if (matchingModels.equals("")) {
            return "No SmartBands match your search";
        } else {
            return matchingModels;
        }
    }

    /**
     * Searches for WearableDevices with the specified size.
     *
     * @param size the size to search for.
     * @return a string containing matching WearableDevices, or a message indicating no matches were found.
     */
    public String searchBySize(String size) {
        String matchingModels = "";
        for (WearableDevice techdev : wearableList) {
            if (techdev.getSize().toUpperCase().contains(size.toUpperCase())) {
                matchingModels += wearableList.indexOf(techdev) + ": " + techdev + "\n";
            }
        }
        if (matchingModels.equals("")) {
            return "No WearableDevices match your search";
        } else {
            return matchingModels;
        }
    }

    /**
     * Searches for WearableDevices with the specified material.
     *
     * @param material the material to search for.
     * @return a string containing matching WearableDevices, or a message indicating no matches were found.
     */
    public String searchByMaterial(String material) {
        String matchingModels = "";
        for (WearableDevice techdev : wearableList) {
            if (techdev.getMaterial().toUpperCase().contains(material.toUpperCase())) {
                matchingModels += wearableList.indexOf(techdev) + ": " + techdev + "\n";
            }
        }
        if (matchingModels.equals("")) {
            return "No WearableDevices match your search";
        } else {
            return matchingModels;
        }
    }

    // Persistence methods

    /**
     * The load method uses the XStream component to read all the models.WearableDevice objects from the wearabledevices.xml
     * file stored on the hard disk.  The read objects are loaded into the wearableList ArrayList
     *
     * @throws Exception An exception is thrown if an error occurred during the load e.g. a missing file.
     */
    @SuppressWarnings("unchecked")
    public void load() throws Exception {
        //list of classes that you wish to include in the serialisation, separated by a comma
        Class<?>[] classes = new Class[]{SmartBand.class, SmartWatch.class, WearableDevice.class};

        //setting up the xstream object with default security and the above classes
        XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);

        //doing the actual serialisation to an XML file
        ObjectInputStream in = xstream.createObjectInputStream(new FileReader(this.file));
        wearableList = (List<WearableDevice>) in.readObject();
        in.close();
    }

    /**
     * The save method uses the XStream component to write all the objects in the wearableList ArrayList
     * to the wearabledevices.xml file stored on the hard disk.
     *
     * @throws Exception An exception is thrown if an error occurred during the save e.g. drive is full.
     */
    public void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter(this.file));
        out.writeObject(wearableList);
        out.close();
    }

    /**
     * Swaps the positions of two WearableDevices in the list.
     *
     * @param wearableList the list of WearableDevices.
     * @param i the index of the first WearableDevice.
     * @param j the index of the second WearableDevice.
     */
    private void swapWearableDevice(List<WearableDevice> wearableList, int i, int j) {
        WearableDevice smaller = wearableList.get(i);
        WearableDevice bigger = wearableList.get(j);

        wearableList.set(i, bigger);
        wearableList.set(j, smaller);
    }
}
