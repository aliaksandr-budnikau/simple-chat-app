package ru.itsphere.simplechat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.itsphere.simplechat.controllers.forms.MessageForm;
import ru.itsphere.simplechat.controllers.validator.MessageFromValidator;
import ru.itsphere.simplechat.domain.Message;

import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/")
public class ChatController {
    public static final String MESSAGES_ATTRIBUTE = "messages";
    public static final String PAGE = "chat";
    public static final String MESSAGE_FROM_ATTRIBUTE = "messageForm";
    private List<Message> messages = new LinkedList<Message>();

    @Autowired
    private MessageFromValidator messageFromValidator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(messageFromValidator);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String dispatchToChatPage(ModelMap model) {
        model.addAttribute(MESSAGE_FROM_ATTRIBUTE, new MessageForm());
        model.addAttribute(MESSAGES_ATTRIBUTE, messages);
        return PAGE;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String showMessage(@Validated MessageForm messageForm, BindingResult bindingResult, ModelMap model) {
        if (bindingResult.hasErrors()) {
            return PAGE;
        }

        saveMessage(messageForm);
        return dispatchToChatPage(model);
    }

    private synchronized void saveMessage(MessageForm messageForm) {
        messages.add(new Message(messageForm.getAuthor(), messageForm.getText()));
    }
}
