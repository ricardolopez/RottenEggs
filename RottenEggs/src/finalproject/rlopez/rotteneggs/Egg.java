package finalproject.rlopez.rotteneggs;

public class Egg {
	
	public static final int RED = 0;
	public static final int BLUE = 1;
	public static final int GREEN = 2;
	public int x, y;
	public int width, height;
	public int type;
	public int value;
	
	public Egg(int x, int y, int type, int value) {
		this.x = x;
		this.y = y;
		this.type = type;
		this.width = 36;
		this.height = 40;
		this.value = value;
	}
	
}
