public class NoClientException extends Exception{

    private static final long serialVersionUID = -4729752032684420048L;

    public NoClientException() {
        super("No client found with this id.");
    }
}
