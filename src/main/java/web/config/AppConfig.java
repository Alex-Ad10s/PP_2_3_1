package web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;


@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
@ComponentScan(value = "web")
public class AppConfig {

   @Autowired
   private Environment env;

   @Bean
   public DataSource dataSource() {
      DriverManagerDataSource dataSource = new DriverManagerDataSource();
      dataSource.setDriverClassName(env.getRequiredProperty("db.driver"));
      dataSource.setUrl(env.getProperty("db.url"));
      dataSource.setUsername(env.getProperty("db.username"));
      dataSource.setPassword(env.getProperty("db.password"));
      return dataSource;
   }

   public Properties hibernateProperties() {
      Properties properties = new Properties();
      properties.put("hibernate.hbm2ddl.auto", env.getProperty("db.hibernate.hbm2ddl.auto"));
      properties.put("hibernate.show_sql", env.getProperty("db.hibernate.show_sql"));
      properties.put("hibernate.dialect", env.getProperty("db.hibernate.dialect"));
      return properties;
   }

   @Bean
   public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Autowired DataSource dataSource) {
      LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
      em.setDataSource(dataSource);
      em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
      em.setPackagesToScan("web");
      em.setJpaProperties(hibernateProperties());
      return em;
   }

   @Bean
   public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
      JpaTransactionManager transactionManager = new JpaTransactionManager();
      transactionManager.setEntityManagerFactory(entityManagerFactory);
      return transactionManager;
   }
}