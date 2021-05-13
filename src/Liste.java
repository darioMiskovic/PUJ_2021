import Baza.Konekcija;
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
            List<Student> studenti = Student.dohvatiStudente();

            for (Student student: studenti) {
                System.out.println(student);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


       // Nastavnik.spasi(new Nastavnik("john","doe","123222223"));


        List<Nastavnik> nastavnici = Nastavnik.dohvatiNastavnike();

        for (Nastavnik nastavnik: nastavnici) {
            if(nastavnik.getIme().equals("john")){
                Nastavnik.brisi(nastavnik);
            }

            System.out.println(nastavnik);
        }



    }
}
