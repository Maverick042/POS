
public class BDVATAdapter implements IVATCalculator
{
    
    @Override
    public int getVATAmount(int saleTotal)
    {
        BDVATCalculator bvc=new BDVATCalculator();
        return (int) Math.floor (bvc.CalculateVATAmount(saleTotal));
    }
    
}
