package com.corporation.repository.service;

import com.corporation.model.service.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {

}
