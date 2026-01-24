package LT03;

public class Main {
    public static void main(String[] args) {
        Student[] students = new Student[3];
        students[0] = new Student("Nguyen Chi Thinh",20,9.5);
        students[1] = new Student("Pham Nguyen Hai Trieu",19,10);
        students[2] = new Student("Mohamach Bubaka",20,10);

        System.out.println("----------- Student List -----------");
        for (int i = 0; i<students.length; i++) {
            System.out.println("Student "+(i+1)+ ": ");
            students[i].printInfo();
            System.out.println();
        }

        System.out.println("Tổng số sinh viên: "+ Student.getCount());
    }
}
