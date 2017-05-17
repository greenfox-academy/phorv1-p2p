package com.example.chatapp.service;

import com.example.chatapp.model.Username;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserRepository extends CrudRepository<Username, Long> {
}
