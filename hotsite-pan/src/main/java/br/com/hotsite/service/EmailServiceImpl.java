package br.com.hotsite.service;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class EmailServiceImpl implements EmailService {

	@Override
	public void enviaEmailConfirmacaoCadastroParticipante(String emailTo) {
		Email email = new SimpleEmail();
		email.setHostName("smtp.gmail.com");
		String authuser = "surpreendapan@dagaz.tur.br";
		String authpwd = "dagaz102030";
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator(authuser, authpwd));
		email.setSSLOnConnect(true);
		try {
			email.setFrom("surpreendapan@dagaz.tur.br");
			email.setSubject("Confirmação de participação");
			email.setMsg("Você confirmou a participação no evento com sucesso!");
			email.addTo(emailTo);
			email.send();
		} catch (EmailException e) {
			// TODO logar mensagem
			// e.printStackTrace();
		}
	}

}
