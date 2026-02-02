package LT0.ra.business;

import LT0.ra.entity.Product;

import java.util.Scanner;

public class ProductBusiness {
    public static final int MAX_PRODUCT=1000;
    public static Product[] products= new Product[MAX_PRODUCT];
    private static int crrProduct=0;


    public static void display(){
        if (crrProduct==0){
            System.out.printf("Chưa có sản phẩm nào !");
            return;
        }

        System.out.println("------ DANH SÁCH SẢN PHẨM ------");

        displayProducts(products, crrProduct);
    }

    public static void addProduct(Scanner sc){
        if (crrProduct == MAX_PRODUCT) {
            System.err.println("Kho đầy rồi, không thêm được nữa!");
            return;
        }

        Product p=new Product();

        p.inputData(sc);

        if (isDuplicateName(p.getProductName())){
            String name="";
            System.out.println("Tên sản phẩm đã tồn tại ! Vui lòng nhập lại tên.");

            do{
                System.out.print("Mời bạn nhập tên sản phẩm (10-50 kí tự): ");
                name=sc.nextLine();

                if (isDuplicateName(name))
                    System.out.println("Tên sản phẩm đã tồn tại ! Vui lòng nhập lại tên.");
                else {
                    p.setProductName(name);
                    break;
                }

            }while (true);
        }

        products[crrProduct]=p;
        crrProduct++;

        System.out.println("Thêm sản phẩm thành công !!!");
    }

    public static void removeProduct(Scanner sc, int id){
        int index=findProduct(id);

        if (index==-1){
            System.out.println("Không tồn tại sản phẩm với id: "+id+" !");
            return;
        }

        for (int i=index;i<crrProduct-1;i++){
            products[i]=products[i+1];
        }

        crrProduct--;

        System.out.println("Đã xóa sản phẩm với id: "+id+" !");
    }

    public static void searchProduct(Scanner sc,String keyword){
        if (crrProduct==0){
            System.out.printf("Chưa có sản phẩm nào !");
            return;
        }

        Product[] matches= new Product[crrProduct];
        int total=0;

        for (int i=0;i<crrProduct;i++){
            if (products[i].getProductName().toLowerCase().contains(keyword.toLowerCase())){
                matches[total++]=products[i];
            }
        }

        System.out.println("------ DANH SÁCH SẢN PHẨM TRÙNG KHỚP ------");

        if (total==0){
            System.out.println("Không có sản phẩm trùng khớp !");
            return;
        }


        displayProducts(matches,total);
    }

    public static void sortProductByPrice(int order){
        if (order==0){}
    }

    private static int findProduct(int id) {
        for (int i=0;i<crrProduct;i++){
            if (products[i].getProductId()==id)
                return i;
        }

        return -1;
    }

    private static boolean isDuplicateName(String productName) {
        for (int i=0;i<crrProduct;i++){
            if (products[i].getProductName().equals(productName)){
                return true;
            }
        }

        return false;
    }

    private static void displayProducts(Product[] products, int crrProduct){
        System.out.printf("+%s+%s+%s+%s+%s+\n","-".repeat(7),"-".repeat(52),"-".repeat(22),"-".repeat(22), "-".repeat(7));
        System.out.printf("| %-5s | %-50s | %-20s | %-20s | %-5s |\n", "Id", "ProductName", "Price", "Category", "Stock");
        System.out.printf("+%s+%s+%s+%s+%s+\n","-".repeat(7),"-".repeat(52),"-".repeat(22),"-".repeat(22), "-".repeat(7));

        for (int i=0;i<crrProduct;i++){
            products[i].displayDate();
        }
        System.out.printf("+%s+%s+%s+%s+%s+\n","-".repeat(7),"-".repeat(52),"-".repeat(22),"-".repeat(22), "-".repeat(7));
    }

}
