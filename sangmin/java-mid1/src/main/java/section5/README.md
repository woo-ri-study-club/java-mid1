# section 5 미션

## 요구사항
그 후배에게 잘 알려줘서인지 학교에서 나름 코딩과 설명을 잘한다고 인식이 박히게 되었다. 그러던 중에 팀플을 진행하게 되었고
내가 발표 파트를 맡게 되었다. ㅠㅠ 주제는 래퍼, Class 클래스에 대해 설명해보자는 것이였다. 실제 발표에 앞서서 미리 연습이 필요할 것으로 보였다. 연습겸 실제 발표처럼 해당 주제에 대해 설명해보자.

## 기본 타입 vs 래퍼형 타입
- 기본 자료타입을 객체로 다루기 위해서 사용하는 클래스들을 래퍼 클래스라고 한다.
- 래퍼 클래스의 종류

| 기본 자료형 | 래퍼 클래스 |
|---|---|
| byte | Byte |
| short | Short |
| int | Integer |
| long | Long |
| float | Float |
| double | Double |
| char | Character |
| boolean | Boolean |

- 기본 타입을 래퍼클래스로 변환하는 행위를 `박싱`이라고 하고 반대로 래퍼클래스를 기본 타입으로 변환하는 행위를 `언박싱`이라고 한다.
```java
Integer i = new Integer(10); // 박싱
int j = i.intValue(); // 언박싱
```

- 래퍼클래스는 다른 객체들과 같이 `==`이 아니라 `equals()` 메소드를 사용하여 값을 비교하여야 한다.
- 래퍼클래스는 null 값을 가질 수 있다.
- 래퍼클래스는 불변 객체이다.
- 래퍼클래스의 기본 메서드
  - `valueOf()` : 래퍼 타입을 반환한다. 숫자, 문자열을 모두 지원한다.
  - `parseXxx()` : 문자열을 기본형으로 변환한다.
  - `compareTo()` : 내 값과 인수로 넘어온 값을 비교한다. 내 값이 크면 `1` , 같으면 `0` , 내 값이 작으면 `-1` 을 반환한다.
  - `Integer.sum()` , `Integer.min()` , `Integer.max()` : `static` 메서드이다. 간단한 덧셈, 작은 값, 큰 값 연산을 수행한다.

## Class 클래스
- `Class` 클래스는 클래스의 정보를 담고 있는 클래스이다.
- `Class` 클래스의 주요기능
  - 타입 정보 얻기: 클래스의 이름, 슈퍼클래스, 인터페이스, 접근 제한자 등과 같은 정보를 조회할 수 있다. 
  - 리플렉션: 클래스에 정의된 메서드, 필드, 생성자 등을 조회하고, 이들을 통해 객체 인스턴스를 생성하거나 메서드 를 호출하는 등의 작업을 할 수 있다.
  - 동적 로딩과 생성: `Class.forName()` 메서드를 사용하여 클래스를 동적으로 로드하고, `newInstance()` 메서드를 통해 새로운 인스턴스를 생성할 수 있다.
  - 애노테이션 처리: 클래스에 적용된 애노테이션(annotation)을 조회하고 처리하는 기능을 제공한다.

```java
public class ClassMetaMain {
    public static void main(String[] args) throws Exception {
        //Class 조회
        Class clazz = String.class; // 1.클래스에서 조회
        //Class clazz = new String().getClass();// 2.인스턴스에서 조회
        //Class clazz = Class.forName("java.lang.String"); // 3.문자열로 조회
            
        // 모든 필드 출력
        // Field: class [B value
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            System.out.println("Field: " + field.getType() + " " +
            field.getName());
        }
        
        // 모든 메서드 출력
        // Method: public boolean java.lang.String.equals(java.lang.Object)
        // Method: public int java.lang.String.length()
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println("Method: " + method);
        }
        
        // 상위 클래스 정보 출력
        // Superclass: java.lang.Object
        System.out.println("Superclass: " + clazz.getSuperclass().getName());
    
        // 인터페이스 정보 출력
        // Interface: java.io.Serializable
        // Interface: java.lang.Comparable
        Class[] interfaces = clazz.getInterfaces();
        for (Class i : interfaces) {
        System.out.println("Interface: " + i.getName());
        }
    }
}
```