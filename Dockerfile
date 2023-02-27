FROM gradle:latest AS build
WORKDIR /usr/app
COPY . .
RUN gradle build

FROM amazoncorretto:11-alpine-full
ENV JAR_NAME=F1Discord-Bot-all.jar
ENV APP_HOME=/usr/app
WORKDIR $APP_HOME
COPY --from=build $APP_HOME .

ENTRYPOINT exec java -jar $APP_HOME/build/libs/$JAR_NAME