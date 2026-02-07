package BT5.Model;

import java.math.BigDecimal;

public interface IAccountModel extends IBaseModel {
    void deposit(double amount);
    void withdraw(double amount);
    void transfer(Account accTo, double amount);
}
