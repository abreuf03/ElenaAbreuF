package asint;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class main {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        try {
            Reader input = new InputStreamReader(new FileInputStream(args[0]));
            AnalizadorSintacticoT0 asint = new AnalizadorSintacticoT0DJ(input);
            asint.analiza();
            System.out.println("OK");
        } catch (RuntimeException ex) {
            // TODO: handle exception
            System.out.println(ex.getMessage());
        }
       
        
    }
    
}
