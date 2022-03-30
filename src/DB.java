import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.transform.Result;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Properties;
import java.util.concurrent.ThreadPoolExecutor;

public class DB {
    private static Connection con;
    private static PreparedStatement ps;
    private static ResultSet rs;

    private static String host;
    private static String port;
    private static String databaseName;
    private static String userName;
    private static String password;

    private DB() {
    }
    static {
        Properties props = new Properties();
        String fileName = "db.properties";
        InputStream input;
        try{
            input = new FileInputStream(fileName);
            props.load(input);
            host = props.getProperty("host");
            port = props.getProperty("port","1433");
            databaseName = props.getProperty("dbName");
            userName=props.getProperty("user", "SA");
            password=props.getProperty("pass");

        }catch(Exception e){
            System.err.println(e.getMessage());
        }
    }
    private static void connect(){
        try {
            con = DriverManager.getConnection("jdbc:sqlserver://"+host+":"+port+";databaseName="+databaseName,userName,password);
            System.out.println("connected to db");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

    }
    private static void disconnect(){
        try {
            con.close();
            System.out.println("disconnected from db");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static int availableCages(int week) {
        connect();
        try {
            PreparedStatement ps = con.prepareStatement("exec CalcAvailable "+ week);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        disconnect();
        return -1;
    }

    public static int occupiedCages(int week) {
        connect();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT COUNT(Week_no) FROM tblBooking WHERE Week_no = " + week);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        disconnect();
        return -1;
    }

    // Returns format:
    // ID|Customer_Name|Customer_Address|Customer_Phone
    //
    public static String getRegisteredCustomer(String number) {
        connect();
        try {
            PreparedStatement ps = con.prepareStatement("select * from tblCustomer where Customer_Phone = '"+number+"'");
            ResultSet rs = ps.executeQuery();
            ArrayList<String> col = new ArrayList<>();
            while (rs.next())
            {
                ArrayList<String> row = new ArrayList<>();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++)
                {
                    row.add(rs.getString(i));
                }
                col.add(String.join("|",row));
            }
            if(col.size() == 0)
                return "";
            return col.get(0);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    // Returns name of pet as String
    public static String getPetName(String custID) {
        connect();
        try {
            PreparedStatement ps = con.prepareStatement("select Pet_name from tblPet where Customer_ID = '"+custID+"'");
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getString(1);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void insertCustomer(String custName, String custAddr, String custPhone, String custEmail) {
        connect();
        try {
            PreparedStatement ps = con.prepareStatement("exec insert_customer '"+custName+"', '"+custAddr+"', '"+custPhone+"', '"+custEmail+"'");
            ps.execute();
            return;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        disconnect();
    }

    public static int saveBooking (int week, int petID, int shelterID) {
        connect();
        try {
            PreparedStatement ps = con.prepareStatement("exec insert_booking "+ week +", 1");
            ResultSet rs = ps.executeQuery();
            rs.next();
            return Integer.parseInt(rs.getString(1));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        disconnect();
        return -1;
    }

    public static ObservableList<String> getAllBookings() {
        connect();
        ObservableList<String> observableList = null;
        try {
            String sql = "SELECT * from tblBooking";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // Init Arraylist
            ArrayList<String> bookings = new ArrayList<>();

            while (rs.next()) {
                bookings.add(rs.getString("Booking_ID"));
            }

            // Now add observability by wrapping it with ObservableList.
            observableList = FXCollections.observableList(bookings);

            rs.close();
            ps.close();
            return observableList;

        } catch (Exception e) {
            e.printStackTrace();
        }
        disconnect();
        return observableList;
    }
}
