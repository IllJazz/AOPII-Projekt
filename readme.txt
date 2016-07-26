Das zu lösende LGS kann über die Funktion Datei>Öffnen aus einer Textdatei eingelesen werden,
oder per Eingabe über das große Textfeld im linken Bereich der GUI.

Die Form der Eingabe unterliegt dabei jeweils den folgenden Konventionen:

- alle Werte des LGS dürfen nur aus Zahlen, sowie + oder - als Vorzeichen bestehen
- die Werte müssen mittels Semikolon separiert sein
- es dürfen keine Variablen enthalten sein
- der letzte Eintrag in jeder Zeile ist das Ergebnis dieser Zeile
- fehlende Werte müssen auch separiert werden
- hinter dem letzten Wert wird kein Trennzeichen gesetzt

Bsp. 
die folgende Zeile des LGS: 
3x1 -4x2 +5x4 =6

muss diese Form in der Datei oder der Eingabe im Textfeld haben:
3;-4;0;5;6