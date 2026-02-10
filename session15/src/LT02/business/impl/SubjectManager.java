package LT02.business.impl;

import LT02.business.ISubjectService;
import LT02.model.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SubjectManager implements ISubjectService {
    private List<Subject> subjects = new ArrayList<Subject>();

    @Override
    public List<Subject> findSubjectsByName(String name) {

        return subjects.stream()
                .filter(s -> s.getName()
                .toLowerCase().contains(name.toLowerCase()))
                .toList();
    }

    @Override
    public List<Subject> findSubjectsByCredit(int credits) {

        return subjects.stream()
                .filter(s -> s.getCredits() > credits)
                .toList();
    }

    @Override
    public boolean isExistByCode(String code) {
        return  subjects.stream().anyMatch(s -> s.getCode().equals(code));
    }

    @Override
    public void add(Subject subject) {
        subjects.add(subject);
    }

    @Override
    public void update(Subject subject, String code) {
        int index = subjects.indexOf(findByCode(code));
        if (index != -1) {
            subjects.set(index, subject);
        }
    }

    @Override
    public void delete(String code) {
        Subject subject = findByCode(code);
        if (subject != null) {
            subjects.remove(subject);
        }
    }

    @Override
    public Subject findByCode(String s) {
        for (Subject subject : subjects) {
            if (subject.getCode().equals(s)) {
                return subject;
            }
        }
        return null;
    }

    @Override
    public List<Subject> findAll() {
        return subjects;
    }

    @Override
    public void sort() {

    }

    public static void displayList(List<Subject> subjects) {
        if (subjects.isEmpty()) {
            System.out.println("Chưa có môn học nào !");
        }

        for (Subject subject : subjects) {
            subject.display();
        }
    }
}
