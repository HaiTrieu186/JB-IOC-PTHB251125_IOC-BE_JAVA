package LT0.ra;

import LT0.ra.business.ProductBusiness;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProductBusiness.addProduct(sc);
        ProductBusiness.display();
    }
}
