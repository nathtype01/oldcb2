@echo off
setlocal enabledelayedexpansion

cd /d "%~dp0"

set VERSION_DIR=original-oldcb
set SOURCE_NAME=Offline CheatBreaker 1.8.9
set OUTPUT_NAME=oldcb2
set LIB_ROOT=%APPDATA%\.minecraft\libraries
set JOPT_JAR=%LIB_ROOT%\net\sf\jopt-simple\jopt-simple\4.6\jopt-simple-4.6.jar
set GSON_JAR=%LIB_ROOT%\com\google\code\gson\gson\2.2.4\gson-2.2.4.jar
set AUTHLIB_JAR=%LIB_ROOT%\com\mojang\authlib\1.5.21\authlib-1.5.21.jar

if not exist "%VERSION_DIR%\%SOURCE_NAME%.jar" (
    echo [ERREUR] "%VERSION_DIR%\%SOURCE_NAME%.jar" trouve nulle part
    echo il devrait etre venu avec ce repo verifie que ton checkout est complet
    pause
    exit /b 1
)
if not exist "%VERSION_DIR%\%SOURCE_NAME%.json" (
    echo [ERREUR] "%VERSION_DIR%\%SOURCE_NAME%.json" trouve nulle part
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
javac -d build\classes -cp "%VERSION_DIR%\%SOURCE_NAME%.jar;%JOPT_JAR%;%GSON_JAR%;%AUTHLIB_JAR%" src\*.java
if errorlevel 1 (
    echo.
    echo [ERREUR] compilation echouee regarde les erreurs au dessus
    pause
    exit /b 1
)

echo assemblage de "%VERSION_DIR%\%OUTPUT_NAME%.jar"...
copy /y "%VERSION_DIR%\%SOURCE_NAME%.jar" "%VERSION_DIR%\%OUTPUT_NAME%.jar" >nul
pushd build\classes
jar uf "..\..\%VERSION_DIR%\%OUTPUT_NAME%.jar" *.class
set JARRESULT=%errorlevel%
popd
if not %JARRESULT%==0 (
    echo [ERREUR] echec de la mise a jour du jar
    pause
    exit /b 1
)

echo ajout du shader polyblur phosphor dans le jar...
jar uf "%VERSION_DIR%\%OUTPUT_NAME%.jar" assets
set JARRESULT2=%errorlevel%
if not %JARRESULT2%==0 (
    echo [ERREUR] echec de l'ajout des assets polyblur
    pause
    exit /b 1
)

copy /y "%VERSION_DIR%\%SOURCE_NAME%.json" "%VERSION_DIR%\%OUTPUT_NAME%.json" >nul

echo.
echo termine "%VERSION_DIR%\%OUTPUT_NAME%.jar" et "%VERSION_DIR%\%OUTPUT_NAME%.json" sont prets
echo copie les deux dans "%%APPDATA%%\.minecraft\versions\%OUTPUT_NAME%\" et choisis
echo "%OUTPUT_NAME%" comme version dans le launcher minecraft officiel

endlocal
