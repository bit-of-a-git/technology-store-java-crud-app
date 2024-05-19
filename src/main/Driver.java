package main;

import controllers.WearableDeviceAPI;
import models.SmartBand;
import models.SmartWatch;
import models.WearableDevice;
import utils.DisplayTypeUtility;
import utils.ManufacturerNameUtility;
import utils.ScannerInput;
import utils.Utilities;

public class Driver {
    // Driver instantiates a WearableDeviceAPI object to manage WearableDevices
    private final WearableDeviceAPI wearableAPI = new WearableDeviceAPI();

    public static void main(String[] args) throws Exception {
        new Driver().start();
    }

    public void start() {
        runMainMenu();
    }

    // The mainMenu() method both displays the main menu and fetches a user input
    private int mainMenu() {
        return ScannerInput.readNextInt("""
                   |--------WearableDevice Store--------|
                   |  1) WearableDevice CRUD Menu       |
                   |  2) Reports MENU                   |
                   |------------------------------------|
                   |  3) Search WearableDevice Devices  |
                   |  4) Sort WearableDevice Devices    |
                   |------------------------------------|
                   |  5) Save All                       |
                   |  6) Load All                       |
                   |------------------------------------|
                   |  0) Exit                           |
                   |------------------------------------|
                ==>>  """);
    }

    // The runMainMenu() method fetches the user input from mainMenu() and calls the desired method
    private void runMainMenu() {
        int option = mainMenu();
        while (option != 0) {

            switch (option) {
                case 1 -> crudMenu();
                case 2 -> reportsMenu();
                case 3 -> searchWearableDevices();
                case 4 -> sortWearableDevices();
                case 5 -> saveWearableDevices();
                case 6 -> loadWearableDevices();
                default -> System.out.println("Invalid option entered: " + option);
            }
            //pause the program so that the user can read what we just printed to the terminal window
            ScannerInput.readNextLine("\nPress enter key to continue...");
            //display the main menu again
            option = mainMenu();
        }
        exitApp();
    }

    // The crudMenu() method displays a menu, takes user input, and calls the desired method
    private void crudMenu() {
        int option = ScannerInput.readNextInt("""
                   |-----WearableDevice CRUD Menu-----|
                   |  1) ADD a WearableDevice         |
                   |  2) LIST all WearableDevice      |
                   |  3) UPDATE a WearableDevice      |
                   |  4) DELETE a WearableDevice      |
                   |----------------------------------|
                   |  0) Return to main menu          |
                   |----------------------------------|
                ==>>  """);

        switch (option) {
            case 1 -> addWearableDevice();
            case 2 -> System.out.println(wearableAPI.listAllWearableDevices());
            case 3 -> updateWearableDevice();
            case 4 -> deleteWearableDevice();
            case 0 -> runMainMenu();
            default -> System.out.println("Invalid option entered: " + option);
        }
    }

    /* The reportsMenu() method displays a menu, takes user input, and calls the desired method
       For some options, the method will take further input from users - e.g. entering a manufacturer
       to search for. */
    private void reportsMenu() {
        int option = ScannerInput.readNextInt("""
                   |--------------Wearable Device Reports Menu-------------|
                   |  1) List all technology                               |
                   |  2) List all SmartBands                               |
                   |  3) List all SmartWatches                             |
                   |  4) List all devices above a price                    |
                   |  5) List all devices below a price                    |
                   |  6) List the top five most expensive WearableDevices  |
                   |  7) List the top five most expensive SmartBands       |
                   |  8) List the top five most expensive SmartWatches     |
                   |  9) List all devices for a chosen Manufacturer        |
                   |-------------------------------------------------------|
                   |  0) Return to main menu                               |
                   |-------------------------------------------------------|
                ==>>  """);

        switch (option) {
            case 1 -> System.out.println(wearableAPI.listAllWearableDevices());
            case 2 -> System.out.println(wearableAPI.listAllSmartBands());
            case 3 -> System.out.println(wearableAPI.listAllSmartWatches());
            case 4 -> {
                double price = ScannerInput.readNextDouble("Enter a price to view WearableDevices above that price: ");
                System.out.println(wearableAPI.listAllWearableDeviceAbovePrice(price));
            }
            case 5 -> {
                double price = ScannerInput.readNextDouble("Enter a price to view WearableDevices below that price: ");
                System.out.println(wearableAPI.listAllWearableDeviceBelowPrice(price));
            }
            case 6 -> {
                for (WearableDevice wearableDevice : wearableAPI.topFiveMostExpensiveWearableDevice()) {
                    System.out.println(wearableDevice);
                }
            }
            case 7 -> {
                for (WearableDevice wearableDevice : wearableAPI.topFiveMostExpensiveSmartBand()) {
                    System.out.println(wearableDevice);
                }
            }
            case 8 -> {
                for (WearableDevice wearableDevice : wearableAPI.topFiveMostExpensiveSmartWatch()) {
                    System.out.println(wearableDevice);
                }
            }
            case 9 -> {
                String manufacturerName = readValidManufacturerName("Enter a manufacturer name from the following options to view WearableDevices made by that manufacturer: \n");
                System.out.println(wearableAPI.listAllTechDevicesByChosenManufacturer(manufacturerName));
            }
            case 0 -> runMainMenu();
            default -> System.out.println("Invalid option entered: " + option);
        }
    }

