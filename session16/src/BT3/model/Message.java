package BT3.model;

import BT2.Exception.EmptyInputException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Message implements IBaseModel {
    private static final DateTimeFormatter formatterFull = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private static final DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private String sender;
    private String content;
    private LocalDateTime timestamp;

    public Message() {
        this.timestamp = LocalDateTime.now();
    }

    public Message(String sender, String content, LocalDateTime timestamp) {
        this.sender = sender;
        this.content = content;
        this.timestamp = timestamp;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public void input(Scanner sc) {
        while (true) {
            try {
                this.content = inputContent(sc);
                break;
            }catch (EmptyInputException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static String inputName(Scanner sc) {
        System.out.println("Nhập tên người gửi (hoặc 'exit' để thoát):");
        String name = sc.nextLine();

        if (name.isBlank()) {
            throw new EmptyInputException("Lỗi: Không được để trống tên người gửi!");
        }
        return name;
    }

    public static String inputContent(Scanner sc) {
        System.out.println("Nhập nội dung tin nhắn:");
        String content = sc.nextLine();

        if (content.isBlank()) {
            throw new EmptyInputException("Lỗi: Không được để trống nội dung tin nhắn!");
        }
        return content;
    }

    public static LocalDate inputDate(Scanner sc) {
        String date;
        System.out.println("Nhập ngày (dd/MM/yyyy): ");
        date = sc.nextLine();

        if (date.isBlank()) {
            throw new EmptyInputException("Lỗi: Không được để trống ngày tìm kiếm !");
        }

        try {
            return LocalDate.parse(date , formatterDate);
        }catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Lỗi: Định dạng ngày không hợp lệ (dd/MM/yyyy) !");
        }

    }

    @Override
    public void display() {
        System.out.print("["+this.timestamp.format(formatterFull)+"] ");
        System.out.println(this.sender+": "+ this.content);
    }


}
