/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습 
 * @version 2022년도 2학기
 * @author 김상진
 * 2019136149 박건우 
 * @file Milk.java
 * 장식패턴에서 구체적인 장식자 클래스
 * 우유 첨가물
 */
public class Milk extends CondimentDecorator {
	public Milk(Beverage beverage){
		super(beverage);
		
		if (beverage.hasInOneMilk())
			throw new IllegalArgumentException("Milk 는 2개이상 장식할 수 없습니다.");		
	}
	@Override
	public String getDescription() {
		return beverage.getDescription()+", 우유";
	}
	@Override
	public int cost() {
		return beverage.cost()+500;
	}
	
}
