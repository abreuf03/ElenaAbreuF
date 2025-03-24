printf "Building Tiny's JavaCC project...\n\n"

cd src/asint/
java -cp ../../lib/javacc.jar org.javacc.parser.Main spec.jj
printf "Ran 'lib/javacc.jar'.\n"

javac *.java
printf "Compiled Java classes.\n\n"
