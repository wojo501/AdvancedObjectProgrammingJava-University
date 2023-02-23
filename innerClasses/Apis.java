import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class Apis {
    private ArrayList<Pszczola> listaPszczol;
    public static Random generator = new Random();

    public Apis() {
        this.listaPszczol = new ArrayList<Pszczola>();
        this.listaPszczol.add(new KrolowaMatka("Matka", generator.nextInt(360), generator.nextInt(1000)));
    }

    public void zyciePszczol(){
        for (Pszczola pszczola: listaPszczol) {
            if(pszczola instanceof Robotnica){
                pszczola.run();
            }
            if (pszczola instanceof KrolowaMatka){
                int i = 0;
                for (Pszczola pszczola1: listaPszczol) {
                    if (pszczola1 instanceof Truten){
                        ((Truten) pszczola1).zaplodnienie((KrolowaMatka) pszczola);
                        i++;
                        if (i >= 2) break;
                    }
                }
            }
        }
    }

    public void sortujWgSilyIImienia(){
        Comparator<Pszczola> porownanieSilyiImienia = new Comparator<Pszczola>() {
            @Override
            public int compare(Pszczola o1, Pszczola o2) {
                if (o1.silaAtaku == o2.silaAtaku){
                    return o1.imie.compareTo(o2.imie);
                }
                return o2.silaAtaku - o1.silaAtaku;
            }
        };
        listaPszczol.sort(porownanieSilyiImienia);
    }

    public void sortujWgSily(){
        PorownanieSily porownanieSily = new PorownanieSily();
        listaPszczol.sort(porownanieSily);
    }

    public void dodajZolnierza(){
        Pszczola zolnierz = new Pszczola("Helena", 99, 10) {
            @Override
            public void run() {
                System.out.println("Walka to moje życie!!!");
            }

            @Override
            public String toString() {
                return "Żołnierz Helena (atak: 99), żyję 10 dni i potrafię użądlić!";
            }
        };

        listaPszczol.add(zolnierz);
    }

    public void dodajPszczole(Pszczola pszczola){
        listaPszczol.add(pszczola);
    }

    public void watkiPszczol(){
        for (Pszczola pszczola: listaPszczol) {
            pszczola.run();
        }
    }

    @Override
    public String toString() {
        String napis = " ";
        for (Pszczola pszczola: listaPszczol) {
            napis += pszczola.toString();
            napis += "\r\n";
        }
        return napis;
    }

    // klasa wewnetrzna pszczola
    public abstract class Pszczola implements Runnable{
        protected String imie;
        protected int silaAtaku;
        protected int wiek;

        public Pszczola(String imie, int silaAtaku, int wiek) {
            this.imie = imie;
            this.silaAtaku = silaAtaku;
            this.wiek = wiek;
        }
    }

    // klasa krolowa Matka
    public class KrolowaMatka extends Pszczola{
        protected int iloscJaj;

        public KrolowaMatka(String imie, int wiek, int iloscJaj) {
            super(imie, 100, wiek);
            this.iloscJaj = iloscJaj;
        }

        private void zaplodnienie(){
            this.iloscJaj += 1000;
        }

        @Override
        public void run() {
            System.out.println("Lot godowy...");
        }

        @Override
        public String toString() {
            return " Krolowa " + imie + " atak: " + silaAtaku +
                    " zyje " + wiek + " dni i bede matka dla " +
                    iloscJaj + " mlodych pszczolek ";
        }
    }
    //koniec klasy KrolowaMatka

    // klasa Truten
    public class Truten extends Pszczola {
        protected boolean przydatny;

        public Truten(String imie, int wiek) {
            super(imie, 0, wiek);
            this.przydatny = true;
        }

        public void zaplodnienie(KrolowaMatka krolowa) {
            if (this.przydatny) {
                this.przydatny = false;
                krolowa.zaplodnienie();
                System.out.println(this.imie + " - bylem z krolowa, mozna umierac");
            }
        }

        @Override
        public void run() {
            int prawd = generator.nextInt(101);
            if (prawd <= 50) {
                for (Pszczola pszczola : listaPszczol) {
                    if (pszczola instanceof KrolowaMatka) {
                        this.zaplodnienie((KrolowaMatka) pszczola);
                        break;
                    }
                }
            }
        }

        @Override
        public String toString() {
            return " Truteń "+ imie +" (atak:"+ silaAtaku +" ), spełniłem swoje zadanie :( ";
        }
    }// koniec klasa Truten


        public class Robotnica extends Pszczola{
            protected int iloscWyprMiodu;

            public Robotnica(String imie, int silaAtaku, int wiek, int iloscWyprMiodu) {
                super(imie, silaAtaku, wiek);
                this.iloscWyprMiodu = iloscWyprMiodu;
            }

            public void zbierajNektar(int ilosc){
                iloscWyprMiodu += ilosc;
            }

            @Override
            public void run() {
                this.zbierajNektar(generator.nextInt(21));
            }

            @Override
            public String toString() {
                return " Robotnica " + imie
                        + " (atak: " + silaAtaku + " ), żyję " +
                        wiek + " dni i zrobiłam " + iloscWyprMiodu +
                        " baryłek miodu :)";
            }
        } //koniec klasy Robotnica

    private class PorownanieSily implements Comparator<Pszczola> {
        @Override
        public int compare(Pszczola o1, Pszczola o2) {
            return o2.silaAtaku-o1.silaAtaku;
        }
    }

    }

