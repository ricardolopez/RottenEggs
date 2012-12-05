package finalproject.rlopez.rotteneggs;

import android.util.Log;

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
		g.drawPixmap(Assets.gs, 0, 0);
		g.drawPixmap(Assets.basket, world.basket.x, world.basket.y);
		renderClouds(world);
		renderEggs(world);
		renderSplats(world);
	}
	
	private void renderClouds(World world) {
		for(Cloud cloud: world.clouds) {
			switch(cloud.type) {
			case 0: g.drawPixmap(Assets.cloud3, (int)cloud.x, (int)cloud.y);
					break;
			case 1: g.drawPixmap(Assets.cloud2, (int)cloud.x, (int)cloud.y);
					break;
			case 2: g.drawPixmap(Assets.cloud1, (int)cloud.x, (int)cloud.y);
					break;
			default:break;
			}
		}
	}
	
	private void renderEggs(World world) {
		for(Egg egg: world.eggs) {
			switch(egg.type) {
			case 0: g.drawPixmap(Assets.red_egg, egg.x, egg.y);
					break;
			case 1: g.drawPixmap(Assets.blue_egg, egg.x, egg.y);
					break;
			case 2: g.drawPixmap(Assets.green_egg, egg.x, egg.y);
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
			case 2: g.drawPixmap(Assets.green_splat, splat.x - World.SPLAT_OFFSET, splat.y);
					break;
			}
		}
	}
	
	
}
