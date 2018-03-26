package test_suite.test_get_books;

import company.ListBooksType;
import company.model.Book;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import company.service.IReader;
import company.service.impl.ReaderService;
import company.AuthorizationUtils;
import company.RecuestUrl;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * Тест проверяет корректность полученных книг, от сервера
 */
public class TestGetBooks {
    private IReader readerService = new ReaderService();
    private HttpEntity<String> header;

    /**
     * Метод делает авторизацию на сервере
     *
     * @param login    логин пользователя
     * @param password пароль пользователя
     */

    @Parameters({"login", "password"})
    @BeforeClass
    public void init(String login, String password) {
        String token = AuthorizationUtils.getAuthorization(login, password).getHeaders().getFirst("X-Auth-Token");
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Auth-Token", token);
        header = new HttpEntity<>(headers);
    }

    /**
     * Метод тестирует корректность полученных книг, от сервера
     *
     * @param pathToExpectDataBooks путь к файлу, хранящий ожидаемый результат от сервера
     */
    @Test
    @Parameters({"pathToExpectDataBooks"})
    public void testGetBooks(String pathToExpectDataBooks) throws IOException {
        ResponseEntity<List<Book>> responseEntity =
                new RestTemplate().exchange(RecuestUrl.URL_BOOKS, HttpMethod.GET, header,
                        new ListBooksType());
        String expect = readerService.read(pathToExpectDataBooks);
        String actual = responseEntity.getBody().toString();
        assertEquals(actual, expect);
        assertEquals(responseEntity.getStatusCode().value(), 200);

    }
}
