public class Station {
    protected String color;
    protected String name;
    protected Station next;
    protected Station prev;
    protected boolean available;

    public Station(String color, String name) {
        this.color = color;
        this.name = name;
        this.available = true;
    }

    public void addNext(Station next) {
        this.next = next;
        next.prev = this;
    }

    public void addPrev(Station prev) {
        this.prev = prev;
        prev.next = this;
    }

    public void connect(Station other) {
        this.addNext(other);
    }

    public boolean isAvailable() {
        return available;
    }

    public boolean switchAvailable() {
        this.available = !this.available;
        return this.available;
    }

    @Override
    public String toString() {
        String prevStation;
        String nextStation;

        if (prev == null) {
            prevStation = "none";
        } else {
            prevStation = prev.getName();
        }
        if (next == null) {
            nextStation = "none";
        } else {
            nextStation = next.getName();
        }
        return "STATION " + name + ": " + color + " line, in service: " + available + 
           ", previous station: " + prevStation + ", next station: " + nextStation;
    }

    @Override
    public boolean equals(Object current) {
        if (current instanceof Station) {
            Station other = (Station) current;
            return this.name.equals(other.name) && this.color.equals(other.color);
        }
        return false;
    }

    ///getter methods, debugging

    public String getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public String getNext() {
        return next.getName();
    }

    public String getPrev() {
        return prev.getName();
    }

}
