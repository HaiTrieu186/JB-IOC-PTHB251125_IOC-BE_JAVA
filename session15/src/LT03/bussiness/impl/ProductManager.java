package LT03.bussiness.impl;

import LT02.model.Subject;
import LT03.bussiness.IProductService;
import LT03.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductManager implements IProductService {
    private List<Product> products= new ArrayList<Product>();

    @Override
    public boolean isExistById(Integer id) {
        for(Product p:products){
            if (p.getId() == id){
                return true;
            }
        }
        return false;
    }

    @Override
    public void add(Product product) {
        products.add(product);
    }

    @Override
    public void update(Product product, Integer id) {
        Product p = findByID(id);

        if  (p == null){
            throw new RuntimeException("Không tìm thấy sản phẩm với id: " + id);
        }

        int index = products.indexOf(p);
        products.set(index, product);
    }

    @Override
    public void delete(Integer id) {
        Product p = findByID(id);
        if (p != null) {
            products.remove(p);
        }
    }

    @Override
    public Product findByID(Integer integer) {
        for (Product product : products) {
            if (product.getId() == integer) {
                return product;
            }
        }
        return null;
    }

    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public void sort() {

    }

    public static void displayList(List<Product> products) {
        if (products.isEmpty()) {
            System.out.println("Chưa có sản phẩm nào !");
        }

        for (Product product : products) {
            product.display();
        }
    }
}
