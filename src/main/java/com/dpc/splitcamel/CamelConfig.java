package com.dpc.splitcamel;

import org.apache.camel.CamelContext;
import org.apache.camel.spi.RestConfiguration;
import org.apache.camel.spring.boot.CamelContextConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class CamelConfig {
    @Bean
    CamelContextConfiguration contextConfiguration() {
        return new CamelContextConfiguration() {
            @Override
            public void beforeApplicationStart(CamelContext camelContext) {
                RestConfiguration rest = new RestConfiguration();
                camelContext.setRestConfiguration(rest);
                rest.setComponent("jetty");
                rest.setPort(5000);
                rest.setBindingMode(RestConfiguration.RestBindingMode.json);
                rest.setDataFormatProperties(new HashMap<>());
                rest.getDataFormatProperties().put("prettyPrint", "true");
            }

            @Override
            public void afterApplicationStart(CamelContext camelContext) {
                // empty
            }
        };
    }
}