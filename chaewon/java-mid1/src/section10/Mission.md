## 예외 처리의 필요성

---

유저에게 객관식 퀴즈를 내는 게임 프로그램을 생각해봅시다. 간단하게 컬렉션에 문제와 답 번호를 넣는다고 가정하고, 컬렉션에 담긴 것들을 순차적으로 유저에게 보인 후 유저의 답변을 입력 받아온다고 합시다. 내부 설계 코드야 저마다 각각 다르겠지만 대부분의 사람들이 `scanner.nextInt()`로 답변을 받아올 것입니다.

이 때, 유저가 숫자 대신 문자 값을 입력하면 어떻게 될까요? 그렇게 되면 자바는 콘솔에 `java.util.InputMismatchException` 라는 에러 문구를 보이며 프로그램을 즉시 종료시킵니다. 하지만 일반적인 게임 진행 방식을 생각하면, 이는 비정상적입니다. 유저가 잘못된 값을 입력하면 프로그램이 꺼지는 것이 아닌 '숫자로 입력해주세요.' 같은 안내 문구가 뜨는 것이 일반적이니까요.

이런 경우 외에도 예상치 못한 에러 문구로 프로그램이 종료된 경험을 종종 겪었을 것 입니다. 저희는 이런 예외를 처리할 방법을 알아야 합니다.

이 예시 외에도 예외 처리는 여러 의미가 있습니다. 자바의 예외는 계층을 잘 구분해두었습니다. 예외 클래스명만 보아도 무슨 예외가 터졌는지 알 수 있고, 이 예외에 관련된 내용을 로그 처리 해놓으면 개발자가 후속 대응을 하기도 편합니다.

결국 예외 처리는 프로그램의 안정성과 신뢰를 위한 도구입니다.

## 예외 처리

---

### 예외 계층
예외도 결국 자바의 한 객체 중 하나입니다. 객체이기 때문에 모든 객체의 부모인 Object를 당연히 상속 받고 있습니다.
대략적은 계보는 아래와 같습니다.

Object ← Throwable ← Exception ← 여러 예외 클래스들(RuntimeException 포함)...

Object ← Throwable ← Error ← 여러 에러 클래스들...

본격적인 내용을 들어가기 전에 Exception과 Error의 차이를 간단히 짚고 넘어갑시다. Error는 메모리 부족 등의 심각한 시스템 오류 시 발생하는 시스템 예외입니다. 복구가 불가능한 문제이기 때문에 개발자는 이 예외를 처리하지 않습니다. Exception은 저희가 로직에서 처리할 최상위 예외 클래스입니다.

### 체크 예외 vs 언체크 예외
Exception의 하위 예외 클래스들은 체크 예외와 언체크(런타임) 예외로 구분이 가능합니다. 언체크 예외 이외에는 모두 컴파일러가 체크하는 체크 예외입니다. 이 체크 예외들은 개발자가 명시적으로 처리하지 않을 시 컴파일 오류가 난다는 특징이 있습니다. 이 체크 예외는 언체크 예외는 RuntimeException과 그 하위 클래스들을 말합니다. 이 예외들은 체크 예외와 달리 컴파일러가 체크 하지 않고 런타임 중에 발생한다고 해서 런타임 예외라고도 합니다.

| 체크 예외                                | 언체크 예외                  |
|--------------------------------------|-------------------------|
| RuntimeException 제외 Exception 하위 예외들 | RumtimeException 하위 예외들 |
| 개발자가 명시적 처리                          | 선택적 처리                  |

### 예외 처리의 규칙
예외 처리의 기본 매커니즘을 우선적으로 알아봅시다.
1. 예외 발생
2. 예외를 프로그램 내로 던지기
3. 던져진 예외를 프로그램 특정 계층에서 처리 후 정상 흐름으로 되돌리기 or 처리하지 못한다면 다른 계층으로 던지기(main 메서드에서도 해결을 못하고 던지면 프로그램 종료)

잡아서 처리하기와 던진다의 의미를 살피기 전에, 한 가지 주의할 점은 예외도 상속 구조를 갖고 있기 때문에 최상위 예외인 Exception을 던지기로 명시했다면 그 하위 예외 전부를 던질 수 있고 그보다 상위인 Throwable을 던지기로 명시했다면 Exception과 Error 전부를 던질 수 있습니다. 그러니 개발자는 던질거나 잡을 예외 클래스를 적절하게 지정해야 합니다.

