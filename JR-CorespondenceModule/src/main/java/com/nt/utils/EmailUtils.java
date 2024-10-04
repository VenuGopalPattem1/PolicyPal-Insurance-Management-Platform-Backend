package com.nt.utils;

import java.io.File;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtils {
	@Autowired
	private JavaMailSender mail;
	
	public void senMail(String toMail,String subject,String body,File file) throws Exception {
		MimeMessage msg = mail.createMimeMessage();
		MimeMessageHelper help = new MimeMessageHelper(msg,true);
		help.setTo(toMail);
		help.setSentDate(new Date());
		help.setSubject(subject);
		help.setText(body,true);
		help.addAttachment(file.getName(), file);
		mail.send(msg);
	}
}
