package BT4;

public class Car {
    private int currentSpeed=0;

    public Car() {
    }

    public Car(int currentSpeed) {
        this.currentSpeed = currentSpeed;
    }

    public int getCurrentSpeed() {
        return currentSpeed;
    }

    public void setCurrentSpeed(int currentSpeed) {
        this.currentSpeed = currentSpeed;
    }

    public void acclerate(){
        this.currentSpeed+=10;
        System.out.println("Car acclerates by default: +10 km/h");
    };
    public void acclerate(int speed){
        this.currentSpeed+=speed;
        System.out.println("Car accelerates by " + speed + " km/h");
    };
    public void acclerate(int speed, int seconds){
        int increase= speed*seconds;
        this.currentSpeed+=increase;
        System.out.println("Car accelerates " + increase + " km/h (speed x time)");
    };

    public void printStatus(){
        System.out.println("Current speed: "+currentSpeed+" km/h");
    }
}
