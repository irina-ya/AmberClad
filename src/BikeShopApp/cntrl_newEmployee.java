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

public class cntrl_newEmployee implements Initializable {
    @FXML
    JFXTextField firstNameTextField,lastNameTextField, phoneNumber1TextField, phoneNumber2TextField, emailTextField, address1TextField, address2TextField, CityTextField, zipTextField;
    @FXML
    JFXComboBox stateBox, EmpTypeBox, EmpPositionBox, EmpStatusBox;
    @FXML
    JFXButton btn_close;

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
        PopulateEmpBoxes();
        stateBox.setValue("Texas");
        CityTextField.setText("Houston");
    }

    @FXML private void PopulateEmpBoxes() {
        Connection conn = this.connect_db();
        ObservableList<String> States = FXCollections.observableArrayList();
        ObservableList<String> EmpTypes = FXCollections.observableArrayList();
        ObservableList<String> EmpPositions = FXCollections.observableArrayList();
        ObservableList<String> EmpStatus = FXCollections.observableArrayList();
        try {
            String sql_states = "SELECT * from State_Table";
            ResultSet state_set;
            state_set = conn.createStatement().executeQuery(sql_states);
            while (state_set.next()) {
                States.add(state_set.getString("stateName"));
            }

            String sql_status = "Select * from Employee_Status";
            ResultSet status_set;
            status_set = conn.createStatement().executeQuery(sql_status);
            while (status_set.next()) {
                EmpStatus.add(status_set.getString("employeeStatus"));
            }

            String sql_type = "Select * from Employee_Type ";
            ResultSet type_set;
            type_set = conn.createStatement().executeQuery(sql_type);
            while (type_set.next()){
                EmpTypes.add(type_set.getString("employeeType"));
            }

            String sql_position = "Select * from Employee_Position ";
            ResultSet posset;
            posset = conn.createStatement().executeQuery(sql_position);
            while (posset.next()){
                EmpPositions.add(posset.getString("employeePositionName"));
            }

            stateBox.setItems(States);
            EmpTypeBox.setItems(EmpTypes);
            EmpPositionBox.setItems(EmpPositions);
            EmpStatusBox.setItems(EmpStatus);

            status_set.close();
            state_set.close();
            type_set.close();
            posset.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }

    @FXML public void setBtn_close(){
        Stage stage = (Stage) btn_close.getScene().getWindow();
        stage.hide();
    }

    @FXML private void add_new_employee(){
        String fname, lname, email, phone1, phone2, addy1, addy2, city, zip;
        int state, status, position, type;
        Connection conn = this.connect_db();
        Statement ps_conn;

        fname = firstNameTextField.getText();
        lname = lastNameTextField.getText();
        email = emailTextField.getText();
        phone1 = phoneNumber1TextField.getText();
        phone2 = phoneNumber2TextField.getText();
        addy1 = address1TextField.getText();
        addy2 = address2TextField.getText();
        city = CityTextField.getText();
        zip = zipTextField.getText();
        state = stateBox.getSelectionModel().getSelectedIndex() + 1;
        status = EmpStatusBox.getSelectionModel().getSelectedIndex() + 1;
        position = EmpPositionBox.getSelectionModel().getSelectedIndex() + 1;
        type = EmpTypeBox.getSelectionModel().getSelectedIndex() + 1;

        try {
            ps_conn = conn.createStatement();
            String sql = "insert into Employee(employeeFirstName, employeeLastName, employeeEmail, employeePrimaryPhone, employeeSecondaryPhone,"
                    + "employeeAddress1, employeeAddress2, employeeCity, employeeZip, stateID, employeeStatusID, employeePositionID, employeeTypeID)"
                    + "VALUES('" + fname + "','" + lname + "','" + email + "','" + phone1 + "','" + phone2 + "','" + addy1 + "','" + addy2 + "','"
                    + city + "','" + zip + "','" + state + "','" + status + "','" + position + "','" + type +"')";
            ps_conn.executeUpdate(sql);
            ps_conn.close();
            conn.commit();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();}
    }
}
