FROM maven:latest
LABEL authors="vladi"

WORKDIR /app

COPY pom.xml /app

COPY . /app

RUN mvn package

CMD ["java", "-jar", "target/IBMCalculator.jar"]
