public class Main {

    public static void main(String[] args) throws WyjatekLotniczy {
        Lotnisko lotnisko = new Lotnisko(5);
        Lotnisko.Myśliwiec m = lotnisko.new Myśliwiec("a", 4);
        System.out.println("Pierwsze wypisanie----------");
        lotnisko.wypisz();
        lotnisko.sortowanieSamolotowPoNazwie();
        System.out.println("Sortowanie po nazwie----------");
        lotnisko.wypisz();
        lotnisko.sortowanieSamolotowPoPredkosci();
        System.out.println("Sortowanie po predkości---------------------");
        lotnisko.wypisz();
        lotnisko.sortowanieLosowe();
        System.out.println("Sortowanie losowe---------------------");
        lotnisko.wypisz();
        lotnisko.odprawaSamolotow();
        lotnisko.działaniaLotniskowe();
        lotnisko.działaniaLotniskowe();

    }
}
