package finalproject.rlopez.rotteneggs;

import android.graphics.Color;

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
		renderScore(world);
		renderTarget(world);
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
		int offset = 0;
		for(Egg egg: world.eggs) {
			if (egg.value > 9) {
				offset = 6;
			} else {
				offset = 12;
			}
			switch(egg.type) {
			case 0: g.drawPixmap(Assets.red_egg, egg.x, egg.y);
					g.drawText(Integer.toString(egg.value), egg.x+offset, egg.y+28, 22, Color.WHITE);
					break;
			case 1: g.drawPixmap(Assets.blue_egg, egg.x, egg.y);
					g.drawText("!", egg.x+15, egg.y+28, 22, Color.WHITE);
					break;
			case 2: g.drawPixmap(Assets.green_egg, egg.x, egg.y);
					g.drawText(Integer.toString(egg.value), egg.x+offset, egg.y+28, 22, Color.WHITE);
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
	
	private void renderScore(World world) {
		g.drawText("Score:", 210, 30, 20, Color.BLACK);
		g.drawText(Integer.toString(world.score), 270, 30, 25, Color.BLACK);
	}
	
	private void renderTarget(World world) {
		g.drawText(Integer.toString(world.target), 20, 30, 25, Color.BLACK);
	}
	
	public void renderWin() {
		g.drawRect(0, 0, 320, 480, Color.BLACK);
		g.drawText("'YOU WIN!'", 80, 220, 30, Color.WHITE);
	}
	
	public void renderLose() {
		g.drawRect(0, 0, 320, 480, Color.BLACK);
		g.drawText("'YOU LOSE!'", 78, 220, 30, Color.WHITE);
	}
	
	
}
