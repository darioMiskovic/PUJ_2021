package Model;

public class Predmet {
    private String naziv;
    private  int ECTS;

    public Predmet(String naziv, int ECTS){
        this.naziv = naziv;
        this.ECTS = ECTS;
    }

    @Override
    public String toString(){
        return  naziv;
    }
}
