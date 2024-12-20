package section08;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

import static section08.TableState.AVAILABLE;

public class Restaurant {

    private Map<Integer, Table> tables = new HashMap<>();
    private Map<Integer, Reservation> reserveList = new HashMap<>();

    static class Table {
        private int tableNumber;
        private TableState tableState;
        private Integer reserveNumber;

        public Table(int tableNumber) {
            this.tableNumber = tableNumber;
            this.reserveNumber = null;
            this.tableState = AVAILABLE;
        }

        public boolean isAvailable() {
            return AVAILABLE.equals(tableState);
        }

        public int getTableNumber() {
            return tableNumber;
        }
    }

    class Reservation {
        private int reservationNumber;
        private int tableNumber;
        private LocalDate reserveDate;
        private String reserveName;
        private String comment;

        public Reservation(int reservationNumber, String reserveDate, String reserveName, String comment) {
            this.reservationNumber = reservationNumber;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            try {
                this.reserveDate = LocalDate.parse(reserveDate, formatter);
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("날짜 형식이 올바르지 않습니다. yyyy/MM/dd 형식으로 입력해주세요.");
            }
            this.reserveName = reserveName;
            this.comment = comment;
        }

        public LocalDate getReserveDate() {
            return reserveDate;
        }

        @Override
        public String toString() {
            return
                    "예약번호: " + reservationNumber +
                            ", 예약테이블: " + tableNumber +
                            ", 예약 날짜: " + reserveDate +
                            ", 예약하신 분: " + reserveName +
                            ", 참고사항: " + comment;
        }

        public Object getReserveName() {
            return reserveName;
        }
    }

    public void createTable(int tableNumber) {
        if (tables.containsKey(tableNumber)) {
            throw new IllegalArgumentException("이미 존재하는 테이블 번호입니다.");
        }
        Table table = new Table(tableNumber);
        tables.put(table.tableNumber, table);
    }

    public Table getAvailableTable() {
        for (Table table : tables.values()) {
            if (table.isAvailable()) {
                return table;
            }
        }
        return null;
    }

    void displayAvailableTableList() {
        if (getAvailableTable() == null) {
            System.out.println("예약가능한 테이블이 없습니다.");
        }
        System.out.print("예약 가능한 테이블은 ");
        for (Table table : tables.values()) {
            if (table.isAvailable()) {
                System.out.print(table.getTableNumber() + " ");
            }
        }
        System.out.println("입니다.");
    }

    void displayReserveList() {
        for (Reservation reservation : reserveList.values()) {
            System.out.println(reservation.toString());
        }
    }


    public Map<Integer, Reservation> getReserveList() {
        return reserveList;
    }

    public void reserve(int reservationNumber, String reserveDate, String reserveName, String comment) {
        class TableStatusUpdater {
            void markAsReserved(Table table, int reservationNumber) {
                table.tableState = TableState.RESERVED;
                table.reserveNumber = reservationNumber;
            }
        }

        Table table = getAvailableTable();
        if (table == null) {
            throw new IllegalStateException("사용 가능한 테이블이 없습니다.");
        }

        if (reserveList.containsKey(reservationNumber)) {
            throw new IllegalArgumentException("이미 존재하는 예약번호입니다.");
        }

        TableStatusUpdater statusUpdater = new TableStatusUpdater();
        Reservation reservation = new Reservation(reservationNumber, reserveDate, reserveName, comment);
        reservation.tableNumber = table.getTableNumber();
        table.reserveNumber = reservationNumber;
        statusUpdater.markAsReserved(table, reservationNumber);

        reserveList.put(reservation.reservationNumber, reservation);
    }

    public void cancel(int reservationNumber) {
        class TableStatusUpdater {
            void markAsAvailable(Table table) {
                table.tableState = TableState.AVAILABLE;
                table.reserveNumber = null;
            }
        }

        Reservation reservation = reserveList.get(reservationNumber);
        if (reservation == null) {
            throw new IllegalArgumentException("존재하지 않는 예약번호입니다.");
        }

        Table table = tables.get(reservation.tableNumber);
        TableStatusUpdater statusUpdater = new TableStatusUpdater();
        statusUpdater.markAsAvailable(table);

        reserveList.remove(reservationNumber);
    }

    public static void showResultByCondition(Condition condition) {
        condition.check();
    }
}
