package section8;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Restaurant {
    private final List<Table> tables;
    private final List<Reservation> reservations = new ArrayList<>();

    public Restaurant(List<Table> tables) {
        this.tables = tables;
    }

    public static class Table {
        private int tableNumber;
        private int capacity;
        private final Map<LocalDateTime, Boolean> reservationStatus = new HashMap<>();

        public Table(int tableNumber,int capacity) {
            this.tableNumber = tableNumber;
            this.capacity = capacity;
        }

        public boolean isAvailable(LocalDateTime time) {
            return !reservationStatus.getOrDefault(time, false);
        }

        public void reserve(LocalDateTime time) {
            reservationStatus.put(time, true);
        }

        public void cancelReservation(LocalDateTime time) {
            reservationStatus.remove(time);
        }

        public int getTableNumber() {
            return tableNumber;
        }

        public int getCapacity() {
            return capacity;
        }

        @Override
        public String toString() {
            return "Table{" +
                    "tableNumber: " + tableNumber +
                    ", capacity: " + capacity +
                    '}';
        }
    }

    public class Reservation {
        private static final AtomicInteger idCounter = new AtomicInteger(1);
        private int id;
        private User user;
        private Table table;
        private LocalDateTime reservationTime;
        private int amount;

        public Reservation(User user, Table table, LocalDateTime reservationTime, int amount) {
            this.id = idCounter.getAndIncrement();
            this.user = user;
            this.table = table;
            this.reservationTime = reservationTime;
            this.amount = amount;
        }

        @Override
        public String toString() {
            return "Reservation{" +
                    ", name=" + user.getName() +
                    ", email=" + user.getEmail() +
                    ", phoneNumber=" + user.getPhoneNumber() +
                    ", table=" + table +
                    ", reservationTime=" + reservationTime +
                    ", amount=" + amount +
                    '}';
        }
    }

    public void addReservation(User user, Table table, LocalDate date, LocalTime time, int amount) {
        LocalDateTime reservationTime = LocalDateTime.of(date, time);

        isAlreadyReserve(user, date);

        isAmountAvailable(amount, table.capacity);

        isAmountMoreThanZero(amount);

        isAlreadyReservedTable(table, reservationTime);

        reservations.add(new Reservation(user, table, reservationTime, amount));
        table.reserve(reservationTime);
        System.out.println("예약이 완료되었습니다.");
    }

    public void updateReservation(User user, LocalDate oldDate, LocalTime oldTime, LocalDate newDate, LocalTime newTime, int amount) {
        LocalDateTime oldReservationTime = LocalDateTime.of(oldDate, oldTime);
        LocalDateTime newReservationTime = LocalDateTime.of(newDate, newTime);

        Reservation reservation = reservations.stream()
                .filter(r -> r.user.equals(user) && r.reservationTime.equals(oldReservationTime))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("수정할 예약이 존재하지 않습니다."));

        isAmountAvailable(amount, reservation.table.capacity);

        isAlreadyReservedTable(reservation.table, newReservationTime);

        reservation.table.cancelReservation(oldReservationTime);

        reservations.remove(reservation);
        reservations.add(new Reservation(user, reservation.table, newReservationTime, amount));
        reservation.table.reserve(newReservationTime);
        System.out.println("예약이 수정되었습니다.");
    }

    public void deleteReservation(User user, LocalDate date) {
        Reservation reservation = reservations.stream()
                .filter(r -> r.user.equals(user) && r.reservationTime.toLocalDate().equals(date))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("삭제할 예약이 존재하지 않습니다."));

        reservations.remove(reservation);
        reservation.table.cancelReservation(reservation.reservationTime);
        System.out.println("예약이 삭제되었습니다.");
    }

    public List<Table> findAvailableTables(LocalDate date, LocalTime time, SearchCondition condition) {
        LocalDateTime reservationTime = LocalDateTime.of(date, time);

        List<Table> availableTables = tables.stream()
                .filter(table -> table.isAvailable(reservationTime))
                .filter(condition::execute)
                .toList();

        if(availableTables.isEmpty()){
            throw new IllegalArgumentException("조건에 맞는 예약 가능한 테이블이 없습니다.");
        }

        return availableTables;
    }

    private static void isAmountAvailable(int amount, int capacity) {
        if (capacity < amount) {
            throw new IllegalArgumentException("테이블 수용 가능 인원 수보다 많게 예약 할 수 없습니다. (최대 가능 인원: " + capacity + "명)");
        }
    }

    private void isAlreadyReserve(User user, LocalDate date) {
        if (reservations.stream()
                .anyMatch(r ->
                        r.user.equals(user) && r.reservationTime.toLocalDate().equals(date)
                )) {
            throw new IllegalArgumentException("해당 일에 이미 예약한 이력이 있습니다.");
        }
    }

    private static void isAmountMoreThanZero(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("1명 이상 예약해야 합니다.");
        }
    }

    private static void isAlreadyReservedTable(Table table, LocalDateTime reservationTime) {
        if (!table.isAvailable(reservationTime)) {
            throw new IllegalArgumentException("이미 해당 시간에 예약된 테이블입니다.");
        }
    }
}
