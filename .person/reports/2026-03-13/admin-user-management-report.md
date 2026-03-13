Reporter: KimNaKim

# 관리자 회원 관리 기능 구현 보고서

## 1. 작업 요약
- 관리자가 전체 회원 목록을 조회하고, 각 회원의 권한(ADMIN/USER)을 토글할 수 있는 기능을 구현했습니다.
- 보안을 위해 `AdminInterceptor`와 연동되는 `/admin/users` 경로를 사용했습니다.

## 2. 변경 사항
- **엔티티 수정 (`User.java`)**: 권한을 USER <-> ADMIN으로 전환하는 `toggleRole()` 비즈니스 메서드 추가.
- **DTO 설계 (`UserResponse.java`)**: 회원 목록 표시를 위한 `ListDTO` 구현.
- **Service 구현 (`UserService.java`)**: 전체 회원 조회(`findAll()`) 및 권한 토글(`updateRole()`) 로직 구현.
- **Controller 구현 (`UserController.java`)**: 관리자용 목록 조회(`GET /admin/users`) 및 토글 처리(`POST /admin/users/{id}/update-role`) 매핑 추가.
- **View 완성 (`admin/user/list.mustache`)**: 동적 데이터 렌더링 및 권한 변경을 위한 POST 폼 추가.

## 3. 검증 결과
- 관리자 페이지 접근 시 전체 회원 데이터가 테이블에 올바르게 출력됨을 확인했습니다.
- 권한 변경 버튼 클릭 시 데이터베이스의 `role` 값이 즉시 반영되고 목록 페이지로 리다이렉트됨을 확인했습니다.
- 엔티티 내부 메서드를 사용함으로써 비즈니스 로직의 응집도를 높였습니다.

## 4. 비유로 설명하기
- 마치 **"캠핑장 관리자 대장에 회원 명부를 비치하고, 각 회원의 출입 등급(권한)을 도장 하나로 간단히 변경할 수 있게 한 것"**과 같습니다. 이제 관리자는 한눈에 모든 회원을 파악하고 필요에 따라 즉시 권한을 부여하거나 회수할 수 있습니다.
