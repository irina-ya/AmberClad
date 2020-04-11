package BikeShopApp;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TableModel_RepairOrder {
    public IntegerProperty RepairOrderID_Property(){return RepairOrder_ID;}
    public StringProperty RepairOrderType_Property(){return Repair_Type;}
    public StringProperty Customer_fNameProperty() {return Customer_fName;}
    public StringProperty Customer_lNameProperty() {return Customer_lName;}
    public StringProperty Customer_BikeProperty() {return Customer_Bike;}
    public StringProperty RepiarOrder_StatusProperty(){return RepairOrder_Status;}

    private final IntegerProperty RepairOrder_ID = new SimpleIntegerProperty();
    private final StringProperty Repair_Type = new SimpleStringProperty();
    private final StringProperty Customer_fName = new SimpleStringProperty();
    private final StringProperty Customer_lName = new SimpleStringProperty();
    private final StringProperty Customer_Bike = new SimpleStringProperty();
    private final StringProperty RepairOrder_Status = new SimpleStringProperty();

    public TableModel_RepairOrder(int ID, String type, String fname, String lname, String bike, String status){
        RepairOrder_ID.set(ID);
        Repair_Type.set(type);
        Customer_fName.set(fname);
        Customer_lName.set(lname);
        Customer_Bike.set(bike);
        RepairOrder_Status.set(status);
    }

    public int getRepairOrder_ID(){return RepairOrder_ID.get();}
    public void setRepairOrder_ID(int ID){RepairOrder_ID.set(ID);}

    public String getCustomer_fName(){return Customer_fName.get();}
    public void setCustomer_fName(String fname){Customer_fName.set(fname);}

    public String getCustomer_lName(){return Customer_lName.get();}
    public void setCustomer_lName(String lname){Customer_lName.set(lname);}

    public String getCustomer_Bike(){return Customer_Bike.get();}
    public void setCustomer_Bike(String bike){Customer_Bike.set(bike);}

    public String getRepairOrder_Status(){return RepairOrder_Status.get();}
    public void setRepairOrder_Status(String status){RepairOrder_Status.set(status);}

}
