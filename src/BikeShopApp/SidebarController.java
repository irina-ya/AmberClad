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
    @FXML
    private void home(MouseEvent event){
        bp.setCenter(main);
    }

    @FXML
    private void repairOrderMain(MouseEvent event){
        System.out.println("I load Repair Order Screen!");
        loadPage("repairOrderMain");
    }

    @FXML
    private void repairOrderLookup(MouseEvent event){
        System.out.println("I load Repair Order Lookup Screen!");
        loadPage("repairOrderLookup");
    }

    @FXML
    private void newRepairOrder(MouseEvent event){
        System.out.println("I load new Repair Order Screen!");
        loadPage("newRepairOrder");
    }

    @FXML
    private void activeRepairOrder(MouseEvent event){
        System.out.println("I load Active Order Screen!");
        loadPage("activeRepairOrders");
    }

    @FXML
    private void allRepairOrder(MouseEvent event){
        System.out.println("I load All Order Screen!");
        loadPage("allRepairOrders");
    }

    @FXML
    private void productOrdersMain(MouseEvent event){
        System.out.println("I load  Product Order Screen!");
        loadPage("productOrdersMain");
    }

    @FXML
    private void productOrderLookup(MouseEvent event){
        System.out.println("I load Product Order Lookup Screen!");
        loadPage("productOrderLookup");
    }

    @FXML
    private void newProductOrder(MouseEvent event){
        System.out.println("I load new Product Order Screen!");
        loadPage("newProductOrder");
    }

    @FXML
    private void activeProductOrders(MouseEvent event){
        System.out.println("I load Active Product Order Screen!");
        loadPage("activeProductOrders");
    }

    @FXML
    private void allProductOrders(MouseEvent event){
        System.out.println("I load All Product Order Screen!");
        loadPage("ProductDirectory");
    }

    @FXML
    private void customerMain(MouseEvent event){
        System.out.println("I load Customer Main Screen!");
        loadPage("customersMain");
    }

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

    @FXML
    private void employeeMain(MouseEvent event){
        System.out.println("I load employee Main");
        loadPage("employeeMain");
    }

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

    @FXML
    private void reportingMain(MouseEvent event){
        System.out.println("I load Reporting Main Screen!");
        loadPage("reportingMain");
    }

    @FXML
    private void customReport(MouseEvent event){
        System.out.println("I load Custom Reports screen!");
        loadPage("customReport");
    }

    @FXML
    private void myReports(MouseEvent event){
        System.out.println("I load My Reports Screen!");
        loadPage("myReports");
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
