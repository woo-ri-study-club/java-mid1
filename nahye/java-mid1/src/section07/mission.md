### 미션

어느덧 미션을 마치고 나는 부족한 학점을 위해 계절학기를 신청하게 되었다. 그리고 어느 덧 계절학기 첫 시간이 되었고 자바의 "날짜와 시간"이라는 것을 학습하게 되었다. 그리고 과제가 발표를 하는 것이였다. 한번 발표 대본을 작성해보고 연습해보자!


---
이번 발표주제는 자바의 날짜와 시간 라이브러리입니다.

날짜와 시간을 직접 계산하려면 윤년도 고려해야하고, 각 나라마다 사용하는 시간의 기준도 다르기 때문에 무척 까다롭습니다.
자바에서는 날짜와 시간을 나타내거나 계산할 수 있는 여러 기능들을 제공합니다.

그러면 하나씩 알아보겠습니다.

기본적인 날짜와 시간을 표현할 때는 LocalDateTime을 사용합니다.
## 1. LocalDateTime
특정 지역의 날짜와 시간만 고려할 때 사용합니다.
```
LocalDateTime ldt = LocalDateTime.now(); // 현재 날짜 시간을 기준으로 생성
LocalDateTime ldt = LocalDateTime.of(2020,1,1); // 지정된 날짜를 기준으로 생성
```

기준날짜에서 1달을 더하고 싶다면
```
ldt.plusMonths(1);
```
이렇게 적으면 됩니다. 다양한 plusXxx() 메서드가 존재합니다.

비교할수 있는 메서드도 제공하는데, `isBefore()`, `isAfter()`, `isEqual()`입니다.
```
ldt.isBefore(LocalDateTime.now()); // ldt가 현재시간보다 이전이면 true를 반환 
ldt.isAfter(LocalDateTime.now());  // ldt가 현재시간보다 이후면 true를 반환 
ldt.isEqual(LocalDateTime.now());  // ldt가 현재시간과 같으면 true를 반환 
```

이제 시간대 정보를 포함한 날짜와 시간을 다루는 방법을 알아보겠습니다.
## 2. ZonedDateTime
ZonedDateTime은 "Asia/Seoul"같은 타임존을 포함하고 있습니다.  
타임존은 ZoneId라는 클래스에 저장되어있습니다.
```
ZoneId.of() // 타임존을 직접 제공해서 ZoneId를 반환한다.
ZoneId.systemDefault(); //시스템이 사용하는 기본 ZoneId를 반환한다.
```
```
// 특정 타임존의 현재 시간 얻기
ZonedDateTime tokyoTime = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));
ZonedDateTime newYorkTime = ZonedDateTime.now(ZoneId.of("America/New_York"));

// 다른 타임존으로 시간을 변경할때는 withZoneSameInstant(ZoneId)을 사용합니다.
ZonedDateTime seoulTime = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
ZonedDateTime londonTime = seoulTime.withZoneSameInstant(ZoneId.of("Europe/London"));
```

### OffsetDateTime
OffsetDateTime은 LocalDateTime에 UTC(협정 세계시) 오프셋 정보인 ZoneOffset이 합쳐진 것입니다.
```
2030-01-01T13:30:50+01:00
```
ZoneOffset은 `+01:00`처럼 UTC(협정 세계시)와의 시간 차이인 오프셋 정보만 보관합니다.

- ZonedDateTime은 구체적인 지역의 시간을 다룰 때 사용
- OffsetDateTime은 UTC와의 시간 차이만을 나타낼 때 사용 (주로 로그나 데이터를 기록할 때)

## 3. Instant
Instant는 UTC(협정 세계시)를 기준으로 하는, 시간의 한 지점을 나타냅니다.
날짜와 시간 계산에 사용할 때는 적합하지않고, 전 세계적인 시간 기준 필요시에 사용합니다.
예를 들어 로그 기록이나 타임스탬프, 서버간의 동기화 등에 사용합니다.
데이터베이스에 날짜와 시간 정보를 저장하거나, 다른 시스템과 날짜와 시간 정보를 교환할 때 사용하면
모든 시스템에서 동일한 기준점을 사용하게 되므로 데이터의 일관성을 유지할수있습니다.

