
public class MyVATCalculator implements IVATCalculator{
    public int getVATAmount(int saleTotal)
    {
        return (int) Math.round(saleTotal*.05);
    }
}
