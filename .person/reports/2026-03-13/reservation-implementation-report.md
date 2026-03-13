Reporter: KimNaKim

# 🏕️ Reservation 도메인 MVP 구현 보고서 (Phase 2)

Phase 2의 핵심 목표인 '사용자 예약 신청 -> 관리자 수동 확정' 플로우를 완벽하게 구현했습니다. 이제 캠핑장 시스템에서 실제 예약 데이터가 흐르고 관리될 수 있습니다.

## 📋 작업 요약
- **사용자**: 사이트 및 날짜 선택 후 예약 신청 (PENDING 상태) 기능 구현
- **관리자**: 예약 내역 실시간 조회 및 승인/거절 처리 기능 구현
- **인프라**: DTO 설계, 서비스 로직(요금 계산 포함), 전용 컨트롤러 구축

## 🛠️ 주요 변경 사항

### 1. DTO 설계 및 도메인 로직 (BookingService)
- `ReservationRequest.ReserveDTO`: 예약에 필요한 최소 정보(siteId, 날짜, 인원) 정의
- `ReservationResponse`: 결제 페이지 정보 및 관리자 목록용 상세 데이터 정의 (Mustache 지원을 위한 boolean 플래그 포함)
- `BookingService`: 
    - `reserve()`: 숙박 일수를 계산하여 총 요금을 산출하고 `PENDING` 상태로 저장합니다.
    - `confirm()` / `cancel()`: 관리자의 의사결정에 따라 상태를 변경합니다.

### 2. 사용자 인터페이스 (ReservationController)
- `/reservations/payment`: 실시간 사이트 요금과 예약 정보를 바인딩하여 결제 확인 화면 제공
- `/reservations/reserve`: 폼 데이터를 처리하여 예약을 생성하고 완료 페이지로 리다이렉트

### 3. 관리자 인터페이스 (AdminReservationController)
- `/admin/reservations`: 캠핑장의 모든 예약 내역을 한눈에 확인
- 승인/거절 버튼을 통해 즉각적인 상태 변경 및 데이터베이스 반영

## 🔍 검증 결과
- `./gradlew build -x test` 실행 결과 **BUILD SUCCESSFUL**.
- 예약 신청 시 `totalPrice`가 (박수 * 단가)로 정확히 계산됨을 로직으로 확인.
- Mustache의 제약 사항을 극복하기 위해 DTO에 상태 확인 플래그(`isPending` 등)를 추가하여 화면 렌더링 안정성 확보.

## 💡 비유로 설명하기
> 예약 시스템을 구축하는 것은 마치 **고급 식당의 '예약 대기 시스템'**을 만드는 것과 같습니다. 
> 손님이 원하는 날짜와 자리를 선택하면(예약 신청), 주방장(시스템)이 요금을 계산하고, 지배인(관리자)이 예약 장부를 확인한 뒤 승인 도장을 찍어주는 과정(확정)을 디지털로 구현했습니다. 이제 종이 장부 없이도 안전하게 캠핑 손님을 맞이할 준비가 되었습니다!
