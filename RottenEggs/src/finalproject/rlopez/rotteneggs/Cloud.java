package finalproject.rlopez.rotteneggs;

public class Cloud {

	public static final int SMALL = 0;
	public static final int MEDIUM = 1;
	public static final int LARGE = 2;
	public static final int SMALL_WIDTH = 46;
	public static final int SMALL_HEIGHT = 14;
	public static final int MEDIUM_WIDTH = 86;
	public static final int MEDIUM_HEIGHT = 28;
	public static final int LARGE_WIDTH = 120;
	public static final int LARGE_HEIGHT = 36;
	
	public float x, y;
	public int width, height;
	public int type;
	
	public Cloud(int type) {
		this.type = type;
		setPosition(type);
		setDimensions(type);
	}
	
	private void setDimensions(int type) {
		switch(type) {
		case 0: width = SMALL_WIDTH;
				height = SMALL_HEIGHT;
				break;
		case 1: width = MEDIUM_WIDTH;
				height = MEDIUM_HEIGHT;
				break;
		case 2: width = LARGE_WIDTH;
				height = LARGE_HEIGHT;
				break;
		}
	}
	
	private void setPosition(int type) {
		switch(type) {
		case 0: x = 120;
				y = 45;
				break;
		case 1: x = 200;
				y = 65;
				break;
		case 2: x = 25;
				y = 80;
				break;
		}
	}
	
}
