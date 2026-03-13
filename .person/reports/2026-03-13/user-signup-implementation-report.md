Reporter: KimNaKim

# 고객 회원가입 기능 구현 보고서

## 1. 작업 요약
- 사용자가 서비스를 이용하기 위해 계정을 생성할 수 있는 **회원가입 기능**을 구현했습니다.
- 이메일을 아이디(username)로 사용하는 정책을 적용하여 사용자 편의성을 높였습니다.

## 2. 변경 사항
- **DTO 설계 (`UserRequest.java`)**: `JoinDTO`에 이름, 이메일, 전화번호, 비밀번호 필드 및 `toEntity()` 메서드 추가.
- **Service 구현 (`UserService.java`)**: `join()` 메서드에서 이메일 중복 체크 및 사용자 저장 로직 구현.
- **Controller 연결 (`UserController.java`)**: 회원가입 화면(`GET /join-form`) 및 처리(`POST /join`) 매핑 추가.
- **View 수정 (`join-form.mustache`)**: 폼 전송을 위한 `action`, `method`, `name` 속성 적용.

## 3. 검증 결과
- `UserRequest.JoinDTO`가 `User` 엔티티로 올바르게 매핑됨을 확인했습니다.
- 중복 가입 시 `RuntimeException`을 통해 방어 로직이 작동함을 확인했습니다.
- 폼 데이터가 서버의 DTO로 정확히 바인딩되도록 필드명을 일치시켰습니다.

## 4. 비유로 설명하기
- 마치 **"캠핑장 멤버십 가입 창구를 개설한 것"**과 같습니다. 이제 방문객들은 자신의 정보를 기입하고 고유한 회원 번호(계정)를 발급받아 포레스트 헤이븐의 정식 회원이 될 수 있는 절차를 갖추게 되었습니다.
