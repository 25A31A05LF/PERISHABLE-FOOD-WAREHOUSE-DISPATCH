# Complexity Analysis

## 1. Introduction

The Perishable Food Warehouse Dispatch System uses a custom Min-Heap implemented with Java ArrayList and HashMap.

The Min-Heap organises food batches according to their expiry dates. The earliest-expiry batch is maintained at the root of the heap.

Let **n** represent the number of food batches stored in the warehouse.

## 2. Time Complexity of Operations

| Operation | Time Complexity | Explanation |
|---|---|---|
| Insert Batch | O(log n) | Inserts at the end and restores the heap using siftUp(). |
| Peek Earliest Batch | O(1) | Retrieves the root element directly. |
| Extract Minimum | O(log n) | Removes the root and restores the heap using siftDown(). |
| Update Expiry Date | O(log n) expected | Uses the index map for lookup and heap adjustment. |
| Search Batch by ID | O(1) expected | Uses HashMap lookup. |
| Display All Batches | O(n log n) | Copies and sorts the batches before displaying them. |
| Calculate Total Quantity | O(n) | Visits every batch to calculate the total quantity. |
| Delete Expired Batches | O(n log n) | Checks batches and removes expired entries while maintaining the heap. |
| Simulate Dispatch Day | O(n log n) | Extracts every batch from the heap. |
| Save Inventory | O(n log n) | Retrieves batches in sorted order before writing them to a file. |
| Load Inventory | O(n log n) expected | Inserts each valid record into the Min-Heap. |

*HashMap operations are expected O(1), assuming a suitable hash distribution.*

## 3. Space Complexity

The system uses the following additional storage:

- ArrayList: Stores n batch objects.
- HashMap: Stores n Batch ID and heap-index entries.
- Temporary lists: Used for deleted batches and sorted batch copies.

The overall space complexity is **O(n)**.

## 4. Comparison: Min-Heap vs Sorting

A Min-Heap is useful when batches need to be inserted and the earliest-expiry batch needs to be retrieved repeatedly.

| Operation | Min-Heap | Sorting All Batches |
|---|---|---|
| Insert one batch | O(log n) | O(n log n) if the collection is sorted again |
| Find earliest batch | O(1) | O(1) after sorting |
| Remove earliest batch | O(log n) | O(n) for an array-based sorted list |
| Retrieve all batches in order | O(n log n) | O(n) after sorting |

Sorting can be suitable when all batches are processed together, while a Min-Heap is useful for repeated priority-based insertion and extraction.

## 5. Conclusion

The custom Min-Heap provides efficient priority-based inventory management.

Insertion and extraction take O(log n) time, while identifying the earliest-expiry batch takes O(1) time. The index map also supports expected O(1) batch lookup.

Therefore, the Min-Heap is an appropriate data structure for managing perishable food batches according to their expiry dates.