    // addWearableDevice() gives the user the option to add either a SmartBand or SmartWatch.
    private void addWearableDevice() {
        boolean isAdded = false;

        int option = ScannerInput.readNextInt("""
                ----------------------------
                |  1) Add a SmartBand      |
                |  2) Add a SmartWatch     |
                ---------------------------|
                |  0) Return to CRUD Menu  |
                ==>> """);

        switch (option) {
            case 1 -> {
                // Asks the user to input the object details, occasionally using helper methods like the below
                String manufacturerName = readValidManufacturerName("Please enter the manufacturer name from the following options: ");
                String modelName = ScannerInput.readNextLine("Enter the model name: ");
                double price = ScannerInput.readNextDouble("Enter the price: ");
                String size = ScannerInput.readNextLine("Enter the size: ");
                String material = ScannerInput.readNextLine("Enter the material it is made of: ");
                String id = readUniqueId("Enter the ID: ");
                char heartRateMonitor = ScannerInput.readNextChar("Is this SmartBand also a heart monitor (y/n): ");
                boolean isHeartRateMonitor = Utilities.YNtoBoolean(heartRateMonitor);
                isAdded = wearableAPI.addWearableDeviceDevice(new SmartBand(size, price, manufacturerName, material, modelName, id, isHeartRateMonitor));
                if (isAdded) {
                    System.out.println("WearableDevice added successfully");
                } else {
                    System.out.println("Error: WearableDevice was not added");
                }
            }
            case 2 -> {
                String manufacturerName = readValidManufacturerName("Please enter the manufacturer name from the following options: ");
                String modelName = ScannerInput.readNextLine("Enter the model name: ");
                double price = ScannerInput.readNextDouble("Enter the price: ");
                String size = ScannerInput.readNextLine("Enter the size: ");
                String material = ScannerInput.readNextLine("Enter the material it is made of: ");
                String id = readUniqueId("Enter the ID: ");
                String displayType = readValidDisplayType("Please enter a display type from the following options: ");
                isAdded = wearableAPI.addWearableDeviceDevice(new SmartWatch(size, price, manufacturerName, material, modelName, id, displayType));
                if (isAdded) {
                    System.out.println("WearableDevice added successfully");
                } else {
                    System.out.println("Error: WearableDevice was not added");
                }
            }
            case 0 -> crudMenu();
            default -> System.out.println("Invalid option entered: " + option);
        }
    }

