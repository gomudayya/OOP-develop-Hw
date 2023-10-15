import java.util.ArrayList;

/**
 * 
 * 2019136149 박건우
 */ 

public class GameInfo {
	private HandType lastUserHand; 
	private boolean isUserAttack;
	private boolean playingMookJiBa;
	private ArrayList<HandType> userHandData = new ArrayList<HandType>();
	
	
	public HandType getLastUserHand() {
		return lastUserHand;
	}
	public void setLastUserHand(HandType lastUserHand) {
		this.lastUserHand = lastUserHand;
		userHandData.add(lastUserHand);
	}
	public boolean isUserAttack() {
		return isUserAttack;
	}
	public void setUserAttack(boolean isUserAttack) {
		this.isUserAttack = isUserAttack;
	}
	public boolean isPlayingMookJiBa() {
		return playingMookJiBa;
	}
	public void setPlayingMookJiBa(boolean playingMookJiBa) {
		this.playingMookJiBa = playingMookJiBa;
	}
	public ArrayList<HandType> getUserHandData() {
		return userHandData;
	}
}