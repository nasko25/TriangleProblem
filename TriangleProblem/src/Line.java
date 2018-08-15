import java.util.ArrayList;

public class Line {
	public int startX,endX,startY,endY;
	private static final int RWIDTH = TriangleProblem.WIDTH, RHEIGHT = TriangleProblem.HEIGHT; // R was for relative in this class
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
		int counter = 1;
		while (counter!=TriangleProblem.WIDTH/*/2*/) {
			for (int i = 0; i < RHEIGHT; i++){
				for (int j = 0; j < RWIDTH; j++){
					/*if (j<TriangleProblem.WIDTH-1)
					lines.add(new Line(j, i, (j+1), i));
					 */
				
					/*else */ if (j<RWIDTH-counter) {
						lines.add(new Line(j,i,(j+counter), i)); // keep in mind that line 1, 2, 3, 4 is the
						// same line as 3, 4, 1, 2
					}
					if (i<RHEIGHT-counter) {
						lines.add(new Line(j,i,j,(i+counter)));
					} // ???
					
				}
			}
		counter++;
		}
		for (int i = 0; i < RHEIGHT; i++){
			for (int j = 0; j < RWIDTH; j++){
				if (j%2!=0&&i%2==0) {
					lines.add(new Line(j,i,j+1,i+1));
				} 
				if(j%2==0&&i%2!=0) {
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
		
		// TODO make an array with all the "special lines" in the case above ^ and concatenate them together to form the bigger lines
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
			while (counter <= RWIDTH - (line.endX) && !SpecialCase(line)) {
			lines.add(new Line(line.startX, line.startY, line.startX + counter, line.startY +counter)); // because counter starts from 2
			counter++;
			} // TODO what is the special case an what to do in it
				if (SpecialCase(line)){
							while (specialCounter <= RWIDTH - (line.startX+line.startY+2)) {
								// lines.add(new Line(line.startX, line.startY, line.endX + specialCounter, line.endY + specialCounter));
								specialCounter++;
					}
				} 
			counter = 2;
			specialCounter = 2;
		}
		System.out.println();
	}       // TODO ^ function does not account for (line.endX == line.startX + 1) && (line.endY == line.startY - 1) 
	
	// if the line is the first the while condition counter < RWIDTH - (line.startX+2) would not work
	// first on the first; first 2 on the second; first 2 on the third; first 3 on the 4th and 5th; first 4 on the 6th and 7th; first 5 on the 8th and 9th; first 6 on the 10th and 11th; first 7 on the 12th
	public static boolean SpecialCase(Line line) {
			 if (line.startX <= line.endY) { return true; }
			// TODO finish the special case
			return false;
	}
	
	// ???? 
	public static boolean Equals(Line line, Line line2) {
		if ((line.startX==line2.startX)&&(line.endX==line2.endX)&&(line.endY==line2.endY)&&(line.startY==line2.startY)) {
			return true;
		}
		return false;
	}

	
}
