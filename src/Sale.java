
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;


enum SLI_Type{
    new_sli_created,
    old_sli_updated,
    sli_creation_error
}

class SLI_Info{
    public SLI_Type sliType;
    public int positionInList;
}

public class Sale {
    protected LinkedList <SalesLineItem> sli;// = new LinkedList();
    private SLI_Info lastSLI_EntryInfo;// = new SLI_Info();
    LinkedList<PropertyListener> propertyListeners;
    IVATCalculator ivac;
    PricingStrategyFactory psSt;
    int total;
    DiscountType discountType=null;
    
    Sale(PricingStrategyFactory psSt){
        propertyListeners = new LinkedList<>();
        lastSLI_EntryInfo = new SLI_Info();
        sli = new LinkedList();
        this.psSt = psSt;
    }
    
    void addPropertyListener(PropertyListener pl){
        propertyListeners.add(pl);
    }
    
    void setTotal(int newTotal){
        total = newTotal;
        publishPropertyEvent();
    }
    
    void publishPropertyEvent(){
        for(PropertyListener pl: propertyListeners){
            pl.onPropertyEvent(total);
        }
    }
    
    void calculateTotal(){
        int t = 0;
        for (SalesLineItem sli1 : sli) {
            t += sli1.getSubTotal();
        }
        setTotal(t);        
    }
    
    public int getTotal(){
        return total;
    }
    
    public SLI_Info getLast_SLI_EntryInfo(){
        return lastSLI_EntryInfo;
    } 
    
    public SalesLineItem getSLItem(int index){
        return sli.get(index);
    }
    
    public LinkedList<SalesLineItem> getSLItemsList(){
        return sli;
    }
    
    void addSaleLineItem(int id, int quantity){
        int index = searchForPreviousEntry(id);
        if(index == -1){
            try{
                sli.add(new SalesLineItem(id,quantity));
                lastSLI_EntryInfo.sliType = SLI_Type.new_sli_created;
                lastSLI_EntryInfo.positionInList = sli.size()-1;
                calculateTotal();   
            }catch(Exception ex){
                System.out.println("Error! ID not in System");
                lastSLI_EntryInfo.sliType = SLI_Type.sli_creation_error;
                lastSLI_EntryInfo.positionInList = -1;
            }
        }else{
            sli.get(index).setQuantity(quantity);
            lastSLI_EntryInfo.sliType = SLI_Type.old_sli_updated;
            lastSLI_EntryInfo.positionInList = index;
            calculateTotal();
        }
    }
    
    void print(){
        System.out.println("SL#inList:: ID-ProductName-Quantity-Price-Subtotal");
        int i=0;
        for (SalesLineItem sli1 : sli){        
            System.out.println(i + ":: "+sli1.getID()+"-"+sli1.getName()+"-"+sli1.getPrice()+"-"+sli1.getQuantity()+"-"+sli1.getSubTotal());
        }
        System.out.println("\t\tEnd Of ProductList\n");
    }
    
    int searchForPreviousEntry(int id){
        if(!sli.isEmpty()){
            for(int i=0,j=sli.size();i<j;i++){
                if(sli.get(i).getID()==id){
                    return i;
                }
            }
        }
        return -1;
    }
    
    int getVATAmount(){
        try{
            ivac = SaleFactory.getInstance().getVatCalculator();                        
        }catch(Exception e){
            System.out.println(e);
        }
        return ivac.getVATAmount(this.getTotal());
    }
    
    int getPreDiscountTotal(){
        return this.getTotal()+this.getVATAmount();
    }
    
    int getDiscountAmount(DiscountType dscntType){
        this.discountType = dscntType;
        if(getTotal()!=0)
            return this.psSt.getDiscount(discountType, this);
        else
            return 0;
    }
    
    int getGrandTotal(){
        return this.getPreDiscountTotal()-this.psSt.getDiscount(this.discountType, this);
    }
}


    