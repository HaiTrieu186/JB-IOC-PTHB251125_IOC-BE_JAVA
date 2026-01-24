package BT1;

public class Student {
    private int id;
    private String name;
    private int age;

    public Student() {}

    public Student(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static void main(String[] args) {
        Student student1 = new Student(1,"Nguyen Van A", 18 );
        Student student2 = new Student(2,"Nguyen Thi C", 20 );

        System.out.printf("SV1 - ID: %d, Name: %s, Age: %d\n ", student1.getId(), student1.getName(), student1.getAge());
        System.out.printf("SV2 - ID: %d, Name: %s, Age: %d\n ", student2.getId(), student2.getName(), student2.getAge());
    }
}
