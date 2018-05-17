package com.example.springbootmultidbsample.tenanta;



import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaAuditing
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "tenantAEntityManagerFactory",
    transactionManagerRef = "tenantATransactionManager", basePackages = {"com.example.springbootmultidbsample.tenanta"})
public class TenantADBConfig {

	
	/*
	
	//This configuration should be used when configuring the datasources with url, user name , password etc in .properties/yml file
	 * Remember, at least one data source should be marked as Primary when configuring multiple datasources.
	 * 
	@Bean
	@Primary
	//@ConfigurationProperties(prefix = "app.db.tenanta")
	public DataSourceProperties firstDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean(name = "tenantADataSource")
	@Primary
	//@ConfigurationProperties(prefix = "ais.db.geico")
	public DataSource firstDataSource() {
		return firstDataSourceProperties().initializeDataSourceBuilder().build();
	}
	
	*/
	
	Map<String,?> additionalJpaProperties(){
        Map<String,String> map = new HashMap<>();
        map.put("hibernate.hbm2ddl.auto", "none");
        map.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        map.put("hibernate.show_sql", "true");
        return map;
    }
	
	@Bean(name = "tenantADataSource" , destroyMethod="")
	@Primary
	public DataSource dataSource() throws Exception {
		JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
		dataSourceLookup.setResourceRef(true);
		return dataSourceLookup.getDataSource("java:jboss/datasources/GeicoDB");
	}
	
  @Bean(name = "tenantAEntityManagerFactory")
  @ConditionalOnBean(name = "tenantADataSource")
  @Primary
  public LocalContainerEntityManagerFactoryBean tenant_a_EntityManagerFactory(
      EntityManagerFactoryBuilder builder, @Qualifier("tenantADataSource") DataSource dataSource) {
    return builder
    		.dataSource(dataSource)
    		.packages("com.example.springbootmultidbsample.domain")
    		.persistenceUnit("tenant_a")
    		.properties(additionalJpaProperties())
        .build();
  }

  @Bean(name = "tenantATransactionManager")
  @Primary
  public PlatformTransactionManager tenant_a_TransactionManager(
      @Qualifier("tenantAEntityManagerFactory") EntityManagerFactory tenant_AEntityManagerFactory) {
    return new JpaTransactionManager(tenant_AEntityManagerFactory);
  }

}