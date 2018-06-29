package com.nelioalves.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.nelioalves.cursomc.domain.Pedido;

public interface EmailService {

	void sendOrderConfigmationEmail(Pedido pedido);
	
	void sendEmail(SimpleMailMessage msg);
}
