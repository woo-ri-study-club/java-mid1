package src.section9;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Restaurant {
    private String name;
    private List<Table> tables;
    private List<Reservation> reservations;

    public Restaurant(String name, int tableCount) {
        this.name = name;
        tables = new ArrayList<>();
        reservations = new ArrayList<>();
        for (int i = 0; i < tableCount; i++) {
            tables.add(new Table(i,4));
        }
    }


    public static class Table {
        private final int tableNumber;
        private final int capacity;
        private List<Reservation> tableReservations;

        public Table(int tableNumber, int capacity) {
            this.tableNumber = tableNumber;
            this.capacity = capacity;
            this.tableReservations = new ArrayList<>();
        }

        public boolean isAvailable(LocalDateTime startTime, LocalDateTime endTime) {
            if (tableReservations.isEmpty()) return true;

            return tableReservations.stream().noneMatch(reservation ->
                    (startTime.isBefore(reservation.end) &&
                            endTime.isAfter(reservation.start))
            );
        }

        public int getTableNumber() { return tableNumber; }
        public int getCapacity() { return capacity; }
        private List<Reservation> getTableReservations() { return tableReservations; }
    }

    private class Reservation {
        private final int id;
        private final String customerName;
        private LocalDateTime start;
        private LocalDateTime end;
        private int numberOfGuests;
        private Table table;

        public Reservation(String customerName, LocalDateTime start, LocalDateTime end, int numberOfGuests, Table table) {
            this.id = reservations.size()+1;
            this.customerName = customerName;
            this.start = start;
            this.end = end;
            this.numberOfGuests = numberOfGuests;
            this.table = table;
        }
    }

    public void tryReservation(String customerName, LocalDateTime startTime,
                                  LocalDateTime endTime, int numberOfGuests) {
        class TableStatusUpdater {
            void addReservationToTable(Table table, Reservation reservation) {
                table.getTableReservations().add(reservation);
                System.out.println(table.getTableNumber() + "번 테이블" +
                        " (수용 인원: " + table.getCapacity() + ") " +
                        numberOfGuests + " 명 예약. 시간: " +
                        startTime + " ~ " + endTime);
            }
        }

        List<Table> suitableTables = getSuitableTables(numberOfGuests);

        if (suitableTables.isEmpty()) {
            throw new IllegalArgumentException("해당 인원수를 수용 가능한 테이블이 없습니다.");
        }

        Optional<Table> availableTable = getAvailableTable(startTime, endTime, suitableTables);

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

    private static Optional<Table> getAvailableTable(LocalDateTime startTime, LocalDateTime endTime, List<Table> suitableTables) {
        return suitableTables.stream()
                .filter(table -> table.isAvailable(startTime, endTime))
                .findFirst();
    }

    private List<Table> getSuitableTables(int numberOfGuests) {
        return tables.stream()
                .filter(table -> table.getCapacity() >= numberOfGuests)
                .sorted(Comparator.comparing(Table::getCapacity))
                .toList();
    }
}
