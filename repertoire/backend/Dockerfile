FROM maven:3.9-amazoncorretto-21 as build
ENV HOME=/usr/app
RUN mkdir -p $HOME
WORKDIR $HOME
COPY repertoire/pom.xml .
RUN mvn dependency:resolve-plugins dependency:resolve
ADD repertoire $HOME
RUN mvn install

FROM openjdk:21 as backend
ENV HOME=/usr/app
COPY --from=build "${HOME}/target/*.jar" repertoire.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/repertoire.jar"]
