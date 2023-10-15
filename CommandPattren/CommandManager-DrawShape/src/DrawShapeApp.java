

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습 
 * @version 2022년도 2학기
 * @author 김상진
 * Lab 06. 명령 패턴	
 * 도형 그리기, 색 채우기, 도형 삭제 기능
 * 2019136149 박건우
 */

public class DrawShapeApp extends Application {
	private static final int HEIGHT = 500;
	private static final int WIDTH = 500;
	private static final int RADIUS = 40;
	
	private RadioButton drawButton = new RadioButton("추가");
	private RadioButton selectButton = new RadioButton("선택");
	private Button undoButton = new Button("취소");
	private Button redoButton = new Button("재실행");
	
	private ComboBox<String> shapeChoice = new ComboBox<>();
	private ShapeType currentShape = ShapeType.SQUARE;
	private Shape selectedShape = null;
	
	private ContextMenu popupMenu = new ContextMenu();
	
	private Pane centerPane = new Pane();
	// CommandManager 유지 
	private CommandManager CM = new CommandManager();
	
	private void drawShape(double x, double y) {
		Command command = null;
		switch(currentShape) {
		case SQUARE:
			command = new DrawSquareCommand(centerPane, x, y); 
			command.execute();
			break;
		case CIRCLE:
			command = new DrawCircleCommand(centerPane, x, y);
			command.execute();
			break;
		case TRIANGLE:
			command = new DrawTriangleCommand(centerPane, x, y);
			command.execute();
		}
		
		CM.addUndoCommand(command);
		undoButton.setDisable(false);
		
		CM.clearRedoStack();
		redoButton.setDisable(true);
	}
	
	private void changeColor() {
		ColorPicker picker = new ColorPicker();
		picker.setLayoutX(selectedShape.getBoundsInLocal().getCenterX());
		picker.setLayoutY(selectedShape.getBoundsInLocal().getCenterY());
		centerPane.getChildren().add(picker);
		picker.setOnAction(e->{
			Command command = new ChangeShapeColorCommand(selectedShape, picker.getValue());
			command.execute();
			centerPane.getChildren().remove(picker);
			
			CM.addUndoCommand(command);
			undoButton.setDisable(false);
			
			CM.clearRedoStack();
			redoButton.setDisable(true);
		});
	}
	
	private void deleteShape() {
		Command command = new RemoveShapeCommand(centerPane, selectedShape);
		command.execute();
		
		CM.addUndoCommand(command);
		undoButton.setDisable(false);
		
		CM.clearRedoStack();
		redoButton.setDisable(true);
	}
	
	private void selectShape(double x, double y, double screenX, double screenY) {
		for(int i=centerPane.getChildren().size()-1; i>=0; i--) {
			Shape shape = (Shape) centerPane.getChildren().get(i);
			if(shape.getBoundsInLocal().contains(x, y)) {
				selectedShape = shape;
				popupMenu.show(centerPane, screenX, screenY);
				break;
			}
		}
	}
	
	private void mouseHandle(MouseEvent mouseEvent) {
		double x = mouseEvent.getX();
		double y = mouseEvent.getY();
		if(x<RADIUS) x = RADIUS;
		else if(x+RADIUS>WIDTH) x = WIDTH-RADIUS;
		if(y<RADIUS) y = RADIUS;
		else if(y+RADIUS>HEIGHT) y = HEIGHT-RADIUS;
		if(drawButton.isSelected()) drawShape(x, y);
		else selectShape(x, y, mouseEvent.getScreenX(), mouseEvent.getScreenY());
	}
	
	private HBox constructControlPane() {
		String[] shapeList = {"Square", "Circle", "Triangle"};
		shapeChoice.getItems().addAll(shapeList);
		shapeChoice.getSelectionModel().selectFirst();
		shapeChoice.setOnAction(e->
			currentShape = ShapeType.valueOf(shapeChoice.getSelectionModel().getSelectedItem().toUpperCase())
		);
	
		ToggleGroup actionGroup = new ToggleGroup();
		actionGroup.getToggles().addAll(drawButton, selectButton);
		drawButton.setSelected(true);
		Region spacer = new Region();
		HBox.setHgrow(spacer, Priority.ALWAYS);
		
		undoButton.setDisable(true);
		redoButton.setDisable(true);
		undoButton.setOnAction(e->undo());
		redoButton.setOnAction(e->redo());
		
		HBox controlPane = new HBox();
		controlPane.setPadding(new Insets(10));
		controlPane.setSpacing(10);
		controlPane.getChildren().addAll(shapeChoice, drawButton, selectButton, spacer, undoButton, redoButton);
		return controlPane;
	}
	

	private void undo() {
		CM.undo();
		if (CM.isUndoStackEmpty()) undoButton.setDisable(true);
		if (redoButton.isDisable()) redoButton.setDisable(false);
	}

	private void redo() {
		CM.redo();
		if (CM.isRedoStackEmpty()) redoButton.setDisable(true);
		if (undoButton.isDisable()) undoButton.setDisable(false);
	}

	private void constructPopupMenu() {
		MenuItem fillColorItem = new MenuItem("채우기 색 변경");
		fillColorItem.setOnAction(e->changeColor());
		MenuItem deleteItem = new MenuItem("삭제");
		deleteItem.setOnAction(e->deleteShape());
		popupMenu.getItems().addAll(fillColorItem, deleteItem);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane mainPane = new BorderPane();
		
		centerPane.setBackground(
			new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		centerPane.setMinWidth(500d);
		centerPane.setMinHeight(500d);
		centerPane.setOnMouseClicked(e->mouseHandle(e));
		
		mainPane.setCenter(centerPane);
		mainPane.setTop(constructControlPane());
		primaryStage.setTitle("도형 그리기");
		primaryStage.setScene(new Scene(mainPane));
		primaryStage.setResizable(false);
		primaryStage.show();
		
		constructPopupMenu();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}