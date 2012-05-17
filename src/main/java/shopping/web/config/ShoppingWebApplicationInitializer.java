package shopping.web.config;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.DispatcherServlet;

import shopping.config.ShoppingApplicationConfig;

public class ShoppingWebApplicationInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext container) throws ServletException {
		/*
		<context-param>
        	<param-name>contextConfigLocation</param-name>
        	<param-value>classpath:/META-INF/spring/*.xml</param-value>
    	</context-param>
    	
		<listener>
            <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
        </listener>
        */
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(ShoppingApplicationConfig.class);
		
		container.addListener(new ContextLoaderListener(rootContext));
		
		
		/*
		<servlet>
	        <servlet-name>appServlet</servlet-name>
	        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
	        <init-param>
	            <param-name>contextConfigLocation</param-name>
	            <param-value>/WEB-INF/spring/servlet-context.xml</param-value>
	        </init-param>
	        <load-on-startup>1</load-on-startup>
	    </servlet>
	
	    <servlet-mapping>
	        <servlet-name>appServlet</servlet-name>
	        <url-pattern>/</url-pattern>
	    </servlet-mapping>
	    */
		AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
		dispatcherContext.register(ShoppingWebApplicationConfig.class);
		
		ServletRegistration.Dynamic dispatcher = container.addServlet("dispatcher", new DispatcherServlet(dispatcherContext));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
		
		
		/*
		<filter>
	        <filter-name>encodingFilter</filter-name>
	        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
	        <init-param>
	            <param-name>encoding</param-name>
	            <param-value>UTF-8</param-value>
	        </init-param>
	        <init-param>
	            <param-name>forceEncoding</param-name>
	            <param-value>true</param-value>
	        </init-param>
	    </filter>
	
	    <filter-mapping>
	        <filter-name>encodingFilter</filter-name>
	        <url-pattern>/*</url-pattern>
	    </filter-mapping>
	    
	    <filter>
	        <filter-name>hiddenHttpMethodFilter</filter-name>
	        <filter-class>org.springframework.web.filter.HiddenHttpMethodFilter</filter-class>
	    </filter>
	
	    <filter-mapping>
	        <filter-name>hiddenHttpMethodFilter</filter-name>
	        <url-pattern>/*</url-pattern>
	    </filter-mapping>
	    */
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		encodingFilter.setEncoding("UTF-8");
		encodingFilter.setForceEncoding(true);
		
		HiddenHttpMethodFilter hiddenHttpMethodFilter = new HiddenHttpMethodFilter();
		
		container.addFilter("encodingFilter", encodingFilter)
		         .addMappingForServletNames(EnumSet.allOf(DispatcherType.class), true, "dispatcher");
		
		container.addFilter("hiddenHttpMethodFilter", hiddenHttpMethodFilter)
		         .addMappingForServletNames(EnumSet.allOf(DispatcherType.class), true, "dispatcher");
	}

}
