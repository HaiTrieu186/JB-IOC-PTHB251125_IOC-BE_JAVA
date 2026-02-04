package BT2;

import java.util.ArrayList;
import java.util.List;

public class AttendanceManager implements Manage<Student>{
    private static List<Student> students = new ArrayList<>();

    @Override
    public void add(Student item) {
        students.add(item);
    }

    @Override
    public void update(int index, Student item) {
        students.set(index, item);
    }

    @Override
    public void delete(int index) {
        Student student = findById(index);

        if(student != null){
            students.remove(student);
        }

    }

    public Student findById(int index) {
        for (Student student : students) {
            if (student.getId() == index) {
                return student;
            }
        }
        return null;
    }

    @Override
    public void display() {
        int i=1;
        for (Student student : students) {
            System.out.println("--- Sinh viên thứ "+ (i++) +" ---");
            student.displayData();
        }
    }

    public List<Student> getStudents() {
        return students;
    }


    public boolean checkIdExist(int id) {
        for (Student student : students) {
           if(student.getId() == id){
               return true;
           }
        }
        return false;

    }
}
