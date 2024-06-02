FROM bellsoft/liberica-openjdk-alpine:17

CMD ["./gradlew", "clean", "build"]

VOLUME /tmp

# 환경변수 선언
ARG JAR_FILE=build/libs/*.jar

# 로컬 파일을 app.jar로 복사
COPY ${JAR_FILE} app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]