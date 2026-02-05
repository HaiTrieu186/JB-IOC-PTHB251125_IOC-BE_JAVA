package BT3;

import BT2.Student;

import java.util.Scanner;

public class Main {
    private static final InvoiceManager invoiceManager = new InvoiceManager();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("""
                    ------------- MENU QUẢN LÝ HÓA ĐƠN -------------
                    1. Thếm hóa đơn
                    2. Sửa hóa đơn
                    3. Xóa hóa đơn
                    4. Hiển thị danh sách hóa đơn
                    5. Thoát
                    """);
            System.out.print("Mời bạn lựa chọn (1-5): ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:{
                    Invoice invoice = new Invoice();
                    invoice.inputData(sc);

                    invoiceManager.add(invoice);
                    System.out.println("Hóa đơn đã được thêm thành công !");


                    break;}
                case 2:{
                    System.out.println("------- DANH SÁCH HIỆN TẠI -------");
                    invoiceManager.display();

                    System.out.print("Mời bạn nhập id cần sửa: ");
                    int id= Integer.parseInt(sc.nextLine());

                    if (!invoiceManager.checkIdExist(id)){
                        System.out.println("ID không tồn tại");
                    } else{
                        String newRecipeID;
                        double newPrice;
                        int index=invoiceManager.findIndexById(id);

                        while (true){
                            System.out.println("Mời bạn nhập ID mới: ");
                            newRecipeID=sc.nextLine();

                            if (newRecipeID.isBlank()){
                                System.out.println("Vui lòng không để trống !");
                                continue;
                            }
                            break;
                        }

                        while (true){
                            System.out.print("Mời bạn nhập giá mới: ");
                            newPrice=Double.parseDouble(sc.nextLine());

                            if(newPrice<=0){
                                System.out.println("Vui lòng nhập giá trị hợp lệ >= 0");
                            }else
                                break;
                        }

                        Invoice newInvoice= new Invoice();
                        newInvoice.setId(id);
                        newInvoice.setReceiptId(newRecipeID);
                        newInvoice.setPrice(newPrice);
                        invoiceManager.update(index, newInvoice);

                        System.out.println("Hóa đơn đã được sửa thành công !");

                    }

                    break;}
                case 3:{
                    System.out.println("------- DANH SÁCH HIỆN TẠI -------");
                    invoiceManager.display();

                    System.out.print("Mời bạn nhập id cần xóa: ");
                    int id= Integer.parseInt(sc.nextLine());

                    if (!invoiceManager.checkIdExist(id)){
                        System.out.println("ID không tồn tại");
                        break;
                    } else{
                        invoiceManager.delete(invoiceManager.findIndexById(id));
                    }
                    break;}
                case 4:{
                    if (invoiceManager.getInvoices().isEmpty()){
                        System.out.println("Chưa có hóa đơn nào !");
                        break;
                    }

                    System.out.println("-------- DANH SÁCH HIỆN TẠI --------");
                    invoiceManager.display();
                    break;}
                case 5:{
                    System.out.println("Thoát chương trình!");
                    sc.close();
                    System.exit(0);
                }
                default:{
                    System.out.println("Vui lòng chọn lựa chọn hợp lệ");
                }
            }
        }
    }
}
