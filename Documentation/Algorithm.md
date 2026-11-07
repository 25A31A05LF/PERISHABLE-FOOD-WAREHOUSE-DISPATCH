ALGORITHM – PERISHABLE FOOD WAREHOUSE DISPATCH SYSTEM

Algorithm Used:** FEFO (First Expired, First Out) using a Min-Heap.

Step 1: Start the Program
    Start the execution of the Perishable Food Warehouse Dispatch System.

Step 2: Initialize the Inventory System
  1. Create a Min-Heap to store food batches based on their expiry dates.
  2. Create a HashMap to track the positions of batches in the Min-Heap.
  3. Load previously saved inventory data from the file, if available.
  4. Initialize dispatch and waste counters to zero.

Step 3: Display the Main Menu

    Display the following menu options:

  1. Add a New Food Batch.
  2. View All Available Batches.
  3. View the Earliest Expiring Batch.
  4. Dispatch Food.
  5. Update Batch Expiry Date.
  6. Delete Expired Batches.
  7. Simulate a Dispatch Day.
  8. Generate Waste Report.
  9. Check Near-Expiry Batches.
  10. Exit.

Step 4: Perform the Selected Operation

  1. Add a New Food Batch** 
     Read the batch ID, food name, quantity and expiry date.
     Validate the entered details.
     Insert the new batch into the Min-Heap.

  2. View All Available Batches**
     Display all available food batches in expiry-date order.

  3. View the Earliest Expiring Batch**
     Retrieve and display the batch at the root of the Min-Heap.

  4. Dispatch Food**
     Accept the required food quantity.
     Dispatch food according to FEFO order.
     Update the remaining quantity.
     Remove the batch when its quantity becomes zero.

  5. Update Batch Expiry Date**
     Search for a batch using its batch ID.
     Update its expiry date.
     Restore the Min-Heap property.

  6. Delete Expired Batches**
     Identify batches whose expiry dates are earlier than the current date.
     Remove the expired batches.
	 Update the waste statistics.

  7. Simulate a Dispatch Day**
	 Accept a dispatch date.
	 Identify expired batches.
	 Dispatch eligible food stock according to FEFO order.

  8. Generate Waste Report**
	 Display the total number of wasted batches.
	 Display the total quantity of wasted food.

  9. Check Near-Expiry Batches**
	 Identify batches expiring within the next three days.
	 Display the near-expiry batches.

  10. Exit**
	 Save the inventory data into the file.
	 Terminate the program.

Step 5: Maintain FEFO Order
	 1. Maintain the Min-Heap property after every insertion, deletion or expiry-date update.
	 2. Use `siftUp()` and `siftDown()` operations to restore the heap property.
	 3. Ensure that the batch with the earliest expiry date remains at the root.
	 4. If two batches have the same expiry date, compare their batch IDs.

Step 6: Update Statistics
     Maintain the following statistics throughout the program:

	 1. Total dispatched quantity.
	 2. Total wasted quantity.
	 3. Number of dispatched batch entries.
	 4. Total wasted batch count.

Step 7: Repeat the Main Menu
	 Continue displaying the main menu and performing the selected operations until the user chooses the Exit option.

Step 8: Save Inventory and Stop
	 1. Save the remaining inventory data into `warehouse_inventory.txt`.
	 2. Terminate the program.

       END OF ALGORITHM
