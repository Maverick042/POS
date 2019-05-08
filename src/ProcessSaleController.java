
import java.util.Observable;
import java.util.Observer;
import javax.swing.table.AbstractTableModel;

public class ProcessSaleController {
    static PricingStrategyFactory psSt;
    Sale sale;
    //PropertyListener saleJFrame, beeper;
    
    ProcessSaleController (SaleJFrame saleJFrame){
        //beeper = new Beeper();
        psSt = PricingStrategyFactory.getInstance();
        makeNewSale();
        sale.addPropertyListener(saleJFrame);
        sale.addPropertyListener(new Beeper());
    }
    
    void makeNewSale(){
        sale = new Sale(psSt);
    }
    
    void addItem(int id, int quantity){
        sale.addSaleLineItem(id, quantity);
    }
    
    public Sale getSale(){
        return sale;
    }
    
    ProductSpecification getProductSpecification(int id){
        return SaleFactory.getProductSpecification(id);
    }
}
