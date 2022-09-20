package com.apress.commons;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;

@DisplayNameGeneration(ReplaceUnderscores.class)
class YamlPropertySourceLoaderTest {

    @Test
    void should_read_properties_from_yaml_file() {
        // Given
        final var applicationContext = new AnnotationConfigApplicationContext(Config.class);
        applicationContext.registerShutdownHook();

        // When
        final var property = applicationContext.getBean(Environment.class).getProperty("module.name");

        // Then
        then(property).isEqualTo("commons");
        applicationContext.close();
    }
}
