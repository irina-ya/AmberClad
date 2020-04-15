package BikeShopApp;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TableModel_Employee {
    public IntegerProperty Employee_IDProperty() {return Employee_ID;}
    public StringProperty Employee_fNameProperty() {return Employee_fName;}
    public StringProperty Employee_lNameProperty() {return Employee_lName;}
    public StringProperty Employee_Status() {return Employee_Type;}
    public StringProperty Employee_EmailAddressProperty() {return Employee_Status;}

    private final IntegerProperty Employee_ID = new SimpleIntegerProperty();
    private final StringProperty Employee_fName = new SimpleStringProperty();
    private final StringProperty Employee_lName = new SimpleStringProperty();
    private final StringProperty Employee_Type = new SimpleStringProperty();
    private final StringProperty Employee_Status = new SimpleStringProperty();

    public TableModel_Employee(int ID, String fname, String lname, String type, String status){
        Employee_ID.set(ID);
        Employee_fName.set(fname);
        Employee_lName.set(lname);
        Employee_Type.set(type);
        Employee_Status.set(status);
    }

    public int getEmployee_ID(){return Employee_ID.get();}
    public void setEmployee_ID(int ID){Employee_ID.set(ID);}

    public String getEmployee_fName(){return Employee_fName.get();}
    public void setEmployee_fName(String fname){Employee_fName.set(fname);}

    public String getEmployee_lName(){return Employee_lName.get();}
    public void setEmployee_lName(String lname){Employee_lName.set(lname);}

    public String getEmployee_Type() {return Employee_Type.get();}
    public void setEmployee_Type(String type){
        Employee_Type.set(type);}

    public String getEmployee_Status(){return Employee_Status.get();}
    public void setEmployee_Status(String status){
        Employee_Status.set(status);}


}
