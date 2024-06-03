FROM bellsoft/liberica-openjdk-alpine:17

# 빌드 과정 실행
CMD ["./gradlew", "clean", "build"]

# 환경변수 선언
ARG JAR_FILE=build/libs/*.jar

# 로컬 파일을 app.jar로 복사
COPY ${JAR_FILE} app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app.jar"]