package LT2;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice=-1;
        Assest[] assests = new Assest[100];
        int total=0;

        do{
            System.out.println("""
                    ------------ MENU ------------
                    1. Nhập tài sản
                    2. Xuất báo cáo
                    3. Tìm kiếm (assetCode hoặc purchasePrice)
                    4. Sửa giá mua
                    5. Thoát.
                    """);
            System.out.print("Mời bạn nhập lựa chọn: ");
            choice= Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:{
                    if (total==100) {
                        System.out.println("Đã đầy, không thể thêm mới !");
                        break;
                    }

                    int c=0;
                    do {
                        System.out.println("\n-- Mời bạn chọn ---");
                        System.out.println("1. Computer (Máy tính)");
                        System.out.println("2. NetworKDevice (Thiết bị mạng)");
                        System.out.print("Mời bạn chọn loại thiết bị: ");
                        c=Integer.parseInt(sc.nextLine());

                        if (c!=1 && c!=2) {
                            System.out.println("Vui lòng chọn lựa chọn hợp lệ");
                        } else
                            break;
                    }while (true);

                    if (c==1){
                        Computer computer = new Computer();
                        computer.inputData(sc);

                        assests[total++]=computer;
                    } else{
                        NetworkDevice networkDevice = new NetworkDevice();
                        networkDevice.inputData(sc);
                        assests[total++]=networkDevice;
                    }

                    System.out.println("Thêm thiết bị thành công !");

                    break;
                }
                case 2:{
                    if (total==0) {
                        System.out.println("Chưa có thiết bị nào");
                        break;
                    }

                    System.out.println("-------- DANH SÁCH SẢN PHẨM --------");
                    displayList(assests, total);

                    break;
                }
                case 3:{
                    if (total==0) {
                        System.out.println("Chưa có thiết bị nào");
                        break;
                    }
                    int c=0;
                    do {
                        System.out.println("------- Tìm kiếm thiết bị -------");
                        System.out.println("1. Tìm kiếm theo assestCode ");
                        System.out.println("2. Tìm kiếm theo giá (tìm những máy có giá lớn hơn) ");
                        c= Integer.parseInt(sc.nextLine());

                        if (c!=1 && c!=2) {
                            System.out.println("Vui lòng chọn hợp lệ (1-2) !");
                        } else
                            break;
                    }while (true);

                    if (c==1){
                        System.out.println("Mời bạn nhập code: ");
                        String code=sc.nextLine();
                        findAssest(code, assests, total);
                    }

                    if (c==2){
                        double p;
                        do {
                            System.out.print("Mời bạn nhập giá cần tìm: ");
                            p=Double.parseDouble(sc.nextLine());

                            if (p<=0)
                                System.out.println("Mời bạn nhập giá tiền hợp lệ");
                            else
                                break;
                        }while(true);
                        findAssest(p, assests, total);

                    }

                    break;
                }
                case 4:{
                    if (total==0) {
                        System.out.println("Chưa có thiết bị nào");
                        break;
                    }

                    System.out.println("Mời bạn nhập code thiết bị cần sửa: ");
                    String code=sc.nextLine();

                    int index=-1;
                    for (int i=0; i<total; i++) {
                        if (assests[i].getAssestCode().equals(code)) {
                            index = i;
                        }
                    }

                    if (index==-1) {
                        System.out.println("Mã code không tồn tại");
                        break;
                    } else{
                        double p;
                        do {
                            System.out.print("Mời bạn nhập giá cần tìm: ");
                            p=Double.parseDouble(sc.nextLine());

                            if (p<=0)
                                System.out.println("Mời bạn nhập giá tiền hợp lệ");
                            else
                                break;
                        }while(true);

                        assests[index].setPurchasePrice(p);

                    }


                    break;
                }
                case 5:{
                    System.out.println("Kết thúc chương trình !");
                    sc.close();
                    System.exit(0);
                }
                default:{
                    System.err.println("Vui loòng chon từ 1-5 !");
                }
            }

        }while (true);

    }

    private static void showValue(Assest assest) {
        assest.getMarketValue();
    }


    private static void displayList(Assest[]  assests, int total) {
        for (int i = 0; i < total; i++) {

            System.out.println("--- Thiết bị thứ "+(i+1)+" ---");
            System.out.println("Mã code: "+ assests[i].getAssestCode());
            System.out.println("Tên thiết bị: "+ assests[i].getName());
            showValue(assests[i]);

            if (assests[i] instanceof Computer){
                Computer computer = (Computer) assests[i];
                System.out.println("CPU: "+ computer.getCpu());
                System.out.println("Ram: "+ computer.getRam());
            }
            if (assests[i] instanceof NetworkDevice){
                NetworkDevice networkDevice = (NetworkDevice) assests[i];
                System.out.println("Số cổng kết nối: "+ networkDevice.getNumberOfPorts());
            }
        }
    }

    private static void findAssest(String code, Assest[] assests, int total) {
        Assest[] matches = new Assest[100];
        int count=0;

        for (int i=0;i<total;i++){
            if (assests[i].getAssestCode().equals(code)){
                matches[count++]=assests[i];
            }
        }

        if (count==0){
            System.out.println("KHÔNG CÓ SẢN PHẨM TRÙNG KHỚP");
            return;
        }

        System.out.println(" --- DANH SÁCH KẾT QUẢ --- ");
        displayList(matches, count);

    }

    private static void findAssest(double price, Assest[] assests, int total){
        Assest[] matches = new Assest[100];
        int count=0;

        for (int i=0;i<total;i++){
            if (assests[i].getPurchasePrice() >= price) {
                matches[count++]=assests[i];
            }
        }

        if (count==0){
            System.out.println("KHÔNG CÓ SẢN PHẨM TRÙNG KHỚP");
            return;
        }

        System.out.println(" --- DANH SÁCH KẾT QUẢ --- ");
        displayList(matches, count);
    }
}
