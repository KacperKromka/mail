package com.example.demo.data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;


@Repository
public interface MessageRepository extends CassandraRepository<Message, UUID> {

    @Value("SELECT * FROM message WHERE magic_number = ?")
    List<Message> findMessageByMagicNumber(int magicNumber);
    Slice<Message> findMessagesByEmail(String emailValue, Pageable pageable);
    
    void deleteByCreatedAtBefore(Date expiryTime);
    
}
