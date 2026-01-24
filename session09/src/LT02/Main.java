package LT02;

public class Main {
    public static void main(String[] args) {
        Book[] books = new Book[3];
        books[0] = new Book("Xac Suat Thong Ke","PTIT",100000);
        books[1] = new Book("Giai Tich II","HUST",125000);
        books[2] = new Book("Co So Tao Hinh","PTIT",25000);


        System.out.println("----------- Book List -----------");
        for (int i = 0; i<books.length; i++) {
            System.out.println("=== Book "+(i+1)+ " === ");
            books[i].printInfo();
            System.out.println();
        }
    }
}
