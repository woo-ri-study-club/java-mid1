## 미션

드디어 아끼는 후배의 추천으로 과제를 통과하고 면접까지 도달하였다. 그리고 면접장에서 다음과 같은 질문을 던져주었다.

> 예외처리에 대해 상세히 발표해보세요. 특히 트랜젝션 관련해서 체크드 예외와 언체크드 예외의 차이를 설명해보세요.
> 
그럼 한번 예외처리에 대해 발표해보자!

## 1. 자바의 예외 처리
자바는 프로그래밍 실행 중에 발생할 수 있는 예외에 대한 처리 메커니즘을 제공한다.  
그 예외를 다루기 위한 객체들을 제공한다.
### 예외 객체의 계층 구조
최상위 예외 객체로 **Throwable**이 있고, 그 하위 예외로 **Exception**과 **Error**가 있다.
- **Error**는 메모리 부족이나 심각한 시스템 오류같은 애플리케이션 선에서 복구가 불가능한 예외를 말한다.
- **Exception**은 애플리케이션 로직에서 사용할수있는 실질적인 최상위 예외이다. 
   - Exception 하위 예외는 RuntimeException 제외하고 모두 컴파일러가 체크하는 체크예외이다.
- **RuntimeException** 과 그 하위 예외는 모두 언체크 예외이다.

### 예외의 기본 규칙
1. 예외는 처리하거나 처리하지못하면 자신을 호출한 곳으로 던져야한다.
2. 예외를 잡거나 던질때 지정한 예외 뿐만 아니라 그 예외의 자식들도 처리할 수 있다.

- 예외를 발생시키려면 `throw`를 사용하면 된다.
```java
public void call(){
    throw new MyCheckedException("ex");
}
```
## 2. 예외의 종류 
### 체크 예외
Exception과 하위 예외(RuntimeException은 빼고)는 모두 컴파일러가 체크하는 체크 예외이다.  
체크 예외는 개발자가 직접 모든 예외를 직접 명시적으로 처리해야한다.

- Exception을 상속받은 예외는 모두 체크 예외가 된다. 
```java
public class MyCheckedException extends Exception {
    public MyCheckedException(String message) {
        super(message);
    }
}
```

- 예외를 밖으로 던질때는 `throws` 를 사용하면 된다.  
채크예외는 예외를 던지려면 필수적으로 적어줘야 한다. 
```java
public void catchTrows() throws MyCheckedException{
        client.call();
    }
```

예외를 잡아서 처리할때는 `try`-`catch` 를 사용한다.  
try에 에서 발생하는 예외를 잡아서 catch로 넘긴다.  
처리하려는 예외를 catch () 에 적어주고 예외 처리 로직을 작성해주면된다.  
```java
public void callCatch() {
    try {
        client.call();
    } catch (MyCheckedException e) {
        // 예외 처리 로직
        System.out.println("예외 처리, message = " + e.getMessage());
    }
}
```

### 언체크 예외 
언체크 예외는 말그대로 컴파일러가 예외를 체크하지 않는 다는 뜻이다.

- RuntimeException 상속받은 예외는 언체크 예외가 된다.
```java
public class MyUncheckedException extends RuntimeException {
    public MyUncheckedException(String message) {
        super(message);
    }
}
```
- 언체크 예외는 체크예외와 달리 예외를 잡거나, 던지지 않아도 된다.
- 아래 코드처럼 필요한 경우 예외를 잡아서 처리할 수 있다. 
```java
    public void callCatch(){
        try {
            client.call();
        } catch (MyUncheckedException e) {
            System.out.println("예외 처리, message : " + e.getMessage());
        }
    }
```
- 체크 예외와 다르게 `throws 예외` 선언을 하지않아도 상위로 넘어간다.
```java
public void callThrow(){
        client.call();
    }
```

### 트랜잭션에서의 예외
트랜잭션은 데이터베이스에서도 쓰이는 개념으로서, 하나의 작업 단위를 뜻한다.

예를 들어 수강신청을 할때,  
```
1. 수강인원체크
2. 수강생등록
```
두가지를 모두 처리해야 성공하는 것으로 간주한다면, 이건 하나의 작업단위를 트랜잭션이라한다.
1가지라도 실패하면 모두 취소해야한다. 
```java
public void 수강신청() {
  try {
    수강인원체크();  
    수강생등록();
  } catch (Exception e) {
    모두취소(); // 하나라도 실패하면 모두 롤백
    throw new 수강신청실패("수강신청에 실패했습니다");
  }
}
```
반대로 각 작업 메서드 내에서 일일히 try-catch 예외 처리를 한다면  
하나가 예외 발생하더라도 메서드 자체적으로 예외처리를 해주었기때문에 제약없이 모두 실행되게된다.
```java

public void 수강신청(){
  수강인원체크();
  수강생등록();
}

public void 수강인원체크(){
    try{
      ...
    }catch(예외){
        취소();
    }
}

public void 수강생등록(){
  try{
      ...
  }catch(예외){
    취소();
  }
}
```
이렇게 예외처리를 어느 작업 메소드에 하느냐에 따라 작업을 취소하고 롤백을 할지, 아니면 롤백을 하지않을지   
개발자가 직접 정할 수 있는 것이다.

    데이터베이스의 트랜잭션을 다룰 때도 체크드 예외, 언체크드 예외 무관하게 롤백하는 것은 개발자가 직접 정할 수 있다.


