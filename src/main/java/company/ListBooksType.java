package company;

import company.model.Book;
import org.springframework.core.ParameterizedTypeReference;

import java.util.List;

public class ListBooksType extends ParameterizedTypeReference<List<Book>> {
}
