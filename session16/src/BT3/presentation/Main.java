package BT3.presentation;

import BT2.Exception.EmptyInputException;
import BT3.model.Message;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static List<Message> messages = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== MENU ỨNG DỤNG CHAT =====");
            System.out.println("1. Gửi tin nhắn");
            System.out.println("2. Xem lịch sử chat");
            System.out.println("3. Lọc tin nhắn theo người gửi");
            System.out.println("4. Lọc tin nhắn theo ngày");
            System.out.println("0. Thoát");
            System.out.print("Mời bạn chọn chức năng (0-4): ");

            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1":
                    handleSendMessage(sc);
                    break;
                case "2":
                    handleHistory(sc);
                    break;
                case "3":
                    handleFilterBySender(sc);
                    break;
                case "4":
                    handleFilterByDate(sc);
                    break;
                case "0":
                    System.out.println("Kết thúc chương trình !");
                    sc.close();
                    return; // Thoát chương trình
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng chọn lại!");
            }
        }
    }

    private static void handleSendMessage(Scanner sc) {
        String sender = "";

        while (true) {
            try {
                sender = Message.inputName(sc);
                break;
            } catch (EmptyInputException e) {
                System.out.println(e.getMessage());
            }
        }

        if (sender.equalsIgnoreCase("exit")) {
            System.out.println("Đã hủy gửi tin nhắn!");
            return;
        }

        Message newMessage = new Message();
        newMessage.setSender(sender);
        newMessage.input(sc);
        messages.add(newMessage);
        System.out.println("Đã gửi!");
    }



    private static void handleFilterByDate(Scanner sc) {
        LocalDate date;

        while (true) {
            try {
                date = Message.inputDate(sc);
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        LocalDate findDate = date;
        List<Message> list = messages.stream().filter((p) -> {
            return p.getTimestamp().toLocalDate().isEqual(findDate);
        }).toList();

        System.out.println("Tin nhắn trong ngày [" + findDate + "]: ");
        list.forEach(Message::display);
    }

    private static void handleFilterBySender(Scanner sc) {
        String temp;

        while (true) {
            try {
                temp = Message.inputName(sc);
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        String findSender = temp;
        List<Message> list = messages.stream().filter((p) -> {
            return p.getSender().equalsIgnoreCase(findSender);
        }).toList();

        System.out.println("Tin nhắn từ " + findSender + ": ");
        list.forEach(Message::display);
    }

    private static void handleHistory(Scanner sc) {
        System.out.println("Lịch sử chat: ");
        messages.forEach(Message::display);
    }
}