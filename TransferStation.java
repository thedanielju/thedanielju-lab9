//adds functionality for transfer points between different lines

public class TransferStation extends Station{
    
    public TransferStation(String color, String name) {
        super(color, name);
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
        return "TRANSFER STATION " + name + ": " + color + " line, in service: " + available + 
           ", previous station: " + prevStation + ", next station: " + nextStation;
    }
    
}
