## 날짜, 시간 관련 라이브러리의 필요성
우선 날짜와 시간 라이브러리의 존재 이유부터 간단하게 짚고 넘어가겠습니다. 저희가 일상적으로는 달력과 시계로 간편하게 시간 정보를 접할 수 있기에, 굳이 필요할까? 라는 의문이 들 수도 있습니다. 하지만 실제 날짜, 시간 계산은 꽤나 복잡한 로직을 갖고 있습니다. 달마다 다른 날짜 수, 60진법으로 이루어진 시간, 시간차로 인한 다른 국가의 시간 등... 직접 구현하기에는 내용도 로직도 방대하고 복잡합니다. 이 불편함을 한 번에 해소시켜주는 것이 바로 날짜와 시간 관련 라이브러리입니다.

## 본격적인 라이브러리 설명 전 짚어야 할 개념
한국에서는 사용하지 않거나 생소한 시간 개념이 있기 때문에, 본격적으로 라이브러리를 학습하기 전에 몇 가지 개념을 알아야합니다.

### 일광 절약 시간(DST) = 썸머타임
일 년 동안 태양이 뜨는 시기가 조금씩 달라집니다. 여름은 해가 빠르게 뜨고, 겨울은 해가 늦게 뜨기 마련이죠. 이 태양이 뜨는 시기에 맞춰 시간을 당기거나 늦추는 것을 일광 절약 시간이라고 합니다. 한국에서는 쓰지 않는 개념이지만, 해외 서비스를 염두하고 있다면 알고 있어야 하는 내용입니다.

### 타임존
세계 협정시, UTC로부터의 각 국의 시간 차이를 정의한 것이 타임존입니다. 'UTC +09:00' 같은 표기가 바로 그것입니다.

이 두 개념의 복잡한 계산은 라이브러리 내에서 모두 해결해주기 때문에, 저희는 정의만 잘 이해하고 있으면 충분합니다.

## java.time 패키지의 탄생
요즘 만드는 프로젝트들의 시간, 날짜 데이터는 거의 대부분 `java.time` 패키지를 이용합니다. 이 패키지는 자바 8부터 나온 것으로, 이전의 java.util.Date나 java.util.Calendar의 문제점을 개선한 라이브러리입니다. 모든 클래스가 불변 객체로 작성되었다는 특징이 존재하며, 사이드 이펙트로부터 안전하고, 스레드 안전성이 보장된다는 장점이 있습니다. 메서드명도 매우 직관적입니다. 이제 이 내부의 대표적 클래스들을 확인해보겠습니다.

### LocalDate, LocalTime, LocalDateTime
**한 지역**의 날짜, 시간, 혹은 그 둘 모두를 표기할 수 있는 클래스입니다. 특정 지역의 시간과 날짜만을 나타내기 때문에 타임존을 고려하지 않습니다. 국내 서비스만을 고려할 때 흔히 사용되는 클래스들입니다.

LocalDate의 주요 메서드부터 확인하겠습니다.
```
LocalDate now = LocalDate.now(); //현재 날짜를 반환합니다.

LocalDate of = LocalDate.of(2024, 12, 16); //년, 월, 일을 매개변수로 받아 입력한 날짜를 반환합니다.

LocalDate plusOf = of.plusDays(n); //n일 후의 날짜를 반환합니다.
LocalDateTime startOfDay = of.atStartOfDay(); //of 날짜의 시작 시점을 반환합니다.
LocalDateTime atTime = of.atTime(21, 16); //of 날짜의 시간 정보도 더해 LocalDateTime으로 반환합니다.

Month ofMonth = of.getMonth(); //of 날짜의 달을 Month enum으로 반환합니다.
Month ofMonthValue = of.getMonthValue(); //of 날짜의 달을 int로 반환합니다.

LocalDate ofWith = of.with(TemporalAdjusters.next(DayOfWeek.MONDAY)); //of 날짜 기준 다음 월요일을 반환합니다.

boolean isAfter = of.isAfter(LocalDate.of(2024, 12, 31)); //of 날짜가 매개변수의 날짜보다 늦는지 boolean으로 반환합니다.
```

