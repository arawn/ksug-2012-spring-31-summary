package shopping.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;


@Configuration
@Import({PersistenceConfig.class})
@ComponentScan(basePackages={"shopping.repositories"})
@PropertySource({"classpath:META-INF/config.properties"})
public class ShoppingApplicationConfig {
	
}
