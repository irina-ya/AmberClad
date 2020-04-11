package BikeShopApp;

import javafx.beans.property.*;

public class TableModel_Customer {
    public IntegerProperty Customer_IDProperty() {return Customer_ID;}
    public StringProperty Customer_fNameProperty() {return Customer_fName;}
    public StringProperty Customer_lNameProperty() {return Customer_lName;}
    public StringProperty Customer_PhoneNumberProperty() {return Customer_PhoneNumber;}
    public StringProperty Customer_EmailAddressProperty() {return Customer_EmailAddress;}

    private final IntegerProperty Customer_ID = new SimpleIntegerProperty();
    private final StringProperty Customer_fName = new SimpleStringProperty();
    private final StringProperty Customer_lName = new SimpleStringProperty();
    private final StringProperty Customer_PhoneNumber = new SimpleStringProperty();
    private final StringProperty Customer_EmailAddress = new SimpleStringProperty();

    public TableModel_Customer(int ID, String fname, String lname, String phone, String email){
        Customer_ID.set(ID);
        Customer_fName.set(fname);
        Customer_lName.set(lname);
        Customer_PhoneNumber.set(phone);
        Customer_EmailAddress.set(email);
    }

    public int getCustomer_ID(){return Customer_ID.get();}
    public void setCustomer_ID(int ID){Customer_ID.set(ID);}

    public String getCustomer_fName(){return Customer_fName.get();}
    public void setCustomer_fName(String fname){Customer_fName.set(fname);}

    public String getCustomer_lName(){return Customer_lName.get();}
    public void setCustomer_lName(String lname){Customer_lName.set(lname);}

    public String getCustomer_PhoneNumber() {return Customer_PhoneNumber.get();}
    public void setCustomer_PhoneNumber(String phone){Customer_PhoneNumber.set(phone);}

    public String getCustomer_EmailAddress(){return Customer_EmailAddress.get();}
    public void setCustomer_EmailAddress(String email){Customer_EmailAddress.set(email);}
}
