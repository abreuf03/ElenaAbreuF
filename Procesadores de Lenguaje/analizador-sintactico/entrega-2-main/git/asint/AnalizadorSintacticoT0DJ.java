package asint;

import alex.UnidadLexica;
import java.io.IOException;
import java.io.Reader;

public class AnalizadorSintacticoT0DJ extends AnalizadorSintacticoT0 {
    public AnalizadorSintacticoT0DJ(Reader input) throws IOException {
        super(input);
    }

    protected final void traza_emparejamiento(UnidadLexica unidad) {
       
            switch(unidad.clase()) {
                case IDENTIFICADOR: case LIT_ENTERO: case LIT_REAL:
                    System.out.println(unidad.lexema());
                    break;
                default:
                    System.out.println(unidad.clase().getImage());
            }
        }
    
    

}
