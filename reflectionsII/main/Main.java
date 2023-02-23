package pl.edu.pw.mini.jrafalko.main;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Pracownik> pracownicy = Fabryka.tworzZalogeFabryki();
        Cenzor cenzor = new Cenzor();
        for(Pracownik p : cenzor.cenzuruj(pracownicy)){
            System.out.println(p);
        }
    }
}
