
# 

## Model
www.msaez.io/#/53361445/storming/walkingstudio

## Before Running Services
### Make sure there is a Kafka server running
```
cd kafka
docker-compose up
```
- Check the Kafka messages:
```
cd infra
docker-compose exec -it kafka /bin/bash
cd /bin
./kafka-console-consumer --bootstrap-server localhost:9092 --topic
```

## Run the backend micro-services
See the README.md files inside the each microservices directory:

- walkinghistory
- weather
- user
- userwalkingstat
- teamwalkingstat
- companywalkingstat
- challenge
- calculatepoint


## Run API Gateway (Spring Gateway)
```
cd gateway
mvn spring-boot:run
```

## Test by API
- walkinghistory
```
 http :8088/walkingHsts pUserId="p_user_id" teamId="team_id" comId="com_id" baseDate="base_date" baseTime="base_time" walking="walking" 
```
- weather
```
 http :8088/usrtFcstHsts baseDate="base_date" baseTime="base_time" nx="nx" ny="ny" category="category" fcstDate="fcst_date" fcstTime="fcst_time" fcstValue="fcst_value" 
 http :8088/weatherStnInfos id="id" divisionLevel1="division_level1" divisionLevel2="division_level2" nx="nx" ny="ny" 
```
- user
```
 http :8088/users pUserId="p_user_id" pUserName="p_user_name" comId="com_id" teamId="team_id" otp="otp" point="point" 
```
- userwalkingstat
```
 http :8088/walkingStatByUsers pUserId="p_user_id" baseDate="base_date" walking="walking" ranking="ranking" 
```
- teamwalkingstat
```
 http :8088/walkingStatByTeams teamId="team_id" baseDate="base_date" walking="walking" ranking="ranking" 
```
- companywalkingstat
```
 http :8088/walkingStatByCompanies comId="com_id" baseDate="base_date" walking="walking" ranking="ranking" 
```
- challenge
```
 http :8088/challengeInfos challengeId="challenge_id" missionStDt="mission_st_dt" missionFnDt="mission_fn_dt" range="range" point="point" 
 http :8088/challengeHsts challengeId="challenge_id" pUserId="p_user_id" rewardDt="reward_dt" rewardYn="reward_yn" 
```
- calculatepoint
```
 http :8088/pointStandardInfos baseDate="base_date" baseTime="base_time" nx="nx" ny="ny" weight="weight" 
```


## Run the frontend
```
cd frontend
npm i
npm run serve
```

## Test by UI
Open a browser to localhost:8088

## Required Utilities

- httpie (alternative for curl / POSTMAN) and network utils
```
sudo apt-get update
sudo apt-get install net-tools
sudo apt install iputils-ping
pip install httpie
```

- kubernetes utilities (kubectl)
```
curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl"
sudo install -o root -g root -m 0755 kubectl /usr/local/bin/kubectl
```

- aws cli (aws)
```
curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"
unzip awscliv2.zip
sudo ./aws/install
```

- eksctl 
```
curl --silent --location "https://github.com/weaveworks/eksctl/releases/latest/download/eksctl_$(uname -s)_amd64.tar.gz" | tar xz -C /tmp
sudo mv /tmp/eksctl /usr/local/bin
```
=======
# 워킹 스튜디오

## 워킹 스튜디오(Walking Studio)
서비스에 대한 설명을 적는 란

## 서비스 시나리오

### 기능적 요구사항
1. 수집
- 모바일 앱에서 걸음 데이터를 주기적으로 수집합니다.
- 공공 데이터 포탈로부터, 1시간 단위의 날씨 정보를 주기적으로 수집합니다.

2. 업데이트
- 날씨 정보가 업데이트 됨에 따라 날씨에 따른 부스트 점수를 계산합니다.
- 모바일 앱에서 수집한 걸음 데이터와 날씨에 따른 부스트 점수를 계산하여, 사용자에게 포인트를 부여합니다.
- 사용자가 챌린지를 달성하였을 때, 보상에 대한 포인트를 부여합니다.

3. 조회
- 사용자가 개인/팀/기업별 걸음 통계 정보를 조회합니다.
- 사용자가 개인/팀/기업별 챌린지 정보를 조회합니다.
- 사용자가 현재 날씨 정보를 조회합니다.

### 비기능적 요구사항
1. 트랜잭션
- 챌린지 보상에 대한 포인트를 요청 받았을 때, 개인/팀/기업의 walking stat 서버와 Req/Res(Query for Aggregate)로 현재 실제 걸음 수를 확인됐을 때만 보상을 제공합니다.

2. 장애격리
- 개인/팀/기업별 걸음 통계 정보 중 특정 정보가 조회되지 않더라도 나머지 정보에 대해서는 조회가 가능해야 합니다.
- 수집서버와 ETL성 통계 적재 서버는 분리되어야 한다.
- 수집서버에 대해서는 접속량에 비례하여 자원이 유동적으로 할당되어야 한다.

3. 성능
- 총 유저 수를 10,000명으로 가정하고, 시스템은 최소 초당 10,000개의 요청을 처리할 수 있어야 한다.
- 클라이언트 동시접속자를 1,000명으로 가정하고, 조회서버가 지연없이 요청을 처리할 수 있어야 한다.

## 체크포인트

#### 분석 설계

