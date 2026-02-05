package BT4;

import java.util.ArrayList;
import java.util.List;

public class OrderManager implements Manage<Order>{
    private static List<Order> orders=new ArrayList<Order>();

    @Override
    public void add(Order item) {
        orders.add(item);
    }

    @Override
    public void update(int index, Order item) {
        orders.set(index, item);
    }

    @Override
    public void delete(int index) {
        if (index >= 0 && index < orders.size()) {
            orders.remove(index);
            System.out.println("Hóa đơn đã được xóa thành công !");
        } else {
            System.out.println("Không tồn tại vị trí cần xóa !");
        }
    }

    @Override
    public void display() {
        int i=1;
        for (Order item : orders) {
            System.out.print((i++)+". ");
            item.outputData();
        }
    }

    public List<Order> getOrders() {
        return orders;
    }

    public int getIndexById(String id){
        for(int i=0;i<orders.size();i++){
            if (orders.get(i).getOrderId().equals(id)){
                return i;
            }
        }
        return -1;
    }

    public boolean isExistId(String id){
        for (Order order : orders) {
            if (order.getOrderId().equals(id)) {
                return true;
            }
        }
        return false;
    }
}