```
// UTC 기준 현재시간의 Instant 생성
Instant now = Instant.now(); 

// 다른 타입의 날짜와 시간을 기준으로 Instant를 생성
ZonedDateTime zdt = ZonedDateTime.now();
Instant instant = Instant.from(zdt);

// 에포크 시간을 기준으로 Instant를 생성
Instant instant1 = Instant.ofEpochSecond(0);
Instant instant2 = Instant.ofEpochSecond(1000);

// 현재 시간의 에포크 초 얻기
long currentEpochSecond = Instant.now().getEpochSecond();
```

> 참고: 에포크 시간은 1970년 1월 1일 00:00:00 UTC를 기준으로 경과한 시간을 초 단위로 나타낸 것입니다.

Instant 내부에는 초데이터만 들어있으므로 초 계산을 할 수있습니다.
```java
plusSecond(); // 초를 더한다.
```



## 4. Duration과 Period
시간의 기간, 시간의 간격을 계산하는 클래스입니다.

### Duration
시간 사이의 간격을 시, 분, 초 단위로 나타냅니다.
```
Duration duration1 = Duration.ofDays(1);      // 1일
Duration duration2 = Duration.ofHours(2);     // 2시간
Duration duration3 = Duration.ofMinutes(30);  // 30분

// 두 시간 사이의 차이 계산
Duration duration = Duration.between(start, end);
```
- 계산에 사용  
  1:00에 30분을 더하면 1:30이 된다. 라고 표현할때 특정시간에 30분이라는 시간(시간의 간격)을 더할 수 있다.
### Period
두 날짜 사이의 간격을 년, 월, 일 단위로 나타냅니다.
```
Period period1 = Period.ofYears(1);      // 1년
Period period2 = Period.ofMonths(6);     // 6개월
Period period3 = Period.ofDays(15);      // 15일

// 두 날짜 사이의 차이 계산
Period period = Period.between(startDate, endDate);
```
- 계산에 사용  
  2030년 1월 1일에 10일을 더하면 2030년 1월 11일이 됩니다.

## 5. 날짜와 시간의 핵심 인터페이스 
자바의 날짜와 시간 API에는 몇 가지 핵심 인터페이스가 있습니다.
- TemporalAccessor - 날짜와 시간을 읽기 위한 기본 인터페이스
- Temporal - TemporalAccessor의 하위 인터페이스로 날짜와 시간을 조작(추가, 빼기 등)하기 위한 기능을 제공
  - 구현으로 LocalDateTime, LocalDate, LocalTime, ZonedDateTime, OffsetDateTime, Instant 등이 있다. 
- TemporalAmount - 시간의 간격(시간의 양, 기간)을 나타내며, 날짜와 시간 객체에 적용하여 그 객체를 조정
  - 구현으로 Period, Duration이 있다.

###  시간의 단위와 시간 필드 
### - TemporalUnit과 ChronoUnit

TemporalUnit은 시간의 단위를 나타내는 인터페이스이며, ChronoUnit은 이를 구현한 열거형입니다.  
ChronoUnit은 다양한 시간 단위를 제공합니다.
ChronoUnit 주요 상수
```
// 날짜 관련 단위
ChronoUnit.YEARS          // 연
ChronoUnit.MONTHS         // 월
ChronoUnit.WEEKS          // 주
ChronoUnit.DAYS           // 일

// 시간 관련 단위
ChronoUnit.HOURS          // 시
ChronoUnit.MINUTES        // 분
ChronoUnit.SECONDS        // 초
ChronoUnit.MILLIS        // 밀리초
ChronoUnit.NANOS         // 나노초
```
### - TemporalField와 ChronoField
TemporalField는 시간의 필드(연, 월, 일 등)를 나타내는 인터페이스이며, ChronoField는 이를 구현한 열거형입니다.  
ChronoField의 주요상수 
```
// 날짜 관련 필드
ChronoField.YEAR              // 연도
ChronoField.MONTH_OF_YEAR     // 월(1-12)
ChronoField.DAY_OF_MONTH      // 일(1-31)
ChronoField.DAY_OF_WEEK       // 요일(1-7)
ChronoField.DAY_OF_YEAR       // 일(1-365/366)

// 시간 관련 필드
ChronoField.HOUR_OF_DAY       // 시(0-23)
ChronoField.MINUTE_OF_HOUR    // 분(0-59)
ChronoField.SECOND_OF_MINUTE  // 초(0-59)
```

