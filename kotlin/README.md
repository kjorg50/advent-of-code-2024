# advent-of-code-2024
https://adventofcode.com/

## Kotlin Usage
Add this shortcut for compiling and running kotlin programs to your bash_profile or zshrc file

```
export PATH=$PATH:/usr/local/bin/kotlin
function kotlinr() {
  echo Compiling, please wait...
  kotlinc $1 -include-runtime -d out.jar
  java -jar out.jar
}
```

For each day
```
Usage: kotlinr day0X.kt
```

### Template

Run the `newDay.sh` template script to setup the folder for each new day

```
sh newDay.sh 01
```