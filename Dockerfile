FROM gradle:latest AS build
WORKDIR /usr/app
COPY . .
RUN gradle build --no-daemon

FROM amazoncorretto:11-alpine-full
ENV JAR_NAME=F1Discord-Bot-all.jar
WORKDIR $APP_HOME
COPY --from=build $APP_HOME .

ENTRYPOINT exec java -jar /build/libs/$JAR_NAME