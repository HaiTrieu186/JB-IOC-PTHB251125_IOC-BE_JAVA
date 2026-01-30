package BT4;

public class Main {
    public static void main(String[] args) {
        Device[] devices = new Device[3];
        devices[0]=new Laptop(1,"MSI GF63");
        devices[1]=new SmartPhone(2,"IPhone XS");
        devices[2]=new Television(3,"TCL LED");

        for (int i = 0; i<devices.length; i++) {
            Device device = devices[i];
            System.out.println("---Thiết bị thứ "+(i+1)+"---");
            device.turnOn();
            device.turnOff();

            if (device instanceof Laptop) {
                Laptop laptop = (Laptop) device;
                laptop.connectWifi();
                laptop.charge();
            } else
            if (device instanceof SmartPhone) {
                SmartPhone smartPhone = (SmartPhone) device;
                smartPhone.connectWifi();
                smartPhone.charge();
            } else
            if (device instanceof Television) {
                Television television = (Television) device;
                television.connectWifi();
            }
        }
    }
}
