package Trainpack;

import Connectionpack.ConnectionDatabse;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TrainOpImpl implements TrainOp {
    private static Connection con=null;


    @Override
    public Train getTrainDeatils(int trainNo) {
        con= ConnectionDatabse.getconnection();
        Train train=null;
        try {
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery("select *from trains where TRAIN_NO="+trainNo);
            while(rs.next())
            {
                train=new Train(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5));

            }
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println("train number not found");
        }

        return train;
    }
}
