package LT3;

import LT2.Computer;
import LT2.NetworkDevice;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Drink[] drinks = new Drink[100];
        int n=0, choice=-1;

        do {
            System.out.println("""
                    ------------ MENU ------------
                    1. Thêm món vào menu
                    2. Hiển thị menu
                    3. Áp dụng mã giảm giá
                    4. Xóa món
                    5. Thống kê:
                    6. Thoát
                    """);
            System.out.print("Mời bạn nhập lựa chọn (1-6): ");
            choice= Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:{
                    if (n==100) {
                        System.out.println("Đã đầy, không thể thêm mới !");
                        break;
                    }

                    int c=0;
                    do {
                        System.out.println("\n-- Mời bạn chọn loại nước ---");
                        System.out.println("1. Coffee (Cà phê)");
                        System.out.println("2. Fruitea (Trà trái cây)");
                        System.out.print("Mời bạn chọn loại: ");
                        c=Integer.parseInt(sc.nextLine());

                        if (c!=1 && c!=2) {
                            System.out.println("Vui lòng chọn lựa chọn hợp lệ");
                        } else
                            break;
                    }while (true);

                    Drink newDrink;
                    if (c==1){
                        newDrink=new Coffee();
                        newDrink.inputData(sc);

                        drinks[n++]=newDrink;
                    } else{
                        newDrink=new FruitTea();
                        newDrink.inputData(sc);

                        drinks[n++]=newDrink;
                    }

                    System.out.println("Thêm nước thành công !");

                    break;
                }
                case 2:{
                    if  (n==0) {
                        System.out.println("Chưa có món nào trong menu");
                        break;
                    }

                    System.out.println("---------- Danh sách menu ! ----------");

                    for (int i=0;i< n;i++){
                        System.out.println("--- Món thứ "+ (i+1)+" --- ");
                        System.out.println("ID: "+ drinks[i].getId());
                        System.out.println("Tên món: "+ drinks[i].getName());
                        System.out.println("Giá: " + drinks[i].getPrice());
                    }

                    System.out.println();
                    break;}
                case 3:{
                    int discount;

                    do {
                        System.out.println("Mời bạn nhập giảm giá (0-100%): ");
                        discount=Integer.parseInt(sc.nextLine());

                        if(discount>100|| discount<0) {
                            System.out.println("Vui lòng nhập hợp lệ (0-100%) !");
                        } else
                            break;
                    }while (true);


                    System.out.println("---------- Danh sách menu sau giảm ! ----------");

                    for (int i=0;i< n;i++){
                        System.out.println("--- Món thứ "+ (i+1)+" --- ");
                        System.out.println("ID: "+ drinks[i].getId());
                        System.out.println("Tên món: "+ drinks[i].getName());
                        System.out.print("Cách pha chế: ");
                        drinks[i].prepare();
                        System.out.println("Giá (gốc): " + drinks[i].getPrice());
                        System.out.println("Giá (giảm giá): " + drinks[i].applyDiscount((double) discount /100));
                    }

                    break;}
                case 4:{
                    int index;
                    do {
                        System.out.println("Mời bạn nhập id cần xóa: ");
                        index=Integer.parseInt(sc.nextLine());

                        if (index<0 || index>n) {
                            System.out.println("Index không hợp lệ");
                        } else
                            break;
                    }while (true);

                    int indexFound = -1;
                    for (int i = 0; i < n; i++) {
                        if (drinks[i].getId() == index) {
                            indexFound = i;
                            break;
                        }
                    }


                    if (indexFound == -1) {
                        System.out.println("Không tìm thấy món có ID = " + index);
                    } else {
                        for (int i = indexFound; i < n - 1; i++) {
                            drinks[i] = drinks[i + 1];
                        }
                        n--;
                        drinks[n] = null;
                        System.out.println("Đã xóa thành công món ID " + index);}

                    break;}
                case 5:{
                    double sumPrice=0;

                    for (int i=0;i<n;i++){
                        sumPrice+=drinks[i].getPrice();
                    }

                    System.out.printf("Trung bình giá tiền của tất cả đồ uống là: %.2f", (double)(sumPrice/n));

                    break;}
                case 6:{
                    System.out.println("Thoát chương trình !");
                    sc.close();
                    System.exit(0);
                }
                default:{
                    System.out.println("VUi lòng chọn lựa chọn hợp lệ ! (1-6)!");
                };}
        }while (true);


    }
}
