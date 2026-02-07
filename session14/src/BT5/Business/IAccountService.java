package BT5.Business;

import BT5.Model.Account;

public interface IAccountService extends IBaseService<Account, String>{
    boolean existById(String id);
}
