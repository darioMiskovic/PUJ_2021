package Model;

import java.util.ArrayList;
import java.util.List;

public class Nastavnik {

    private String ime;
    private String prezime;
    private String JMBG;
    private List<Predmet> predmeti;

    public Nastavnik(String ime, String prezime, String JMBG) {
        this.ime = ime;
        this.prezime = prezime;
        this.JMBG = JMBG;
        this.predmeti = new ArrayList<>();
    }

    public void dodajPredmet(Predmet p) {
        this.predmeti.add(p);
    };

    public void ispisPredmeta() {
        for (Predmet p : this.predmeti){
            System.out.println(p);
        }
    };

    @Override
    public String toString(){
        return  this.ime + " " + this.prezime;
    }
}

