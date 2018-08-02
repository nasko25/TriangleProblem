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
				} // TODO finish for all lines
				if(j%2==0&&i%2!=0) {
					lines.add(new Line(j,i,j+1,i+1)); 
				}
				
				if (/*i>0&&*/j%2!=0&&i%2==0) {
					lines.add(new Line(j,i,j+1,i-1)); 
				}
				if (/*i>0&&*/j%2==0&&i%2!=0) 
				{
					lines.add(new Line(j,i,j+1,i-1)); 
				}
				
			}
		}
	}

	// ???? 
	public static boolean Equals(Line line, Line line2) {
		if ((line.startX==line2.startX||line.endX==line2.startX)&&(line.startX==line2.endX||line.endX==line2.endX)) {
			return true;
		}
		if ((line.startY==line2.endY||line.endY==line2.endY)&&(line.startY==line2.startY||line.endY==line2.startY)) {
			return true;
		}
		return false;
	}

	
}
