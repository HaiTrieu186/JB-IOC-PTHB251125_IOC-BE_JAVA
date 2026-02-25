package BT1.business.impl;

import BT1.business.IProductService;
import BT1.model.Product;

import java.util.*;
import java.util.stream.Collectors;

public class ProductManager implements IProductService {
    private final Map<Integer, Product> productList= new HashMap<>();

    public static void displayList(List<Product> all) {
        if (all.isEmpty()) {
            System.out.println("Chưa có sản phầm nào !");
        } else {

            for (Product p : all) {
                p.display();
            }
        }
    }

    @Override
    public List<Product> filterByPrice(double price) {
        return productList.values().stream().filter(p -> {
            return Double.compare(p.getPrice(), price) >=0;
        }).collect(Collectors.toList());}

    @Override
    public double calculateTotalValue() {
        return productList.values().stream().mapToDouble(Product::getPrice).sum();
    }

    @Override
    public void add(Product product) {
        this.productList.put(product.getId(), product);
    }

    @Override
    public void update(Product product) {
        this.productList.put(product.getId(), product);
    }

    @Override
    public void delete(Integer id) {
        this.productList.remove(id);
    }

    @Override
    public Optional<Product> findById(Integer id) {
        return Optional.ofNullable(productList.get(id));
    }


    @Override
    public List<Product> findAll() {
        return this.productList.values().stream().toList();
    }

    @Override
    public void sort() {

    }

    public boolean isExistById(Integer id){
        return productList.containsKey(id);
    }


}
