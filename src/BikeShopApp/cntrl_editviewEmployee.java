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

import static BikeShopApp.cntrl_EmployeeDirectory.employee_selected_id;


public class cntrl_editviewEmployee {
    private int selected_id;

    @FXML
    JFXTextField firstNameTextField,lastNameTextField, phoneNumber1TextField, phoneNumber2TextField, emailTextField, address1TextField, address2TextField, CityTextField, zipTextField;
    @FXML
    JFXComboBox stateBox, EmpTypeBox, EmpPositionBox, EmpStatusBox;
    @FXML
    JFXButton btn_close;

    @FXML
    private void initialize() {
        selected_id = employee_selected_id;
        PopulateEmpBoxes();
        PopulateEmpTextFields ();
    }
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
    @FXML private void PopulateEmpTextFields () {
            Connection conn = this.connect_db();

            try {
                String sql_main = "SELECT *  FROM Employee WHERE EmployeeID=" + selected_id;
                ResultSet result_set;

                result_set = conn.createStatement().executeQuery(sql_main);
                result_set.next();
                firstNameTextField.setText(result_set.getString("employeeFirstName"));
                lastNameTextField.setText(result_set.getString("employeeLastName"));
                zipTextField.setText(result_set.getString("employeeZip"));
                CityTextField.setText(result_set.getString("employeeCity"));
                address1TextField.setText(result_set.getString("employeeAddress1"));
                address2TextField.setText(result_set.getString("employeeAddress2"));
                phoneNumber1TextField.setText(result_set.getString("employeePrimaryPhone"));
                phoneNumber2TextField.setText(result_set.getString("employeeSecondaryPhone"));
                emailTextField.setText(result_set.getString("employeeEmail"));
                int stateid = result_set.getInt("stateID");
                int EmpStatusid = result_set.getInt("employeeStatusID");
                int EmpTypeid = result_set.getInt("employeeTypeID");
                int EmpPositionId = result_set.getInt("employeePositionID");
                result_set.close();

                //Pre-select the State
                String sql_state = "SELECT * FROM State_Table where stateID =" + stateid;
                ResultSet state_set;
                state_set = conn.createStatement().executeQuery(sql_state);
                state_set.next();
                stateBox.setValue(state_set.getString("stateName"));
                state_set.close();

                //Pre-select Status
                String sql_status = "select * from Employee_Status where employeeStatusID =" + EmpStatusid;
                ResultSet status_set;
                status_set = conn.createStatement().executeQuery(sql_status);
                status_set.next();
                EmpStatusBox.setValue(status_set.getString("employeeStatus"));
                status_set.close();

                //Pre-select Type
                String sql_type = "select * from Employee_Type where employeeTypeID =" + EmpTypeid;
                ResultSet type_set;
                type_set = conn.createStatement().executeQuery(sql_type);
                type_set.next();
                EmpTypeBox.setValue(type_set.getString("employeeType"));
                type_set.close();

                //Pre-select Position
                String sql_position = "select * from Employee_Position where employeePositionID =" + EmpPositionId;
                ResultSet pos_set;
                pos_set = conn.createStatement().executeQuery(sql_position);
                pos_set.next();
                EmpPositionBox.setValue(pos_set.getString("employeePositionName"));
                pos_set.close();

                conn.close();

            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error on Building Data");
            }

        }

    @FXML private void save_emp_changes(){
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
            String sql = "update Employee set employeeFirstName ='" + fname + "',employeeLastName ='" + lname + "',employeeEmail ='" + email + "',employeePrimaryPhone='" + phone1
                    + "',employeeSecondaryPhone ='"+ phone2+ "',employeeAddress1 ='" + addy1 +"',employeeAddress2 ='" + addy2 + "',employeeCity ='" + city + "',employeeZip ='"+ zip
                    + "',stateID ='" + state + "',employeeStatusID ='" + status + "',employeePositionID ='" + position + "',employeeTypeID ='" + type + "' where employeeID = " + selected_id;
            ps_conn.executeUpdate(sql);
            conn.commit();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();}
    }

    @FXML public void setBtn_close(){
        Stage stage = (Stage) btn_close.getScene().getWindow();
        stage.hide();
    }
}

