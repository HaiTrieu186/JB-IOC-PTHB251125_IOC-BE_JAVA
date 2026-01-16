public class BT2 {
    public static void main(String[] args) {
        String s= "hello";
        StringBuffer sbf = new StringBuffer(s);
        StringBuilder sbd = new StringBuilder(s);

        long startTime, endtime, stime, sbftime, sbdtime;


        // STRING (Để 1 000 000 nó bị tràn heap ạ)
        startTime = System.currentTimeMillis();
        for (int i = 1; i <= 50000; i++) {
            s+="World";
        }
        endtime= System.currentTimeMillis();
        stime = endtime - startTime;


        // STRING BUFFER
        startTime = System.currentTimeMillis();
        for (int i = 1; i <= 1000000; i++) {
            sbf.append("World");
        }
        endtime= System.currentTimeMillis();
        sbftime = endtime - startTime;


        // STRING BULDER
        startTime = System.currentTimeMillis();
        for (int i = 1; i <= 1000000; i++) {
            sbd.append("World");
        }
        endtime= System.currentTimeMillis();
        sbdtime = endtime - startTime;


        System.out.println("Thời gian thực hiện với String: "+ stime);
        System.out.println("Thời gian thực hiện với StringBuilder "+ sbdtime);
        System.out.println("Thời gian thực hiện với StringBuffer: "+ sbftime);


        System.out.println("Nhận xét:");
        System.out.println("- String: Không hiệu quả cho phép nối chuỗi nhiều lần do tạo ra nhiêu đối tượngới");
        System.out.println("- StringBuilder: Hiệu quả và nhanh chóng, thích hợp cho nhiều thao tác nối chuỗi trong 1 luồng");
        System.out.println("- StringBuffer: Tương tự như StringBuilder nhưng an toàn với đa luồng, có thể chậm hơn một chút do đồng bộ hóa");

    }
}
