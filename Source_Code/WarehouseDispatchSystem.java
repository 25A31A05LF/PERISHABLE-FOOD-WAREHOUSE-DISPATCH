import java.util.*;
import java.time.LocalDate;
import java.io.*;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;

class Batch {
    String batchId;
    String foodName;
    int quantity;
    LocalDate expiryDate;

    Batch(String batchId, String foodName, int quantity, LocalDate expiryDate) {
        this.batchId = batchId;
        this.foodName = foodName;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
    }

    @Override
    public String toString() {
        return "ID: " + batchId +
               " | Food: " + foodName +
               " | Quantity: " + quantity +
               " | Expiry: " + expiryDate;
    }
}

class MinHeap {
    private final ArrayList<Batch> heap = new ArrayList<>();
    private final HashMap<String, Integer> indexMap = new HashMap<>();

    private int compare(Batch a, Batch b) {
        int result = a.expiryDate.compareTo(b.expiryDate);
        if (result == 0) {
            result = a.batchId.compareTo(b.batchId);
        }
        return result;
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public int size() {
        return heap.size();
    }

    public int totalQuantity() {
        int total = 0;
        for (Batch batch : heap) {
            total += batch.quantity;
        }
        return total;
    }

    private void swap(int i, int j) {
        Batch temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);

        indexMap.put(heap.get(i).batchId, i);
        indexMap.put(heap.get(j).batchId, j);
    }

    private void siftUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (compare(heap.get(index), heap.get(parent)) < 0) {
                swap(index, parent);
                index = parent;
            } else {
                break;
            }
        }
    }

    private void siftDown(int index) {
        int size = heap.size();

        while (true) {
            int smallest = index;
            int left = 2 * index + 1;
            int right = 2 * index + 2;

            if (left < size &&
                compare(heap.get(left), heap.get(smallest)) < 0) {
                smallest = left;
            }

            if (right < size &&
                compare(heap.get(right), heap.get(smallest)) < 0) {
                smallest = right;
            }

            if (smallest != index) {
                swap(index, smallest);
                index = smallest;
            } else {
                break;
            }
        }
    }

    public boolean insert(Batch batch) {
        if (batch == null || batch.batchId == null ||
            batch.batchId.trim().isEmpty() || batch.foodName == null ||
            batch.foodName.trim().isEmpty() || batch.quantity <= 0 ||
            batch.expiryDate == null) {
            return false;
        }

        if (indexMap.containsKey(batch.batchId)) {
            System.out.println("Batch ID already exists!");
            return false;
        }

        heap.add(batch);
        int index = heap.size() - 1;
        indexMap.put(batch.batchId, index);
        siftUp(index);
        return true;
    }

    public Batch peek() {
        return isEmpty() ? null : heap.get(0);
    }

    public Batch extractMin() {
        return isEmpty() ? null : removeAt(0);
    }

    private Batch removeAt(int index) {
        Batch removed = heap.get(index);
        int lastIndex = heap.size() - 1;
        Batch last = heap.remove(lastIndex);
        indexMap.remove(removed.batchId);

        if (index < heap.size()) {
            heap.set(index, last);
            indexMap.put(last.batchId, index);

            if (index > 0 &&
                compare(heap.get(index), heap.get((index - 1) / 2)) < 0) {
                siftUp(index);
            } else {
                siftDown(index);
            }
        }
        return removed;
    }

    public boolean updateExpiry(String id, LocalDate newDate) {
        Integer index = indexMap.get(id);
        if (index == null || newDate == null) {
            return false;
        }

        heap.get(index).expiryDate = newDate;

        if (index > 0 &&
            compare(heap.get(index), heap.get((index - 1) / 2)) < 0) {
            siftUp(index);
        } else {
            siftDown(index);
        }
        return true;
    }

    public ArrayList<Batch> deleteExpired(LocalDate today) {
        ArrayList<Batch> removed = new ArrayList<>();
        int i = 0;

        while (i < heap.size()) {
            if (heap.get(i).expiryDate.isBefore(today)) {
                removed.add(removeAt(i));
            } else {
                i++;
            }
        }
        return removed;
    }

    public ArrayList<Batch> getSortedBatches() {
        ArrayList<Batch> copy = new ArrayList<>(heap);
        copy.sort(this::compare);
        return copy;
    }

    public void displayAll() {
        if (isEmpty()) {
            System.out.println("Warehouse is empty.");
            return;
        }

        System.out.println("\n--- FOOD BATCHES (FEFO ORDER) ---");
        for (Batch batch : getSortedBatches()) {
            System.out.println(batch);
        }
        System.out.println("Total Food Units: " + totalQuantity());
    }
}

public class WarehouseDispatchSystem {
    private static final Scanner sc = new Scanner(System.in);
    private static final MinHeap warehouse = new MinHeap();
    private static final String FILE_NAME = "warehouse_inventory.txt";

