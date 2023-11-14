FROM amazoncorretto:17-alpine3.17

# JAR File Name & Location
ENV JAR_NAME=F1Discord-Bot-all.jar
ENV HOME_DIR=/app

# Setup
WORKDIR $HOME_DIR
COPY $JAR_NAME $HOME_DIR

# Launch with args
ENTRYPOINT java -jar ${HOME_DIR}/${JAR_NAME}