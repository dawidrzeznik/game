package wojnaswiatow;

import java.awt.Canvas;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.awt.Font;
//import java.awt.TexturePaint;
import java.awt.Rectangle;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import org.apache.log4j.Appender;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.PatternLayout;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
//import javax.swing.JButton;
//import javax.swing.JMenu;
//import javax.swing.JMenuBar;
//import javax.swing.JMenuItem;
//import javax.swing.JRadioButtonMenuItem;

public class WojnaSwiatow extends Canvas implements Stage, KeyListener, Mail{
    
    public int SZYBKOSC;
    public long usedTime;
    public BufferStrategy strategia;
    private SpriteCache spriteCache;
    private ArrayList actors;
    private Player player;
    private boolean gameEnded=false;
    private boolean pause=false;
    //private SoundCache soundCache;
    //private BufferedImage background, backgroundTile;
    //private int backgroundY;
    
       
    
    public void setPause(boolean pause) {
        this.pause = pause;
    }

    
    
    public void setGameEnded(boolean gameEnded) {
        this.gameEnded = gameEnded;
    }

    public boolean isGameEnded() {
        return gameEnded;
    }

    public int getSZYBKOSC() {
        return SZYBKOSC;
    }

    public void setSZYBKOSC(int SZYBKOSC) {
        this.SZYBKOSC = SZYBKOSC;
    }
    
    
    public WojnaSwiatow() {
        
        spriteCache = new SpriteCache();
        
        JFrame okno = new JFrame(".: Gra :.");
        JPanel panel = (JPanel)okno.getContentPane();
        setBounds(0,0,Stage.SZEROKOSC,Stage.WYSOKOSC);
        panel.setPreferredSize(new Dimension(Stage.SZEROKOSC,Stage.WYSOKOSC));
        panel.setLayout(null);
        panel.add(this);
        okno.setBounds(0,0,Stage.SZEROKOSC,Stage.WYSOKOSC);
        okno.setVisible(true);
        okno.addWindowListener( new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        okno.setResizable(false);
        okno.setLocationRelativeTo(null);
        
        createBufferStrategy(2);
        strategia = getBufferStrategy();
        requestFocus();
        addKeyListener(this);
        BufferedImage cursor = spriteCache.createCompatible(10,10,Transparency.BITMASK);
        Toolkit t = Toolkit.getDefaultToolkit();
        Cursor c = t.createCustomCursor(cursor,new Point(5,5),"null");
        setCursor(c);
        setIgnoreRepaint(true);
        
        //logger
         Layout lay1 = new PatternLayout("[%p] %c - %m - Data wpisu: %d %n");
        Appender app1 = null;
        try {
             app1 = new FileAppender(lay1,"D:/log_gra.txt");
            } catch(IOException ex) {
        }

        BasicConfigurator.configure(app1);
        final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getRootLogger();
        logger.debug("Uruchomiono gre");
        logger.setLevel(org.apache.log4j.Level.TRACE);
        
        
    }
    public void start() throws MessagingException{
        this.game();
    }

   /* public SoundCache getSoundCache() {
        return soundCache;
    }*/
    public void checkCollisions() {
        Rectangle playerBounds = player.getBounds();
        for (int i = 0; i < actors.size(); i++) {
            Actor a1 = (Actor)actors.get(i);
            Rectangle r1 = a1.getBounds();
            if (r1.intersects(playerBounds)) {
                player.collision(a1);
                a1.collision(player);
            }
            for (int j = i+1; j < actors.size(); j++) {
                Actor a2 = (Actor)actors.get(j);
                Rectangle r2 = a2.getBounds();
                if (r1.intersects(r2)) {
                    a1.collision(a2);
                    a2.collision(a1);
                }
            }
        }
    }
    
    
    public void initWorld() {
        actors = new ArrayList();
        for (int i = 0; i < 10; i++){
            Monster m = new Monster(this);
            m.setX( (int)(Math.random()*Stage.SZEROKOSC) );
            m.setY( i*20 );
            m.setVx( (int)(Math.random()*3)+1 );
            actors.add(m);
        }
        player = new Player(this);
        player.setX(Stage.SZEROKOSC/2);
        player.setY(Stage.WYSOKOSC - 2*player.getHeight());
       /* soundCache.loopSound("musica.wav");
        
        backgroundTile = spriteCache.getSprite("oceano.gif");
        background = spriteCache.createCompatible(
                Stage.SZEROKOSC,
                Stage.WYSOKOSC+backgroundTile.getHeight(),
                Transparency.OPAQUE);
        Graphics2D g = (Graphics2D)background.getGraphics();
        g.setPaint( new TexturePaint( backgroundTile,
                new Rectangle(0,0,backgroundTile.getWidth(),backgroundTile.getHeight())));
        g.fillRect(0,0,background.getWidth(),background.getHeight());
        backgroundY = backgroundTile.getHeight();*/
        
    }
    
    
    public void paintShields(Graphics2D g) {
        g.setPaint(Color.red);
        g.fillRect(150,Stage.WYSOKOSC_GRY,Player.MAX_SHIELDS,30);
        g.setPaint(Color.blue);
        g.fillRect(150+Player.MAX_SHIELDS-player.getShields(),Stage.WYSOKOSC_GRY,player.getShields(),30);
        g.setFont(new Font("Arial",Font.BOLD,20));
        g.setPaint(Color.green);
        //g.drawString("Shields",170,Stage.WYSOKOSC_GRY+20);
    }
    
    public void paintScore(Graphics2D g) {
        g.setFont(new Font("Arial",Font.BOLD,20));
        g.setPaint(Color.red);
        g.drawString("Wynik:",20,Stage.WYSOKOSC_GRY + 20);
        g.setPaint(Color.red);
        g.drawString(player.getScore()+"",100,Stage.WYSOKOSC_GRY  + 20);
    }
    
    public void paintAmmo(Graphics2D g) {
        int xBase = 280+Player.MAX_SHIELDS+10;
        for (int i = 0; i < player.getClusterBombs();i++) {
            BufferedImage bomb = spriteCache.getSprite("bombUL.gif");
            g.drawImage( bomb ,xBase+i*bomb.getWidth(),Stage.WYSOKOSC_GRY,this);
        }
    }
    
//    public void paintfps(Graphics2D g) {
//        g.setFont( new Font("Arial",Font.BOLD,12));
//        g.setColor(Color.white);
//        if (usedTime > 0)
//            g.drawString(String.valueOf(1000/usedTime)+" fps",Stage.SZEROKOSC-50,Stage.WYSOKOSC_GRY);
//        else
//            g.drawString("--- fps",Stage.WIDTH-50,Stage.WYSOKOSC_GRY);
//    }
    
    public void paintStatus(Graphics2D g) {
        paintScore(g);
        paintShields(g);
        //paintAmmo(g);
        //paintfps(g);
    }
    
    
    public void paintWorld() {
        /*Graphics2D g = (Graphics2D)strategia.getDrawGraphics();
        g.drawImage( background,
                0,0,Stage.SZEROKOSC,Stage.WYSOKOSC,
                0,backgroundY,Stage.SZEROKOSC,backgroundY+Stage.WYSOKOSC,this);
        for (int i = 0; i < actors.size(); i++) {
            Actor m = (Actor)actors.get(i);
            m.paint(g);
        }
        player.paint(g);
        paintStatus(g);
        strategia.show();*/
        Graphics g = strategia.getDrawGraphics();
    Graphics2D gg = (Graphics2D)strategia.getDrawGraphics();
    g.setColor(Color.BLACK);
    g.fillRect(0,0,getWidth(),getHeight());
    for (int i = 0; i < actors.size(); i++)
    {
        Actor m = (Actor)actors.get(i);
        m.paint((Graphics2D) g);
    }
    player.paint((Graphics2D) g);
    g.setColor(Color.red);
    paintStatus(gg);
    strategia.show();
    }
    
    public void addActor(Actor a) {
        actors.add(a);
    }
    
    public void updateWorld() {
        int i = 0;
        while (i < actors.size()) {
            Actor m = (Actor)actors.get(i);
            if (m.isMarkedForRemoval()) {
                actors.remove(i);
            } else {
                m.act();
                i++;
            }
        }
        player.act();
    }
    
    public Player getPlayer() {
        return player;
    }
    
    public void keyPressed(KeyEvent e) {
        player.keyPressed(e);
    }
    public void keyReleased(KeyEvent e) {
        player.keyReleased(e);
    }
    public void keyTyped(KeyEvent e) {}
    
    public SpriteCache getSpriteCache() {
        return spriteCache;
    }
    
    public void gameOver() {
        gameEnded = true;
    }
    
    public void paintGameOver() {
        Graphics2D g = (Graphics2D)strategia.getDrawGraphics();
        g.setColor(Color.white);
        g.setFont(new Font("Arial",Font.BOLD,20));
        g.drawString("GAME OVER",Stage.SZEROKOSC/2-50,Stage.WYSOKOSC/2);
        g.drawString("Wynik: "+player.getScore(),Stage.SZEROKOSC/2-50,Stage.WYSOKOSC/2+20);
        strategia.show();
         
        
    }
    
    public String readFile(String filename)
{
   String content = null;
   File file = new File(filename); //for ex foo.txt
   try {
       FileReader reader = new FileReader(file);
       char[] chars = new char[(int) file.length()];
       reader.read(chars);
       content = new String(chars);
       reader.close();
   } catch (IOException e) {
       e.printStackTrace();
   }
   return content;
}
    
    public void saveFile(String filename) throws FileNotFoundException, IOException, MessagingException
    {
        FileWriter file = new FileWriter(filename, true);
        PrintWriter out;
        out = new PrintWriter(file);
        MainFrame obj = new MainFrame();
        
        out.println(obj.getActiveUser()+" "+player.getScore());
        out.close();
        
        Transaction tx = null;
        Session sesja = HibernateUtil.getSessionFactory().openSession();
        tx = sesja.beginTransaction();
        Query zmiana = sesja.createQuery("UPDATE Players SET games=games+1 WHERE name='"+obj.getActiveUser()+"'");
        zmiana.executeUpdate();
        tx.commit();
        sesja.close();
        saveResult(obj.getActiveUser());
    }
    
    public void saveResult(String name) throws MessagingException
    {
        Transaction tx = null;
        Session sesja = HibernateUtil.getSessionFactory().openSession();
        tx = sesja.beginTransaction();
        
        Query zapytanie = sesja.createQuery("SELECT score FROM Players WHERE name='"+name+"'");
        Object odczyt = zapytanie.uniqueResult();

        String wynik=odczyt.toString();
        int wynikInt=Integer.parseInt(wynik);
        System.out.println(name);
        System.out.println(wynikInt);
 
        if((player.getScore())>wynikInt)
                {
                   Query zmiana = sesja.createQuery("UPDATE Players SET score="+player.getScore()+" WHERE name='"+name+"'");
                   zmiana.executeUpdate();
                   Query zmianaPoziomu = sesja.createQuery("UPDATE Players SET level="+SZYBKOSC+" WHERE name='"+name+"'");
                   zmianaPoziomu.executeUpdate();
                   Query pobierzMail = sesja.createQuery("SELECT mail FROM Players WHERE name='"+name+"'");
                   Object odczytMail = pobierzMail.uniqueResult();
                   String adresMail = odczytMail.toString();
                   String trescMail = "Gratulacje "+name+" twój nowy rekord to "+player.getScore()+" punktów!";
                   
                   System.out.println(odczytMail+" -to jest mail");
                   System.out.println(trescMail+" -to jest trescMaila");
                   new SendMail().send(adresMail,trescMail);
                   
                }
        
        tx.commit();
        sesja.close();
    }
    
    
    public void game() throws MessagingException {
        try {
            usedTime=1000;
            SZYBKOSC=Integer.parseInt(readFile("src/poziom.txt"));
            initWorld();
            while (isVisible() && !gameEnded)
            {
                while (isVisible() && !gameEnded && !pause) {
                    
                    updateWorld();
                    checkCollisions();
                    paintWorld();
                    
                    try
                    {
                        Thread.sleep(SZYBKOSC);
                    }
                    catch (InterruptedException e) {}
                    //}}
                }
            }
            paintGameOver();
            saveFile("src/wyniki.txt");
        } catch (IOException ex) {
            Logger.getLogger(WojnaSwiatow.class.getName()).log(Level.SEVERE, null, ex);
        }
        
  
     }
//    public static void main(String[] args) throws FileNotFoundException{
//        
//        //EventQueue.in
//        new MainFrame();
//        //(new Thread(new WojnaSwiatow())).start();
//
//        //inv.game();
//    }
}