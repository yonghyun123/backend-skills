package com.commento.cleanair.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;


@Configurable
public class LocalCacheConfig extends CachingConfigurerSupport {
    // 이게 필요한건가...?
    // 왜 있어야하는지 모르겠다
    @Bean
    public EhCacheManagerFactoryBean getEhCacheFactory(){
        EhCacheManagerFactoryBean factoryBean = new EhCacheManagerFactoryBean();
        factoryBean.setConfigLocation(new ClassPathResource("ehcache-config.xml"));
        factoryBean.setShared(true);
        return factoryBean;
    }
}
