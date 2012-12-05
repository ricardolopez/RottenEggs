package finalproject.rlopez.rotteneggs;

import java.util.List;

import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Input.TouchEvent;
import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.Game;

public class MainMenuScreen extends Screen {

	public MainMenuScreen(Game game) {
		super(game);
	}
	
	@Override
	public void update(float time) {
		
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();

		int size = touchEvents.size();

		for(int i = 0; i < size; i++) {
			TouchEvent event = touchEvents.get(i);
			if(event.type == TouchEvent.TOUCH_UP) {
				if(tapScreen(event, 0, 0, 320, 480) ) {
					game.setScreen(new GameScreen(game));
					return;
				}
			}
		}
	}
	
	private boolean tapScreen(TouchEvent event, int x, int y, int width, int height) {
		
		if(event.x > x && event.x < x + width - 1 && 
		   event.y > y && event.y < y + height - 1) {
			return true;
		} else {
			return false;
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
