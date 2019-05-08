
import java.lang.Exception.*;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SalesLineItem extends Observable{
    private ProductSpecification ps;
    private int quantity = 0;
    
    SalesLineItem(int id, int quantity) throws Exception{
        ProductSpecification ps1 = SaleFactory.getProductSpecification(id);
        //System.out.println("Found matched ps = "+ps1.getName()+" , "+ps1.getPrice());
        if(ps1!=null){
            ps = ps1;
            this.quantity = quantity;
        }else{
            throw new Exception("Invalid ID");
        }
    }
    
    
    ProductSpecification getProductSpecification(){
        return ps;
    }
    
    void getProductSpecification(ProductSpecification ps){
        this.ps = ps;
    }
    public String getName(){
        return ps.getName();
    }
    
    public int getID(){
        return ps.getID();
    }
    
    public int getPrice(){
        return ps.getPrice();
    }
        
    public String getDescription(){
        return ps.getDescription();
    }
    
    public int getQuantity(){
        return quantity;
    }
    
    public void setName(String Name){
        ps.setName(Name);
    }
    
    public void setID(int ID){
        ps.setID(ID);
    }
    
    public void setPrice(int Price){
        ps.setPrice(Price);
    }
    
    public void setDescription(String Description){
        ps.setDescription(Description);
    }
    
    public void setQuantity(int Quantity){
        if(quantity==0){
            quantity = Quantity;
        }else{
            quantity += Quantity;
        }
    }
    
    public int getSubTotal(){
        return quantity * ps.getPrice();
    }
}