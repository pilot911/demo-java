package ru.telenok.newspaper.admin

import lombok.extern.slf4j.Slf4j
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.ContextHierarchy
import ru.telenok.newspaper.admin.config.db.DaoTestConfiguration
import ru.telenok.newspaper.admin.initializer.TestContextInitializer
import spock.lang.Specification

import javax.transaction.Transactional

@ContextHierarchy(
        [
                @ContextConfiguration(classes = DaoTestConfiguration.class, initializers = TestContextInitializer.class)
        ]
)
@Transactional
@Slf4j
class CoreTestSpecification extends Specification {}
