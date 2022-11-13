FROM openjdk:11
EXPOSE 8089
ADD target/*1.0.jar back_secteuractivite.jar
ENTRYPOINT ["java","-jar","back_secteuractivite.jar"]
