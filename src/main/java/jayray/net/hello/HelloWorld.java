package jayray.net.hello;

import org.apache.log4j.Logger;

public class HelloWorld {
	private static final Logger logger = Logger.getLogger(HelloWorld.class);

	public static void main(String[] args) {
		HelloWorld greeter = new HelloWorld();
		greeter.sayhello();
	}

	private String sayhello() {
		System.out.println("hello stdout");
		logger.debug("sample debug message");
		logger.info("sample info message");
		logger.warn("sample warning message");
		logger.error("sample error message");
		return "hello";
	}

}
