package BikeShopApp;

import com.jfoenix.controls.JFXTextField;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class cntrl_ProductDirectory  implements Initializable {

    @FXML public TableColumn col_1,col_2,col_3,col_4, col_5;
    @FXML TableView product_table;
    private ObservableList<TableModel_Product> product_data;
    @FXML JFXTextField filter_name;

    public static int product_selected_id;

    public void initialize(URL url, ResourceBundle resourceBundle) {

        col_1.setCellValueFactory(new PropertyValueFactory<TableModel_Product,String>("Product_Name"));
        col_2.setCellValueFactory(new PropertyValueFactory<TableModel_Product,String>("Product_Cat"));
        col_3.setCellValueFactory(new PropertyValueFactory<TableModel_Product,String>("Product_Manufac"));
        col_4.setCellValueFactory(new PropertyValueFactory<TableModel_Product,String>("Product_Cost"));
        col_5.setCellValueFactory(new PropertyValueFactory<TableModel_Product,String>("Product_Status"));

        PopulateProdTable();
    }

    private void PopulateProdTable(){
        Connection conn = this.connect_db();
        product_data = FXCollections.observableArrayList();

        String sql_main = "SELECT productDesc, Product_Manufacturer.productManufacturerName, Product_Category.productCategoryName,"
                + "productCost, Product_Status.productStatus from Product"
                + " join Product_Category on Product_Category.productCategoryID = Product.productCategoryID"
                + " join Product_Manufacturer on Product_Manufacturer.productManufacturerID = Product.productManufacturerID"
                + " join Product_Status on Product_Status.productStatusID = Product.productStatusID";
        try {
            PreparedStatement ps = conn.prepareStatement(sql_main);
            ResultSet result_set = ps.executeQuery();
            while (result_set.next()){
                product_data.add(new TableModel_Product(result_set.getString(1), result_set.getString(2), result_set.getString(3),
                        result_set.getDouble(4), result_set.getString(5)));
            }
            product_table.setItems(product_data);
            result_set.close();
            conn.close();
        } catch (SQLException tableQueryException) {
            System.err.println(tableQueryException.toString());
        }

    }

    private void fetch_RowID(){
        int selected_index;
        selected_index = product_table.getSelectionModel().getSelectedIndex();
        TableModel_Customer selected_record = (TableModel_Customer)product_table.getItems().get(selected_index);
        product_selected_id = selected_record.getCustomer_ID();
    }

    @FXML private void Products_delete (){
        if (product_table.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Cannot delete item!");
            alert.setHeaderText(null);
            alert.setContentText("No item selected!");
            alert.showAndWait();
        } else {
            fetch_RowID();
            Connection conn = this.connect_db();
            try {
                String sql = "DELETE FROM Product WHERE productID = " + product_selected_id;
                PreparedStatement sql_statement = conn.prepareStatement(sql);
                sql_statement.executeUpdate();
                conn.commit();
                System.out.println("Item Deleted!");
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error on Building Data");
            }
            PopulateProdTable();
        }
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

    @FXML public void setfilers(){
        String name="";

        product_data = null;
        product_data = FXCollections.observableArrayList();

        name = " productDesc LIKE '%" + filter_name.getText() + "%'";
        String sql_main = "SELECT productDesc, Product_Manufacturer.productManufacturerName, Product_Category.productCategoryName,"
                + "productCost, Product_Status.productStatus from Product"
                + " join Product_Category on Product_Category.productCategoryID = Product.productCategoryID"
                + " join Product_Manufacturer on Product_Manufacturer.productManufacturerID = Product.productManufacturerID"
                + " join Product_Status on Product_Status.productStatusID = Product.productStatusID"
                + " WHERE " + name;

        Connection conn = this.connect_db();
        try {
            PreparedStatement ps = conn.prepareStatement(sql_main);
            ResultSet result_set = ps.executeQuery();
            while (result_set.next()){
                product_data.add(new TableModel_Product(result_set.getString(1), result_set.getString(2), result_set.getString(3),
                        result_set.getDouble(4), result_set.getString(5)));
            }
            product_table.setItems(product_data);
            result_set.close();
            conn.close();
        } catch (SQLException tableQueryException) {
            System.err.println(tableQueryException.toString());
        }


    }


}
