package test_suite.test_security_module;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import company.AuthorizationUtils;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Тест проверяет на возможность авторизации на сервере,
 * при валиидных и не валидных, авторизационных данных
 */
public class TestSecurity {

    /**
     * DataProvider хранит список не валидных авторизационных данных
     *
     * @return массив объектов не валидных авторизационных данных
     */
    @DataProvider(name = "AuthenticationError")
    public static Object[][] usersNotValid() {
        return new Object[][]{
                {"testuser_1", "Test@123"},
                {"testuser_1", "Test@123"},
                {"test_user123", "Test1234@mail"}
        };
    }

    /**
     * DataProvider хранит список  валидных авторизационных данных
     *
     * @return массив объектов  валидных авторизационных данных
     */
    @DataProvider(name = "AuthenticationValid")
    public static Object[][] usersValid() {
        return new Object[][]{
                {"user", "user"},
        };
    }

    /**
     * Этот тест проверяет возможность авторизации с не валидными данными
     */
    @Test(dataProvider = "AuthenticationError", expectedExceptions = HttpClientErrorException.class)
    public void testNotValidUser(String login, String password) {
        AuthorizationUtils.getAuthorization(login, password);
    }

    /**
     * Этот тест проверяет возможность авторизации с валидными данными
     */
    @Test(dataProvider = "AuthenticationValid")
    public void testValidUser(String login, String password) {
        ResponseEntity response = AuthorizationUtils.getAuthorization(login, password);
        String token = response.getHeaders().getFirst("X-Auth-Token");
        assertEquals(response.getStatusCode().value(), 200);
        assertTrue(token != null);
    }
}
