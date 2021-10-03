package Ticketpack;

import Trainpack.Train;
import passengerpack.Passenger;

import java.util.Date;
import java.util.TreeMap;

public class Ticket {
    private int counter;
    private String pnr;
    private Date travelDate;
    Train train=null;
    TreeMap<Passenger,Integer> passengers=new TreeMap<Passenger,Integer>();

    public Ticket(Date travelDate, Train train) {
        this.travelDate = travelDate;
        this.train = train;
    }
}
