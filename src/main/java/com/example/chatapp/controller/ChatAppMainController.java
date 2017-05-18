package com.example.chatapp.controller;

import com.example.chatapp.model.Logging;
import com.example.chatapp.model.NameOfUser;
import com.example.chatapp.service.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.NoHandlerFoundException;

@Controller
public class ChatAppMainController {

  @Autowired
  UserRepository repository;

  String chatAppUniqueId;
  String chatAppPeerAddress;

  public ChatAppMainController() {
    this.chatAppUniqueId = System.getenv("CHAT_APP_UNIQUE_ID");
    this.chatAppPeerAddress = System.getenv("CHAT_APP_PEER_ADDRESSS");
  }

  @ExceptionHandler(value = NoHandlerFoundException.class)
  public String notFound() {
    System.err.println("ERROR");
    return "redirect:/";
  }

  @RequestMapping(value = "/")
  public String mainPage() {

    String currentLogLevel = System.getenv("CHAT_APP_LOGLEVEL");

    if (currentLogLevel != null && currentLogLevel.equals("INFO")) {
      System.out.println(new Logging("INFO", "/", "GET", ""));
    }
    return "index";
  }

  @GetMapping(value = "/enter")
  public String registerPage() {
    return "register";
  }

  @PostMapping(value = "/enter")
  public String addNewUser(String userentry) {
    if (userentry.equals("")) {
      return "register-error";
    }
    repository.save(new NameOfUser(userentry));
    return "redirect:/";
  }
}
