From openjdk:8
copy ./target/achat-1.8.jar achat-1.8.jar
CMD ["java","-jar","achat-1.8.jar"]