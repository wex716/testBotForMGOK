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
            messageToUser.setText("Здравстуйте! Это теханическая поддержка МГОК.\n \nДанный бот призван упростить взаимодействие преподавателей и Сис админов\n \nБудем рады помочь решить проблему, которая у вас возникла\n \nДля того, чтобы бот начал работу, нажмите /start");
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

        if (!textFromUser.equals(InlineButtonsStorage.ShowQuestionsStart.getCallBackData()) && !textFromUser.equals(InlineButtonsStorage.ViewProblemComputer.getCallBackData()) && !textFromUser.equals(InlineButtonsStorage.ViewProblemPrinter.getCallBackData()) && !textFromUser.equals(InlineButtonsStorage.ViewProblemProjector.getCallBackData())) {
            messageToUser.setText("Ошибка. Нажмите на кнопку.");
            return messageToUser;
        }


        if (textFromUser.equals(InlineButtonsStorage.ShowQuestionsStart.getCallBackData())) {
            messageToUser.setText("Выберите, с чем возникла проблема");
            messageToUser.setReplyMarkup(InlineKeyboardsStorage.getProblemSystemShowKeyboard());
            return messageToUser;
        }

        if (!textFromUser.equals(InlineButtonsStorage.ViewProblemComputer.getCallBackData())) {
            transmittedData.setState(State.WaitingViewProblemComputer);
        } else if (!textFromUser.equals(InlineButtonsStorage.ViewProblemPrinter.getCallBackData())) {
            transmittedData.setState(State.WaitingViewProblemPrinter);
        } else if (!textFromUser.equals(InlineButtonsStorage.ViewProblemProjector.getCallBackData())) {
            transmittedData.setState(State.WaitingViewProblemProjector);
            return messageToUser;
        }
        return messageToUser;
    }
}
