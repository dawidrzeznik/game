/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wojnaswiatow;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.User;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.apache.log4j.*;

/**
 *
 * @author Dawidziu
 */
public class MainFrame extends JFrame {

    private RunGame game;
    public JButton graj, pauza, koniec, powrot,loginButton,registerButton,resultsButton,tableButton,statButton,stat2Button;
    public JLabel userLabel,passwordLabel,background,errorLabel,resultsLabel;
    public JTextField userText ;
    public JPasswordField passwordText;
    private static String activeUser;
    public Image img;
    public JOptionPane msg;
    public JTable wyniki;
    public List odczyt2;
   

    
    public String getActiveUser() {
        return activeUser;
    }
    

    public void setActiveUser(String activeUser) {
        this.activeUser = activeUser;
    }
    
     
    protected MainFrame() {
       
        initComponents();
       
    }
    
    public void showMenu()
       {
              
       final JPanel content = new JPanel();
       
       graj = new JButton("Play"); 
       content.add(graj);
       
       pauza = new JButton("Pause");
       content.add(pauza);
       
       koniec = new JButton("Exit");
       content.add(koniec);
       
       powrot = new JButton("Resume");
       content.add(powrot);
       
       resultsButton = new JButton ("Results");
       content.add(resultsButton);
       
       statButton = new JButton ("Scores");
       content.add(statButton);
       
       stat2Button = new JButton ("Games");
       content.add(stat2Button);

       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       this.setSize(200,200);
       this.setLocation(50,50);
       this.setVisible(true);
       this.setContentPane(content);
       
       graj.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent arg0){
            
            game = new RunGame();
            
        }
        });
       
        pauza.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent arg0){
            game.PauseGame();
           
        }
        });
        
        koniec.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent arg0){
            game.ExitGame();
        }
        });
        
        powrot.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent arg0){
            game.ResumeGame();
        }
        });
        
        resultsButton.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent arg0){
            //content.setVisible(false);
            showResults();
        }
        });
        
         statButton.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent arg0){
            //content.setVisible(false);
            showStats();
        }
        });
         
         stat2Button.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent arg0){
            //content.setVisible(false);
            showStats2();
        }
        });
         
       }

    public void showResults()
    {
       new Lista().setVisible(true);
    }
    
    public void showStats()
    {
        final ScoreChart demo = new ScoreChart();
        demo.pack();
        demo.setVisible(true);
    }
    
    public void showStats2()
    {
       final GamesChart games = new GamesChart();
       games.pack();
       games.setVisible(true);
        
    }
    
    private void initComponents()
    {
        Layout lay1 = new PatternLayout("[%p] %c - %m - Data wpisu: %d %n");
        Appender app1 = null;
        try {
             app1 = new FileAppender(lay1,"D:/log_game.txt");
            } catch(IOException ex) {
        }

        BasicConfigurator.configure(app1);
        final Logger logger = Logger.getRootLogger();
        //logger.debug("Uruchomiono gre");
        logger.setLevel(Level.TRACE);
        
        final JPanel login = new JPanel();
        login.setLayout(null);

        userLabel = new JLabel("User");
        userLabel.setBounds(10, 10, 80, 25);
        login.add(userLabel);

        userText = new JTextField(20);
        userText.setBounds(100, 10, 160, 25);
        login.add(userText);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 40, 80, 25);
        login.add(passwordLabel);
        
        errorLabel = new JLabel();
        errorLabel.setBounds(10, 120, 200, 25);
        login.add(errorLabel);

        passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 40, 160, 25);
        login.add(passwordText);

        loginButton = new JButton("login");
        loginButton.setBounds(10, 80, 80, 25);
        login.add(loginButton);

        registerButton = new JButton("register");
        registerButton.setBounds(180, 80, 80, 25);
        login.add(registerButton);

       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       this.setSize(300,200);
       this.setLocation(50,50);
       this.setVisible(true);
       this.setContentPane(login); 
        
        loginButton.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent arg0){
            
            Transaction tx = null;
            Session sesja = HibernateUtil.getSessionFactory().openSession();
            tx = sesja.beginTransaction();

            Query zapytanie = sesja.createQuery("SELECT password FROM Players WHERE name='"+userText.getText()+"'");
            Object odczyt = zapytanie.uniqueResult();
            String haslo = passwordText.getText();

            Query zapytanie2 = sesja.createQuery("SELECT name FROM Players");
            odczyt2 = zapytanie2.list();
            if (odczyt2.contains(userText.getText()))
            {
                 if (odczyt.equals(haslo))
                {

                    setActiveUser(userText.getText());
                    login.setVisible(false);
                    showMenu();
                    logger.info("Zalogowano uzytkownika");
                }

                else
                {
                    errorLabel.setText("Złe hasło!");
                    logger.error("Nieudane logowanie");
                }   
            }
            else
            {
                errorLabel.setText("Nie ma takiego użytkownika!");
                logger.error("Wprowadzono błednego użytkownika");
            }
        }
        });
        
        registerButton.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent arg0){
            Transaction tx = null;
            Session sessionRegister = HibernateUtil.getSessionFactory().openSession();
            tx = sessionRegister.beginTransaction();
            
            Query zapytanie3 = sessionRegister.createQuery("SELECT name FROM Players");
            List odczyt3 = zapytanie3.list();
            
            if (((userText.getText()).equals("")) || (odczyt3.contains(userText.getText())))
            {
                errorLabel.setText("Błędny użytkownik");
                sessionRegister.close();
            }
            else
            {
            sessionRegister.close();
            String mail = msg.showInputDialog("Podaj swój adres e-mail");
            Pattern wzorMail = Pattern.compile(".+@.+\\.[a-z]+");
            Matcher sprawdzMail = wzorMail.matcher(mail);
            boolean matchFound = sprawdzMail.matches();
            if (matchFound)
            {
                msg.showMessageDialog(login, "Poprawny adres");
                Players user = new Players();
                //user.setPlayerId(1);
                user.setName(userText.getText());
                user.setPassword(passwordText.getText());
                user.setMail(mail);
                Transaction tx2 = null;
                Session sesja = HibernateUtil.getSessionFactory().openSession();
                tx = sesja.beginTransaction();
                sesja.save(user);
                tx.commit();
                sesja.close();
                logger.info("Dodano pomyslnie nowego uzytkownika");
                
            }
            else
            {
                msg.showMessageDialog(login, "Zly mail");
            }

        }
        }
        });
    }
 }
    

