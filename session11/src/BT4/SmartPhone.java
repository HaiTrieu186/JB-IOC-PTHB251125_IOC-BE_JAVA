package BT4;

public class SmartPhone extends Device implements Connectable, Chargeable {

    public SmartPhone() {
    }

    public SmartPhone(int id, String name) {
        super(id, name);
    }

    @Override
    public void turnOn() {
        System.out.println("Mở điện thoại");
    }

    @Override
    public void turnOff() {
        System.out.println("Tắt điện thoại");
    }

    @Override
    public void charge() {
        System.out.println("Sạc điện thoại");
    }

    @Override
    public void connectWifi() {
        System.out.println("Kết nối wifi với điện thoại thành công");
    }
}
