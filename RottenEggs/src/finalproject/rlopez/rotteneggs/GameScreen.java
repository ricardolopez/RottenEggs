package finalproject.rlopez.rotteneggs;

import java.util.List;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Input.TouchEvent;
import com.badlogic.androidgames.framework.Screen;

public class GameScreen extends Screen {
	
	enum GameState {
		Main, Controls, Running, GameOver, Win, Lose
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
		state = GameState.Main;
		render = new Renderer(game.getGraphics());
	}
	
	@Override
	public void update(float time) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();
		
		if (state == GameState.Main) updateMain(touchEvents); 
		if (state == GameState.Controls) updateControls(touchEvents);
		if (state == GameState.Running) updateRunning(touchEvents, time);
		if (state == GameState.Win) drawWinLoseUI(touchEvents);
		if (state == GameState.Lose) drawWinLoseUI(touchEvents);
	}
	
	private void updateMain(List<TouchEvent> touchEvents) {
		render.renderMain();
		for (int i = 0; i < touchEvents.size(); i++) {
			TouchEvent event = touchEvents.get(i);
			
			if (event.type == TouchEvent.TOUCH_DOWN) {
				if(touchPlay(event.x, event.y)) {
					state = GameState.Running;
				}
				if(touchControls(event.x, event.y)) {
					touchEvents.removeAll(touchEvents);
					state = GameState.Controls;
				}
			}
		}
	}
	
	private void updateControls(List<TouchEvent> touchEvents) {
		render.renderControls();
		for (int i = 0; i < touchEvents.size(); i++) {
			TouchEvent event = touchEvents.get(i);
			
			if (event.type == TouchEvent.TOUCH_DOWN) {
				state = GameState.Main;
			}
		}
	}
	
	private boolean touchPlay(int x, int y) {
		if (x >= 97 && x <= 223 &&
		    y >= 182 && y <= 224) {
		    return true;
		} else {
		    return false;
		}
	}
	
	private boolean touchControls(int x, int y) {
		if (x >= 97 && x <= 223 &&
		    y >= 250 && y <= 292) {
		    return true;
		} else {
		    return false;
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
		world.checkCatchEgg();
		world.updateBasket(basket_x);
		world.updateEggs(time);
		world.updateClouds(time);
		if (world.checkWin()) {
			state = GameState.Win;
			render.renderWinLose(world, true);
		}
		if (world.checkLose()) {
			state = GameState.Lose;
			render.renderWinLose(world, false);
		}
	}
	
	@Override
	public void present(float time) {
		if (state == GameState.Running) drawRunningUI();
	}
	
	public void drawRunningUI() {
		render.renderWorld(world);
	}
	
	public void drawWinLoseUI(List<TouchEvent> touchEvents) {
		if (state == GameState.Win){
			render.renderWinLose(world, true);
		} else {
			render.renderWinLose(world, false);
		}
		
		for (int i = 0; i < touchEvents.size(); i++) {
			TouchEvent event = touchEvents.get(i);
			
			if (event.type == TouchEvent.TOUCH_DOWN) {
				world.resetWorld();
				state = GameState.Main;
			}
		}
	}
	
	private boolean touchBasket(int x, int y) {
		
		if (x > world.basket.x-40 && 
			y > world.basket.y && 
		    x < (world.basket.width + world.basket.x + 40) && 
		    y < (world.basket.height + world.basket.y + 40)) {
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
