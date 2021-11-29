# 모바일프로그래밍 4팀 프로젝트 'COO-IT'
#### 냉장고 속 재료로 만들 수 있는 요리 레시피 추천 앱
## Introduction
1. 자신의 냉장고에 있는 재료로 만들 수 있는 레시피를 추천해준다.
2. 상세레시피를 통해 자세한 요리 방법과 사진들을 볼 수 있다. 
3. 카테고리별로 나누어 음식을 추천해준다.
4. 레시피를 스크랩할 수 있다.
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
<img src="https://user-images.githubusercontent.com/66251759/143869910-3314aace-499d-45f2-ab6b-a0a7fa873071.png" width="250"/>

2. 로그인 화면
<img src="https://user-images.githubusercontent.com/66251759/143869936-28131e4d-f5e7-4f3f-94a2-65c6f4e344a2.png" width="250"/>
<img src="https://user-images.githubusercontent.com/66251759/143870014-bc26cf01-cab4-4418-b90c-04b5fe039272.png" width="250"/>

3. 회원가입 화면
<img src="https://user-images.githubusercontent.com/66251759/143870032-523376c7-d955-482b-8b77-586792724363.png" width="250"/>
* 회원가입 형식: 이메일 형식에 맞아야 하고, 비밀번호는 6자리 이상이어야 한다.


4. 냉장고 화면
<img src="https://user-images.githubusercontent.com/66251759/143870056-c9193dd9-81c4-4c43-ba31-c9d9a47785c6.png" width="250"/>
 -냉장고 터치 -> 재료 선택 화면 (재료는 2개 이상 선택해야 레시피가 추천된다.)
<img src="https://user-images.githubusercontent.com/66251759/143870137-b4d8f446-2a33-40c8-bb75-bff9dce68858.png" width="250"/>
 선택된 재료는 남색으로 표시된다.

 -요리하러 가기 버튼 터치 -> 레시피 추천 화면 (선택된 재료가 없는 상태에서 요리하러 가기 버튼을 터치하면 재료 선택 화면으로 이동함)
<img src="https://user-images.githubusercontent.com/66251759/143870203-040a70cc-6b5e-4d08-b2e2-0c38cfda2996.png" width="250"/>
<img src="https://user-images.githubusercontent.com/66251759/143870229-5725105f-c694-432a-a08b-893210a4c11c.png" width="250"/>

5. 상세 레시피 설명 화면
<img src="https://user-images.githubusercontent.com/66251759/143872389-0877ec62-cd16-4865-87ee-d03b6ec5be69.png" width="250"/>
요리 사진 아래의 스크랩버튼을 누르면 레시피가 스크랩 화면에 저장된다.

6. 카테고리별 레시피 화면
<img src="https://user-images.githubusercontent.com/66251759/143870296-4c1d4dcc-3669-4a40-9723-86f5e5e5e44c.png" width="250"/>

7. 스크랩 화면
<img src="https://user-images.githubusercontent.com/66251759/143870366-bc7491f2-d7ef-4faa-9495-009014aee8ab.png" width="250"/>