    // updateWearableDevice() gives the user the option to update a SmartBand or a SmartWatch
    private void updateWearableDevice() {
        if (wearableAPI.numberOfWearableDevices() > 0) {
            boolean isUpdated = false;

            int option = ScannerInput.readNextInt("""
                    |--------------------------|
                    |  1) Update SmartBands    |
                    |  2) Update SmartWatches  |
                    |--------------------------|
                    |  0) Return to CRUD Menu  |
                    |--------------------------|
                    ==>> """);

            switch (option) {
                case 1 -> {
                    /* asks the user to enter the ID of the object to update. Assuming it is a valid ID and
                     is a SmartBand, gathers the new data from the user and updates the selected object. */
                    if (wearableAPI.numberOfSmartBands() > 0) {
                        listAllSmartBands();
                        String id = ScannerInput.readNextLine("Enter the id of the SmartBand to update ==> ");
                        if (!wearableAPI.isValidId(id) && wearableAPI.getWearableDeviceById(id) instanceof SmartBand) {
                            String newId = readUniqueId("Enter the new id: ");
                            String size = ScannerInput.readNextLine("Enter the new size: ");
                            double price = ScannerInput.readNextDouble("Enter the new price: ");
                            String manufacturerName = readValidManufacturerName("Enter the new manufacturer name from the following options: ");
                            String material = ScannerInput.readNextLine("Enter the new material it is made of: ");
                            String modelName = ScannerInput.readNextLine("Enter the new model name: ");
                            char heartRateMonitor = ScannerInput.readNextChar("Is this SmartBand also a heart monitor (y/n): ");
                            boolean isHeartRateMonitor = Utilities.YNtoBoolean(heartRateMonitor);
                            SmartBand updatedDetails = new SmartBand(size, price, manufacturerName, material, modelName, newId, isHeartRateMonitor);

                            isUpdated = wearableAPI.updateSmartBand(id, updatedDetails);
                            if (isUpdated) {
                                System.out.println("WearableDevice updated successfully");
                            } else {
                                System.out.println("Error: WearableDevice was not updated");
                            }
                        } else {
                            System.out.println("That is not a valid SmartBand ID.");
                        }
                    } else {
                        System.out.println("No SmartBands added yet");
                    }
                }
                case 2 -> {
                    /* asks the user to enter the ID of the object to update. Assuming it is a valid ID and
                     is a SmartWatch, gathers the new data from the user and updates the selected object. */
                    if (wearableAPI.numberOfSmartWatches() > 0) {
                        listAllSmartWatches();
                        String id = ScannerInput.readNextLine("Enter the id of the SmartWatch to update ==> ");
                        if (!wearableAPI.isValidId(id) && wearableAPI.getWearableDeviceById(id) instanceof SmartWatch) {
                            String newId = readUniqueId("Enter the new id: ");
                            String size = ScannerInput.readNextLine("Enter the new size: ");
                            double price = ScannerInput.readNextDouble("Enter the new price: ");
                            String manufacturerName = readValidManufacturerName("Enter the new manufacturer name from the following options: ");
                            String material = ScannerInput.readNextLine("Enter the new material it is made of: ");
                            String modelName = ScannerInput.readNextLine("Enter the new model name: ");
                            String displayType = readValidDisplayType("Enter the new display type from the following: ");
                            SmartWatch updatedDetails = new SmartWatch(size, price, manufacturerName, material, modelName, newId, displayType);

                            isUpdated = wearableAPI.updateSmartWatch(id, updatedDetails);
                            if (isUpdated) {
                                System.out.println("WearableDevice updated successfully");
                            } else {
                                System.out.println("Error: WearableDevice was not updated");
                            }
                        } else {
                            System.out.println("That is not a valid SmartBand ID.");
                        }
                    } else {
                        System.out.println("No SmartBands added yet");
                    }
                }
                case 0 -> runMainMenu();
                default -> System.out.println("Invalid option entered: " + option);
            }
        } else {
            System.out.println("No WearableDevices added yet");
        }
    }

    /* deleteWearableDevice() takes an index from the user, and assuming it is valid will delete and return
       the wearableDevice */
    private void deleteWearableDevice() {
        System.out.println(wearableAPI.listAllWearableDevices());
        //only asks the user to choose the WearableDevice to delete if the list is not empty
        if (wearableAPI.numberOfWearableDevices() > 0) {
            int indexToDelete = ScannerInput.readNextInt("Enter the index of the WearableDevice to delete ==> ");
            //passes the index of the wearableDevice to wearableAPI to delete and check for success.
            WearableDevice wearableDevice = wearableAPI.deleteWearableDeviceByIndex(indexToDelete);
            if (wearableDevice != null) {
                System.out.println("Delete Successful! Deleted post: " + wearableDevice);
            } else {
                System.out.println("Delete NOT Successful");
            }
        }
    }

    private void exitApp() {
        System.out.println("Exiting....");
        System.exit(0);
    }

