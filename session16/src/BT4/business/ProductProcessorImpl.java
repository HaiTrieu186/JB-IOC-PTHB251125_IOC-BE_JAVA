package BT4.business;

import BT4.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductProcessorImpl implements ProductProcessor, IBaseService<Product> {
    private static final List<Product> products = new ArrayList<>();

    @Override
    public double calculateTotalValue(List<Product> products) {
        return products.stream().mapToDouble(Product::getPrice).sum();
    }

    @Override
    public void add(Product product) {
        products.add(product);
    }

    @Override
    public void update(Product product) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getName().equals(product.getName())) {
                products.set(i, product);
                break;
            }
        }
    }

    @Override
    public void delete(Product product) {
        products.removeIf(p -> p.getName().equals(product.getName()));
    }

    @Override
    public Optional<Product> findByName(String name) {
        return products.stream()
                .filter(p -> p.getName().equals(name))
                .findFirst();
    }

    @Override
    public List<Product> findAll() {
        return products;
    }

    public boolean isExistByName(String name) {
        return products.stream()
                .anyMatch(p -> p.getName().equals(name));
    }
}