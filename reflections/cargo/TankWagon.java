package pl.edu.pw.mini.jrafalko.cargo;

public class TankWagon extends Car {

    private int capacity;
    private int content;

    public TankWagon() {
        this.capacity = 2000;
    }

    private TankWagon(boolean big) {
        if(big) {
            this.capacity = 30000;
        } else {
            this.capacity = 10000;
        }
    }

    private void fillTheTank() {
        this.content = capacity;
    }

    private void pour() {
        if(content > 0) {
            content--;
        }
    }
}
