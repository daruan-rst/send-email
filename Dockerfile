FROM openjdk:11

EXPOSE 6080

ADD target/send-email-0.0.1.jar send-email-0.0.1.jar

ENTRYPOINT ["java","-jar","send-email-0.0.1.jar"]