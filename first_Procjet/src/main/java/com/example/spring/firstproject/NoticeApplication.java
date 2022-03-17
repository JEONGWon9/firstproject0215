package com.example.spring.firstproject;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.navercorp.lucy.security.xss.servletfilter.XssEscapeServletFilter;


@SpringBootApplication
public class NoticeApplication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(NoticeApplication.class, args);

	}

	

	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);

		Resource[] res = new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/*.xml");
		sessionFactory.setMapperLocations(res);

		return sessionFactory.getObject();

	}
	
	//xss luxy
	
	/*
	 * @Bean
	 * public FilterRegistrationBean<XssEscapeServletFilter>
	 * getFilterRegistrationBean(){ FilterRegistrationBean<XssEscapeServletFilter>
	 * registrationBean = new FilterRegistrationBean<>();
	 * registrationBean.setFilter(new XssEscapeServletFilter());
	 * registrationBean.setOrder(1);
	 * registrationBean.addUrlPatterns("/ProWriteNotice", "/noitceInsertReply");
	 * return registrationBean; }
	 */

}
