# ProcessMessage
Message Processing Java Spring Boot Component.

# Usage

Execute command as follows:

java -XX:-UseParallelGC -XX:ParallelGCThreads=4 -jar ProcessMessage-1.0.jar

	(for optimal java tuning related to the number of available cores)

or

docker run --name processmessage -p 8080:8080 ricardopalves1/processmessage

(according to http://hub.docker.com/r/ricardopalves1/processmessage/)

# Java version
This component is compliance with Java 8.

# Java tuning
This component uses a consumers thread pool according to the number of processors available on the system.
Therefore, tuning the Java GC also accordantly to this number improves the heap objects release task.

	@Bean
	public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
		int cores = Runtime.getRuntime().availableProcessors();
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory());
		factory.setConcurrency(cores + "-" + cores);
		factory.setMessageConverter(messageConverter());
		factory.setAutoStartup(false);

		return factory;
	}
	
Please, validate also the respective consumers count as in "ConsumerCount.png" image within jconsole folder.

# Messaging server
This component uses an embedded Apache ActiveMQ server.

	spring.activemq.broker-url=tcp://localhost:61616
	
# Logging
This project uses SLF4j that has been configured for production site, in other words "ERROR" category.
Nevertheless, the reports use "INFO" to print out the results in both console and log file outputs.

	logging.file=./ProcessMessage.log
	logging.pattern.console=%-40(%d{ISO8601} [%thread]) ProcessMessage %-5level %-60logger -%msg%n
	logging.pattern.file=%-40(%d{ISO8601} [%thread]) ProcessMessage %-5level %-60logger -%msg%n
	logging.level.com.app.msg.process=info
	logging.level.=error
	
# Quality assurance
This project has been analyzed with SonarQube and passed 
	(see "SonarQube.png" image).

# JVM Analysis
Although, the component just runs a 50 established number of messages. 
It's possible to add an argument to increase this threshold as follows.

	For instance, 100 messages:
		java -Dmax.msg.number=100 -XX:-UseParallelGC -XX:ParallelGCThreads=4 -jar ProcessMessage-1.0.jar

Moreover, the cosume of memory was low and steady what is a good evidence, the tests had no leak of memory as we can see in the images "Overview.png" and "Non-Heap.png" within the jconsole folder. 

Last but not least, the number of active threads over time and CPU usage were also steady and diminishing what is a good evidence of throughput and no deadlocks.

# How to test it.
The Spring Boot Java component provides a rest webservice that can be consumed through POST methods. For instance:

- Message Type 1 – contains the details of 1 sale 
	E.g apple at 10p.
	
	curl -X POST http://localhost:8080/sales/product/apple/units/10

- Message Type 2 – contains the details of a sale and the number of occurrences of that sale. 
	E.g 20 sales of apples at 10p each.
	
	curl -X POST http://localhost:8080/sales/product/apple/units/10/occurrences/20

- Message Type 3 – contains the details of a sale and an adjustment operation to be applied to all stored sales of this product type. Operations can be add, subtract, or multiply. 
	E.g Add 20p apples would instruct your application to add 20p to each sale of apples you have recorded.
	
	curl -X POST http://localhost:8080/sales/product/apple/units/5/operation/add/value/20