## 6. 날짜와 시간 조회하고 조작하기
```
⭐️  인터페이스를 통해 특정 구현 클래스와 무관하게 아주 일관성 있는 시간 조회, 조작 기능을 제공합니다.
    수많은 구현에 관계없이 일관성 있는 방법으로 시간을 조회하고 조작할 수 있습니다.
```

### - 날짜와 시간 조회하기
조회하려면 날짜와 시간 중 어떤 필드를 조회해야할지 선택해야 합니다.  
그때 ChronoField가 사용됩니다.
```java
public class GetTimeMain {
    public static void main(String[] args) {
        LocalDateTime dt = LocalDateTime.of(2030, 1, 1, 13, 30, 59);
        System.out.println("YEAR = " + dt.get(ChronoField.YEAR));              // 출력: YEAR = 2030
        System.out.println("MONTH_OF_YEAR = " + dt.get(ChronoField.MONTH_OF_YEAR));    // 출력: MONTH_OF_YEAR = 1
        System.out.println("DAY_OF_MONTH = " + dt.get(ChronoField.DAY_OF_MONTH));      // 출력: DAY_OF_MONTH = 1
        System.out.println("HOUR_OF_DAY = " + dt.get(ChronoField.HOUR_OF_DAY));        // 출력: HOUR_OF_DAY = 13
        System.out.println("MINUTE_OF_HOUR = " + dt.get(ChronoField.MINUTE_OF_HOUR));  // 출력: MINUTE_OF_HOUR = 30
        System.out.println("SECOND_OF_MINUTE = " + dt.get(ChronoField.SECOND_OF_MINUTE)); // 출력: SECOND_OF_MINUTE = 59
    }
}
```
**TemporalAccessor.get(TemporalField field)**
- get(TemporalField field)을 호출할 때 어떤 날짜와 시간 필드를 조회할지 TemporalField의 구현인 ChronoField를 인수로 전달

편의 메서드도 제공합니다.  
```
System.out.println("YEAR = " + dt.getYear());
System.out.println("MONTH_OF_YEAR = " + dt.getMonthValue());
System.out.println("DAY_OF_MONTH = " + dt.getDayOfMonth());
System.out.println("HOUR_OF_DAY = " + dt.getHour());
System.out.println("MINUTE_OF_HOUR = " + dt.getMinute());
System.out.println("SECOND_OF_MINUTE = " + dt.getSecond());
```
- dt.get(ChronoField.DAY_OF_MONTH) → dt.getDayOfMonth();

### - 날짜와 시간 조작하기

날짜와 시간을 조작하려면 어떤 시간 단위(Unit)를 변경할지 선택해야 합니다.  
이때 날짜와 시간의 단위를 뜻하는 ChronoUnit이 사용됩니다.  
```java
public class ChangeTimePlusMain {
    public static void main(String[] args) {
        LocalDateTime dt = LocalDateTime.of(2018, 1, 1, 13, 30, 59);
        LocalDateTime plusDt1 = dt.plus(10, ChronoUnit.YEARS);
        LocalDateTime plusDt2 = dt.plusYears(10);

        Period period = Period.ofYears(10);
        LocalDateTime plusDt3 = dt.plus(period);
    }
}
```
**Temporal.plus(long amountToAdd, TemporalUnit unit)**
- plus(long amountToAdd, TemporalUnit unit)를 호출할 때 더하기 할 숫자와 시간의 단위(Unit)를 전달하면 됩니다.
- 불변이므로 반환 값을 받아야 합니다.
- 편의 메소드를 제공합니다 
  - dt.plus(10, ChronoUnit.YEARS) → dt.plusYears(10)

