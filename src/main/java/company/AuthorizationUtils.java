package company;

import company.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * Класс испльзуетья для отсылки авторизационных на сервере
 */
public class AuthorizationUtils {
    /**
     * Метод отсылает авторизационные данные на сервер
     *
     * @param login    логин пользователя
     * @param password пароль пользователя
     * @return возвращает объект ResponseEntity, для дальнейшей работы над ним
     */
    public static ResponseEntity getAuthorization(String login, String password) {
        return new RestTemplate().postForEntity(RecuestUrl.URL_LOGIN,
                new User(login, password), Object.class);
    }
}
