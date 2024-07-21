package com.example.projecctforandroidlessons;


import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class RegisterViewModel extends AndroidViewModel {

    private final MutableLiveData<String> status = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isSuccess = new MutableLiveData<>();

    public RegisterViewModel(Application application) {
        super(application);
    }

    public LiveData<String> getStatus() {
        return status;
    }

    public LiveData<Boolean> getIsSuccess() {
        return isSuccess;
    }

    public void registerUser(String name, String email, String password) {
        new Thread(() -> {
            String result = "";
            boolean success = false;
            Connection con = null;
            Statement stat = null;
            try {
                con = ConnectionClass.connectionClass(ConnectionClass.un, ConnectionClass.pass, ConnectionClass.db, ConnectionClass.ip);
                if (con == null) {
                    result = "Check Your Internet Connection";
                } else {
                    String sql = "INSERT INTO register (name, email, password) VALUES ('" + name + "','" + email + "','" + password + "')";
                    stat = con.createStatement();
                    stat.executeUpdate(sql);
                    result = "Register success";
                    success = true;
                }
            } catch (Exception e) {
                result = e.getMessage();
                success = false;
            } finally {
                try {
                    if (stat != null) stat.close();
                    if (con != null) con.close();
                } catch (Exception e) {
                    // Ignore
                }
            }
            status.postValue(result);
            isSuccess.postValue(success);
        }).start();
    }

    public static class ConnectionClass {
        public static String un = "sa";
        public static String pass = "bkmz1234";
        public static String db = "DBExample";
        public static String ip = "10.0.2.2";

        public static Connection connectionClass(String user, String password, String database, String server) {
            Connection connection = null;
            String connectionURL;
            try {
                Class.forName("net.sourceforge.jtds.jdbc.Driver");
                connectionURL = "jdbc:jtds:sqlserver://" + server + "/" + database + ";user=" + user + ";password=" + password + ";";
                connection = DriverManager.getConnection(connectionURL);
            } catch (Exception e) {
                Log.e("SQL Connection Error: ", e.getMessage());
            }
            return connection;
        }
    }
}