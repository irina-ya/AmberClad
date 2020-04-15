package BikeShopApp;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class custDirectoryController {

    @FXML
    public TableColumn col_1,col_2,col_3,col_4, col_5;
    private ObservableList<TableModel_Customer> customer_data;
    @FXML
    TableView customer_list;
    @FXML
    JFXTextField filter_fname,filter_lname, filter_phone,filter_email;
    @FXML
    JFXButton clearBtn, searchBtn,viewDetailsBtn;

    public static int customer_selected_id;

    @FXML private void initialize() {

        col_1.setCellValueFactory(new PropertyValueFactory<TableModel_Customer,String>("Customer_ID"));
        col_2.setCellValueFactory(new PropertyValueFactory<TableModel_Customer,String>("Customer_fName"));
        col_3.setCellValueFactory(new PropertyValueFactory<TableModel_Customer,String>("Customer_lName"));
        col_4.setCellValueFactory(new PropertyValueFactory<TableModel_Customer,String>("Customer_PhoneNumber"));
        col_5.setCellValueFactory(new PropertyValueFactory<TableModel_Customer,String>("Customer_EmailAddress"));

       populateDataTable();

    }


    //set up DB connection
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

    public void populateDataTable() {

        Connection conn = this.connect_db();
        customer_data = FXCollections.observableArrayList();

        String sql_main = "SELECT customerID, customerFirstName, customerLastName, customerPrimaryPhone, customerEmail FROM Customer ORDER BY CustomerID";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql_main);
            ResultSet result_set = preparedStatement.executeQuery();
            while (result_set.next()) {
                customer_data.add(new TableModel_Customer(result_set.getInt(1),result_set.getString(2),result_set.getString(3),result_set.getString(4),
                        result_set.getString(5)));
            }
            customer_list.setItems(customer_data);
            result_set.close();
            conn.close();
        } catch (SQLException tableQueryException) {
            System.err.println(tableQueryException.toString());
        }
    }

    private void fetch_RowID(){
        int selected_index;
        selected_index = customer_list.getSelectionModel().getSelectedIndex();
        TableModel_Customer selected_record = (TableModel_Customer)customer_list.getItems().get(selected_index);
        customer_selected_id = selected_record.getCustomer_ID();
    }

    @FXML public void setfilers(){
        String fname = "", lname = "", phone="", email="";

        customer_data = null;
        customer_data = FXCollections.observableArrayList();

        fname = " customerFirstName LIKE '%" + filter_fname.getText() + "%'";
        lname = " AND customerLastName LIKE '%" + filter_lname.getText() + "%'";
        phone = " AND customerPrimaryPhone LIKE '" + filter_phone.getText() + "%'";
        email = " AND customerEmail LIKE '" + filter_email.getText() + "%'";


        Connection conn = this.connect_db();
        String sql_main = "SELECT customerID, customerFirstName, customerLastName, customerPrimaryPhone, customerEmail FROM Customer WHERE" + fname + lname + phone + email + " ORDER BY CustomerID";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql_main);
            ResultSet result_set = preparedStatement.executeQuery();
            while (result_set.next()) {
                customer_data.add(new TableModel_Customer(result_set.getInt(1),result_set.getString(2),result_set.getString(3),result_set.getString(4),
                        result_set.getString(5)));
            }
            customer_list.setItems(customer_data);

            result_set.close();
            conn.close();
        } catch (SQLException tableQueryException) {
            System.err.println(tableQueryException.toString());
        }
    }

    @FXML private void clearFilters(){
        filter_fname.setText("");
        filter_lname.setText("");
        filter_phone.setText("");
        filter_email.setText("");
        populateDataTable();
    }

    @FXML private void customersdelete (){
        if (customer_list.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Cannot delete item!");
            alert.setHeaderText(null);
            alert.setContentText("No item selected!");
            alert.showAndWait();
        } else {
            fetch_RowID();
            Connection conn = this.connect_db();
            try {
                String sql = "DELETE FROM Customer WHERE customerID = " + customer_selected_id;
                PreparedStatement sql_statement = conn.prepareStatement(sql);
                sql_statement.executeUpdate();
                conn.commit();
                System.out.println("Item Deleted!");
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error on Building Data");
            }
            populateDataTable();
        }
    }

    @FXML public void vieweditclick() throws IOException {
        fetch_RowID();
        if (customer_list.getSelectionModel().getSelectedItem() == null) {
            System.out.println("oops");
        } else {
            Stage customerStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("editviewCustomer.fxml"));
            customerStage.setTitle("Customers - Edit/View");
            customerStage.setScene(new Scene(root, 800, 800));
            customerStage.show();
        }
    }
    }




