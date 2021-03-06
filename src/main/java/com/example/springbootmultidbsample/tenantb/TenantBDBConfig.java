package com.example.springbootmultidbsample.tenantb;



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
@EnableJpaRepositories(entityManagerFactoryRef = "tenantBEntityManagerFactory",
    transactionManagerRef = "tenantBTransactionManager", basePackages = {"com.example.springbootmultidbsample.tenantb"})
public class TenantBDBConfig {

	/*@Bean
	@ConfigurationProperties("app.db.tenantb")
	public DataSourceProperties secondDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean(name = "tenantBDataSource")
	@ConfigurationProperties("app.db.tenantb")
	public DataSource secondDataSource() {
		return secondDataSourceProperties().initializeDataSourceBuilder().build();
	}*/
	
	@Bean(name = "tenantBDataSource" , destroyMethod="")
	public DataSource dataSource() throws Exception {
		JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
		dataSourceLookup.setResourceRef(true);
		return dataSourceLookup.getDataSource("java:jboss/datasources/UsaaDB");
	}
 
	Map<String,?> additionalJpaProperties(){
        Map<String,String> map = new HashMap<>();
        map.put("hibernate.hbm2ddl.auto", "none");
        map.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        map.put("hibernate.show_sql", "true");
        return map;
    }

  @Bean(name = "tenantBEntityManagerFactory")
  @ConditionalOnBean(name = "tenantBDataSource")
  public LocalContainerEntityManagerFactoryBean tenant_b_EntityManagerFactory(
      EntityManagerFactoryBuilder builder, @Qualifier("tenantBDataSource") DataSource dataSource) {
    return builder
    		.dataSource(dataSource)
    		.packages("com.example.springbootmultidbsample.domain")
    		.persistenceUnit("tenant_b")
    		.properties(additionalJpaProperties())
    		.build();
  }

  @Bean(name = "tenantBTransactionManager")
  @ConditionalOnBean(name = "tenantBEntityManagerFactory")
  public PlatformTransactionManager tenant_b_TransactionManager(
      @Qualifier("tenantBEntityManagerFactory") EntityManagerFactory tenant_BEntityManagerFactory) {
    return new JpaTransactionManager(tenant_BEntityManagerFactory);
  }

}
