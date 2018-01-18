FROM openjdk:8-jre-alpine

RUN mkdir /app

WORKDIR /app

ADD ./api/target/shippings-api-1.0.0-SNAPSHOT.jar /app

EXPOSE 8089

CMD ["java", "-jar", "shippings-api-1.0.0-SNAPSHOT.jar"]