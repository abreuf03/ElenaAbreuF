package asint;

import alex.AnalizadorLexicoTiny;
import alex.UnidadLexica;
import alex.ClaseLexica;
import errors.GestionErroresTiny0;
import java.io.IOException;
import java.io.Reader;
import java.util.EnumSet;
import java.util.Set;

public class AnalizadorSintacticoT0 {

    private UnidadLexica anticipo; //token emparejado
    private GestionErroresTiny0 error; //gestor de errores sintácticos
    private AnalizadorLexicoTiny alex; //analizador léxico
    private Set<ClaseLexica> expected; //clase léxica esperada

    protected boolean errorDetectado = false;

    public AnalizadorSintacticoT0(Reader input) throws IOException{ //constructor

        error = new GestionErroresTiny0();
        alex = new AnalizadorLexicoTiny(input, error);
        expected = EnumSet.noneOf(ClaseLexica.class); //primer director
        sigToken();

    }

    //Fallo en el código: detecta los errores correctamente  pero imprime un token de más en lugar de quedarse en el precedente al error

    private void sigToken(){
        try {
            anticipo = alex.sigToken();
           //System.out.println(anticipo);
            expected.clear();
        } catch (IOException e) {
            error.errorFatal(e);
        }
    }

    public void analiza() {
        programa();
        if (anticipo.clase() != ClaseLexica.EOF) {  
            empareja(ClaseLexica.EOF);
        }

        //error.lanzarErrorSintacticoSiHay(); 
    }
    

    private void programa(){
        switch (anticipo.clase()) {
            case LLA_APERTURA:
                empareja(ClaseLexica.LLA_APERTURA);
                bloque_declaraciones();
                bloque_instrucciones();
                empareja(ClaseLexica.LLA_CIERRE);
                break;
            default:
                expected.add(ClaseLexica.LLA_CIERRE);
                break;
            }	
        
    }

    private void bloque_declaraciones(){
        switch(anticipo.clase()){
            case TIPO_ENT:
            case TIPO_REAL:
            case TIPO_BOOL:{
                lista_declaraciones();
    
               // System.out.println("Después de lista_declaraciones: " + anticipo);
    
                empareja(ClaseLexica.AMPER_DOBLE);
                break;
            }
            default:{
                expected.add(ClaseLexica.TIPO_ENT);
                expected.add(ClaseLexica.TIPO_REAL);
                expected.add(ClaseLexica.TIPO_BOOL);
                break;
            }
        }

    }

    private void bloque_instrucciones(){
        switch(anticipo.clase()){
            case EVAL:{
                lista_instrucciones();
                break;
            }
            default:{
                expected.add(ClaseLexica.EVAL); 
                break;
            }
        }

    }

    private void lista_declaraciones(){

        switch(anticipo.clase()){
            case TIPO_ENT:
            case TIPO_BOOL:
            case TIPO_REAL:{
                declaracion();
                rlista_declaraciones();
                break;
            }
            default:{
                expected.add(ClaseLexica.TIPO_ENT);
                expected.add(ClaseLexica.TIPO_REAL);
                expected.add(ClaseLexica.TIPO_BOOL);
                error();
                break;
            }

        }

    }

    private void lista_instrucciones(){
        switch(anticipo.clase()){
            case EVAL:{
                instruccion();
                rlista_instrucciones();
                break;
            }
            default:{
                expected.add(ClaseLexica.EVAL);
                error();
                break;
            }
        }

    }

    private void declaracion(){
        switch(anticipo.clase()){
            case TIPO_ENT:{
                empareja(ClaseLexica.TIPO_ENT);
                empareja(ClaseLexica.IDENTIFICADOR);
                break;
            }
            case TIPO_REAL:{
                empareja(ClaseLexica.TIPO_REAL);
                empareja(ClaseLexica.IDENTIFICADOR);
                break;
            }
            case TIPO_BOOL:{
                empareja(ClaseLexica.TIPO_BOOL);
                empareja(ClaseLexica.IDENTIFICADOR);
                break;
            }
            default:{
                expected.add(ClaseLexica.TIPO_ENT);
                expected.add(ClaseLexica.TIPO_REAL);
                expected.add(ClaseLexica.TIPO_BOOL);
                error();
                break;
            }
        }

        

    }

   
    private void rlista_declaraciones(){
        switch (anticipo.clase()) {
            case PUNTO_COMA:
                empareja(ClaseLexica.PUNTO_COMA);
                declaracion();
                rlista_declaraciones();
                break;
            default:
                expected.add(ClaseLexica.PUNTO_COMA);
                break;
            }	
        /* ESTO LO HICE POR UN EJEMPLO QUE SE RAYABA DESPUES DEL &&
        if (anticipo.clase() == ClaseLexica.PUNTO_COMA) {
            empareja(ClaseLexica.PUNTO_COMA);
            if (anticipo.clase() == ClaseLexica.TIPO_ENT || 
                anticipo.clase() == ClaseLexica.TIPO_REAL || 
                anticipo.clase() == ClaseLexica.TIPO_BOOL) {
                declaracion();
                rlista_declaraciones();
            }
        }
            */
        // No llamamos a sigToken(); aquí para no consumir `&&` sin querer
    }
    

