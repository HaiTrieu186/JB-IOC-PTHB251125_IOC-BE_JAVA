package BT4.business;


import BT4.model.Product;

import java.util.List;

public interface ProductProcessor{
    double calculateTotalValue(List<Product> products);
    static void printProductList(List<Product> products){
        System.out.println("-- Danh sách sản phẩm ---");

        if(products.isEmpty()){
            System.out.println("Chưa có sản phẩm nào !");
            return;
        }
        products.forEach(System.out::println);
    };

    default boolean hasExpensiveProduct(List<Product> products){
        return products.stream().anyMatch((p) -> Double.compare(p.getPrice(), 100) > 0);
    };

}
