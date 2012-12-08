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
	public List<Cloud> clouds;
	public int score, value, target;
	public int x_coord, y_coord, egg_type, egg_index, catch_index;
	public float timePassed;
	public boolean egg_break, caught_egg, change_egg;
	
	public World() {
		basket = new Basket(BASKET_START_X, BASKET_START_Y);
		eggs = new ArrayList<Egg>();
		splats = new ArrayList<Splat>();
		clouds = new ArrayList<Cloud>();
		random = new Random();
		initClouds();
		x_coord = y_coord = egg_type = egg_index = catch_index = 0;
		timePassed = 1;
		target = 120;
		egg_break = false;
		caught_egg = false;
		change_egg = false;
	}
	
	public void initClouds() {
		for (int i = 0; i < 3; i++) {
			clouds.add(new Cloud(i));
		}
	}
	
	public void checkPlaceEgg(float time) {
		timePassed += time;
		
		if (timePassed > 1.50) {
			x_coord = random.nextInt(275) + EGG_Y_OFFSET/2;
			egg_type = getType(random.nextInt(100));
			value = random.nextInt(19)+1;
			eggs.add(new Egg(x_coord, EGG_Y_OFFSET, egg_type, value));
			timePassed = 1;
		}
	}
	
	private int getType(int type) {
		if (type < 10) {
			return 1;
		} else {
			return 2;
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
		if (change_egg) {
			setNewEggTypes();
		}
		for(Egg egg: eggs) {
			egg.y += 6;
		}
	}
	
	public void updateClouds(float time) {
		for(Cloud cloud: clouds) {
			switch(cloud.type) {
			case 0: 
				if(cloud.x > 320) {
					cloud.x = 0-cloud.width;
				} else {
					cloud.x += time*5;
				}
				break;
			case 1: 
				if(cloud.x < 0-cloud.width) {
					cloud.x = 320;
				} else {
					cloud.x -= time*10;
				}
				break;
			case 2: 
				if(cloud.x > 320) {
					cloud.x = 0-cloud.width;
				} else {
					cloud.x += time*20;
				}
				break;
			}
		}
	}
	
	public void checkCatchEgg() {
		for(Egg e: eggs) {
			if (hasCaught(e)) {
				if (e.type == 1) {
					change_egg = true;
				}
				score += e.value;
				caught_egg = true;
				break;
			} else {
				catch_index++;
			}
		}
		if(caught_egg) {
			removeEgg(catch_index);
			caught_egg = false;
		}
		catch_index = 0;
	}
	
	public void setNewEggTypes() {
		for(Egg egg: eggs) {
			if ((egg.value + score) > target) {
				egg.type = 0;
			}
		}
	}
	
	public boolean checkWin() {
		if (score == target) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean checkLose() {
		if (score > target) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean hasCaught(Egg egg) {
		if ((egg.y + egg.height) > basket.y &&
			 ((egg.x > basket.x && egg.x < basket.x+basket.width)
			 || (egg.x+egg.width > basket.x && egg.x+egg.width < basket.x+basket.width))) {
			return true;
		} else {
			return false;
		}
	}
	
	public void updateBasket(int x) {
		basket.x = x;
	}
}
