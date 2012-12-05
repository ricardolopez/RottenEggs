package finalproject.rlopez.rotteneggs;

import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.impl.AndroidGame;

public class RottenEggs extends AndroidGame {

	public Screen getStartScreen() {
		return new SplashScreen(this);
	}
	
}
