public class TriangleProblem {
	public static final int HEIGHT = 14;
	public static final int WIDTH = 16;
	private static char[][] MakeTheCoordinateSystem() {
		char[][] co = new char[HEIGHT][WIDTH];
		for (int i = 0; i < HEIGHT; i ++)
			for (int j = 0; j < WIDTH; j++){
				co[i][j] = '.';
			}
		
		return co;
	}

	private static long TriangleCount(char []oneD){
		long ALLtriangleCount = 0;
		int space = WIDTH - 1; // between two of the three dots (first and last)
		for (int i = 0; i < oneD.length; i ++){
			for (int j = 0; j < oneD.length; j++){
				for (int l = 0; l < oneD.length; l++) {
					if (i!=j&&i!=l&&j!=l) {
						if(Math.abs(i-j)>=space||Math.abs(i-l)>=space||Math.abs(l-j)>=space) {
							ALLtriangleCount++;
						}
					}
				}
			}
			if (space ==0) {space = WIDTH-1;}
			else{space--;}
		}
		// TODO make this ^ do the same but in the opposite direction	???
		return ALLtriangleCount;
	}
	
	public static Line[] ConvertPointToLine(int i, int j, int l) {
	// TODO must return three Lines that are formed from the point i, j and l in the 1D array.  
	// I, j and l are numbers for the positions of the points in the array, and they must be converted into three lines, formed by connecting them. 
		Line[] triangle = new Line[3];
		if (i - WIDTH <0 ) {
			triangle[0].startX = i;
			triangle[0].startY = 0;
			triangle[1].startX = i;
			triangle[1].startY = 0;
			if(j - WIDTH < 0) {
				triangle[0].endX = j;
				triangle[0].endY = 0;
				triangle[2].startX = j;
				triangle[2].startY = 0;
				if (l - WIDTH < 0) {
					return null;
				}
				else {
					int temp; 
				}
			}
		}
		
		return null;
	}
	
	public static long PossibleTrianglesCount(char oneD[]) {
		long PossibleTriangles = 0;
		int space = WIDTH - 1;
		for (int i = 0; i < oneD.length; i ++){
			for (int j = 0; j < oneD.length; j++){
				for (int l = 0; l < oneD.length; l++) {
					if (i!=j&&i!=l&&j!=l) {
						if(Math.abs(i-j)>=space||Math.abs(i-l)>=space||Math.abs(l-j)>=space) {
							for (Line line : Line.lines){
							// check if line.startX, line.endX, line.startY and line.endY are actually lines that the triangles create
							// if so PossibleTriangles++;
							continue; }
						}
					}
				}
			}
			if (space ==0) {space = WIDTH-1;}
			else{space--;}
		}
		return PossibleTriangles;
	}
	
	private static char[] ConvertTo1D(char co[][]) {
		int c = 0;
		char[] oneD = new char[HEIGHT*WIDTH];
		for (int i = 0; i < HEIGHT; i ++){
			for (int j = 0; j < WIDTH; j++){
			oneD[c] = co[i][j];	
			c++;	
			}
			}
		return oneD; 
	}
	
	public static void main (String args[]){
		char co[][] = MakeTheCoordinateSystem();
		for (int i = 0; i < HEIGHT; i ++){
			for (int j = 0; j < WIDTH; j++){
				System.out.print(co[i][j] + " ");
			}
		System.out.println();
		}
		char oneD[] = ConvertTo1D(co);
		long countT = TriangleCount(oneD);
		System.out.print(countT);
		
		Line.CreateAllLines();
		System.out.println();
		for (int i = 0; i < Line.lines.size(); i++) {
			System.out.println(Line.lines.get(i).startX + "," + Line.lines.get(i).startY + ";" + Line.lines.get(i).endX + "," + Line.lines.get(i).endY);
			System.out.print(i + "  ");
		}
		
	}	
}