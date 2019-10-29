FROM openjdk:10.0.2-jre-slim
COPY target/order-1.0-SNAPSHOT.jar .
CMD /usr/bin/java -Xmx400m -Xms400m -jar order-1.0-SNAPSHOT.jar
EXPOSE 8080