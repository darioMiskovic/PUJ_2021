import Model.Nastavnik;
import Model.Predmet;
import Model.Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class Liste {

    public static void main (String [] args){
        try {
            //Spajanje na bazu
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection konekcija= DriverManager.getConnection("jdbc:mysql://localhost:3307/evidencija","root", "");

            //Lista svih objekata
            List<Nastavnik> nastavnici = new ArrayList<Nastavnik>();

            //Upit koji se izvršava
            Statement iskaz =konekcija.createStatement();
            ResultSet rs =iskaz.executeQuery("select * from osoba where uloga = 'nastavnik'");

            //Popunjavanje liste sa objektima tipa Nastavnik
            while(rs.next()){
                nastavnici.add(new Nastavnik(
                        rs.getString("ime"),
                        rs.getString("prezime"),
                        rs.getString("JMBG")
                ));
            }

            for (Nastavnik nastavnik: nastavnici) {
                System.out.println(nastavnik);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
