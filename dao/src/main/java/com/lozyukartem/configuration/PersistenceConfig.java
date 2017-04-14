package com.lozyukartem.configuration;

import com.lozyukartem.dao.CommentDao;
import com.lozyukartem.dao.PostDao;
import com.lozyukartem.dao.UserDao;
import com.lozyukartem.dao.impl.CommentDaoImpl;
import com.lozyukartem.dao.impl.PostDaoImpl;
import com.lozyukartem.dao.impl.UserDaoImpl;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
/*@ImportResource({"classpath:hibernate4config.xml"})*/
public class PersistenceConfig {

    @Bean
    public UserDao userDao() {
        return new UserDaoImpl();
    }

    @Bean
    public PostDao postDao() {
        return new PostDaoImpl();
    }

    @Bean
    public CommentDao CommentDao() {
        return new CommentDaoImpl();
    }

    @Bean//3
    public DataSource getDataSource() {
        final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
        dsLookup.setResourceRef(true);
        DataSource dataSource = dsLookup.getDataSource("jdbc/DefaultDB");
        return dataSource;
    }
    @Bean//5
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean localSessionFactoryBean =
                new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(this.getDataSource());
        localSessionFactoryBean.setPackagesToScan("com.lozyukartem.entity");
        localSessionFactoryBean.setHibernateProperties(this.hibProperties());

        return localSessionFactoryBean;
    }


    @Bean//4
    public HibernateTransactionManager transactionManager(
            SessionFactory sessionFactoryBean) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactoryBean);
        return transactionManager;
    }

    //6
    private Properties hibProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect","org.hibernate.dialect.HANARowStoreDialect");
        properties.put("hibernate.hbm2ddl.auto","create");
        properties.put("hibernate.default_schema","ARTEM");

        return properties;
    }

}
