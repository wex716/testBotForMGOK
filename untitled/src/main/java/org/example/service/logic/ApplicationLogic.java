package org.example.service.logic;

import org.example.statemachine.TransmittedData;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class ApplicationLogic {

    public SendMessage processWaitingSubmitApplication(String textFromUser, TransmittedData transmittedData) throws Exception {
        SendMessage messageToUser = new SendMessage();


        return messageToUser;
    }

    public SendMessage processWaitingViewApplicationHistory(String textFromUser, TransmittedData transmittedData) throws Exception {
        SendMessage messageToUser = new SendMessage();


        return messageToUser;

    }
}
