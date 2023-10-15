/**
 * 2019136149 박건우
 */

import java.util.concurrent.ThreadLocalRandom;

public class AsteroidRectangleFactory extends AsteroidFactory{

	@Override
	protected Double[] createPoints(Location centerLoc, int radius) {
	
		Double[] points = new Double[8];
		int width = radius;
		int height = radius;
		if(ThreadLocalRandom.current().nextBoolean()) 
			width += ThreadLocalRandom.current().nextInt(radius);
		if(ThreadLocalRandom.current().nextBoolean()) 
			height += ThreadLocalRandom.current().nextInt(radius);
		// 왼쪽 아래  
		points[0] = Double.valueOf(centerLoc.x()-width);
		points[1] = Double.valueOf(centerLoc.y()-height);
		
		// 오른쪽 아래  
		points[2] = Double.valueOf(centerLoc.x()+width);
		points[3] = Double.valueOf(centerLoc.y()-height);
		
		// 오른쪽 위 
		points[4] = Double.valueOf(centerLoc.x()+width);
		points[5] = Double.valueOf(centerLoc.y()+height);
		
		// 왼쪽 위
		points[6] = Double.valueOf(centerLoc.x()-width);
		points[7] = Double.valueOf(centerLoc.y()+height);
		return points;
		
	}	
}