- 이벤트스토밍:
    - 스티커 색상별 객체의 의미를 제대로 이해하여 헥사고날 아키텍처와의 연계 설계에 적절히 반영하고 있는가?
    - 각 도메인 이벤트가 의미있는 수준으로 정의되었는가?
    - 어그리게잇: Command와 Event 들을 ACID 트랜잭션 단위의 Aggregate 로 제대로 묶었는가?
    - 기능적 요구사항과 비기능적 요구사항을 누락 없이 반영하였는가?
- 서브 도메인, 바운디드 컨텍스트 분리
    - 팀별 KPI 와 관심사, 상이한 배포주기 등에 따른  Sub-domain 이나 Bounded Context 를 적절히 분리하였고 그 분리 기준의 합리성이 충분히 설명되는가?
        - 적어도 3개 이상 서비스 분리
    - 폴리글랏 설계: 각 마이크로 서비스들의 구현 목표와 기능 특성에 따른 각자의 기술 Stack 과 저장소 구조를 다양하게 채택하여 설계하였는가?
    - 서비스 시나리오 중 ACID 트랜잭션이 크리티컬한 Use 케이스에 대하여 무리하게 서비스가 과다하게 조밀히 분리되지 않았는가?
- 컨텍스트 매핑 / 이벤트 드리븐 아키텍처
    - 업무 중요성과  도메인간 서열을 구분할 수 있는가? (Core, Supporting, General Domain)
    - Request-Response 방식과 이벤트 드리븐 방식을 구분하여 설계할 수 있는가?
    - 장애격리: 서포팅 서비스를 제거 하여도 기존 서비스에 영향이 없도록 설계하였는가?
    - 신규 서비스를 추가 하였을때 기존 서비스의 데이터베이스에 영향이 없도록 설계(열려있는 아키택처)할 수 있는가?
    - 이벤트와 폴리시를 연결하기 위한 Correlation-key 연결을 제대로 설계하였는가?

구현

[DDD] 분석단계에서의 스티커별 색상과 헥사고날 아키텍처에 따라 구현체가 매핑되게 개발되었는가?

Entity Pattern 과 Repository Pattern 을 적용하여 JPA 를 통하여 데이터 접근 어댑터를 개발하였는가
[헥사고날 아키텍처] REST Inbound adaptor 이외에 gRPC 등의 Inbound Adaptor 를 추가함에 있어서 도메인 모델의 손상을 주지 않고 새로운 프로토콜에 기존 구현체를 적응시킬 수 있는가?
분석단계에서의 유비쿼터스 랭귀지 (업무현장에서 쓰는 용어) 를 사용하여 소스코드가 서술되었는가?
Request-Response 방식의 서비스 중심 아키텍처 구현

마이크로 서비스간 Request-Response 호출에 있어 대상 서비스를 어떠한 방식으로 찾아서 호출 하였는가? (Service Discovery, REST, FeignClient)
서킷브레이커를 통하여  장애를 격리시킬 수 있는가?
이벤트 드리븐 아키텍처의 구현

카프카를 이용하여 PubSub 으로 하나 이상의 서비스가 연동되었는가?
Correlation-key: 각 이벤트 건 (메시지)가 어떠한 폴리시를 처리할때 어떤 건에 연결된 처리건인지를 구별하기 위한 Correlation-key 연결을 제대로 구현 하였는가?
Message Consumer 마이크로서비스가 장애상황에서 수신받지 못했던 기존 이벤트들을 다시 수신받아 처리하는가?
Scaling-out: Message Consumer 마이크로서비스의 Replica 를 추가했을때 중복없이 이벤트를 수신할 수 있는가
CQRS: Materialized View 를 구현하여, 타 마이크로서비스의 데이터 원본에 접근없이(Composite 서비스나 조인SQL 등 없이) 도 내 서비스의 화면 구성과 잦은 조회가 가능한가?
폴리글랏 플로그래밍

각 마이크로 서비스들이 하나이상의 각자의 기술 Stack 으로 구성되었는가?
각 마이크로 서비스들이 각자의 저장소 구조를 자율적으로 채택하고 각자의 저장소 유형 (RDB, NoSQL, File System 등)을 선택하여 구현하였는가?
API 게이트웨이

API GW를 통하여 마이크로 서비스들의 집입점을 통일할 수 있는가?
게이트웨이와 인증서버(OAuth), JWT 토큰 인증을 통하여 마이크로서비스들을 보호할 수 있는가?
운영

SLA 준수

셀프힐링: Liveness Probe 를 통하여 어떠한 서비스의 health 상태가 지속적으로 저하됨에 따라 어떠한 임계치에서 pod 가 재생되는 것을 증명할 수 있는가?
서킷브레이커, 레이트리밋 등을 통한 장애격리와 성능효율을 높힐 수 있는가?
오토스케일러 (HPA) 를 설정하여 확장적 운영이 가능한가?
모니터링, 앨럿팅:
무정지 운영 CI/CD (10)

Readiness Probe 의 설정과 Rolling update을 통하여 신규 버전이 완전히 서비스를 받을 수 있는 상태일때 신규버전의 서비스로 전환됨을 siege 등으로 증명
Contract Test : 자동화된 경계 테스트를 통하여 구현 오류나 API 계약위반를 미리 차단 가능한가?

## 분석/설게

## 구현

## 운영

