import java.util.ArrayList;
import java.util.HashMap;
// TODO there should be more lines
// TODO threading
public class Line {
	public int startX,endX,startY,endY;
	private static final int RWIDTH = TriangleProblem.WIDTH-1, RHEIGHT = TriangleProblem.HEIGHT-1; // R was for relative in this class
	// previous working - change RWIDTH and RHEIGHT with TriangleProblem.WIDTH and TriangleProblem.HEIGHT
	// previously RWIDTH and RHEIGHT was = TriangleProblem.WIDTH-1 and TriangleProblem.HEIGHT-1
	public static ArrayList<Line> lines = new ArrayList<>();
	public Line(int startX, int startY, int endX, int endY) {
		this.startX = startX;
		this.startY = startY;
		this.endX = endX;
		this.endY = endY;
	}

	public static void CreateAllLines() {
		int counter = 0, count = 1;
		while (counter!=RWIDTH/*/2*/) {
			for (int i = 0; i <= RHEIGHT; i++){
				for (int j = 0; j <= RWIDTH; j++){
					/*if (j<TriangleProblem.WIDTH-1)
					lines.add(new Line(j, i, (j+1), i));
					 */
				
					/*else */ if (j<RWIDTH-counter) {
						lines.add(new Line(j,i,(j+count), i)); // keep in mind that line 1, 2, 3, 4 is the
						// same line as 2, 1, 4, 3
					}
					if (i<RHEIGHT-counter) {
						lines.add(new Line(j,i,j,(i+count)));
					} // ???
					System.out.println(i);
				}
			}
		counter++; count++;
		}
		for (int i = 0; i <= RHEIGHT; i++){
			for (int j = 0; j < RWIDTH; j++){
				if (j%2!=0&&i%2==0&&i!=RHEIGHT) {
					lines.add(new Line(j,i,j+1,i+1));
				} 
				if(j%2==0&&i%2!=0&&i!=RHEIGHT) {
					lines.add(new Line(j,i,j+1,i+1)); 
				}
				
				if (i>0&&j%2!=0&&i%2==0) {
					lines.add(new Line(j,i,j+1,i-1)); 
				}
				if (i>0&&j%2==0&&i%2!=0) 
				{
					lines.add(new Line(j,i,j+1,i-1)); 
				}
				// TODO ^ this is not the general case; there are lines formed by these lines that are unaccounted for
			}
		}
		
		//  make an array with all the "special lines" in the case above ^ and concatenate them together to form the bigger lines
		ConcatenateSpecialLines();
	}

	public static void ConcatenateSpecialLines() {
		// fill the array list with the diagonal lines
		ArrayList<Line> tempLine = new ArrayList<>();
		int counter = 2, specialCounter = 2;
		for (Line line : lines) {
			if (((line.endX == line.startX + 1)&&(line.endY==line.startY+1))) {
				tempLine.add(line);
			}
		}
		System.out.println();
		for (Line line : tempLine) {
			System.out.println("startX: " + line.startX + " startY: " + line.startY + " endX: " + line.endX + " endY: " + line.endY);
			// concatenate and save every line (in the lines ArrayList) made up of 2 or more lines, whose coordinates are +1 to both the startX and startY variables in the previous line
			while (counter <= TriangleProblem.WIDTH - (line.endX) && !SpecialCase(line)) {
			lines.add(new Line(line.startX, line.startY, line.startX + counter, line.startY +counter)); // because counter starts from 2
			counter++;
			} // TODO what is the special case an what to do in it
				if (SpecialCase(line)){
							while (specialCounter <= TriangleProblem.WIDTH - (line.endY+2)) {
								lines.add(new Line(line.startX, line.startY, line.startX + specialCounter, line.startY + specialCounter));
								specialCounter++;
					}
				} 
			counter = 2;
			specialCounter = 2;
		}
		System.out.println();
		System.out.println();
		// TODO this is not counting all the lines; count all the lines
		tempLine.clear();
		for (Line line : lines) {
			if (((line.endX == line.startX + 1)&&(line.endY==line.startY-1))) {
				tempLine.add(line);
			}
		}
		for (Line line : tempLine) {
			System.out.println("startX: " + line.startX + " startY: " + line.startY + " endX: " + line.endX + " endY: " + line.endY);
			// concatenate and save every line (in the lines ArrayList) made up of 2 or more lines, whose coordinates are +1 and -1 the startX and startY variables in the previous line
			while (counter <= line.endX && !SpecialCaseRight(line) && line.startX - counter >= 0) { // < counter???
			lines.add(new Line(line.endX, line.endY, line.endX - counter, line.endY + counter)); // because counter starts from 2      
			// ^^^ TODO should endX and endY be substituted with startX and startY(as they were before)???
			counter++;
			} // TODO what is the special case an what to do in it
				if (SpecialCaseRight(line)){
							while (specialCounter <= TriangleProblem.HEIGHT - (line.endY) && line.endY != 12) {
								lines.add(new Line(line.endX, line.endY, line.endX - specialCounter, line.endY + specialCounter));
								specialCounter++; // TODO I might need to change some RWIDTHs and RHEIGHTS to normal WIDTH and HEIGHT and is it line.startX + specialCounter or -
							}
					}
			counter = 2;
			specialCounter = 2;
		}
	
	} 

	
	// if the line is the first the while condition counter < RWIDTH - (line.startX+2) would not work
	// first on the first; first 2 on the second; first 2 on the third; first 3 on the 4th and 5th; first 4 on the 6th and 7th; first 5 on the 8th and 9th; first 6 on the 10th and 11th; first 7 on the 12th
	public static boolean SpecialCase(Line line) {
			 if (line.startX <= line.endY) { return true; }
			// TODO finish the special case ???
			return false;
	}
	
