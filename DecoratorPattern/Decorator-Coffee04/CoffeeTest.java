/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습 
 * @version 2021년도 2학기
 * @author 김상진
 * 2019136149 박건우 
 * @file CoffeeTest.java
 * 생성 메소드 추가 버전
 * 인자로 클래스 이름과 같은 문자열을 제공해야 함
 * 한글을 사용하고 싶으면 별도 매핑 테이블이 필요함
 */

class Dog{
}

public class CoffeeTest {
	public static void main(String[] args) {
		try {
			Beverage b1 = BeverageFactory.createCoffee("HouseBlend", "Mocha", "Whip", "Mocha");
			System.out.printf("%s: %,d원%n", b1.getDescription(), b1.cost());
			
			b1 = BeverageFactory.createCoffee("DarkRoast", "Milk", "Mocha");
			System.out.printf("%s: %,d원%n", b1.getDescription(), b1.cost());
			
			b1 = BeverageFactory.createCoffee("DarkRoast", "Milk", "Milk", "Milk", "Whip");
			System.out.printf("%s: %,d원%n", b1.getDescription(), b1.cost());
			
			
			/*Restriction Test*/
			
			//다크로스트에는 모카를 못 넣어요.
//			BeverageFactory.addCoffeeRestriction("Mocha", "DarkRoast");
//			Beverage b2 = BeverageFactory.createCoffee("DarkRoast", "Whip", "Whip", "Mocha");

			
			//모카를 3번 넘게 추가하면 안 돼요.
//			BeverageFactory.addAdditionRestriction("Mocha", 3);
//			Beverage b3 = BeverageFactory.createCoffee("HouseBlend", "Mocha", "Mocha", "Mocha", "Mocha");
			
			//하우스블렌드에는 휩을 못 넣어요. + 휩은 두 번 넘게 추가하면 안 돼요.
//			BeverageFactory.addCoffeeRestriction("Whip", "HouseBlend");
//			BeverageFactory.addAdditionRestriction("Whip", 2);
//			
//			Beverage b4 = BeverageFactory.createCoffee("HouseBlend", "Whip", "Whip");
//			Beverage b5 = BeverageFactory.createCoffee("DarkRoast", "Whip", "Whip", "Whip");
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
