package BikeShopApp;

import com.jfoenix.controls.*;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class cntrl_newRepairOrder implements Initializable {


    @FXML
    JFXDatePicker repairOrderDate, estimatedCompletionDate;

    @FXML
    JFXComboBox repairType, customerName;

    @FXML
    JFXTextArea orderComments;

    private Connection connect_db() {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setUser("gui");
        ds.setPassword("GUIAmberClad2020!");
        ds.setServerName("COT-CIS3365-14.cougarnet.uh.edu");
        ds.setPortNumber(1433);
        ds.setDatabaseName("HBC_FINAL");

        Connection con = null;

        try {
            con = ds.getConnection();
            con.setAutoCommit(false);
            System.out.println("Opened database successfully");
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        PopulateRepairOrderBoxes();
    }

    //populates repair type drop down
    @FXML private void PopulateRepairOrderBoxes() {
        Connection conn = this.connect_db();
        ObservableList<String> RepairType = FXCollections.observableArrayList();
        ObservableList<String> CustomerList = FXCollections.observableArrayList();

        try {
            String sql_repairTypes = "SELECT * from Repair_Type";
            ResultSet repairType_set;
            repairType_set = conn.createStatement().executeQuery(sql_repairTypes);
            while (repairType_set.next()) {
                RepairType.add(repairType_set.getString("repairTypeDesc"));
            }

            String sql_custNames = "SELECT " +
                    "CONCAT (Customer.customerFirstName, ' ', Customer.customerLastName) as Customer " +
                    "FROM Customer";
            ResultSet cust_set;
            cust_set = conn.createStatement().executeQuery(sql_custNames);
            while (cust_set.next()) {
                CustomerList.add(cust_set.getString("Customer"));
            }

            repairType.setItems(RepairType);
            customerName.setItems(CustomerList);
            repairType_set.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }

/*
    @FXML private void add_new_repair_order(){
        String roDate, estDate, comm;
        int roType, custName;
        Connection conn = this.connect_db();
        Statement ps_conn;


        roDate = repairOrderDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        estDate = repairOrderDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        roType = repairType.getSelectionModel().getSelectedIndex() + 1;
        custName = customerName.getSelectionModel().getSelectedIndex() + 1;
        comm = orderComments.getText();


        try {
*//*            ps_conn = conn.createStatement();
            String sql = "insert into Employee(employeeFirstName, employeeLastName, employeeEmail, employeePrimaryPhone, employeeSecondaryPhone,"
                    + "employeeAddress1, employeeAddress2, employeeCity, employeeZip, stateID, employeeStatusID, employeePositionID, employeeTypeID)"
                    + "VALUES('" + fname + "','" + lname + "','" + email + "','" + phone1 + "','" + phone2 + "','" + addy1 + "','" + addy2 + "','"
                    + city + "','" + zip + "','" + state + "','" + status + "','" + position + "','" + type +"')";
            ps_conn.executeUpdate(sql);
            ps_conn.close();
            conn.commit();
            conn.close();*//*
//        } catch (SQLException e) {
//            e.printStackTrace();}
    }*/
}
