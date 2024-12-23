## 미션

드디어 아끼는 후배의 추천으로 과제를 통과하고 면접까지 도달하였다. 그리고 면접장에서 다음과 같은 질문을 던져주었다.

> 예외처리에 대해 상세히 발표해보세요. 특히 트랜젝션 관련해서 체크드 예외와 언체크드 예외의 차이를 설명해보세요.

그럼 한번 예외처리에 대해 발표해보자!

---------

### 예외 처리의 필요성
예외 처리는 정상 흐름을 유지하고 코드의 안정성을 높이는 역할을 합니다.
프로그램 내에서 정상적인 작업 흐름과 예외 발생 시 흐름이 섞이는 경우, 코드를 이해하기 어려워지는 문제가 발생합니다. 
예외 처리를 도입하면 이러한 문제를 해결할 수 있습니다.
또한 예외 처리를 통해 코드의 가독성을 높이고, 유지보수가 용이해집니다.

### 예외 클래스 설계
- 사용자 정의 예외: 자바에서는 예외도 객체로 취급하므로, 필요에 따라 사용자 정의 예외 클래스를 만들어 사용할 수 있습니다.
```java
public class NetworkClientException extends Exception {
    private String errorCode;

    public NetworkClientException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
```

### 체크 예외 vs 언체크 예외
#### 체크 예외 (Checked Exception):
- 개발자가 예외를 강제로 처리해야 합니다. 예를 들어, 파일을 열거나 네트워크 연결을 시도할 때 발생할 수 있는 IOException 등이 이에 해당합니다.
- 메서드 선언 시 throws 키워드로 예외를 명시해야 합니다.
- 컴파일러가 예외 처리를 강제하기 때문에, 예외를 처리하지 않으면 컴파일 오류가 발생합니다.
- RuntimeException을 제외한, Exception을 상속받은 자식들이 체크 예외에 해당합니다.
- 예외를 잡아서 호출한 곳으로 던지거나, 직접 처리해야 합니다.

```java
import java.io.IOException;

public void readFile(String filename) throws IOException { // throws 선언 필수
    // 파일을 읽는 코드
    ...
    throw new IOException();
}
```

#### 언체크 예외 (Unchecked Exception):
- 주로 프로그래머의 실수로 인해 발생하는 예외입니다. 예를 들어, 배열의 인덱스가 범위를 초과하는 경우 발생하는 ArrayIndexOutOfBoundsException 등이 있습니다.
- throws 키워드를 사용하지 않아도 되며, 컴파일러가 체크하지 않습니다.
- 프로그램 실행 중 발생할 수 있어, 예외를 처리할 수 있는 방법이 없을 때 주로 사용됩니다.
- RuntimeException을 상속받은 자식들이 언체크 예외에 해당합니다.

```java
public void mayThrowUnchecked() { // throws 선언 안해도 됨.
    throw new IllegalArgumentException("잘못된 인자");
}
```

### 트랜잭션과 예외 처리
트랜잭션은 데이터베이스 작업의 논리적인 단위입니다. 데이터베이스 작업이 모두 성공적으로 완료되거나, 실패하면 모든 작업을 취소(rollback)해야 하는데, 이 과정에서 예외 처리가 중요합니다.
체크 예외와 언체크 예외 모두 자동 롤백 대상은 아니며, 상황에 맞게 프로그래머가 명시적으로 롤백 처리를 해야 합니다.

### 체크 예외와 트랜잭션:
데이터베이스 접근에서 체크드 예외는 데이터 무결성을 유지하며, 어떤 예외가 발생했는지를 명시적으로 확인하여 오류에 빠진 작업을 롤백할 수 있도록 도와줍니다.
예를 들어, 다음과 같이 데이터베이스 연결 시 발생할 수 있는 체크 예외를 처리합니다.
```java
public void performTransaction() throws SQLException {
    try {
        connection.setAutoCommit(false); // 트랜잭션 시작
        // 데이터베이스 작업 코드
        connection.commit(); // 작업 성공 시 커밋
    } catch (SQLException e) {
        connection.rollback(); // 오류 발생 시 롤백
        throw e;
    }
}
```

### 언체크 예외와 트랜잭션:
언체크 예외는 개발자의 실수나 예기치 못한 상황에서 발생할 수 있으므로, 미리 방지하기 어렵습니다. 그러나 이를 처리하여 트랜잭션이 깨지지 않도록 유도하는 것이 중요합니다.
예를 들어, 실행 중 예기치 않은 오류가 발생했을 때, 트랜잭션을 유지하는 방법입니다.
```java
public void execute() {
    try {
        performTransaction();
    } catch (RuntimeException e) {
        // 예외를 로깅하고 적절한 조치를 취함
    }
}
```
체크 예외와 언체크 예외는 프로그래머가 예외를 어떻게 처리하느냐에 따라 프로그램의 안정성과 가독성을 크게 좌우합니다. 트랜잭션 처리에서 예외 관리를 통해 데이터 무결성을 유지할 수 있습니다. 
체크 예외를 통해 발생 가능한 예외를 미리 방지하고, 언체크 예외는 적절한 로깅 및 사용자 피드백을 통해 회복 가능성을 높이는 전략이 필요합니다.

### 자바의 예외 처리 구조
#### try-catch-finally
- try: 정상 흐름이 실행되는 블록입니다.
- catch: 예외가 발생할 경우 처리하는 블록입니다.
- finally: 예외 발생 여부와 관계없이 반드시 실행되는 블록으로, 주로 자원 반납에 사용됩니다.

```java
try {
    // 정상 흐름
} catch (Exception e) {
    // 예외 처리
} finally {
    // 자원 반환 등 마무리 작업
}
```
#### try with resources 구문
- try 블럭이 끝나면 자동으로 AutoCloseable.close() 호출하여 자원을 해제합니다. (try 블럭만 있어도 호출됩니다.)
- 리소스 누수를 방지하고 간결성과 가독성이 높아집니다.
- 자원 해제가 조금 더 빨라집니다. (try 블럭 종료후 즉시 해제)
```java
try (NetworkClientV5 client = new NetworkClientV5(address)) { // 사용할 자원 명시
     client.initError(data);
     client.connect();
     client.send(data);
} catch (Exception e) {
    System.out.println("[예외 확인]: " + e.getMessage()); 
    throw e;
}
```

### 예외 처리 전략
- 구체적인 예외 처리: 발생 가능한 구체적인 예외를 먼저 처리하고, 더 일반적인 예외를 나중에 처리해야 합니다. 이를 통해 각 예외에 맞춘 적절한 대응을 할 수 있습니다.
- 실무에서 처리할 수 없는 예외가 발생하면 고객에게는 시스템 오류에 대한 메시지를 보여주고, 개발자가 문제 인식을 빠르게 하도록 오류 로그를 남겨두어야 합니다.
- 점차 처리할 수 없는 예외가 많아지면서 체크 예외 사용에 대한 부담이 커졌습니다. 따라서 이런 경우 공통으로 예외를 처리하는 부분을 만들어 해결하는 것이 좋습니다.