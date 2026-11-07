METHODOLOGY – PERISHABLE FOOD WAREHOUSE DISPATCH SYSTEM

1. SYSTEM INITIALIZATION

   The system starts by initializing the inventory management process.
   
   a) Create a Min-Heap to store food batches based on expiry dates.
   b) Create a HashMap to track the positions of batches.
   c) Load previously saved inventory data from the file, if available.
   d) Initialize dispatch and waste counters.

2. INVENTORY MANAGEMENT

   The system allows users to manage food batches efficiently.

   a) Add new food batches by entering batch ID, food name, quantity and expiry date.
   b) View all available food batches.
   c) Display the earliest expiring food batch.
   d) Update the expiry date of an existing batch.

3. FEFO-BASED DISPATCH MECHANISM

   The system follows the FEFO (First Expired, First Out) principle.

   a) Arrange food batches according to their expiry dates.
   b) Give priority to the batch with the earliest expiry date.
   c) Dispatch food from the earliest expiring batch first.
   d) Update the remaining quantity after dispatch.
   e) Remove a batch when its quantity becomes zero.

4. MIN-HEAP OPERATIONS

   A Min-Heap is used to maintain the priority of food batches.

   a) Use siftUp() after inserting a new batch.
   b) Use siftDown() to restore the heap property when required.
   c) Maintain the Min-Heap after deletion or expiry-date updates.
   d) Compare batch IDs when two batches have the same expiry date.

5. EXPIRY DATE MONITORING

   The system continuously monitors food expiry dates.

   a) Identify batches whose expiry dates are earlier than the current date.
   b) Delete expired batches from the inventory.
   c) Identify batches that will expire within the next three days.
   d) Display near-expiry batches to the user.

6. DISPATCH DAY SIMULATION

   The system provides a feature to simulate daily warehouse dispatch operations.

   a) Accept a dispatch date from the user.
   b) Identify expired food batches.
   c) Select eligible food batches for dispatch.
   d) Dispatch food according to the FEFO principle.
   e) Update inventory quantities and statistics.

7. WASTE MANAGEMENT AND REPORT GENERATION

   The system maintains information about food wastage.

   a) Calculate the total wasted food quantity.
   b) Count the total number of wasted batches.
   c) Maintain dispatched food quantity.
   d) Generate a waste report displaying the collected statistics.

8. FILE HANDLING AND DATA PERSISTENCE

   File handling is used to store inventory information permanently.

   a) Store inventory records in warehouse_inventory.txt.
   b) Save the remaining inventory when the user exits.
   c) Load previously saved records during the next execution.
   d) Maintain inventory information between program executions.

9. PROGRAM TERMINATION

   a) Display the main menu repeatedly until the user selects Exit.
   b) Save the remaining inventory data into the file.
   c) Close the file operations.
   d) Terminate the program successfully.

CONCLUSION

The Perishable Food Warehouse Dispatch System provides an efficient approach to managing perishable food inventory using Java programming. It implements the FEFO principle with Min-Heap and HashMap data structures to prioritize food batches based on expiry dates. The system also provides inventory management, expiry monitoring, dispatch simulation and waste reporting facilities to help reduce food wastage and improve warehouse management.
