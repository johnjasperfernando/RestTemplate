package com.restTemplateExample.RestTemplate.Appconfig;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

//@Configuration
//@EnableTransactionManagement
public class DataSourceConfig {

    @Bean("db")
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource dataSource()
    {
        DataSourceBuilder dataSourceBuilder=DataSourceBuilder.create();
        return DataSourceBuilder.create().build();
    }

    @Bean(name="jdbcTemplate")
    public JdbcTemplate jdbcTemplate(@Qualifier("db")DataSource jdbcdataSource)
    {
        return jdbcTemplate((DataSource) jdbcdataSource);
    }

    @Bean(name="jdbcNamedTemplate")
    public NamedParameterJdbcTemplate jdbcNamedTemplate(@Qualifier("db")DataSource jdbcNameddataSource)
    {
        return new NamedParameterJdbcTemplate((DataSource) jdbcNameddataSource);
    }


}
