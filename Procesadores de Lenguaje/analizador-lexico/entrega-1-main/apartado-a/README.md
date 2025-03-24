# Maneras de ejecutar el analizador léxico

## DomJudge runner

Esté es el runner file que utilizará `DomJudge`, utilizando el `System.in` como entrada al programa y la respuesta se imprime en el `System.out`.

``` bash
java ./src/DomJudge.java
```

## Runner para archivos específicos

Se puede también ejecutar el analizador utilizando el `FileSystem` y pasando por parámetro el path del archivo. La respuesta también se imprime en el `System.out`.

``` bash
java ./src/alex/AnalizadorLexicoTiny.java ./files/in/example_eval.txt
```

## La manera pro de hacerlo

``` bash
$(cat .\files\in\example_tiny_0.txt | java .\src\DomJudge.java) > .\files\out\example_tiny_0.txt
```

Te ejecutará el DomJudge a un archivo en `./files/out/`.
