# Test Cases

## 1. Introduction

Testing is performed to verify that the Perishable Food Warehouse Dispatch System works correctly and maintains the Min-Heap property during inventory operations.

The following test cases cover batch management, heap operations, expiry handling, dispatch processing and file storage.

## 2. Test Case Table

| Test ID | Test Scenario | Input / Condition | Expected Result |
|---|---|---|---|
| TC01 | Add a valid batch | Valid ID, food name, positive quantity and expiry date | Batch is added successfully. |
| TC02 | Add duplicate Batch ID | Enter an existing Batch ID | Duplicate batch is rejected. |
| TC03 | Add invalid quantity | Enter zero or a negative quantity | Batch is rejected. |
| TC04 | Add empty batch details | Enter an empty ID or food name | Invalid batch details are rejected. |
| TC05 | Peek earliest expiry | Multiple batches exist | Batch with the earliest expiry date is displayed. |
| TC06 | Extract earliest batch | Multiple batches exist | Earliest-expiry batch is removed. |
| TC07 | Duplicate expiry dates | Two batches have the same expiry date | Batch IDs determine their ordering. |
| TC08 | Update expiry date | Enter an existing Batch ID and valid new date | Expiry date is updated and heap order is restored. |
| TC09 | Update nonexistent batch | Enter an unknown Batch ID | Batch ID not found message is displayed. |
| TC10 | Delete expired batches | One or more batches have expiry dates before today | Expired batches are removed and waste is calculated. |
| TC11 | Empty warehouse | Perform operations without any batches | Appropriate empty-warehouse message is displayed. |
| TC12 | Dispatch required quantity | Request a quantity available in the warehouse | Food is dispatched according to FEFO order. |
| TC13 | Partial batch dispatch | Request less than the earliest batch quantity | Only the requested quantity is dispatched; remaining stock is retained. |
| TC14 | Near-expiry alert | Batch expiry falls within the next three days, including today | Matching batch is displayed. |
| TC15 | Simulate dispatch day | Provide a dispatch date and existing batches | Batches are classified as dispatched or wasted. |
| TC16 | Save inventory | Add or modify batches | Inventory is written to the text file. |
| TC17 | Load inventory | Restart with previously saved inventory | Valid saved batches are loaded. |

## 3. Important Edge Cases

### Duplicate Expiry Dates

When two batches have identical expiry dates, the system compares their Batch IDs to determine their order.

### Empty Heap

When the warehouse contains no batches, operations such as peek and extraction should handle the empty condition without causing an error.

### Expired Batches

A batch is considered expired when its expiry date is earlier than the relevant current or dispatch date. A batch expiring on that date is not treated as expired.

### Partial Dispatch

If the requested quantity is smaller than the available quantity in the earliest-expiry batch, only the requested quantity is deducted.

## 4. Testing Status

The test cases listed above describe the planned verification scenarios. Actual pass/fail results should be recorded after executing each test against the Java application.

## 5. Conclusion

These test cases help verify the correctness of batch insertion, Min-Heap operations, expiry-date management, dispatch processing, waste calculation and inventory file handling.
