import java.util.Scanner;

public class BT5 {
    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int option = -1;
        int n, heDiem;// 0: 0-10, 0-100
        double temp;


        // NHẬP ĐIỂM SINH VIÊN
        do {
            System.out.print("Mời bạn nhập số lượng sinh viên: ");
            n= Integer.parseInt(sc.nextLine());
            System.out.print("Mời bạn nhập hệ diểm (0: [0-10], 1: [0-100]): ");
            heDiem= Integer.parseInt(sc.nextLine());

            if (heDiem!=0 && heDiem!=1) {
                System.out.println(RED + "Lỗi: Vui lòng nhập hệ điểm hợp lệ (0 hoặc 1)!" + RESET);
            }

            if (n<=0){
                System.out.println(RED + "Lỗi: Vui lòng nhập số lượng hợp lệ (n>0)!" + RESET);
            }

        } while (n<=0 || (heDiem!=0 && heDiem!=1));
        double[] DS= new double[n];

        int j=0;
        do{
            System.out.print("Nhập điểm sinh viên thứ "+(j+1)+ ": ");
            temp= Double.parseDouble(sc.nextLine());

            if (heDiem==0) {
                if (temp <0 || temp>10) {
                    System.out.println(RED + "Lỗi: Vui lòng nhập điểm hợp lệ [0-10]!" + RESET);
                } else{
                    DS[j] = temp;
                    j++;
                }

            } else {
                if (temp <0 || temp>100) {
                    System.out.println(RED + "Lỗi: Vui lòng nhập điểm hợp lệ [0-100]!" + RESET);
                } else{
                    DS[j] = temp;
                    j++;
                }
            }

            if (j==n)
                break;

        }while (true);



        // QUẢN LÝ SINH VIÊN
        do {
            System.out.println("\n\n-----QUẢN LÝ ĐIỂM SINH VIÊN-----");
            System.out.printf("- 1. %-25s -\n","Xem tất cả điểm ");
            System.out.printf("- 2. %-25s -\n","Sắp xếp điểm ");
            System.out.printf("- 3. %-25s -\n","Tìm kiếm điểm ");
            System.out.printf("- 4. %-25s -\n","Thống kê điểm ");
            System.out.printf("- 5. %-25s -\n","Thoát ");
            System.out.println("--------------------------------");

            System.out.print("- Lựa chọn của bạn: ");
            option= Integer.parseInt(sc.nextLine());

            if (option==5) {
                System.out.println(RED + "Kết thúc chương trình !"+ RESET);
                System.exit(0);
            }

            if (option==1){
                System.out.println("\n-----Danh Sách Điêm-----");
                for (int i=0; i< DS.length; i++ ){
                    System.out.printf("Sinh viên thứ %d: %.2f\n",i+1,DS[i]);
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
                    BubbleSort(DS, order);
                } else {
                    SelectionSort(DS, order);
                }

            }

            if (option==3){
                double key;

                System.out.println("\n-----Tìm kiếm điểm-----");

                do{
                    System.out.print("Mời bạn nhập điểm cần tìm: ");
                    key = Double.parseDouble(sc.nextLine());


                    // Check điểm có hợp lệ với hệ điểm
                    if (heDiem==0) {
                        if (key <0 || key>10) {
                            System.out.println(RED + "Lỗi: Vui lòng nhập điểm hợp lệ [0-10]!" + RESET);
                        } else{
                            break;
                        }

                    } else {
                        if (key <0 || key>100) {
                            System.out.println(RED + "Lỗi: Vui lòng nhập điểm hợp lệ [0-100]!" + RESET);
                        } else{
                           break;
                        }
                    }
                    }while (true);

                    int idxLinear= LinearSearch(DS, key);
                    BubbleSort(DS,1);
                    int idxBinary= BinarySearch(DS, key);

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
                double avgPoint, sumPoint=0, maxPoint=DS[0],minPoint=DS[0];

                for (int i=0;i<DS.length;i++){
                    // Tính tổng điểm
                   sumPoint+=(DS[i]);

                    // Tìm max
                    if (DS[i]>maxPoint)
                        maxPoint=DS[i];

                    // Tìm min
                    if (DS[i]<minPoint)
                        minPoint=DS[i];

                }

                avgPoint= (sumPoint/DS.length);
                for (int i=0;i<DS.length;i++){
                    if (DS[i]>avgPoint) count++;
                }

                System.out.println("Điểm trung bình: "+avgPoint);
                System.out.println("Điểm cao nhất: "+ maxPoint);
                System.out.println("Điểm thấp nhất: "+ minPoint);
                System.out.println("Số sinh viên trên điểm TB: "+count);


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
                    if (a[j]<a[index]){}
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
