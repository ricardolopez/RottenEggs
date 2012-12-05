package finalproject.rlopez.rotteneggs;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Graphics.PixmapFormat;
import com.badlogic.androidgames.framework.Screen;

public class SplashScreen extends Screen {

	public SplashScreen(Game game) {
		 super(game);
	}
	
	@Override
	public void update(float time) {
		
		Graphics g = game.getGraphics();
		Assets.splash_screen = g.newPixmap("splash_screen.png", PixmapFormat.RGB565);
		Assets.game_screen = g.newPixmap("game_screen.png", PixmapFormat.RGB565);
		Assets.sp = g.newPixmap("sp.png", PixmapFormat.RGB565);
		Assets.gs = g.newPixmap("gs.png", PixmapFormat.RGB565);
		Assets.cloud1 = g.newPixmap("cloud1.png", PixmapFormat.ARGB4444);
		Assets.cloud1 = g.newPixmap("cloud2.png", PixmapFormat.ARGB4444);
		Assets.cloud1 = g.newPixmap("cloud3.png", PixmapFormat.ARGB4444);
		Assets.basket = g.newPixmap("basket.png", PixmapFormat.ARGB4444);
		Assets.blue_egg = g.newPixmap("blue_egg.png", PixmapFormat.ARGB4444);
		Assets.blue_splat = g.newPixmap("blue_splat.png", PixmapFormat.ARGB4444);
		Assets.green_egg = g.newPixmap("green_egg.png", PixmapFormat.ARGB4444);
		Assets.green_splat = g.newPixmap("green_splat.png", PixmapFormat.ARGB4444);
		Assets.red_egg = g.newPixmap("red_egg.png", PixmapFormat.ARGB4444);
		Assets.red_splat = g.newPixmap("red_splat.png", PixmapFormat.ARGB4444);
		game.setScreen(new MainMenuScreen(game));
	}
	
	@Override
	public void pause() {
		
	}
	
	@Override
	public void present(float time) {
	
	}
	
	@Override
	public void resume() {
		
	}
	
	@Override
	public void dispose() {
		
	}
	
}
