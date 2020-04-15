package BikeShopApp;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
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
import java.util.ResourceBundle;

public class cntrl_newCustomer implements Initializable {

    @FXML
    JFXTextField firstNameTextField,lastNameTextField, cityTextField, phoneNumber1TextField, phoneNumber2TextField, emailTextField, address1TextField, address2TextField, CityTextField, zipTextField;
    @FXML
    JFXComboBox stateBox, statusBox;
    @FXML
    JFXButton btn_submit, btn_close;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        populateComboBoxes();
        stateBox.setValue("Texas");
        cityTextField.setText("Houston");
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
    public void populateComboBoxes() {
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

    @FXML private void setBtn_submits(){
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
            String sql = "INSERT INTO Customer(customerFirstName, customerLastName, customerEmail, customerPrimaryPhone, customerSecondaryPhone, customerAddress1, customerAddress2, customerCity, customerZip, StateID, customerStatusID) " +
                    "VALUES('"+fname+"','"+lname+"','"+email+"','"+phone1+"','"+phone2+"','"+addy1+"','"+addy2+"','"+city+"','"+zip+"','"+state+"','"+status+"')";
            ps_conn.executeUpdate(sql);
            ps_conn.close();
            conn.commit();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
}
    @FXML public void setBtn_close(){
        Stage stage = (Stage) btn_close.getScene().getWindow();
        stage.hide();
    }
}
