package BT4;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        String s="098765432, 01234, 0383299556, 0385425616, 12345ac@1a, 0909 888 777";
        String[] temp=s.trim().split(", ");

        List<String> phones= new ArrayList<>(Arrays.asList(temp));

        List<String> validPhones= new ArrayList<>();
        Map<String,String> map=new HashMap<>();

        for (String phone : phones) {
            try {
                InvalidPhoneNumberLengthException.validatePhoneNumber(phone);
                validPhones.add(phone);
            } catch (InvalidPhoneNumberLengthException e){
                map.put(phone,e.getMessage());
            }
        }

        System.out.println("Số điện thoại hợp lệ: ");
        for (String phone : validPhones) {
            System.out.println("- "+phone);
        }

        System.out.println("Số điện thoại hợp lệ: ");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println("- "+key + " : " + value);
        }


    }
}
