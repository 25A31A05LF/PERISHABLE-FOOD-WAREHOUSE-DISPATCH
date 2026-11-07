# Problem Statement

Managing perishable food products in a warehouse is challenging because every food batch has a specific expiry date. If products are not dispatched in the correct order, batches with earlier expiry dates may remain unused and eventually become waste.

Traditional inventory management methods may require repeated sorting or searching to identify the batches that should be dispatched first. This can increase processing effort when the warehouse contains many batches.

The problem is to develop an efficient warehouse dispatch system using a **Min-Heap data structure** that automatically prioritises food batches based on their expiry dates.

The system should support the following operations:

1. Insert new food batches into the warehouse.
2. Identify the batch with the earliest expiry date.
3. Dispatch food batches according to the First Expiry, First Out (FEFO) principle.
4. Update the expiry date of an existing batch.
5. Identify and remove expired batches.
6. Simulate a dispatch day and calculate dispatched and wasted quantities.
7. Generate reports showing inventory and food wastage details.

The system should also handle duplicate expiry dates, prevent duplicate batch IDs, and manage empty warehouse conditions.

The main objective is to improve inventory organisation, prioritise timely food distribution, and help reduce food wastage through efficient Min-Heap operations.
