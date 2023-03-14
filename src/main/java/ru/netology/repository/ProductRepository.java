package ru.netology.repository;

import ru.netology.exception.NotFoundException;
import ru.netology.service.Product;

public class ProductRepository {
    private Product[] items = new Product[0];

    public Product findById(int id) {                                   // поиск по Id
        for (Product item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public void deleteById(int id) {
        if (findById(id) == null) {
            throw new NotFoundException("Element with id " + id + " not found.");            // исключение
        }                                                                                  // удаляем по Id
        Product[] tmp = new Product[items.length - 1];
        int copyToIndex = 0;
        for (Product item : items) {
            if (item.getId() != id) {
                tmp[copyToIndex] = item;
                copyToIndex++;
            }
        }
        items = tmp;
    }

    public Product[] findAll() {                                // выводим
        return items;
    }

    public void save(Product item) {                            // добавляем
        Product[] tmp = new Product[items.length + 1];
        for (int i = 0; i < items.length; i++) {
            tmp[i] = items[i];
        }
        tmp[tmp.length - 1] = item;
        items = tmp;
    }

}
