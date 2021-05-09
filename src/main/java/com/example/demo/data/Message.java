package com.example.demo.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.UUID;

import javax.persistence.GeneratedValue;

import org.hibernate.annotations.Entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.Table;

import com.datastax.oss.driver.api.core.uuid.Uuids;

@Entity
@Table(value = "message")
@Data
@AllArgsConstructor
public class Message {
	
	@Id
	@GeneratedValue
	@Column("id")
	UUID id =  Uuids.timeBased();

    @Column("email")
    private  String email;

    @Column("title")
    private  String title;

    @Column("content")
    private  String content;

    @Column("magic_number")
    private int magicNumber;
}
