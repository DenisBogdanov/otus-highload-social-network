package com.bogdanium.social.network.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DbConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource masterDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.slave-datasource")
    public DataSource slaveDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public NamedParameterJdbcTemplate jdbcTemplate(DataSource masterDataSource) {
        return new NamedParameterJdbcTemplate(masterDataSource);
    }

    @Bean
    public NamedParameterJdbcTemplate slaveJdbcTemplate(DataSource slaveDataSource) {
        return new NamedParameterJdbcTemplate(slaveDataSource);
    }
}
