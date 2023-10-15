import java.util.concurrent.ThreadLocalRandom;

/**
 * 2019136149 박건우
 */

public class AsteroidPolygonFactory extends AsteroidFactory{

	@Override
	protected Double[] createPoints(Location centerLoc, int radius) {
		
		//정점 좌표의 수 계산 ( 10개 + random(0~5) )
		int coordinateNum = 10;
		int randomNum =  + ThreadLocalRandom.current().nextInt(6);
		coordinateNum += randomNum;
		
		Double[] points = new Double[coordinateNum*2];
		double angleStep = 360.0 / coordinateNum;
		
		//좌표 값 대입
		for (int i = 0; i<coordinateNum ; i++) {
			int polygonRadius = radius + ThreadLocalRandom.current().nextInt(radius);
			double angle = ThreadLocalRandom.current().nextDouble( i*angleStep, (i+1)*angleStep );
			points[2*i] =  Double.valueOf(centerLoc.x()+Math.cos(Math.toRadians(angle))*polygonRadius);
			points[2*i+1] =  Double.valueOf(centerLoc.y()+Math.sin(Math.toRadians(angle))*polygonRadius);
		}
		
		return points;
	}

}
