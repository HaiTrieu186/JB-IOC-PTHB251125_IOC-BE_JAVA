package LT03.bussiness;

import LT03.model.Product;

public interface IProductService extends IBaseService<Product, Integer> {
    boolean isExistById(Integer id);


}
