bash ./scripts/build.sh
printf "Analizing '"$1"'...\n"
bash ./scripts/run.sh $1
printf "Execution ended."
