/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.linkdin.email;
import java.security.GeneralSecurityException;
import java.util.Random;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import org.apache.commons.mail.*;

/**
 *
 * @author leonardyuan
 */
public class EmailSection {
    private static String USERNAME = "1035776404@qq.com";
    private static String PASSWORD = "xsqoyxjbuttrbbcb";
    private String confirmCode;

    public EmailSection() {
        this.confirmCode = newConfirmCode();
        
    }
    
    private String newConfirmCode(){
        StringBuilder str=new StringBuilder();
        Random random=new Random();

        for(int i=0;i<8;i++){
            str.append(random.nextInt(10));
        }
        return str.toString();
    }
    
    public boolean sendEmail(String emailaddress) throws EmailException, GeneralSecurityException, NoSuchProviderException, MessagingException, Exception{
        Email email = new SimpleEmail();
        email.setSSLOnConnect(true);
        email.setHostName("smtp.qq.com");
        email.setSslSmtpPort("465");
        email.setAuthenticator(new DefaultAuthenticator(USERNAME, PASSWORD));
        email.setCharset("UTF-8");
        try{
            email.setFrom(USERNAME);
            email.setSubject("Email CONFIRM");
            email.setMsg("Confirm Code: " + confirmCode);
            email.addTo(emailaddress);
            email.send();
        }catch(Exception e) {
            System.out.println("Error");
            e.printStackTrace();
            return false;
        }
        
        return true;
    }

    public static String getUSERNAME() {
        return USERNAME;
    }

    public static void setUSERNAME(String USERNAME) {
        EmailSection.USERNAME = USERNAME;
    }

    public static String getPASSWORD() {
        return PASSWORD;
    }

    public static void setPASSWORD(String PASSWORD) {
        EmailSection.PASSWORD = PASSWORD;
    }

    public String getConfirmCode() {
        return confirmCode;
    }

    public void setConfirmCode(String confirmCode) {
        this.confirmCode = confirmCode;
    }
    
}
