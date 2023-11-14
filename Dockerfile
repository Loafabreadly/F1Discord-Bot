FROM amazoncorretto:17-alpine3.17 as debug
COPY . /debug
RUN ls -la /debug


FROM amazoncorretto:17-alpine3.17
# JAR File Name & Location
ENV JAR_NAME=F1Discord-Bot-all.jar
ENV HOME_DIR=/app

# Setup
RUN mkdir $HOME_DIR
COPY --from=debug /debug/$JAR_NAME ${HOME_DIR}/${JAR_NAME}

# Launch with args
ENTRYPOINT java -jar ${HOME_DIR}/${JAR_NAME}