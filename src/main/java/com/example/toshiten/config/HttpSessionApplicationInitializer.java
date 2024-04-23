package com.example.toshiten.config;

import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

/**
 * @see <a href="https://docs.spring.io/spring-session/reference/3.0/guides/java-redis.html#_java_servlet_container_initialization">Spring Session Documentation</a>
 */
public class HttpSessionApplicationInitializer extends AbstractHttpSessionApplicationInitializer {

    public HttpSessionApplicationInitializer() {
        super(RedisSessionConfig.class);
    }

}