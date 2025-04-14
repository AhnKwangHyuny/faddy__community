패션 애호가들을 위한 소셜 네트워킹 서비스로, 사용자들이 자신의 패션 스타일을 마음껏 공유하고 소통할 수 있는 플랫폼입니다.

## 주요 기능

- 👗 **스타일 게시물** - 이미지와 설명으로 자신의 패션 스타일 공유
- 👥 **팔로우/팔로잉** - 다른 사용자 팔로우 및 활동 피드 확인 
- 💬 **실시간 채팅** - 사용자 간 직접 메시지 교환
- 🏷️ **해시태그** - 태그 기반 스타일 분류 및 검색
- 👍 **좋아요 & 댓글** - 게시물 상호작용
- 🔍 **검색** - 사용자 및 스타일 검색

## 기술 스택

### 백엔드
- **언어 및 프레임워크**: Java 17, Spring Boot 3
- **인증**: Spring Security, JWT
- **데이터베이스**: MySQL, Redis(캐싱, 실시간 기능)
- **ORM**: Spring Data JPA, QueryDSL
- **API 문서화**: Springdoc OpenAPI
- **실시간 기능**: WebSocket, STOMP
- **클라우드 스토리지**: AWS S3(이미지 저장)

### 프론트엔드
- **언어 및 프레임워크**: React, TypeScript
- **상태 관리**: React Hooks
- **UI 라이브러리**: Material UI, Styled Components
- **HTTP 클라이언트**: Axios
- **폼 관리**: React Hook Form

### 인프라
- **버전 관리**: Git
- **클라우드 서비스**: AWS EC2
- **이미지 스토리지**: AWS S3

## 아키텍처

### Faddy 아키텍처 다이어그램
<img width="829" alt="image" src="https://github.com/user-attachments/assets/7b64c43a-872f-4951-bb1b-68336c08f4fd" />

### Faddy 사용자 인터렉션 다이어그램
<img width="771" alt="image" src="https://github.com/user-attachments/assets/09b01997-28b0-423a-929e-e747eda8e775" />


### 아키텍처 개요
- **프론트엔드**: React SPA(Single Page Application)을 통한 사용자 인터페이스 제공
- **백엔드**: RESTful API 서버와 WebSocket 서버로 구성
- **데이터 계층**: MySQL을 주 데이터베이스로, Redis를 캐싱 및 실시간 기능 지원용으로 사용
- **스토리지**: AWS S3를 통한 이미지 파일 저장 및 관리
- **인증 흐름**: JWT 토큰 기반 인증, Redis를 활용한 리프레시 토큰 관리

## 프로젝트 구조

```
faddy-app/
├── backend/                # 백엔드 프로젝트
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/faddy/
│   │   │   │   ├── auth/          # 인증/인가 관련 코드
│   │   │   │   ├── user/          # 사용자 관리
│   │   │   │   ├── styleBoard/    # 스타일 게시물 관리
│   │   │   │   ├── comment/       # 댓글 기능
│   │   │   │   ├── hashTags/      # 해시태그 기능
│   │   │   │   ├── follows/       # 팔로우 관리
│   │   │   │   ├── chat/          # 실시간 채팅
│   │   │   │   ├── like/          # 좋아요 기능
│   │   │   │   ├── image/         # 이미지 업로드/관리
│   │   │   │   └── ...
│   │   │   └── resources/         # 설정 파일
│   │   └── test/                  # 테스트 코드
│   └── build.gradle              # 백엔드 의존성 관리
│
└── frontend/               # 프론트엔드 프로젝트
    ├── public/
    ├── src/
    │   ├── components/     # 재사용 가능한 UI 컴포넌트
    │   ├── pages/          # 페이지 컴포넌트
    │   ├── hooks/          # 커스텀 훅
    │   ├── api/            # API 연동 로직
    │   ├── context/        # 전역 상태 관리
    │   ├── utils/          # 유틸리티 함수
    │   └── ...
    └── package.json       # 프론트엔드 의존성 관리
```

## 로컬 개발 환경 설정

### 사전 요구사항
- JDK 17+
- Node.js 16+
- MySQL 8+
- Redis 6+
- AWS S3 계정 및 버킷

### 백엔드 실행
```bash
# MySQL과 Redis 실행 (Docker 사용 시)
docker-compose up -d mysql redis

# 프로젝트 디렉토리로 이동
cd backend

# 애플리케이션 실행
./gradlew bootRun
```

