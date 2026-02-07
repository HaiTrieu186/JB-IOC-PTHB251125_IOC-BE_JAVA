package BT5.Business.impl;

import BT5.Business.IAccountService;
import BT5.Model.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountServiceImpl implements IAccountService {
    private List<Account> accounts=new ArrayList<Account>();

    @Override
    public boolean existById(String id) {
        for (Account account : accounts) {
            if (account.getAccountId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void add(Account account) {
        accounts.add(account);
    }

    @Override
    public void update(Account account, String id) {
        int index=accounts.indexOf(findById(id));

        if(index!=-1){
            accounts.set(index,account);
        }
    }

    @Override
    public void delete(String id) {
        Account a = findById(id);
        if(a!=null){
            accounts.remove(a);
        }
    }

    @Override
    public Account findById(String id) {
        for(Account account:accounts){
            if (account.getAccountId().equals(id)){
                return account;
            }
        };
        return null;
    }

    @Override
    public List<Account> findAll() {
        return accounts;
    }

    @Override
    public void sort() {

    }
}
