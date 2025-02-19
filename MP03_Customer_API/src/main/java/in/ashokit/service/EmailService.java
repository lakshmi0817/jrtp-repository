package in.ashokit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;
import lombok.SneakyThrows;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@SneakyThrows
	public boolean sendMail(String to, String subject, String body) {
		
		MimeMessage message = mailSender.createMimeMessage();
		
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(body,true);
		
		mailSender.send(message);
		
		return true;
	}

}
