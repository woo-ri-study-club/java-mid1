## 예외 처리

### 1. 예외처리가 필요한 이유

#### 시작
- 예외 처리는 프로그램 실행 중 예외적인 상황을 처리해 프로그램이 중단되지 않도록 합니다.
- 잘못된 사용자 입력, 파일 미존재, 네트워크 문제 등 다양한 상황에서 필요합니다.

#### 오류상황 만들기
- 의도적으로 오류를 발생시켜 예외처리의 중요성을 실습해볼 수 있습니다.

```java
int result = 10 / 0; // ArithmeticException 발생
```

#### 반환값으로 예외처리
- 과거에는 오류를 특별한 반환값(e.g., -1)으로 처리했으나, 이는 버그를 야기할 가능성이 큽니다.
- 자바는 예외 클래스를 통해 오류를 명확히 구분합니다.

---

### 2. 자바 예외 처리

#### 예외 계층
- `Throwable`은 예외 계층의 최상위 클래스입니다.
  - `Error`: 시스템 레벨에서 발생하며 복구 불가능(e.g., OutOfMemoryError).
  - `Exception`: 애플리케이션 레벨의 예외로 복구 가능성이 있음.

#### 예외 기본 규칙
- 모든 예외는 `try-catch-finally` 또는 `throws`를 통해 처리해야 합니다.
- 예외가 적절히 처리되지 않으면 프로그램이 비정상 종료됩니다.

#### 체크 예외 (Checked Exception)
- 컴파일 타임에 반드시 처리해야 하는 예외(e.g., `IOException`, `SQLException`).
- 반드시 `try-catch`로 감싸거나 메서드 선언에 `throws`를 추가해야 합니다.

```java
public void readFile() throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader("test.txt"));
}
```

#### 언체크 예외 (Unchecked Exception)
- 런타임 시 발생하며 강제적으로 처리하지 않아도 되는 예외(e.g., `NullPointerException`, `ArrayIndexOutOfBoundsException`).
- 프로그램 설계 단계에서 예방해야 합니다.

#### 트랜잭션에 대한 예외처리

많은 블로그들이 예외처리를 할 때 아래의 표를 많이 봤을 것입니다.

![레퍼런스](./assets/reference01.png)

위의 표를 보면서 이상한 생각이 든다. 예외 발생시 트랜잭션 처리에 관해서 체크드 예외는 roll back하지 않고 언체크드 예외는 roll back한다고 되어있다.
과연 이게 맞을까? 정말 잘못된 표현이다! 그 이유를 하나하나 살펴보자.

트랜잭션은 이런 예외는 저렇게 한다, 저런 예외는 그렇게 한다라는 것이 전혀 없다. 또한 이 트랜잭션이 DB의 트랜잭션인지 메세지 큐 트랜잭션인지 명시를 정확히 해야한다.
만약 이게 DB 트랜잭션이였다면 예외가 발생할때 roll back을 할지 말지 여부는 우리가 정하는 것이다. 위의 표같은 규칙이 없다!
그러면 왜 다수의 사람들이 이렇게 적은 것일까? 그 이유는 바로 스프링의 트랜잭션 처리에서 나온 것이다. 스프링에서 트랜잭션 처리는 기본적으로는 런타임 계열은 롤백을 한다. 체크드 예외는 롤백을 바로 하지 않는다.
그런데 이것은 기본적인 동작일 뿐 우리가 option을 통해 설정을 얼마든지 할  수 있다.

결국 다시 생각해보면 과연 자바의 예외를 트랜잭션 발생 시, 저렇게 표현하는게 맞을까? 이게 맞을 수 있다. 만약 자바를 스프링과 동일시 한다면 말이다.

---

### 3. 예외처리 도입

#### 예외 복구
- 예외 발생 후 적절한 복구 로직을 추가해 프로그램 흐름을 유지.

```java
try {
    int[] array = new int[2];
    array[5] = 10;
} catch (ArrayIndexOutOfBoundsException e) {
    System.out.println("배열의 범위를 벗어났습니다. 기본값으로 설정합니다.");
}
```

