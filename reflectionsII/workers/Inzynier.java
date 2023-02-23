package pl.edu.pw.mini.jrafalko.workers;

import pl.edu.pw.mini.jrafalko.censor.ReplaceString;
import pl.edu.pw.mini.jrafalko.censor.Shortcut;
import pl.edu.pw.mini.jrafalko.main.Pracownik;


public class Inzynier extends Pracownik {
    @Shortcut
    private String aktualnyProjekt;
    @ReplaceString
    private String opiniaKolegow;
    private int zarobki;

    public Inzynier(String imie, String nazwisko, int wiek,
                    String aktualnyProjekt, String opiniaKolegow, int zarobki) {
        super(imie, nazwisko, wiek);
        this.aktualnyProjekt = aktualnyProjekt;
        this.opiniaKolegow = opiniaKolegow;
        this.zarobki = zarobki;
    }

    @Override
    protected void zwiekszZysk() {
        wypracowanyZysk += 3;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", aktualnyProjekt='" + aktualnyProjekt + '\'' +
                ", opiniaKolegow='" + opiniaKolegow + '\'' +
                ", zarobki=" + zarobki + " zł " +
                ", inżynier";
    }
}
