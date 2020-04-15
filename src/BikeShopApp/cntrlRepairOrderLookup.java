package BikeShopApp;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class cntrlRepairOrderLookup implements Initializable {

    @FXML
    public TableColumn id, type, first, last, bike, status;

    @FXML
    JFXTextField custFirstName, custLastName, orderNum;

    @FXML
    JFXComboBox repairType, repairStatus, bikeCombo;

    @FXML
    JFXButton editBtn, delBtn, filterBtn, clearBtn;

    @FXML
    TableView repairOrder_list;

    public static int repair_order_selected_id;

    private ObservableList<TableModel_RepairOrder> repairOrder_data;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(new PropertyValueFactory<TableModel_Employee,String>("RepairOrder_ID"));
        type.setCellValueFactory(new PropertyValueFactory<TableModel_Employee,String>("Repair_Type"));
        first.setCellValueFactory(new PropertyValueFactory<TableModel_Employee,String>("Customer_fName"));
        last.setCellValueFactory(new PropertyValueFactory<TableModel_Employee,String>("Customer_lName"));
        bike.setCellValueFactory(new PropertyValueFactory<TableModel_Employee,String>("Customer_Bike"));
        status.setCellValueFactory(new PropertyValueFactory<TableModel_Employee,String>("RepairOrder_Status"));

        populateRepairOrderTable();

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

    private void populateRepairOrderTable(){
        Connection conn = this.connect_db();
        repairOrder_data = FXCollections.observableArrayList();

        String sql_main = "SELECT repairOrderID, Repair_Type.repairTypeDesc, Customer.customerFirstName, Customer.customerLastName, CONCAT(Bike_Manufacturer.bikeManufacturerName,' ', Bike_Line.bikeLineName,' ',Bike_Model.bikeModelName), Repair_Order_Status.repairOrderStatus FROM Repair_Order JOIN Repair_Type on Repair_Type.repairTypeID = Repair_Order.repairTypeID" +
                " JOIN Repair_Order_Status on Repair_Order_Status.repairOrderStatusID = Repair_Order.repairOrderStatusID JOIN Customer_Bike on Customer_Bike.customerBikeID = Repair_Order.customerBikeID" +
                " JOIN Customer on Customer.customerID = Customer_Bike.customerID JOIN Bike on Customer_Bike.bikeID = Bike.bikeID JOIN Bike_Model on Bike.bikeModelID = Bike_Model.bikeModelID" +
                " JOIN Bike_Line on Bike_Model.bikeLineID = Bike_Line.bikeLineID JOIN Bike_Manufacturer on Bike_Line.bikeManufacturerID = Bike_Manufacturer.bikeManufacturerID";
        try {
            PreparedStatement ps = conn.prepareStatement(sql_main);
            ResultSet result_set = ps.executeQuery();
            while (result_set.next()){
                repairOrder_data.add(new TableModel_RepairOrder(result_set.getInt(1), result_set.getString(2), result_set.getString(3),
                        result_set.getString(4), result_set.getString(5), result_set.getString(6)));
            }
            repairOrder_list.setItems(repairOrder_data);
            result_set.close();
            conn.close();
        } catch (SQLException tableQueryException) {
            System.err.println(tableQueryException.toString());
        }
    }

    private void fetch_RowID(){
        int selected_index;
        selected_index = repairOrder_list.getSelectionModel().getSelectedIndex();
        TableModel_Employee selected_record = (TableModel_Employee)repairOrder_list.getItems().get(selected_index);
        repair_order_selected_id = selected_record.getEmployee_ID();
    }

    @FXML private void setFiltersRepairOrder(){
        String fname = "", lname = "";

        repairOrder_data = null;
        repairOrder_data = FXCollections.observableArrayList();

        fname = " customerFirstName LIKE '%" + custFirstName.getText() + "%'";
        lname = " AND customerLastName LIKE '%" + custLastName.getText() + "%'";

        Connection conn = this.connect_db();
        String sql_main = "SELECT repairOrderID, Repair_Type.repairTypeDesc, Customer.customerFirstName, Customer.customerLastName, CONCAT(Bike_Manufacturer.bikeManufacturerName,' ', Bike_Line.bikeLineName,' ',Bike_Model.bikeModelName), Repair_Order_Status.repairOrderStatus FROM Repair_Order JOIN Repair_Type on Repair_Type.repairTypeID = Repair_Order.repairTypeID" +
                " JOIN Repair_Order_Status on Repair_Order_Status.repairOrderStatusID = Repair_Order.repairOrderStatusID JOIN Customer_Bike on Customer_Bike.customerBikeID = Repair_Order.customerBikeID" +
                " JOIN Customer on Customer.customerID = Customer_Bike.customerID JOIN Bike on Customer_Bike.bikeID = Bike.bikeID JOIN Bike_Model on Bike.bikeModelID = Bike_Model.bikeModelID" +
                " JOIN Bike_Line on Bike_Model.bikeLineID = Bike_Line.bikeLineID JOIN Bike_Manufacturer on Bike_Line.bikeManufacturerID = Bike_Manufacturer.bikeManufacturerID"
                + " WHERE " + fname + lname;
        try {
            PreparedStatement ps = conn.prepareStatement(sql_main);
            ResultSet result_set = ps.executeQuery();
            while (result_set.next()){
                repairOrder_data.add(new TableModel_RepairOrder(result_set.getInt(1), result_set.getString(2), result_set.getString(3),
                        result_set.getString(4), result_set.getString(5), result_set.getString(6)));
            }
            repairOrder_list.setItems(repairOrder_data);
            result_set.close();
            conn.close();
        } catch (SQLException tableQueryException) {
            System.err.println(tableQueryException.toString());
        }
    }


    @FXML private void clearFilters(){
        custFirstName.setText("");
        custLastName.setText("");
        populateRepairOrderTable();
    }

}
