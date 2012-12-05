package finalproject.rlopez.rotteneggs;

import java.util.List;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Input.TouchEvent;
import com.badlogic.androidgames.framework.Screen;

public class GameScreen extends Screen {
	
	enum GameState {
		Ready, Running, Paused, GameOver
	}
	
	public GameState state;
	public World world;
	public Renderer render;
	public boolean drag_basket;
	public int delta_x, old_basket, basket_x;
	
	public GameScreen(Game game) {
		super(game);
		drag_basket = false;
		world = new World();
		state = GameState.Ready;
		render = new Renderer(game.getGraphics());
	}
	
	@Override
	public void update(float time) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();
		
		if (state == GameState.Ready) updateReady(touchEvents);
		if (state == GameState.Running) updateRunning(touchEvents, time);
	}
	
	private void updateReady(List<TouchEvent> touchEvents) {
		if (touchEvents.size() > 0) {
			state = GameState.Running;
		}
	}
	
	private void updateRunning(List<TouchEvent> touchEvents, float time) {
		
		for (int i = 0; i < touchEvents.size(); i++) {
			TouchEvent event = touchEvents.get(i);
			
			if (event.type == TouchEvent.TOUCH_DOWN) {
				if(touchBasket(event.x, event.y)) {
					delta_x = event.x;
					old_basket = world.basket.x;
				}
			}
			if (event.type == TouchEvent.TOUCH_DRAGGED) {
				if (drag_basket) {
					basket_x = event.x - delta_x + old_basket;
				}
			}
		}
		
		world.checkPlaceEgg(time);
		world.checkSplats();
		world.updateBasket(basket_x);
		world.updateEggs(time);
		world.updateClouds(time);
	}
	
	@Override
	public void present(float time) {
		render.renderSplash();
		
		if (state == GameState.Running)drawRunningUI();
	}
	
	public void drawRunningUI() {
		render.renderWorld(world);
	}
	
	private boolean touchBasket(int x, int y) {
		
		if (x > world.basket.x && 
			y > world.basket.y && 
		    x < (world.basket.width + world.basket.x) && 
		    y < (world.basket.height + world.basket.y)) {
			drag_basket = true;
		} else {
			drag_basket = false;
		}
		return drag_basket;
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
