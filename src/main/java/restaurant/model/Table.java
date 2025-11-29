package restaurant.model;

public class Table {

    private final int number;
    private TableStatus status = TableStatus.AVAILABLE;

    public Table(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public TableStatus getStatus() {
        return status;
    }

    public void setStatus(TableStatus s) {
        this.status = s;
    }
}
