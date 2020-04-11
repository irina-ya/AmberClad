package BikeShopApp;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TableModel_Employee {
    public IntegerProperty Employee_IDProperty() {return Employee_ID;}
    public StringProperty Employee_fNameProperty() {return Employee_fName;}
    public StringProperty Employee_lNameProperty() {return Employee_lName;}
    public StringProperty Employee_PhoneNumberProperty() {return Employee_PhoneNumber;}
    public StringProperty Employee_EmailAddressProperty() {return Employee_EmailAddress;}

    private final IntegerProperty Employee_ID = new SimpleIntegerProperty();
    private final StringProperty Employee_fName = new SimpleStringProperty();
    private final StringProperty Employee_lName = new SimpleStringProperty();
    private final StringProperty Employee_PhoneNumber = new SimpleStringProperty();
    private final StringProperty Employee_EmailAddress = new SimpleStringProperty();

    public TableModel_Employee(int ID, String fname, String lname, String phone, String email){
        Employee_ID.set(ID);
        Employee_fName.set(fname);
        Employee_lName.set(lname);
        Employee_PhoneNumber.set(phone);
        Employee_EmailAddress.set(email);
    }

    public int getEmployee_ID(){return Employee_ID.get();}
    public void setEmployee_ID(int ID){Employee_ID.set(ID);}

    public String getEmployee_fName(){return Employee_fName.get();}
    public void setEmployee_fName(String fname){Employee_fName.set(fname);}

    public String getEmployee_lName(){return Employee_lName.get();}
    public void setEmployee_lName(String lname){Employee_lName.set(lname);}

    public String getEmployee_PhoneNumber() {return Employee_PhoneNumber.get();}
    public void setEmployee_PhoneNumber(String phone){Employee_PhoneNumber.set(phone);}

    public String getEmployee_EmailAddress(){return Employee_EmailAddress.get();}
    public void setEmployee_EmailAddress(String email){Employee_EmailAddress.set(email);}


}
