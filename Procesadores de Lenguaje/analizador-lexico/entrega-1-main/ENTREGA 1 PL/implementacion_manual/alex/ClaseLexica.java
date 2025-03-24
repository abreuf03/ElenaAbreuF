package alex;

public enum ClaseLexica {
    IDENTIFICADOR,
    ENTERO,
    REAL,
    DISTINTO("!="),
    MENOR("<"),
    MENOR_IGUAL("<="),
    MAYOR(">"),
    MAYOR_IGUAL(">="),
    MOD("%"),
    AMPER("&"),
    AMPER_DOBLE("&&"),
    PAR_APERTURA("("),
    PAR_CIERRE(")"),
    LLA_APERTURA("{"),
    LLA_CIERRE("}"),
    COR_APERTURA("["),
    COR_CIERRE("]"),
    EVAL("@"),
    IGUAL_ASIG("="),
    IGUAL_COMP("=="),
    PUNTO_COMA(";"),
    MAS("+"),
    MENOS("-"),
    POR("*"),
    DIV("/"),
    TIPO_ENT("<int>"),
    TIPO_REAL("<real>"),
    TIPO_BOOL("<bool>"),
    OP_AND("<and>"),
    OP_OR("<or>"),
    OP_NOT("<not>"),
    TRUE("<true>"),
    FALSE("<false>"),
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
