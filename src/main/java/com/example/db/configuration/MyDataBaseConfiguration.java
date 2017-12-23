package com.example.db.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.example.db.enuma.DatabaseType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class MyDataBaseConfiguration {
    private Logger logger = LoggerFactory.getLogger(MyDataBaseConfiguration.class);

    @Autowired
    Protis1 protis1;

    @Autowired
    Protis2 protis2;

    @Autowired
    Protis3 protis3;

    @Bean(name = "dataSource1")
    public DataSource writeDataSource1() throws SQLException {
        System.out.println("注入druid1！！！");
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(protis1.getUrl());
        datasource.setDriverClassName("com.mysql.jdbc.Driver");
        datasource.setUsername(protis1.getUsername());
        datasource.setPassword(protis1.getPassword());
        return datasource;
    }


    @Bean(name = "dataSource2")
    public DataSource writeDataSource2() throws SQLException {
        System.out.println("注入druid2！！！");
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(protis2.getUrl());
        datasource.setDriverClassName("com.mysql.jdbc.Driver");
        datasource.setUsername(protis2.getUsername());
        datasource.setPassword(protis2.getPassword());
        return datasource;
    }


    @Bean(name = "dataSource3")
    public DataSource writeDataSource3() throws SQLException {
        System.out.println("注入druid3！！！");
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(protis3.getUrl());
        datasource.setDriverClassName("com.mysql.jdbc.Driver");
        datasource.setUsername(protis3.getUsername());
        datasource.setPassword(protis3.getPassword());
        return datasource;
    }


    /**
     * @Primary 该注解表示在同一个接口有多个实现类可以注入的时候，默认选择哪一个，而不是让@autowire注解报错
     * @Qualifier 根据名称进行注入，通常是在具有相同的多个类型的实例的一个注入（例如有多个DataSource类型的实例）
     */
    @Bean
    @Primary
    public DynamicDataSource dataSource(@Qualifier("dataSource1") DataSource dataSource1,
                                        @Qualifier("dataSource2") DataSource dataSource2,
                                        @Qualifier("dataSource3") DataSource dataSource3) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DatabaseType.mybd1, dataSource1);
        targetDataSources.put(DatabaseType.mybd2, dataSource2);
        targetDataSources.put(DatabaseType.mybd3, dataSource3);

        DynamicDataSource dataSource = new DynamicDataSource();
        // 该方法是AbstractRoutingDataSource的方法
        //可通过determineCurrentLookupKey方法指定相应的key(DatabaseType.mybd2)
        dataSource.setTargetDataSources(targetDataSources);
        dataSource.setDefaultTargetDataSource(dataSource1);// 默认的datasource设置为dataSource1

        return dataSource;
    }

    /**
     * 根据数据源创建SqlSessionFactory
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory(DynamicDataSource ds) throws Exception {
        SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
        fb.setDataSource(ds);// 指定数据源(这个必须有，否则报错)
        // 下边两句仅仅用于*.xml文件，如果整个持久层操作不需要使用到xml文件的话（只用注解就可以搞定），则不加
        fb.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath:mapping/**/*Mapper.xml"));
        fb.setConfigLocation(new ClassPathResource("mybatis-config.xml"));

        return fb.getObject();
    }

}
