# Introduction

## Perishable Food Warehouse Dispatch System

The Perishable Food Warehouse Dispatch System is a Java-based application developed using the concept of a **Min-Heap**, a fundamental data structure in Advanced Data Structures.

The main purpose of this project is to manage perishable food products in a warehouse and dispatch them according to their expiry dates. It follows the **First Expiry, First Out (FEFO)** principle, in which food batches with the earliest expiry dates are processed first.

Each food batch contains a unique Batch ID, food name, quantity, and expiry date. The system uses a custom Min-Heap to efficiently organise and retrieve batches based on their expiry dates. An index map is also used to locate batches during expiry-date updates and deletion operations.

The application provides several operations, including adding food batches, viewing inventory, checking the earliest-expiring batch, dispatching required quantities, updating expiry dates, deleting expired batches, and generating warehouse reports.

It also supports inventory storage using a text file, allowing previously saved inventory records to be loaded when the application starts.

This project demonstrates the practical application of heap-based priority queue operations in warehouse inventory management. It also highlights how suitable data structures can help reduce food wastage and improve the efficiency of perishable food distribution.
