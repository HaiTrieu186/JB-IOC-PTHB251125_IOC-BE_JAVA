package BT4;

public class Television extends Device implements Connectable{

    public Television() {
    }

    public Television(int id, String name) {
        super(id, name);
    }

    @Override
    public void connectWifi() {
        System.out.println("Kết nối wifi với tivi thành công ");
    }

    @Override
    public void turnOn() {
        System.out.println("Tắt tivi");
    }

    @Override
    public void turnOff() {
        System.out.println("Mở tivi");
    }
}
