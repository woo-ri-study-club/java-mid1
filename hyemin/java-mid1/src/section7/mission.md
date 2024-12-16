
# 미션
어느덧 미션을 마치고 나는 부족한 학점을 위해 계절학기를 신청하게 되었다. 그리고 어느 덧 계절학기 첫 시간이 되었고 자바의 "날짜와 시간"이라는 것을 학습하게 되었다. 그리고 과제가 발표를 하는 것이였다. 한번 발표 대본을 작성해보고 연습해보자!

---
## Java에서의 날짜와 시간 처리

안녕하세요! Java에서 날짜와 시간을 처리하는 방법에 대해 발표를 맡은 김혜민입니다. 

### 1. 날짜와 시간의 중요성
- **일상 생활에서의 역할**:
우리가 매일 사용하는 스마트폰이나 컴퓨터에서 날짜와 시간은 필수적인 정보입니다. 일정을 관리하거나 예약을 하는 등 거의 모든 어플리케이션에서 사용되죠.
특히, 일정을 관리하는 애플리케이션에서는 사용자가 입력한 날짜와 시간을 기반으로 알림 기능을 구현해야 하므로, 날짜와 시간을 정확하게 다루어야 합니다.

- **프로그래밍에서의 역할**:
  1) 예약 시스템: 비행기 예약 시스템이나 호텔 예약 시스템 등에서는 사용자와 즉각적으로 상호작용하기 때문에 날짜와 시간을 정확히 다룰 수 있어야 합니다.
  2) 로그 기록: 서버의 로그 데이터는 특정 시간에 어떤 일이 발생했는지를 기록하는데 사용되며, 이 또한 날짜와 시간을 정확하게 기록하고 처리해야 합니다.

### 2. Java의 날짜와 시간 API
- **Java 8 이후의 변화**:
Java 8에서는 java.time 패키지가 도입되어 날짜와 시간을 처리하는 방식이 더 정확하고 간편해졌습니다.
이전의 java.util.Date와 java.util.Calendar에서 사용하기 어려웠고 부정확하던 점을 보완하여, 더 직관적이고 사용하기 쉬워졌어요.
- **주요 클래스 설명**:
  1) `LocalDate`: 날짜만 표현할 때 사용하며, 시각을 포함하지 않습니다. "2024-12-31"과 같은 형식으로 저장됩니다.
  2) `LocalTime`: 시각을 표현할 때 사용하며, 날짜를 포함하지 않습니다. "14:30:00.213"처럼 저장됩니다.
  3) `LocalDateTime`: 날짜와 시각을 모두 포함하는 클래스입니다. "2024-12-31T14:30:00"처럼 저장됩니다.

### 3. LocalDateTime 클래스
LocalDateTime을 사용하여 날짜와 시간을 생성하는 예제 코드를 살펴봅시다.
```java
LocalDateTime now = LocalDateTime.now();
System.out.println("현재 날짜와 시간: " + now);
```
위 코드는 현재 날짜와 시간을 가져옵니다. 중요한 점은 LocalDateTime 객체는 불변(Immutable)이기 때문에, 객체가 생성된 후에는 그 값을 변경할 수 없다는 것입니다.

### 4. 날짜와 시간 변경하기
- LocalDateTime에선 날짜와 시간을 조작할 수 있는 다양한 메서드가 제공됩니다. 특정 날짜를 설정하거나 연도를 변경하는 예제코드를 살펴봅시다.
```java
LocalDateTime dt = LocalDateTime.of(2023, 3, 15, 8, 30);
LocalDateTime changedDt = dt.withYear(2025);
System.out.println("변경된 날짜: " + changedDt);
```
위 코드에서 withYear 메서드를 사용하여 연도를 변경했습니다.

- `TemporalAdjusters`를 통한 날짜 조작하는 예제 코드도 살펴봅시다.
```java
LocalDateTime nextFriday = dt.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
System.out.println("다음 금요일: " + nextFriday);
```
위 코드에서는 현재 날짜로부터 다음 금요일의 날짜를 계산합니다.

### 5. 날짜와 시간 포맷팅
DateTimeFormatter를 이용해서 사용자에게 보여주는 날짜와 시간을 포맷팅할 수 있습니다.
```java
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH:mm:ss");
String formattedDate = dt.format(formatter);
System.out.println("포맷된 날짜와 시간: " + formattedDate);
```
위 예제에서는 입력받은 날짜와 시간 정보를 더 읽기 쉬운 형식으로 포맷팅했습니다.

### 6. 날짜 계산
- 특정 날짜에 특정한 날짜를 더하거나 빼고 싶을 때 plusDays, minusDays 메서드를 활용할 수 있습니다.
```java
LocalDate today = LocalDate.now();
LocalDate nextWeek = today.plusDays(7);
System.out.println("지정 날짜: " + nextWeek);
```
위 코드는 오늘 날짜에 7일을 더하여 7일 후의 지정 날짜를 계산합니다.

### 7. 타임존 다루기
다양한 지역에서 사용되는 응용 프로그램을 개발할 때는 타임존을 고려해야 합니다. 해외에서는 썸머타임을 적용하는 나라나 지역들이 있으므로 타임존 고려가 필수적입니다. 
ZonedDateTime 클래스를 사용하면 특정 타임존이 적용된 날짜와 시간을 쉽게 다룰 수 있습니다.
```java
ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
System.out.println("서울의 현재 시간: " + zonedDateTime);
```
위 코드는 서울의 현재 시간을 가져옵니다. 타임존을 활용하면 사용자의 위치에 따라 정확한 시간 정보를 알 수 있습니다.

### 8. Instant 클래스
Instant 클래스는 GMT(UTC) 기준으로 특정 시점을 표현합니다. 즉, 1970년 1월 1일 0시 0분 0초(UTC)부터 경과된 시간을 초와 나노초 단위로 표현합니다.
주로 시간 간격을 측정하거나 시간에 대한 정확한 표현이 필요한 경우에 사용됩니다. 예를 들어, 데이터베이스 시간 기록, 여러 시스템 간 데이터 동기화 등에서 유용합니다.
```java
Instant now = Instant.now();
System.out.println("현재 시점: " + now);
```
Instant 클래스는 모든 시스템에서 동일한 기준점인 UTC를 사용하므로, 시간 정보를 저장하거나 교환할 때 일관성을 제공합니다.
일반적으로 날짜와 시간을 사용할 때는 LocalDateTime 또는 ZonedDateTime을 사용하고 Instant는 특정 요구사항이 있을 때 주로 사용합니다.

### 9. Period와 Duration
Java에서는 두 날짜 또는 시간 사이의 차이를 표현하기 위해 Period와 Duration 클래스를 사용합니다.
1) **Period**: 날짜 간의 기간(날, 월, 년 단위)을 나타냅니다.
2) **Duration**: 시간 간의 기간(초, 분, 시 단위)을 나타냅니다.

날짜와 시간을 자바로 다루는 것은 계속해서 발전해왔고 다양한 클래스와 메서드를 활용해서 개발자들이 활용하기 쉬워졌습니다.
더 방대한 내용이 많이 있지만, 대표적인 내용만 다루어보았습니다! 필요한 상황에서 각자에 맞는 API를 활용하고 더 깊이 학습하는 것이 중요할 것 같습니다. 이것으로 발표를 마치겠습니다. 감사합니다.