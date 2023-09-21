package com.demo.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "memcached")
public class MemcachedProperties {

    private String server;

    private int poolsize;

    private long timeout;
}
