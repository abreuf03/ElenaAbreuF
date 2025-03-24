/*
la clase que usa el archivo JFlex 
generado para realizar el análisis léxico.
 */

package alex;

public enum ClaseLexica {
    IDENTIFICADOR,
    ENTERO,
    NUMREAL,
    CADENA,
    SUM("+"),
    REST("-"),
    MUL("*"),
    DIV("/"),
    MOD("%"),
    MENOR("<"),
    MAYOR(">"),
    MENORIGUAL("<="),
    MAYORIGUAL(">="),
    IGUAL("=="),
    DISTINTO("!="),
    ASIG("="),
    PARAPER("("),
    PARCIERRE(")"),
    CORAPER("["),
    CORCIERRE("]"),
    LLAVEAPER("{"),
    LLAVECIERRE("}"),
    PUNTO("."),
    COMA(","),
    PUNTOYCOMA(";"),
    AMPERSAND("&"),
    DOBLEAMPERSAND("&&"),
    PUNTERO("^"),
    EVALUACION("@"),
    INT("<int>"),
    REAL("<real>"),
    BOOL("<bool>"),
    STR("<string>"),
    AND("<and>"),
    OR("<or>"),
    NOT("<not>"),
    NUL("<null>"),
    TRUE("<true>"),
    FAL("<false>"),
    PROC("<proc>"),
    SI("<if>"),
    SINO("<else>"),
    MIENTRAS("<while>"),
    ESTRUCT("<struct>"),
    NUEVO("<new>"),
    BORRAR("<delete>"),
    LEER("<read>"),
    ESCRIB("<write>"),
    NEWLINE("<nl>"),
    TIPO("<type>"),
    LLAMAR("<call>"),
    EOF("EOF");

    private String image;

    public String getImage() {
        return image;
    }

    private ClaseLexica() {
        image = toString();
    }

    private ClaseLexica(String image) {
        this.image = image;
    }

}