package com.demo.config;

import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class MemcachedConfig {

    @Autowired
    MemcachedProperties memcachedProperties;

    @Bean
    public net.rubyeye.xmemcached.MemcachedClient memcachedClient(){
        MemcachedClientBuilder memcachedClientBuilder = new XMemcachedClientBuilder(memcachedProperties.getServer());
        memcachedClientBuilder.setConnectionPoolSize(memcachedProperties.getPoolsize());
        memcachedClientBuilder.setConnectTimeout(memcachedProperties.getTimeout());
        net.rubyeye.xmemcached.MemcachedClient memcachedClient = null;
        try {
            memcachedClient = memcachedClientBuilder.build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return memcachedClient;
    }

}