### - with()메서드
```java
public class ChangeTimeWithMain {
    public static void main(String[] args) {
        LocalDateTime dt = LocalDateTime.of(2018, 1, 1, 13, 30, 59);
        LocalDateTime changedDt1 = dt.with(ChronoField.YEAR, 2020);
        LocalDateTime changedDt2 = dt.withYear(2020);

        // TemporalAdjuster 사용
        // 다음주 금요일
        LocalDateTime with = dt.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        System.out.println("기준 날짜: " +dt);      // 출력: 기준 날짜: 2018-01-01T13:30:59
        System.out.println("다음 금요일: " +with);   // 출력: 다음 금요일: 2018-01-05T13:30:59

        // 이번 달의 마지막 일요일
        LocalDateTime with2 = dt.with(TemporalAdjusters.lastInMonth(DayOfWeek.SUNDAY));
        System.out.println("같은 달의 마지막 일요일 = " + with2); // 출력: 같은 달의 마지막 일요일 = 2018-01-28T13:30:59
    }
}
```
**Temporal with(TemporalField, long newValue)**
- Temporal.with()를 사용하면 날짜와 시간의 특정 필드의 값만 변경할 수 있다.
- 불변이므로 반환 값을 받아야 한다.

- 편의메서드가 제공된다 dt.with(ChronoField.YEAR, 2020) → dt.withYear(2020)

**TemporalAdjuster 사용**  
TemporalAdjuster는 날짜와 시간을 변경하는 다양한 방법을 제공하는 인터페이스입니다.  
자바가 필요한 구현체들을 TemporalAdjusters에 다 모아뒀습니다.  
```
주요 TemporalAdjusters 메서드:
- firstDayOfMonth() : 이번 달의 첫 날
- lastDayOfMonth() : 이번 달의 마지막 날
- firstDayOfNextMonth() : 다음 달의 첫 날
- firstDayOfYear() : 올해의 첫 날
- lastDayOfYear() : 올해의 마지막 날
- next(DayOfWeek) : 다음 요일
- previous(DayOfWeek) : 이전 요일
- dayOfWeekInMonth(int ordinal, DayOfWeek) : 이번 달의 n번째 요일
```
- TemporalAdjusters.next(DayOfWeek.FRIDAY) : 다음주 금요일을 구한다.
- TemporalAdjusters.lastInMonth(DayOfWeek.SUNDAY): 이번 달의 마지막 일요일을 구한다.

## 7. 날짜와 시간 문자열 파싱과 포맷팅

날짜와 시간 데이터를 원하는 포맷의 문자열로 변경하는 것을 **포맷팅**이라고 합니다.
```
LocalDate date = LocalDate.of(2024, 12, 31);
LocalDateTime dateTime = LocalDateTime.of(2024, 12, 31, 23, 59, 59);

// 기본 포맷팅
DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
String formattedDate = date.format(formatter1);  // 2024년 12월 31일

// 시간 포함 포맷팅
DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
String formattedDateTime = dateTime.format(formatter2);  // 2024-12-31 23:59:59

```
문자열을 날짜와 시간 데이터로 변경하는 것을 **파싱**이라고 합니다.
```
String input1 = "2030년 01월 01일";
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
LocalDate parsedDate = LocalDate.parse(input1, formatter);  // 2030-01-01

String input2 = "2030-01-01 13:30:00";
DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
LocalDateTime parsedDateTime = LocalDateTime.parse(input2, formatter2);  // 2030-01-01T13:30:00
```
- LocalDateTime과 같은 같은 날짜와 시간객체를 원하는 형태의 문자로 변경하려면 DateTimeFormatter를 사용 하고 여기에 ofPattern()으로 원하는 포맷을 지정하면 됩니다.

참고 : 주요 포맷 패턴
```
- yyyy: 연도 (4자리)
- MM: 월 (01-12)
- dd: 일 (01-31)
- HH: 24시간제 (00-23)
- hh: 12시간제 (01-12)
- mm: 분 (00-59)
- ss: 초 (00-59)
- a: 오전/오후 마커
```

감사합니다.