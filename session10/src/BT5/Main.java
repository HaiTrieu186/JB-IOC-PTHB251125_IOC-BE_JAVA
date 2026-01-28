package BT5;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int MAX=0, option=-1, totalAnimal=0;
        do{
            System.out.print("Mời bạn nhập số lượng tối đa lưu trữ: ");
            MAX= Integer.parseInt(sc.nextLine());

            if (MAX<=0) continue;
            break;

        }while (true);

        Animal[] animals = new Animal[MAX];

        // Do đề yêu cầu tạo trước.
        animals[totalAnimal++]= new Dog("Buddy",3,true);
        animals[totalAnimal++]= new Cat("Mimi",2,true);
        animals[totalAnimal++]= new Elephant("Dumbo",10,false);

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
                    System.out.println("\n----- Thông tin các động vật -----");
                    for (int i = 0; i < totalAnimal; i++) {
                        animals[i].showInfo();
                    }
                    System.out.println();
                    break;}
                case 2:{
                    System.out.println("\n----- Overriding:  makeSound() -----");
                    for (int i = 0; i < totalAnimal; i++) {
                        animals[i].makeSound();
                    }
                    System.out.println();
                    break;}
                case 3:{
                    System.out.println("\n----- Overloading: eat() -----");
                    for (int i = 0; i < totalAnimal; i++) {
                        animals[i].eat();

                        if (animals[i] instanceof Cat) {
                            animals[i].eat("Fish");
                        } else
                        if (animals[i] instanceof Dog) {
                            animals[i].eat("Meat");
                        } else
                        if (animals[i] instanceof Elephant) {
                            animals[i].eat("Fruit");
                        }

                    }
                    System.out.println();
                    break;}
                case 4:{
                    System.out.println("\n----- Polymorphism runtime -----");
                    for (int i = 0; i < totalAnimal; i++) {
                        animals[i].makeSound();
                    }
                    System.out.println();
                    break;}
                case 5:{
                    System.out.println("\n----- Phương thức riêng của từng lòa -----");
                    for (int i = 0; i < totalAnimal; i++) {

                        if (animals[i] instanceof Cat) {
                            Cat cat = (Cat) animals[i];
                            cat.climbTree();
                        } else
                        if (animals[i] instanceof Dog) {
                            Dog dog = (Dog) animals[i];
                            dog.fetchBall();
                        } else
                        if (animals[i] instanceof Elephant) {
                            Elephant elephant = (Elephant) animals[i];
                            elephant.sprayWater();
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
