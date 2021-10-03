package Ticketpack;

import Trainpack.Train;
import Trainpack.TrainOpImpl;
import passengerpack.Passenger;

import java.io.*;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class TicketOPImpl implements TicketOp{
    TrainOpImpl toi=new TrainOpImpl();
    private String sr;
    private StringBuilder fText=null;
    @Override
    public String generatePNR(int train_no,String str) {
        Train train=toi.getTrainDeatils(train_no);
        String[] word=str.split("/");
        String fstr="";
        for (String s:word)
        {
            fstr+=s;
        }

        Random rand=new Random();
        int count=(rand.nextInt(100)+100);
        String s3=Integer.toString(count);
        char ch=train.getSource().charAt(0);
        String s1=Character.toString(ch);
        char ch1=train.getDestination().charAt(0);
        String s2=Character.toString(ch1);
        String s=s1+s2+"_"+fstr+"_"+s3;
        sr=s;
        return (s);

    }

    @Override
    public double calcPassengerFare(int trainNo, int age, char gender) {
        Train train=toi.getTrainDeatils(trainNo);
        double price=0;
        if(age<=12)
        {
            price=0.5*train.getTicketPrice();
            return price;
        }
        else if(age>=60)
        {
            price=0.6*train.getTicketPrice();
            return price;
        }
        else if(gender=='F') {
            price = 0.75 * train.getTicketPrice();
            return price;
        }
        return price=train.getTicketPrice();
    }

    @Override
    public void addPassenger(String name, char gender) {

    }
    TreeMap<Passenger,Double>passenger=new TreeMap<Passenger, Double>();
    @Override
    public double calculateTotalTicketPrice(Passenger p,double price) {
        passenger.put(p,price);
        double value=0;
        for(Map.Entry<Passenger,Double> entry:passenger.entrySet())
        {
            value+=entry.getValue();

        }
        return value;
    }


    @Override
    public StringBuilder generateTicket(int train_no,String str,double price,Passenger p) {
        Train train=toi.getTrainDeatils(train_no);
        StringBuilder sb=new StringBuilder();
        sb.append("PNR\t\t  : "+generatePNR(train_no,str)+"\r\nTrain no\t  : "
                +train.getTrainNo()+"\r\nTrain Name        : "+train.getTrainName()+"\r\nFrom\t\t  : "+train.getSource()+"\r\nTo\t\t  : "
                +train.getDestination()+"\r\nTravel Date       : "+str+"\r\n\r\nPassengers : \r\nName\t\tAge\t\tGender\t\tFare");
        for(Map.Entry<Passenger,Double> entry:passenger.entrySet())
        {
            //String key=entry.getKey()
            sb.append(entry.getKey());
            sb.append(entry.getValue());

        }
        sb.append("\r\n\r\nTotal Price  : "+calculateTotalTicketPrice(p,price));
        fText=sb;
        return sb;
    }

    @Override
    public void writeTicket() {
        System.out.println("Your ticket has been booked successfully with pnr no.:"+sr);

        File file=new File("C:\\Users\\user109\\Desktop\\"+sr+".txt");
        try
                (
                        FileOutputStream output=new FileOutputStream(file);
                        BufferedOutputStream bs=new BufferedOutputStream(output);
                ) {
            bs.write(fText.toString().getBytes());
            System.out.println("you can download your ticket from desktop. Enjoy your Travelling");

        }
        catch (IOException e) {
            e.printStackTrace();
        }


    }
}
