# 섹션2 미션
## 요구사항
어느날 김영한님의 중급1편의 섹션2를 듣고 잠시 잠이 들었다. 그리고 눈을 떠보니 A대학교 학생으로 된 상태이다.

그리고 A대학교의 아끼는 후배가 마침 Object 클래스에 대해 쉽게 알려달라고 한다.

후배는 비전공자이고 아무것도 모르는 상태이다. 최대한 쉽게 설명해보자.

## 답변
Object 클래스는 자바에서 제공하는 클래스로서, 모든 클래스의 최상위 클래스이다. 즉, 모든 클래스는 Object 클래스를 상속받는다.

```java
// 실제로는 이와 같이 모든 클래스는 Object 클래스를 상속받는다. 하지만 생략이 가능하다.
public class Parent extends Object {
    public void print() {
        System.out.println("Parent");
    }
}
```

Object 클래스는 모든 클래스가 공통으로 사용하는 메소드들을 제공한다. (공통 기능 제공)

Object에서 공통 메소드를 제공하기 때문에 이를 상속받는 모든 클래스는 일관성을 가지게 된다.

Object 가 제공하는 기능은 다음과 같다.
- 객체의 정보를 제공하는 toString()
- 객체의 같음을 비교하는 equals()
- 객체의 클래스 정보를 제공하는 getClass()
- 객체를 식별하는 해시 코드를 제공하는 hashCode()

### toString()
- toString() 메서드는 객체의 정보를 문자열 형태로 제공한다. 그래서 디버깅과 로깅에 유용하게 사용된다
- Object 클래스에서 제공하는 toString() 메서드는 기본적으로 패키지를 포함한 객체의 이름과 객체의 참조값(해시코드)를 16진수로 변환하여 제공한다.
```java
public String toString() {
    return getClass().getName() + "@" + Integer.toHexString(hashCode());
}
```
- 보통 다른 객체에서 toString() 메서드를 사용할 때는 필요한 정보를 제공하도록 오버라이딩하여 사용한다.

### equals()
- Object 가 기본으로 제공하는 equals() 는 == 으로 동일성 비교를 제공한다.
- equals() 메서드를 오버라이딩하여 객체의 동등성을 비교하도록 사용한다.
- equals() 메서드를 구현할 때 지켜야 하는 규칙
  - 반사성(Reflexivity): 객체는 자기 자신과 동등해야 한다. ( x.equals(x) 는 항상 true ). 
  - 대칭성(Symmetry): 두 객체가 서로에 대해 동일하다고 판단하면, 이는 양방향으로 동일해야 한다.( x.equals(y) 가 true 이면 y.equals(x) 도 true ). 
  - 추이성(Transitivity): 만약 한 객체가 두 번째 객체와 동일하고, 두 번째 객체가 세 번째 객체와 동일하다면, 첫 번째 객체는 세 번째 객체와도 동일해야 한다. 
  - 일관성(Consistency): 두 객체의 상태가 변경되지 않는 한, equals() 메소드는 항상 동일한 값을 반환해야 한다. 
  - null에 대한 비교: 모든 객체는 null 과 비교했을 때 false 를 반환해야 한다.

### 동일성과 동등성의 차이점
- 동일성(Identity): 두 객체의 레퍼런스가 같은지를 비교한다. ( == )
- 동등성(Equality): 두 객체의 값이 같은지를 비교한다. ( equals() )
- 동일성과 동등성의 예
```java
String str1 = new String("abc");
String str2 = new String("abc");

System.out.println(str1 == str2); // false (레퍼런스가 다름)
System.out.println(str1.equals(str2)); // true (값이 같음)
```