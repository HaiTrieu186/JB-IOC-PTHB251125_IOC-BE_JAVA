package BT4;

public class Laptop extends Device implements Connectable, Chargeable {

    public Laptop() {
    }

    public Laptop(int id, String name) {
        super(id, name);
    }

    @Override
    public void charge() {
        System.out.println("Sạc laptop");
    }

    @Override
    public void connectWifi() {
        System.out.println("Kết nối wifi với laptop thành công");
    }

    @Override
    public void turnOn() {
        System.out.println("Mở laptop");
    }

    @Override
    public void turnOff() {
        System.out.println("Tắt laptop");
    }
}
