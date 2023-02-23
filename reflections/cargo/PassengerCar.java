package pl.edu.pw.mini.jrafalko.cargo;

import pl.edu.pw.mini.jrafalko.Reflectionable;

public class PassengerCar extends Car implements Reflectionable {

    private String materialOfChairs;
    private String label;

    public PassengerCar(boolean metal) {
        if(metal) {
            this.materialOfChairs = "Metal";
        } else {
            this.materialOfChairs = "Drewno";
        }
    }

    private PassengerCar() {
        this.materialOfChairs = "Plastyk";
    }

    private PassengerCar(boolean metal, String label) {
        this(metal);
        this.label = label;
    }


}