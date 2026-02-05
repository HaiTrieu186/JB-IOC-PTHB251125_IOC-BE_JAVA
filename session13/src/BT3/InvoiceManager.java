package BT3;

import BT2.Student;

import java.util.ArrayList;
import java.util.List;

public class InvoiceManager implements Manage<Invoice>{
    private final List<Invoice> invoices= new ArrayList<>();

    @Override
    public void add(Invoice item) {
        item.setId(getMaxId()+1);
        invoices.add(item);
    }

    @Override
    public void update(int index, Invoice item) {
        invoices.set(index, item);
    }

    @Override
    public void delete(int index) {
        if (index >= 0 && index < invoices.size()) {
            invoices.remove(index);
            System.out.println("Hóa đơn đã được xóa thành công !");
        } else {
            System.out.println("Không tồn tại vị trí cần xóa !");
        }
    }

    @Override
    public void display() {
        int i=1;
        for (Invoice item : invoices) {
            System.out.println("--- Hóa đơn thứ "+ (i++) +" ---");
            item.displayData();
        }
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }


    public int findIndexById(int id) {
        for (int i = 0; i< invoices.size(); i++) {
            if (invoices.get(i).getId() == id) return i;
        }
        return -1;
    }

    public Invoice getInvoiceByIndex(int index) {
        return  invoices.get(index);
    }

    public int getMaxId(){
        int maxId = 0;
        for (Invoice item : invoices) {
            if (item.getId() > maxId) maxId = item.getId();
        }
        return maxId;
    }

    public boolean checkIdExist(int id){
        for (Invoice item : invoices) {
            if (item.getId() == id) return true;
        }
        return false;
    }
}
