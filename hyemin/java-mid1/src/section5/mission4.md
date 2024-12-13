# 래퍼, Class 클래스

안녕하세요. 래퍼 클래스, Class 클래스에 대한 발표를 맡은 김혜민입니다. 발표 순서는 다음과 같습니다.
1. 래퍼 클래스
- 래퍼 클래스란?
- 래퍼 클래스의 특징
- 기본형 vs 래퍼 클래스
2. Class 클래스
- Class 클래스란?
- Class 클래스의 특징

## 래퍼 클래스
### 래퍼 클래스란?
래퍼 클래스(Wrapper Class)는 기본 데이터 타입(primitive type)을 객체로 감싸는 클래스입니다. 자바에서는 기본형 데이터 타입에 대한 래퍼 클래스를 제공합니다.
이를 통해 객체 지향의 장점을 활용하고 메서드를 가질 수 있으며, 컬렉션 프레임워크나 제네릭을 사용할 수 있습니다. 또한 래퍼 클래스는 데이터가 없다는 것을 null 을 통해 명확히 표현할 수 있습니다.

[기본형 -> 래퍼 클래스]
- int -> Integer
- char -> Character
- boolean -> Boolean
- byte -> Byte
- short -> Short
- long -> Long
- float -> Float
- double -> Double

### 래퍼 클래스의 특징
1) **객체화** : 래퍼 클래스를 사용하면 기본 데이터 타입을 객체로 사용할 수 있습니다. 이는 컬렉션 프레임워크(ArrayList, HashMap 등)와 같은 객체 기반 데이터 구조에 데이터를 저장할 수 있게 해줍니다.

2) **오토 박싱(Autoboxing)과 오토 언박싱(Unboxing)** : 자바 5부터 제공된 기능으로, 기본 데이터 타입과 래퍼 클래스 간에 자동으로 변환이 이루어집니다.
```java
    Integer obj = 10; // 오토 박싱
    int num = obj; // 오토 언박싱
```

3) **불변성** : 래퍼 클래스의 인스턴스는 불변 객체로, 생성된 객체의 값을 변경할 수 없습니다.

4) **주요 메소드** : 래퍼 클래스는 기본 데이터 타입과 관련된 다양한 유틸리티 메소드를 제공합니다.
```java
valueOf() // 숫자, 문자열을 모두 파라미터로 받아 래퍼 타입 객체를 반환.
parseInt() //문자열을 기본형으로 변환한다.
compareTo() // 해당 데이터 타입의 크기를 비교한다. 내 값에서 비교 값을 뺄셈하여 1, -1, 0을 반환함.
Integer.sum() , Integer.min() , Integer.max() // static 메서드로 합, 최소값, 최대값을 반환한다.
```

### 기본형 vs 래퍼 클래스
래퍼 클래스가 앞서 말한 장점들이 있지만, 메모리 효율이나 성능은 기본형이 더 좋습니다. 
반복 횟수가 많지 않은 경우 래퍼형을 사용해도 비슷하지만, 연산이 수만 이상인 경우 기본형을 사용하는 것을 추천합니다.
일반적으로는 **유지보수하기 편한 방향**으로 구현하는 것이 좋습니다.

## Class 클래스
### Class 클래스란?
모든 클래스의 런타임 정보를 제공하는 메타데이터 클래스입니다. 
Class 클래스를 통해 클래스의 이름, 선언된 메서드나 필드, 생성자나 인터페이스 정보를 얻을 수 있습니다.

### Class 클래스의 특징
1) **클래스 정보 제공** : Class 클래스는 클래스의 이름, 슈퍼클래스, 인터페이스, 접근 제한자 등 다양한 정보를 조회할 수 있는 메소드를 제공합니다.
   (`getDeclaredFields()`, `getDeclaredMethods()`, `getSuperclass()`, `getInterfaces()` 등)
2) **리플렉션(Reflection)** : Class 클래스를 사용하면 런타임 시에 클래스의 구조를 조사하고 수정할 수 있습니다. 이를 통해 객체의 필드나 메서드에 접근하거나 호출할 수 있고 인스턴스를 생성할 수 있습니다.

3) **동적 로딩과 생성** : `Class.forName()` 메서드로 클래스를 동적으로 로드할 수 있고, `newInstance()` 메소드를 사용하면 클래스의 인스턴스를 생성할 수 있습니다.

4) **애노테이션 처리** : 클래스에 적용된 `@Controller` 등의 애노테이션을 조회하고 처리할 수 있습니다.

```java
    // Class 객체 얻기
    Class clazz = Class.forName("java.lang.String"); // 문자열로 조회
    //Class clazz = String.class; // 클래스에서 조회
    //Class clazz = new String().getClass();// 인스턴스에서 조회

    // 클래스 이름 출력
    System.out.println(clazz.getName());

    // 메서드 출력
    Method[] methods = clazz.getDeclaredMethods();
    for (Method method : methods) {
        System.out.println(method.getName());
    }
```

