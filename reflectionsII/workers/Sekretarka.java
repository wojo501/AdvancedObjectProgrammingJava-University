package pl.edu.pw.mini.jrafalko.workers;

import pl.edu.pw.mini.jrafalko.censor.EmptyString;
import pl.edu.pw.mini.jrafalko.censor.RunImmediately;
import pl.edu.pw.mini.jrafalko.main.Pracownik;

@EmptyString
public class Sekretarka extends Pracownik {

    private String drugieImie;
    private String opiniaZewnetrzna;

    public Sekretarka(String imie, String drugieImie, String nazwisko, int wiek,
                      String opiniaZewnetrzna) {
        super(imie, nazwisko, wiek);
        this.drugieImie = drugieImie;
        this.opiniaZewnetrzna = opiniaZewnetrzna;
    }

    @RunImmediately(times = 3)
    @Override
    protected void zwiekszZysk() {
        wypracowanyZysk += 4;
    }

    @Override
    public String toString() {
        return "Pracownik:" +
                " imie='" + imie + '\'' +
                ", drugieImie='" + drugieImie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", wiek=" + wiek +
                ", wypracowanyZysk=" + wypracowanyZysk +
                ", opiniaZewnetrzna='" + opiniaZewnetrzna + '\'' +
                ", sekretarka";
    }
}
