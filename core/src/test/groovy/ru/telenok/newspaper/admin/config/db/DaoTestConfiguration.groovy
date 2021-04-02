package ru.telenok.newspaper.admin.config.db

import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Import

@Import([
        EmbeddedDBTestConfiguration.class // Настройка DataSource for RDBMS
])
@TestConfiguration
class DaoTestConfiguration {
}
