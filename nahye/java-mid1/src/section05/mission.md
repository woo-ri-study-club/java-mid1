## 미션

그 후배에게 잘 알려줘서인지 학교에서 나름 코딩과 설명을 잘한다고 인식이 박히게 되었다. 그러던 중에 팀플을 진행하게 되었고
내가 발표 파트를 맡게 되었다. ㅠㅠ 주제는 래퍼, Class 클래스에 대해 설명해보자는 것이였다. 실제 발표에 앞서서 미리 연습이 필요할 것으로 보였다. 연습겸 실제 발표처럼 해당 주제에 대해 설명해보자.

```
오늘은 자바의 주요 클래스들에 대해 알아보겠습니다.
먼저 기본형을 객체로 다루기 위한 래퍼 클래스를 살펴보고,
이어서 클래스의 정보를 다루는 Class 클래스,
그리고 자주 사용되는 유틸리티 클래스인 System, Math, Random 클래스에 대해 알아보겠습니다.

자바를 사용하다 보면 기본형과 참조형이라는 두 가지 자료형을 마주치게 됩니다. 
자바는 객체 지향 언어이기 때문에 객체를 통해 다양한 기능, 특히 메서드를 활용할 수 있는데요. 
그런데 여기서 한 가지 문제가 있습니다. 기본형은 객체가 아니라서 메서드를 사용할 수 없다는 점입니다.
또한 나중에 배우게 될 컬렉션 프레임워크나 제네릭에서도 기본형은 사용이 불가능합니다. 
게다가 기본형은 항상 값을 가져야 해서 null도 표현할 수 없죠. 이러한 제약을 해결하기 위해 등장한 것이 바로 래퍼 클래스입니다!

자바는 각각의 기본형에 대응되는 래퍼 클래스를 제공합니다. 

byte -> Byte
short -> Short
int -> Integer
long -> Long
float -> Float
double -> Double
char -> Character
boolean -> Boolean

자바가 제공하는 모든 래퍼 클래스는 불변 타입입니다. 비교할때는 equals()를 사용해야합니다.
래퍼 클래스의 주요 메서드를 보면 

// 문자열을 정수로 변환
int num1 = Integer.parseInt("100");
// 정수를 문자열로 변환
String str1 = Integer.toString(100);

같은 경우가 있습니다. 

그리고 재미있는 용어가 나오는데요. 기본형을 래퍼 클래스로 변환하는 것을 '박싱'이라고 합니다. 
마치 박스에 물건을 넣는 것과 비슷하다고 해서 붙은 이름이죠. 반대로 래퍼 클래스를 기본형으로 변환하는 것은 '언박싱'이라고 합니다.

// 박싱의 예
int value = 7;
Integer boxedValue = Integer.valueOf(value);

// 언박싱의 예
int unboxedValue = boxedValue.intValue();

그런데 이렇게 매번 변환 메서드를 직접 써주는 게 번거롭잖아요? 
다행히도 자바는 이를 자동으로 처리해주는 '오토박싱'과 '오토언박싱' 기능을 제공합니다.

int value = 7;
Integer boxedValue = value; // 오토박싱
int unboxedValue = boxedValue; // 오토언박싱

여기서 궁금증이 하나 생깁니다. 이렇게 편리한 래퍼 클래스가 있는데 왜 아직도 기본형을 제공할까요? 
그 이유는 바로 성능 때문입니다! 래퍼 클래스는 다양한 기능을 제공하지만, 연산 속도는 기본형이 더 빠르거든요. 
다만, 최근에는 컴퓨터 성능이 많이 좋아져서 유지보수가 용이한 코드를 선택하는 것이 더 중요해졌다고 합니다.

이어서 Class 클래스도 소개하겠습니다.
Class 클래스는 클래스의 정보를 다루는데 사용합니다.

String str = "Hello";
Class<?> cls = str.getClass(); // 클래스 객체를 얻음
Method[] methods = cls.getDeclaredMethods(); // 클래스의 모든 메서드 조회
Field[] fields = cls.getDeclaredFields(); // 클래스의 모든 필드 조회

몇가지 메소드를 소개하면, getDeclaredFields()는 클래스의 모든 필드를 조회하고, getDeclareMethods()는 클래스의 모든 메서드를 조회합니다.
또한 Class를 사용하면 메타정보를 기반으로 클래스의 정의된 메소드, 필드, 생성자 등을 조회하고, 이들을 통해 객체 인스턴스를 생성하거나 메소드를 호출하는 작업을 할수 있습니다. 
이것을 리플렉션이라고 합니다. 

System 클래스는 시스템과 관련된 기본 기능들을 제공하는 클래스입니다.
표준 입력, 출력, 환경 변수, 시간 측정, 시스템 속성, 배열 고속 복사 기능 등을 제공합니다.

System.out.println("출력"); // 출력

// 환경 변수를 읽는다.
System.out.println("getenv = " + System.getenv());

// 현재 시간 측정 (밀리초)
long time = System.currentTimeMillis();

Math 클래스는 수많은 수학문제를 해결해주는 클래스 입니다. 필요한 것을 찾아서 사용하면 됩니다.

double pi = Math.PI;   // 원주율 

// 수학 연산
double sqrt = Math.sqrt(16);    // 제곱근: 4.0
int abs = Math.abs(-5);        // 절대값: 5
long max = Math.max(10, 20);   // 최대값: 20
double power = Math.pow(2, 3); // 거듭제곱: 8.0
double rand = Math.random();    // 0.0 이상 1.0 미만의 난수

다양한 난수가 필요할때 Math.random()을 사용해도 되지만 
Random클래스를 사용하면 더욱 다양한 랜덤값을 구할 수 있습니다.

Random random = new Random();   // Random 객체 생성
int num = random.nextInt(100);    // 0-99 사이의 정수
long longNum = random.nextLong(); // long 타입의 난수
boolean flag = random.nextBoolean(); // true 또는 false

Random은 내부에서 씨드(Seed)값을 사용해서 랜덤 값을 구합니다. 
그런데 이 씨드 값이 같으면 항상 같은 결과가 출력됩니다.

Random random = new Random(1); // 시드 값으로 1 사용.

씨드 값을 사용하면 여러번 반복 실행해도 결과같이 같이 때문에 값이 달라지는 랜덤값은 구할수없지만,
결과가 고정되기 때문에 테스트 코드 같은 곳에서 결과를 검증할 수 있습니다.

지금까지 래퍼 클래스를 이용해 기본형 타입을 객체로 다루는 방법,
Class 클래스, System, Math, Random 클래스를에 대해서 간략히 알아보았습니다.
```