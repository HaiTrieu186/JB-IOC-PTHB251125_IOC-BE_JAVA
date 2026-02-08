package LT01.model;

import LT01.Exception.EmptyInputException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Movie implements IBaseModel {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private String id;
    private String title;
    private String director;
    private LocalDate releaseDate;
    private double rating;

    public Movie() {
    }

    public Movie(String id, String title, String director, LocalDate releaseDate, double rating) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.releaseDate = releaseDate;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public void display() {
        String formattedDate = (this.releaseDate != null) ? this.releaseDate.format(DATE_FORMATTER) : "N/A";

        System.out.println("ID: " + this.id
                + ", Title: " + this.title
                + ", Director: " + this.director
                + ", Release Date: " + formattedDate
                + ", Rating: " + this.rating);
    }


    @Override
    public void input(Scanner sc) {
//        Em comment vì đem ID riêng để dễ check tồn tại
//        while (true) {
//            try {
//                this.id = inputID(sc);
//                break;
//            } catch (EmptyInputException e) {
//                System.out.println(e.getMessage());
//            }
//        }

        while (true) {
            try {
                this.title = inputTitle(sc);
                break;
            } catch (EmptyInputException e) {
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            try {
                this.director = inputDirector(sc);
                break;
            } catch (EmptyInputException e) {
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            try {
                this.releaseDate = inputReleaseDate(sc);
                break;
            } catch (EmptyInputException e) {
                System.out.println(e.getMessage());
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            try {
                this.rating = inputRating(sc);
                break;
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static String inputID(Scanner sc) {
        String id;
        System.out.println("Mời bạn nhập ID phim: ");
        id = sc.nextLine();

        if (id.isEmpty()) {
            throw new EmptyInputException("Lỗi: Không được bỏ trống ID!");
        }

        return id;
    }

    public static String inputTitle(Scanner sc) {
        String title;
        System.out.println("Mời bạn nhập tiêu đề phim: ");
        title = sc.nextLine();

        if (title.isEmpty()) {
            throw new EmptyInputException("Lỗi: Không được bỏ trống tiêu đề phim!");
        }

        return title;
    }

    public static String inputDirector(Scanner sc) {
        String director;
        System.out.println("Mời bạn nhập đạo diễn: ");
        director = sc.nextLine();


        if (director.isEmpty()) {
            throw new EmptyInputException("Lỗi: Không được bỏ trống tên đạo diễn!");
        }

        return director;
    }

    public static double inputRating(Scanner sc) {
        double r;

        try {
            System.out.println("Mời bạn nhập rating:");
            r = Double.parseDouble(sc.nextLine());
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Lỗi: Vui lòng nhập rating hợp lệ, vui lòng nhập lại!");
        }

        if (r < 0 || r > 10) {
            throw new IllegalArgumentException("Lỗi: Vui lòng nhập rating hợp lệ trong khoảng [1.0 - 10.0] ");
        }

        return r;
    }

    public static LocalDate inputReleaseDate(Scanner sc) {
        System.out.println("Mời bạn nhập ngày phát hành (yyyy-MM-dd): ");
        String dateStr = sc.nextLine();

        if (dateStr.trim().isEmpty()) {
            throw new EmptyInputException("Lỗi: Không được bỏ trống ngày phát hành!");
        }

        try {
            return LocalDate.parse(dateStr, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new RuntimeException("Lỗi: Ngày không hợp lệ hoặc sai định dạng (yyyy-MM-dd)!");
        }
    }
}
