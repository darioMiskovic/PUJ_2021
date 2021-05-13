package Model;

import Baza.Konekcija;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Nastavnik {
    private int id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getJMBG() {
        return JMBG;
    }

    public void setJMBG(String JMBG) {
        this.JMBG = JMBG;
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

    //Obrisi nastavnike
    public static boolean brisi(Nastavnik nastavnik){
        Connection konekcija = Konekcija.BAZA.getKonekcija();
        String sql = "delete from  osoba where id = ?";

        PreparedStatement upit = null;
        try {

            upit = konekcija.prepareStatement(sql);
            upit.setInt(1,nastavnik.getId());
            upit.executeUpdate();
            return true;

        } catch (SQLException throwables) {
            System.out.println("Nastala je greška prilikom brisanja");
            return false;
        }


    }

    //Spremi nastavnike
    public static Nastavnik spasi(Nastavnik nastavnik){
        Connection konekcija = Konekcija.BAZA.getKonekcija();
        String sql = "insert into osoba values (null, ?,?,?,null,?)";


         try {
             PreparedStatement upit = konekcija.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

             upit.setString(1,nastavnik.getIme());
             upit.setString(2,nastavnik.getPrezime());
             upit.setString(3,nastavnik.getJMBG());
             upit.setString(4,"nastavnik");

             upit.executeUpdate();

             ResultSet rs = upit.getGeneratedKeys();

             if (rs.next()){
                 nastavnik.setId(rs.getInt(1));
             }

             return nastavnik;


        } catch (SQLException throwables) {
            System.out.println("Nastala je greška: Nisam uspio dodati nastavnika");
             return null;
        }


    }


    //Dohvati nastavnike
    public static List<Nastavnik> dohvatiNastavnike (){

        Connection konekcija = Konekcija.BAZA.getKonekcija();


        //Lista svih objekata
        List<Nastavnik> nastavnici = new ArrayList<Nastavnik>();

        //Upit koji se izvršava
        Statement iskaz = null;
        try {
            iskaz = konekcija.createStatement();
            ResultSet rs =iskaz.executeQuery("select * from osoba where uloga = 'nastavnik'");

            //Popunjavanje liste sa objektima
            while(rs.next()){
                Nastavnik n = (new Nastavnik(
                        rs.getString("ime"),
                        rs.getString("prezime"),
                        rs.getString("JMBG")
                ));
                n.setId(rs.getInt("id"));

                nastavnici.add(n);

            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return nastavnici;


    }
}

