package g58412.boulder.model.Command;

import java.util.Stack;

public class CommandManager {

    Stack<Command> undo = new Stack<Command>();
    Stack<Command> redo = new Stack<Command>();;


    public void doCommand(Command command){
        redo.clear();
        command.execute();
        undo.push(command);
    }
    public void undo(){
        if(!undo.isEmpty()){
            Command undoCom = undo.pop();
            undoCom.unexecute();
            redo.push(undoCom);
        }
    }

    public void redo(){
        if(!redo.isEmpty()) {
            Command redoCom = redo.pop();
            redoCom.execute();
            undo.push(redoCom);
        }
    }
}
