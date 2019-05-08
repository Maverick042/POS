
public class ProductSpecification {
    //private 
    private int id;
    private String name, description;
    private int price;
    
    public String getName(){
        return name;
    }
    
    public int getPrice(){
        return price;
    }
    
    public int getID(){
        return id;
    }
    
    public String getDescription(){
        return description;
    }
    
    public void setName(String Name){
        name = Name;
    }
    
    public void setPrice(int Price){
        price = Price;
    }
    
    public void setID(int ID){
        id = ID;
    }
    
    public void setDescription(String Description){
        description = Description;
    }
}
