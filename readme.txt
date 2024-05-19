
/* readme.txt

******************

Name: David O' Connor

Student Number: 08566496

Total: 107/110


Part A - Inheritance Hierarchy (24 / 24)
-----------------------------

WearableDevices (10 / 10)
SmartWatch( 7 / 7)
SmartBand ( 7 / 7)


Part B - TechnologyDeviceAPI  (34 /34)
-----------------------------
basic CRUD (13 /13 )
reporting/numberOf methods (8 / 8)
validation methods  & persistence  (5 / 5)
sorting / top 5 (8 / 8)


Part C - UX (user experience) and Driver:Mark (22 /23)
-----------------------------
Good Menu Structure  (3 / 4) - there are improvements I would like to have made to the structure, but I think they could potentially have added a lot of complexity to the actual code.

ArrayListCRUD  - all  types handled  (7 / 7)
Reports Menu - for all wearables (7 / 7)
Search, Sort, top5   (4 / 4)  
Save, Load, Exit   (1 / 1)    


Part D - DX (Overall style) (11%)	 (10 /11)
-----------------------------
Code correctly commented (3 / 3)
Standard naming, indentation, DRY Code etc. (4 / 5) - there were sections where I wanted to make the code more DRY (e.g. Driver add and update methods) but when I attempted to do so, it made the code extremely complex. In this case I decided to keep the code KISS.

Javadoc written for WearableDeviceAPI  (3 / 3)


Part E - For Extra Credit (10%)  (10 / 10)
-----------------------------
Please list the following for each extra functionality that you implemented
     the extra functionality 
     what changes this lead to from spec given
     where the extra implementation took place.


searchBySize
    This method allows users to enter a size and return all items that have that size.
    This is an addition to the spec as opposed to a change, an addition to the search functionality.
    526-538 of WearableDeviceAPI, 314 of Driver

searchByMaterial
    This method allows users to enter a material and return all items that have that material.
    This is an addition to the spec as opposed to a change, an addition to the search functionality.
    546-558 of WearableDeviceAPI, 318 of Driver

searchByScreenType
    This method allows users to enter a screen type and return all SmartWatches that have that screen type.
    This is an addition to the spec as opposed to a change, an addition to the search functionality.
    483-497 of WearableDeviceAPI, 322 of Driver

searchForHeartMonitors
    This method returns all SmartBands that have a heart monitor functionality.
    This is an addition to the spec as opposed to a change, an addition to the search functionality.
    504-518 of WearableDeviceAPI, 325 of Driver

The three "read" helper methods were implemented for DRY in the add and update methods.

readValidManufacturerName
    This method asks the user for a manufacturer name from a list of choices fetched from the ManufacturerNameUtility, which it fetches and displays to the user. It ensures the manufacturer name is valid, repeating until the user enters a valid name. It takes a message parameter, which it displays to the user before the list of valid manufacturer choices.
    This slightly changes the spec but leads to a better user experience. If a user slightly mistypes a name or forgets a letter, they will be prompted to retype the manufacturer name instead of going to the update screen, finding the details for the WearableDevice, and updating all the fields again. 
    389-395 of Driver

readUniqueId
    This method asks the user for an ID, checks whether it is valid, and repeats until the user enters a valid ID. It takes a message parameter, which it displays to the user.
    This slightly changes the spec but leads to a better user experience. If a user accidentally enters an ID which already exists, they will be prompted to retype the ID instead of having to restart the process all over again.
    399-405 of Driver
  
readValidDisplayType
    This method asks the user for a display type from a list of choices fetched from the DisplayTypeUtility, which it fetches and displays to the user. It checks whether the display type is valid, and repeats until the user enters a valid type. It takes a message parameter, which it displays to the user.
    This slightly changes the spec but leads to a better user experience. If a user slightly mistypes a display type or forgets a letter, they will be prompted to retype the display type instead of going through the process all over again.
    409-416 of Driver

DisplayTypeUtility.formatList
    This method was implemented to display a list of valid display types to the user to ensure they select an allowable one. It is fetched from Driver and added to the string displayed to the user.
    This does not change the spec but leads to a better user experience. It is clear to the user which display types are allowable and will change if any further allowable types are added to the DisplayTypeUtility class.
    26-36 of DisplayTypeUtility, 410-412 of Driver

ManufacturerNameUtility.formatList
    This method was implemented to display a list of valid manufacturer names to the user to ensure they select an allowable one. It is fetched from Driver and added to the string displayed to the user.
    This does not change the spec but leads to a better user experience. It is clear to the user which manufacturers are allowable and will change if any further allowable types are added to the ManufacturerNameUtility class.
    26-36 of ManufacturerNameUtility, 390-392 of Driver


Part F - Reflection (7 / 8)
-----------------------------
Filled out parts A - D above (3 / 3)

Rest(4 / 5)


Chronology of my implementation (what I did first, etc.. )

    I followed the suggested plan of action exactly and felt that it provided good guidance. I started by writing the three classes. I noticed mentions of abstract classes and methods but we had not covered these at this point, so I noted them for later. I wrote the WearableDeviceAPI controller code and most of the Driver. I referenced previous labs for this, like SocialNetwork or College. I would estimate I had about 75%-80% of this done before I moved back to the Web Development assignment.

    When I came back to this assignment, I mainly focused on ensuring my code was working as expected. I wrote helper methods, tested, wrote the Javadoc, wrote extra methods for searching for specific materials or screen types. I also had extra menus that I deleted for a smoother user experience.

Main difficulties encountered during development of solution and how they were solved:

    One of the main problems I had was implementing the File field. I did not have any examples of this in the labs, so I had to do some research to find out how to implement it. I also wanted to implement things like returning prices/insurance costs to two digits after the decimal point instead of just one, and adding the class name to the string. I did some research and found quick and easy solutions to do so which I added to my toStrings.

Any bugs remaining in the solution or unfinished elements of spec (no need if you have detailed these in the rubric section):

    There are no unfinished elements of the spec, but there are some features I would have liked to implement. I would have liked to allow the user to press enter to keep the previous value when updating various fields.

Main learnings from my engagement with assignment:

    I learned a lot about OOP and concepts such as abstraction.


-----------------------------
References used in development/implementation of this submission:

    https://www.geeksforgeeks.org/file-class-in-java/
    https://docs.oracle.com/javase/8/docs/api/java/io/File.html
    https://stackoverflow.com/questions/1196586/calling-remove-in-foreach-loop-in-java
    https://www.upgrad.com/tutorials/software-engineering/java-tutorial/selection-sort-java/
    https://mkyong.com/java/java-display-double-in-2-decimal-points/
    https://stackoverflow.com/questions/6271417/java-get-the-current-class-name

-----------------------------


This is my work apart from the specific references noted above (and any code from class notes). I understand the code and can describe any parts of the solution if needs be.