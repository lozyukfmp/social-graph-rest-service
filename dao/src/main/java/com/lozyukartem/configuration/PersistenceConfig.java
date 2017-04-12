package com.lozyukartem.configuration;

import com.lozyukartem.dao.CommentDao;
import com.lozyukartem.dao.PostDao;
import com.lozyukartem.dao.UserDao;
import com.lozyukartem.dao.impl.CommentDaoImpl;
import com.lozyukartem.dao.impl.PostDaoImpl;
import com.lozyukartem.dao.impl.UserDaoImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ImportResource({"classpath:hibernate4config.xml"})
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

}
