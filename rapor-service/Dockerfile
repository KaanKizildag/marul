FROM amazoncorretto:17.0.3
CMD cd .. && mvn clean install
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
COPY target/classes/raporlar /raporlar
ENTRYPOINT ["java","-jar","/app.jar"]