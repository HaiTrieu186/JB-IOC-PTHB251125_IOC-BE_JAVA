package BT4;
import java.util.Scanner;

public class Main {
    private static final OrderManager orderManager = new OrderManager();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("""
                    ------------- MENU QUẢN LÝ ĐƠN HÀNG -------------
                    1. Thêm đơn hàng
                    2. Sửa đơn hàng
                    3. Xóa đơn hàng
                    4. Hiển thị danh sách đơn hàng
                    5. Thoát
                    """);
            System.out.print("Mời bạn lựa chọn (1-5): ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:{
                    Order order= new Order();
                    order.inputData(sc);

                    if (!orderManager.isExistId(order.getOrderId())) {

                        orderManager.add(order);
                        System.out.println("Hóa đơn đã được thêm thành công !");
                        break;
                    }

                    System.out.println("ID đơn hàng đã tồn tại vui lòng nhập lại !");
                    String id;

                    do {
                        System.out.print("Mời bạn nhập id: ");
                        id=sc.nextLine();

                        if (orderManager.isExistId(id)){
                            System.out.println("ID Đã tồn tại vui lòng nhập lại !");
                        }else {
                            order.setOrderId(id);
                            orderManager.add(order);
                            System.out.println("Đơn hàng đã được thêm thành công !");
                            break;
                        }

                    }while (true);

                    break;}
                case 2:{
                    System.out.println("------- DANH SÁCH HIỆN TẠI -------");
                    orderManager.display();

                    System.out.print("Mời bạn nhập id cần sửa: ");
                    String id=sc.nextLine();

                    if (!orderManager.isExistId(id)){
                        System.out.println("ID không tồn tại");
                    } else{
                        String newCustomerName;
                        int index=orderManager.getIndexById(id);

                        while (true){
                            System.out.println("Mời bạn nhập tên khách hàng mới: ");
                            newCustomerName=sc.nextLine();

                            if (newCustomerName.isBlank()){
                                System.out.println("Vui lòng không để trống !");
                                continue;
                            }
                            break;
                        }

                        Order order=new Order();
                        order.setOrderId(id);
                        order.setCustomerName(newCustomerName);
                        orderManager.update(index, order);


                        System.out.println("Hóa đơn đã được sửa thành công !");

                    }

                    break;}
                case 3:{
                    System.out.println("------- DANH SÁCH HIỆN TẠI -------");
                    orderManager.display();

                    System.out.print("Mời bạn nhập id cần xóa: ");
                    String id=sc.nextLine();

                    if (!orderManager.isExistId(id)){
                        System.out.println("ID không tồn tại");
                        break;
                    } else{
                        orderManager.delete(orderManager.getIndexById(id));
                    }
                    break;}
                case 4:{
                    if (orderManager.getOrders().isEmpty()){
                        System.out.println("Chưa có hóa đơn nào !");
                        break;
                    }

                    System.out.println("-------- DANH SÁCH HIỆN TẠI --------");
                    orderManager.display();
                    break;}
                case 5:{
                    System.out.println("Thoát chương trình!");
                    sc.close();
                    System.exit(0);
                }
                default:{
                    System.out.println("Vui lòng chọn lựa chọn hợp lệ");
                }
            }
        }
    }
}
