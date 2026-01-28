package BT6;

import BT5.Animal;
import BT5.Cat;
import BT5.Dog;
import BT5.Elephant;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int MAX=0, option=-1, totalVehicle=0;
        do{
            System.out.print("Mời bạn nhập số lượng tối đa lưu trữ: ");
            MAX= Integer.parseInt(sc.nextLine());

            if (MAX<=0) continue;
            break;

        }while (true);

        Vehicle[] vehicles = new Vehicle[MAX];

        // Do đề yêu cầu tạo trước.
        vehicles[totalVehicle++]= new Car("Toyota",2020,"Gasoline");
        vehicles[totalVehicle++]= new Motorcycle("Honda",2018,"Electric");
        vehicles[totalVehicle++]= new Truck("Volvo",2022,"Diesel");

        do {
            System.out.println("""
                    ===== ZOO MANAGEMENT MENU =====
                    1. Tạo đối tượng và hiển thị thông tin
                    2. Kiểm tra Overriding: makeSound() 
                    3. Kiêm tra Overloading: eat()
                    4. Kiểm tra đa hình runtime (Animal array)
                    5. Gọi phương thức đặc trưng từng loài
                    0. Thoát
                    ================================ 
                    """);

            System.out.print("Mời bạn nhập lựa chọn: ");
            option = Integer.parseInt(sc.nextLine());

            switch (option) {
                case 1:{
                    System.out.println("\n----- Thông tin phương tiện -----");
                    for (int i = 0; i < totalVehicle; i++) {
                        vehicles[i].showInfo();
                    }
                    System.out.println();
                    break;}
                case 2:{
                    System.out.println("\n----- Overriding:  startEngine() -----");
                    for (int i = 0; i < totalVehicle; i++) {
                        vehicles[i].startEngine();
                    }
                    System.out.println();
                    break;}
                case 3:{
                    System.out.println("\n----- Overloading: move() -----");
                    for (int i = 0; i < totalVehicle; i++) {
                        vehicles[i].move();
                        vehicles[i].move(80);
                    }
                    System.out.println();
                    break;}
                case 4:{
                    System.out.println("\n----- Polymorphism runtime -----");
                    for (int i = 0; i < totalVehicle; i++) {
                        vehicles[i].startEngine();
                    }
                    System.out.println();
                    break;}
                case 5:{
                    System.out.println("\n----- Phương thức riêng của từng lòa -----");
                    for (int i = 0; i < totalVehicle; i++) {

                        if (vehicles[i] instanceof Car) {
                           Car car = (Car) vehicles[i];
                           car.openTrunk();
                        } else
                        if (vehicles[i] instanceof Motorcycle) {
                            Motorcycle motorcycle = (Motorcycle) vehicles[i];
                            motorcycle.doWheelie();
                        } else
                        if (vehicles[i] instanceof Truck) {
                           Truck truck = (Truck) vehicles[i];
                           truck.loadCargo();
                        }

                    }
                    System.out.println();
                    break;}
                case 0:{
                    sc.close();
                    System.out.println("Kết thúc chương trình !");
                    System.exit(0);
                    }
                default:{
                    System.out.println("Lựa chọn không hợp lệ !");
                }
            }

        }while (true);

    }
}
