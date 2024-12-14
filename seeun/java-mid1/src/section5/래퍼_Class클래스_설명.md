안녕하세요! 오늘은 Java의 래퍼 클래스와 Class 클래스에 대해 발표하도록 하겠습니다.

우선 이 두 클래스를 알기 전 기본형에 대해서 알아야 하는데요

기본형은 객체지향언어인 자바에서 객체가 아닙니다. 따라서 몇가지 제한사항이 있습니다.

### 메서드를 가질 수 없다.

기본형은 객체가 아니기 때문에 객체가 메서드를 제공하는 것처럼 기능을 제공할 수 없고 객체를 참조해서 사용하는 컬렉션 프레임워크도 사용할 수 없습니다.

### null 상태를 가질 수 없다.

데이터를 가지지 않는 상태를 뜻하는 null처리를 할 수 없습니다. 따라서 어떤 값이든 저장을 해주어야 합니다.

이로 인한 불편함은 무엇이 있을까요?

먼저 기능인 메서드를 가지지 않기 때문에 자신과 다른 값을 비교하기 위해 외부 메서드를 만들어 사용해야 합니다.
<br>
사실 이런 경우는 자신의 값은 무조건 사용되므로 내부에 자신과 비교하는 기능을 가지는 것이 좋겠죠.

그리고 다음 코드를 봅시다.

```java
private static int findValue(int[] intArr, int target) {
    for (int value : intArr) {
        if (value == target) {
            return value;
        }
    }
    return -1;
}
```
같은 값이 없는 경우 -1을 리턴합니다. 값이 없다라는 의미와 -1이 명확한 연관성을 가지고 있다고 보기는 힘듭니다.

지금까지 말한 한계를 해결하고자 래퍼클래스가 나왔습니다.

## 래퍼 클래스

앞서 말씀드렸듯이 Java에는 int, long, double과 같은 기본형(primitive type)이 있습니다.
<br>
이러한 기본형은 객체가 아니기 때문에 몇 가지 제한사항이 있습니다:

이러한 제한을 해결하기 위해 등장한 것이 바로 래퍼 클래스입니다. 래퍼 클래스는 기본형을 클래스로 감싸는 형태입니다.

```java
public class MyInteger {
    private final int value;

    public MyInteger(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public int compareTo(int target) {
        if (value < target) {
            return -1;
        } else if (value > target) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
```
기본형이었던 int형 value에게 무언가 많은 것이 더해졌습니다. 자체 메서드를 가지는 클래스의 형태가 되었네요.
<br>
이제는 메서드를 사용할 수도 있고 null형태도 가능합니다. 이제는 NPE에 유의해야겠죠.

cf. NullPointerException(NPE) -> 런타임 에러 중 하나로 null값을 가지는 참조 변수로 메서드를 호출하거나 멤버변수에 접근하려고 할 때 발생하는 예외

정리하면, 래퍼클래스는 기본형의 객체버전입니다. 자바는 기본적으로 기본형에 대한 래퍼클래스를 제공하고 있습니다.

기본 래퍼 클래스 안의 필드는 불변으로 정의되고 있고 참조형이기 때문에 필드 값 비교 시 equals로 비교해야 합니다.


## 그럼 객체지향답게 래퍼형을 써야하는 것 아니야?

기본적인 래퍼 클래스의 형태를 보셨는데요. 그렇다면 단순한 값뿐만 아니라 여러가지 기능도 가질 수 있는 참조형 즉 래퍼클래스로만 사용하는 것이 좋지 않을까요?
<br>
답은 아니요입니다. 지금까지 둘 다 공존해온 이유가 있겠죠. 둘다 설계목적에 따라 쓰일 수 있습니다.

다음은 기본형과 래퍼클래스의 변수에 많은 연산을 수행하게끔 작성한 코드입니다. 결과는 어떨까요?
```java
int iterations = 1_000_000_000;
long startTime, endTime;

long sumPrimitive = 0;
startTime = System.currentTimeMillis();
for (int i = 0; i < iterations; i++) {
    sumPrimitive += i;
}
endTime = System.currentTimeMillis();

Long sumWrapper = 0L;
startTime = System.currentTimeMillis();
for (int i = 0; i < iterations; i++) {
sumWrapper += i;
}
endTime = System.currentTimeMillis();
```
```text
sumPrimitive = 499999999500000000
기본 자료형 long 실행시간: 612ms
sumPrimitive = 499999999500000000
기본 자료형 long 실행시간: 1507ms
```
제 컴퓨터에서의 결과는 다음과 같았습니다. 2배가 넘는 속도 차이를 보였고요 다른 컴퓨터에서는 5배까지의 차이도 보였습니다.

기본형은 메모리에서 단순히 타입의 크기만큼의 공간을 차지합니다. 하지만 래퍼 객체는 필드의 값뿐만 아니라 객체의 메타데이터를 포함하기 때문에 더 많은 메모리를 필요로 한다고 합니다.
<br>
대략 8-16바이트 정도를 추가로 사용하면서 성능 상의 차이를 보이는 것입니다.