	public static boolean SpecialCaseRight(Line line) {
		if (line.endX+line.endY>=TriangleProblem.HEIGHT) { return true; }
		return false;
	}
	
	public static ArrayList<Line> fillList(Line key, HashMap<Line, ArrayList<Line>> map, Line toAdd) {
		ArrayList<Line> list;
		if (map.get(key) == null) {
			list = new ArrayList<>();
			list.add(toAdd);
			map.put(key, list);
		}
		else {
			list = map.get(key);
			map.put(key, list);
		}
		return list;
			
	}
	
	public static boolean newCombination(Line line1, HashMap<Line, ArrayList<Line>> map, Line line2) {
		if (map.get(line1) == null && map.get(line2) == null) {
			return true;
		}
		else if (lineInList(line2, map.get(line1))) {
			map.get(line2).add(line1);
			return false;
		}
		else if (lineInList(line1, map.get(line2))) {
			map.get(line1).add(line2);
			return false;
		}
		return true;
	}
	
	public static boolean lineInList(Line line, ArrayList<Line> list) {
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).startX == line.startX && list.get(i).endX == line.endX && list.get(i).startY == line.startY && list.get(i).endY == line.endY){
				return true;
			}
		}
		return false;
	}
	
	// ???? 
	public static boolean Equals(Line line, Line line2) {
		HashMap<Line, ArrayList<Line>> usedCombinations = new HashMap<>();
		ArrayList<Line> dictionary;
		if ((line.startX==line2.startX)&&(line.endX==line2.endX)&&(line.endY==line2.endY)&&(line.startY==line2.startY) && newCombination(line, usedCombinations, line2)) {
			dictionary = fillList(line, usedCombinations, line2);
			return true;
		}
		/* it should not need to count the reversed lines (and this is also a wrong way to do so)
		 * if ((line.startX==line2.startY)&&(line.endX==line2.endY)&&(line.startY==line2.startX)&&(line.endY==line2.endX)) {
			return true;
		} 
		the right way would be 
		if ((line.startX==line2.endX)&&(line.endX==line2.startX)&&(line.startY==line2.endY)&&(line.endY==line2.startY)) {
			return true;
		*/
		// TODO this is wrong, and needs fixing
		/* if(((line.startX<line.endX && line.startY < line.endY) || (line2.startX<line2.endX && line2.startX < line2.endY))&&((line.startX>line.endX && line.startY < line.endY) || (line2.startX>line2.endX && line2.startX < line2.endY))){
			if ((line.startX==line2.endX)&&(line.endX==line2.startX)&&(line.endY==line2.startY)&&(line.startY==line2.endY))			
			{
				return true;
			}
		
		}*/
		
		if ((line.startX==line2.endX)&&(line.endX==line2.startX)&&(line.startY==line2.endY)&&(line.endY==line2.startY)&& newCombination(line, usedCombinations, line2)) {
			dictionary = fillList(line, usedCombinations, line2);
			return true;
		}
		
		return false;
	}

	
}
