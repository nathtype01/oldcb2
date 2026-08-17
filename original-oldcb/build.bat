@echo off
setlocal enabledelayedexpansion

rem recompile les sources patchees dans src\ et reassemble un
rem "offline cheatbreaker 1.8.9-patched.jar" + .json qui va avec pret a drop dans
rem %appdata%\.minecraft\versions\ et lancer avec le launcher minecraft officiel
rem
rem lit a cote de ce script :
rem   - "offline cheatbreaker 1.8.9.jar"   le jar client original non modifie
rem   - "offline cheatbreaker 1.8.9.json"  son manifest de version qui va avec
rem les deux sont dans ce repo donc un checkout tout frais build sans telechargement
rem en plus a part les 3 libs en dessous
rem
rem demande aussi 3 petites libs sur lesquelles ce client a ete build que le
rem launcher officiel telecharge deja la premiere fois que tu lances n'importe
rem quel profil qui les utilise (jopt-simple gson et authlib sont communs a la
rem plupart des versions 1.8.x) par defaut ce script les cherche la ou le
rem launcher officiel les met sous %appdata%\.minecraft\libraries\ si t'as
rem jamais lance quoi que ce soit avec le launcher officiel sur cette machine
rem lance n'importe quelle version 1.8.x une fois d'abord (meme celle-ci meme si
rem elle est encore cassee a ce moment) pour que ces libs se telechargent
rem puis relance ce script

cd /d "%~dp0"

set SOURCE_NAME=Offline CheatBreaker 1.8.9
set OUTPUT_NAME=Offline CheatBreaker 1.8.9-patched
set LIB_ROOT=%APPDATA%\.minecraft\libraries
set JOPT_JAR=%LIB_ROOT%\net\sf\jopt-simple\jopt-simple\4.6\jopt-simple-4.6.jar
set GSON_JAR=%LIB_ROOT%\com\google\code\gson\gson\2.2.4\gson-2.2.4.jar
set AUTHLIB_JAR=%LIB_ROOT%\com\mojang\authlib\1.5.21\authlib-1.5.21.jar

if not exist "%SOURCE_NAME%.jar" (
    echo [ERREUR] "%SOURCE_NAME%.jar" trouve nulle part a cote de ce script
    echo il devrait etre venu avec ce repo verifie que ton checkout est complet
    pause
    exit /b 1
)
if not exist "%SOURCE_NAME%.json" (
    echo [ERREUR] "%SOURCE_NAME%.json" trouve nulle part a cote de ce script
    pause
    exit /b 1
)

where javac >nul 2>nul
if errorlevel 1 (
    echo [ERREUR] javac trouve nulle part dans le path installe un jdk 8 ou plus recent
    pause
    exit /b 1
)

for %%L in ("%JOPT_JAR%" "%GSON_JAR%" "%AUTHLIB_JAR%") do (
    if not exist %%L (
        echo [ERREUR] dependance manquante : %%L
        echo lance n'importe quelle version 1.8.x une fois avec le launcher officiel
        echo pour qu'il telecharge les libs communes puis relance ce script
        pause
        exit /b 1
    )
)

if not exist build mkdir build
if exist build\classes rmdir /s /q build\classes
mkdir build\classes

echo compilation des sources patchees...
javac -d build\classes -cp "%SOURCE_NAME%.jar;%JOPT_JAR%;%GSON_JAR%;%AUTHLIB_JAR%" src\*.java
if errorlevel 1 (
    echo.
    echo [ERREUR] compilation echouee regarde les erreurs au dessus
    pause
    exit /b 1
)

echo assemblage de "%OUTPUT_NAME%.jar"...
copy /y "%SOURCE_NAME%.jar" "%OUTPUT_NAME%.jar" >nul
pushd build\classes
jar uf "..\..\%OUTPUT_NAME%.jar" *.class
set JARRESULT=%errorlevel%
popd
if not %JARRESULT%==0 (
    echo [ERREUR] echec de la mise a jour du jar
    pause
    exit /b 1
)

copy /y "%SOURCE_NAME%.json" "%OUTPUT_NAME%.json" >nul

echo.
echo termine "%OUTPUT_NAME%.jar" et "%OUTPUT_NAME%.json" sont prets
echo copie les deux dans "%%APPDATA%%\.minecraft\versions\%OUTPUT_NAME%\" et choisis
echo "%OUTPUT_NAME%" comme version dans le launcher minecraft officiel

endlocal
