import java.util.*;
import java.util.function.Consumer;

public class Lotnisko {
    private List<Samolot> samoloty;
    protected Random rand = new Random();


    public Lotnisko(int ile){
        samoloty = new ArrayList<>();
        for (int i=0; i<ile; i++){
            Generuj g = ()->{
                StringBuilder sb= new StringBuilder();
                for (int j=0; j<rand.nextInt(20)+1; j++){
                    sb.append((char)(rand.nextInt(26)+ 'a'));
                }
                return sb.toString();
            };
            int pom= rand.nextInt(3);
            switch (pom){
                case 0:
                    samoloty.add(new SamolotPasażerski(g.generujNazwe(), rand.nextInt(501)+500, rand.nextInt(201)+100));
                    break;
                case 1:
                    samoloty.add(new SamolotTowarowy(g.generujNazwe(), rand.nextInt(401)+300, rand.nextInt(91)+10));
                    break;
                case 2:
                    samoloty.add(new Myśliwiec(g.generujNazwe(), rand.nextInt(2101)+900));
                    break;
            }
        }
    }
    @FunctionalInterface
    public interface Generuj{
        String generujNazwe();
    }

    public void wypisz(){
        for (Samolot samolot: samoloty){
            System.out.println(samolot);
        }
    }
    public void odprawaSamolotow() throws WyjatekLotniczy {
        int ilośćZaładunku=0;
        for (Samolot samolot: samoloty){
            if (samolot instanceof SamolotPasażerski){
                ilośćZaładunku=rand.nextInt(401);
            } else {
                if (samolot instanceof SamolotTowarowy){
                    ilośćZaładunku=rand.nextInt(201);
                } else {
                    ilośćZaładunku=rand.nextInt(11);
                }
            }
            try {
                samolot.odprawa(ilośćZaładunku);
            } catch (WyjatekLotniczy e){
                System.out.println(e.getMessage());
            }
        }
    }

    public void działaniaLotniskowe(){
        for (Samolot samolot: samoloty){
            Consumer<Samolot> działania = (x) ->{
                System.out.println(x);
                x.laduj();
                try {
                    x.odprawa(rand.nextInt(401));
                } catch (WyjatekLotniczy e) {
                    System.out.println(e.getMessage());
                }
                x.lec(10);
                if (x instanceof Myśliwiec){
                    ((Myśliwiec) x).atak();
                }
            };
            działania.accept(samolot);
        }
    }

    public void sortowanieSamolotowPoPredkosci(){
        samoloty.sort(Comparator.comparing(s->s.prędkośćMax));
    }

    public void sortowanieSamolotowPoNazwie(){
        samoloty.sort((s1, s2)->{
            if (s1.nazwa.length()<=5){
                return 1;
            } else {
                if (s2.nazwa.length()<=5){
                    return -1;
                } else {
                    return s1.nazwa.compareTo(s2.nazwa);
                }
            }
        });
    }

    public interface Losuj{
        boolean losujSortowanie();
    }

    public void sortowanieLosowe(){
        Losuj l = ()->{
                return rand.nextBoolean();
        };
        if (l.losujSortowanie()){
            sortowanieSamolotowPoPredkosci();
        } else {
            sortowanieSamolotowPoNazwie();
        }
    }



//    public String generujNazwe(){
//        StringBuilder sb= new StringBuilder();
//        for (int i=0; i<rand.nextInt(20)+1; i++){
//            sb.append((char)(rand.nextInt(26)+ 'a'));
//        }
//        return sb.toString();
//    }

    public static abstract class Samolot {
        protected String nazwa;
        protected int prędkośćMax;
        protected int ilośćGodzin=0;
        protected boolean czyOdprawa=false;
        protected boolean czyLeci=false;

        public Samolot(String nazwa, int prędkośćMax) {
            this.nazwa = nazwa;
            this.prędkośćMax = prędkośćMax;
        }

        public void lec(int czasLotu){
            if (czyLeci){
                System.out.println("Lecimy");
            } else {
                if (czyOdprawa){
                    System.out.println("Starujemy");
                    czyLeci=true;
                    ilośćGodzin+=czasLotu;
                } else {
                    System.out.println("Nie możemy wystartować");
                }
            }
        }

