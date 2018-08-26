package com.tech.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tech.model.Message;

public interface MessageRepository extends JpaRepository<Message, Integer>{

}
