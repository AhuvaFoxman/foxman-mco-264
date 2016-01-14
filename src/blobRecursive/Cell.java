package blobRecursive;

public class Cell{
	private boolean isBlob;
	private boolean visited;
	private int row;
	private int col;

	public Cell(int row, int col) {
		this.isBlob = false; // starts off as false
		this.visited = false;
		this.row = row;
		this.col = col;
	}

	public void setBlob(boolean bool) {
		this.isBlob = bool;
	}

	public void setVisited() {
		this.visited = true;
	}

	public String toString() {
		if (isBlob == false) {
			return "-";
		} else {
			return "x";
		}
	}

	public void resetVisited() {
		this.visited = false;
	}

	public boolean hasBlob() {
		return (this.isBlob);
	}

	public boolean isVisited() {
		return this.visited;
	}

	public void reset() {
		this.isBlob = false;
		this.visited = false;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	

}