이외에도 다양하고 범용적인 메서드들이 매우 많지만, 시간상 넘어가겠습니다. 필요한 메서드는 그때 그때 자바 공식 문서를 참고하시면 좋습니다. 그리고 이후에 나오는 클래스의 메서드들도 네이밍과 역할이 동일한 것이 많기 때문에 그런 부분도 아래에서는 중요한 몇 개만 짚고 넘기도록 하겠습니다.

LocalTime은 한 지역의 시간을 나타내는 클래스입니다. 주요 메서드는 LocalDate와 거의 유사하기에 넘어가겠습니다. LocalDateTime은 LocalDate와 LocalTime을 합친 개념으로, 사용법은 위와 유사합니다.

참고로 LocalDateTime은 `.toLocalDate()`, `toLocalTime()` 로 각각 LocalDate와 LocalTime으로 변환해 반환하는 편의 메서드도 존재합니다.

### ZonedDateTime
ZonedDateTime은 타임존을 고려한 클래스입니다. 타임존에는 썸머타임 정보와 협정시로부터의 시간 차인 오프셋이 함께 담겨있고, 이는 ZoneId라는 클래스에 저장되어 있습니다.

```
ZoneId.getAvailableZoneIds() //가능한 타임존들을 Set에 담아 반환합니다.

ZoneId zoneId = ZoneId.systemDefault(); //운영체제에 있는 국가 정보를 통해 타임존을 반환합니다.
```

ZonedDateTime은 결과적으로 LocaDateTime과 타임존 관련 ZoneId와 ZoneOffset이 합쳐진 클래스입니다. 사용법 자체는 LocalDateTime과 유사하나, of 메서드로 인스턴스 생성 시 ZoneId 정보도 함께 들어갑니다.

```
 ZonedDateTime zdt = ZonedDateTime.of(LocalDateTime.of(2024, 12, 31, 13, 0, 0), ZoneId.of("Asia/Seoul"));
 
 ZonedDateTime utcZdt = zdt.withZoneSameInstans(ZoneId.of("UTC")); //타임존을 변경 한 후, 그 타임존에 맞게 변환된 시간을 반환합니다.
```

### OffsetDateTime
타임존은 없이 ZoneOffset과 LocalDateTime을 포함한 클래스입니다. 세계 협정시와의 시간 차를 나타낼 때 사용하는 클래스로 주로 로그 기록이나, 데이터 일괄 저장 처리 시 사용합니다. 사용법은 ZonedDateTime과 유사합니다.

```
OffsetDateTime odt = OffsetDateTime.of(LocalDateTime.of(2024, 12, 31, 13, 0, 0), ZoneOffset.of("+09:00"));
```

### Instant
세계 협정시 시작 시간으로부터, 특정 시간까지의 시간을 **나노초** 단위로 나타내주는 클래스입니다. 어느 국가에서든 동일한 값을 반환해주기 때문에 일관성이 보장된다는 장점이 존재하지만, 사람이 이해하고 이용하기에는 비직관적입니다. 따라서 변하지 않는 시간 기준이 필요한 경우들, 예를 들어 로그 기록, 트랜잭션, 서버 동기화 등에 사용됩니다.

### Period, Duration
두 시간 사이의 기간을 표현하는 클래스들입니다. Period는 년, 월, 일 단위이고 Duration은 시, 분, 초 단위로 기간을 표시합니다. 두 클래스의 사용법은 거의 동일하므로 여기에서는 Period의 사용법만 간단히 알아보겠습니다.

```
Period period = Period.ofDays(10); //기간이 10일인 Period를 반환합니다.

LocalDate now = LocalDate.now();
LocalDate plusNow = now.plus(period); //현재 날짜에 10일을 더한 날짜를 반환합니다.

Period between = Period.between(LocalDate.of(...), LocalDate.of(...)); //두 날짜 사이의 기간을 반환합니다.
```

## 핵심 인터페이스

