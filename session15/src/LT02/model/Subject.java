package LT02.model;

import LT02.Exception.EmptyInputException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Subject implements IBaseModel{
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private String code;
    private String name;
    private int credits;
    private LocalDate startDate;

    public Subject() {
    }

    public Subject(String code, String name, int credits, LocalDate startDate) {
        this.code = code;
        this.name = name;
        this.credits = credits;
        this.startDate = startDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @Override
    public void input(Scanner sc) {

        while (true) {
            try {
                this.name= inputName(sc);
                break;
            }catch (EmptyInputException e){
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            try {
                this.credits=inputCredits(sc);
                break;
            }catch (NumberFormatException e){
                System.out.println(e.getMessage());
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            try {
                this.startDate=inputStartDate(sc);
                break;
            } catch (EmptyInputException e){
                System.out.println(e.getMessage());
            } catch (RuntimeException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public static String inputCode(Scanner sc) {
        String code;
        System.out.println("Mời bạn nhập Code môn học: ");
        code = sc.nextLine().trim();

        if (code.isEmpty()) {
            throw new EmptyInputException("Lỗi: Không được bỏ trống Code!");
        }

        return code;
    }

    public static String inputName(Scanner sc) {
        String name;
        System.out.println("Mời bạn nhập tên môn học: ");
        name = sc.nextLine().trim();

        if (name.isEmpty()) {
            throw new EmptyInputException("Lỗi: Không được bỏ trống tên môn học!");
        }

        return name;
    }

    public static int inputCredits(Scanner sc) {
        int credits;

        try {
            System.out.println("Mời bạn nhập số tín chỉ:");
            credits = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Lỗi: Vui lòng nhập định dạng hợp lệ, mời bạn nhập lại!");
        }

        if (credits < 0 || credits > 10) {
            throw new IllegalArgumentException("Lỗi: Vui lòng nhập số tín hợp lệ trong khoảng [1-10] ");
        }

        return credits;
    }

    public static LocalDate inputStartDate(Scanner sc) {
        System.out.println("Mời bạn nhập ngày bắt đầu (yyyy-MM-dd): ");
        String dateStr = sc.nextLine();

        if (dateStr.trim().isEmpty()) {
            throw new EmptyInputException("Lỗi: Không được bỏ trống ngày bắt đầu!");
        }

        try {
            return LocalDate.parse(dateStr, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new RuntimeException("Lỗi: Ngày không hợp lệ hoặc sai định dạng (yyyy-MM-dd)!");
        }
    }

    @Override
    public void display() {
        String formattedDate = (this.startDate != null) ? this.startDate.format(DATE_FORMATTER) : "N/A";

        System.out.println("Code: " + this.code
                + ", Name: " + this.name
                + ", Credits: " + this.credits
                + ", Start date " + formattedDate);
    }

}
