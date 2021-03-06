public class TriangleProblem {
	/// 7860       it is 51195
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

/*	private static long TriangleCount(char []oneD){
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
		return ALLtriangleCount;
	}
	*/
	public static Line[] ConvertPointToLine(int i, int j, int l) {
	// returns three Lines that are formed from the point i, j and l in the 1D array.  
	// I, j and l are numbers for the positions of the points in the array, and they must be converted into three lines, formed by connecting them. 
		Line[] triangle = new Line[3];
		InitilizeLineArray(triangle);
		int temp, counter;
		if (i - WIDTH < 0) {
			triangle[0].startX = i;
			triangle[0].startY = 0;
			triangle[1].startX = i;
			triangle[1].startY = 0;
		}
		else {
			temp = i; 
			counter = 0;
			while (temp > WIDTH) {
				temp = temp - WIDTH;
				counter++;
			}
			triangle[0].startX = temp;
			triangle[0].startY = counter;
			triangle[1].startX = temp;
			triangle[1].startY = counter;
		}
		if(j - WIDTH < 0) {
			triangle[0].endX = j;
			triangle[0].endY = 0;
			triangle[2].startX = j;
			triangle[2].startY = 0;
			
		}
		else {
			temp = j; 
			counter = 0;
			while (temp > WIDTH) {
				temp = temp - WIDTH;
				counter++;
			}
			triangle[0].endX = temp;
			triangle[0].endY = counter;
			triangle[2].startX = temp;
			triangle[2].startY = counter;
		}
		
		if (l - WIDTH < 0) {
			triangle[1].endX = l;
			triangle[1].endY = 0;
			triangle[2].endX = l;
			triangle[2].endY = 0;
		}
		else {
			temp = l; 
			counter = 0;
			while (temp > WIDTH) {
				temp = temp - WIDTH;
				counter++;
			}
			triangle[1].endX = temp;
			triangle[1].endY = counter;
			triangle[2].endX = temp;
			triangle[2].endY = counter;
		}
		return triangle;
	}
	
	public static long PossibleTrianglesCount(char oneD[]) {
		long PossibleTriangles = 0;
		int space = WIDTH; // -1 ????? TODO consider
		int count = 0; Line points[];
		for (int i = 0; i < oneD.length; i ++){
			for (int j = 0; j < oneD.length; j++){
				for (int l = 0; l < oneD.length; l++) {
					count = 0;
					points = ConvertPointToLine(i, j, l);
					if (i!=j&&i!=l&&j!=l) {																																	// This should fix when the points are in one line with reference to the (0,0) coordinate.																
						if((Math.abs(i-j)>=space||Math.abs(i-l)>=space||Math.abs(l-j)>=space)&&(Math.abs(i-j)%16!=0||Math.abs(i-l)%16!=0||Math.abs(l-j)%16!=0)&&(((Math.abs(points[0].startX-points[1].startX)==Math.abs(points[0].startY-points[1].startY)))||(Math.abs(points[0].startX-points[2].startX)==Math.abs(points[0].startY-points[2].startY))||(Math.abs(points[1].startX-points[2].startX)==Math.abs(points[1].startY-points[2].startY)))) {
							here: // TODO is this check valid ^ ??? Also, think how to optimize it.
							for (int c = 0; c < Line.lines.size(); c++){
							// check if line.startX, line.endX, line.startY and line.endY are actually lines that the triangles create
							// if so PossibleTriangles++;
								if (count == 3) {
									PossibleTriangles++;
									count = 0;
									break here;
								}
								if (Line.Equals(Line.lines.get(c), points[count])) {
									count++;
									c = 0;
								}
								
							}
						}
					}
					count = 0;
				}
			}
			if (space ==1) {space = WIDTH; }// WIDTH - 1???
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
	
	private static void InitilizeLineArray(Line[] triangle) {
		for (int i = 0; i < triangle.length; i++) {
			triangle[i] = new Line(0,0,0,0);
		}
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
		// long countT = TriangleCount(oneD);
		// System.out.print(countT);
		
		Line.CreateAllLines();
		System.out.print(" the lines are " + Line.lines.size());
		long countPossible = PossibleTrianglesCount(oneD);
		System.out.println();
		for (int i = 0; i < Line.lines.size(); i++) {
			System.out.println(Line.lines.get(i).startX + "," + Line.lines.get(i).startY + ";" + Line.lines.get(i).endX + "," + Line.lines.get(i).endY);
			System.out.print(i + "  ");
		}
		System.out.println("Possible triangles count = " + countPossible);
	}	
}
