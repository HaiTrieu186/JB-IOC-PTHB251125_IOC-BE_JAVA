import java.util.Arrays;
import java.util.Scanner;

public class BT3 {
    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";
    public static final String PLATE_REGEX = "^\\d{2}[A-Z]-\\d{3}\\.\\d{2}$";

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int choice, number=0;
        boolean flag=false;
        String[] listNumberPlate= new String[100];

        do{
            System.out.println("""
                    \n+-------------------------QUẢN LÝ BIỂN SỐ XE--------------------------+
                    1. Thêm các biển số xe
                    2. Hiển thị danh sách biển số xe
                    3. Tìm kiếm biên số xe
                    4. Tìm kiếm biển số xe theo mã tỉnh
                    5. Sắp xếp biển số xe theo mã tỉnh
                    6. Thoát
                    +-------------------------MENU--------------------------+""");
            System.out.print("Vui lòng chọn chức năng: ");
            choice = Integer.parseInt(sc.nextLine());

            if (choice>=2 && choice<=5) {
                if (!flag){
                    System.out.println(RED + "Lỗi: Vui lòng thêm biển số xe trước !" + RESET);
                }
            }

            switch (choice) {
                case 1:{
                    flag=true;
                    System.out.println("Danh sách biển số hiện tại: "+ number);
                    int n;

                    // NHẬP SÓ LƯỢNG BIỂN SỐ CẦN THÊM
                    do{
                        System.out.print("Mời bạn nhập số lượng biển cần thêm mới: ");
                        n = Integer.parseInt(sc.nextLine());

                        if (n<=0) {
                            System.out.println(RED + "Lỗi: Vui lòng nhập số lượng hợp lệ (n>=0) !" + RESET);
                        }

                    }while (n<=0);


                    // TẠO MANG MỚI NẾU KHÔNG ĐỦ
                    if ((number+ n )>= listNumberPlate.length) {
                        listNumberPlate= Arrays.copyOf(listNumberPlate, listNumberPlate.length+100);
                    }


                    // THÊM VÀO MẢNG
                    for (int i=0; i<n; i++){
                        System.out.print("Mời bạn nhập biển số xe thứ "+(number+1)+": ");
                        String temp=sc.nextLine();


                        if (isValidPlate(temp)){

                            if (contains(listNumberPlate, number, temp)!=-1){
                                System.out.println(RED + "Lỗi: Biển số xe đã tồn tại !" + RESET);
                                i--;
                                continue;
                            }

                            listNumberPlate[number++]=temp;

                        } else{
                            System.out.println(RED + "Lỗi: Vui lòng nhập biển số xe hợp lệ: (VD 67B-123.45) !" + RESET);
                            i--;
                        }

                    }

                    break;
                }
                case 2:{
                    printList(listNumberPlate, number);
                    break;
                }
                case 3:{
                    String find;
                    do{
                        System.out.print("Mời bạn nhập biển số xe cần tìm: ");
                        find=sc.nextLine();

                        if (!isValidPlate(find)){
                            System.out.println(RED + "Lỗi: Biển số xe không hợp lệ - VD: 67F-123.45 !" + RESET);
                        }

                    }while (!isValidPlate(find));

                    int index= contains(listNumberPlate, number, find);

                   if (index ==-1){
                       System.out.println("Kết quả tìm kiếm: không tìm thấy biển số "+find);
                   } else
                        System.out.printf("Kết quả tìm kiếm: tìm thấy biển %s tại vị trí %d trong danh sách \n",find, index+1);
                    break;
                }
                case 4:{
                    int code;
                    do{
                        System.out.print("Mời bạn nhập mã tỉnh (1-99): ");
                        code=Integer.parseInt(sc.nextLine());

                        if (code <1 || code>99)
                            System.out.println(RED + "Lỗi: Vui lòng nhập mã tỉnh hợp lệ  (1-99) !" + RESET);

                    } while (code <1 || code>99);

                    String[] matchedPlates= new  String[number];
                    int count=0;

                    for (int i=0; i<number; i++) {
                        int currentCode= Integer.parseInt(listNumberPlate[i].substring(0,2));
                        if (currentCode==code){
                            matchedPlates[count++]=listNumberPlate[i];
                        }
                    }

                    if (count==0){
                        System.out.println("Danh sách kết quả: Không tìm thấy ");
                        break;
                    }

                    System.out.println("Danh sách kết quả");
                    printList(matchedPlates, count);

                    break;
                }
                case 5:{
                    sortList(listNumberPlate, number, 0);
                    System.out.println("Đã sắp xếp danh sách tăng dần !");

                    break;
                }
                case 6:{
                    System.out.println("Kết thúc chương trình !");
                    sc.close();
                    System.exit(0);
                    break;
                }
                default:{
                    System.out.println(RED + "Lỗi: Vui lòng nhập số lượng hợp lệ (1-6)!" + RESET);
                    break;
                }
            }
        }while (true);


    }

    public static void sortList(String[] list,int number, int order){
        String temp;

        for (int i=0;i<number-1;i++){
            for (int j=0;j<number-i-1;j++){

                boolean check=
                        order==0 ? list[j].compareToIgnoreCase(list[j+1]) > 0  :
                                   list[j].compareToIgnoreCase(list[j+1]) < 0;
                if (check){
                    temp=list[j];
                    list[j]=list[j+1];
                    list[j+1]=temp;
                }

            }
        }
    }

    public static void printList(String[] list, int number){
        System.out.printf("\n+%s+%s+\n","-".repeat(7),"-".repeat(32));
        System.out.printf("| %-5s | %-30s |\n","STT","Biển số xe");
        System.out.printf("+%s+%s+\n","-".repeat(7),"-".repeat(32));

        for (int i=0;i<number;i++) {
            System.out.printf("| %-5s | %-30s |\n",(i+1),list[i]);
        }
        System.out.printf("+%s+%s+\n","-".repeat(7),"-".repeat(32));
    }

    public static boolean isValidPlate(String plate){
        return plate.matches(PLATE_REGEX);
    }

    public static int contains(String[] list, int number, String plate) {
        for (int i=0;i<number;i++) {
            if (list[i].equalsIgnoreCase(plate)) {
                return i;
            }
        }
        return -1;
    }

}
