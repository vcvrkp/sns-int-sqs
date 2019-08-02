FROM openjdk:8u212-jre
ADD target/sns-int-sqs-1.jar sns-int-sqs.jar
EXPOSE 8090
ENTRYPOINT ["java","-jar","sns-int-sqs.jar"]