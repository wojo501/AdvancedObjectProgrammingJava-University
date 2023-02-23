package pl.edu.pw.mini.jrafalko.workers;

import pl.edu.pw.mini.jrafalko.censor.ReplaceIntEnum;
import pl.edu.pw.mini.jrafalko.censor.ReplaceString;
import pl.edu.pw.mini.jrafalko.censor.Shortcut;
import pl.edu.pw.mini.jrafalko.main.Pracownik;


public class Prezes extends Pracownik {
    private String ksywka;
    @ReplaceString(text = "Władywostok")
    private String miastoUrodzenia;
    @Shortcut
    private String charakterystykaOsobowosci;
    @ReplaceIntEnum
    private int iloscPodwladnych;

    public Prezes(String imie, String nazwisko, int wiek, String ksywka,
                  String miastoUrodzenia, String charakterystykaOsobowosci,
                  int iloscPodwladnych) {
        super(imie, nazwisko, wiek);
        this.ksywka = ksywka;
        this.miastoUrodzenia = miastoUrodzenia;
        this.charakterystykaOsobowosci = charakterystykaOsobowosci;
        this.iloscPodwladnych = iloscPodwladnych;
    }

    @Override
    protected void zwiekszZysk() {
        wypracowanyZysk += 10;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", ksywka='" + ksywka + '\'' +
                ", miastoUrodzenia='" + miastoUrodzenia + '\'' +
                ", charakterystykaOsobowosci='" + charakterystykaOsobowosci + '\'' +
                ", iloscPodwladnych=" + iloscPodwladnych +
                ", prezes";
    }
}
