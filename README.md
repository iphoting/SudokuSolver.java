# SudokuSolver
## Introduction
This is a java implementation of a Sudoku solver which was initially posted to me as a java exercise question from my IS200 IS Foundations course. I'm posting this up for the fun of it.

Currently a brute-force, backtracking (BFBT) algorithm is used to solve the puzzle and is inefficient at puzzles with minimal hints. One of the highest-difficulty puzzle currently takes approximately 1000 milliseconds under this algorithm.

## Usage
This solver takes in 81 arguments, one for each cell, separated by a space, as the initial puzzle state.

	$ java SudokuSolver \
		0 6 3 4 9 0 0 0 1 \
		0 0 0 0 0 0 7 0 9 \
		0 1 9 0 0 0 0 0 0 \
		0 0 1 0 0 2 9 3 0 \
		9 0 0 1 0 7 0 0 2 \
		0 7 8 9 0 0 4 0 0 \
		0 0 0 0 0 0 8 2 0 \
		3 0 6 0 0 0 0 0 0 \
		4 0 0 0 2 9 1 7 0

## TODO
There are plans to implement the following:

1. Improve the current brute-force, backtracking (BFBT) algorithm by iterating through a list of valid numbers, rather than just blindly going through 1–9.
2. Supersede the current BFBT algorithm with [Dancing Links (DLX)][1], keeping BFBT as an option.

## License
FreeBSD license. Refer to LICENSE.

## Author
Ronald Ip, <http://about.me/ronaldip>.

[1]: http://en.wikipedia.org/wiki/Dancing_Links