#### 정상, 예외 흐름
- 정상 흐름과 예외 흐름을 분리하여 코드 가독성을 높입니다.
- 정상 흐름에서의 조건문으로 예외를 방지하기보다는 예외 처리 구조 사용을 권장합니다.

#### 리소스 반환 문제
- 파일이나 데이터베이스 연결과 같은 리소스는 반드시 반환되어야 합니다.
- `finally` 블록 또는 `try-with-resources`를 사용합니다.

#### finally
- 예외 발생 여부와 관계없이 실행되는 블록으로, 리소스 정리 등에 사용됩니다.

```java
try {
    FileInputStream fis = new FileInputStream("test.txt");
} catch (FileNotFoundException e) {
    e.printStackTrace();
} finally {
    System.out.println("리소스 정리 완료");
}
```

---

### 4. 예외 계층 활용

#### 시작
- 특정 예외 계층을 활용하여 일관된 방식으로 예외를 처리합니다.
- 예외 메시지와 로그 기록을 통해 디버깅에 도움을 줍니다.

#### 활용
- 커스텀 예외 클래스를 정의하여 비즈니스 로직에 맞는 예외를 처리할 수 있습니다.

```java
public class CustomException extends Exception {
    public CustomException(String message) {
        super(message);
    }
}
```

---

### 5. 실무 예외 처리 방안

#### 예외 메시지 관리
- 명확한 예외 메시지를 제공하여 디버깅을 용이하게 만듭니다.
- 실무에서는 로그 시스템(e.g., Logback, Log4j)을 통해 기록합니다.

#### 최소한의 예외 처리
- 불필요한 `try-catch`는 피하고 예외를 상위 계층으로 위임합니다.
- `RuntimeException`을 적절히 활용합니다.

---

### 6. try-with-resources
- 자바 7에서 도입된 기능으로, 리소스 반환을 자동으로 처리합니다.
- `AutoCloseable` 인터페이스를 구현한 클래스(e.g., `BufferedReader`)에 사용 가능.

```java
try (BufferedReader br = new BufferedReader(new FileReader("test.txt"))) {
    System.out.println(br.readLine());
} catch (IOException e) {
    e.printStackTrace();
}
```

### 7. throws와 try~catch 처리의 차이

#### throws를 사용하는 경우
- 메서드 선언부에 `throws` 키워드를 추가하여 호출하는 쪽에서 예외를 처리하도록 위임합니다.
- 주로 체크 예외(Checked Exception)에 사용되며, 예외를 처리할 책임을 상위 메서드로 넘깁니다.

```java
public void readFile() throws IOException {
    FileReader reader = new FileReader("test.txt");
}
```

- 장점
  - 코드가 간결해지고 예외 처리 책임을 명확히 분리할 수 있습니다.
- 단점
  - 호출 계층이 깊어질수록 예외 처리가 복잡해질 수 있습니다.

#### try-catch를 사용하는 경우
- 예외를 발생한 위치에서 바로 처리합니다.
- 예외가 발생할 가능성이 있는 코드를 try 블록에 작성하고, 예외 처리 로직을 catch 블록에서 구현합니다.

```java
try {
    FileReader reader = new FileReader("test.txt");
} catch (IOException e) {
    e.printStackTrace();
}
```

- 장점
  - 예외 발생 시 즉각적인 대응이 가능하며, 코드의 흐름을 명확히 유지할 수 있습니다.
- 단점
  - 예외 처리 코드가 많아질 경우 가독성이 떨어질 수 있습니다.

#### 주요 차이점
- throws는 예외를 처리하지 않고 상위 메서드에 전달하며, try-catch는 발생한 예외를 바로 처리합니다.
- 언제 사용?
  - throws: 예외를 상위 로직에서 처리하거나 예외 복구가 필요 없는 경우.
  - try-catch: 예외를 발생한 위치에서 바로 복구하거나 특정 로직을 실행해야 할 경우.

---

### 결론

자바의 예외 처리는 프로그램의 안정성을 높이고 유지보수를 용이하게 합니다. 예외 계층, try-catch-finally, try-with-resources를 효과적으로 활용하고 실무에 맞는 예외 처리 전략을 설계하는 것이 중요합니다.