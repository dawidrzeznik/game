/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wojnaswiatow;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.log4j.*;
/**
 *
 * @author Dawidziu
 */
public interface Mail {
    
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class SendMail {

private static final String HOST = "smtp.gmail.com";
private static final int PORT = 465;

// Adres email osby która wysyła maila

private static final String FROM = "dawid.rzeznik@gmail.com";

// Hasło do konta osoby która wysyła maila

private static final String PASSWORD = "niezapominajka";

// Adres email osoby do której wysyłany jest mail

//private static final String TO = "";

// Temat wiadomości

private static final String SUBJECT = "SpaceInv";

// Treść wiadomości

//private static final String CONTENT = "Testowy mail do Lukasza";



public void send(String to,String content) throws MessagingException {

 

Properties props = new Properties();

props.put("mail.transport.protocol", "smtps");

props.put("mail.smtps.auth", "true");


// Inicjalizacja sesji

Session mailSession = Session.getDefaultInstance(props);

 

// ustawienie debagowania, jeśli nie chcesz oglądać logów to usuń

// linijkę poniżej lub zmień wartość na false

mailSession.setDebug(true);

 

// Tworzenie wiadomości email

MimeMessage message = new MimeMessage(mailSession);

message.setSubject(SUBJECT);

message.setContent(content, "text/plain; charset=ISO-8859-2");

message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

 

Transport transport = mailSession.getTransport();

transport.connect(HOST, PORT, FROM, PASSWORD);

 

// wysłanie wiadomości

transport.sendMessage(message, message

.getRecipients(Message.RecipientType.TO));

transport.close();


}

//public static void main(String[] args) {
//try {
//new SendMail().send();
//} catch (MessagingException e) {
//e.printStackTrace();
//}
//}
}



    
}