    private void instruccion(){

        switch(anticipo.clase()) {
            case EVAL: 
                empareja(ClaseLexica.EVAL); 
                E0();
                break;
            default:
                expected.add(ClaseLexica.EVAL);
                error();
                break;
            }

    }

    private void rlista_instrucciones(){

        switch(anticipo.clase()){
            case PUNTO_COMA:{
                empareja(ClaseLexica.PUNTO_COMA);
                instruccion();
                rlista_instrucciones();
                break;
            }
            default:{
                expected.add(ClaseLexica.PUNTO_COMA);
                break;
            }
        }

    }

    private void E0(){
     
                E1();
                RE0();

    }

    private void RE0(){

        switch(anticipo.clase()){
            case IGUAL_ASIG:{
                empareja(ClaseLexica.IGUAL_ASIG);
                E0();
                break;
            }
            default:{
                expected.add(ClaseLexica.IGUAL_ASIG);
                break;
            }
        }

    }

    private void E1(){
                E2();
                RE1();

    }

    //DIR(RE1 → OP1 E2 RE1) = {'==', '!=', '>', '<', '>=', '<='}
    private void RE1(){

        switch(anticipo.clase()){
            case IGUAL_COMP:
            case DISTINTO:
            case MAYOR:
            case MENOR:
            case MAYOR_IGUAL:
            case MENOR_IGUAL:{
                OP1();
                E2();
                RE1();
                break;
            }
            default:{
                expected.add(ClaseLexica.IGUAL_COMP);
                expected.add(ClaseLexica.DISTINTO);
                expected.add(ClaseLexica.MAYOR);
                expected.add(ClaseLexica.MENOR);
                expected.add(ClaseLexica.MAYOR_IGUAL);
                expected.add(ClaseLexica.MENOR_IGUAL);
                break;
            }
        }

    }

    private void OP1(){
        switch(anticipo.clase()){
            case IGUAL_COMP:{
                empareja(ClaseLexica.IGUAL_COMP);
                break;
            }
            case DISTINTO:{
                empareja(ClaseLexica.DISTINTO);
                break;

            }
            case MAYOR:{
                empareja(ClaseLexica.MAYOR);
                break;
            }
            case MENOR:{
                empareja(ClaseLexica.MENOR);
                break;
            }
            case MAYOR_IGUAL:{
                empareja(ClaseLexica.MAYOR_IGUAL);
                break;
            }
            case MENOR_IGUAL:{
                empareja(ClaseLexica.MENOR_IGUAL);
                break;
            }
            default:{
                expected.add(ClaseLexica.IGUAL_COMP);
                expected.add(ClaseLexica.DISTINTO);
                expected.add(ClaseLexica.MAYOR);
                expected.add(ClaseLexica.MENOR);
                expected.add(ClaseLexica.MAYOR_IGUAL);
                expected.add(ClaseLexica.MENOR_IGUAL);
                error();
                break;
            }
        }
    }

    private void E2(){
    
                E3();
                RE2();
                RE2_PRIMA();
    
    }

   

    private void RE2() {
        switch(anticipo.clase()) {
            case MENOS:
                empareja(ClaseLexica.MENOS);
                E3();
                break;
            default:
                expected.add(ClaseLexica.MENOS);
                break;
        }
    }
    private void RE2_PRIMA() {
        switch(anticipo.clase()) {
            case MAS:
                empareja(ClaseLexica.MAS);
                E3();
                RE2_PRIMA();
                break;
            default:
                expected.add(ClaseLexica.MAS);
                break;
        }
    }

    private void E3(){
        
                E4();
                RE3();

    }

    /*
     * DIR(RE3 → OP3_A E3) = DIR(OP3_A → and)
    DIR(RE3 → OP3_NA E4) = DIR(OP3_NA → or)
    DIR(RE3 → ε) = ∅

     */
    private void RE3(){
        switch(anticipo.clase()){
            case OP_AND:{
                empareja(ClaseLexica.OP_AND);
                E3();
                break;
            }
            case OP_OR:{
                empareja(ClaseLexica.OP_OR);
                E4();
                break;
            }
            default:{
                expected.add(ClaseLexica.OP_AND);
                expected.add(ClaseLexica.OP_OR);
                break;
            }
        }

    }

