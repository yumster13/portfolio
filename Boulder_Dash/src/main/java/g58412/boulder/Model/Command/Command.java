package g58412.boulder.model.Command;

public interface Command {
    /**
     * Execute the command
     */
    void execute();

    /**
     * Unexecute the command
     */
    void unexecute();
}
