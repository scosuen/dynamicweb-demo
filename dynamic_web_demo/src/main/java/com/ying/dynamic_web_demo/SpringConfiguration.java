package com.ying.dynamic_web_demo;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.dao.support.PersistenceExceptionTranslator;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class SpringConfiguration {
	
	@Value("${datasource.url}")
	private String url;
	@Value("${datasource.name}")
	private String username;
	@Value("${datasource.pwd}")
	private String pwd;
	@Value("${hibernate.property.hbm2ddl.auto}")
	private String hibernateHbm2ddlAuto;
	@Value("${hibernate.property.dialect}")
	private String hibernateDialect;
	@Value("${hibernate.property.globally_quoted_identifiers}")
	private String hibernateGlobally_quoted_identifiers;
	
	@Bean
	public DataSource dataSource() {
		final DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(pwd);
		return dataSource;
	}

	@Bean
	@Autowired
	public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
		final LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		sessionFactory.setPackagesToScan(new String[] { "com.ying" });
		sessionFactory.setHibernateProperties(new Properties() {
			{
				setProperty("hibernate.hbm2ddl.auto", hibernateHbm2ddlAuto);
				setProperty("hibernate.dialect", hibernateDialect);
				setProperty("hibernate.globally_quoted_identifiers", hibernateGlobally_quoted_identifiers);
			}
		});

		return sessionFactory;
	}

	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);

		return txManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslationPostProcessor() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	@Bean
	public PersistenceExceptionTranslator exceptionTranslator() {
		return new HibernateExceptionTranslator();
	}
}
