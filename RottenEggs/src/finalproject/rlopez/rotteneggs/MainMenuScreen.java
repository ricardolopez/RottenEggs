package finalproject.rlopez.rotteneggs;

import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.Game;

public class MainMenuScreen extends Screen {
	
	float time_passed;

	public MainMenuScreen(Game game) {
		super(game);
		time_passed = 0;
	}
	
	@Override
	public void update(float time) {
		time_passed += time;
		if(time_passed > 2) {
			game.setScreen(new GameScreen(game));
			return;
		}
	}
	
	@Override
	public void present(float time) {
		Graphics g = game.getGraphics();
		g.drawPixmap(Assets.sp, 0, 0);
	}
	
	@Override
	public void pause() {
		
	}
	
	@Override
	public void resume() {
		
	}
	
	@Override
	public void dispose() {
		
	}
	
}
