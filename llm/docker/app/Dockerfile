FROM eclipse-temurin:21-jdk AS buildTool

WORKDIR app

COPY ../.. .

RUN ./gradlew clean
RUN ./gradlew bootJar

FROM eclipse-temurin:21-jre

WORKDIR app

COPY --from=buildTool app/build/libs .

ENTRYPOINT java -jar *.jar









