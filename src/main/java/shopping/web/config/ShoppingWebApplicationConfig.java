package shopping.web.config;

import java.io.IOException;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;

import org.resthub.web.springmvc.router.RouterHandlerMapping;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewInterceptor;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import shopping.domain.support.convert.ProductEntityConvert;
import shopping.domain.support.convert.StringToProductTypeConvert;

import com.google.common.collect.Lists;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages={"shopping.web"})
public class ShoppingWebApplicationConfig extends WebMvcConfigurationSupport {
	
	@Inject ResourceLoader resourceLoader; 
	@Inject ConfigurableEnvironment environment;
	
	@Inject EntityManagerFactory entityManagerFactory;
	
	
	@PostConstruct
	public void initWebEnvironment() throws IOException {
		PropertiesFactoryBean factoryBean = new PropertiesFactoryBean();
		factoryBean.setLocation(resourceLoader.getResource("WEB-INF/web-config.xml"));
		factoryBean.afterPropertiesSet();
		factoryBean.getObject();
		
		environment.getPropertySources().addFirst(
				new PropertiesPropertySource("web-config", factoryBean.getObject()));
	}
	
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("WEB-INF/messages");
		
		return messageSource;
	}
	
	@Bean
	public RouterHandlerMapping routerHandlerMapping() {
		RouterHandlerMapping handlerMapping = new RouterHandlerMapping();
		handlerMapping.setRouteFiles(Lists.newArrayList("classpath:routes.conf"));
		handlerMapping.setServletPrefix("/spring-mvc-31-demo");
		handlerMapping.setInterceptors(getInterceptors());
		handlerMapping.setOrder(0);
		
		return handlerMapping;
	}
	
	@Bean
	@Override
	public RequestMappingHandlerMapping requestMappingHandlerMapping() {
		RequestMappingHandlerMapping handlerMapping = new RequestMappingHandlerMapping();
		handlerMapping.setInterceptors(getInterceptors());
		handlerMapping.setOrder(1);
		
		return handlerMapping;
	}

	@Bean
	public ViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views");
		viewResolver.setSuffix(".jsp");
		viewResolver.setOrder(1);
		
		return viewResolver;
	}
	
	@Bean
	public HandlerExceptionResolver simpleMappingExceptionResolver() {
		Properties mappings = new Properties();
		mappings.put(NoSuchRequestHandlingMethodException.class.getName(), "/error/404");
		mappings.put(HttpRequestMethodNotSupportedException.class.getName(), "/error/404");
		
		SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();
		exceptionResolver.setExceptionMappings(mappings);
		exceptionResolver.setDefaultErrorView("/error/default");
		exceptionResolver.setDefaultStatusCode(HttpStatus.BAD_REQUEST.value());
		exceptionResolver.setOrder(1);
		
		return exceptionResolver;
	}
	
	@Bean
	public ProductEntityConvert stringToProductConvert() {
		return new ProductEntityConvert();
	}
	
	@Bean
	public OpenEntityManagerInViewInterceptor openEntityManagerInViewInterceptor() {
		OpenEntityManagerInViewInterceptor interceptor = new OpenEntityManagerInViewInterceptor();
		interceptor.setEntityManagerFactory(entityManagerFactory);
		return interceptor;
	}

	/*
	 * <mvc:annotation-driven conversion-service="conversionService"/>
     * <bean id="conversionService" class="org.springframework.format.support.FormattingConversionServiceFactoryBean">
     *     <property name="converters">
     *         <list>
     *             <bean class="..."/>
     *             <bean class="..."/>
     *         </list>
     *     </property>
     *     <property name="formatters">
     *         <list>
     *             <bean class="..."/>
     *         </list>
     *     </property>
     * </bean>  
	 */
	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(new StringToProductTypeConvert());
		registry.addConverter(stringToProductConvert());
	}
	
	/*
	 * <mvc:interceptors>
	 *     <bean class="org.springframework.orm.jpa.support.OpenEntityManagerInViewInterceptor"
	 *           p:entityManagerFactory-ref="entityManagerFactory"/>
	 * </mvc:interceptors>
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addWebRequestInterceptor(openEntityManagerInViewInterceptor());
	}
	
	/*
	 * <mvc:view-controller path="/" view-name="redirect:/movie"/>
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName(Routes.REDIRECT + Routes.PRODUCT_MOVIE);
	}	

	/*
	 * <mvc:resources mapping="/resources/**" location="/resources/" />
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	/*
	 * <mvc:default-servlet-handler/>
	 */
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	
}
