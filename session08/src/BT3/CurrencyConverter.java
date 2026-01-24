package BT3;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public final class CurrencyConverter {
    private static double rate=-1;

    public static double getRate() {
        return rate;
    }

    public static void setRate(double rate) {
        if (rate <= 0) {
            System.err.println("Tỉ giá không hợp lệ !");
            return;
        }

        CurrencyConverter.rate = rate;
    }

    public static double toUSD(int vnd, int n){
        if (vnd <= 0) {
            System.err.println("Số tiền VND không hợp lệ !");
            return -1;
        }

        if (rate <= 0) {
            System.err.println("Tỉ giá chưa hợp lệ (Mời nhập tỉ giá) !");
            return -1;
        }

        double usd= vnd/rate;

        return formatUSD(usd, n);

    }

    public static double formatUSD(double usd, int n){
        if (usd <= 0) {
            System.err.println("Số tiền USD không hợp lệ !");
            return -1;
        }

        BigDecimal formattedUSD = BigDecimal.valueOf(usd);
        formattedUSD = formattedUSD.setScale(n, RoundingMode.HALF_UP); // HALF_UP là quy tắc >= 0.5 thì lên
        return formattedUSD.doubleValue();

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. Nhập tỷ giá
        System.out.print("Nhập tỷ giá (VND/USD): ");
        double r = Double.parseDouble(sc.nextLine());
        CurrencyConverter.setRate(r);

        // 2. Nhập số tiền
        System.out.print("Nhập số tiền VND muốn đổi: ");
        int vnd = Integer.parseInt(sc.nextLine());

        // 3. Nhập số chữ số làm tròn
        int n;
        do {
            System.out.print("Mời bạn nhập số lượng chữ số làm tròn (>=0): ");
            n = Integer.parseInt(sc.nextLine());
            if (n < 0) System.err.println("Phải lớn hơn hoặc bằng 0!");
        } while (n < 0);


        // 4. Tính toán
        double usd = toUSD(vnd, n);
        if (usd != -1) {
            System.out.printf("%,d VND = %." + n + "f USD", vnd, usd);
        }
        sc.close();
    }
}
