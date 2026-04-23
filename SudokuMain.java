package sudoku;
import java.util.ArrayList;

public class SudokuMain {
	public static void main(String[] args) {
		long time1 = System.currentTimeMillis();
		ArrayList<ArrayList<Integer>> board = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < 9; i++) {
			board.add(new ArrayList<Integer>());
			for(int j = 0; j < 9; j++) {
				board.get(i).add(0);
			}
		}
		solve(board);
		printBoard(board);
		System.out.println(System.currentTimeMillis() - time1);
	}
	public static boolean verifyRows(ArrayList<ArrayList<Integer>> arr, int rand, int row) {
		return arr.get(row).contains(rand);
	}
	public static boolean verifyColumns(ArrayList<ArrayList<Integer>> arr, int rand, int col){
		for(int i = 0; i < arr.size(); i++) {
			if(arr.get(i).get(col) == rand)
				return true;
		}
		return false;
	}
	public static boolean verifySquares(ArrayList<ArrayList<Integer>> arr, int rand, int row, int col){
		ArrayList<Integer> square = new ArrayList<Integer>();
		int newX = (row/3)*3;
		int newY = (col/3)*3;
		for(int i = newX; i < newX + 3; i++) {
			for (int j = newY; j < newY + 3; j++) {
				square.add(arr.get(i).get(j));
			}
		}
		return square.contains(rand);
	}
	public static boolean verifyFull(ArrayList<ArrayList<Integer>> arr, int rand, int row, int col) {
		return !verifyRows(arr, rand, row) && !verifyColumns(arr, rand, col) && !verifySquares(arr, rand, row, col);
	}
	public static boolean solve(ArrayList<ArrayList<Integer>> arr) {
		for(int r = 0; r < arr.size(); r++) {
			for(int c = 0; c < arr.get(0).size(); c++) {
				if(arr.get(r).get(c) == 0) {
					int rando = (int)(Math.random()*9)+1;
					for(int i = 1; i < 10; i++) {
						int temp = (i + rando - 1)%9 + 1;
						if(verifyFull(arr, temp, r, c)) {
							arr.get(r).set(c, temp);
							if (solve(arr)) {
		                        return true;
		                    }
		                    arr.get(r).set(c, 0);
						}
					}
					return false;
				}
			}
		}
		return true;
	}
	public static void printBoard(ArrayList<ArrayList<Integer>> arr){
	    for(ArrayList<Integer> subArr : arr) {
	        System.out.print("| ");
	        int ct = 0;
	        for(int i = 0; i < subArr.size(); i++){
	            if(ct == 3){
	                System.out.print(" | ");
	                ct = 0;
	                i--;
	            }
	            else{
	                ct++;
	                System.out.print(subArr.get(i) + " ");
	            }
	        }
	        System.out.println("|");
	    }
	}
}