
public class Beeper implements PropertyListener{

    @Override
    public void onPropertyEvent(int total) 
    {
        java.awt.Toolkit.getDefaultToolkit().beep();
    }
    
}
