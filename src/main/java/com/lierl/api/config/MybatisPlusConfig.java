package com.lierl.api.config;

import com.baomidou.mybatisplus.enums.DBType;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by lierl on 2017/6/25.
 */
@Configuration
@MapperScan("com.lierl.api.mapper*")
public class MybatisPlusConfig {
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setDialectType(DBType.MYSQL.getDb());
        return paginationInterceptor;
    }

//    @Bean
//    public SqlSessionFactory sqlSessionFactory() throws Exception {
//        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
//        PaginationInterceptor pagination = new PaginationInterceptor();
//        // 具体参考自己设置，参考 xml 参数说明或源码注释
//        sqlSessionFactory.setPlugins(new Interceptor[]{
//                pagination
//        });
//        return sqlSessionFactory.getObject();
//    }
}
