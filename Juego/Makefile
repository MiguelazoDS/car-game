make:
	javac -d bin src/*.java
	echo Main-Class: Ventana > Manifest
	jar -cfm Juego.jar Manifest -C bin/ .
clean:
	rm -rf bin/*
	rm -rf Juego.jar
	rm -rf Manifest
run:
	java -Dsun.java2d.opengl=True -cp bin Ventana
jar:
	java -Dsun.java2d.opengl=True -jar Juego.jar
