package section9;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Restaurant {

    private List<Table> tables = new ArrayList<>();

    public static class Table {

        private int tableNumber;

        private int capacity;

        private boolean isAvailable;

        private List<Reservation> reservations = new ArrayList<>();

        public Table(int tableNumber, int capacity) {
            this.tableNumber = tableNumber;
            this.capacity = capacity;
            this.isAvailable = true;
        }

        public class Reservation {

            private String customerName;

            private LocalDateTime startTime;

            private LocalDateTime endTime;

            public Reservation(String customerName, LocalDateTime startTime, LocalDateTime endTime) {
                this.customerName = customerName;
                this.startTime = startTime;
                this.endTime = endTime;
            }

            public String getCustomerName() {
                return customerName;
            }

            public boolean overlaps(LocalDateTime startTime, LocalDateTime endTime) {
                return (startTime.isBefore(this.startTime) && endTime.isAfter(this.endTime));
            }

            @Override
            public String toString() {
                return "Reservation{" +
                        "customerName='" + customerName + '\'' +
                        ", startTime=" + startTime +
                        ", endTime=" + endTime +
                        '}';
            }
        }

        public boolean addReservation(String customerName, LocalDateTime startTime, LocalDateTime endTime) {
            for (Reservation reservation : reservations) {
                if (reservation.overlaps(startTime, endTime)) {
                    return false;
                }
            }

            reservations.add(new Reservation(customerName, startTime, endTime));
            this.isAvailable = false;

            return true;
        }

        public boolean isAvailable() {
            return isAvailable;
        }

        public int getCapacity() {
            return capacity;
        }

        public int getTableNumber() {
            return tableNumber;
        }

        public List<Reservation> getReservations() {
            return reservations;
        }

        @Override
        public String toString() {
            return "Table{" +
                    "tableNumber=" + tableNumber +
                    ", capacity=" + capacity +
                    ", isAvailable=" + isAvailable +
                    '}';
        }
    }

    public List<Table> getTables() {
        return tables;
    }

    public void addTable(int tableNumber, int capacity) {
        tables.add(new Table(tableNumber, capacity));
    }

    public Table getTableBy(int tableNumber) {
        return tables.stream()
                .filter(table -> table.getTableNumber() == tableNumber)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("테이블을 찾을 수 없습니다. "));
    }

    public void updateTableStatus(int tableNumber, boolean isAvailable) {
        Table table = getTableBy(tableNumber);

        class TableStatusUpdater {
            void update() {
                table.isAvailable = isAvailable;
            }
        }

        new TableStatusUpdater().update();
    }

    public void findTables(Predicate<Table> condition) {
        tables.stream().filter(condition).forEach(System.out::println);
    }
}
