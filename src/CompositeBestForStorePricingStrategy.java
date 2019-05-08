
public class CompositeBestForStorePricingStrategy extends CompositePricingStrategy{
 
    public int getTotal(Sale sale){
        int discountAmount=0,dA;
        for(ISalePricingStrategy is: isPss){
            dA=is.getTotal(sale);
            if(discountAmount==0 || discountAmount>dA)
                discountAmount=dA;
        }
        return discountAmount;
    }
}
