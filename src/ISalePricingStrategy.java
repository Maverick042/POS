
public interface ISalePricingStrategy {
    int getTotal(Sale sale);
    void add(ISalePricingStrategy ps);
}
