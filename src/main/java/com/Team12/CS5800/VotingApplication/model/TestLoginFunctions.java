package com.Team12.CS5800.VotingApplication.model;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;
import javax.mail.*;
import javax.activation.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class TestLoginFunctions {

    public static void sendEmailVerification(String clientEmail, String user, String authKeyLink){
        String username = "fswe.login.check.team12@gmail.com";
        String password = "fsweIsFun";


        String sender = "fswe.login.check.team12@gmail.com";
        String host = "smtp.gmail.com";

        Properties properties = System.getProperties();
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.host", sender);
        properties.put("mail.smtp.user",clientEmail);
        properties.put("mail.smtp.password", password);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(properties);
        MimeMessage message = new MimeMessage(session);

        try{

            message.setFrom(new InternetAddress(sender));
            InternetAddress toAddress = new InternetAddress(clientEmail);

            message.addRecipient(Message.RecipientType.TO, toAddress);

            message.setSubject("Register Your Account");

            String url = "http://localhost:8080/emailAuth?authKey=" + authKeyLink;
            
            String html = "Welcome, " + user + "! \nPlease <a href='" + url + "'> validate your email here</a>.";
            
            message.setText(html, "UTF-8", "html");


            Transport transport = session.getTransport("smtp");
            transport.connect(host, username, password);
            transport.sendMessage(message, message.getAllRecipients());

            transport.close();
            System.out.println("message sent");

        } catch (MessagingException error1){
            error1.printStackTrace();
        }


    }
    public static boolean passwordRequirementCheck(String passwordToCheck){
        //at least one capital, no space, contains at least 1 symbol
        Pattern p = Pattern.compile("[^A-Za-z0-9]");
        Matcher m = p.matcher(passwordToCheck);

        //checks that it contains at least 1 special character
        if(m.find() == false){
            return false;
        }
        else{
            p = Pattern.compile("\\s");
            m = p.matcher(passwordToCheck);
            //checks for whitespace
            if(m.find() == true){
                return false;
            }
            else{
                p = Pattern.compile("[A-Z]");
                m = p.matcher(passwordToCheck);

                if(m.find() == false){
                    return false;
                }
                else{
                    return true;
                }
            }
        }
    }

    public static boolean usernameRequirementCheck(String usernameToCheck){
        Pattern p = Pattern.compile("[^A-Za-z0-9]");
        Matcher m = p.matcher(usernameToCheck);

        //checks that the username doesn't contain any special characters
        if(usernameToCheck.length()>3) {
            if (m.find() == true) {
                return false;
            } else {
                p = Pattern.compile("\\s");
                m = p.matcher(usernameToCheck);

                //checks that the username does not have any white spaces
                if (m.find() == true) {
                    return false;
                }
                else{
                    return true;
                }
            }
        }else{
            return false;
        }


    }
    
    public static boolean socialSecurityNumberCheck(String ssn) {
    		Pattern p = Pattern.compile("^\\d{9}$");
    		Matcher m = p.matcher(ssn);
    		
    		boolean b = m.matches();
    		
    		return b;
    }
    
    public static void sendPasswordRecoveryEmail(String clientEmail, String requestCode){
    		String username = "fswe.login.check.team12@gmail.com";
        String password = "fsweIsFun";

        String sender = "fswe.login.check.team12@gmail.com";
        String host = "smtp.gmail.com";

        Properties properties = System.getProperties();
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.host", sender);
        properties.put("mail.smtp.user",clientEmail);
        properties.put("mail.smtp.password", password);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(properties);
        MimeMessage message = new MimeMessage(session);
        

        try{

            message.setFrom(new InternetAddress(sender));
            InternetAddress toAddress = new InternetAddress(clientEmail);

            message.addRecipient(Message.RecipientType.TO, toAddress);

            message.setSubject("Forgot Password");

            String url = "http://localhost:8080/forgotPassword?request=" + requestCode;
            
            String html = "There was a request to reset your password for Team 12's VottingApplication service. If this wasn't you, feel free to ignore this email. Otherwise, please <a href='" + url + "'> reset your password here.</a>.";
            
            message.setText(html, "UTF-8", "html");

            Transport transport = session.getTransport("smtp");
            transport.connect(host, username, password);
            transport.sendMessage(message, message.getAllRecipients());
            

            transport.close();

        } catch (MessagingException error1){
            error1.printStackTrace();
        }


    }

}
