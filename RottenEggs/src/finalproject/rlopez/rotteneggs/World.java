package finalproject.rlopez.rotteneggs;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class World {

	public static int SPLAT_OFFSET = 20;
	public static int EGG_Y_OFFSET = 30;
	public static int BASKET_START_X = 120;
	public static int BASKET_START_Y = 400;
	public Random random;
	public Basket basket;
	public List<Egg> eggs;
	public List<Splat> splats;
	public int x_coord, y_coord, egg_type, egg_index;
	public float timePassed;
	public boolean egg_break;
	
	public World() {
		basket = new Basket(BASKET_START_X, BASKET_START_Y);
		eggs = new ArrayList<Egg>();
		splats = new ArrayList<Splat>();
		random = new Random();
		x_coord = y_coord = egg_type = egg_index = 0;
		timePassed = 1;
		egg_break = false;
	}
	
	public void checkPlaceEgg(float time) {
		timePassed += time;
		
		if ((int)timePassed % 2 == 0) {
			x_coord = random.nextInt(275) + EGG_Y_OFFSET/2;
			egg_type = random.nextInt(4);
			eggs.add(new Egg(x_coord, EGG_Y_OFFSET, egg_type));
			timePassed = 1;
		}
	}
	
	public void checkSplats() {
		for(Egg e: eggs) {
			if (hasSplat(e)) {
				egg_break = true;
				break;
			} else {
				egg_index++;
			}
		}
		if(egg_break) {
			removeEgg(egg_index);
			egg_break = false;
		}
		egg_index = 0;
	}
	
	public boolean hasSplat(Egg egg) {
		if ((basket.y + SPLAT_OFFSET) <= egg.y) {
			splats.add(new Splat(egg.x, egg.y, egg.type));
			return true;
		} else {
			return false;
		}
	}
	
	public void removeEgg(int index) {
		eggs.remove(index);
	}
	
	public void updateEggs(float time) {
		for(Egg e: eggs) {
			e.y += 2;
		}
	}
	
	public void updateBasket(int x) {
		basket.x = x;
	}
}
