
public class AbsoluteDiscountOverThresholdPricingStrategy implements ISalePricingStrategy{
    double percentage=.07;
    double threshold = 1000;
    @Override
    public int getTotal(Sale sale){
        if(sale.getPreDiscountTotal()>=threshold)
            return (int) Math.floor (sale.getPreDiscountTotal()*percentage);
        return 0;
                
    }

    @Override
    public void add(ISalePricingStrategy ps) {
        
    }
}
