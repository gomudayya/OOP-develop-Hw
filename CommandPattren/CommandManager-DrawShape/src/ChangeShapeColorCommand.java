import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class ChangeShapeColorCommand implements Command{
	Drawing drawing = new Drawing();
	Color prevColor;
	
	Shape shape;
	Color currColor;
	
	
	
	public ChangeShapeColorCommand(Shape shape, Color color) {
		this.shape = shape;
		this.currColor = color;
	}

	public void execute() {
		prevColor = (Color) shape.getFill();
		drawing.changeShapeColor(shape, currColor);
	}
	
	public void undo() {
		drawing.changeShapeColor(shape, prevColor);
	}
}
