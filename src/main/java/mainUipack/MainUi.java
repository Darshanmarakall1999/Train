package mainUipack;

import Ticketpack.TicketOPImpl;
import Trainpack.TrainOpImpl;
import Trainpack.Train;
import passengerpack.Passenger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class MainUi {
    public static void main(String[] args) {
        TrainOpImpl toi = new TrainOpImpl();
        TicketOPImpl tc = new TicketOPImpl();
        DateComparison dc = new DateComparison();
        Passenger p = null;

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("1. Book Ticket ");
            System.out.println("2. Exit");
            System.out.println("Enter your choice : ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1: {
                    System.out.println("Emnter the Train number:");
                    int train_no = sc.nextInt();
                    if (train_no >= 1001 && train_no <= 1006) {
                        System.out.println("Enter the Travel Date:");
                        Date date = null;
                        System.out.println("enter date in yyyy/MM/dd format: ");
                        String d = sc.next();
                        try {
                            date = new SimpleDateFormat("yyyy/MM/dd").parse(d);

                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        if (dc.dateCom(date)) {
                            System.out.println("Enter the number of passengers:");
                            int pNo = sc.nextInt();
                            double FarePrice = 0;
                            double TotalPrice = 0;
                            while (pNo > 0) {
                                System.out.println("enter the passenger name:");
                                String pNmae = sc.next();
                                System.out.println("enter passenger age:");
                                int age = sc.nextInt();
                                System.out.println("enter the gender:");
                                char gender = sc.next().charAt(0);
                                Train train = toi.getTrainDeatils(train_no);
                                p = new Passenger(pNmae, age, gender);
                                FarePrice = tc.calcPassengerFare(train_no, age, gender);
                                TotalPrice = tc.calculateTotalTicketPrice(p, FarePrice);
                                pNo--;

                            }
                            tc.generateTicket(train_no,d, FarePrice, p);
                        } else {
                            System.out.println("Date must me after your journey date. Your journey ends here");

                        }
                    } else {
                        System.out.println("Train with given no doesn't exist\n");
                    }
                    System.out.println(" \n\nIf you want to add any more passengers in ticket press 1 or press 2 for exit\n");
                    break;
                }

                case 2: {
                    sc.close();
                    tc.writeTicket();
                    System.out.println("Thank you for visiting......Visit again!!!!!!!!!!");
                    System.exit(0);
                }

            }
        }
    }
}
