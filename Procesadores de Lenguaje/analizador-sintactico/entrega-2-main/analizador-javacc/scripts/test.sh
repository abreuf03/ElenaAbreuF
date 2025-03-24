export LANG="es_ES"

if [ -z $1 ]; then
    bash ./scripts/build.sh
    echo "No file specified, will run all available files. Found these test files:"
    FOUND=$(ls files/in/)
    echo "$FOUND"
    printf "\n"
    rm -r ./scripts/executions/*.diff
    for FILE in $FOUND; do
        bash ./scripts/test.sh $FILE
        printf "\n"
    done
else
    echo "Testing '"$1"'..."
    cd src/
    RESULT=$(cat ../files/in/$1 | java DomJudge.java)
    DIFFERENCE=$(echo "$RESULT" | diff -u --strip-trailing-cr - ../files/out/$1.asint)
    if [ -z "$DIFFERENCE" ]; then
        echo "PASSED. No difference found with reference."
    else
        echo "$DIFFERENCE" > ../scripts/executions/$1.diff
        echo "ERRORED. Saved difference at 'scripts/executions/$1.diff'."
    fi
    cd ..
fi
