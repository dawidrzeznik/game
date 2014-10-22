/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wojnaswiatow;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;

/**
 *
 * @author Dawidziu
 */
public class RunGame implements Runnable{

    private Thread watek;
    private WojnaSwiatow inv;
    @Override
    public void run() {
        try {
            //inv = new WojnaSwiatow();
            inv.start();
            
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (MessagingException ex) {
            Logger.getLogger(RunGame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public RunGame() {
        inv = new WojnaSwiatow();
         watek = new Thread(this);
        watek.start();
        
    }
    
    public void PauseGame() {
  
            //inv.setGameEnded(true);
        //inv.setSZYBKOSC(100000);
        inv.setPause(true);
    }
    
    public void ExitGame(){
        //inv.setSZYBKOSC(5);
        inv.setGameEnded(true);
        inv.setPause(true);
        
    }
    
    public void ResumeGame(){
        //inv.setSZYBKOSC(10);
        inv.setPause(false);
    }
}
