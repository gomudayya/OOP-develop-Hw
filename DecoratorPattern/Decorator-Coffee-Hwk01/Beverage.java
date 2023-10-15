import java.util.HashMap;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습 
 * @version 2022년도 2학기
 * @author 김상진
 * 2019136149 박건우 
 * @file Beverage.java
 * 장식패턴에서 장식대상 추상클래스
 * 기본 예제
 */
public abstract class Beverage{
	private String description = "이름없는 음료";
	// 이 메소드의 문제점???
	public void setDescription(String description){
		this.description = description;
	}
	public String getDescription(){
		return description;
	}
	public abstract int cost();
	
	
	public boolean hasInOneMilk() {
		return false;
	}
	
	public Beverage removeCondiment(){
		return this;
	}
	
	//구성요소를 맵으로 얻어오는 메소드
	//Ex : {Whip=2, HouseBlend=1, Mocha=3, Milk=1}
	public HashMap<String, Integer> getComposition() {
		HashMap<String, Integer> compositionMap = new HashMap<>();
		
		compositionMap.put(this.getClass().getName(), 1);
		return compositionMap;
	}
	
	//구성요소가 같으면 true 리턴 
	public boolean equals(Beverage beverage) {
		return this.getComposition().equals( beverage.getComposition() );
	}
}
