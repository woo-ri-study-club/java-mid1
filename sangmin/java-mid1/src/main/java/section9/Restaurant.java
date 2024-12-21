package section9;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class Restaurant {
    Tables tables;
    List<Reservation> reservations = new ArrayList<>();

    public Restaurant(int tableCount) {
        tables = new Tables(tableCount);
    }

    public static class Table {
        private int tableNumber;
        boolean isReserved;

        public Table(int tableNumber) {
            this.tableNumber = tableNumber;
            this.isReserved = false;
        }

        public void reserve() {
            isReserved = true;
        }

        public void cancel() {
            isReserved = false;
        }

        public boolean equalsTableNumber(int tableNumber) {
            return this.tableNumber == tableNumber;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            final Table table = (Table) o;
            return tableNumber == table.tableNumber && isReserved == table.isReserved;
        }

        @Override
        public int hashCode() {
            return Objects.hash(tableNumber, isReserved);
        }
    }

    public static class Tables {
        List<Table> tables;

        public Tables(int tableCount) {
            tables = Stream.iterate(1, i -> i + 1)
                    .limit(tableCount)
                    .map(Table::new)
                    .toList();
        }

        public Table findTable(int tableNumber) {
            return tables.stream()
                    .filter(table -> table.equalsTableNumber(tableNumber))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Table not found"));
        }
    }

    public class Reservation {
        private String name;
        private String phoneNumber;
        private String date;
        private Table table;

        public Reservation(String name, String phoneNumber, String date, Table table) {
            this.name = name;
            this.phoneNumber = phoneNumber;
            this.date = date;
            this.table = table;
        }

        public int getTableNumber() {
            return table.tableNumber;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            final Reservation that = (Reservation) o;
            return Objects.equals(name, that.name) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(date, that.date) && Objects.equals(table, that.table);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, phoneNumber, date, table);
        }
    }

    public void reserve(int tableNumber, String name, String phoneNumber, String date) {
        class Reserve implements TableStatusUpdater {
            public void updateTableStatus() {
                Table table = tables.findTable(tableNumber);
                table.reserve();
                reservations.add(new Reservation(name, phoneNumber, date, table));
            }
        }
        TableStatusUpdater updater = new Reserve();
        updater.updateTableStatus();
    }

    public void cancel(int tableNumber) {
        class Cancel implements TableStatusUpdater {
            public void updateTableStatus() {
                Table table = tables.findTable(tableNumber);
                table.cancel();
                reservations.removeIf(reservation -> reservation.table == table);
            }
        }
        TableStatusUpdater updater = new Cancel();
        updater.updateTableStatus();
    }

    public void updateTableStatus(TableStatusUpdater updater) {
        updater.updateTableStatus();
    }

    public List<Reservation> getReservations() {
        return reservations;
    }
}
