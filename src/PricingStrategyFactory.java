
import java.util.logging.Level;
import java.util.logging.Logger;

enum DiscountType{
    bestForCustomerPricingStrategy,
    bestForStorePricingStrategy,
    percentagePricingStrategy,
    absoluteThresholdPricingStrategy
}

public class PricingStrategyFactory {
    static PricingStrategyFactory instance;
    ISalePricingStrategy strategy;
    
    static CompositePricingStrategy cps;
    static CompositeBestForCustomerPricingStrategy cbCps;
    static CompositeBestForStorePricingStrategy cbSps;
    static ISalePricingStrategy adots;
    static ISalePricingStrategy pds;
    
    int getDiscount(DiscountType dsType, Sale sale){
        try {
            getSalePricingStrategy(dsType);
            return strategy.getTotal(sale);
        } catch (Exception ex) {
            System.out.println("Error");
        }
        return 0;
    }
    
    public static synchronized PricingStrategyFactory getInstance(){
        if(instance==null)
            instance = new PricingStrategyFactory();
        return instance;
    }
    
    ISalePricingStrategy getSalePricingStrategy(DiscountType dsType) throws Exception{
        switch(dsType){
            case absoluteThresholdPricingStrategy:{
                strategy = (ISalePricingStrategy) AbsoluteDiscountOverThresholdPricingStrategy.class.newInstance();
                break;
            }case bestForCustomerPricingStrategy:{
                strategy = (ISalePricingStrategy) CompositeBestForCustomerPricingStrategy.class.newInstance();
                strategy.add(AbsoluteDiscountOverThresholdPricingStrategy.class.newInstance());
                strategy.add(PercentageDiscountPricingStrategy.class.newInstance());
                break;
            }case bestForStorePricingStrategy:{
                strategy = (ISalePricingStrategy) CompositeBestForStorePricingStrategy.class.newInstance();
                strategy.add(AbsoluteDiscountOverThresholdPricingStrategy.class.newInstance());
                strategy.add(PercentageDiscountPricingStrategy.class.newInstance());
                break;
            }case percentagePricingStrategy:{
                strategy = (ISalePricingStrategy) PercentageDiscountPricingStrategy.class.newInstance();
                break;
            }
        }
        return strategy;                
    }
    /*
    ISalePricingStrategy getAbsoluteDiscountOverThresholdPricingStrategy(){
        String className= System.getProperty("PercentageDiscountPricingStrategy.class.Name");
        try{
            strategy = (ISalePricingStrategy) Class.forName(className).newInstance();
        }catch (Exception e){
            System.out.println(e);
        }
        return strategy;                
    }
    
    ISalePricingStrategy getPercentDiscountPricingStrategy(){
        String className= System.getProperty("PercentageDiscountPricingStrategy.class.Name");
        try{
            strategy = (ISalePricingStrategy) Class.forName(className).newInstance();
        }catch (Exception e){
            System.out.println(e);
        }
        return strategy;                
    }
    
    ISalePricingStrategy getCompositeBestForCustomerPricingStrategy(){
        String className= System.getProperty("CompositeBestForCustomerPricingStrategy.class.Name");
        try{
            strategy = (ISalePricingStrategy) Class.forName(className).newInstance();
        }catch (Exception e){
            System.out.println(e);
        }
        return strategy;                
    }
    
    ISalePricingStrategy getCompositeBestForStorePricingStrategy(){
        String className= System.getProperty("CompositeBestForStorePricingStrategy.class.Name");
        try{
            strategy = (ISalePricingStrategy) Class.forName(className).newInstance();
        }catch (Exception e){
            System.out.println(e);
        }
        return strategy;                
    }   
    */
}
