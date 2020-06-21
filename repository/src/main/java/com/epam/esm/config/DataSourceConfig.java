package com.epam.esm.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

@Configuration
@PropertySource("classpath:connection.properties")
@RequiredArgsConstructor
public class DataSourceConfig {
    private static final String DB_URL = "db.url";
    private static final String DB_DRIVER = "db.driver";
    private static final String DB_PASSWORD = "db.password";
    private static final String DB_USER_NAME = "db.userName";
    private final Environment env;

    @Bean
    public HikariConfig hikariConfig() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(env.getProperty(DB_URL));
        config.setUsername(env.getProperty(DB_USER_NAME));
        config.setPassword(env.getProperty(DB_PASSWORD));
        config.setDriverClassName(env.getProperty(DB_DRIVER));
        return config;
    }

    @Bean
    public KeyHolder keyHolder() {
        return new GeneratedKeyHolder();
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(new HikariDataSource(hikariConfig()));
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(new HikariDataSource(hikariConfig()));
    }
}