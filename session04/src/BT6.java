import java.util.Scanner;

public class BT6 {
    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int option = -1;
        int n;
        double temp;


        // NHẬP ĐIỂM NHÂN VIÊN
        do {
            System.out.print("Mời bạn nhập số lượng nhân viên: ");
            n= Integer.parseInt(sc.nextLine());


            if (n<=0){
                System.out.println(RED + "Lỗi: Vui lòng nhập số lượng hợp lệ (n>0)!" + RESET);
            }

        } while (n<=0);
        double[] salaries= new double[n];

        int j=0;
        do{
            System.out.print("Nhập lương nhân viên thứ "+(j+1)+ ": ");
            temp= Double.parseDouble(sc.nextLine());

                if (temp <=0) {
                    System.out.println(RED + "Lỗi: Vui lòng nhập luương hợp lệ (Lớn hơn 0)!" + RESET);
                } else{
                    salaries[j] = temp;
                    j++;
                }

            if (j==n)
                break;

        }while (true);



        // QUẢN LÝ SINH VIÊN
        do {
            System.out.println("\n\n-----QUẢN LÝ LƯƠNG NHÂN VIÊN-----");
            System.out.printf("- 1. %-25s -\n","Xem danh sách lương ");
            System.out.printf("- 2. %-25s -\n","Sắp xếp lương ");
            System.out.printf("- 3. %-25s -\n","Tìm kiếm lương ");
            System.out.printf("- 4. %-25s -\n","Thống kê lương ");
            System.out.printf("- 5. %-25s -\n","Thoát ");
            System.out.println("--------------------------------");

            System.out.print("- Lựa chọn của bạn: ");
            option= Integer.parseInt(sc.nextLine());

            if (option==5) {
                System.out.println(RED + "Kết thúc chương trình !"+ RESET);
                System.exit(0);
            }

            if (option==1){
                System.out.println("\n-----Danh Sách Lương-----");
                for (int i=0; i< salaries.length; i++ ){
                    System.out.printf("Nhân viên viên thứ %d: %.2f\n",i+1,salaries[i]);
                }
                System.out.println("------------------------");

            }

            if (option==2){
                int type, order;

                // Chọn loại sắp xếp
                System.out.println("\n-----Chọn Loại Sắp xếp-----");
                System.out.println("1. Sắp xếp nổi bọt (Bubbles Sort)");
                System.out.println("2. Sắp xếp chọn (Selection Sort)");
                do {
                    System.out.print("Mời bạn chọn: ");
                    type= Integer.parseInt(sc.nextLine());

                    if (type!=1 && type!=2) {
                        System.out.println(RED + "Lỗi: Vui lòng chỉ chọn 1 hoặc 2!" + RESET);
                    }

                } while (type!=1 && type!=2);

                // Chọn sắp xếp tăng hay giảm
                System.out.println("\n-----Chọn Kiểu Sắp xếp-----");
                System.out.println("1. Sắp xếp tăng dần (Ascending)");
                System.out.println("2. Sắp xếp giảm dần (Descending)");
                do {
                    System.out.print("Mời bạn chọn: ");
                    order= Integer.parseInt(sc.nextLine());

                    if (order!=1 && order!=2) {
                        System.out.println(RED + "Lỗi: Vui lòng chỉ chọn 1 hoặc 2!" + RESET);
                    }

                } while (order!=1 && order!=2);

                if (type==1) {
                    BubbleSort(salaries, order);
                } else {
                    SelectionSort(salaries, order);
                }
                System.out.println("Đã sắp xếp lương "+ (order==1 ? "tăng dần" : "giảm dần"));

            }

            if (option==3){
                double key;

                System.out.println("\n-----Tìm kiếm lương-----");

                do{
                    System.out.print("Mời bạn nhập lương cần tìm: ");
                    key = Double.parseDouble(sc.nextLine());

                    if (key <=0) {
                        System.out.println(RED + "Lỗi: Vui lòng nhập lương hợp lệ (Lớn hơn 0)!" + RESET);
                    }

                    break;

                }while (true);

                int idxLinear= LinearSearch(salaries, key);
                BubbleSort(salaries,1);
                int idxBinary= BinarySearch(salaries, key);

                if (idxLinear==-1) {
                    System.out.printf("Tìm kiếm tuyến tính: số %.2f không được tìm thấy",key);
                } else
                    System.out.printf("Tìm kiếm tuyến tính: số %.2f được tìm thấy tại vị trí %d",key,idxLinear);

                if (idxBinary==-1) {
                    System.out.printf("\nTìm kiếm nhị phân (Theo mảng tăng dần): số %.2f không được tìm thấy",key);
                } else
                    System.out.printf("\nTìm kiếm nhị phân (Theo mảng tăng dần): số %.2f được tìm thấy tại vị trí %d",key,idxBinary);


            }

            if (option==4){
                int count=0;
                double sumSalary=0, avgSalary, maxSalary=salaries[0],minSalary=salaries[0];

                for (int i=0;i<salaries.length;i++){
                    // Tính tổng điểm
                    sumSalary+=(salaries[i]);

                    // Tìm max
                    if (salaries[i]>maxSalary)
                        maxSalary=salaries[i];

                    // Tìm min
                    if (salaries[i]<minSalary)
                        minSalary=salaries[i];

                }

                avgSalary=sumSalary / salaries.length;
                for (int i=0;i<salaries.length;i++){
                    if (salaries[i]>avgSalary) count++;
                }

                System.out.println("Lương trung bình: "+avgSalary);
                System.out.println("Lương cao nhất: "+ maxSalary);
                System.out.println("Lương thấp nhất: "+ minSalary);
                System.out.println("Số nhân viên trên Lương TB: "+count);


            }

        } while (true);

    }

    public static void BubbleSort (double[] a, int order){
        double temp;

        if (order==1){
            for (int i=0; i< a.length-1; i++)
                for (int j=0; j< a.length-1-i; j++)
                    if (a[j]>a[j+1]){
                        temp = a[j];
                        a[j] = a[j+1];
                        a[j+1] = temp;
                    }
        }

        if (order==2){
            for (int i=0; i< a.length-1; i++)
                for (int j=0; j< a.length-1-i; j++)
                    if (a[j]<a[j+1]){
                        temp = a[j];
                        a[j] = a[j+1];
                        a[j+1] = temp;
                    }
        }



    }

    public static void SelectionSort (double[] a, int order){
        double temp;
        int index;
        for (int i=0; i< a.length-1; i++){
            index= i;
            for (int j=i+1; j<a.length; j++){
                if (order==1){
                    if (a[j]<a[index])
                        index=j;
                }

                if (order==2){
                    if (a[j]>a[index])
                        index=j;
                }
            }
            temp = a[i];
            a[i] = a[index];
            a[index]=temp;

        }
    }

    public static int LinearSearch(double[] a, double key) {

        for (int i=0; i< a.length; i++){
            if (a[i]==key){
                return i;
            }
        }

        return -1;

    }

    public static int BinarySearch(double[] a, double key) {
        int mid, left=0, right=a.length-1;
        while (left<=right){
            mid= (left+right)/2;

            if (a[mid]==key){
                return mid;
            }

            if (a[mid]<key){
                left=mid+1;
            }

            if (a[mid]>key){
                right=mid-1;
            }
        }

        return -1;
    }
}
