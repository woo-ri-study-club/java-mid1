package section08;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static section08.TableState.AVAILABLE;
import static section08.TableState.RESERVED;

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

        @Override
        public String toString() {
            return "테이블 번호 : " + tableNumber +
                    ", 예약 현황 : " + ( tableState==RESERVED ? "예약완료" : "예약가능" ) +
                    (reserveNumber==null ? "" : ", 예약 번호: " + reserveNumber);
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
            parseDate(reserveDate);
            this.reserveName = reserveName;
            this.comment = comment;
        }

        private void parseDate(String reserveDate) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            try {
                this.reserveDate = LocalDate.parse(reserveDate, formatter);
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("날짜 형식이 올바르지 않습니다. yyyy/MM/dd 형식으로 입력해주세요.");
            }
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

        public String getReserveName() {
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

    public Optional<Table> getAvailableTable() {
        for (Table table : tables.values()) {
            if (table.isAvailable()) {
                return Optional.of(table);
            }
        }
        return Optional.empty();
    }

    void displayAvailableTableList() {
        String availableTables = tables.values().stream()
                .filter(Table::isAvailable)
                .map(table -> String.valueOf(table.getTableNumber()))
                .collect(Collectors.joining(", "));

        System.out.println(availableTables.isEmpty()
                ? "예약가능한 테이블이 없습니다."
                : "예약 가능한 테이블은 " + availableTables + " 번 입니다.");
    }

    void displayAllTableList() {
        tables.values().stream()
                .map(Table::toString)
                .forEach(System.out::println);
    }

    void displayReserveList() {
        reserveList.values().stream()
                .map(Reservation::toString)
                .forEach(System.out::println);
    }

    public Optional<Reservation> findReservation(int reservationNumber) {
        return Optional.ofNullable(reserveList.get(reservationNumber));
    }

    public List<Reservation> findReservationsByName(String name) {
        return reserveList.values().stream()
                .filter(reservation -> reservation.getReserveName().equals(name))
                .collect(Collectors.toList());
    }

    public List<Reservation> findReservationsByDate(LocalDate date) {
        return reserveList.values().stream()
                .filter(reservation -> reservation.getReserveDate().equals(date))
                .collect(Collectors.toList());
    }


    public void reserve(int reservationNumber, String reserveDate, String reserveName, String comment) {
        class TableStatusUpdater {
            void markAsReserved(Table table, int reservationNumber) {
                table.tableState = TableState.RESERVED;
                table.reserveNumber = reservationNumber;
            }
        }

        Table table = getAvailableTable()
                .orElseThrow(() -> new IllegalStateException("사용 가능한 테이블이 없습니다."));

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

        Reservation reservation = findReservation(reservationNumber)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 예약번호입니다."));

        Table table = tables.get(reservation.tableNumber);
        TableStatusUpdater statusUpdater = new TableStatusUpdater();
        statusUpdater.markAsAvailable(table);

        reserveList.remove(reservationNumber);
    }

    public static void showResultByCondition(Condition condition) {
        condition.check();
    }
}
