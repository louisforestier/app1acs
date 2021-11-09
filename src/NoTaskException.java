public class NoTaskException extends Exception{

    private static final long serialVersionUID = 5361754320149343662L;

    public NoTaskException() {
        super("No task found with this id.");
    }
}
