package section8_9;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Restaurant {

    private static int tableCount = 0;

    private static class Table{
        private int num;
        private boolean booked;

        public Table() {
            this.num = ++tableCount;
            this.booked = false;
        }

        public void bookTable() {
            booked = true;
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

    private List<Table> tables = new ArrayList<>();
    private List<Reservation> reservations = new ArrayList<>();

    public void addTable(){
        Table newTable = new Table();
        tables.add(newTable);
        System.out.println(newTable);
    }

    public void addReservation(String name, int tableNum) {

        class TableStatusUpdater {
            private Table table;

            public TableStatusUpdater(Table table) {
                this.table = table;
            }

            void updateStatus() {
                table.bookTable();
                System.out.println(table.num + "번 테이블 예약 완료");
            }
        }

        Table findTable = findTableBy(tableNum);
        if(findTable.isBooked()){
            throw new IllegalStateException("이미 예약된 테이블입니다.");
        }
        reservations.add(new Reservation(name, findTable));
        new TableStatusUpdater(findTable).updateStatus();
    }

    private Table findTableBy(int tableNum) {
        return tables.stream()
                .filter(t -> t.num == tableNum)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("없는 테이블 번호입니다."));
    }

    public void search(Consumer<List<Reservation>> searcher) {
        searcher.accept(this.reservations);
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

}
