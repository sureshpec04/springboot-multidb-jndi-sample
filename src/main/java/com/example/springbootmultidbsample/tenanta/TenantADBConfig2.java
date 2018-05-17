package com.example.springbootmultidbsample.tenanta;


/*
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
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
public class TenantADBConfig2 {

	/*
	@Bean
	@Primary
	//@ConfigurationProperties(prefix = "ais.db.geico")
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
	
	/*Map<String,?> additionalJpaProperties(){
        Map<String,String> map = new HashMap<>();
        map.put("hibernate.hbm2ddl.auto", "none");
        map.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        map.put("hibernate.show_sql", "true");
        return map;
    }
	
	@Bean(name = "tenantADataSource" , destroyMethod="")
	//@ConfigurationProperties(prefix="ais.db.geico")
	public DataSource dataSource() throws Exception {
		JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
		dataSourceLookup.setResourceRef(true);
		return dataSourceLookup.getDataSource("jdbc/GeicoDB");
	}
	
	//Configure JNDI in Embedded Tomcat
	/*@Bean
	public TomcatServletWebServerFactory  tomcatFactory() {
	    return new TomcatServletWebServerFactory() {
	    	
	    	@Override
	        protected TomcatWebServer getTomcatWebServer(Tomcat tomcat) {
	            tomcat.enableNaming();
	            return super.getTomcatWebServer(tomcat);
	        }
	    	
	    	@Override
	    	protected void postProcessContext(Context context) {
		    	ContextResource resource = new ContextResource();
		    	resource.setName("jdbc/GeicoDB");
		    	resource.setType(DataSource.class.getName());
		    	//resource.setProperty("factory", "org.apache.tomcat.jdbc.pool.DataSourceFactory");
		    	//resource.setProperty("type", "javax.sql.DataSource");
		    	resource.setProperty("driverClassName", "com.mysql.jdbc.Driver");
		    	resource.setProperty("url", "jdbc:mysql://127.0.0.1/tenant_a?useSSL=false");
		    	resource.setProperty("username", "devuser");
		    	resource.setProperty("password", "devuser1");
		    	resource.setScope("Shareable");
		    	resource.setAuth("Container");
		    	context.getNamingResources().addResource(resource);
	    	}
	    };

	       
	}
	
	@Bean(destroyMethod="")
	@ConfigurationProperties(prefix="ais.db.geico")
	public DataSource dataSource() throws IllegalArgumentException, NamingException {
	    JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
	    bean.setJndiName("java:comp/env/jdbc/GeicoDB");
	    bean.setProxyInterface(DataSource.class);
	    bean.setLookupOnStartup(false);
	    bean.afterPropertiesSet();
	    System.err.println("**** Data source object: " + (DataSource)bean.getObject());
	    return (DataSource)bean.getObject();
	}
	*/
/*
  @Bean(name = "tenantAEntityManagerFactory")
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

}*/