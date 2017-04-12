package com.lozyukartem.configuration;

import org.springframework.context.annotation.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ImportResource({"classpath:hibernate4config.xml", "classpath:dao-beans.xml"})
public class PersistenceConfig {


}
