package demandlane.com.booklending.configuration;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class PostgresDatabaseConfig {

    @Autowired
    Environment environment;

    


     @Primary
     @Bean(name = "spring")
     @ConfigurationProperties(prefix = "spring.datasource")
    public HikariDataSource dataSource() {
        HikariDataSource dataSource = DataSourceBuilder.create().type(HikariDataSource.class)
        .build();
        //dataSource.setMaximumPoolSize(Integer.parseInt(environment.getProperty("spring.datasource.hikari.MaxPoolSize")));
       // dataSource.setMinimumIdle(Integer.parseInt(environment.getProperty("spring.datasource.hikari.minIdle")));
        dataSource.setPoolName(environment.getProperty("spring.datasource.hikari.poolName"));
        //dataSource.setConnectionTimeout(Long.parseLong(environment.getProperty("spring.datasource.hikari.connectionTimeout")));
       // dataSource.setIdleTimeout(Long.parseLong(environment.getProperty("spring.datasource.hikari.idleTimeout")));
       // dataSource.setMaxLifetime(Long.parseLong(environment.getProperty("spring.datasource.hikari.maxLifetime")));
        return dataSource;
    }

}
