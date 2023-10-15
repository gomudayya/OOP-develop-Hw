import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

public class RemoveShapeCommand implements Command{
	Drawing drawing = new Drawing();
	
	Pane pane;
	Shape shape;
	
	
	public RemoveShapeCommand(Pane pane, Shape shape) {
		this.pane = pane;
		this.shape = shape;
	}

	public void execute() {
		drawing.removeShape(pane, shape);
	}

	public void undo() {
		drawing.addShape(pane, shape);
	}
}
