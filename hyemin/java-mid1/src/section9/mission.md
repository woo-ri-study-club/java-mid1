## 미션

기말고사를 무사히 마치고 어느덧 취업의 난을 겪기 시작했다. 넣는 서류마다 다 탈락하고 자존감이 떨어지려는 그때,
아끼는 후배의 추천으로 나는 N기업에 지원을 하게 되었다. 그리고 어느덧 과제전형까지 왔고 문제는 다음과 같았다.

레스토랑 예약 관리 시스템을 구현하라. 구성은 자유롭게 하되, 아래의 개념들을 반드시 녹여내어 작성하라.
- 내부 클래스
- 정적 중첩 클래스
- 지역 클래스
- 익명 클래스
- 람다

또한 아래의 기능은 반드시 구현해야 한다.
- 테이블 관리 및 예약 기록
- 예약 정보를 추가/삭제 및 테이블 상태 업데이트.
- 특정 조건에 따라 예약 또는 테이블을 검색.

클래스 구조는 다음과 같이 한다.

Restaurant
└── Table (정적 중첩 클래스)
├── Reservation (내부 클래스)
└── TableStatusUpdater (지역 클래스)

익명 클래스와 람다는  main 메서드 안에 구현을 하여 활용한다.

