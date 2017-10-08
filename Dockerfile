FROM openjdk:8
MAINTAINER ricardopalves1
COPY ./ProcessMessage-1.0.jar /opt/
WORKDIR /opt/
CMD ["java", "-XX:-UseParallelGC", "-XX:ParallelGCThreads=4", "-jar", "ProcessMessage-1.0.jar"]