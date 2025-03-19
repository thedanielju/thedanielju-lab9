

public class EndStation extends Station{

    public EndStation(String color, String name) {
        super(color, name);
    }

    //trip calc from stations needs .next to find destination
    //therefore provides "turn around" and find other direction
    public void makeEnd() {
        if (next != null && prev == null) {
            prev = next;
            //start of line, loop back to next station

        } else if (prev!= null && next == null) { 
            next = prev;
            //end of line, loop back to previous station
        }
    }

    public int tripLength(Station destination) {
        int numStops = 0;
        Station current = this;

        if (this.equals(destination)) {
            return 0;
        }

        if (!this.color.equals(destination.getColor())) {
            return -1;
        }

        while (current != destination) {
            current = current.next;
            numStops++;

            if (current.equals(destination)) {
                return numStops;
            }
        }
        return -1;
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
        return "ENDSTATION " + name + ": " + color + " line, in service: " + available + 
           ", previous station: " + prevStation + ", next station: " + nextStation;
    }
}
