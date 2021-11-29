# 모바일프로그래밍 4팀 프로젝트 'COO-IT'
#### 냉장고 속 재료로 만들 수 있는 요리 레시피 추천 앱
## Introduction
1. 자신의 냉장고에 있는 재료로 만들 수 있는 레시피를 추천해준다.
2. 상세레시피를 통해 자세한 요리 방법과 사진들을 볼 수 있다. 
3. 카테고리별로 나누어 음식을 추천해준다.
4. 레시피를 스크랩할 수 있다.
## Implementation
+ 회원가입 형식은 이메일 형식에 맞아야하고, 비밀번호는 6자리 이상이어야한다. 
+ 로그인 후, 냉장고를 클릭하여 재료를 선택한다.
+ 재료를 두 개 이상 선택하면, 요리하러가기 버튼이 활성화되어 레시피화면으로 넘어간다.  
## Development Environment
+ Android studio 3.1.25
+ Kotlin
+ firebase
+ firestore
## Application Version
+ Android 11.0(R)
+ Android 10.0(Q)
## API 
OpenAPI 를 사용함. 
<https://openapi.foodsafetykorea.go.kr/api/cf8505a99bb545f8882c/COOKRCP01/xml/1/10> 
## Database 
+ firebase 에서 cloud firestore 사용
## Instruction
1. 로딩 화면
2. 로그인 화면
3. 회원가입 화면
4. 냉장고 화면
 - 냉장고 터치 -> 재료 선택 화면
 - 요리하러 가기 버튼 터치 -> 레시피 추천 화면 (선택된 재료가 없는 상태에서 요리하러 가기 버튼을 터치하면 재료 선택 화면으로 이동함)
5. 상세 레시피 설명 화면
6. 카테고리별 레시피 화면
7. 스크랩 화면
