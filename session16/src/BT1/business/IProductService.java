package BT1.business;

import BT1.model.Product;

import java.util.List;

public interface IProductService extends IBaseService<Product, Integer>{
    List<Product> filterByPrice(double price);
    double calculateTotalValue();
}