### Temporal, TemporalAmount
특정 날짜나 시간의 시점을 나타내는 클래스들의 경우는 `Temporal` 인터페이스를 구현하고 간격을 나타내는 클래스들은 `TemporalAmount` 인터페이스를 구현합니다.

Temporal 인터페이스는 상위 인터페이스로 TemporalAccessor를 포함하고 있으며, TemporalAccessor는 최소한의 날짜 시간 정보 기능을 제공합니다. Temporal은 본격적인 날짜 조작 기능을 제공합니다.

TemporalAmount 인터페이스는 특정 시점에 기간으로 조작할 수 있는 기능이 포함되어 있습니다.

### TemporalUnit(ChronoUnit), TemporalField(ChronoField)
TemporalUnit 인터페이스는 날짜와 시간의 측정 단위를 나타냅니다. java.time.temporal.ChronoUnit이라는 enum 타입의 구현체를 주로 사용합니다. ChronoUnit의 내부는 아래와 같습니다.
```
    NANOS("Nanos", Duration.ofNanos(1)),

    MICROS("Micros", Duration.ofNanos(1000)),

    MILLIS("Millis", Duration.ofNanos(1000_000)),

    SECONDS("Seconds", Duration.ofSeconds(1)),

    MINUTES("Minutes", Duration.ofSeconds(60)),

    HOURS("Hours", Duration.ofSeconds(3600)),

    HALF_DAYS("HalfDays", Duration.ofSeconds(43200)),

    DAYS("Days", Duration.ofSeconds(86400)),

    WEEKS("Weeks", Duration.ofSeconds(7 * 86400L)),

    MONTHS("Months", Duration.ofSeconds(31556952L / 12)),
   
    YEARS("Years", Duration.ofSeconds(31556952L)),

    DECADES("Decades", Duration.ofSeconds(31556952L * 10L)),

    CENTURIES("Centuries", Duration.ofSeconds(31556952L * 100L)),

    MILLENNIA("Millennia", Duration.ofSeconds(31556952L * 1000L)),

    ERAS("Eras", Duration.ofSeconds(31556952L * 1000_000_000L)),

    FOREVER("Forever", Duration.ofSeconds(Long.MAX_VALUE, 999_999_999));
```

주요 메서드도 간단하게 살펴보겠습니다.

```
ChronoUnit.SECONDS.between(LocalTime.of(...), LocalTime.of(...)); //두 시간 사이의 기간을 초로 반환합니다.
```

ChronoFiled는 특정 시간의 연도, 월, 일 처럼 특정 부분만을 나타내는 구현체입니다. ChronoUnit과 마찬가지로 enum 타입으로 구현되어있습니다. 내부를 확인해보겠습니다.

