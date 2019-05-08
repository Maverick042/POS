
public class PercentageDiscountPricingStrategy implements ISalePricingStrategy{
    double percentage=.05;
    public int getTotal(Sale sale)
    {
        return (int) Math.floor (sale.getPreDiscountTotal()*percentage);
    }
    public void add(ISalePricingStrategy ps){
        
    }
}
