package com.example.config.dbConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


@Configuration
public class DataSourceConfiguration {

    private static Logger log = LoggerFactory.getLogger(DataSourceConfiguration.class);

    @Value("${mysql.datasource.type}")
    private Class<? extends DataSource> dataSourceType;


    //写库
    @Bean("writeDataSource")
    @ConfigurationProperties(prefix = "mysql.datasource.write")
    public DataSource writeDataSource(){
        log.info("-------------------- writeDataSource init ---------------------");
        return DataSourceBuilder.create().type(dataSourceType).build();
    }


    //读库
    @Bean(name = "readDataSource")
    @ConfigurationProperties(prefix = "mysql.datasource.read")
    public DataSource readDataSourceOne() {
        log.info("-------------------- read DataSourceOne init ---------------------");
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

}
