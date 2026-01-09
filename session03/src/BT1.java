import java.util.Scanner;

public class BT1 {
    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String customerName, productName, checkMember;
        double price, vat, total, discount=0;
        int quantity;
        boolean isMember, validate;


        do {
            validate = true;
            System.out.println("\n----------NHẬP THÔNG TIN HÓA ĐƠN----------");
            System.out.print("Tên khách hàng: ");
            customerName = sc.nextLine();
            System.out.print("Tên sản phẩm: ");
            productName = sc.nextLine();
            System.out.print("Giá: ");
            price = Double.parseDouble(sc.nextLine());
            System.out.print("Số lượng: ");
            quantity = Integer.parseInt(sc.nextLine());
            System.out.print("Thẻ thành viên (true/false): ");
            checkMember = sc.nextLine().trim().toLowerCase();

             if (!checkMember.equals("true") && !checkMember.equals("false")) {
                 validate=false;
                 System.out.println(RED + "Lỗi: Vui lòng chỉ nhập true hoặc false!" + RESET);
             }

             isMember= Boolean.parseBoolean(checkMember);

             if (price <= 0){
                 validate=false;
                 System.out.println(RED + "Lỗi: Giá phải là số dương!" + RESET);
             }

             if (quantity <= 0){
                 validate=false;
                 System.out.println(RED + "Lỗi: Số lượng phải là số dương!" + RESET);
             }

        } while (!validate);


        total = price * quantity;
        vat = total * 0.08;

        if (isMember){
            discount = total * 0.1;
        }

        System.out.println("\n----------THÔNG TIN HÓA ĐƠN----------");
        System.out.println("Khách hàng: "+ customerName);
        System.out.println("Sản phẩm: "+ productName);
        System.out.println("Giá: "+price+ " VNĐ");
        System.out.println("Số lượng: "+ quantity);
        System.out.println("Thành tiền: "+ total+" VNĐ");
        System.out.println("Giảm giá: "+ discount+ " VNĐ");
        System.out.println("Tiền VAT: "+ vat+ " VNĐ");
        System.out.println("Tông tiền thanh toán: "+ (total-discount+vat)+ " VNĐ");


    }
}
