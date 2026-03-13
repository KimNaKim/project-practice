<!-- Parent: ../AI-CONTEXT.md -->

# domain/site/

## 목적
캠핑 공간 및 요금 정책 관리. 구역(Zone)과 개별 사이트(Site) 정보를 관리한다.

## 주요 파일
| 파일명 | 설명 |
|--------|------|
| `Zone.java` | 구역 엔티티 (normalPrice, peakPrice 등 요금 정보 포함) |
| `Site.java` | 개별 사이트 엔티티 (siteName, maxPeople, Zone 참조) |
| `SiteRepository.java` | 구역별 사이트 조회 등 지원 |
| `SiteService.java` | 사이트 조회 및 가용성 체크 로직 (뼈대 생성됨) |
| `SiteRequest.java` / `SiteResponse.java` | 데이터 교환용 DTO |

## AI 작업 지침
- **가격 산출**: `Zone`의 가격 정보를 바탕으로 시즌별 요금 계산 로직을 구현해야 한다.
- **Fetch Join**: `Site` 조회 시 성능을 위해 `Zone`과 `fetch join` 사용을 권장한다.

## 미구현 및 향후 과제 (Phase 4 예정)
- **시즌 관리 CRUD**: 관리자 페이지(`admin/site/season.mustache`)와 연동되는 시즌 생성, 수정, 삭제 기능 및 API 구현 필요.
- **시즌별 요금 반영**: 예약 시 선택한 날짜가 특정 시즌에 해당할 경우, 해당 시즌의 요금 조정 정책(%, 정액)이 반영된 최종 가격 산출 로직 구현.
