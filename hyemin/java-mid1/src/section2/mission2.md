## 미션
어느날 김영한님의 중급1편의 섹션2를 듣고 잠시 잠이 들었다. 그리고 눈을 떠보니 A대학교 학생으로 된 상태이다.
그리고 A대학교의 아끼는 후배가 마침 Object 클래스에 대해 쉽게 알려달라고 한다.
후배는 비전공자이고 아무것도 모르는 상태이다. 최대한 쉽게 설명해보자.

### Object 클래스란?
Object 클래스는 모든 자바 객체의 부모 클래스야. 모든 클래스는 Object 클래스를 상속받아 공통적인 기능을 제공받지. 
예를 들어 볼게. 우리 인간들은 먼 조상으로부터 걷기, 먹기, 숨쉬기 기능을 공통적으로 물려 받았잖아?
Object 클래스도 이처럼 모든 객체들에게 필요한 공통적인 기능을 제공해주는 거야. 
객체의 정보를 제공하고(toString), 다른 객체와 비교할 수 있게 하고(equals), 고유한 값(hashCode)을 가지도록 하는 등의 공통적인 기능을 말이야.

### 객체의 정보를 제공하는 toString 함수
toString() 메서드는 객체의 정보를 문자열 형태로 제공해줘. 
내부 구현은 아래 코드처럼 되어 있지만, 하위 클래스에서 오버라이딩으로 적절하게 커스텀해서 보여주고 싶은 정보만 보여줄 수 있어.
```java
    public String toString() {
        return getClass().getName() + "@" + Integer.toHexString(hashCode());
    }
```

### 다른 객체와 동등성 비교하는 equals 함수
두 객체가 논리적으로 동일한지 비교할 수 있도록 하는 equals 함수도 설명해줄게. 
`==`으로 비교하면 두 객체의 주소가 동일한지 확인하는 거고, equals 함수를 오버라이딩해서 비교하면 주소(참조)가 다르더라도 논리적으로 비교할 수 있어.
예를 들어, 주민등록번호가 일치한다거나 도서의 isbn이 동일하면 같은 사람인 것이고, 같은 책인 걸로 생각하고 싶다면 equals를 오버라이딩해서 사용하면 돼!
```java
    public boolean equals(Object obj) {
        return (this == obj);
    }
```

### Object 클래스가 없었다면?
이러한 장점이 있는 Object 클래스를 자바에서 기본적으로 제공해주지 않았다면 개발자들은 모든 객체의 공통 기능이 필요하면 각자 만들어야 했겠지? 그럼 프로젝트끼리, 개발자끼리도 서로 다르게 개발해서 혼란이 생겼을거야.

### Object 클래스 사용의 장점
Object 클래스는 모든 클래스의 부모이기 때문에 모든 자식 타입으로 초기화할 수 있어! 그래서 다형성(여러가지 자식 타입을 담을 수 있는 성질)의 장점을 활용하면서 OCP 원칙(기존 코드를 변경하지 않고도 새로운 기능을 추가할 수 있어야 한다)을 지킬 수 있는 거지. 어떤 클래스인지에 관계 없이 공통 기능을 추가적으로 제공하고 싶을 때 Object 클래스를 통해 기능 개발하면 편리하다구!

### 코드 예시
간단한 코드로도 살펴보자.

```java
public class Fruit {
    private String name;

    public Fruit(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "과일 이름: " + name;
    }
}

public class Main {
    public static void main(String[] args) {
        Fruit apple = new Fruit("사과");
        System.out.println(apple.toString()); // "과일 이름: 사과"
        System.out.println(apple); // "과일 이름: 사과" -> 객체만 출력해도 자동으로 toString 호출이 된다!
    }
}
```
Object 클래스의 toString 함수를 오버라이딩해서 과일 이름을 원하는 형식으로 출력했어! 이런 기능은 모든 객체들에게 필요한 기능일 것 같지? 
결국 Object 클래스를 통해 다형적 참조와 오버라이딩이 가능하고 코드를 더 유연하게 작성할 수 있는 장점이 있는 거야!