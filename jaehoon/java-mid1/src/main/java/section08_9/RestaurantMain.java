package section08_9;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.function.Consumer;

public class RestaurantMain {

  public static void main(String[] args) {
    // 좌석이 5개인 레스토랑 생성
    Restaurant restaurant = new Restaurant(5);

    // 오픈시간에 5팀 예약 추가
    LocalDateTime reserveAt1 = LocalDateTime.of(2024, 12, 9, 14, 0, 0);
    restaurant.addReservation("010-1234-1111", reserveAt1);
    restaurant.addReservation("010-1234-2222", reserveAt1);
    restaurant.addReservation("010-1234-3333", reserveAt1);
    restaurant.addReservation("010-1234-4444", reserveAt1);
    restaurant.addReservation("010-1234-5555", reserveAt1);
    System.out.println();

    // NOTE: 기존 좌석들의 예약시간의 3시간이내 예약이므로 실패
    LocalDateTime reserveAt2 = LocalDateTime.of(2024, 12, 9, 12, 0, 0);
    restaurant.addReservation("010-1234-1234", reserveAt2);
    System.out.println();

    // NOTE: 예약은 3시간 이후이므로 예약 성공
    LocalDateTime reserveAt3 = LocalDateTime.of(2024, 12, 9, 10, 0, 0);
    restaurant.addReservation("010-1234-6666", reserveAt3);
    restaurant.addReservation("010-1234-7777", reserveAt3);
    Optional<String> reservationId = restaurant.addReservation("010-1234-8888", reserveAt3);
    System.out.println();

    // 예약되지 않은 번호로 검색
    System.out.println("### `010-1234-1234` 예약 기록 조회 ###");
    restaurant.showReservationByPhoneNumber("010-1234-1234");
    System.out.println();

    // 예약된 번호로 검색
    System.out.println("### `010-1234-1111` 예약 기록 조회 ###");
    restaurant.showReservationByPhoneNumber("010-1234-1111");
    System.out.println();

    // 예약취소 성공, 실패 테스트
    reservationId.ifPresent(restaurant::cancelReservation);
    restaurant.cancelReservation("12d48107-982b-430f-8b19-420d0ddf2fee");
    System.out.println();

    // 레스토랑의 테이블 예약 정보 출력
    // NOTE: 익명 클래스 활용
    Consumer<Restaurant> consumer = re -> {
      for (Restaurant.Table table : re.getTables()) {
        System.out.println(table);
      }
    };
    consumer.accept(restaurant);
    System.out.println();

  }
}
