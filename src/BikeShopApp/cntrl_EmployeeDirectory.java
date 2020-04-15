package BikeShopApp;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class cntrl_EmployeeDirectory implements Initializable {
    @FXML JFXTextField filter_eFirstName, filter_eLastName;
    @FXML JFXButton clearBtn, vieweditBtn, searchBtn;
    @FXML public TableColumn col_1,col_2,col_3,col_4, col_5;
    @FXML TableView employee_list;

    public static int employee_selected_id;

    private ObservableList<TableModel_Employee> employee_data;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        col_1.setCellValueFactory(new PropertyValueFactory<TableModel_Employee,String>("Employee_ID"));
        col_2.setCellValueFactory(new PropertyValueFactory<TableModel_Employee,String>("Employee_fName"));
        col_3.setCellValueFactory(new PropertyValueFactory<TableModel_Employee,String>("Employee_lName"));
        col_4.setCellValueFactory(new PropertyValueFactory<TableModel_Employee,String>("Employee_Type"));
        col_5.setCellValueFactory(new PropertyValueFactory<TableModel_Employee,String>("Employee_Status"));

        populateEmpDataTable();
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


    private void populateEmpDataTable(){
        Connection conn = this.connect_db();
        employee_data = FXCollections.observableArrayList();

        String sql_main = "SELECT employeeID, employeeFirstName, EmployeeLastName, Employee_Status.employeeStatus, Employee_Type.employeeType from Employee"
                + " join Employee_Status on Employee_Status.employeeStatusID = Employee.employeeStatusID"
                +" join Employee_Type on Employee_Type.employeeTypeID = Employee.employeeTypeID";
        try {
            PreparedStatement ps = conn.prepareStatement(sql_main);
            ResultSet result_set = ps.executeQuery();
            while (result_set.next()){
                employee_data.add(new TableModel_Employee(result_set.getInt(1), result_set.getString(2), result_set.getString(3),
                        result_set.getString(4), result_set.getString(5)));
            }
            employee_list.setItems(employee_data);
            result_set.close();
            conn.close();
        } catch (SQLException tableQueryException) {
            System.err.println(tableQueryException.toString());
        }
    }
    private void fetch_RowID(){
        int selected_index;
        selected_index = employee_list.getSelectionModel().getSelectedIndex();
        TableModel_Employee selected_record = (TableModel_Employee)employee_list.getItems().get(selected_index);
        employee_selected_id = selected_record.getEmployee_ID();
    }

    @FXML private void setFiltersEmp(){
        String fname = "", lname = "";

        employee_data = null;
        employee_data = FXCollections.observableArrayList();

        fname = " employeeFirstName LIKE '%" + filter_eFirstName.getText() + "%'";
        lname = " AND employeeLastName LIKE '%" + filter_eLastName.getText() + "%'";

        Connection conn = this.connect_db();
        String sql_main = "SELECT employeeID, employeeFirstName, EmployeeLastName, Employee_Status.employeeStatus, Employee_Type.employeeType from Employee"
                + " join Employee_Status on Employee_Status.employeeStatusID = Employee.employeeStatusID"
                +" join Employee_Type on Employee_Type.employeeTypeID = Employee.employeeTypeID"
                + " WHERE " + fname + lname;
        try {
            PreparedStatement ps = conn.prepareStatement(sql_main);
            ResultSet result_set = ps.executeQuery();
            while (result_set.next()){
                employee_data.add(new TableModel_Employee(result_set.getInt(1), result_set.getString(2), result_set.getString(3),
                        result_set.getString(4), result_set.getString(5)));
            }
            employee_list.setItems(employee_data);
            result_set.close();
            conn.close();
        } catch (SQLException tableQueryException) {
            System.err.println(tableQueryException.toString());
        }
    }

    @FXML private void clearFilters(){
        filter_eFirstName.setText("");
        filter_eLastName.setText("");
        populateEmpDataTable();
    }

    public void EmpDelete(){
        if (employee_list.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Cannot delete item!");
            alert.setHeaderText(null);
            alert.setContentText("No item selected!");
            alert.showAndWait();
        } else {
            fetch_RowID();
            Connection conn = this.connect_db();
            try {
                String sql = "DELETE FROM Customer WHERE customerID = " + employee_selected_id;
                PreparedStatement sql_statement = conn.prepareStatement(sql);
                sql_statement.executeUpdate();
                conn.commit();
                System.out.println("Item Deleted!");
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error on Building Data");
            }
            populateEmpDataTable();
        }
    }

    @FXML public void vieweditclick() throws IOException {
        fetch_RowID();
        if (employee_list.getSelectionModel().getSelectedItem() == null) {
            System.out.println("oops");
        } else {
            Stage customerStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("editviewEmployee.fxml"));
            customerStage.setTitle("Employee - Edit/View");
            customerStage.setScene(new Scene(root, 800, 800));
            customerStage.show();
        }
    }
    }


