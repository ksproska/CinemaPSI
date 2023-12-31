FROM maven:3.9-amazoncorretto-21 as build
ENV HOME=/usr/app
RUN mkdir -p $HOME
WORKDIR $HOME
COPY client-service/pom.xml .
RUN mvn dependency:resolve-plugins dependency:resolve
ADD client-service $HOME
RUN mvn install

FROM openjdk:21 as backend
ENV HOME=/usr/app
COPY --from=build "${HOME}/target/*.jar" client-service.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/client-service.jar"]
