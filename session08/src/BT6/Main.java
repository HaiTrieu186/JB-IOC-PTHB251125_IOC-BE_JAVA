package BT6;

import BT5.Student;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Product[] products = new Product[100];
        int n=0, option=-1;
        do {
            System.out.println("""
                    ==== MENU SẢN PHẨM ====
                    1. Thêm sản phẩm mới
                    2. In danh sách sản phẩm
                    3. Tìm sinh viên theo khoảng giá
                    4. Thống kê số sản phẩm đã tạo
                    0. Thoát
                    =========================""");

            System.out.print("Mời bạn nhập lựa chọn: ");
            option = Integer.parseInt(sc.nextLine());

            switch (option) {
                case 1: {
                    int number;

                    do{
                        System.out.print("Mời bạn nhập số lượng cần thêm: ");
                        number = Integer.parseInt(sc.nextLine());

                        if (number < 0) {
                            System.out.println("Vui lòng nhập số lượng hợp lệ !");
                            continue;
                        }
                        break;
                    }while (true);

                    for (int i = 0; i < number; i++) {
                        Product p = new Product();
                        p.input(sc);

                        products[n++] = p;
                    }

                    break;
                }
                case 2: {
                    System.out.println("\n==== Danh sách sản phẩm ====");
                    printList(products, n);
                    break;
                }
                case 3: {
                    double startRange, endRange;

                    do {
                        System.out.printf("Moời ban nhập giá nhỏ nhất: \n");
                        startRange = Double.parseDouble(sc.nextLine());
                        System.out.printf("Moời ban nhập giá lớn nhất: \n");
                        endRange = Double.parseDouble(sc.nextLine());

                        if (startRange >= endRange ){
                            System.out.println("Lỗi: giá trị bắt đầu phải nhỏ hơn kết thức !");
                            continue;
                        }

                        if (startRange <0 || endRange < 0) {
                            System.out.println("Lỗi: Giá trị không được phép nhỏ hơn không !");
                            continue;
                        }

                        break;

                    }while (true);

                    Product[] matchesProducts = new Product[products.length];
                    int count = 0;

                    for (int i = 0; i < n; i++) {
                        if (products[i].getPrice()>=startRange && products[i].getPrice()<=endRange ) {
                            matchesProducts[count++] = products[i];
                        }
                    }

                    System.out.printf("Danh sách các sản phẩm trong khoảng %f - %f: \n");
                    printList(matchesProducts, count);
                    break;
                }
                case 4: {
                    System.out.printf("Số sản phâm đã tạo: "+ Product.getNumberProduct());
                    break;
                }
                case 0: {
                    System.out.println("Kết thúc chương trình !");
                    sc.close();
                    System.exit(0);
                    break;
                }
                default: {
                    System.out.println("Lựa chọn không hợp lệ (phải chọn 0-4) !");

                }
            }

        } while (true);
    }


    public static void printList(Product[] products, int n){
        System.out.printf("+%s+%s+%s+\n",
                "-".repeat(5),
                "-".repeat(22),
                "-".repeat(17));
        System.out.printf("| %-3s | %-20s | %-15s |\n","ID","NAME","PRICE");
        System.out.printf("+%s+%s+%s+\n",
                "-".repeat(5),
                "-".repeat(22),
                "-".repeat(17));


        if (n==0){
            System.out.printf("|   %-43s|\n", "CHƯA CÓ SẢN PHẨM NÀO !");
        }

        for (int i=0;i<n;i++){
            products[i].print();
        }
        System.out.printf("+%s+%s+%s+\n\n",
                "-".repeat(5),
                "-".repeat(22),
                "-".repeat(17));

    }
}