다만 이런 차이는 실제 어플리케이션에서는 미세한 차이라고 볼 수 있습니다. ms차이 정도니까요.
<br>
하지만 CPU를 오래 점유해서 많은 연산을 처리해야 하는 경우나 배치성 프로그램은 눈에 띄는 성능 저하를 가져올 수 있겠죠.

따라서 결론은 때에 따라 잘 사용해야 한다는 것입니다. 정답은 없으니 설계의 방향에 맞게 선택하시면 됩니다.

래퍼형을 써서 여러가지 기능을 누리는 대신 성능은 조금 느리기 vs 기본형을 써서 코드는 좀 복잡해지는데 성능은 조금 빠르기!

그래도 유지보수와 좋은 코드를 고려하는 것이 좋겠죠.

### 래퍼 클래스의 메서드

기본적인 유틸성 메서드는 거의 지원합니다.
```java
// 값 또는 문자열 래퍼형 변환
Integer i1 = Integer.valueOf(10);
Integer i2 = Integer.valueOf("10");
// 문자열 기본형으로 변환
int intValue = Integer.parseInt("10");

int compareResult = i1.compareTo(20);

// 기본형끼리 연산
Integer.sum(10, 20));
Integer.min(10, 20));
Integer.max(10, 20));
```

### 기본형 <-> 참조형

서로 변환할 수 있도록 메서드를 지원합니다. 5버전부터는 auto boxing을 제공하고 있습니다.

```java
Integer boxedValue = Integer.valueOf(value); // 메서드 이용해서 boxing
int unboxedValue = boxedValue.intValue(); // 메서드 이용해서 unboxing

Integer boxedValue = value; // auto boxing
int unboxedValue = boxedValue; // auto unboxing
```

## Class 클래스

말 그대로 클래스에 대한 클래스입니다. 메타데이터를 조회하고 조작할 수 있게 해줍니다.

```java
Class clazz = String.class; // 클래스에서 조회
Class clazz1 = new String().getClass(); // 인스턴스로 조회
Class clazz2 = Class.forName("java.lang.String"); // 문자열로 조회

// 클래스 내부 모든 요소들 배열
Field[] fields = clazz.getDeclaredFields();
Method[] declaredMethods = clazz.getDeclaredMethods();
Class[] interfaces = clazz.getInterfaces();

// 부모 클래스 정보
String superClassName = clazz.getSuperclass().getName();

```

또 다른 편의를 지원해주는 클래스들이 있습니다.

## System 클래스

시스템 관련 기본 기능을 제공하는 클래스입니다.

```java
long currentTimeMillis = System.currentTimeMillis(); // 현재 시간 (밀리초)
long l = System.nanoTime(); // 현재 시간 (나노초)
System.out.println("getenv: " + System.getenv()); // 환경 변수 읽기

System.out.println("properties: " + System.getProperties());
System.out.println("Java version: " + System.getProperty("java.version"));

char[] originalArray = {'h', 'e', 'l', 'l', 'o'};
char[] copiedArray = new char[5];
System.arraycopy(originalArray, 0, copiedArray, 0, originalArray.length);

System.out.println("copiedArray = " + Arrays.toString(copiedArray));

System.exit(0); // 프로그램 바로 끝내기, 프로그램 종료 상태 코드 전달 (0 : 정상 종료 0아님 : 오류나 예외적인 종료)

```

System.exit()은 가급적이면 사용하지 않는 것이 좋고 로직이 모두 수행된 후에 코드가 종료되도록 하는 것이 좋습니다.


## Math, Random 클래스

수학연산 메서드를 가지는 Math와 랜덤값을 구해주는 Random 클래스입니다.

```java
System.out.println("Math.max(10, 20) = " + Math.max(10, 20));
System.out.println("Math.min(10, 20) = " + Math.min(10, 20));
System.out.println("Math.abs(-10) = " + Math.abs(-10));

System.out.println("Math.ceil(2.1) = " + Math.ceil(2.1)); // 올림
System.out.println("Math.floor(2.1) = " + Math.floor(2.1)); // 내림
System.out.println("Math.round(2.5) = " + Math.round(2.5)); // 반올림

System.out.println("Math.sqrt(4) = " + Math.sqrt(4));
System.out.println("Math.random() = " + Math.random()); // 0.0 ~ 1.0 사이 double값
```

정밀한 반올림이 필요하다면 BigDecimal을 사용합니다. 돈이나 정산 관련한 코딩에서는 많이 사용한다고 해요.


```java
Random random = new Random();
int randomInt = random.nextInt(); // -556300574

double randomDouble = random.nextDouble(); //0.12247177959373867

boolean randomBoolean = random.nextBoolean(); //true

int randomRange1 = random.nextInt(10); // 0~9까지 출력

int randomRange2 = random.nextInt(10) + 1; //1 ~ 10까지 출력
```
특정 값 사이의 랜덤 값을 받을 수 있습니다.

처음 Random 객체를 만들 때 seed값을 매개변수로 줄 수 있습니다.
```java
Random random = new Random(10); //10을 seed로 하는 랜덤 환경 설정
```
하나의 seed 값에 대해서 random 메서드의 결과는 늘 동일해서 특정 seed값 환경에서의 값을 저장해놓고 활용할 수 있겠죠?

지금까지 래퍼 클래스와 다른 여러가지 제공 클래스에 대해서 알아보았습니다.