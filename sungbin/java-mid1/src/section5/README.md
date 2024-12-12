## 미션

안녕하세요, 오늘은 자바의 래퍼 클래스와 Class 클래스에 대해 설명드리겠습니다. 이 주제는 자바의 기본형 데이터와 객체 지향적 개념을 연결하고, 프로그램의 동적 기능을 이해하는 데 중요한 역할을 합니다. 발표를 크게 다섯 부분으로 나누어 설명하겠습니다.

- 래퍼 클래스란 무엇이고, 왜 사용하는가?
- 오토 박싱과 언박싱
- Class 클래스의 역할과 활용 사례
- System 클래스
- Math 클래스

### 1. 래퍼 클래스란 무엇인가?

먼저, 자바는 두 가지 데이터 유형을 제공합니다: 기본형(Primitive Type)과 참조형(Reference Type)입니다. 기본형은 메모리 사용이 적고 성능이 뛰어나지만 객체로 사용할 수 없다는 단점이 있습니다.
예를 들어, int, double 같은 기본형은 컬렉션 프레임워크(ArrayList 등)에서 사용할 수 없습니다.(객체지향적이지 않다.) 또한 '값이 없다'라는 null표현을 할 수 없습니다. 이러한 한계를 해결하기 위해 자바는 래퍼 클래스(Wrapper Class)를 제공합니다.

래퍼 클래스는 기본형을 객체로 감싸주는 역할을 합니다. 다음은 기본형과 래퍼 클래스의 매칭입니다.

- int → Integer
- double → Double
- boolean → Boolean

```java
int num = 5; // 기본형
Integer wrappedNum = Integer.valueOf(num); // 래퍼 클래스
```

래퍼 클래스는 다음과 같은 장점을 제공합니다.

- 객체로서의 기능 활용: 메서드를 통해 추가적인 기능 사용 가능.
- 컬렉션 프레임워크와 호환성: 객체로 다뤄야 하는 상황에서 사용.
- 기본형 데이터에 대한 유연성 제공.

### 오토박싱과 언박싱

자바 5부터는 오토 박싱(Auto-Boxing)과 언박싱(Unboxing) 기능이 추가되었습니다.

- 오토 박싱: 기본형 → 래퍼 클래스로 자동 변환.
- 언박싱: 래퍼 클래스 → 기본형으로 자동 변환.

이 기능 덕분에 기본형과 래퍼 클래스 간의 변환이 간단해졌습니다.

```java
// 오토 박싱
int num = 10;
Integer wrappedNum = num; // 자동 변환

// 언박싱
Integer wrapped = Integer.valueOf(20);
int primitive = wrapped; // 자동 변환
```

하지만 주의할 점도 있습니다. 래퍼 클래스의 값이 null일 경우, 언박싱 시 NullPointerException이 발생할 수 있습니다. 따라서 항상 값이 null이 아닌지 확인하는 습관이 필요합니다.

### Class 클래스란 무엇인가?

Class 클래스는 자바의 핵심적인 부분으로, 런타임 시 클래스 정보를 담고 있는 객체입니다. Class 클래스는 리플렉션(Reflection)이라는 기능을 통해 프로그램이 실행 중에 클래스의 구조를 분석하거나 조작할 수 있도록 합니다.
사용 사례는 다음과 같습니다.

- 동적 객체 생성: 런타임에 클래스를 동적으로 생성합니다.
- 메서드와 필드 분석: 클래스의 필드, 메서드, 생성자 등을 확인.
- 애너테이션 처리: 컴파일 시점에 붙인 애너테이션을 런타임에 활용.

```java
Class clazz = String.class;

// 클래스 이름 출력
System.out.println("클래스 이름: " + clazz.getName());

// 모든 메서드 출력
Method[] methods = clazz.getDeclaredMethods();
for (Method method : methods) {
    System.out.println("메서드: " + method.getName());
}
```

리플렉션은 매우 강력하지만 성능이 저하될 수 있으므로 필요한 경우에만 사용해야 합니다.

### System 클래스란?

System 클래스는 자바 프로그램이 운영체제와 상호작용할 수 있도록 도와주는 유틸리티 클래스입니다. 이 클래스는 모든 메서드와 필드가 정적(static)으로 정의되어 있어 인스턴스를 생성할 필요가 없습니다. 주요 기능을 살펴보겠습니다.
주요 기능은 다음과 같습니다.

- 입출력 관리
  - System.in → 표준 입력 (키보드 입력)
  - System.out → 표준 출력 (콘솔 출력)
  - System.err → 에러 출력
- 시스템 속성 정보 제공
  - System.getProperty(String key) → 특정 속성값 가져오기
  - 예: System.getProperty("os.name") → 운영체제 이름
- 프로그램 성능 측정:
  - System.currentTimeMillis()와 System.nanoTime() → 현재 시간 또는 프로그램 실행 시간 측정

```java
// 현재 OS 정보 출력
System.out.println("운영체제: " + System.getProperty("os.name"));

// 코드 실행 시간 측정
long startTime = System.nanoTime();
for (int i = 0; i < 1000000; i++) {} // 간단한 반복문
long endTime = System.nanoTime();
System.out.println("소요 시간 (나노초): " + (endTime - startTime));
```

### Math 클래스란?

Math 클래스는 자바에서 수학적 연산을 위한 메서드를 제공하는 정적 유틸리티 클래스입니다.
이 클래스는 인스턴스를 생성할 수 없으며, 모든 메서드가 static으로 정의되어 있습니다.
주요 메서드는 다음과 같습니다.

- 최댓값/최솟값 계산
  - Math.max(a, b) / Math.min(a, b)
- 거듭제곱, 제곱근 계산
  - Math.pow(a, b) / Math.sqrt(a)
- 랜덤 값 생성
  - Math.random() → 0.0 이상 1.0 미만의 double 값을 반환
- 삼각 함수와 절댓값
  - Math.sin(x), Math.abs(x)

> 해당 메서드들은 어디서 주로 많이 사용할까 고민하다가 코테같은 것을 볼때 많이 사용할 수 있을 것 같다는 생각이 들었다.

```java
// 최댓값, 제곱근 계산
System.out.println("5와 10 중 최댓값: " + Math.max(5, 10));
System.out.println("16의 제곱근: " + Math.sqrt(16));

// 랜덤 값 생성
double randomValue = Math.random(); // 0.0 이상 1.0 미만
System.out.println("랜덤 값: " + randomValue);
```

### Random 클래스란?

Random 클래스는 보다 정교한 랜덤 값 생성을 위해 사용됩니다.

- Math.random()은 단순한 double 값을 생성하지만, Random 클래스는 다양한 유형(int, long, float 등)의 랜덤 값을 생성할 수 있습니다.
- Random 객체를 생성하여 메서드를 호출하면 됩니다.

주요 메서드는 다음과 같다.

- 정수 랜덤 값 생성
  - nextInt() → 랜덤한 int 값 생성
  - nextInt(bound) → 0부터 bound-1까지의 값 생성
- 실수 랜덤 값 생성:
  - nextDouble(), nextFloat() → 0.0 이상 1.0 미만
- Boolean 랜덤 값 생성:
  - nextBoolean() → true 또는 false

```java
import java.util.Random;

Random random = new Random();

// 1부터 100 사이의 랜덤 값 생성
int randomInt = random.nextInt(100) + 1; // 0~99 + 1
System.out.println("랜덤 정수: " + randomInt);

// 랜덤 Boolean 값 생성
boolean randomBoolean = random.nextBoolean();
System.out.println("랜덤 Boolean: " + randomBoolean);
```