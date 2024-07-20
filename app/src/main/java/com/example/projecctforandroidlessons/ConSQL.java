package com.example.projecctforandroidlessons;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ConSQL {
    Connection connection;

    @SuppressLint("NewApi")
    public Connection conClass() {
        String ip = "10.0.2.2";
        String port = "1433";
        String db = "DBForAndroidApp";
        String username = "sa";
        String password = "bkmz1234";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Connection connection = null;
        String ConnectionURL;

        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionURL = "jdbc:jtds:sqlserver://" + ip + ":" + port + ";" + "databaseName=" + db + ";user=" + username + ";password=" + password + ";";
            connection = DriverManager.getConnection(ConnectionURL, username, password);

        } catch (SQLException se) {
            Log.e("ERROR", se.getMessage());
        } catch (ClassNotFoundException e) {
            Log.e("ERROR", e.getMessage());
        } catch (Exception e) {
            Log.e("ERROR", e.getMessage());
        }

        return connection;
    }

    public List<FlightInfo> getFlightInfoList() {
        List<FlightInfo> flightInfoList = new ArrayList<>();

        Connection connection = conClass();
        if (connection != null) {
            String sqlStatement = "SELECT [price], [timeStart], [timeEnd], [duration], [from], [to], [customerId], [routeId] FROM Booking";

            try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sqlStatement)) {
                while (resultSet.next()) {
                    String price = resultSet.getString("price");
                    String timeStart = resultSet.getString("timeStart");
                    String timeEnd = resultSet.getString("timeEnd");
                    String duration = resultSet.getString("duration");
                    String from = resultSet.getString("from");
                    String to = resultSet.getString("to");
                    int customerId = resultSet.getInt("customerId");
                    int routeId = resultSet.getInt("routeId");

                    FlightInfo flightInfo = new FlightInfo(price, timeStart, timeEnd, duration, from, to, customerId, routeId);
                    flightInfoList.add(flightInfo);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return flightInfoList;
    }
}