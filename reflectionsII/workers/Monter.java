package pl.edu.pw.mini.jrafalko.workers;

import pl.edu.pw.mini.jrafalko.censor.DeleteWorker;
import pl.edu.pw.mini.jrafalko.censor.ReplaceInt;
import pl.edu.pw.mini.jrafalko.censor.ReplaceIntEnum;
import pl.edu.pw.mini.jrafalko.censor.RunImmediately;
import pl.edu.pw.mini.jrafalko.main.Pracownik;
import pl.edu.pw.mini.jrafalko.main.Produkty;

@DeleteWorker
public class Monter extends Pracownik {

    @ReplaceInt
    private int iloscWyprodukowanychElementow;
    @ReplaceIntEnum
    private Produkty produkowanyElement;

    public Monter(String imie, String nazwisko, int wiek,
                  int iloscWyprodukowanychElementow, Produkty produkowanyElement) {
        super(imie, nazwisko, wiek);
        this.iloscWyprodukowanychElementow = iloscWyprodukowanychElementow;
        this.produkowanyElement = produkowanyElement;
    }

    @RunImmediately(times = 10)
    @Override
    protected void zwiekszZysk() {
        wypracowanyZysk += 2;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", iloscWyprodukowanychElementow=" + iloscWyprodukowanychElementow +
                ", produkowanyElement=" + produkowanyElement +
                ", monter";
    }
}
