package ru.itsphere.simplechat.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/")
public class ChatController {
    public static final String AUTHORS_ATTRIBUTE = "authors";
    public static final String MESSAGES_ATTRIBUTE = "messages";
    public static final String PAGE = "chat";
    private List<String> authors = new LinkedList<>();
    private List<String> messages = new LinkedList<>();

    @RequestMapping(method = RequestMethod.GET)
    public String dispatchToChatPage(ModelMap model) {
        model.addAttribute(AUTHORS_ATTRIBUTE, authors);
        model.addAttribute(MESSAGES_ATTRIBUTE, messages);
        return PAGE;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String showMessage(@RequestParam String author, @RequestParam String text, ModelMap model) {
        saveMessage(author, text);
        return dispatchToChatPage(model);
    }

    private synchronized void saveMessage(@RequestParam String author, @RequestParam String text) {
        authors.add(author);
        messages.add(text);
    }
}
