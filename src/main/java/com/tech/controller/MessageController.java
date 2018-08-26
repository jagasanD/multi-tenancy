package com.tech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tech.model.Message;
import com.tech.repository.MessageRepository;

@RestController
@RequestMapping("/messages")
public class MessageController {

	@Autowired
	private MessageRepository repository;

    public MessageController(MessageRepository repository){
        this.repository = repository;
    }

    @GetMapping("/gets")
    public Page<Message> getMessages(Pageable pageable){
        return repository.findAll(pageable);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Message createMessage(@RequestBody Message message){
        return repository.save(message);
    }

    @DeleteMapping("/delete/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMessage(@RequestParam Integer uuid){
        repository.deleteById(uuid);
    }
	
	
	
}
