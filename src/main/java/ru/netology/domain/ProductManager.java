package ru.netology.domain;

import ru.netology.repository.ProductRepository;
import ru.netology.service.Product;

public class ProductManager {
    protected ProductRepository repository;

    public ProductManager(ProductRepository repository) {
        this.repository = repository;
    }


    public void add(Product product) {                        // добавляем
        repository.save(product);
    }

    public Product[] findAll() {
        return repository.findAll();
    }


    public Product[] searchBy(String text) {                     // ищем по тексту
        Product[] result = new Product[0];
        for (Product product : repository.findAll()) {
            if (matches(product, text)) {
                Product[] tmp = new Product[result.length + 1];
                System.arraycopy(result, 0, tmp, 0, result.length);
                tmp[tmp.length - 1] = product;
                result = tmp;
            }
        }
        return result;
    }

    public boolean matches(Product product, String search) {
        if (product.getName().contains(search)) {
            return true;
        } else {
            return false;
        }
    }
}
