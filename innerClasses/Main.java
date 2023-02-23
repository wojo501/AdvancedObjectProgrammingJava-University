import java.util.Random;

public class Main {
    public static void main(String[] args) {

        Apis apis1 = new Apis();
        System.out.println(apis1);

        //tworzenie roznych pszczol
        Apis.KrolowaMatka krolowaMatka1 = apis1.new KrolowaMatka("Alicja",8,2000);
        Apis.KrolowaMatka krolowaMatka2 = apis1.new KrolowaMatka("Waleria",7,0);
        Apis.KrolowaMatka krolowaMatka3 = apis1.new KrolowaMatka("Kamila",5,3400);

        Apis.Robotnica robotnica1 = apis1.new Robotnica("Maja", 10,15,102);
        Apis.Robotnica robotnica2 = apis1.new Robotnica("Basia", 12, 10,23);
        Apis.Robotnica robotnica3 = apis1.new Robotnica("Marta", 6,12,30);

        Apis.Truten truten1 = apis1.new Truten("Gucio",12);
        Apis.Truten truten2 = apis1.new Truten("Tadek",4);
        Apis.Truten truten3 = apis1.new Truten("Radek",5);

        apis1.dodajPszczole(krolowaMatka1);
        apis1.dodajPszczole(krolowaMatka2);
        apis1.dodajPszczole(krolowaMatka3);
        apis1.dodajPszczole(robotnica1);
        apis1.dodajPszczole(robotnica2);
        apis1.dodajPszczole(robotnica3);
        apis1.dodajPszczole(truten1);
        apis1.dodajPszczole(truten2);
        apis1.dodajPszczole(truten3);

        System.out.println();
        apis1.zyciePszczol();


        System.out.println();
        System.out.println(apis1);

        System.out.println();
        System.out.println("Pszczoły posortowane wg siły i imienia");
        apis1.sortujWgSilyIImienia();
        System.out.println(apis1);

        System.out.println();
        System.out.println("Pszczoly posortowane wg siły");
        apis1.sortujWgSily();
        System.out.println(apis1);
        System.out.println();



        System.out.println();
        System.out.println("Żolnierz:");

        apis1.dodajZolnierza();
        System.out.println(apis1);



        System.out.println();
        System.out.println("Wątki pszczół:");
        apis1.watkiPszczol();



    }
}
