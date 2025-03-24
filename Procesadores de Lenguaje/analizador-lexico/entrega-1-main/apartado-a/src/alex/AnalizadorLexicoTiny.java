package alex;

import java.io.FileInputStream;
import java.io.Reader;
import java.io.InputStreamReader;
import java.io.IOException;

public class AnalizadorLexicoTiny {

  public static class ECaracterInesperado extends RuntimeException {
    public ECaracterInesperado(String msg) {
      super(msg);
    }
  };

  private Reader input;
  private StringBuffer lex;
  private int sigCar;
  private int filaInicio;
  private int columnaInicio;
  private int filaActual;
  private int columnaActual;
  private static String NL = System.getProperty("line.separator");

  private static enum Estado {
    INICIO,
    REC_EXCLAMACION, REC_DISTINTO,
    REC_MENOR, REC_MENOR_IGUAL,
    REC_MAYOR, REC_MAYOR_IGUAL,
    REC_AMPER, REC_AMPER_DOBLE,
    REC_PAR_APERTURA, REC_PAR_CIERRE,
    REC_LLA_APERTURA, REC_LLA_CIERRE,
    REC_EVAL,
    REC_IGUAL_ASIG, REC_IGUAL_COMP,
    REC_PUNTO_COMA,
    REC_IDENTIFICADOR,
    REC_NUMERAL, REC_COMENTARIO,
    REC_MUL, REC_DIV,
    REC_MAS, REC_MENOS,
    REC_0, REC_ENTERO,
    REC_PUNTO, REC_REAL, REC_REAL_TRAMPA,
    REC_EL, REC_EL_0, REC_EL_REAL_SIG, REC_EL_REAL,
    REC_EOF
  }

  private Estado estado;

  public AnalizadorLexicoTiny(Reader input) throws IOException {
    this.input = input;
    lex = new StringBuffer();
    sigCar = input.read();
    filaActual = 1;
    columnaActual = 1;
  }

