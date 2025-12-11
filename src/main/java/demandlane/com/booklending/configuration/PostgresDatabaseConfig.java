package demandlane.com.booklending.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class PostgresDatabaseConfig {


    


     @Primary
     @Bean(name = "spring")
     @ConfigurationProperties(prefix = "spring.datasource")
    public HikariDataSource dataSource() {
        HikariDataSource dataSource = DataSourceBuilder.create().type(HikariDataSource.class)
        .build();
        return dataSource;
    }

}
