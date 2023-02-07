package com.damian.apexedu.smtp;

import com.damian.apexedu.util.AlertSender;
import javafx.scene.control.Alert;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailToStudent{
    public static void sendMail(String email,String name,String sid,String username,String password) {
        // Recipient's email ID needs to be mentioned.
        String to = email;

        // Sender's email ID needs to be mentioned
        String from = "drpeiris3@gmail.com";

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("drpeiris3@gmail.com", "mtilvgujflfzpqjp");

            }

        });

        // Used to debug SMTP issues
        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("Thank you for registering with us!");

            // Now set the actual message
            message.setText("Student ID: " + sid + "\n" + "Student Name :" + name+"\n"+"Student Username :  "+username+"\n"+"Password : "+password);

            System.out.println("sending...");
            // Send message
            Transport.send(message);
        } catch (MessagingException mex) {
            AlertSender.sendAlert(mex.getLocalizedMessage(),"ERROR.", Alert.AlertType.ERROR);
        }
    }


}

