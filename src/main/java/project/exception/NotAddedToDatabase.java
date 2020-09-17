package project.exception;

public class NotAddedToDatabase extends RuntimeException {
    public NotAddedToDatabase(String message)
    {
        super(message);
    }
}
