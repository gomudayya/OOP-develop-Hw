import java.util.concurrent.ThreadLocalRandom;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2022년도 2학기
 * @author 김상진 
 * 2019136149 박건우 
 * @file RandomStrategy.java
 * 전략패턴: 구체적인 전략 클래스
 * 낼 손으로 랜덤으로 결정
 */
public class RandomStrategy implements PlayingStrategy {
	@Override
	public HandType computeNextHand(GameInfo gameInfo) {
		return HandType.valueOf(ThreadLocalRandom.current().nextInt(3));
	}
}
