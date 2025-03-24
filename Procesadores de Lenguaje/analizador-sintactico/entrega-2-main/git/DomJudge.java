import asint.AnalizadorSintacticoT0;
import asint.AnalizadorSintacticoT0DJ;
import errors.GestionErroresTiny0.ErrorLexico;
import errors.GestionErroresTiny0.ErrorSintactico;

import java.io.FileInputStream;
import java.io.InputStreamReader;
public class DomJudge{
   public static void main(String[] args) throws Exception {
     try{

      AnalizadorSintacticoT0 asint;
		asint = new AnalizadorSintacticoT0DJ(new InputStreamReader(System.in));
			
		asint.analiza();
      
     }
     catch(ErrorSintactico e) {
        System.out.println("ERROR_SINTACTICO"); 
     }
     catch(ErrorLexico e) {
        System.out.println("ERROR_LEXICO"); 
     }
   }
}