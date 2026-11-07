# 🥗 Perishable Food Warehouse Dispatch System

A Java-based warehouse inventory management system that uses a **Min-Heap data structure** to dispatch perishable food batches according to the **First Expiry, First Out (FEFO)** principle.

The system prioritises food batches with the earliest expiry dates to support efficient dispatch and reduce food wastage.

## 🎯 Project Objectives

- Manage perishable food inventory efficiently.
- Implement a Min-Heap for expiry-based prioritisation.
- Dispatch batches according to the FEFO principle.
- Update batch expiry dates.
- Identify and remove expired batches.
- Generate near-expiry alerts and warehouse reports.
- Analyse the time complexity of heap operations.

## 🧠 Data Structure Used

### Min-Heap

A Min-Heap is a complete binary tree in which the parent node has a priority value less than or equal to its children.

In this project:
- Each food batch is prioritised using its expiry date.
- The batch with the earliest expiry date is stored at the root.
- If two batches have the same expiry date, their Batch IDs are used as a secondary comparison.
- The earliest-expiring batch can be accessed efficiently.

**Why Min-Heap?**

A Min-Heap supports efficient insertion and extraction, making it suitable for managing food batches according to expiry priority.

## 🏗️ Project Structure

```text
PERISHABLE-FOOD-WAREHOUSE-DISPATCH/
│
├── Source_Code/
│   ├── Batch.java
│   ├── MinHeap.java
│   └── WarehouseDispatchSystem.java
│
├── Documentation/
│   ├── Introduction.md
│   ├── Abstract.md
│   ├── Problem_Statement.md
│   ├── Objectives.md
│   ├── Algorithm.md
│   ├── Methodology.md
│   ├── Complexity_Analysis.md
│   ├── Test_Cases.md
│   ├── Conclusion.md
│   ├── Future_Scope.md
│   └── References.md
│
├── Output_Screenshots/
│   ├── Main_Menu.png
│   ├── FEFO_Inventory.png
│   ├── Near_Expiry_Alert.png
│   ├── Dispatch_Report.png
│   └── Warehouse_Report.png
│
├── .gitignore
└── README.md
```

## ⚙️ Technologies Used

- **Programming Language:** Java
- **Data Structure:** Min-Heap
- **Collections:** ArrayList and HashMap
- **Date Handling:** LocalDate
- **File Handling:** Java I/O and NIO
- **Development Environment:** Any Java-compatible IDE or JDK

## ✨ Key Features

1. **Add Food Batch** – Add a food batch with its ID, name, quantity and expiry date.
2. **View All Batches** – Display inventory in FEFO order.
3. **Peek Earliest Expiry** – View the batch with the earliest expiry date without removing it.
4. **Dispatch Required Quantity** – Dispatch food according to expiry priority.
5. **Update Batch Shelf Life** – Update the expiry date of an existing batch.
6. **Delete Expired Batches** – Remove batches that have already expired.
7. **Simulate Dispatch Day** – Simulate dispatch and classify batches as dispatched or wasted.
8. **Near Expiry Alert** – Identify batches expiring within the next three days.
9. **Warehouse Report** – Display dispatch, wastage and remaining inventory statistics.
10. **Inventory Persistence** – Save and load inventory using a text file.

## 🔄 Working Procedure

1. Start the application.
2. Previously saved inventory is loaded, if available.
3. Add food batches with their details.
4. The Min-Heap organises batches according to expiry dates.
5. View or access the earliest-expiring batch.
6. Dispatch the required quantity using FEFO priority.
7. Update expiry dates or remove expired batches when necessary.
8. View near-expiry alerts and warehouse reports.
9. Save inventory and exit the application.

## 📊 Algorithm

The project uses the following Min-Heap operations:

- **Insertion:** Add a new batch and restore the heap property using sift-up.
- **Peek:** Return the root element without removing it.
- **Extraction:** Remove the root element and restore the heap property using sift-down.
- **Update Expiry:** Change a batch's expiry date and adjust its heap position.
- **Deletion:** Identify and remove expired batches.
- **Sorting for Display:** Display batches in ascending expiry-date order.

For detailed pseudocode and explanations, see the [Algorithm Documentation](Documentation/Algorithm.md).

## 🖥️ Output Screenshots

The following screenshots demonstrate the application's console output.

| Output | Screenshot |
|---|---|
| Main Menu | [View](Output_Screenshots/Main_Menu.png) |
| FEFO Inventory | [View](Output_Screenshots/FEFO_Inventory.png) |
| Near Expiry Alert | [View](Output_Screenshots/Near_Expiry_Alert.png) |
| Dispatch Report | [View](Output_Screenshots/Dispatch_Report.png) |
| Warehouse Report | [View](Output_Screenshots/Warehouse_Report.png) |

## 🧪 Testing

The project documentation includes test scenarios for:

- Adding valid and duplicate batches.
- Handling invalid input.
- Viewing and extracting the earliest-expiry batch.
- Handling duplicate expiry dates.
- Updating expiry dates.
- Deleting expired batches.
- Handling an empty warehouse.
- Dispatching full and partial quantities.
- Generating near-expiry alerts.
- Saving and loading inventory.

Refer to [Test Cases](Documentation/Test_Cases.md) for the detailed test scenarios and space to record actual results.

## ⚖️ Performance Analysis

| Min-Heap Operation | Time Complexity |
|---|---:|
| Insertion | O(log n) |
| Peek | O(1) |
| Extraction | O(log n) |
| Update Expiry | O(log n) expected |
| Display in sorted order | O(n log n) |

A sorting-based approach requires sorting the inventory whenever ordered dispatch is needed. A Min-Heap provides efficient insertion and access to the earliest-expiring batch.

For detailed analysis, see [Complexity Analysis](Documentation/Complexity_Analysis.md).

## 📚 Documentation

Explore the complete project documentation:

- [Introduction](Documentation/Introduction.md)
- [Abstract](Documentation/Abstract.md)
- [Problem Statement](Documentation/Problem_Statement.md)
- [Objectives](Documentation/Objectives.md)
- [Algorithm](Documentation/Algorithm.md)
- [Methodology](Documentation/Methodology.md)
- [Complexity Analysis](Documentation/Complexity_Analysis.md)
- [Test Cases](Documentation/Test_Cases.md)
- [Conclusion](Documentation/Conclusion.md)
- [Future Scope](Documentation/Future_Scope.md)
- [References](Documentation/References.md)

## 🚀 Future Enhancements

- Develop a graphical user interface.
- Integrate a database for inventory storage.
- Introduce barcode scanning.
- Support multiple warehouse management.
- Generate advanced inventory and wastage reports.
- Add improved search and filtering options.

## 👨‍💻 Author
Suvarnam Pavan Dinesh


## 📜 License

This project is created for academic and educational purposes.
