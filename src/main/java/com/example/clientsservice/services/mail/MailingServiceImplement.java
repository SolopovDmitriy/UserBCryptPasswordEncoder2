package com.example.clientsservice.services.mail;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailingServiceImplement {
    private static final String SUBJECT="Account info";
    private static final String MSG_TEMPLATE="We detected some suspicious activity from your account. \n%s";

    @Autowired
    private JavaMailSender sender;

    public void sendAccountInfo(final String toAdr, final String info){
        SimpleMailMessage smm = new SimpleMailMessage();
        smm.setTo(toAdr);
        smm.setSubject(SUBJECT);
        smm.setText(String.format(MSG_TEMPLATE, info));
        sender.send(smm);
    }


    public void sendAccountsInfo(final String[] toAdr, final String info){
        SimpleMailMessage smm = new SimpleMailMessage();
        smm.setTo(toAdr);
        smm.setSubject(SUBJECT);
        smm.setText(String.format(MSG_TEMPLATE, info));
        sender.send(smm);
    }
}
