package BT2.presentation;

import BT2.Exception.EmptyInputException;
import BT2.model.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static List<Event> events = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String eventName = "";

            while (true) {
                try {
                    eventName = Event.inputName(sc);
                    break;
                } catch (EmptyInputException e) {
                    System.out.println(e.getMessage());
                }
            }


            if (eventName.equalsIgnoreCase("exit")) {
                System.out.println("Kết thúc chương trình !");
                break; // THOÁT VÒNG LẶP TỔNG
            }

            Event newEvent = new Event();
            newEvent.setName(eventName);
            newEvent.input(sc);

            events.add(newEvent);

        }


        System.out.println("\n--- Danh sách sự kiện ---");
        if (events.isEmpty()) {
            System.out.println("Chưa có sự kiện nào !");
        } else
            for (Event event : events) {
                event.display(); // Tự động gọi hàm toString() bên Event
            }

        sc.close();

    }

}

