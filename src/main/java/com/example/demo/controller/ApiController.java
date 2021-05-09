package com.example.demo.controller;

import com.example.demo.data.Message;
import com.example.demo.data.MessageRepository;
import com.example.demo.service.MailService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    private MailService mailSender;
    private MessageRepository messageRepository;

    public ApiController(MailService mailSender, MessageRepository messageRepository){
        this.mailSender = mailSender;
        this.messageRepository = messageRepository;
    }


    @PostMapping("/message")
    public Message message(@RequestBody Message newMessage){
        return messageRepository.save(newMessage);
    }

    @PostMapping("/send")
    public List<Message> send(@RequestBody int magicNumber){
        List<Message> toSend = new ArrayList<>(messageRepository.findMessageByMagicNumber(magicNumber));
        for(Message message : toSend){
            mailSender.sendEmail(
                    message.getEmail(),
                    message.getTitle(),
                    message.getContent()
            );
        }
        return toSend;
    }

    @GetMapping("/messages/{emailValue}")
    public Slice<Message> page(@PathVariable String emailValue){
        return messageRepository.findMessagesByEmail(emailValue, PageRequest.of(1, 5));
    }
}
