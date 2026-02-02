package LT2;

import java.util.Scanner;

public class NetworkDevice extends Assest {

    private static double KHAU_HAO=0.1;
    private int numberOfPorts;

    public NetworkDevice() {
    }

    public NetworkDevice(String assestCode, String name, double purchasePrice, int numberOfPorts) {
        super(assestCode, name, purchasePrice);
        this.numberOfPorts = numberOfPorts;
    }

    public static double getKhauHao() {
        return KHAU_HAO;
    }


    public int getNumberOfPorts() {
        return numberOfPorts;
    }

    public void setNumberOfPorts(int numberOfPorts) {
        this.numberOfPorts = numberOfPorts;
    }

    @Override
    public void inputData(Scanner sc) {
        super.inputData(sc);

        int n;
        do {
            System.out.print("Mời bạn nhập giá mua: ");
            n = Integer.parseInt(sc.nextLine());

            if (n <= 0){
                System.out.println("Số cổng phải lớn hơn 0.");
            } else
                break;
        }while (true);
        this.numberOfPorts = n;
    }

    @Override
    public void getMarketValue() {
        System.out.println("Giá trị trước khấu hao: "+ super.getPurchasePrice());
        System.out.println("Giá trị sau khấu hao: "+ (super.getPurchasePrice())*(1-KHAU_HAO));
    }
}
