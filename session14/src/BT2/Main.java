package BT2;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        String temp;
        List<String> list=new ArrayList<>();
        int i=0;
        while(true){
            System.out.printf("Mời bạn nhập chuỗi mới (nhập exit để thoát, số chuỗi hiện tại %d): ",i);
            temp=sc.nextLine();

            if (temp.trim().equalsIgnoreCase("exit")){
                break;
            } else {
                list.add(temp);
                i++;
            }
        }

        int countInvalid=0;
        ListIterator<String> listIterator=list.listIterator();
        while(listIterator.hasNext()){
            try {
                convertStringToInt(listIterator.next());
            }catch (NumberFormatException e){
                listIterator.remove();
                countInvalid++;
            }
        }

        System.out.println("_________________ Kết quả ________________");
        System.out.println("Số chuỗi hợp lệ: "+ list.size());
        System.out.println("Số chuỗi không hợp lệ: "+ countInvalid);
        System.out.println("Danh sách số nguyên hợp lệ: "+ list);

    }

    public static void convertStringToInt(String s) throws NumberFormatException{
            Integer.parseInt(s);
    }
}