```
    NANO_OF_SECOND("NanoOfSecond", NANOS, SECONDS, ValueRange.of(0, 999_999_999)),

    NANO_OF_DAY("NanoOfDay", NANOS, DAYS, ValueRange.of(0, 86400L * 1000_000_000L - 1)),

    MICRO_OF_SECOND("MicroOfSecond", MICROS, SECONDS, ValueRange.of(0, 999_999)),

    MICRO_OF_DAY("MicroOfDay", MICROS, DAYS, ValueRange.of(0, 86400L * 1000_000L - 1)),

    MILLI_OF_SECOND("MilliOfSecond", MILLIS, SECONDS, ValueRange.of(0, 999)),
 
    MILLI_OF_DAY("MilliOfDay", MILLIS, DAYS, ValueRange.of(0, 86400L * 1000L - 1)),

    SECOND_OF_MINUTE("SecondOfMinute", SECONDS, MINUTES, ValueRange.of(0, 59), "second"),

    SECOND_OF_DAY("SecondOfDay", SECONDS, DAYS, ValueRange.of(0, 86400L - 1)),

    MINUTE_OF_HOUR("MinuteOfHour", MINUTES, HOURS, ValueRange.of(0, 59), "minute"),

    MINUTE_OF_DAY("MinuteOfDay", MINUTES, DAYS, ValueRange.of(0, (24 * 60) - 1)),

    HOUR_OF_AMPM("HourOfAmPm", HOURS, HALF_DAYS, ValueRange.of(0, 11)),

    CLOCK_HOUR_OF_AMPM("ClockHourOfAmPm", HOURS, HALF_DAYS, ValueRange.of(1, 12)),

    HOUR_OF_DAY("HourOfDay", HOURS, DAYS, ValueRange.of(0, 23), "hour"),

    CLOCK_HOUR_OF_DAY("ClockHourOfDay", HOURS, DAYS, ValueRange.of(1, 24)),

    AMPM_OF_DAY("AmPmOfDay", HALF_DAYS, DAYS, ValueRange.of(0, 1), "dayperiod"),

    DAY_OF_WEEK("DayOfWeek", DAYS, WEEKS, ValueRange.of(1, 7), "weekday"),

    ALIGNED_DAY_OF_WEEK_IN_MONTH("AlignedDayOfWeekInMonth", DAYS, WEEKS, ValueRange.of(1, 7)),

    ALIGNED_DAY_OF_WEEK_IN_YEAR("AlignedDayOfWeekInYear", DAYS, WEEKS, ValueRange.of(1, 7)),

    DAY_OF_MONTH("DayOfMonth", DAYS, MONTHS, ValueRange.of(1, 28, 31), "day"),

    DAY_OF_YEAR("DayOfYear", DAYS, YEARS, ValueRange.of(1, 365, 366)),

    EPOCH_DAY("EpochDay", DAYS, FOREVER, ValueRange.of(-365243219162L, 365241780471L)),

    ALIGNED_WEEK_OF_MONTH("AlignedWeekOfMonth", WEEKS, MONTHS, ValueRange.of(1, 4, 5)),

    ALIGNED_WEEK_OF_YEAR("AlignedWeekOfYear", WEEKS, YEARS, ValueRange.of(1, 53)),

    MONTH_OF_YEAR("MonthOfYear", MONTHS, YEARS, ValueRange.of(1, 12), "month"),

    PROLEPTIC_MONTH("ProlepticMonth", MONTHS, FOREVER, ValueRange.of(Year.MIN_VALUE * 12L, Year.MAX_VALUE * 12L + 11)),
 
    YEAR_OF_ERA("YearOfEra", YEARS, FOREVER, ValueRange.of(1, Year.MAX_VALUE, Year.MAX_VALUE + 1)),

    YEAR("Year", YEARS, FOREVER, ValueRange.of(Year.MIN_VALUE, Year.MAX_VALUE), "year"),

    ERA("Era", ERAS, FOREVER, ValueRange.of(0, 1), "era"),

```

주요 메서드도 ChronoUnit과 유사하기 때문에 넘기도록 하겠습니다.

### TemporalAdjuster
이전에 LocalDate 클래스 부분에서, with 메서드 사용 시 매개변수에 들어갔던 부분입니다. 특정 일 기준 다음 금요일, 이 달의 마지막 금요일 등의 복잡한 계산을 편하게 이용할 수 있습니다.

## 문자열 파싱과 포맷팅
### Date → String 포맷팅
```
DateTimeformatter formatter = DateTimeformatter.ofPattern("yyyy/MM/dd");

String formatDate = LocalDate.of(...).format(formatter); //미리 정의한 포맷터 형식으로 날짜를 문자열로 변환합니다.
```

### String → Date 파싱
```
String input = "2024년 12월 31일";

LocalDate parsing = LocalDate.parse(input, formatter); //정해진 형식을 기준으로 문자열을 날짜로 변환합니다.
```

DateTimeformatter에서 사용하는 대표적인 포맷터 패턴은 아래와 같습니다.
- u:연도
- y:연도
- D: 연중 일수
- M: 연중 월
- d: 특정 달의 일수
- Q/q: 분기
- Y: 주 기준 연도
- w: 주 기준 연중 주
- W: 특정 월의 주
- E: 요일
- H: 24시간제 시
- m: 분
- s: 초