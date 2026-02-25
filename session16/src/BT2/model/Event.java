package BT2.model;

import BT2.Exception.EmptyInputException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Event implements IBaseModel {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public Event() {
    }

    public Event(String name, LocalDateTime startDate, LocalDateTime endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    @Override
    public void input(Scanner sc) {

        // 1. Nhập Ngày Bắt Đầu
        while (true) {
            try {
                this.startDate = inputDate(sc, 1);
                break;
            } catch (EmptyInputException | IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        // 2. Nhập Ngày Kết Thúc
        while (true) {
            try {
                LocalDateTime tempEndDate = inputDate(sc, 2);

                // Ngày kết thúc không được diễn ra trước ngày bắt đầu
                if (tempEndDate.isBefore(this.startDate)) {
                    System.out.println("Lỗi: Thời gian kết thúc phải diễn ra SAU thời gian bắt đầu!");
                    continue;
                }

                this.endDate = tempEndDate;
                break;
            } catch (EmptyInputException | IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static String inputName(Scanner sc) {
        System.out.println("Nhập tên sự kiện (hoặc 'exit' để thoát):");
        String name = sc.nextLine();

        if (name.isBlank()) {
            throw new EmptyInputException("Lỗi: Không được để trống tên sự kiện!");
        }
        return name;
    }

    public static LocalDateTime inputDate(Scanner sc, int option) {
        String date;
        String time = option == 1 ? "bắt đầu" : "kết thúc";


        System.out.println("Nhập thời gian " + time + " (dd-MM-yyyy HH:mm): ");
        date = sc.nextLine();

        if (date.isBlank()) {
            throw new EmptyInputException("Lỗi: Không được để trống thời gian "+ time+" !");
        }

        try {
            return LocalDateTime.parse(date , formatter);
        }catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Lỗi: Định dạng thời gian không hợp lệ (dd/MM/yyyy HH:mm) !");
        }


    }

    @Override
    public void display() {
        System.out.println("Tên sự kiện: " + this.name);
        System.out.println("Thời gian bắt đầu: " + this.startDate.format(formatter));
        System.out.println("Thời gian kết thúc: " + this.endDate.format(formatter));
        System.out.println("-------------------------------------------------");
    }


}
