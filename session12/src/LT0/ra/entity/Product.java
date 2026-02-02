package LT0.ra.entity;

import java.util.Scanner;

public class Product {
    private static int crrId=0;
    private int productId;
    private String productName;
    private float price;
    private String category;
    private int quantity;

    public Product() {
        this.productId=crrId++;
    }

    public Product(int productId, String productName, float price, String category, int quantity) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
    }

    public static int getCrrId() {
        return crrId;
    }

    public static void setCrrId(int crrId) {
        Product.crrId = crrId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", quantity=" + quantity +
                '}';
    }

    public void displayDate(){
        System.out.printf("| %-5s | %-50s | %-20s | %-20s | %-5s |\n", productId, productName, price, category, quantity);
    }

    public void inputData(Scanner sc) {
        this.productName= this.inputName(sc);
        this.price= this.inputPrice(sc);
        this.category= this.inputCategory(sc);
        this.quantity= this.inputQuantity(sc);
    }


    // Nhập số lượng tồn kho
    private int inputQuantity(Scanner sc) {
        int quantity=0;

        do{
            System.out.print("Mời bạn nhập số lượng tồn kho (>=0): ");
            quantity= Integer.parseInt(sc.nextLine());

            if (quantity>=0)
                break;
            else
                System.out.println("Vui lòng nhập số lượng tồn kho sản phẩm hợp lệ (>=0) !");
        } while (true);

        return quantity;

    }

    // Nhập loại
    private String inputCategory(Scanner sc) {
        String category="";

        do{
            System.out.print("Mời bạn nhập tên loại sản phẩm (1-200 kí tự): ");
            category= sc.nextLine();

            if (category.length()>0 && category.length()<=200)
                break;
            else
                System.out.println("Vui lòng nhập loại sản phẩm hợp lệ (1-200 kí tự) !");
        } while (true);

        return category;
    }

    // Nhập giá
    private float inputPrice(Scanner sc) {
        float price=0;

        do{
            System.out.print("Mời bạn nhập giá sản phẩm: ");
            price= Float.parseFloat(sc.nextLine());

            if (price>0)
                break;
            else
                System.out.println("Vui lòng nhập giá lớn hơn 0");
        } while (true);

        return price;
    }

    // Nhập tên
    private String inputName(Scanner sc) {
        String name=null;

        do{
            System.out.print("Mời bạn nhập tên sản phẩm (10-50 kí tự): ");
            name=sc.nextLine();

            if (name.length()>=10 && name.length()<=50)
                break;
            else {
                System.err.println("Tên phải từ 10-50 ký tự, nhập lại đi!");
            }

        } while (true);

        return name;
    }
}
