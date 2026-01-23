public class Task {
    private String name;
    private boolean isComplete;

    public Task(String name) {
        this.name = name;
        this.isComplete = false;
    }

    public void markAsComplete() {
        isComplete = true;
    }

    public void markAsIncomplete() {
        isComplete = false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", isComplete ? "X" : " ", name);
    }
}
