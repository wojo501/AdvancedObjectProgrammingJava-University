package pl.edu.pw.mini.jrafalko.cargo;

public class DiningCar extends Car {

    private String label;
    private int numberOfCustomers;

    private void addCustomer(int potatoes) {
        numberOfCustomers++;
    }

    private long numbersOfBeersBought() {
        return numberOfCustomers * 3l;
    }

    protected void sayHello() {
        System.out.println("Hello!");
    }

}
