import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 
 * 2019136149 박건우 
 * LastBasedStrategy에서 조금 업그레이드된 전략
 * 
 * 의외로 두 번 연속으로 같은 손을 내는 사람들은 많다고 생각한다. 
 * 하지만 웬만해서는 세 번 연속으로 같은 손을 내진 않는다. 
 * 따라서 두 번 연속으로 같은 손을 냈을때만 LastBasedStrategy를 사용한다.
 * 
 * 그런데 유저가 작정하고 같은 손만 낼 수도 있으므로 만약에 세 번 연속으로 같은 손을내면
 * 마지막으로 낸 손을 잡아먹는 Hand를 낸다  (Counter Hand)
 * (가위바위보일땐 winValueOf, 묵찌빠 && userAttack이면 winValueOf, 묵지빠 && !userAttack이면 같은 손)
 * 나머지 경우의 상황에는 RandomStrategy를 사용한다.
 */
public class UpgradedLastBasedStrategy implements PlayingStrategy{
	public HandType computeNextHand(GameInfo gameInfo) {
		ArrayList <HandType> userHandData = gameInfo.getUserHandData();
		int lastIndex = userHandData.size() - 1;

		if (userHandData.size() < 3) // avoid IndexOutofBoundException
		{
			return HandType.valueOf(ThreadLocalRandom.current().nextInt(3)); //randomStrategy
		}

		if(userHandData.get(lastIndex) == userHandData.get(lastIndex-1))
			if (userHandData.get(lastIndex-1) == userHandData.get(lastIndex-2)) //3번 연속 같은손
				return getCounterHand(gameInfo);
			else // 2번만 연속으로 같은손
			{
				LastHandBasedStrategy lastHandBasedStrategy= new LastHandBasedStrategy();
				return lastHandBasedStrategy.computeNextHand(gameInfo);
			}
		
		else 
			return HandType.valueOf(ThreadLocalRandom.current().nextInt(3)); //randomStrategy
	}

	private HandType getCounterHand(GameInfo gameInfo) {
		HandType lastUserHand = gameInfo.getLastUserHand();
		
		if(!gameInfo.isPlayingMookJiBa()) // 가위바위보
			return lastUserHand.winValueOf();
		else if(gameInfo.isUserAttack()) // 묵찌빠 && userAttack
			return lastUserHand.winValueOf();
		else
			return lastUserHand; // 묵찌빠 && computerAttack
	}
}
