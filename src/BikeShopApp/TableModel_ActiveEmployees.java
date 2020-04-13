package BikeShopApp;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TableModel_ActiveEmployees {
    public StringProperty Employee_fNameProperty() {return Employee_fName;}
    public StringProperty Employee_lNameProperty() {return Employee_lName;}
    public StringProperty Employee_PositionProperty() {return Employee_Position;}
    public StringProperty Employee_StatusProperty() {return Employee_Type;}
    public StringProperty Employee_PhoneProperty() {return Employee_Phone;}

    private final StringProperty Employee_fName = new SimpleStringProperty();
    private final StringProperty Employee_lName = new SimpleStringProperty();
    private final StringProperty Employee_Position = new SimpleStringProperty();
    private final StringProperty Employee_Type = new SimpleStringProperty();
    private final StringProperty Employee_Status = new SimpleStringProperty();
    private final StringProperty Employee_Phone = new SimpleStringProperty();

    public TableModel_ActiveEmployees(String fname, String lname, String position, String type, String status, String phone){
        Employee_fName.set(fname);
        Employee_lName.set(lname);
        Employee_Position.set(position);
        Employee_Type.set(type);
        Employee_Status.set(status);
        Employee_Phone.set(phone);
    }

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

    public String getEmployee_Phone(){return Employee_Phone.get();}
    public void setEmployee_Phone(String phone){Employee_Phone.set(phone);}
}
