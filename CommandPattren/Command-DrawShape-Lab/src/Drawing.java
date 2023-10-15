import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Drawing extends DrawShapeApp{	
	private static final int RADIUS = 40;
	
	public Shape drawSquare(Pane pane, double x, double y) {
		Shape shape = new Rectangle(x-RADIUS, y-RADIUS, RADIUS*2, RADIUS*2);
		shape.setStroke(Color.BLACK);
		shape.setFill(null);
		shape.setStrokeWidth(5d);
		addShape(pane, shape);
		return shape;
	}
	
	public Shape drawCircle(Pane pane, double x, double y) {
		Shape shape = new Circle(x,y, RADIUS);
		shape.setStroke(Color.BLACK);
		shape.setFill(null);
		shape.setStrokeWidth(5d);
		addShape(pane, shape);
		return shape;
	}
	
	public Shape drawTriangle(Pane pane, double x, double y) {
		Shape shape = null;
		Polygon triangle = new Polygon();
		final double radian = Math.PI / 180F;
		triangle.getPoints().addAll(new Double[] {
			x+RADIUS*Math.cos(30*radian),
			y+RADIUS*Math.sin(30*radian),
			x+RADIUS*Math.cos(150*radian),
			y+RADIUS*Math.sin(150*radian),
			x+RADIUS*Math.cos(270*radian),
			y+RADIUS*Math.sin(270*radian)
		});
		shape = triangle;
		shape.setStroke(Color.BLACK);
		shape.setFill(null);
		shape.setStrokeWidth(5d);
		addShape(pane, shape);
		return shape;
	}
	
	public Shape removeShape(Pane pane, Shape shape) {
		pane.getChildren().remove(shape);
		return shape;
	}
	
	public void addShape(Pane pane, Shape shape) {
		pane.getChildren().add(shape);
	}
	
	public void changeShapeColor(Shape shape, Color color) {
		shape.setFill(color);
	}
}