  public UnidadLexica sigToken() throws IOException {
    estado = Estado.INICIO;
    filaInicio = filaActual;
    columnaInicio = columnaActual;
    lex.delete(0, lex.length());
    while (true) {
      switch (estado) {
        case INICIO:
          if (hayLetra() || hayBarraBaja())
            transita(Estado.REC_IDENTIFICADOR);
          else if (hayNatural())
            transita(Estado.REC_ENTERO);
          else if (hayCero())
            transita(Estado.REC_0);
          else if (hayMas())
            transita(Estado.REC_MAS);
          else if (hayMenos())
            transita(Estado.REC_MENOS);
          else if (hayMul())
            transita(Estado.REC_MUL);
          else if (hayDiv())
            transita(Estado.REC_DIV);
          else if (hayAmper())
            transita(Estado.REC_AMPER);
          else if (hayEval())
            transita(Estado.REC_EVAL);
          else if (hayParApertura())
            transita(Estado.REC_PAR_APERTURA);
          else if (hayParCierre())
            transita(Estado.REC_PAR_CIERRE);
          else if (hayLlaApertura())
            transita(Estado.REC_LLA_APERTURA);
          else if (hayLlaCierre())
            transita(Estado.REC_LLA_CIERRE);
          else if (hayExclamacion())
            transita(Estado.REC_EXCLAMACION);
          else if (hayMayor())
            transita(Estado.REC_MAYOR);
          else if (hayMenor())
            transita(Estado.REC_MENOR);
          else if (hayIgual())
            transita(Estado.REC_IGUAL_ASIG);
          else if (hayPuntoComa())
            transita(Estado.REC_PUNTO_COMA);
          else if (hayNumeral())
            transitaIgnorando(Estado.REC_NUMERAL);
          else if (haySeparador())
            transitaIgnorando(Estado.INICIO);
          else if (hayEOF())
            transita(Estado.REC_EOF);
          else
            error();
          break;
        case REC_IDENTIFICADOR:
          if (hayLetra() || hayDigito() || hayBarraBaja())
            transita(Estado.REC_IDENTIFICADOR);
          else
            return unidadIdentificador();
          break;
        case REC_ENTERO:
          if (hayDigito())
            transita(Estado.REC_ENTERO);
          else if (hayPunto())
            transita(Estado.REC_PUNTO);
          else if (hayEl())
            transita(Estado.REC_EL);
          else
            return unidadEntero();
          break;
        case REC_0:
          if (hayPunto())
            transita(Estado.REC_PUNTO);
          else if (hayEl())
            transita(Estado.REC_EL);
          else
            return unidadEntero();
          break;
        case REC_PUNTO:
          if (hayDigito())
            transita(Estado.REC_REAL);
          else
            error();
          break;
        case REC_REAL:
          if (hayNatural())
            transita(Estado.REC_REAL);
          else if (hayCero())
            transita(Estado.REC_REAL_TRAMPA);
          else if (hayEl())
            transita(Estado.REC_EL);
          else
            return unidadReal();
          break;
        case REC_REAL_TRAMPA:
          if (hayCero())
            transita(Estado.REC_REAL_TRAMPA);
          else if (hayNatural())
            transita(Estado.REC_REAL);
          else
            error();
          break;
        case REC_EL:
          if (hayCero())
            transita(Estado.REC_EL_0);
          else if (haySigno())
            transita(Estado.REC_EL_REAL_SIG);
          else if (hayNatural())
            transita(Estado.REC_EL_REAL);
          else
            error();
          break;
        case REC_EL_0:
          return unidadReal();
        case REC_EL_REAL_SIG:
          if (hayCero())
            transita(Estado.REC_EL_0);
          else if (hayNatural())
            transita(Estado.REC_EL_REAL);
          else
            error();
          break;
        case REC_EL_REAL:
          if (hayDigito())
            transita(Estado.REC_EL_REAL);
          else
            return unidadReal();
          break;
        case REC_MAS:
          if (hayNatural())
            transita(Estado.REC_ENTERO);
          else if (hayCero())
            transita(Estado.REC_0);
          else
            return unidadMas();
          break;
        case REC_MENOS:
          if (hayNatural())
            transita(Estado.REC_ENTERO);
          else if (hayCero())
            transita(Estado.REC_0);
          else
            return unidadMenos();
          break;
        case REC_MUL:
          return unidadMul();
        case REC_DIV:
          return unidadDiv();
        case REC_AMPER:
          if (hayAmper())
            transita(Estado.REC_AMPER_DOBLE);
          else
            error();
          break;
        case REC_AMPER_DOBLE:
          return unidadAmperDoble();
        case REC_PAR_APERTURA:
          return unidadParApertura();
        case REC_PAR_CIERRE:
          return unidadParCierre();
        case REC_LLA_APERTURA:
          return unidadLlaApertura();
        case REC_LLA_CIERRE:
          return unidadLlaCierre();
        case REC_IGUAL_ASIG:
          if (hayIgual())
            transita(Estado.REC_IGUAL_COMP);
          else
            return unidadIgualAsig();
          break;
        case REC_IGUAL_COMP:
          return unidadIgualComp();
        case REC_MAYOR:
          if (hayIgual())
            transita(Estado.REC_MAYOR_IGUAL);
          else
            return unidadMayor();
          break;
        case REC_MENOR:
          if (hayIgual())
            transita(Estado.REC_MENOR_IGUAL);
          else
            return unidadMenor();
          break;
        case REC_EVAL:
          return unidadEval();
        case REC_EXCLAMACION:
          if (hayIgual())
            transita(Estado.REC_DISTINTO);
          else
            error();
          break;
        case REC_DISTINTO:
          return unidadDistinto();
        case REC_MAYOR_IGUAL:
          return unidadMayorIgual();
        case REC_MENOR_IGUAL:
          return unidadMenorIgual();
        case REC_PUNTO_COMA:
          return unidadPuntoComa();
        case REC_NUMERAL:
          if (hayNumeral())
            transita(Estado.REC_COMENTARIO);
          else
            error();
          break;
        case REC_COMENTARIO:
          if (hayNL())
            transitaIgnorando(Estado.INICIO);
          else if (hayEOF())
            transita(Estado.REC_EOF);
          else
            transitaIgnorando(Estado.REC_COMENTARIO);
          break;
        case REC_EOF:
          return unidadEof();
      }
    }
  }

  private void transita(Estado sig) throws IOException {
    lex.append((char) sigCar);
    sigCar();
    estado = sig;
  }

  private void transitaIgnorando(Estado sig) throws IOException {
    sigCar();
    filaInicio = filaActual;
    columnaInicio = columnaActual;
    estado = sig;
  }

  private void sigCar() throws IOException {
    sigCar = input.read();
    if (sigCar == NL.charAt(0))
      saltaFinDeLinea();
    if (sigCar == '\n') {
      filaActual++;
      columnaActual = 0;
    } else {
      columnaActual++;
    }
  }

  private void saltaFinDeLinea() throws IOException {
    for (int i = 1; i < NL.length(); i++) {
      sigCar = input.read();
      if (sigCar != NL.charAt(i))
        error();
    }
    sigCar = '\n';
  }

  private boolean hayBarraBaja() {
    return sigCar == '_';
  }

  private boolean hayLetra() {
    return sigCar >= 'a' && sigCar <= 'z' ||
        sigCar >= 'A' && sigCar <= 'Z';
  }

  private boolean hayNatural() {
    return sigCar >= '1' && sigCar <= '9';
  }

  private boolean hayCero() {
    return sigCar == '0';
  }

  private boolean hayDigito() {
    return hayNatural() || hayCero();
  }

  private boolean hayMas() {
    return sigCar == '+';
  }

  private boolean hayMenos() {
    return sigCar == '-';
  }

  private boolean haySigno() {
    return hayMas() || hayMenos();
  }

  private boolean hayMul() {
    return sigCar == '*';
  }

  private boolean hayDiv() {
    return sigCar == '/';
  }

