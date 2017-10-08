# ProcessMessage
Message Processing Java Component.

# Usage

java -XX:-UseParallelGC -XX:ParallelGCThreads=4 -jar ProcessMessage-1.0.jar
(for optimal performance related to the number of available cores)

or

docker run --name processmessage -p 8080:8080 ricardopalves1/processmessage
(according to http://hub.docker.com/r/ricardopalves1/processmessage/)
