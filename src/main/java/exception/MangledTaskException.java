package exception;

public class MangledTaskException extends Exception{
    String rawTask;
    public MangledTaskException(String message, String rawTask) {
        super(message);
        this.rawTask = rawTask;
    }

    public String getRawTask() {
        return rawTask;
    }
}
