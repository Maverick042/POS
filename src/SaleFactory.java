
import java.util.LinkedList;

public class SaleFactory {
    static LinkedList <ProductSpecification> psList = new LinkedList();
    IVATCalculator vatCalculator;
    IVATCalculator vc = new BDVATAdapter();//MyVATCalculator();
    static SaleFactory instance;
    
    SaleFactory(){
        ProductSpecification ps = new ProductSpecification();
        ps.setID(1);
        ps.setName("Book");
        ps.setPrice(250);
        psList.add(ps);
        
        ps = new ProductSpecification();
        ps.setID(2);
        ps.setName("Pen");
        ps.setPrice(15);
        psList.add(ps);
        
        ps = new ProductSpecification();
        ps.setID(3);
        ps.setName("Copy");
        ps.setPrice(50);
        psList.add(ps);
        
        ps = new ProductSpecification();
        ps.setID(4);
        ps.setName("Ruler");
        ps.setPrice(30);
        psList.add(ps);
        
        ps = new ProductSpecification();
        ps.setID(5);
        ps.setName("Geometry Box");
        ps.setPrice(500);
        psList.add(ps);
        
        ps = new ProductSpecification();
        ps.setID(6);
        ps.setName("Sharpner");
        ps.setPrice(10);
        psList.add(ps);
        
        ps = new ProductSpecification();
        ps.setID(7);
        ps.setName("Eraser");
        ps.setPrice(15);
        psList.add(ps);
        
        ps = new ProductSpecification();
        ps.setID(8);
        ps.setName("Calculator");
        ps.setPrice(700);
        psList.add(ps);
        
        ps = new ProductSpecification();
        ps.setID(9);
        ps.setName("Bag");
        ps.setPrice(1500);
        psList.add(ps);
        
    }
    
    static void addProductDescription(int id, String name, int price){
        ProductSpecification ps = new ProductSpecification();
        ps.setID(id);
        ps.setName(name);
        ps.setPrice(price);
        psList.add(ps);
    }
    
    static LinkedList<ProductSpecification> getProductSpecificationList(){
        return psList;
    }
    
    static ProductSpecification getProductSpecification(int id){
        if(psList!=null){
            for(ProductSpecification ps : psList){
                if(ps.getID()==id){
                    return ps;
                }
            }
        }
        return null;
    }
    
    public static synchronized SaleFactory getInstance(){
        if (instance==null)
            instance=new SaleFactory();
        return instance;
    }
    
    public IVATCalculator getVatCalculator() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
        if(vatCalculator==null)
        {
            String className=vc.getClass().getName();
            vatCalculator= (IVATCalculator) Class.forName(className).newInstance();
        }
        return vatCalculator;
    }
}
