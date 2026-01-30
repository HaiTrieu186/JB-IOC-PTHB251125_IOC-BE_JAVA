package BT3;

public class Main {
    public static void main(String[] args) {
        Employee[] e = new Employee[3];

        e[0]= new FullTimeEmployee(1,"Nguyen Van A");
        e[1]= new FullTimeEmployee(2,"Nguyen Van B");
        e[2]= new PartTimeEmployee(3,"Nguyen Van C",5);


        for(int i=0;i<e.length;i++){
            System.out.println("Lương nhân viên "+(i+1)+": "+e[i].calculateSalary());
        }
    }
}
