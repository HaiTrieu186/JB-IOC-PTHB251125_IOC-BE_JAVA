package LT02.business;

import LT02.model.Subject;

import java.util.List;

public interface ISubjectService extends IBaseService<Subject, String>{
    List<Subject> findSubjectsByName(String name);
    List<Subject> findSubjectsByCredit(int credits);
    boolean isExistByCode(String code);
}
