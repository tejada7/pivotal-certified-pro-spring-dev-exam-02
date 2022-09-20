package com.apress.commons;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "/application.yaml", factory = YamlPropertySourceLoader.class)
public class Config {

    private final String moduleName;

    public Config(@Value("${module.name}") final String moduleName) {
        this.moduleName = moduleName;
    }

    public String getModuleName() {
        return moduleName;
    }
}
