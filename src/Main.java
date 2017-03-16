/**
 * Created by Ignas on 2017.03.16.
 */
import com.kartojam.dabar.papildoma.Papildoma;

public class Main {
    public static void main(String[] args) {

        Papildoma klase = new Papildoma();
        klase.pasisveikink();
        System.out.println("Gerai praleiskit laika!");
        while(true){
            klase.paklausk();
            klase.nuspresk();
        }


    }


}