### 체크 예외
> 컴파일러가 체크하는 Exception의 하위 예외들

체크 예외는 잡아서 처리하거나 외부로 던져야 합니다. 이를 이행하지 않을 시 컴파일 오류가 발생합니다.
대표적 체크 예외 중 하나인 IOException 내부를 살펴보고 체크 예외 처리 방법을 알아봅시다.

**IOException의 코드 일부**
```java
public class IOException extends Exception {
    @java.io.Serial
    static final long serialVersionUID = 7818375828146090155L;
    
    public IOException() {
        super();
    }

    public IOException(String message) {
        super(message);
    }
    
    public IOException(String message, Throwable cause) {
        super(message, cause);
    }
    
    //etc...
}
```

1. **체크 얘외 던지기**

**사용 예시**
```java
public class Ex {
    public static void main(String[] args) throws IOException { //발생한 IOException 던지기
        throwIOEx();
    }

    static void throwIOEx() throws IOException{
        throw new IOException(); //예외도 객체이기 때문에 new 생성 후 발생
    }

    static void throwIOExWithMessage() throws IOException{
        throw new IOException("call IOException");
    }
}
```

*실행 결과*
```
Exception in thread "main" java.io.IOException
	at ...
```

`throws` - 발생한 예외를 **던지는** 키워드
`throw` - 예외를 **발생시키는** 키워드

2. **체크 얘외 잡아서 처리하기**

**사용 예시**
```java
public class Ex {
    public static void main(String[] args) {
        try { //일단 내부 코드 블록 수행
            throwIOEx(); //로직 진행 중 예외 발생
        } catch (Exception e){ //try 코드 진행 중 Exception(혹은 그 하위 얘외)이 발생했다면, 잡아서 처리 로직 실행. 처리 로직이 끝나면 정상 흐름 로직으로 돌아감.
            System.out.println("catch " + e.getClass());
            //예외 처리 로직
        }
        
        //정상 흐름 로직
    }

    static void throwIOEx() throws IOException{
        throw new IOException();
    }

    static void throwIOExWithMessage() throws IOException{
        throw new IOException("call IOException");
    }
}

```

*실행 결과*
```
catch class java.io.IOException
```

### 체크 예외의 장단점
**장점**
- 컴파일러를 통하기 때문에 누락 가능성이 없습니다.

**단점**
- 매번 잡거나 던져야하는 번거로움이 없습니다.

### 언체크 예외
> RuntimeException과 그 하위 예외 클래스

체크 예외와 처리 방법은 동일하나, throws를 선언하지 않아도 자동으로 예외를 던집니다.

처음에 말씀드린 퀴즈 게임 예시에서의 InputMismatchException가 언체크 예외 중 하나입니다. 방식은 동일하기에 별도의 실습 코드는 스킵하겠습니다.

### 언체크 예외의 장단점
- throws 키워드를 생략할 수 있습니다.
- 개발자가 처리 누락할 가능성이 있습니다.

### 오류 코드, 오류 메세지
예외로 클래스 중 하나이기에, 내부에 멤버 변수와 메서드, 반환 값 설정이 가능합니다.

오류 코드(errCode)는 어떤 오류가 발생했는지 구분할 수 있는 코드입니다. 오류 메세지는 개발자가 이해할 수 있는 설명을 담은 메세지로 Throwable에서 제공하는 기능입니다.

### finally 키워드
try~catch 로 예외 처리를 할 시 문제가 발생할 수 있습니다. try 문 안에서 메서드1과 메서드2를 수행하도록 로직을 작성하고 catch 문 안에서 예외를 처리하도록 구성했다고 가정합시다. 프로그램 실행 시 try 문 내부의 메서드1이 작동하다 에러가 발생하면 어떻게 될까요? catch 문이 예외 처리 로직을 수행 후에 즉시, try~catch 문 외부로 빠져나가게 됩니다. 즉, 메서드2를 반드시 수행시켜야 하는 경우에도 메서드2는 무시가 되어버립니다.

이런 상황을 해결하기 위한 것이 `finally` 입니다. finally 내의 코드 블럭은 중간에 예외가 발생하든 아니든 반드시 호출됩니다. 그리고 catch할 예외로 지정하지 않은 예상치 못한 예외 발생 시에도 호출시킵니다. 이런 특징 덕분에 finally 키워드는 반드시 실행해야 하는, try 문에서 사용한 자원 해제 시 주로 사용합니다. 기본 형태는 아래와 같습니다.

