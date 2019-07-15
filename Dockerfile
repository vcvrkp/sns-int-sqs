FROM openjdk:8-jre
ADD target/sns-int-sqs-1.jar sns-int-sqs-1.jar
EXPOSE 8090
ENTRYPOINT ["java","-jar","sns-int-sqs-1.jar"]
