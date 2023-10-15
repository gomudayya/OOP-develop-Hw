/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습 
 * @version 2022년도 2학기
 * @author 김상진
 * 2019136149 박건우 
 * @file CoffeeTest.java
 * 테스트 프로그램
 */
public class CoffeeTest {
	public static void main(String[] args) {
		Beverage b1 = new HouseBlend();
		b1 = new Mocha(b1);  b1 = new Whip(b1);  b1 = new Mocha(b1);
		System.out.printf("%s: %,d원%n", b1.getDescription(), b1.cost());
		
		b1 = new DarkRoast();  b1 = new Mocha(b1);  b1 = new Milk(b1);
		System.out.printf("%s: %,d원%n", b1.getDescription(), b1.cost());
		
		//이중우유 test
		Beverage b2 = new HouseBlend();
//		b2 = new Milk(b2);  b2 = new Mocha(b2);  b2 = new Milk(b2); // 생성 불가
		
		
		//equals test1 : 데코레이팅 없는애들
		Beverage b3 = new HouseBlend();
		Beverage b4 = new HouseBlend();
		
		System.out.println(b3.equals(b4)); // true return
		Beverage b5 = new DarkRoast();
		System.out.println(b3.equals(b5)); // false return

		//equals test2 : 데코레이팅 있는애들 
		Beverage b6 = new HouseBlend();
		b6 = new Mocha(b6);
		b6 = new Mocha(b6);
		b6 = new Whip(b6);
		b6 = new Milk(b6);
		b6 = new Whip(b6);
		b6 = new Mocha(b6);
		
		Beverage b7 = new HouseBlend();
		b7 = new Mocha(b7);
		b7 = new Whip(b7);
		b7 = new Whip(b7);
		b7 = new Mocha(b7);
		b7 = new Milk(b7);
		b7 = new Mocha(b7);
		System.out.println(b6.getComposition());
		System.out.println(b7.getComposition());
		System.out.println(b6.equals(b7)); // true return
	}
}
