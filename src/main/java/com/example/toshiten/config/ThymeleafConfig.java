package com.example.toshiten.config;

import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ThymeleafConfig {

    /**
     * @see <a href="https://ultraq.github.io/thymeleaf-layout-dialect/getting-started/#usage">Thymeleaf Layout Dialect Documentation</a>
     */
    @Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect();
    }

}
