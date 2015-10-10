package view;

import controller.Controller;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by User on 21.09.2015.
 */
public class GuiTest {

    @Test
    public  void testMyField(){

        Gui gui=new Gui();
       // Rects rects=new Rects(gui);
        for(int i=0; i<=99; i++){
            int y=(int) i/10;
            int x=i-y*10;
            System.out.println(x+"  "+y);
            //gui.getRects().getMyRect()
        }
    }


}
