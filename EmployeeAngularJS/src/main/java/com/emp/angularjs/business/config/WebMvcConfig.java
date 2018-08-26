/**
 * 
 */
package com.emp.angularjs.business.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * @author Tripti
 *
 */
@EnableWebMvc
@Configuration
@ComponentScan(basePackages="com.emp.angularjs.business")
public class WebMvcConfig implements WebMvcConfigurer {
   @Bean
   public InternalResourceViewResolver resolver() {
      InternalResourceViewResolver resolver = new InternalResourceViewResolver();
      resolver.setViewClass(JstlView.class);
      resolver.setPrefix("/views/");
      resolver.setSuffix(".html");
      return resolver;
   }
   
   @Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	   registry.addResourceHandler("/**").addResourceLocations("/").setCachePeriod(1);
		//WebMvcConfigurer.super.addResourceHandlers(registry);
	}
   
/*   @Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
	   	converters.add(stringConverter());
	   	//converters.add(jsonConverter());
		WebMvcConfigurer.super.extendMessageConverters(converters);
	}
   
   @Override
   public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

   }
   
	@Bean
	public MappingJackson2HttpMessageConverter jsonConverter() {
		MappingJackson2HttpMessageConverter jacksonConverter = new MappingJackson2HttpMessageConverter();
		jacksonConverter.setSupportedMediaTypes(Arrays.asList(MediaType.valueOf("application/json")));
		jacksonConverter.setObjectMapper(jacksonObjectMapper());
		return jacksonConverter;
	}

	@Bean
	public ObjectMapper jacksonObjectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setSerializationInclusion(Include.NON_NULL);
		return objectMapper;
	}
	
	@Bean
	public StringHttpMessageConverter stringConverter() {
		StringHttpMessageConverter stringConverter = new StringHttpMessageConverter();
		return stringConverter;
	}*/
}
