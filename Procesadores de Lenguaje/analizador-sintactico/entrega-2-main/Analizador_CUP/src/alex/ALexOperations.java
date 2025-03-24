package alex;

import asint.ClaseLexica;
import errors.GestionErroresEval;
import errors.GestionErroresEval.ErrorLexico;

public class ALexOperations {
    public static class ECaracterInesperado extends RuntimeException {
        public ECaracterInesperado(String msg) {
            super(msg);
        }
    }

    private AnalizadorLexicoTiny alex;

    public ALexOperations(AnalizadorLexicoTiny alex) {
        this.alex = alex;
    }

    public UnidadLexica unidadENTERO() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.LIT_ENTERO, alex.lexema());
    }

    public UnidadLexica unidadNUMREAL() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.LIT_REAL, "<real>");
    }

    public UnidadLexica unidadBOOL() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.BOOL, "<bool>");
    }

    public UnidadLexica unidadCADENA() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.LIT_STRING, alex.lexema());
    }

    public UnidadLexica unidadAND() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.AND, "<and>");
    }

    public UnidadLexica unidadOR() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.OR, "<or>");
    }

    public UnidadLexica unidadNOT() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.NOT, "<not>");
    }

    public UnidadLexica unidadPROC() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.PROC, "<proc>");
    }

    public UnidadLexica unidadSI() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.IF, "<if>");
    }

    public UnidadLexica unidadSINO() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.ELSE, "<else>");
    }

    public UnidadLexica unidadMIENTRAS() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.WHILE, "<while>");
    }

    public UnidadLexica unidadSTRUCT() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.STRUCT, "<struct>");
    }

    public UnidadLexica unidadNUEVO() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.NEW, "<new>");
    }

    public UnidadLexica unidadBORRAR() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.DELETE, "<delete>");
    }

    public UnidadLexica unidadLEER() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.READ, "<read>");
    }

    public UnidadLexica unidadESCRIBIR() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.WRITE, "<write>");
    }

    public UnidadLexica unidadTIPO() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.TYPE, "<type>");
    }

    public UnidadLexica unidadLLAMAR() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.CALL, "<call>");
    }

    public UnidadLexica unidadSUM() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MAS, "+");
    }

    public UnidadLexica unidadREST() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MENOS, "-");
    }

    public UnidadLexica unidadMUL() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.POR, "*");
    }

    public UnidadLexica unidadDIV() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.DIV, "/");
    }

    public UnidadLexica unidadMOD() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MOD, "%");
    }

    public UnidadLexica unidadMENOR() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MENOR, "<");
    }

    public UnidadLexica unidadMAYOR() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MAYOR, ">");
    }

    public UnidadLexica unidadMENORIGUAL() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MENORIGUAL, "<=");
    }

    public UnidadLexica unidadMAYORIGUAL() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.MAYORIGUAL, ">=");
    }

    public UnidadLexica unidadIGUAL() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.IGUAL, "==");
    }

    public UnidadLexica unidadDISTINTO() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.DIFERENTE, "!=");
    }

    public UnidadLexica unidadPARAPER() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.PAP, "(");
    }

    public UnidadLexica unidadPARCIERRE() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.PCIERRE, ")");
    }

    public UnidadLexica unidadPUNTOYCOMA() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.PUNTOCOMA, ";");
    }

    public UnidadLexica unidadASIG() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.IGUAL, "=");
    }

    public UnidadLexica unidadCORAPER() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.CORA, "[");
    }

    public UnidadLexica unidadCORCIERRE() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.CORC, "]");
    }

    public UnidadLexica unidadPUNTO() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.PUNTO, ".");
    }

    public UnidadLexica unidadPUNTERO() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.PUNT, "^");
    }

    public UnidadLexica unidadCOMA() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.COMA, ",");
    }

    public UnidadLexica unidadLLAVEAPER() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.LLAVEA, "{");
    }

    public UnidadLexica unidadLLAVECIERRE() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.LLAVEC, "}");
    }

    public UnidadLexica unidadAMPERSAND() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.AMP, "&");
    }

    public UnidadLexica unidadDOBLEAMPERSAND() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.DOBLEAMPER, "&&");
    }

    public UnidadLexica unidadINT() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.ENTERO, "<int>");
    }
    public UnidadLexica unidadREAL() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.REAL,"<real>");
    }

    public UnidadLexica unidadIDENTIFICADOR() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.IDENTIFICADOR, alex.lexema());
    }

    public UnidadLexica unidadSTR() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.STRING, "<string>");
    }

 
    public UnidadLexica unidadEOF() {
        return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.EOF, "<EOF>");
    }

    public UnidadLexica unidadEVALUACION() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.ARROBA, "@");
  }
  
  public UnidadLexica unidadNEWLINE() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.NL, "<nl>");
  }
  
  public UnidadLexica unidadNUL() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.NULL, "<null>");
  }
  
  public UnidadLexica unidadTRUE() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.TRUE, "<true>");
  }
  
  public UnidadLexica unidadFAL() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.FALSE, "<false>");
  }
  
  public UnidadLexica unidadESCRIB() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.WRITE, "<write>");
  }
  
  public UnidadLexica unidadESTRUCT() {
      return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.STRUCT, "<struct>");
  }

    public void error() {
        throw new ECaracterInesperado("Caracter inesperado: " + alex.lexema());
    }
}