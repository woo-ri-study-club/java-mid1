package section8_9;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Restaurant {

    private Tables tables = new Tables();
    private Reservations reservations = new Reservations();

    public static class Table{
        private int num;
        private boolean booked;

        public Table(int num) {
            this.num = num;
            this.booked = false;
        }

        public void bookTable() {
            booked = true;
        }

        public void cancelBook() {
            booked = false;
        }

        public boolean isBooked() {
            return booked;
        }

        @Override
        public String toString() {
            return "Table{" +
                    "num=" + num +
                    ", booked=" + booked +
                    '}';
        }
    }

    public class Reservation {
        private String name;
        private Table table;

        public Reservation(String name, Table table) {
            this.name = name;
            this.table = table;
        }

        @Override
        public String toString() {
            return "Reservation{" +
                    "name='" + name + '\'' +
                    ", table=" + table +
                    '}';
        }
    }

    private class Tables {
        private final List<Table> tables = new ArrayList<>();
        private int tableCount = 0;

        private int generateTableNum() {
            return ++tableCount;
        }

        public void add() {
            Table newTable = new Table(generateTableNum());
            tables.add(newTable);
            System.out.println(newTable);
        }

        public Table findByNumber(int tableNum) {
            return tables.stream()
                    .filter(t -> t.num == tableNum)
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("없는 테이블 번호입니다."));
        }

        public void search(Consumer<List<Table>> searcher) {
            searcher.accept(tables);
        }
    }

    private class Reservations {
        private final List<Reservation> reservations = new ArrayList<>();

        public void add(Reservation reservation) {
            reservations.add(reservation);
        }

        public void remove(String name, int tableNum) {
            reservations.removeIf(reservation ->
                    reservation.name.equals(name) &&
                            reservation.table.num == tableNum
            );
        }

        public void search(Consumer<List<Reservation>> searcher) {
            searcher.accept(reservations);
        }

        public List<Reservation> getReservations() {
            return reservations;
        }
    }

    public void addReservation(String name, int tableNum) {

        class TableStatusUpdater {
            private final Table table;

            public TableStatusUpdater(Table table) {
                this.table = table;
            }

            void reserve() {
                table.bookTable();
                System.out.println(table.num + "번 테이블 예약 완료");
            }

        }

        Table findTable = tables.findByNumber(tableNum);
        if(findTable.isBooked()){
            throw new IllegalStateException("이미 예약된 테이블입니다.");
        }
        reservations.add(new Reservation(name, findTable));
        new TableStatusUpdater(findTable).reserve();
    }

    public void cancelReservation(String name, int tableNum) {

        class TableStatusUpdater {
            private final Table table;

            public TableStatusUpdater(Table table) {
                this.table = table;
            }

            void cancel() {
                table.cancelBook();
                System.out.println(table.num + "번 테이블 예약 취소");
            }
        }
        Table findTable = tables.findByNumber(tableNum);
        if(!findTable.isBooked()) {
            throw new IllegalStateException("예약되지 않은 테이블입니다.");
        }
        reservations.remove(name, tableNum);
        new TableStatusUpdater(findTable).cancel();
    }

    public void addTable() {
        tables.add();
    }

    public void searchReservation(Consumer<List<Reservation>> searcher) {
        reservations.search(searcher);
    }

    public void searchTable(Consumer<List<Table>> searcher) {
        tables.search(searcher);
    }

    public List<Reservation> getReservations() {
        return reservations.getReservations();
    }

}
