package finalproject.rlopez.rotteneggs;

import com.badlogic.androidgames.framework.Graphics;

public class Renderer {
	
	public Graphics g;
	public int egg_index;
	public boolean egg_break;
	
	public Renderer (Graphics g) {
		this.g = g;
		egg_index = 0;
		egg_break = false;
	}

	public void renderSplash() {
		g.drawPixmap(Assets.sp, 0, 0);
	}
	
	public void renderWorld(World world) {
		g.drawPixmap(Assets.gs, 0, 0, 0, 0, 320, 480);
		g.drawPixmap(Assets.basket, world.basket.x, world.basket.y);
		renderEggs(world);
		renderSplats(world);
	}
	
	private void renderEggs(World world) {
		for(Egg egg: world.eggs) {
			switch(egg.type) {
			case 0: g.drawPixmap(Assets.red_egg, egg.x, egg.y);
					break;
			case 1: g.drawPixmap(Assets.blue_egg, egg.x, egg.y);
					break;
			case 3: g.drawPixmap(Assets.green_egg, egg.x, egg.y);
					break;
			}
		}
	}
	
	private void renderSplats(World world) {
		if (world.splats.size() > 1) {
			world.splats.remove(0);
		}
		for(Splat splat: world.splats) {
			switch(splat.type) {
			case 0: g.drawPixmap(Assets.red_splat, splat.x - World.SPLAT_OFFSET, splat.y);
					break;
			case 1: g.drawPixmap(Assets.blue_splat, splat.x - World.SPLAT_OFFSET, splat.y);
					break;
			case 3: g.drawPixmap(Assets.green_splat, splat.x - World.SPLAT_OFFSET, splat.y);
					break;
			}
		}
	}
	
	
}
