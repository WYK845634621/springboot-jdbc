package com.yikai.springboot.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author wangyikai
 * @Date 2018/9/18 - 10:30
 */
@Configuration
public class DruidConfig {
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druid(){
        return  new DruidDataSource();
    }

//    配置Druid的监控,先配置一个管理后台是Servlet
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
        Map<String,String> initParamers = new HashMap<>();
        initParamers.put("loginUsername","admin");
        initParamers.put("loginPassword","123456");
        bean.setInitParameters(initParamers);
        return  bean;
    }

//    再配置一个filter
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
        Map<String,String> initParamers = new HashMap<>();
        initParamers.put("exclusions","*.js,*.css,/druid/*");
        bean.setInitParameters(initParamers);
        bean.setUrlPatterns(Arrays.asList("/*"));
        return  bean;
    }
}
