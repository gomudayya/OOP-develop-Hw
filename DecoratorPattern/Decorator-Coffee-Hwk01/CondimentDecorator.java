import java.util.HashMap;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습 
 * @version 2022년도 2학기
 * @author 김상진
 * 2019136149 박건우 
 * @file CondimentDecorator.java
 * 장식패턴에서 장식자 추상 클래스
 */
public abstract class CondimentDecorator extends Beverage {
	protected Beverage beverage;
	protected CondimentDecorator(Beverage beverage){
		this.beverage = beverage;
	}
	
	@Override
	public abstract String getDescription();
	
	public boolean hasInOneMilk() {
		if(this.getClass() == Milk.class)
			return true;

		//beverage의 장식이 다 벗겨지면 Beverage클래스의 hasInOneMilk()와 바인딩
		return beverage.hasInOneMilk();  
	}
	
	public Beverage removeCondiment(){
		return beverage;
	}
	
	public HashMap<String, Integer> getComposition() {
		HashMap<String, Integer> compositionMap = new HashMap<>();
		
		// 제일 껍데기 데코레이터를 맵에 추가
		compositionMap.put(this.getClass().getName(), 1);		
		
		Beverage tempBeverage = beverage;
		while ( (tempBeverage instanceof CondimentDecorator) ) {
			compositionMap.merge(tempBeverage.getClass().getName(), 1, Integer::sum);
			tempBeverage = tempBeverage.removeCondiment();
		}
		
		// 장식을 다 벗긴후, 장식 대상(DarkRoast , HouseBlend .. 등등)  추가
		compositionMap.put(tempBeverage.getClass().getName(), 1);
		
		return compositionMap;
	}
}
