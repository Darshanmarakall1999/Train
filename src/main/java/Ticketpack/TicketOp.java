package Ticketpack;

import passengerpack.Passenger;

public interface TicketOp {
    String generatePNR(int trainN0,String str);
    double calcPassengerFare(int trainNo,int age,char gender);
    void addPassenger(String name,char gender);
    double calculateTotalTicketPrice(Passenger p,double price);
    StringBuilder generateTicket(int train_no,String str,double price,Passenger p);
    void writeTicket();

}
