import java.util.concurrent.ThreadLocalRandom;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2022년도 2학기
 * @author 김상진 
 * 2019136149 박건우 
 * @file RandomStrategy.java
 * 전략패턴: 구체적인 전략 클래스
 * 
 */

public class LastHandBasedStrategy implements PlayingStrategy{
	public HandType computeNextHand(GameInfo gameInfo)
	{	
		if(gameInfo.getLastUserHand() == null)
			return HandType.valueOf(ThreadLocalRandom.current().nextInt(3)); // RandomStrategy

		HandType lastUserHand = gameInfo.getLastUserHand();
		int ranNum1To100 = ThreadLocalRandom.current().nextInt(1, 101);
		
		if (!gameInfo.isPlayingMookJiBa()) 			// When : 가위바위보
			if (ranNum1To100 <= 20) { return lastUserHand.winValueOf(); }
			else
				return lastUserHand.winValueOf().getTheOtherHand();
		
		else 
			if (gameInfo.isUserAttack()) 
			{
				if(ranNum1To100 <= 80)	{ return lastUserHand; }
				else { return lastUserHand.getTheOtherHand(); }
			}
			
			else // computerAttack
			{
				if(ranNum1To100 <= 20) { return lastUserHand; }
				else { return lastUserHand.getTheOtherHand(); }
			}
		}
	}
