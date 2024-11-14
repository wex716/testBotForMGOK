package org.example.service.logic;

import org.example.Buttons.InlineButtonsStorage;
import org.example.Buttons.InlineKeyboardsStorage;
import org.example.statemachine.State;
import org.example.statemachine.TransmittedData;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;


public class StartLogic {
    public SendMessage processWaitingCommandStart(String textFromUser, TransmittedData transmittedData) throws Exception {
        SendMessage messageToUser = new SendMessage();
        messageToUser.setChatId(transmittedData.getChatId());

        if (!textFromUser.equals("/start")) {
            messageToUser.setText("Ошибка запуска бота. Для старта пожалуйста введите /start");
            return messageToUser;
        }

        messageToUser.setText("Выберите то что вы хотите");
        messageToUser.setReplyMarkup(InlineKeyboardsStorage.getStartKeyboard());

        transmittedData.setState(State.WaitingQuestionsOrApplicationOrHistory);

        return messageToUser;
    }

    public SendMessage processWaitingQuestionsOrApplicationOrHistory(String textFromUser, TransmittedData transmittedData) throws Exception {
        SendMessage messageToUser = new SendMessage();
        messageToUser.setChatId(transmittedData.getChatId());

        if (!textFromUser.equals(InlineButtonsStorage.ShowQuestionsStart.getCallBackData()) && !textFromUser.equals(InlineButtonsStorage.SubmitApplication.getCallBackData()) && !textFromUser.equals(InlineButtonsStorage.SubmitHistory.getCallBackData())) {
            messageToUser.setText("Ошибка. Нажмите на кнопку.");
            return messageToUser;
        }

        if (textFromUser.equals(InlineButtonsStorage.ShowQuestionsStart.getCallBackData())) {

            messageToUser.setText("Выберите с чем возникла проблема/n Компьютер/n Принтер/n Проектор");


            messageToUser.setReplyMarkup(InlineKeyboardsStorage.getFirstShowKeyboard());

            transmittedData.setState(State.WaitingFirstViewCommands);


            return messageToUser;
        }
        return messageToUser;
    }
}