    private void E4(){
    
                E5();
                RE4();
    }

    /*
     * DIR(RE4 → OP4 RE4) = {'*', '/', '%'}
    DIR(RE4 → ε) = ∅
     */
    private void RE4(){
       switch(anticipo.clase()){
        case POR:
        case DIV:{
            OP4();
            E5(); //?????
            RE4(); //para probar -> sino quitar
            break;
        }
        default:{
            expected.add(ClaseLexica.POR);
            expected.add(ClaseLexica.DIV);
           // expected.add(ClaseLexica.MOD);
            break;
        }
       }
    }

    private void OP4(){
        switch(anticipo.clase()){
            case POR:{
                empareja(ClaseLexica.POR);
                break;
            }
            case DIV:{
                empareja(ClaseLexica.DIV);
                break;
            }
            default:{
                expected.add(ClaseLexica.POR);
                expected.add(ClaseLexica.DIV);
                //expected.add(ClaseLexica.MOD);
                error();
                break;

            }
        }
    }

    /*
     * DIR(E5 → E6) = {'(', lit_entero, lit_real, lit_bool, identificador}
    DIR(E5 → OP5 E6) = {'-', 'not'}
     */
    private void E5(){
        switch(anticipo.clase()){
            case MENOS:
            case OP_NOT:{
                OP5();
                E5();
                break;
            }
            case PAR_APERTURA:
            case LIT_ENTERO:
            case LIT_REAL:
            case TRUE:
            case FALSE:
            case IDENTIFICADOR:{
               E6();
               break;
            }
            default:{
                expected.add(ClaseLexica.MENOS);
                expected.add(ClaseLexica.OP_NOT);
                expected.add(ClaseLexica.PAR_APERTURA);
                expected.add(ClaseLexica.LIT_ENTERO);
                expected.add(ClaseLexica.LIT_REAL);
                expected.add(ClaseLexica.TRUE);
                expected.add(ClaseLexica.FALSE);
                expected.add(ClaseLexica.IDENTIFICADOR);
                error();
                break;
            }

        }

    }

    private void OP5(){
        switch (anticipo.clase()) {
            case MENOS:{
                empareja(ClaseLexica.MENOS);
                break;
            }
            case OP_NOT:{
                empareja(ClaseLexica.OP_NOT);
                break;
            }
        
            default:{
                expected.add(ClaseLexica.MENOS);
                expected.add(ClaseLexica.OP_NOT);
                error();
                break;
            }
        }
    }

    private void E6(){
        switch(anticipo.clase()){
            case PAR_APERTURA:{
                empareja(ClaseLexica.PAR_APERTURA);
                E0();
                empareja(ClaseLexica.PAR_CIERRE);
                break;
            }
            case LIT_ENTERO:{
                empareja(ClaseLexica.LIT_ENTERO);
                break;
            }
            case LIT_REAL:{
                empareja(ClaseLexica.LIT_REAL);
                break;
            }
            case TRUE:{
                empareja(ClaseLexica.TRUE);
                break;
            }
            case FALSE:{
                empareja(ClaseLexica.FALSE);
                break;
            }
            case IDENTIFICADOR:{
                empareja(ClaseLexica.IDENTIFICADOR);
                break;
            }
            default:{
                expected.add(ClaseLexica.PAR_APERTURA);
                expected.add(ClaseLexica.LIT_ENTERO);
                expected.add(ClaseLexica.LIT_REAL);
                expected.add(ClaseLexica.TRUE);
                expected.add(ClaseLexica.FALSE);
                expected.add(ClaseLexica.IDENTIFICADOR);
                error();
                break;

            }

        }
    }

    private void error(){
        error.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),expected);
       // errorDetectado = true;
    }

   

    private void empareja(ClaseLexica token){
      //  System.out.println("Intentando emparejar: " + token + " con " + anticipo.clase()); // Debug
    
        if (anticipo.clase() == token) {
            //if (!errorDetectado) {
           //     System.out.println("Emparejado correctamente: " + token); // Debug
                traza_emparejamiento(anticipo);
                sigToken();  // Solo avanzamos si no hay error previo
           // }
        } else {
         //   System.out.println("Error sintáctico detectado en empareja() con token: " + anticipo.clase()); // Debug
            expected.add(token);
            error();  // Llama a error() para registrar el problema pero NO avanza
        }
    }
    

    private void traza_emparejamiento(UnidadLexica token){

        //en este archivo no hace nada -> se redefine en DJ
    }
    
    public void errDetectado(){ 
        errorDetectado = true;
    }
}
