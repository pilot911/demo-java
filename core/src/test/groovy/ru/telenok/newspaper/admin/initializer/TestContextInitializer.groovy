package ru.telenok.newspaper.admin.initializer

import org.springframework.boot.env.YamlPropertySourceLoader
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.core.env.PropertySource
import org.springframework.core.io.Resource

class TestContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    private final static String YML = "classpath:application.yml";

    @Override
    void initialize(ConfigurableApplicationContext applicationContext) {
        try {
            Resource resource = applicationContext.getResource(YML)
            YamlPropertySourceLoader sourceLoader = new YamlPropertySourceLoader()
            List<PropertySource<?>> yamlTestProperties = sourceLoader.load("applicationProperties", resource)
            applicationContext.getEnvironment().getPropertySources().addFirst(yamlTestProperties.first())
            String[] profiles = applicationContext.getEnvironment().getProperty("spring.profiles.active").replaceAll(" ", "").split(",")
            applicationContext.getEnvironment().setActiveProfiles(profiles)
        } catch (IOException e) {
            throw new RuntimeException(e)
        }
    }
}