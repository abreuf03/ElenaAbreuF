package alex;


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

   public UnidadLexica unidadIDENTIFICADOR() {
      return new UnidadLexicaMultivaluada(alex.fila(), alex.columna(), ClaseLexica.IDENTIFICADOR,
            alex.lexema());
   }

   public UnidadLexica unidadENTERO() {
      return new UnidadLexicaMultivaluada(alex.fila(), alex.columna(), ClaseLexica.ENTERO,
            alex.lexema());
   }

   public UnidadLexica unidadNUMREAL() {
      return new UnidadLexicaMultivaluada(alex.fila(), alex.columna(), ClaseLexica.NUMREAL,
            alex.lexema());
   }

   public UnidadLexica unidadCADENA() {
      return new UnidadLexicaMultivaluada(alex.fila(), alex.columna(), ClaseLexica.CADENA,
            alex.lexema());
   }

   public UnidadLexica unidadSUM() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.SUM);
   }

   public UnidadLexica unidadREST() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.REST);
   }

   public UnidadLexica unidadMUL() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.MUL);
   }

   public UnidadLexica unidadDIV() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.DIV);
   }

   public UnidadLexica unidadMOD() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.MOD);
   }

   public UnidadLexica unidadMENOR() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.MENOR);
   }

   public UnidadLexica unidadMAYOR() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.MAYOR);
   }

   public UnidadLexica unidadMENORIGUAL() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.MENORIGUAL);
   }

   public UnidadLexica unidadMAYORIGUAL() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.MAYORIGUAL);
   }

   public UnidadLexica unidadIGUAL() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.IGUAL);
   }

   public UnidadLexica unidadDISTINTO() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.DISTINTO);
   }

   public UnidadLexica unidadASIG() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.ASIG);
   }

   public UnidadLexica unidadPARAPER() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.PARAPER);
   }

   public UnidadLexica unidadPARCIERRE() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.PARCIERRE);
   }

   public UnidadLexica unidadCORAPER() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.CORAPER);
   }

   public UnidadLexica unidadCORCIERRE() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.CORCIERRE);
   }

   public UnidadLexica unidadLLAVEAPER() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.LLAVEAPER);
   }

   public UnidadLexica unidadLLAVECIERRE() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.LLAVECIERRE);
   }

   public UnidadLexica unidadPUNTO() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.PUNTO);
   }

   public UnidadLexica unidadCOMA() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.COMA);
   }

   public UnidadLexica unidadPUNTOYCOMA() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.PUNTOYCOMA);
   }

   public UnidadLexica unidadAMPERSAND() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.AMPERSAND);
   }

   public UnidadLexica unidadDOBLEAMPERSAND() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.DOBLEAMPERSAND);
   }

   public UnidadLexica unidadPUNTERO() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.PUNTERO);
   }

   public UnidadLexica unidadEVALUACION() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.EVALUACION);
   }

   public UnidadLexica unidadINT() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.INT);
   }

   public UnidadLexica unidadREAL() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.REAL);
   }

   public UnidadLexica unidadBOOL() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.BOOL);
   }

   public UnidadLexica unidadSTR() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.STR);
   }

   public UnidadLexica unidadAND() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.AND);
   }

   public UnidadLexica unidadOR() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.OR);
   }

   public UnidadLexica unidadNOT() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.NOT);
   }

   public UnidadLexica unidadNUL() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.NUL);
   }

   public UnidadLexica unidadTRUE() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.TRUE);
   }

   public UnidadLexica unidadFAL() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.FAL);
   }

   public UnidadLexica unidadPROC() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.PROC);
   }

   public UnidadLexica unidadSI() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.SI);
   }

   public UnidadLexica unidadSINO() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.SINO);
   }

   public UnidadLexica unidadMIENTRAS() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.MIENTRAS);
   }

   public UnidadLexica unidadESTRUCT() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.ESTRUCT);
   }

   public UnidadLexica unidadNUEVO() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.NUEVO);
   }

   public UnidadLexica unidadBORRAR() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.BORRAR);
   }

   public UnidadLexica unidadLEER() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.LEER);
   }

   public UnidadLexica unidadESCRIB() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.ESCRIB);
   }

   public UnidadLexica unidadNEWLINE() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.NEWLINE);
   }

   public UnidadLexica unidadTIPO() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.TIPO);
   }

   public UnidadLexica unidadLLAMAR() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.LLAMAR);
   }

   public UnidadLexica unidadEOF() {
      return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.EOF);
   }

   public void error() {
      throw new ECaracterInesperado(
            "***" + alex.fila() + "," + alex.columna() + ": Caracter inexperado: " + alex.lexema());
   }
}