        public void laduj(){
            if (czyLeci){
                System.out.println("Lądujemy");
                czyLeci=false;
                czyOdprawa=false;
            } else {
                System.out.println("I tak jesteśmy na ziemii");
            }
        }

        public void odprawa(int ilośćZaładunku) throws WyjatekLotniczy {
            throw new WyjatekLotniczy();
        }

        @Override
        public String toString(){
            return "";
        }
    }

    public class SamolotPasażerski extends Samolot {
        protected int maxIlośćPasażerów;
        protected int aktIlośćPasażerów;

        public SamolotPasażerski(String nazwa, int prędkośćMax, int maxIlośćPasażerów) {
            super(nazwa, prędkośćMax);
            this.maxIlośćPasażerów=maxIlośćPasażerów;
        }

        @Override
        public void odprawa(int aktIlośćPasażerów) throws WyjatekEkonomczny {
            if (aktIlośćPasażerów<maxIlośćPasażerów){
                throw new WyjatekEkonomczny("Za mało pasażerów, nie opłaca się lecieć");
            } else {
                if (aktIlośćPasażerów>maxIlośćPasażerów){
                    throw new WyjatekEkonomczny("Za dużo o "+ (aktIlośćPasażerów-maxIlośćPasażerów) +" pasażerów");
                } else {
                    czyOdprawa=true;
                }
            }
        }

        @Override
        public String toString() {
            String s= "Samolot pasażerski o nazwie '" + nazwa + "'. Prędkość maksymalna " + prędkośćMax +
                    ", w powietrzu spędził łącznie " + ilośćGodzin + " godzin, może zabrać na pokład " + maxIlośćPasażerów +
                    " pasażerów. ";
            if (czyLeci){
                return s+ "Obecnie leci z " + aktIlośćPasażerów + " pasażerami na pokładzie.";
            } else {
                return s+"Aktualnie uziemiony.";
            }
        }
    }

    public class SamolotTowarowy extends Samolot {
        protected int maxŁadunek;
        protected int aktŁadunek;
        public SamolotTowarowy(String nazwa, int prędkośćMax, int maxŁadunek) {
            super(nazwa, prędkośćMax);
            this.maxŁadunek=maxŁadunek;
        }

        @Override
        public void odprawa(int aktLadunek) throws WyjatekLotniczy {
            if (aktŁadunek<maxŁadunek){
                throw new WyjatekLotniczy("Za mały ładunek, nie opłaca się lecieć");
            } else {
                if (aktŁadunek>maxŁadunek){
                    throw new WyjatekLotniczy("Za dużo o "+ (aktŁadunek-maxŁadunek) +"  ton ładunku");
                } else {
                    czyOdprawa=true;
                }
            }
        }

        @Override
        public String toString() {
            String s= "Samolot towarowy o nazwie '" + nazwa + "'. Prędkość maksymalna " + prędkośćMax +
                    ", w powietrzu spędził łącznie " + ilośćGodzin + " godzin, może zabrać na pokład " + maxŁadunek +
                    " ton ładunku. ";
            if (czyLeci){
                return s+ "Obecnie leci z " + aktŁadunek + " tonami ładunku na pokładzie.";
            } else {
                return s+"Aktualnie uziemiony.";
            }
        }
    }

    public class Myśliwiec extends Samolot {
        protected int ilośćRakiet;
        public Myśliwiec(String nazwa, int prędkośćMax) {
            super(nazwa, prędkośćMax);
        }

        @Override
        public void odprawa(int ilośćZaładunku){
            this.ilośćRakiet=ilośćZaładunku;
            czyOdprawa=true;
        }

        public void atak(){
            if (czyLeci){
                ilośćRakiet-=1;
                System.out.println("Ataaaaak");
            }
            if (ilośćRakiet==0){
                laduj();
            }
        }
        @Override
        public String toString() {
            String s= "Myśliwiec o nazwie '" + nazwa + "'. Prędkość maksymalna " + prędkośćMax +
                    ", w powietrzu spędził łącznie " + ilośćGodzin + " godzin. ";
            if (czyLeci){
                return s+ "Obecnie leci, rakiet: " + ilośćRakiet + " .";
            } else {
                return s+"Aktualnie uziemiony.";
            }
        }
    }
}
