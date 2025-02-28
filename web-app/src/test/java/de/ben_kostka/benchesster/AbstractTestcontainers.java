package de.ben_kostka.benchesster;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.flywaydb.core.Flyway;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.sql.DataSource;
//see: https://www.youtube.com/watch?v=98t0WIWCnjc&list=PLeIYyC714HvbVGQJ_L3QW-acmFj2tDx1l&index=110 127-133
@Testcontainers
public abstract class AbstractTestcontainers {

    @BeforeAll
    static void beforeAll() {
        Flyway flyway = Flyway.configure()
                .dataSource(
                        mysqlContainer.getJdbcUrl(),
                        mysqlContainer.getUsername(),
                        mysqlContainer.getPassword()
                ).load();
        flyway.migrate();
    }

    @Container
    protected static final MySQLContainer<?> mysqlContainer =
            new MySQLContainer<>("mysql:8.0")
            .withDatabaseName("chessmanagement")
            .withUsername("root")
            .withPassword("root");

    @DynamicPropertySource
    private static void registerDataSourceProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mysqlContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mysqlContainer::getUsername);
        registry.add("spring.datasource.password", mysqlContainer::getPassword);
    }

    protected static DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create()
                .driverClassName(mysqlContainer.getDriverClassName())
                .url(mysqlContainer.getJdbcUrl())
                .username(mysqlContainer.getUsername())
                .password(mysqlContainer.getPassword());

        return dataSourceBuilder.build();
    }
}
