package app.persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;


class UserMapperTest {
    private final static String USER = "postgres";
    private final static String PASSWORD = "postgres";
    private final static String URL = "jdbc:postgresql://localhost:5432/Cupcake?currentSchema=test";

    private static DatabaseConnector connector;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testConnection() throws SQLException {
        assertNotNull(connector.getConnection());
    }

    @Test
    void login() {
    }

    @Test
    void createuser() {
    }
}