    private static long totalDispatchedUnits = 0;
    private static long totalWastedUnits = 0;
    private static long dispatchedBatchEntries = 0;
    private static long wastedBatches = 0;

    static int readInt(String message) {
        while (true) {
            System.out.print(message);
            try {
                int value = Integer.parseInt(sc.nextLine().trim());
                if (value < 0) {
                    System.out.println("Enter a non-negative number.");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }

    static LocalDate readDate(String message) {
        while (true) {
            System.out.print(message);
            try {
                return LocalDate.parse(sc.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Invalid date. Use YYYY-MM-DD.");
            }
        }
    }

    static void saveInventory() {
        try (BufferedWriter writer = Files.newBufferedWriter(
                Paths.get(FILE_NAME), StandardCharsets.UTF_8)) {
            for (Batch batch : warehouse.getSortedBatches()) {
                writer.write(batch.batchId + "\t" + batch.foodName + "\t" +
                             batch.quantity + "\t" + batch.expiryDate);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving inventory: " + e.getMessage());
        }
    }

    static void loadInventory() {
        Path path = Paths.get(FILE_NAME);
        if (!Files.exists(path)) {
            return;
        }

        try (BufferedReader reader = Files.newBufferedReader(
                path, StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    String[] data = line.split("\t", -1);
                    if (data.length != 4) {
                        continue;
                    }

                    Batch batch = new Batch(
                        data[0],
                        data[1],
                        Integer.parseInt(data[2]),
                        LocalDate.parse(data[3])
                    );

                    if (!warehouse.insert(batch)) {
                        System.out.println("Skipped invalid/duplicate record: " + data[0]);
                    }
                } catch (Exception e) {
                    System.out.println("Skipped invalid inventory record.");
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading inventory: " + e.getMessage());
        }
    }

    static void addBatch() {
        System.out.print("Enter Batch ID: ");
        String id = sc.nextLine().trim();

        System.out.print("Enter Food Name: ");
        String name = sc.nextLine().trim();

        int quantity = readInt("Enter Quantity: ");

        if (id.isEmpty() || name.isEmpty() || quantity <= 0 ||
            id.contains("\t") || name.contains("\t") ||
            id.contains("\n") || name.contains("\n") ||
            id.contains("\r") || name.contains("\r")) {
            System.out.println("Invalid batch details.");
            return;
        }

        LocalDate expiry = readDate("Enter Expiry Date (YYYY-MM-DD): ");
        if (expiry.isBefore(LocalDate.now())) {
            System.out.println("Warning: This batch is already expired.");
        }

        if (warehouse.insert(new Batch(id, name, quantity, expiry))) {
            System.out.println("Batch added successfully!");
            saveInventory();
        } else {
            System.out.println("Unable to add batch. Check the details or duplicate ID.");
        }
    }

    static void peekBatch() {
        Batch batch = warehouse.peek();
        if (batch == null) {
            System.out.println("Warehouse is empty!");
        } else {
            System.out.println("Earliest Expiry Batch:");
            System.out.println(batch);
        }
    }

    static void dispatchEarliest() {
        if (warehouse.isEmpty()) {
            System.out.println("No batches available!");
            return;
        }

        int requested = readInt("Enter Required Dispatch Quantity: ");
        if (requested == 0) {
            System.out.println("Dispatch quantity must be greater than zero.");
            return;
        }

        int remaining = requested;
        long dispatched = 0;
        long wasted = 0;
        LocalDate today = LocalDate.now();

        System.out.println("\n--- DISPATCH REPORT ---");

        while (remaining > 0 && !warehouse.isEmpty()) {
            Batch batch = warehouse.peek();

            if (batch.expiryDate.isBefore(today)) {
                Batch expired = warehouse.extractMin();
                wasted += expired.quantity;
                totalWastedUnits += expired.quantity;
                wastedBatches++;
                System.out.println("WASTE: " + expired);
                continue;
            }

            int amount = Math.min(remaining, batch.quantity);
            batch.quantity -= amount;
            remaining -= amount;
            dispatched += amount;

            totalDispatchedUnits += amount;
            dispatchedBatchEntries++;

            System.out.println("DISPATCHED: " + batch.batchId +
                               " | Food: " + batch.foodName +
                               " | Quantity: " + amount);

            if (batch.quantity == 0) {
                warehouse.extractMin();
            }
        }

        System.out.println("\nRequested Quantity: " + requested);
        System.out.println("Successfully Dispatched: " + dispatched);
        System.out.println("Expired Units Removed: " + wasted);
        System.out.println("Unfulfilled Quantity: " + remaining);
        saveInventory();
    }

    static void updateBatch() {
        System.out.print("Enter Batch ID: ");
        String id = sc.nextLine().trim();
        LocalDate newDate = readDate("Enter New Expiry Date (YYYY-MM-DD): ");

        if (warehouse.updateExpiry(id, newDate)) {
            System.out.println("Expiry date updated successfully!");
            if (newDate.isBefore(LocalDate.now())) {
                System.out.println("Warning: Batch is now expired.");
            }
            saveInventory();
        } else {
            System.out.println("Batch ID not found!");
        }
    }

    static void deleteExpired() {
        ArrayList<Batch> removed = warehouse.deleteExpired(LocalDate.now());
        long units = 0;

        for (Batch batch : removed) {
            units += batch.quantity;
            System.out.println("Removed: " + batch);
        }

        totalWastedUnits += units;
        wastedBatches += removed.size();

        System.out.println("Expired Batches Deleted: " + removed.size());
        System.out.println("Expired Food Units: " + units);
        saveInventory();
    }

    static void nearExpiryAlert() {
        LocalDate today = LocalDate.now();
        LocalDate limit = today.plusDays(3);
        boolean found = false;

        System.out.println("\n--- NEAR EXPIRY ALERT ---");
        System.out.println("Products expiring within 3 days:");

        for (Batch batch : warehouse.getSortedBatches()) {
            if (!batch.expiryDate.isBefore(today) &&
                !batch.expiryDate.isAfter(limit)) {
                System.out.println(batch);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No near-expiry batches found.");
        }
    }

    static void simulateDispatch() {
        if (warehouse.isEmpty()) {
            System.out.println("No batches to process!");
            return;
        }

        LocalDate dispatchDate = readDate("Enter Dispatch Date (YYYY-MM-DD): ");

        long dispatchedUnits = 0;
        long wastedUnits = 0;
        int dispatchedBatches = 0;
        int wastedBatchCount = 0;

        System.out.println("\n--- DISPATCH REPORT ---");

        while (!warehouse.isEmpty()) {
            Batch batch = warehouse.extractMin();

            if (batch.expiryDate.isBefore(dispatchDate)) {
                wastedUnits += batch.quantity;
                wastedBatchCount++;
                totalWastedUnits += batch.quantity;
                wastedBatches++;
                System.out.println("WASTE: " + batch);
            } else {
                dispatchedUnits += batch.quantity;
                dispatchedBatches++;
                totalDispatchedUnits += batch.quantity;
                dispatchedBatchEntries++;
                System.out.println("DISPATCHED: " + batch);
            }
        }

        System.out.println("\nBatches Dispatched: " + dispatchedBatches);
        System.out.println("Food Units Dispatched: " + dispatchedUnits);
        System.out.println("Batches Wasted: " + wastedBatchCount);
        System.out.println("Food Units Wasted: " + wastedUnits);
        saveInventory();
    }

    static void wasteReport() {
        System.out.println("\n--- WAREHOUSE REPORT ---");
        System.out.println("Dispatch Batch Entries: " + dispatchedBatchEntries);
        System.out.println("Total Food Units Dispatched: " + totalDispatchedUnits);
        System.out.println("Wasted Batches: " + wastedBatches);
        System.out.println("Total Food Units Wasted: " + totalWastedUnits);
        System.out.println("Remaining Batches: " + warehouse.size());
        System.out.println("Remaining Food Units: " + warehouse.totalQuantity());
    }

    public static void main(String[] args) {
        loadInventory();

        System.out.println("Welcome to Perishable Food Warehouse!");
        System.out.println("Previously saved inventory loaded.");

        int choice;
        do {
            System.out.println("\n==================================");
            System.out.println(" PERISHABLE FOOD WAREHOUSE SYSTEM");
            System.out.println("==================================");
            System.out.println("1. Add Food Batch");
            System.out.println("2. View All Batches");
            System.out.println("3. Peek Earliest Expiry Batch");
            System.out.println("4. Dispatch Required Quantity");
            System.out.println("5. Update Batch Shelf Life");
            System.out.println("6. Delete Expired Batches");
            System.out.println("7. Simulate Dispatch Day");
            System.out.println("8. Near Expiry Alert");
            System.out.println("9. View Warehouse Report");
            System.out.println("10. Exit");

            choice = readInt("Enter Your Choice: ");

            switch (choice) {
                case 1:
                    addBatch();
                    break;
                case 2:
                    warehouse.displayAll();
                    break;
                case 3:
                    peekBatch();
                    break;
                case 4:
                    dispatchEarliest();
                    break;
                case 5:
                    updateBatch();
                    break;
                case 6:
                    deleteExpired();
                    break;
                case 7:
                    simulateDispatch();
                    break;
                case 8:
                    nearExpiryAlert();
                    break;
                case 9:
                    wasteReport();
                    break;
                case 10:
                    saveInventory();
                    System.out.println("Inventory saved successfully.");
                    System.out.println("Thank you for using the system!");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 10);

        sc.close();
    }
}
