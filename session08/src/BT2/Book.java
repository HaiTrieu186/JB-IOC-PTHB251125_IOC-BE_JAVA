package BT2;

public class Book {
    private String title;
    private String author;
    private double price;

    public Book(){}

    public Book(String title, String author, double price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public static void main(String[] args) {
        Book book1 = new Book("Giai tich 1","HUST",50000);
        Book book2 = new Book("Giai tich 2","HUST",50000);

        System.out.printf("Book1 - Title: %s, Author: %s, Price: %.2f\n", book1.getTitle(), book1.getAuthor(), book1.getPrice());
        System.out.printf("Book2 - Title: %s, Author: %s, Price: %.2f\n", book2.getTitle(), book2.getAuthor(), book2.getPrice());
    }
}
