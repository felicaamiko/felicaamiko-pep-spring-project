package com.example.repository;

import com.example.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//time and timestamp might not be used...
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findMessagesByPostedBy(Integer postedBy);
}
