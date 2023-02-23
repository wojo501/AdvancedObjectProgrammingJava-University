package pl.edu.pw.mini.jrafalko;

import pl.edu.pw.mini.jrafalko.cargo.*;
import pl.edu.pw.mini.jrafalko.train.*;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        System.out.println("\n--------1---------");
        task1();
        System.out.println("\n--------2---------");
        task2();
        System.out.println("\n--------3---------");
        task3();
        System.out.println("\n--------4---------");
        task4();
        System.out.println("\n--------5---------");
        task5();
        System.out.println("\n--------6---------");
        task6();
        System.out.println("\n--------7---------");
        task7();
        System.out.println("\n--------8---------");
        task8();
        System.out.println("\n--------9---------");
        List<Car> wagony = task9();
        System.out.println("\n--------10---------");
        Train pociag = task10();
        System.out.println("\n--------11---------");
        task11(pociag, wagony);
        System.out.println("\n--------12---------");
        task12(pociag);
        System.out.println("\n--------13---------");
        task13(pociag);
        System.out.println("\n--------14---------");
        task14(pociag);

    }

    public static void task1(){
        Constructor[] constructors = TankWagon.class.getDeclaredConstructors();

        for (Constructor constructor: constructors) {
            Parameter[] parameters = constructor.getParameters();
            String result = "";
            result += (String)(constructor.getName() + "{");
            if(parameters.length > 0){
                for (Parameter parameter : parameters) {
                    result+= (String) (parameter.getName() + " ");
                }
            } else {
                result+= "Konstruktor nie ma parametrow";
            }
            result += "}";
            System.out.println(result);
        }
    }


    public static void task2(){
        Constructor[] constructors = PassengerCar.class.getDeclaredConstructors();
        boolean result = false;
        for (Constructor constructor:constructors) {
            Set<Class> checkedClasses = Set.of(String.class, boolean.class);
            if(constructor.getParameterCount() == 2 && Modifier.toString(constructor.getModifiers()).equals("protected")){
                result = true;
                for (Class<?> type: constructor.getParameterTypes()) {
                    if(!(checkedClasses.contains(type))){
                        result = false;
                    };
                }

            }
        }
        System.out.println("Konstruktor istnieje:" + result);
    }


    public static void task3(){
        System.out.println(Car.class.getPackage());
    }


    public static void task4(){
        for (Method method: DiningCar.class.getDeclaredMethods()){
            String result = "";
            if(Modifier.isPrivate(method.getModifiers())){
                result += "Metoda prywatna: " + method.getName() + "{";
                if (method.getParameters().length > 0){
                    for (Parameter parameter: method.getParameters()) {
                        result += parameter;
                        result += " ";
                    }
                result += "}";
                } else {
                    result += "Nie ma parametrow}";
                }
                System.out.println(result);
            }
        }

    }

    public static void task5(){
        for(Field field : Trolley.class.getDeclaredFields()){
            if("sticker".equals(field.getName())){
                try {
                    field.setAccessible(true);
                    field.set(null, "Halo");
                    System.out.println(Trolley.class.getField("sticker").get(null));
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }


    public static void task6(){
        System.out.println(TankWagon.class.getSuperclass());
    }


    public static void task7(){
        for(Class<?> interfacetype : Car.class.getInterfaces()){
            boolean check = interfacetype.getPackage()==Car.class.getPackage();
            System.out.println(interfacetype.getName() + " [w tym samym pakiecie co klasa Car: " + check +"]");
        }
    }


    public static void task8() {
        Constructor[] constructors = Trolley.class.getConstructors();
        Trolley trolley;
        try {
            trolley = (Trolley) constructors[0].newInstance();
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        try {
            Field field = trolley.getClass().getDeclaredField("trolleySize");
            field.setAccessible(true);
            System.out.println("Wartosc pola trolleySize: " + field.get(trolley));

        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Car> task9(){
        List<Car> wagony = new ArrayList<>();

        Constructor[] constructors1 = DiningCar.class.getDeclaredConstructors();
        Constructor[] constructors2 = PassengerCar.class.getDeclaredConstructors();
        Constructor[] constructors3 = TankWagon.class.getDeclaredConstructors();

        try {
            wagony.add((Car) constructors1[0].newInstance());

            wagony.add((Car) constructors2[0].newInstance(true));
            constructors2[1].setAccessible(true);
            wagony.add((Car) constructors2[1].newInstance());
            constructors2[2].setAccessible(true);
            wagony.add((Car) constructors2[2].newInstance(false, "WKD"));

            wagony.add((Car) constructors3[0].newInstance());
            constructors3[1].setAccessible(true);
            wagony.add((Car) constructors3[1].newInstance(true));
            System.out.println("Stworzono wagony");
            return(wagony);
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }


    public static Train task10(){
        try {
            Constructor constructor = Train.class.getConstructor();
            Train train = (Train) constructor.newInstance();
            System.out.println("Stworzono pociąg");
            return(train);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }


    public static void task11(Train pociag, List<Car> wagony){
        try {
            Field field = pociag.getClass().getDeclaredField("wagons");
            field.setAccessible(true);
            Class[] arg = {Car.class};
            Method addCar = field.get(pociag).getClass().getDeclaredMethod("addCar", arg);
            addCar.setAccessible(true);
            for (Car wagon : wagony){
                addCar.invoke(field.get(pociag), wagon);
            }
            System.out.println("Dodano wagony do pociagu");
        } catch (NoSuchFieldException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }


    public static void task12(Train pociag){
        try {
            Field trolleyField = pociag.getClass().getDeclaredField("trolley");
            trolleyField.setAccessible(true);
            Field fuelTankField = pociag.getClass().getDeclaredField("fuelTank");
            fuelTankField.setAccessible(true);


            Constructor trolleyConstructor = Trolley.class.getConstructor();
            Trolley trolley = (Trolley) trolleyConstructor.newInstance();
            if(trolley.getClass().isAnnotationPresent(TrolleyCompany.class)) {
                System.out.println("Marka drezyny: " + TrolleyCompany.nazwaProducenta);
                System.out.println("Jak duza jest drezyna: " + TrolleyCompany.wielkoscDrezyny);
            }
            trolleyField.set(pociag, trolley);

            Constructor[] fuelTankConstructors = fuelTankField.getType().getConstructors();
            Train.FuelTank fuelTank = (Train.FuelTank) fuelTankConstructors[1].newInstance(pociag, true);
            // pierwszy argument to instancja klasy w ktorej znajduje sie klasa FuelTank
            fuelTankField.set(pociag, fuelTank);
            System.out.println("Dodano drezynę oraz bak do pociagu");

        } catch (NoSuchFieldException | IllegalAccessException | InstantiationException | NoSuchMethodException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }


    public static void task13(Train pociag){
        try {
            Field locomotiveField = pociag.getClass().getDeclaredField("locomotive");
            locomotiveField.setAccessible(true);
            Field[] locoFields = locomotiveField.get(pociag).getClass().getDeclaredFields();

            for (Field field : locoFields){
                field.setAccessible(true);
                if(field.get(locomotiveField.get(pociag))==null){
                   Constructor[] constructors = field.getType().getConstructors();
                   constructors[0].setAccessible(true); // ustawiamy konstruktor jako accessible bo klasa IgnitionSwitch jest prywatna
                   field.set(locomotiveField.get(pociag), constructors[0].newInstance(locomotiveField.get(pociag), true));
                    System.out.println("Usunieto wszystkie problemy (czyli pola o wartosci null)");
                }
            }
        } catch (NoSuchFieldException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            throw new RuntimeException(e);
        }

    }


    public static void task14(Train pociag){
        try {
            Field locomotiveField = pociag.getClass().getDeclaredField("locomotive");
            locomotiveField.setAccessible(true);
            Field driverField = locomotiveField.get(pociag).getClass().getDeclaredField("driver");
            driverField.setAccessible(true);

            Method drive = driverField.get(locomotiveField.get(pociag)).getClass().getDeclaredMethod("drive", null);
            drive.setAccessible(true);
            drive.invoke(driverField.get(locomotiveField.get(pociag)));

        } catch (NoSuchFieldException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }


}
