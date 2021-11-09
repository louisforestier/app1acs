import java.io.Serializable;
import java.util.Date;

public class Task implements Serializable {
    private static final long serialVersionUID = -4247493807540494301L;
    private final String description;
    private final Date date;

    public Task(final String description, final Date date) {
        this.description = description;
        this.date = date;
    }

    public Task(final String description) {
        this(description, null);
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }
}
