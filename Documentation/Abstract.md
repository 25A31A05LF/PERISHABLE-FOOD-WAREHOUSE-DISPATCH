# Abstract

The Perishable Food Warehouse Dispatch System is a Java-based project that uses a custom Min-Heap data structure to manage perishable food inventory efficiently.

The system follows the First Expiry, First Out (FEFO) approach, ensuring that food batches with earlier expiry dates are processed before batches with later expiry dates. Each batch contains a unique ID, food name, quantity, and expiry date.

The Min-Heap organises batches according to their expiry dates. An additional index map helps locate batches efficiently during update and deletion operations. The system supports inserting batches, viewing the earliest-expiring batch, dispatching food quantities, updating expiry dates, deleting expired batches, and simulating a dispatch day.

It also provides near-expiry alerts, warehouse reports, and file-based inventory storage. The implementation demonstrates the practical use of heap operations in inventory management and aims to support better stock rotation and reduce food wastage.

**Keywords:** Min-Heap, Priority Queue, FEFO, Java, Warehouse Management, Expiry Date, Inventory Management.
