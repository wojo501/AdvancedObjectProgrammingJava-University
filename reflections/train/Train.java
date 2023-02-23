package pl.edu.pw.mini.jrafalko.train;

import pl.edu.pw.mini.jrafalko.cargo.Car;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Train {

    private Locomotive locomotive = new Locomotive();
    private Wagons wagons = new Wagons();
    private Trolley trolley;
    private FuelTank fuelTank;

    public class Locomotive {

        private Driver driver = new Driver();
        private IgnitionSwitch ignitionSwitch;

        public class Driver {

            private Driver() {}

            private void drive() {

                if (ignitionSwitch == null) {
                    System.out.println("Brak stacyjki? Może popchnąć?");
                } else if (fuelTank == null || !fuelTank.isFilled()) {
                    System.out.println("Bez paliwa? Na stację mam podjechać?");
                } else if (trolley == null) {
                    System.out.println("Bez drezyny nie jadę");
                } else {
                    System.out.println("Ruszyła maszyna...");
                }
            }

        }

        private class IgnitionSwitch {

            private boolean complicated;

            public IgnitionSwitch(boolean complicated) {
                this.complicated = complicated;
            }

            private void start() {
                if (driver != null) {
                    System.out.println("puf puf puf puf...");
                } else {
                    System.out.println("Bez kierowcy nie ma jazdy...");
                }
            }

        }

    }

    public class Wagons {

        private List<Car> cars = new ArrayList<>();

        private void addCar(Car car) {
            cars.add(car);
        }

        private String printWagons() {
            return "Dołączone wagony: " + cars;
        }

    }

    public class FuelTank {

        private boolean filled;

        public FuelTank() {}

        public FuelTank(boolean filled) {
            this.filled = filled;
        }


        public boolean isFilled() {
            return filled;
        }

    }

}
