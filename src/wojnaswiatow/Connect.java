/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wojnaswiatow;
import java.util.Iterator;
import java.util.List;
import org.hibernate.*;
import javax.swing.table.AbstractTableModel;
/**
 *
 * @author Dawidziu
 */
public class Connect {
    
    public void dodaj()
    {
        Players gracz = new Players();
        gracz.setName("Dawidziu");
        //gracz.setLevel(5);
        gracz.setPassword("12345g");
        
        
        Transaction tx = null;
        Session sesja = HibernateUtil.getSessionFactory().openSession();
        
        
        tx = sesja.beginTransaction();
        sesja.save(gracz);
        tx.commit();
        sesja.close();
       
    }
    
    public void odczyt()
    {
        Transaction tx = null;
        Session sesja = HibernateUtil.getSessionFactory().openSession();
        tx = sesja.beginTransaction();
        
        List odczyt = sesja.createQuery("FROM Players").list();
        for (Iterator it = odczyt.iterator(); it.hasNext();)
        {
            Players gracz = (Players) it.next();
            System.out.println(gracz.getName());
        }
        
        
        
        
    }        
}