### 프론트엔드 실행
```bash
# 프로젝트 디렉토리로 이동
cd frontend

# 의존성 설치
npm install

# 개발 서버 실행
npm start
```

## 환경 변수 설정

백엔드 `.env` 파일 생성 (backend/ 루트 디렉토리)
```properties
# 데이터베이스 설정
spring.datasource.url=jdbc:mysql://localhost:3306/faddy_db?serverTimezone=UTC
spring.datasource.username=username
spring.datasource.password=password

# JWT 설정
jwt.secret=your_jwt_secret_key
jwt.expiration=86400000

# AWS S3 설정
aws.s3.access-key=your_access_key
aws.s3.secret-key=your_secret_key
aws.s3.bucket=your_bucket_name
aws.s3.region=ap-northeast-2
```

## API 문서

Faddy 백엔드는 Springdoc OpenAPI를 사용하여 API 문서화가 자동화되어 있습니다. 백엔드 서버 실행 후 다음 URL에서 API 문서를 확인할 수 있습니다:

```
http://localhost:8080/swagger-ui.html
```

### 주요 API 엔드포인트

#### 인증 API
- `POST /api/auth/register` - 사용자 회원가입
- `POST /api/auth/login` - 로그인 및 토큰 발급
- `POST /api/auth/refresh` - 리프레시 토큰으로 액세스 토큰 갱신
- `GET /api/auth/me` - 현재 인증된 사용자 정보 조회

#### 사용자 API
- `GET /api/users/{userId}` - 사용자 프로필 조회
- `PUT /api/users/{userId}` - 사용자 프로필 업데이트
- `GET /api/users/{userId}/followers` - 사용자의 팔로워 목록 조회
- `GET /api/users/{userId}/following` - 사용자의 팔로잉 목록 조회

#### 스타일 게시물 API
- `POST /api/styles` - 스타일 게시물 생성
- `GET /api/styles` - 스타일 게시물 목록 조회
- `GET /api/styles/{styleId}` - 스타일 게시물 상세 조회
- `PUT /api/styles/{styleId}` - 스타일 게시물 수정
- `DELETE /api/styles/{styleId}` - 스타일 게시물 삭제

#### 댓글 API
- `POST /api/styles/{styleId}/comments` - 댓글 작성
- `GET /api/styles/{styleId}/comments` - 댓글 목록 조회
- `PUT /api/comments/{commentId}` - 댓글 수정
- `DELETE /api/comments/{commentId}` - 댓글 삭제

#### 좋아요 API
- `POST /api/styles/{styleId}/like` - 게시물 좋아요 토글
- `GET /api/styles/{styleId}/likes` - 게시물 좋아요 목록 조회

#### 해시태그 API
- `GET /api/hashtags` - 인기 해시태그 목록 조회
- `GET /api/hashtags/{tag}` - 특정 해시태그가 있는 게시물 조회

#### 채팅 API
- `GET /api/chats` - 사용자의 채팅방 목록 조회
- `GET /api/chats/{chatId}` - 특정 채팅방의 메시지 내역 조회
- WebSocket 엔드포인트: `/ws/chat`

### 요청/응답 예시

#### 회원가입 요청
```json
POST /api/auth/register
Content-Type: application/json

{
  "email": "user@example.com",
  "password": "securePassword123",
  "name": "Fashion User",
  "nickname": "fashion_lover"
}
```

#### 회원가입 응답
```json
Status: 201 Created
Content-Type: application/json

{
  "id": 1,
  "email": "user@example.com",
  "name": "Fashion User",
  "nickname": "fashion_lover",
  "createdAt": "2025-04-14T12:00:00Z"
}
```

## 주요 기능 및 구현 방식

### 인증 및 보안
- JWT 기반 인증 시스템으로 사용자 인증 처리
- Spring Security를 통한 인가(권한) 관리
- 비밀번호 암호화 저장

### 이미지 처리
- AWS S3를 활용한 이미지 저장
- 이미지 리사이징 및 최적화 처리

### 실시간 기능
- WebSocket과 STOMP 프로토콜을 이용한 실시간 채팅
- Redis Pub/Sub을 활용한 메시지 브로커 구현

### 검색 최적화
- QueryDSL을 활용한 동적 쿼리 처리
- 해시태그 및 키워드 기반 검색 기능

## 연락처

안광현 - agh0314@gmail.com

프로젝트 링크: [https://github.com/AhnKwangHyuny/faddy-app](https://github.com/AhnKwangHyuny/faddy-app)
