package BikeShopApp;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TableModel_Product {

    public StringProperty ProductName_Property() {return Product_Name;}
    public StringProperty ProductCat_Property() {return Product_Cat;}
    public  StringProperty ProductManufac_Property() {return Product_Manufac;}
    public DoubleProperty ProductCost_Property() {return Product_Cost;}
    public StringProperty ProductStatus_Property () {return Product_Status;}

    private final StringProperty Product_Name = new SimpleStringProperty();
    private final StringProperty Product_Cat = new SimpleStringProperty();
    private final StringProperty Product_Manufac = new SimpleStringProperty();
    private final DoubleProperty Product_Cost = new SimpleDoubleProperty();
    private final StringProperty Product_Status = new SimpleStringProperty();

    public TableModel_Product (String name, String cat, String manufac, double cost, String status){
        Product_Name.set(name);
        Product_Cat.set(cat);
        Product_Manufac.set(manufac);
        Product_Cost.set(cost);
        Product_Status.set(status);
    }

    public String getProduct_Name(){return Product_Name.get();}
    public void setProduct_Name(String name){Product_Name.set(name); }

    public String getProduct_Cat(){return Product_Cat.get();}
    public void setProduct_Cat(String cat){Product_Cat.set(cat);}

    public String getProduct_Manufac(){return Product_Manufac.get();}
    public void setProduct_Manufac(String manufac){Product_Manufac.set(manufac);}

    public double getProduct_Cost() { return Product_Cost.get(); }
    public void setProduct_Cost(double cost){setProduct_Cost(cost);}

    public String getProduct_Status(){return Product_Status.get();}
    public void setProduct_Status(String status){setProduct_Status(status);}
}
