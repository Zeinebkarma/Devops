FROM openjdk:11
EXPOSE 8080
ADD https://github.com/Zeinebkarma/Devops/blob/ons/target/achat.jar achat.jar
ENTRYPOINT ["java" ,"-jar","/achat.jar"]

