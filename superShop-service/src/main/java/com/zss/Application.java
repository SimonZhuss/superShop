package com.zss;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;

import com.zss.superShop.utils.ShutDownBlockHook;
import com.zss.superShop.utils.ShutDownHookLevel;


@SpringBootApplication(scanBasePackages = "com.zss.superShop.*")
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
public class Application implements EmbeddedServletContainerCustomizer {
	private static Logger logger = LoggerFactory.getLogger(Application.class);

	private int port = 8080;

	public void setPort(int port) {
		this.port = port;
	}

	public static void main(String[] args) throws InterruptedException {
		SpringApplication app = new SpringApplication(Application.class);
		app.run(args);
		ShutDownBlockHook.registerHook(new ShutDownBlockHook.ShutDownRunning(){
			
			@Override
			public void run() {
				logger.info("Application ShutDownBlockHook .............");
			}
		}, ShutDownHookLevel.LoggingHookLevel);
	}

	@Override
	public void customize(ConfigurableEmbeddedServletContainer container) {

		container.setPort(this.port);
	}

}
