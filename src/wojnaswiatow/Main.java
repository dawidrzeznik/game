/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wojnaswiatow;

import java.io.IOException;
import org.apache.log4j.*;



/**
 *
 * @author Dawidziu
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new MainFrame();
        
        Layout lay1 = new PatternLayout("[%p] %c - %m - Data wpisu: %d %n");
        Appender app1 = null;
        try {
             app1 = new FileAppender(lay1,"D:/log_game.txt");
            } catch(IOException ex) {
        }

    BasicConfigurator.configure(app1);
    Logger logger = Logger.getRootLogger();
    logger.debug("Wlaczono program");
    logger.setLevel(Level.TRACE);
    


        
 
        
    }
    
}
