import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.domain.ProductManager;
import ru.netology.repository.ProductRepository;
import ru.netology.service.Book;
import ru.netology.service.Product;
import ru.netology.service.Smartphone;

public class ProductManagerTest {
    ProductRepository repository = new ProductRepository();
    ProductManager manager = new ProductManager(repository);

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
    public void emptyListManager() {                              // пустой список менеджер

        Product[] expected = {};
        Product[] actual = manager.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test                                           // вывод всего списка продуктов
    public void groceryListManager() {
        manager.add(product1);
        manager.add(product2);
        manager.add(product3);
        manager.add(product4);
        manager.add(product5);
        manager.add(product6);
        manager.add(product7);
        manager.add(product8);
        manager.add(product9);
        manager.add(product10);

        Product[] expected = {product1, product2, product3, product4, product5, product6, product7, product8, product9, product10};
        Product[] actual = manager.findAll();
        Assertions.assertArrayEquals(expected, actual);


    }

    @Test                                                            // добавляем через менеджер
    public void addProductManager() {

        manager.add(product1);
        manager.add(product5);
        manager.add(product9);
        Product[] expected = {product1, product5, product9};
        Product[] actual = manager.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void searchByName() {                                 // поиск по полному имени
        manager.add(product1);
        manager.add(product3);
        manager.add(product5);
        manager.add(product7);
        manager.add(product8);
        manager.add(product10);
        Product[] expected = {product8};
        Product[] actual = manager.searchBy("C30");
        Assertions.assertArrayEquals(expected, actual);


    }

    @Test
    public void searchByNamePart() {                                 // поиск по части имени
        manager.add(product1);
        manager.add(product3);
        manager.add(product4);
        manager.add(product6);
        manager.add(product8);
        manager.add(product9);
        Product[] expected = {product3};
        Product[] actual = manager.searchBy("Анна");
        Assertions.assertArrayEquals(expected, actual);


    }

    @Test
    public void searchByNameTwoLetters() {                                 // поиск по 2-м буквам/цифрам имени
        manager.add(product3);
        manager.add(product5);
        manager.add(product7);
        manager.add(product8);
        manager.add(product9);
        manager.add(product10);
        Product[] expected = {product7, product10};
        Product[] actual = manager.searchBy("10");
        Assertions.assertArrayEquals(expected, actual);


    }

    @Test                                               // если имя не существует
    public void searchByNoName() {
        manager.add(product1);
        manager.add(product2);
        manager.add(product6);
        manager.add(product4);
        manager.add(product8);
        manager.add(product10);
        Product[] expected = {};
        Product[] actual = manager.searchBy("CI 256");
        Assertions.assertArrayEquals(expected, actual);

    }
}
