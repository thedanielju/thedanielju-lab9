//adds functionality for transfer points between different lines
import java.util.ArrayList;
import java.util.List;

public class TransferStation extends Station{
    public ArrayList<Station> otherStations;

    public TransferStation(String color, String name) {
        super(color, name);
        this.otherStations = new ArrayList<>();
    }

    public boolean addTransferStation(Station transferStation) {
        for (Station station : otherStations) {
            if (station.equals(transferStation)) {
                return false;
            }
        }
        otherStations.add(transferStation);
        return true;
    }

    public void addTransferStationPrev(Station transferStation) {
        this.addTransferStation(transferStation);
        transferStation.next = this;
    }

    public void addTransferStationNext(Station transferStation) {
        this.addTransferStation(transferStation);
        transferStation.prev = this;    
    }

//String expected = "TRANSFERSTATION Museum: pink line, in service: true, previous station: none, next station: none\n\tTransfers: \n";

@Override
public int tripLength(Station destination) {
    //same station
    if (this.equals(destination)) {
        return 0;
    }
    
    //list of visited stations to avoid loop
    List<Station> visited = new ArrayList<>();
    visited.add(this);
    
    return tripLengthHelper(destination, visited);
}

@Override
public int tripLengthHelper(Station destination, List<Station> visited) {
    //destination immediately found
    if (this.equals(destination)) {
        return 0;
    }
    
    //direct path
    if (next != null && !visited.contains(next)) {
        visited.add(next);
        int nextLength = next.tripLengthHelper(destination, visited);
        if (nextLength >= 0) {
            return nextLength + 1;
        }
    }
    
    //transfer stations
    for (Station transfer : otherStations) {
        if (!visited.contains(transfer)) {
            visited.add(transfer);
            int transferLength = transfer.tripLengthHelper(destination, visited);
            if (transferLength >= 0) {
                return transferLength + 1;
            }
        }
    }
    
    //no path
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
    
    StringBuilder output = new StringBuilder();
    output.append("TRANSFERSTATION ").append(name).append(": ").append(color)
          .append(" line, in service: ").append(available)
          .append(", previous station: ").append(prevStation)
          .append(", next station: ").append(nextStation);
    
    output.append("\n\tTransfers: \n");
    
    for (Station station : otherStations) {
        output.append("\t").append(station.toString()).append("\n");
    }
    return output.toString();
    }
}