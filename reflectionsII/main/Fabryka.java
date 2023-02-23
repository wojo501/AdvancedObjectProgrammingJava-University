package pl.edu.pw.mini.jrafalko.main;

import pl.edu.pw.mini.jrafalko.workers.Prezes;
import pl.edu.pw.mini.jrafalko.workers.Inzynier;
import pl.edu.pw.mini.jrafalko.workers.Sekretarka;
import pl.edu.pw.mini.jrafalko.workers.Monter;

import java.util.ArrayList;
import java.util.List;

public class Fabryka {

    public static List<Pracownik> tworzZalogeFabryki() {
        List<Pracownik> pracownicy = new ArrayList<>();

        pracownicy.add(new Prezes("Maria","Wawrzyniec", 32,
                "Chuda", "Mońki",
                "Bardzo twarda dla pracowników", 120));
        pracownicy.add(new Inzynier("Marian","Wolny",30,
                "Silnik rakietowy", "Lubi zaszaleć",10000));
        pracownicy.add(new Sekretarka("Anna", "Weronika", "Mazur",47,
                "Wie zbyt dużo"));
        pracownicy.add(new Monter("Marcin", "Ochojski", 29,
                333, Produkty.BOMBA));
        pracownicy.add(new Monter("Grzegorz", "Nowicki", 48,
                105996, Produkty.AMUNICJA));
        pracownicy.add(new Monter("Dariusz", "Kurowski", 38,
                3876, Produkty.PISTOLET));

        pracownicy.forEach(pracownik -> pracownik.zwiekszZysk());

        return pracownicy;
    }

}
