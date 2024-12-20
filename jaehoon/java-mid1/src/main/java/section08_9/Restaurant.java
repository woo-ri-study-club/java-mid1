package section08_9;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.util.Objects.isNull;

public class Restaurant {

  private static final LocalDateTime OPEN_TIME = LocalDateTime.of(2024, 12, 9, 10, 0);

  private static final LocalDateTime CLOSE_TIME = LocalDateTime.of(2024, 12, 9, 22, 0);

  private static final int RESERVATION_DURATION_HOURS = 3;

  private Table[] tables;

  public Restaurant(int tableCount) {
    this.tables = new Table[tableCount];
    for (int i = 0; i < tableCount; i++) {
      this.tables[i] = new Table(i + 1);
    }
  }

  public Table[] getTables() {
    return tables;
  }

  public Optional<String> addReservation(String phoneNumber, LocalDateTime reservationAt) {
    if (reservationAt.isBefore(OPEN_TIME) || reservationAt.plusHours(RESERVATION_DURATION_HOURS).isAfter(CLOSE_TIME)) {
      System.out.println("예약 시간은 레스토랑 운영 시간 내로 설정해야 합니다.");
      return Optional.empty();
    }

    Table availableTable = getNotReservationTable(reservationAt);
    if (isNull(availableTable)) {
      System.out.println("예약 가능한 테이블이 없습니다.");
      return Optional.empty();
    }

    Reservation reservation = new Reservation(phoneNumber, availableTable.id, reservationAt);
    availableTable.reservations.add(reservation);
    System.out.println("예약이 성공적으로 추가되었습니다: " + reservation);
    return Optional.of(reservation.id);
  }

  public void cancelReservation(String reservationId) {
    for (Table table : tables) {
      Iterator<Reservation> iterator = table.reservations.iterator();
      while (iterator.hasNext()) {
        Reservation reservation = iterator.next();
        if (reservation.id.equals(reservationId)) {
          iterator.remove();
          System.out.println("예약이 성공적으로 취소되었습니다: " + reservation);
          return;
        }
      }
    }
    System.out.println("해당 ID의 예약을 찾을 수 없습니다: " + reservationId);
  }

  public void showReservationByPhoneNumber(String phoneNumber) {
    AtomicBoolean showed = new AtomicBoolean(false);
    Optional<Reservation> reservation = Arrays.stream(tables)
                                              .flatMap(table -> table.reservations.stream())
                                              .filter(r -> r.phoneNumber.equals(phoneNumber))
                                              .findFirst();
    reservation.ifPresentOrElse(reserve -> {
                                  System.out.println(reserve);
                                  showed.set(true);
                                },
                                () -> {
                                });

    if (!showed.get()) {
      System.out.println("`" + phoneNumber + "` 번호로는 예약기록이 없습니다.");
    }
  }

  private Table getNotReservationTable(LocalDateTime reserveTime) {
    for (Table table : tables) {
      if (canReserveAfter(table, reserveTime)) {
        return table;
      }
    }
    return null;
  }

  private boolean canReserveAfter(Table table, LocalDateTime reserveTime) {
    // 특정 조건을 만족하는 요소가 하나라도 있는 경우 false를 반환
    return table.reservations.stream().noneMatch(reservation -> {
      LocalDateTime reservationStart = reservation.reserveAt;
      LocalDateTime reservationEnd = reservationStart.plusHours(RESERVATION_DURATION_HOURS);
      return !(reserveTime.isAfter(reservationEnd) || reserveTime.plusHours(RESERVATION_DURATION_HOURS).isBefore(reservationStart));
    });
  }

  class Table {
    private final int id;
    private final List<Reservation> reservations;

    public Table(int id) {
      this.id = id;
      this.reservations = new ArrayList<>();
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("### 테이블번호: ").append(id).append("\n");
      reservations.sort(Comparator.comparing(re -> re.reserveAt));
      for (Reservation reservation : reservations) {
        sb.append(reservation).append("\n");
      }
      return sb.toString();
    }
  }

  static class Reservation {
    private final String id;
    private final String phoneNumber;
    private final int tableId;
    private final LocalDateTime reserveAt;

    public Reservation(String phoneNumber, int tableId, LocalDateTime reserveAt) {
      this.id = UUID.randomUUID().toString(); // 고유 ID 생성
      this.phoneNumber = phoneNumber;
      this.tableId = tableId;
      this.reserveAt = reserveAt;
    }

    @Override
    public String toString() {
      return "예약{전화번호='" + phoneNumber + "', 테이블번호=" + tableId +
          ", 예약시간=" + reserveAt + '}';
    }
  }
}
