
import java.util.LinkedList;

public class CompositePricingStrategy implements ISalePricingStrategy{
    LinkedList<ISalePricingStrategy> isPss;
    
    public void add(ISalePricingStrategy ps){
        if(isPss==null)
            isPss = new LinkedList<>();
        isPss.add(ps);
    }
    
    
    
    public int getTotal(Sale sale){
        return 0;
    }
}
