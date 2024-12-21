package src.section9;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class Restaurant {
    private static final AtomicInteger idCounter = new AtomicInteger();
    private String name;
    private Tables tables;
    private Reservations reservations;

    public Restaurant(String name, int tableCount) {
        this.name = name;
        tables = new Tables(tableCount);
        reservations = new Reservations();
    }


    public static class Table {
        private final int tableNumber;
        private final int capacity;
        private TableReservations tableReservations;

        public Table(int tableNumber, int capacity) {
            this.tableNumber = tableNumber;
            this.capacity = capacity;
            this.tableReservations = new TableReservations();
        }

        public boolean isAvailable(LocalDateTime startTime, LocalDateTime endTime) {
            return tableReservations.isAvailable(startTime, endTime);
        }

        public int getTableNumber() { return tableNumber; }
        public int getCapacity() { return capacity; }
        private TableReservations getTableReservations() { return tableReservations; }
    }

    private static class Tables {
        private final List<Table> tables;

        public Tables(int tableCount) {
            this.tables = new ArrayList<>();
            for (int i = 0; i < tableCount; i++) {
                tables.add(new Table(i, 4));
            }
        }

        public List<Table> getSuitableTables(int numberOfGuests) {
            return tables.stream()
                    .filter(table -> table.getCapacity() >= numberOfGuests)
                    .sorted(Comparator.comparing(Table::getCapacity))
                    .toList();
        }

        public Optional<Table> findAvailableTable(LocalDateTime startTime,
                                                  LocalDateTime endTime,
                                                  List<Table> suitableTables) {
            return suitableTables.stream()
                    .filter(table -> table.isAvailable(startTime, endTime))
                    .findFirst();
        }
    }

    private static class TableReservations {
        private final List<Reservation> reservations = new ArrayList<>();

        public void add(Reservation reservation) {
            reservations.add(reservation);
        }

        public boolean isAvailable(LocalDateTime startTime, LocalDateTime endTime) {
            if (reservations.isEmpty()) return true;

            return reservations.stream().noneMatch(reservation ->
                    (startTime.isBefore(reservation.getEnd()) &&
                            endTime.isAfter(reservation.getStart())));
        }
    }

    private static class Reservation {
        private final int id;
        private final String customerName;
        private LocalDateTime start;
        private LocalDateTime end;
        private int numberOfGuests;
        private Table table;

        public Reservation(String customerName, LocalDateTime start, LocalDateTime end, int numberOfGuests, Table table) {
            //this.id = reservations.size()+1; // 멀티스레드에서 동기화 문제 발생.
            this.id = idCounter.incrementAndGet();
            this.customerName = customerName;
            this.start = start;
            this.end = end;
            this.numberOfGuests = numberOfGuests;
            this.table = table;
        }

        public LocalDateTime getStart() { return start; }
        public LocalDateTime getEnd() { return end; }
        public String getCustomerName() { return customerName; }

        @Override
        public String toString() {
            return "예약 번호: " + id +
                    ", 예약자: " + customerName + '\'' +
                    ", 예약 시간: " + start +
                    " ~ " + end +
                    ", 인원: " + numberOfGuests +
                    ", 테이블: " + table;
        }
    }

    private static class Reservations {
        private static final AtomicInteger idCounter = new AtomicInteger();
        private final List<Reservation> reservations;

        public Reservations() {
            this.reservations = new ArrayList<>();
        }

        public void add(Reservation reservation) {
            reservations.add(reservation);
        }

        public int generateNextId() {
            return idCounter.incrementAndGet();
        }

        public List<Reservation> findByCustomerName(String customerName) {
            return reservations.stream()
                    .filter(r -> r.getCustomerName().equals(customerName))
                    .toList();
        }
    }

    public void tryReservation(String customerName, LocalDateTime startTime,
                                  LocalDateTime endTime, int numberOfGuests) {
        class TableStatusUpdater {
            void addReservationToTable(Table table, Reservation reservation) {
                table.getTableReservations().add(reservation);
                System.out.println(reservation);
            }
        }

        List<Table> suitableTables = tables.getSuitableTables(numberOfGuests);

        if (suitableTables.isEmpty()) {
            throw new IllegalArgumentException("해당 인원수를 수용 가능한 테이블이 없습니다.");
        }

        Optional<Table> availableTable = tables.findAvailableTable(startTime, endTime, suitableTables);
        if (availableTable.isEmpty()) {
            throw new IllegalArgumentException("예약 가능한 테이블이 없습니다.");
        }

        Table table = availableTable.get();
        Reservation newReservation = new Reservation(customerName,
                startTime, endTime,
                numberOfGuests, table);

        TableStatusUpdater updater = new TableStatusUpdater();
        updater.addReservationToTable(table, newReservation);
        reservations.add(newReservation);
    }
}
