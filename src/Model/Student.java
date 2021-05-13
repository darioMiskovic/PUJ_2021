package Model;

import Baza.Konekcija;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Student {

    private String ime;
    private String prezime;
    private String JMBG;
    private String brojIndeksa;

    public Student(String ime, String prezime, String JMBG, String brojIndeksa)throws Exception {
        if(brojIndeksa.equals("0")){
            throw new Exception("Nastala je greška, broj indeksa ne smije biti 0.");
        }
        this.ime = ime;
        this.prezime = prezime;
        this.JMBG = JMBG;
        this.brojIndeksa = brojIndeksa;
    }


    //Get & Set ime
    public String getIme() {return ime;}

    public void setIme(String ime) { this.ime = ime;}

    //Get & Set prezime
    public String getPrezime() { return prezime;}

    public void setPrezime(String prezime) {this.prezime = prezime;}

    //Get & Set JMBG
    public String getJMBG() {return JMBG;}

    public void setJMBG(String JMBG) { this.JMBG = JMBG;}

    //Get & Set broj indeksa
    public String getBrojIndeksa() {return brojIndeksa;}

    public void setBrojIndeksa(String brojIndeksa) {this.brojIndeksa = brojIndeksa;}


    @Override
    public String toString(){
        return  this.ime + " " + this.prezime+ " " + this.brojIndeksa;
    }


    public static List<Student> dohvatiStudente () throws Exception {

        Connection konekcija = Konekcija.BAZA.getKonekcija();


        //Lista svih objekata
        List<Student> studenti = new ArrayList<Student>();

        //Upit koji se izvršava
        Statement iskaz = null;
        try {
            iskaz = konekcija.createStatement();
            ResultSet rs =iskaz.executeQuery("select * from osoba where uloga = 'student'");

            //Popunjavanje liste sa objektima
            while(rs.next()){
                studenti.add(new Student(
                        rs.getString("ime"),
                        rs.getString("prezime"),
                        rs.getString("JMBG"),
                        rs.getString("brojIndeksa")
                ));
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return studenti;


    }


}
