## 미션

안녕하세요, 여러분. 오늘은 자바의 "날짜와 시간" 라이브러리에 대해 발표하겠습니다.

### 1. 날짜와 시간 라이브러리가 필요한 이유

자바의 초기 버전에서는 `java.util.Date`와 `java.util.Calendar` 클래스를 사용했습니다. 하지만 이 클래스들은 다음과 같은 문제를 가지고 있었습니다.

- 설계가 복잡하고 직관적이지 않다.
- 불변성을 지원하지 않아 동시성 문제를 일으킬 수 있다.
- 월(Month)와 같은 값이 0부터 시작해 혼란을 주었다.

그래서 자바 8에서는 `java.time` 패키지가 도입되었고, 이를 통해 날짜와 시간 처리가 훨씬 간단하고 강력해졌습니다.

---

### 2. 자바 날짜와 시간 라이브러리 소개

java.time 패키지는 다음과 같은 주요 클래스를 제공합니다.

- LocalDate, LocalTime, LocalDateTime: 로컬 날짜와 시간 처리.
- ZonedDateTime: 시간대(time zone)를 포함한 날짜와 시간.
- Instant: 기계 중심의 타임스탬프.
- Duration, Period: 시간 간격과 기간.

각 클래스는 불변 객체로 설계되어 안전하고 효율적인 날짜와 시간 처리를 제공합니다.

---

### 3. LocalDateTime

LocalDateTime은 시간대가 없는 현재의 날짜와 시간을 처리할 때 사용됩니다. 예를 들어,

```java
LocalDateTime now = LocalDateTime.now();
System.out.println("현재 날짜와 시간: " + now);
```

이를 통해 현재의 날짜와 시간을 간단히 출력할 수 있습니다. 또한, 날짜를 쉽게 조작할 수 있습니다.

```java
LocalDateTime future = now.plusDays(7).minusMonths(1);
System.out.println("7일 후, 1개월 전 날짜: " + future);
```

---

### 4. ZonedDateTime

시간대가 중요한 경우 ZonedDateTime을 사용합니다. 예를 들어 서울과 뉴욕의 현재 시간을 출력하려면,

```java
ZonedDateTime seoulTime = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
ZonedDateTime newYorkTime = ZonedDateTime.now(ZoneId.of("America/New_York"));
System.out.println("서울 시간: " + seoulTime);
System.out.println("뉴욕 시간: " + newYorkTime);
```

이처럼 ZonedDateTime은 국제화된 시스템에서 유용합니다.

---

### 5. Instant

Instant는 타임스탬프를 나타내며, UTC 기준으로 시간을 다룹니다. 예를 들어,

```java
Instant timestamp = Instant.now();
System.out.println("현재 타임스탬프: " + timestamp);
```

이는 서버 로그 기록이나 이벤트 트래킹에 유용합니다.

---

### 6. Duration과 Period

Duration은 두 시간 간의 간격을 초 또는 나노초로 나타냅니다.

```java
Duration duration = Duration.between(startTime, endTime);
System.out.println("시간 간격: " + duration.toHours() + " 시간");
```

Period는 두 날짜 간의 차이를 연, 월, 일로 계산합니다.

```java
Period period = Period.between(startDate, endDate);
System.out.println("날짜 간격: " + period.getYears() + "년 " + period.getMonths() + "월 " + period.getDays() + "일");
```

---

### 7. 날짜와 시간 조회 및 조작

java.time 라이브러리는 날짜와 시간을 직관적으로 조회하고 조작할 수 있는 다양한 메서드를 제공합니다.

- 특정 날짜 설정: LocalDate.of(2024, 12, 25)
- 날짜 비교: date1.isBefore(date2)
- 포맷팅: date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))