## 3. 정상 흐름과 예외 흐름의 분리
### try-catch  
자바가 제공하는 try-catch 구조를 사용하면 정상흐름은 try블럭에 모아서 처리하고, 
예외 흐름은 catch블럭에 별도로 모아서 처리할 수 있게된다.
### try-catch-finally  
자바는 어떤 흐름이던 반드시 호출되는 finally 기능을 제공한다.
  - try가 시작하기만 하면 finally는 무조건 호출된다.
  - 애플리케이션 외부자원을 사용하는경우 반드시 무조건 외부자원을 해제해야하는데 그때 유용하게 사용할수있다.

```java
try{
// 정상 흐름
} catch {
// 예외 흐름
} finally {
// 반드시 호출해야 하는 마무리 흐름
}
```

### try-with-resources
- try에서 외부자원을 사용하고 finally에서 외부자원을 반납하는 패턴이 반복되면서 
자바에서는 Try with resources라는 편의기능을 도입했다. 
- 이 기능을 사용하려면 AutoCloseable이라는 인터페이스를 구현을 해야한다.
  - AutoCloseable 인터페이스가 제공하는 close()메서드를 사용하면된다.  
  이 메서드는 try가 끝나면 자동으로 호출된다. 자원반납하는 방법을 여기 정의하면된다.
```java
public class NetworkClientV5 implements AutoCloseable {
    
    ...
    
    @Override
    public void close() {
        System.out.println("NetworkClientV5.close");
        disconnect();
    }
}
```

## 4. 예외 처리 방안

### 예외 계층 사용
- 예외를 오류코드로 분류하는 것이 아니라, 계층화해서 다양하게 만들면 세밀하게 예외를 처리할 수 있다.
- 자바에서 예외는 객체이기때문에 부모 예외를 잡거나 던지면 그 밑의 자식 예외도 모두 잡거나 던질수있다.
- 특정 예외를 잡고싶으면 하위 예외를 잡아서 처리하면 된다.

### 처리할수 없는 예외 
서버 문제로 통신이 안되거나, 데이터베이스 서버에 문제가 생기는 것과 같은 시스템 오류때문에 발생한 예외는 잡아도 해결할 수 있는것들이 없다.  
-> 이런 경우에는 고객에게 오류메시지나 웹페이지를 보여주면 된다. 그리고 개발자가 오류를 알수있도록 오류 로그를 남겨두어야한다.  
  
- 체크 예외로 처리하기 
  - 모든 예외를 잡아서 처리하기 : 앞에 설명한것처럼 시스템 문제가 발생하면 예외를 잡아도 복구할 수 없다. 그래서 밖으로 던지는 것이 더 나은 결정이다.
  - 모든 체크 예외를 던져서 처리하기 : 라이브러리가 늘어날 수록 개발자가 직접 명시해야하는 예외도 많아진다. 상당히 번거로운일이다.
- 런타임 예외로 처리하기
  - 예외를 던져서 처리하기 : 언체크 예외이므로 throws를 선언하지 않아도 된다.
  - 일부 언체크 예외를 잡아서 처리하기 : 시스템 문제가 발생하면 어차피 처리할 수 없는 것이기때문에 밖으로 던지는 것이 나은 방법이다.
  언체크 예외는 잡지않으면 throws선언 없이도 밖으로 던져진다. 처리할수있는 언체크 예외만 잡아서 처리하면 된다.

### 공통 예외로 처리하기
해결할수 없는 예외가 발생하면 Exception을 잡아서 해결하지 못한 예외를 여기서 공통으로 처리한다.  
exceptionHandler()에 예외 객체를 전달하고 exceptionHandler()에서 사용자에게 보여줄 메시지나 개발자에게 남길 로그를 처리한다. 
```java
try{
    networkService.sendMessage(input);
} catch (Exception e) {
    exceptionHandler(e);
}
````
```java
private static void exceptionHandler(Exception e) {
    // 공통 처리
    System.out.println("사용자 메시지: 죄송합니다. 알 수 없는 문제가 발생했습니다.");
    System.out.println("===개발자용 디버깅 메시지===");
    e.printStackTrace(System.out); // 스택 트레이스 출력

    // 필요하면 예외 별로 별도의 추가 처리 가능
    if(e instanceof SendExceptionV4 sendEx){
        System.out.println("[전송 오류] 전송 데이터: " + sendEx.getSendData());
    }
}
```