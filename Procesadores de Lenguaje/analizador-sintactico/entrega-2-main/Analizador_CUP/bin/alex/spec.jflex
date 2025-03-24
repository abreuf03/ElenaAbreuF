package alex;
import errors.GestionErroresEval;
%%
%cup
%line
%column
%class AnalizadorLexicoTiny
%type  UnidadLexica
%unicode
%public

%{
  private ALexOperations ops;
  private GestionErroresEval errores;
  public String lexema() {return yytext();}
  public int fila() {return yyline+1;}
  public int columna() {return yycolumn+1;}
  public void fijaGestionErrores(GestionErroresEval errores) {
   this.errores = errores;
  }
%}

%eofval{
  return ops.unidadEOF();
%eofval}

%init{
  ops = new ALexOperations(this);
%init}

letra  = ([a-zA-Z])
natural = [1-9]
digito = [0-9]
literalEntero = ([\+\-]?({natural}{digito}*|0))
decimal = ({digito}*{natural}|0)
exponencial = ([eE]{literalEntero})
literalCadena =   (\"[^\"]*\")
separador = [\s\b\r\n\t]
comentario = ##[^\n]*
sum = \+
rest = \-
mul = \*
div = \/
mod = \%
puntero = \^
menor = \<
mayor = \>
menorIgual = \<\=
mayorIgual = \>\=
igual = \=\=
diferente = \!\=
asignacion = \=
parentesisApertura = \(
parentesisCierre = \)
corcheteApertura = \[
corcheteCierre = \]
llaveApertura = \{
llaveCierre = \}
coma = \,
punto = \.
ampersand = &
ampersandDoble = &&
evaluacion = @
puntoYComa = \;
literalReal = (({literalEntero}\.{decimal})|({literalEntero}{exponencial})|({literalEntero}\.{decimal}{exponencial}))
integer = [Ii][Nn][Tt]
real = [Rr][Ee][Aa][Ll]
booleano = [Bb][Oo][Oo][Ll]
cadena = [Ss][Tt][Rr][Ii][Nn][Gg]
operadorAnd = [Aa][Nn][Dd]
operadorOr = [Oo][Rr]
operadorNot = [Nn][Oo][Tt]
valorNulo = [Nn][Uu][Ll][Ll]
valorVerdadero = [Tt][Rr][Uu][Ee]
valorFalso = [Ff][Aa][Ll][Ss][Ee]
procedimiento = [Pp][Rr][Oo][Cc]
si = [Ii][Ff]
sino = [Ee][Ll][Ss][Ee]
mientras = [Ww][Hh][Ii][Ll][Ee]
estructura = [Ss][Tt][Rr][Uu][Cc][Tt]
nuevo = [Nn][Ee][Ww]
borrar = [Dd][Ee][Ll][Ee][Tt][Ee]
leer = [Rr][Ee][Aa][Dd]
escribir = [Ww][Rr][Ii][Tt][Ee]
nuevaLinea = [Nn][Ll]
tipo = [Tt][Yy][Pp][Ee]
llamar = [Cc][Aa][Ll][Ll]
identificador = ({letra}|_)({letra}|{digito}|_)*

%%
{literalEntero}           {return ops.unidadENTERO();}
{literalCadena}           {return ops.unidadCADENA();}
{sum}                     {return ops.unidadSUM();}
{rest}                    {return ops.unidadREST();}
{mul}                     {return ops.unidadMUL();}
{div}                     {return ops.unidadDIV();}
{mod}                     {return ops.unidadMOD();}
{menor}                   {return ops.unidadMENOR();}
{mayor}                   {return ops.unidadMAYOR();}
{menorIgual}              {return ops.unidadMENORIGUAL();}
{mayorIgual}              {return ops.unidadMAYORIGUAL();}
{igual}                   {return ops.unidadIGUAL();}
{diferente}               {return ops.unidadDISTINTO();}
{asignacion}              {return ops.unidadASIG();}
{parentesisApertura}      {return ops.unidadPARAPER();}
{parentesisCierre}        {return ops.unidadPARCIERRE();}
{corcheteApertura}        {return ops.unidadCORAPER();}
{corcheteCierre}          {return ops.unidadCORCIERRE();}
{llaveApertura}           {return ops.unidadLLAVEAPER();}
{llaveCierre}             {return ops.unidadLLAVECIERRE();}
{punto}                   {return ops.unidadPUNTO();}
{coma}                    {return ops.unidadCOMA();}
{puntoYComa}              {return ops.unidadPUNTOYCOMA();}
{ampersand}               {return ops.unidadAMPERSAND();}
{ampersandDoble}          {return ops.unidadDOBLEAMPERSAND();}
{puntero}                 {return ops.unidadPUNTERO();}
{evaluacion}              {return ops.unidadEVALUACION();}
{integer}                 {return ops.unidadINT();}
{real}                    {return ops.unidadREAL();}
{booleano}                {return ops.unidadBOOL();}
{cadena}                  {return ops.unidadSTR();}
{operadorAnd}             {return ops.unidadAND();}
{operadorOr}              {return ops.unidadOR();}
{operadorNot}             {return ops.unidadNOT();}
{valorNulo}               {return ops.unidadNUL();}
{valorVerdadero}          {return ops.unidadTRUE();}
{valorFalso}              {return ops.unidadFAL();}
{procedimiento}           {return ops.unidadPROC();}
{si}                      {return ops.unidadSI();}
{sino}                    {return ops.unidadSINO();}
{mientras}                {return ops.unidadMIENTRAS();}
{estructura}              {return ops.unidadESTRUCT();}
{nuevo}                   {return ops.unidadNUEVO();}
{borrar}                  {return ops.unidadBORRAR();}
{leer}                    {return ops.unidadLEER();}
{escribir}                {return ops.unidadESCRIB();}
{nuevaLinea}              {return ops.unidadNEWLINE();}
{tipo}                    {return ops.unidadTIPO();}
{llamar}                  {return ops.unidadLLAMAR();}
{separador}               {}
{comentario}              {}
{literalReal}             {return ops.unidadNUMREAL();}
{identificador}           {return ops.unidadIDENTIFICADOR();}
[^]                       {ops.error();}