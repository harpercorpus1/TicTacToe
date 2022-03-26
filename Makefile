compile:
	cd src/ && javac cpsc2150/extendedTicTacToe/*.java

run:
	cd src/ && java cpsc2150/extendedTicTacToe/TicTacToeGame

clean: 
	cd src/ && rm cpsc2150/extendedTicTacToe/*.class	

all: compile run clean