```
try {
    ...
 }catch () {
    ...
 } finally {
    ...
 }
```

### try ~ finally
직접 예외를 잡아 처리할 필요가 없는 경우에는 try ~ finally 구문의 형태로 작성할 수 있습니다.
이렇게 작성 시에는 예외를 외부로 던지면서, finally 내부의 코드 블럭 실행이 보장됩니다.

### 예외 계층 만들기
예외를 코드로만 분류하는 것 보다는 직접 사용자가 계층적으로 설계할 수 있습니다. 예를 들어 아래와 같은 형태로 설계할 수 있습니다.
Exception ← NetworkException(사용자 정의) ← RequestException(사용자 정의) / ResponseException(사용자 정의)

이렇게 되면 이전에는 복수의 예외를 처리하려면 최상위 예외만을 사용해 개발자가 잡고 싶지 않았던 예외도 잡아버릴 수 있지만, 위와 같이 설계하면 NetworkException을 처리하겠다고 선언 시 ReqeustException과 ResponseException을 함께 처리할 수 있습니다. 동시에, 무슨 역할의 예외 클래스인지 인지하기도 좋습니다.

### 여러 예외 계층 처리하기
catch문과 throws 키워드는 여러 개의 클래스를 처리할 수도 있습니다. 

예외 클래스마다 별도의 처리를 하고 싶을 때의 형식은 아래와 같습니다.

```
try {
    ...
 }catch (NetworkException e) {
    ...
 } }catch (Exception e) {
    ...
 }
```

catch문은 위에서 아래로 순서대로 실행합니다. 즉, NetworkException과 그 하위 예외가 첫 번째 캐치문에서 잡히고 나머지 예외는 다음 catch문에서 잡히게 됩니다.
이런 특성때문에 자식 계층부터 catch 하도록 설계해야 합니다.

위 케이스와 다르게, 여러 예외를 한 번에 잡아 **같은 처리**를 하고 싶을 때의 형식은 아래와 같습니다.

```
try {
    ...
 }catch (RequestException | ResponseException e) {
    ...
 }
```

단 이 경우에는 공통 부모인 NetworkException의 기능 메서드만 사용할 수 있습니다.


## 실무에서의 예외

---

### 처리할 수 없는 예외
시스템 오류로 인한 예외들은 잡아서 처리할 수 있는 방안이 거의 없습니다. 그렇기에, 오류 메세지 혹은 오류 페이지를 나타낼 수 있어야 하고 개발에게는 인지를 위한 로그 기록이 필요합니다.

### 체크 예외의 문제
컴파일러가 잡아주는 체크 예외는 분명 장점이 있지만, 처리할 수 없는 예외가 많고 여러 외부 라이브러리는 사용해 프로그램이 복잡해지는 요즘 체크 예외의 사용이 점점 부담스러워졌습니다.

### 문제 해결법
이런 문제 해결을 위해 저희는 이 처리할 수 없는 시스템 오류 예외들을 공통으로 처리하는 클래스를 생성한 후, 시스템 오류 예외를 모두 해당 클래스로 던지도록 설계합니다. 해당 클래스에서 로그 남기기 등의 기능을 일괄적으로 처리하면 됩니다.

### 예외 클래스의 기능 메서드(Throwable 클래스 내에 구현)
- .printStackTrace() - 예외 메세지와 스택 트레이스를 콘솔에 출력합니다.(역추적 기능) 출력 시 붉은 글씨로 출력됩니다.
- .getMessage() - 발생한 예외의 메세지 문자열을 반환합니다.
- .getStackTrace() - 예외의 호출 스택 정보를 배열로 반환합니다.

### try-with-resources
finally로 매번 외부 자원을 해제하는 과정이 번거롭기 때문에, 자바 7부터 도입한 개념입니다.
`AutoCloseable`이라는 인터페이스를 구현하면 사용이 가능합니다. 이 인터페이스 구현 시, try문이 끝나는 시점에 `AutoCloseable` 내부의 close() 메서드가 자동 호출됩니다.

이 방식을 통해 리소스 누수 방지와 자원 해제 누락 가능성 방지가 됩니다. 또한, 코드가 단순해져 가독성 또한 올라갑니다.



