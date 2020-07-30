package game;

import java.util.Scanner;

public class Grid {

	private int widthX, heightY;
	private byte byteArray[][];

	public Grid(int widthX, int heightY) {
		this.widthX = widthX;
		this.heightY = heightY;
		byteArray = new byte[widthX][heightY];
	}

	/**
	 * Fills the grid with user input.
	 */
	public void enterGenerationZero() {

		for (int y = 0; y < heightY; y++) {
			Scanner sc = new Scanner(System.in);
			String s = sc.nextLine();

			for (int x = 0; x < s.length(); x++) {
				byteArray[x][y] = (byte) Character.getNumericValue(s.charAt(x));
			}
		}
	}

	/*
	 * Transforms the grid to the next Generation.
	 */
	public void transformGrid() {
		// make a copy of the 2d array 
		byte[][] byteArrayCopy = new byte[byteArray.length][];
		for (int i = 0; i < byteArray.length; i++) {
			byte[] aByteArray = byteArray[i];
			int aLength = aByteArray.length;
			byteArrayCopy[i] = new byte[aLength];
			System.arraycopy(aByteArray, 0, byteArrayCopy[i], 0, aLength);
		}
		
		//use the copy of the array to determine the color of a cell
		//and change it's color in the original array
		for (int y = 0; y < heightY; y++) {
			for (int x = 0; x < widthX; x++) {
				int greenNeighbours = Helper.countGreenNeighbours(x, y, byteArrayCopy);
				byteArray[x][y] = Helper.transformCell(byteArrayCopy[x][y], greenNeighbours);
			}
		}
	}

	
	public void calculateResult(int x, int y, int N) {
		int count = 0;
		count += byteArray[x][y]; // including generation zero;
		for (int i = 0; i < N; i++) {
			transformGrid();
			count += byteArray[x][y];
		}

		System.out.println("Result: " + count);
	}

}
