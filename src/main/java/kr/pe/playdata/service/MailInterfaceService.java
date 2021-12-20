package kr.pe.playdata.service;

public interface MailInterfaceService {
	
	public void sendEmail(String toAddress, String subject, String body);

}