  private boolean hayAmper() {
    return sigCar == '&';
  }

  private boolean hayEval() {
    return sigCar == '@';
  }

  private boolean hayParApertura() {
    return sigCar == '(';
  }

  private boolean hayParCierre() {
    return sigCar == ')';
  }

  private boolean hayLlaApertura() {
    return sigCar == '{';
  }

  private boolean hayLlaCierre() {
    return sigCar == '}';
  }

  private boolean hayCorApertura() {
    return sigCar == '[';
  }

  private boolean hayCorCierre() {
    return sigCar == ']';
  }

  private boolean hayIgual() {
    return sigCar == '=';
  }

  private boolean hayExclamacion() {
    return sigCar == '!';
  }

  private boolean hayMayor() {
    return sigCar == '>';
  }

  private boolean hayMenor() {
    return sigCar == '<';
  }

  private boolean hayPuntoComa() {
    return sigCar == ';';
  }

  private boolean hayPunto() {
    return sigCar == '.';
  }

  private boolean hayEl() {
    return sigCar == 'e' || sigCar == 'E';
  }

  private boolean hayNumeral() {
    return sigCar == '#';
  }

  private boolean haySeparador() {
    return sigCar == ' ' || sigCar == '\t' || sigCar == '\n';
  }

  private boolean hayNL() {
    return sigCar == '\r' || sigCar == '\b' || sigCar == '\n';
  }

  private boolean hayEOF() {
    return sigCar == -1;
  }

  private UnidadLexica unidadIdentificador() {
    switch (lex.toString().toLowerCase()) {
      case "int":
        return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.TIPO_ENT);
      case "real":
        return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.TIPO_REAL);
      case "bool":
        return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.TIPO_BOOL);
      case "and":
        return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.OP_AND);
      case "or":
        return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.OP_OR);
      case "not":
        return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.OP_NOT);
      case "true":
        return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.TRUE);
      case "false":
        return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.FALSE);
      default:
        return new UnidadLexicaMultivaluada(filaInicio, columnaInicio, ClaseLexica.IDENTIFICADOR, lex.toString());
    }
  }

  private UnidadLexica unidadEntero() {
    return new UnidadLexicaMultivaluada(filaInicio, columnaInicio, ClaseLexica.ENTERO, lex.toString());
  }

  private UnidadLexica unidadReal() {
    return new UnidadLexicaMultivaluada(filaInicio, columnaInicio, ClaseLexica.REAL, lex.toString());
  }

  private UnidadLexica unidadMas() {
    return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.MAS);
  }

  private UnidadLexica unidadMenos() {
    return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.MENOS);
  }

  private UnidadLexica unidadMul() {
    return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.POR);
  }

  private UnidadLexica unidadDiv() {
    return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.DIV);
  }

  private UnidadLexica unidadParApertura() {
    return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.PAR_APERTURA);
  }

  private UnidadLexica unidadParCierre() {
    return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.PAR_CIERRE);
  }

  private UnidadLexica unidadLlaApertura() {
    return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.LLA_APERTURA);
  }

  private UnidadLexica unidadLlaCierre() {
    return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.LLA_CIERRE);
  }

  private UnidadLexica unidadCorApertura() {
    return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.COR_APERTURA);
  }

  private UnidadLexica unidadCorCierre() {
    return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.COR_CIERRE);
  }

  private UnidadLexica unidadIgualAsig() {
    return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.IGUAL_ASIG);
  }

  private UnidadLexica unidadIgualComp() {
    return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.IGUAL_COMP);
  }

  private UnidadLexica unidadDistinto() {
    return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.DISTINTO);
  }

  private UnidadLexica unidadPuntoComa() {
    return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.PUNTO_COMA);
  }

  private UnidadLexica unidadMenor() {
    return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.MENOR);
  }

  private UnidadLexica unidadMenorIgual() {
    return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.MENOR_IGUAL);
  }

  private UnidadLexica unidadMayor() {
    return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.MAYOR);
  }

  private UnidadLexica unidadMayorIgual() {
    return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.MAYOR_IGUAL);
  }

  private UnidadLexica unidadAmperDoble() {
    return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.AMPER_DOBLE);
  }

  private UnidadLexica unidadEval() {
    return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.EVAL);
  }

  private UnidadLexica unidadEof() {
    return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.EOF);
  }

  private void error() {
    int curCar = sigCar;
    try {
      sigCar();
    } catch (IOException e) {
    }
    throw new ECaracterInesperado("(" + filaActual + ',' + columnaActual + "):Caracter inexperado:" + (char) curCar);
  }

  public static void main(String arg[]) throws IOException {
    Reader input = new InputStreamReader(new FileInputStream(arg[0]));
    AnalizadorLexicoTiny al = new AnalizadorLexicoTiny(input);
    UnidadLexica unidad;
    do {
      unidad = al.sigToken();
      System.out.println(unidad);
    } while (unidad.clase() != ClaseLexica.EOF);
  }
}
