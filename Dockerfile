FROM amazoncorretto:11

# JAR File Name & Location
ENV JAR_NAME=F1Discord-Bot-all.jar
ENV HOME_DIR=/app

# DISCORD Bot Token passed from CI/CD Pipeline
ARG DISCORD_TOKEN
ENV DISCORD_TOKEN=${DISCORD_TOKEN}

# Git Commit Hash from CI/CD Pipeline
ARG VER
ENV VER=${VER}

# Setup
WORKDIR $HOME_DIR
COPY /build/libs/$JAR_NAME $HOME_DIR

# Launch with args
ENTRYPOINT java -jar ${HOME_DIR}/${JAR_NAME} ${DISCORD_TOKEN} ${VER}