    /* The searchWearableDevices displays a menu, takes user input, and calls the desired method,
        additionally fetching parameters from the user and passing them into the method. */
    private void searchWearableDevices() {
        int option = ScannerInput.readNextInt("""
                   |----WearableDevice Search Menu----|
                   |  1) Search by size               |
                   |  2) Search by material           |
                   |  3) Search by screen type        |
                   |  4) Search for heart monitors    |
                   |----------------------------------|
                   |  0) Return to main menu          |
                   |----------------------------------|
                ==>>  """);

        switch (option) {
            case 1 -> {
                String size = ScannerInput.readNextLine("Please enter a size to search for: ");
                System.out.println(wearableAPI.searchBySize(size));
            }
            case 2 -> {
                String material = ScannerInput.readNextLine("Please enter a material to search by: ");
                System.out.println(wearableAPI.searchByMaterial(material));
            }
            case 3 -> {
                String screenType = readValidDisplayType("Please enter a screen type to search by. Options are: ");
                System.out.println(wearableAPI.searchByScreenType(screenType));
            }
            case 4 -> {
                System.out.println(wearableAPI.searchForHeartMonitors());
            }
            case 0 -> runMainMenu();
            default -> System.out.println("Invalid option entered: " + option);
        }
    }

    /* The searchWearableDevices displays a menu and gives the user the option to sort the list by price
    ascending or descending. */
    private void sortWearableDevices() {

        int option = ScannerInput.readNextInt("""
                   |-----WearableDevice Sort Menu-----|
                   |  1) Sort by price ascending      |
                   |  2) Sort by price descending     |
                   |----------------------------------|
                   |  0) Return to main menu          |
                   |----------------------------------|
                ==>>  """);

        switch (option) {
            case 1 -> {
                wearableAPI.sortByPriceAscending();
                System.out.println(wearableAPI.listAllWearableDevices());
            }
            case 2 -> {
                wearableAPI.sortByPriceDescending();
                System.out.println(wearableAPI.listAllWearableDevices());
            }
            case 0 -> runMainMenu();
            default -> System.out.println("Invalid option entered: " + option);
        }
    }

    /* The saveWearableDevices() and loadWearableDevices() methods call the associated methods
       in WearableDeviceAPI, and will print an error message should there be an error. */
    private void saveWearableDevices() {
        try {
            wearableAPI.save();
        } catch (Exception e) {
            System.err.println("Error writing to file: " + e);
        }
    }

    private void loadWearableDevices() {
        try {
            wearableAPI.load();
        } catch (Exception e) {
            System.err.println("Error reading from file: " + e);
        }
    }

    /* The listAllSmartBands() and listAllSmartWatches() methods call and print the strings that are
       returned from the associated methods in WearableDeviceAPI. */
    private void listAllSmartBands() {
        System.out.println(wearableAPI.listAllSmartBands());
    }

    private void listAllSmartWatches() {
        System.out.println(wearableAPI.listAllSmartWatches());
    }

    /* readValidManufacturerName is a helper method which will ask for a manufacturerName String from
    the user, and repeat until a valid one is entered. */
    private String readValidManufacturerName(String message) {
        String manufacturerName = ScannerInput.readNextLine(message + utils.ManufacturerNameUtility.formatList() + ": ");
        while (!ManufacturerNameUtility.isValidManuName(manufacturerName)) {
            manufacturerName = ScannerInput.readNextLine("That was not a valid manufacturer name. Please pick one from the following options: " + utils.ManufacturerNameUtility.formatList() + ": ");
        }
        return manufacturerName;
    }

    /* readUniqueId is a helper method which will ask for an id String from the user, and repeat until a
     valid one is entered. */
    private String readUniqueId(String message) {
        String id = ScannerInput.readNextLine(message);
        while (!wearableAPI.isValidId(id)) {
            id = ScannerInput.readNextLine("Error: ID already exists in the system. Please enter a unique ID: ");
        }
        return id;
    }

    /* readValidDisplayType is a helper method which will ask for a displayType String from the user,
   and repeat until a valid one is entered. */
    private String readValidDisplayType(String message) {
        String displayType = ScannerInput.readNextLine(message + utils.DisplayTypeUtility.formatList() + ": ");
        while (!DisplayTypeUtility.isValidDisplayType(displayType)) {
            displayType = ScannerInput.readNextLine("That was not a valid display type. Please pick one from the following options: " + utils.DisplayTypeUtility.formatList() + ": ");
        }
        return displayType;
    }
}