#############################################################################################
###              Stage where Docker is building spring boot app using maven               ###
#############################################################################################
FROM maven:3.6.3-jdk-8 as build

ARG SERVICE_NAME
ARG SKIP_TESTS=false

COPY . .

WORKDIR ${SERVICE_NAME}

RUN mvn -B clean package \
        -Dmaven.test.skip=${SKIP_TESTS}

#############################################################################################

#############################################################################################
### Stage where Docker is running a java process to run a service built in previous stage ###
#############################################################################################
FROM openjdk:8-jdk-slim

# ARG MVN_PROFILES
ARG SERVICE_NAME

COPY --from=build ./${SERVICE_NAME}/target/${SERVICE_NAME}-*.jar /app/service.jar

CMD ["java", "-jar", "/app/service.jar"]
#############################################################################################
