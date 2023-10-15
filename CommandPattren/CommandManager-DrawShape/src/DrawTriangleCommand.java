import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

public class DrawTriangleCommand implements Command{
	Drawing drawing = new Drawing();
	Shape shape;
	
	Pane pane;
	double pointX;
	double pointY;
	
	public DrawTriangleCommand(Pane pane, double pointX, double pointY) {
		this.pane = pane;
		this.pointX = pointX;
		this.pointY = pointY;
	}

	public void execute() {
		if ( shape == null) {
			shape = drawing.drawTriangle(pane, pointX, pointY);
			return;
		}
		drawing.addShape(pane, shape);
	}
	
	public void undo() {
		drawing.removeShape(pane, shape);
	}

}
