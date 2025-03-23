

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
    
        while (current != null && !current.equals(destination)) {
            current = current.next;
            numStops++;
    
            // check if we have looped back to the starting point
            if (current.equals(this)) {
                return -1;
            }
        }
    
        if (current == null) {
            return -1;
        }
    
        return numStops;
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
