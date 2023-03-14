import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.exception.NotFoundException;
import ru.netology.repository.ProductRepository;
import ru.netology.service.Book;
import ru.netology.service.Product;
import ru.netology.service.Smartphone;

public class ProductRepositoryTest {
    ProductRepository repository = new ProductRepository();
    Product product1 = new Book(2502, "Метро 2033", 980, "Дмитрий Глуховский");
    Product product2 = new Book(2596, "Тёмные аллеи", 1000, "Иван Бунин");
    Product product3 = new Book(258, "Анна Каренина", 1250, "Лев Толстой");
    Product product4 = new Book(6589, "Ночной дозор", 850, "Сергей Лукьяненко");
    Product product5 = new Book(1448, "Во мрак", 500, "Андрей Дьяков");
    Product product6 = new Smartphone(658, "Galaxy A23 ", 17990, "Samsung");
    Product product7 = new Smartphone(2574, "10X Lite", 13995, "Honor");
    Product product8 = new Smartphone(3569, "C30", 6990, "Realme");
    Product product9 = new Smartphone(1205, "nova Y70", 10990, "Huawei");
    Product product10 = new Smartphone(1098, "Redmi 10C", 9990, "Xiaomi");

    @Test
    public void removeExceptionById() {                          // удаление по Id с использованием findById
        repository.save(product1);
        repository.save(product3);
        repository.save(product4);
        repository.save(product6);
        repository.save(product7);
        repository.save(product9);

        repository.deleteById(258);
        repository.deleteById(1205);

        Product[] expected = {product1, product4, product6, product7};
        Product[] actual = repository.findAll();

        Assertions.assertArrayEquals(expected, actual);


    }

    @Test
    public void removeWrongIdException() {     // ошибка если пытаемся удалить не тот Id
        repository.save(product1);
        repository.save(product3);
        repository.save(product4);
        repository.save(product6);
        repository.save(product7);
        repository.save(product9);

        Assertions.assertThrows(NotFoundException.class, () -> {
            repository.deleteById(2590);
        });
    }

    @Test
    public void searchById() {                              // проверяем ищет ли по Id?
        repository.save(product3);
        repository.save(product4);
        repository.save(product5);
        repository.save(product6);
        repository.save(product8);
        repository.save(product9);


        Product[] expected = {product9, product3};
        Product[] actual = new Product[]{repository.findById(1205), repository.findById(258)};

        Assertions.assertArrayEquals(expected, actual);


    }

    // Предыдущие тесты проходят
    @Test
    public void emptyListRepo() {                              // пустой список

        Product[] expected = {};
        Product[] actual = repository.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test                                           // вывод всего списка продуктов
    public void groceryListRepo() {
        repository.save(product1);
        repository.save(product2);
        repository.save(product3);
        repository.save(product4);
        repository.save(product5);
        repository.save(product6);
        repository.save(product7);
        repository.save(product8);
        repository.save(product9);
        repository.save(product10);

        Product[] expected = {product1, product2, product3, product4, product5, product6, product7, product8, product9, product10};
        Product[] actual = repository.findAll();
        Assertions.assertArrayEquals(expected, actual);


    }

    @Test
    public void addProductRepo() {                           // добавляем
        repository.save(product2);
        repository.save(product5);
        repository.save(product10);
        Product[] expected = {product2, product5, product10};
        Product[] actual = repository.findAll();
        Assertions.assertArrayEquals(expected, actual);


    }

    @Test
    public void deleteItemById() {                              // удаляем по Id
        repository.save(product1);
        repository.save(product4);
        repository.save(product7);
        repository.save(product9);
        repository.save(product10);

        repository.deleteById(2502);
        repository.deleteById(2574);
        repository.deleteById(1098);

        Product[] expected = {product4, product9};
        Product[] actual = repository.findAll();
        Assertions.assertArrayEquals(expected, actual);

    }

}
