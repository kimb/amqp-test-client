package fi.iki.kimb.amqp.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

/**
 * Just pings the the AMQP server with one message.
 * @author Kim Blomqist
 *
 */
@Service
public class Pinger {
	private static final Logger log = LoggerFactory.getLogger(Pinger.class);

	@Autowired
	AmqpTemplate template;

	public void run() {
		log.info("running");
		template.convertAndSend("myqueue", "foo");
		String foo = (String) template.receiveAndConvert("myqueue");
		log.info("Received: " + foo);
	}

	/** Simple launcher */
	public static void main(String args[]) {
		log.info("hello world");
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		Pinger pinger = context.getBean("pinger", Pinger.class);
		pinger.run();
	}

}
