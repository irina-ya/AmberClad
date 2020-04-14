package BikeShopApp;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static BikeShopApp.custDirectoryController.customer_selected_id;

public class cntrl_editviewCustomer {

    @FXML
    JFXTextField firstNameTextField,lastNameTextField, cityTextField, phoneNumber1TextField, phoneNumber2TextField, emailTextField, address1TextField, address2TextField, CityTextField, zipTextField;
    @FXML JFXComboBox stateBox, statusBox;
    @FXML JFXButton btn_submit, btn_close;
    private int selected_id;
    @FXML
    private void initialize() {
        selected_id = customer_selected_id;
        populateComboBoxes();
        populateTextFields();
    }
    private Connection connect_db() {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setUser("gui");
        ds.setPassword("GUIAmberClad2020!");
        ds.setServerName("COT-CIS3365-14.cougarnet.uh.edu");
        ds.setPortNumber(1433);
        ds.setDatabaseName("HBC_FINAL");

        Connection con = null;

        try{
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

    private void populateComboBoxes() {
        Connection conn = this.connect_db();
        ObservableList<String> States = FXCollections.observableArrayList();
        ObservableList<String> CustStatus = FXCollections.observableArrayList();
        try {
            String sql_states = "SELECT * from State_Table";
            ResultSet state_set;
            state_set = conn.createStatement().executeQuery(sql_states);
            while(state_set.next()){
                States.add(state_set.getString("stateName"));
            }
            String sql_status = "Select * from Customer_Status";
            ResultSet status_set;
            status_set = conn.createStatement().executeQuery(sql_status);
            while(status_set.next()){
                CustStatus.add(status_set.getString("customerStatusDesc"));
            }

            stateBox.setItems(States);
            statusBox.setItems(CustStatus);
            state_set.close();
            state_set.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }

    @FXML private void populateTextFields() {
        Connection conn = this.connect_db();
        System.out.println(selected_id);
        try {
            String sql_main = "SELECT *  FROM Customer WHERE CustomerID=" + selected_id;
            ResultSet result_set;

            result_set = conn.createStatement().executeQuery(sql_main);
            result_set.next();
            firstNameTextField.setText(result_set.getString("customerFirstName"));
            lastNameTextField.setText(result_set.getString("customerLastName"));
            zipTextField.setText(result_set.getString("customerZip"));
            cityTextField.setText(result_set.getString("customerCity"));
            address1TextField.setText(result_set.getString("customerAddress1"));
            address2TextField.setText(result_set.getString("customerAddress2"));
            phoneNumber1TextField.setText(result_set.getString("customerPrimaryPhone"));
            phoneNumber2TextField.setText(result_set.getString("customerSecondaryPhone"));
            emailTextField.setText(result_set.getString("customerEmail"));
            int stateid = result_set.getInt("stateID");
            int custstatid = result_set.getInt("customerStatusID");
            result_set.close();
    //Pre-select the State
            String sql_state = "SELECT * FROM State_Table where stateID =" + stateid;
            ResultSet state_set;
            state_set = conn.createStatement().executeQuery(sql_state);
            state_set.next();
            stateBox.setValue(state_set.getString("stateName"));
            state_set.close();
    //Pre-select Customer status
            String sql_status = "select * from Customer_Status where customerStatusID =" + custstatid;
            ResultSet status_set;
            status_set = conn.createStatement().executeQuery(sql_status);
            status_set.next();
            statusBox.setValue(status_set.getString("customerStatusDesc"));

            //close connections
            state_set.close();
            status_set.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }

    }
    @FXML private void save_changes(){
        String fname, lname, email, phone1, phone2, addy1, addy2, city, zip;
        int state, status;
        Connection conn = this.connect_db();
        Statement ps_conn;

        fname = firstNameTextField.getText();
        lname = lastNameTextField.getText();
        email = emailTextField.getText();
        phone1 = phoneNumber1TextField.getText();
        phone2 = phoneNumber2TextField.getText();
        addy1 = address1TextField.getText();
        addy2 = address2TextField.getText();
        city = cityTextField.getText();
        zip = zipTextField.getText();
        state = stateBox.getSelectionModel().getSelectedIndex() + 1;
        status = statusBox.getSelectionModel().getSelectedIndex();
        try{
            ps_conn = conn.createStatement();
            String sql = "update Customer set customerFirstName ='" + fname + "',customerLastName ='" + lname + "',customerEmail ='" + email + "',customerPrimaryPhone='" + phone1
                    + "',customerSecondaryPhone='" + phone2 + "',customerAddress1='" + addy1 + "',customerAddress2='" + addy2 + "',customerCity ='" + city + "',customerZip='" + zip +
                    "',StateID='" + state + "',customerStatusID= '" + status + "'WHERE CustomerID=" + selected_id;
            ps_conn.executeUpdate(sql);
            conn.commit();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) btn_close.getScene().getWindow();
        stage.hide();
    }
    @FXML public void setBtn_close(){
        Stage stage = (Stage) btn_close.getScene().getWindow();
        stage.hide();
    }



}

