package BikeShopApp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SidebarController implements Initializable {

    @FXML
    private BorderPane bp;

    @FXML
    private AnchorPane main;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    //These methods load fxml files to anchor pane 'On Mouse Clicked'. Add these methods to button in SceneBuilder.
    //call load page method and add title of fxml w/o fxml extension
/*    @FXML
    private void home(MouseEvent event){
        bp.setCenter(main);
    }*/

    //Repair Order

    @FXML
    private void newRepairOrder(MouseEvent event){
        System.out.println("I load new Repair Order Screen!");
        loadPage("newRepairOrder");
    }

    @FXML
    private void repairOrderLookup(MouseEvent event){
        System.out.println("I load Repair Order Lookup Screen!");
        loadPage("repairOrderLookup");
    }

    @FXML
    private void addRepairService(MouseEvent event){
        System.out.println("I load new Repair Order Screen!");
        loadPage("addRepairService");
    }

    @FXML
    private void assignRepairOrder(MouseEvent event){
        System.out.println("I load assign employee screen!");
        loadPage("assignRepairOrder");
    }

    //Parts for Repair Order

    @FXML
    private void newRepairPart(MouseEvent event){
        System.out.println("I load new Repair Part!");
        loadPage("newRepairPart");
    }

    @FXML
    private void repairPartDirectory(MouseEvent event){
        System.out.println("I load Repair Part Directory!");
        loadPage("RepairPartDirectory");
    }

    @FXML
    private void newSupplier(MouseEvent event){
        System.out.println("I load new Supplier!");
        loadPage("newSupplier");
    }

    @FXML
    private void supplierDirectory(MouseEvent event){
        System.out.println("I load Supplier Directory!");
        loadPage("supplierDirectory");
    }

    //Product Orders

    @FXML
    private void newProductOrder(MouseEvent event){
        System.out.println("I load new Product Order Screen!");
        loadPage("newProductOrder");
    }


    @FXML
    private void allProductOrders(MouseEvent event){
        System.out.println("I load All Product Order Screen!");
        loadPage("ProductDirectory");
    }

    //Customer
    @FXML
    private void newCustomer(MouseEvent event){
        System.out.println("I load new Customer Screen!");

        loadPage("newCustomer");
    }

    @FXML
    private void customerDirectory(MouseEvent event){
        System.out.println("I load Customer Directory Screen!");
        loadPage("CustomerDirectory");
    }

    //Employee

    @FXML
    private void newEmployee(MouseEvent event){
        System.out.println("I load new Employee screen!");
        loadPage("newEmployee");
    }

    @FXML
    private void employeeDirectory(MouseEvent event){
        System.out.println("I load Employee Directory Screen!");
        loadPage("employeeDirectory");
    }


    //Loads fxml files in anchorpane. DO NOT CHANGE
    private void loadPage(String page){
        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getResource(page + ".fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        bp.setCenter(root);
    }
}
