[KSUG 세미나 2012 - Part 1] Spring 3.1에 대한 기능 소개와 @MVC 이야기
================================================================================================


Spring Framework 3.1: Key Themes
------------------------------------------------------------------------------------------------

* Environment profiles for bean definitions
* Java-based application configuration
* Overhaul of the test context framework
* 'c:' namespace
* Cache abstraction & declarative caching
* Explicit support for Servlet 3.0
* **@MVC processing & flash attributes**
* Refined JPA support
* Hibernate 4.0 & Quartz 2.0
* Support for Java SE 7

<br>### ◎ Environment profiles for bean definitions ###

#### ✔ Environment Abstraction ####

* org.springframework.core.env.PropertyResolver
* org.springframework.core.env.Environment

#### ✔ Environment Profiles && Configuration ####

* XML 'profile' attribute on <beans> element

```xml
<beans profiles="dev">
    <jdbc:embedded-database id="dataSource" type="H2"/>
</beans>

<beans profiles="prod">
    <bean id="dataSource" class="..."/>
</beans>
```

* @Profile annotation on configuration classes or individual component classes

```java
@Configuration
@Profile("dev")
public class AppConfig {
    @Bean
    public DataSource embeddedDatabase() { ... }
}
```

* Activating specific profiles by name

  > Environment Variable : export spring.profiles.active=dev
  > JVM Parameter : -Dspring.profiles.active=prod
  > Web.xml : context-param, init-param


<br>### ◎ Java-based application configuration ###

* @Configuration
* @ComponentScan
* @Bean
* @Enable*

  > @EnableTransactionManagement
  > @EnableWebMvc

* @PropertySource("classpath:META-INF/app.properties")
* @ActiveProfiles("dev")


<br>### ◎ 'c:' namespace ###

```xml
<bean class="…" c:age="10" c:name="myName"/>

<bean class="…" c:name-ref="nameBean" c:spouse-ref="spouseBean"/>
```

<br>### ◎ Cache abstraction & declarative caching ###

* Declarative Caching

```java
@Cacheable
public Owner loadOwner(int id);

@Cacheable(condition="name.length < 10")
public Owner loadOwner(String name);

@CacheEvict
public void deleteOwner(int id);
```
```xml
<cache:annotation-driven>
```

* Backend adapters for EhCache, GemFire, Coherence, etc

  > EhCacheCacheManager
  > GemFireCacheManager

<br>### ◎ Explicit support for Servlet 3.0 ###

* support for XML-free web application setup (no web.xml)
* support for asynchronous request processing
* standard Servlet 3.0 file upload support behind Spring's MultipartResolver abstraction

<br>### ◎ @MVC processing & flash attributes ###

* New @MVC Infrastructure
* FlashMap support and FlashMapManager abstraction

<br>### ◎ Refined JPA support ###

* Package scanning without persistence.xml
* Consistent JPA setup by persistence unit name

<br>### ◎ Hibernate 4.0 & Quartz 2.0 ###

<br>### ◎ Support for Java SE 7 ###

* making best use of JRE 7 at runtime
* support for JDBC 4.1
* support for fork-join framework


<br><br>
Spring @MVC 3.1: Key Themes
------------------------------------------------------------------------------------------------

* **Consumes/Produces**
* **URI Variables**
* **Redirect & Flash Attributes**
* **New @MVC Infrastructure**
* **Java Config**
* Multipart Request Support
* UriComponentsBuilder
* HDIV Integration

<br>### ◎ Consumes/Produces ###

#### ✔ Input Media Type ####

```java
@ResponseBody
@RequestMapping(
        method=RequestMethod.POST,
        header="Content-Type=application/json")
public String save(@RequestBody JavaBean javaBean) {

}

@ResponseBody
@RequestMapping(
        method=RequestMethod.POST, 
        consumes="application/json")
public String save(@RequestBody JavaBean javaBean) {

}
```

#### ✔ Output Media Type ####

```java
@ResponseBody
@RequestMapping(
    method=RequestMethod.GET
    header="Accept=application/json")
public JavaBean get() {

}

@ResponseBody
@RequestMapping(
    method=RequestMethod.GET, 
    produces="application/json") 
public JavaBean get() {

}
```


<br>### ◎ URI Variables ###

#### ✔ Data Binding & URI Variables ####

```java
@RequestMapping(value="/people/{firstName}/{lastName}/SSN")
public void search(Person person) {
    // person.getFirstName() is populated
    // person.getLastName()  is populated
}
```

#### ✔ Rendering & Path Variables ####

