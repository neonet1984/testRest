package company.service;

import java.io.IOException;

/**
 * Реализация этого интерфейся дает возможность читать файлы
 */
public interface IReader {
    /**
     * Метод читает файл
     *
     * @param pathFile указываеться путь к файлу, для чтения
     * @return метод возвращает строку файла
     */
    String read(String pathFile) throws IOException;
}
