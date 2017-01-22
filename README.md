# OAuth2.0   Grant Type

## 1. authorization_code
 - 장기 접근이 요구될 때
 - OAuth 클라이언트가 웹 어플리케이션 서버일 때
 - API 호출에 대한 책임이 매우 중요하고 사용자가 접근하는 웹 브라우저에 OAuth 토큰이 노출되지 않아야 할때

## 2. implicit
 - 데이터에 접근이 일시적으로 필요할때
 - 사용자가 규칙적으로 API 제공 업체에 로그인할 때
 - OAuth 클라이언트가 자바스크립트, 플래시 등을 사용하여 웹 브라우저에서 실행될 때
 - 웹 브라우저의 신뢰도가 높고, 신뢰할 수 없는 사용자나 어플리케이션에 노출될 염려가 적을 때
 
## 3. password
 - 비밀번호가 어플리케이션에 노출되기 때문에, 필요한 경우에만 적절히 사용해야 한다. 
 - 보통 API 제공 업체가 배포한 공식 어플리케이션에만 추천하고 제 3의 개발자 커뮤니티의 어플리케이션에는 추천하지 않는다. 

## 4. client_credentials
 - Google Storage나 Amazon S3 같은 저장 API 중 하나를 사용하여 데이터 파일이나 이미지 등의 외부 자원을 저장하는 어플리케이션을 만들 때
 - 자원 소유자가 OAuth 플로우를 사용하지 않고 외부에서 자원을 접근할 수 있도록 허용하는 할 때


# JPA Reference
https://backend.gitbooks.io/jpa/content/chapter6.html
