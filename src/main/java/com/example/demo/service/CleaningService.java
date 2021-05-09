package com.example.demo.service;

import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;

import org.springframework.stereotype.Service;

import com.example.demo.data.MessageRepository;

@Service
public class CleaningService extends TimerTask {
	
	private MessageRepository messageRepository;
	
	public CleaningService(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}

	@Override
	public void run() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE , -5);
		Date expiry = calendar.getTime();
		
		messageRepository.deleteByCreatedAtBefore(expiry);
	}

}
