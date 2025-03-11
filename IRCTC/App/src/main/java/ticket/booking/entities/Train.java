package ticket.booking.entities;

import java.util.List;
import java.util.Map;

public class Train {
    private final String trainId;
    private final String trainNo;
    private final List<List<Integer>> seats;
    private final Map<String, String> stationTimes;
    private final List<String> stations;

    // Constructor
    public Train(String trainId, String trainNo, List<List<Integer>> seats, Map<String, String> stationTimes, List<String> stations) {
        this.trainId = trainId;
        this.trainNo = trainNo;
        this.seats = seats;
        this.stationTimes = stationTimes;
        this.stations = stations;
    }

    // Getters
    public String getTrainId() {
        return trainId;
    }

    public String getTrainNo() {
        return trainNo;
    }

    public List<List<Integer>> getSeats() {
        return seats;
    }

    public Map<String, String> getStationTimes() {
        return stationTimes;
    }

    public List<String> getStations() {
        return stations;
    }
}
