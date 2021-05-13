package Baza;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Konekcija {

    private String nazivRacunala ="localhost";
    private String nazivBaze = "evidencija";
    private String korisnik ="root";
    private  String lozinka = "";
    public Connection konekcija;

    public final static Konekcija BAZA = new Konekcija();

    public Connection getKonekcija() {
        return konekcija;
    }

    public Konekcija() {

        try {

            //Spajanje na bazu
            Class.forName("com.mysql.cj.jdbc.Driver");
             this.konekcija= DriverManager.getConnection("jdbc:mysql://"+this.nazivRacunala+":3307/"+this.nazivBaze+"",this.korisnik, this.lozinka);

        }catch (ClassNotFoundException e) {
            System.out.println("Nastala je greška: Nisam pronašao klasu za spajanje");
        }
        catch (SQLException e) {
            System.out.println("Nastala je greška: Nisam se uspio spojiti na bazu");
        }


    }


}
