# section 10,11 미션
드디어 아끼는 후배의 추천으로 과제를 통과하고 면접까지 도달하였다. 그리고 면접장에서 다음과 같은 질문을 던져주었다.

예외처리에 대해 상세히 발표해보세요. 특히 트랜젝션 관련해서 체크드 예외와 언체크드 예외의 차이를 설명해보세요.

그럼 한번 예외처리에 대해 발표해보자!

## 예외 처리가 필요한 이유
- 실행 코드와 예외 처리 코드를 분리하여 코드의 가독성을 높이기 위함

## 체크드 예외(Checked Exception)와 언체크드 예외(Unchecked Exception)의 차이

### 체크드 예외(Checked Exception)
- 컴파일러가 강제로 예외 처리를 요구하는 예외 처리하지 않으면 컴파일 에러가 발생함
- `try-catch` 블록이나 `throws` 키워드를 사용하여 예외 처리를 해야함
- `IOException`, `SQLException` 등이 대표적인 체크드 예외
- 체크드 예외를 던지는 메소드를 호출하는 경우, 해당 메소드를 호출하는 메소드에서도 예외를 처리해야 함
- 체크드 예외는 예외 발생 시 복구가 가능한 경우에 사용함

### 체크드 예외(Checked Exception)의 예시
```java
// 
public class CheckedExceptionExample {
    public static void main(String[] args) {
        try {
            Service service = new Service();
            service.call();
            
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}

// 체크드 예외를 상위 클래스로 던진다.
class Service {
    public void call() throws CheckedException {
        throw new CheckedException("호출 실패");
    }
}

// Exception 클래스를 상속받아 체크드 예외를 만들어줌
class CheckedException extends Exception {
    public CheckedException(String message) {
        super(message);
    }
}
```

### 언체크드 예외(Unchecked Exception)
- 컴파일러가 예외 처리를 강제하지 않음
- `RuntimeException` 클래스를 상속받은 예외들이 언체크드 예외에 해당함
- `NullPointerException`, `ArrayIndexOutOfBoundsException` 등이 대표적인 언체크드 예외
- 언체크드 예외는 예외 발생 시 복구가 불가능한 경우에 사용함
- 언체크드 예외는 주로 프로그래밍 오류를 나타내는 경우에 사용함

### 언체크드 예외(Unchecked Exception)의 예시

```java
public class UncheckedExceptionExample {
    public static void main(String[] args) {
        try {
            Service service = new Service();
            service.call();
        }catch (UnCheckedException e) {
            e.printStackTrace();
        }
    }
}

class Service {
    public void call() {
        throw new UnCheckedException("호출 실패");
    }
}

class UnCheckedException extends RuntimeException {
    public UnCheckedException(String message) {
        super(message);
    }
}
```

### finally 블록
- `try` , `catch` 안에서 잡을 수 없는 예외가 발생해도 `finally` 는 반드시 호출된다.

```java
public class FinallyExample {
    public static void main(String[] args) {
        try {
            Service service = new Service();
            service.call();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("finally 블록 실행");
        }
    }
}
```

### try-with-resources
- `try` 블록에서 사용한 리소스를 자동으로 종료시켜주는 기능
- `AutoCloseable` 인터페이스를 구현한 클래스만 사용 가능

```java

public class TryWithResourcesExample {
    public static void main(String[] args) {
        try (Service service = new Service()) {
            service.call();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Service implements AutoCloseable {
    @Override
    public void close() throws Exception {
        System.out.println("리소스 해제");
    }

    public void call() {
        System.out.println("서비스 호출");
    }
}
```

## 트랜잭션
- 기본적으로 spring transaction은 `unchecked exception`만 롤백하지만, 필요에 의해 `checked exception`도 롤백하도록 설정할 수 있다.