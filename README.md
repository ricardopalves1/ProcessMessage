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
