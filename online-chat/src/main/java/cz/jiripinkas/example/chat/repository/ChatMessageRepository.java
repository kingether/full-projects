package cz.jiripinkas.example.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cz.jiripinkas.example.chat.entity.Chatmessage;

@Repository
public interface ChatMessageRepository extends JpaRepository<Chatmessage, Integer>{

}