```java
@RequestMapping("/apps/edit/{slug}")
public String editForm(@PathVariable String slug){
    // No need to add "slug" to the model
}
```

#### ✔ "redirect:" & URI Vars ####

```java
@RequestMapping(
    value="/{year}/{month}/{slug}/rooms",
    method=RequestMethod.POST)
public String createRoom() {

    // No need to add "year", "month", & "slug"
    // They will be used in RedirectView

    return "redirect:/{year}/{month}/{slug}";
}
```

#### ✔ Model Attributes & URI Vars ####

```java
@RequestMapping(
        value="/{account}", 
        method = RequestMethod.PUT)
public String update(Account account) {

    // Account was retrieved from DB 
    // via Converter<String, Account>

}
```


<br>### ◎ Redirect & Flash Attributes ###

#### ✔ Redirect URL ####

```java
@RequestMapping(method = RequestMethod.POST)
public String save(Account account){

	return "redirect:/account/" + account.getId();
}
```

#### ✔ RedirectAttributes ####

```java
@RequestMapping(method=POST)
public String save(Account account, RedirectAttributes redirectAttrs){

  redirectAttrs.addAttribute("id", account.getId);

  return "redirect:/action/{id}";

}
```

#### ✔ Flash Attributes ####

```java
@RequestMapping(method=POST)
public String save(Entity entity, RedirectAttributes redirectAttrs){

	redirectAttrs.addFlashAttribute("message", "Success!");

	return "redirect:/show";
}
```

```
	${message}
```


<br>### ◎ Multipart Request Support ###

#### ✔ MultipartFile Example ####

```java
@RequestMapping(method = RequestMethod.POST)
public void create(@RequestParam("file") MultipartFile file){

    InputStream in = file.getInputStream();

    // ...

}
```

#### ✔ javax.servlet.http.Part ####

```java
@RequestMapping(method = RequestMethod.POST)
public void create(@RequestParam("file") Part part){

    InputStream in = part.getInputStream();

    // ...

}
```

#### ✔ @RequestPart ####

```java
@RequestMapping(
    method = RequestMethod.POST, 
    consumes = { "multipart/form-data" })
public ResponseEntity<Object> void create(
    @RequestPart("json-data") @Valid JavaBean javaBean, 
    @RequestPart("file-data") MultipartFile file) {

   // ...

} 
```

<br>### ◎ New @MVC Infrastructure ###

#### ✔ DispatcherServlet과 MVC 아키텍쳐 ####

![MVC 아키텍쳐](http://cfile25.uf.tistory.com/original/165D39114A6E757A228087)

(그림 출처: http://chanwook.tistory.com/784)


**HandlerAdapter**

* SimpleControllerHandlerAdapter (1.0)
* SimpleServletHandlerAdapter (1.1.5)
* HttpRequestHandlerAdapter (2.0)
* AnnotationMethodHandlerAdapter (2.5)

**HandlerMapping**

* BeanNameUrlHandlerMapping (1.0)
* SimpleUrlHandlerMapping (1.0)
* ControllerClassNameHandlerMapping (2.0)
* DefaultAnnotationHandlerMapping (2.5)
* ControllerBeanNameHandlerMapping (2.5.3)

**HandlerExceptionResolver**

* AnnotationMethodHandlerExceptionResolver

  > @ExceptionHandler

* ResponseStatusExceptionResolver
* DefaultHandlerExceptionResolver
* SimpleMappingExceptionResolver

**AnnotationMethodHandlerAdapter의 확장 포인트**

* setCustomArgumentResolver(WebArgumentResolver argumentResolver) - (2.5.2)
* setCustomModelAndViewResolver(ModelAndViewResolver modelAndViewResolver) - (3.0.0)

#### ✔ New @MVC Infrastructure ####

![New @MVC Infrastructure](http://rstoyanchev.github.com/spring-mvc-31-update/file/infrastructure/support-classes.png)

(그립 출처 : http://rstoyanchev.github.com/spring-mvc-31-update/#25)

* HandlerMapping : RequestMappingHandlerMapping
* HandlerAdapter : RequestMappingHandlerAdapter
* HandlerExceptionResolver : ExceptionHandlerExceptionResolver

**New Abstractions**

* HandlerMethod
* HandlerMethodArgumentResolver
* HandlerMethodReturnValueHandler


<br><br>
## 참고자료

* [Configuration Enhancements in Spring 3.1](http://cbeams.github.com/spring-3.1-config/#1)
* [Spring MVC 3.1 Update](http://rstoyanchev.github.com/spring-mvc-31-update/#1)