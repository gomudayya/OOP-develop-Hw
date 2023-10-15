import java.util.Stack;

public class CommandManager {
	private Stack<Command> undoStack = new Stack<>();
	private Stack<Command> redoStack = new Stack<>();
	public void execute(Command command) {
		command.execute();
	}
	
	public boolean undo() {
		if(!undoStack.isEmpty()) {
			Command command = undoStack.pop();
			command.undo();
			
			redoStack.add(command);
			return true;
		}
		
		return false;
	}
	
	public boolean redo() {
		if(!redoStack.isEmpty()) {
			Command command = redoStack.pop();
			command.execute();
			
			undoStack.add(command);
			return true;
		}
		
		return false;
	}
	
	public void addUndoCommand(Command command) {
		undoStack.add(command);
	}
	
	public void addRedoCommand(Command command) {
		redoStack.add(command);
	}
	
	public void clearRedoStack() {
		redoStack.clear();
	}
	
	public boolean isUndoStackEmpty() {
		return undoStack.isEmpty();
	}
	
	public boolean isRedoStackEmpty() {
		return redoStack.isEmpty();
	}
}
