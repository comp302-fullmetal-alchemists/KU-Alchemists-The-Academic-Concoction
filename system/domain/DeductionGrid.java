package system.domain;

import java.util.ArrayList;
import java.util.List;

public class DeductionGrid {

	int[][] grid;
	
	public DeductionGrid() {
		grid = new int[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j ++) {
				grid[i][j] = 0;
			}
		}
	}
	
	public int[][] getGrid() {
		return grid;
	}
	
	public List<Integer> getMarkers() {
		List<Integer> markerList = new ArrayList<Integer>();
		for (int i = 0; i < 8; i ++) {
			for (int j = 0; j < 8; j++) {
				if (grid[i][j] == 1) markerList.add(8*i + j);
			}
		}
		return markerList;
	}
	
	public void mark(int i, int j, int val) {
		grid[i][j] = val;
	}
	
}
