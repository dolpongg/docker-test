#베이스 이미지
FROM openjdk:8-jdk-alpine

# 변수 선언
ARG JAR_FILE=back-test.jar

#실행할 jar파일을 도커 컨테이너 내부에 myboot.jar라는 이름으로 복사
COPY ${JAR_FILE} myboot.jar

# 컨테이너가 시작될 때  실행할 스크립트 혹은 명령
ENTRYPOINT ["java", "-jar", "/myboot.jar"]