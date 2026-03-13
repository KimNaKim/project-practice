<!-- Parent: ../AI-CONTEXT.md -->

# domain/site/

## 목적
캠핑 공간 및 요금 정책 관리. 구역(Zone)과 개별 사이트(Site) 정보를 관리한다.

## 주요 파일
| 파일명 | 설명 |
|--------|------|
| `Zone.java` | 구역 엔티티 (normalPrice, peakPrice 등 요금 정보 포함) |
| `Site.java` | 개별 사이트 엔티티 (siteName, maxPeople, Zone 참조) |
| `ZoneRepository.java` | 구역 CRUD 지원 |
| `SiteRepository.java` | 구역별 사이트 조회 등 지원 |
| `SiteService.java` | 사이트 조회, CRUD 및 가용성 체크 로직 |
| `AdminSiteController.java` | 관리자용 구역 및 사이트 관리 (CRUD) |
| `SiteController.java` | 고객용 사이트 목록 및 상세 조회 |
| `SiteRequest.java` / `SiteResponse.java` | 데이터 교환용 DTO |

## AI 작업 지침
- **가격 산출**: `Zone`의 가격 정보를 바탕으로 시즌별 요금 계산 로직을 구현해야 한다.
- **Fetch Join**: `Site` 조회 시 성능을 위해 `Zone`과 `fetch join` 사용을 권장한다.
- **가용성 체크**: `findAvailableSites`는 `ReservationRepository`의 `findOccupiedSiteIds`를 활용한다.

## 완료된 과제
- **관리자 CRUD**: 구역 및 사이트 생성/삭제 기능 구현 완료.
- **가용성 필터링**: 날짜 및 인원 기반의 실시간 예약 가능 사이트 필터링 구현 완료.
