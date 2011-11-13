// Author: Ronald Ip

import java.util.Scanner;

public class SudokuSolver {
	public static void main(String[] args) {
		int[][] input = new int[9][9];
		
		if(args == null || args.length == 0) {
			input = promptInput();
		} else {
			input = parseArgs(args);
		}

		System.out.println("Selected Input:");
		printMatrix(input);
		System.out.println("===");

		long startTime = System.currentTimeMillis();
		// Begin brute-force routine.
		if(solve(0, 0, input)) {
			System.out.println("Solution:");
			printMatrix(input);
		} else {
			System.out.println("No solution.");
		}
		long endTime = System.currentTimeMillis();
		System.out.println("===");
		System.out.println("Time taken: " + (endTime - startTime) + " ms.");
	}

	public static boolean solve(int i, int j, int[][] input) {
		//System.out.print(".");
		if(i == 9) {
			i = 0; // row 9 doesn't exist, overflow back to 0!
			if(++j == 9) { // col 9 doesn't exist! You've reach the end of the grid!
				return true; // By right, that must be the solution.
			}
		}

		if(input[i][j] != 0) { // Already answered, recurse somewhere else!
			return solve(i + 1, j, input);
		}

		// Keep filling in numbers until they are valid.
		for(int v = 1; v <= 9; v++) {
			if(valid(i, j, v, input)) {
				input[i][j] = v;
				// Recurse into child node.
				if(solve(i + 1, j, input)) {
					return true;
				}
			}
		}

		//System.out.print(".");
		// This solution failed, backtracking...
		input[i][j] = 0;
		return false;
	}

	public static boolean valid(int i, int j, int v, int[][] input) {
		//System.out.print("-");
		// Test the whole row
		for(int c = 0; c < input[0].length; c++) {
			if(v == input[i][c]) {
				return false;
			}
		}

		// Column test
		for(int r = 0; r < input.length; r++) {
			if(v == input[r][j]) {
				return false;
			}
		}

		// 3x3 box test.
		int boxRow = (i / 3) * 3;
		int boxCol = (j / 3) * 3;
		// 3x3 loop
		for(int r = 0; r < input.length / 3; r++) {
			for(int c = 0; c < input[0].length / 3; c++) {
				if(v == input[r+boxRow][c+boxCol]) {
					return false;
				}
			}
		}

		//System.out.print("+");
		// Yay, no failures.
		return true;
	}

	public static void printMatrix(int[][] input) {
		for(int[] line : input) {
			for(int cell : line) {
				System.out.print(cell + " ");
			}
			System.out.println();
		}
	}

	public static int[][] promptInput() {
		int[][] input = new int[9][9];
		String[][] linePieces = new String[9][9];
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter a Sudoku puzzle, one row per line, each cell separated by a space:");
		for(int i = 0; i < input.length; i++) {
			linePieces[i] = sc.nextLine().trim().split(" ");
		}

		for(int i = 0; i < linePieces.length; i++) {
			for(int j = 0; j < linePieces[0].length; j++) {
				try {
					input[i][j] = Integer.parseInt(linePieces[i][j]);
				} catch(NumberFormatException e) {
					System.out.println("Invalid input!");
					printHelp();
					System.exit(1);
				}
			}
		}
		return input;
	}

	public static void printHelp() {
		System.out.println("This is SudokuSolver.");
		System.out.println("You can input the puzzle to solve either as arguments, or inline during execution.");
		System.out.println("\nUsage: java SudokuSolver [--help|-h|-H|-v|--version] [input ...]");
		System.out.println("\nExample:\n> java SudokuSolver \\\n" + 
							"0 0 3 0 0 0 0 0 0 \\\n" + 
							"4 0 0 0 8 0 0 3 6 \\\n" + 
							"0 0 8 0 0 0 1 0 0 \\\n" + 
							"0 4 0 0 6 0 0 7 3 \\\n" + 
							"0 0 0 9 0 0 0 0 0 \\\n" + 
							"0 0 0 0 0 2 0 0 5 \\\n" + 
							"0 0 4 0 7 0 0 6 8 \\\n" + 
							"6 0 0 0 0 0 0 0 0 \\\n" + 
							"7 0 0 6 0 0 5 0 0");
	}

	public static int[][] parseArgs(String[] args) {
		//System.out.println(args.length);
		if(args == null || args.length == 0) {
			return new int[9][9];
		} else if(args.length == 1 && (args[0].equalsIgnoreCase("-h") || args[0].equalsIgnoreCase("-H") || args[0].equalsIgnoreCase("--help"))) {
			// Help is requested.
			printHelp();
			System.exit(1);
		} else if(args.length == 1 && (args[0].equalsIgnoreCase("-v") || args[0].equalsIgnoreCase("--version"))) {
			printHelp();
			System.exit(1);
		}

		int[][] input = new int[9][9];
		//System.out.println(args.length);

		int z = 0;
		for(int i = 0; i < input.length; i++) {
			for(int j = 0; j < input[0].length; j++) {
				if(z >= args.length) {
					break; // Prevent index OOB.
				}

				input[i][j] = Integer.parseInt(args[z]);
				z++;
				//System.out.print(input[i][j] + " ");
			}
		}
		
		return input;
	}
}