package org.example.service.logic;

import org.example.Util.NumberUtil;
import org.example.statemachine.State;
import org.example.statemachine.TransmittedData;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class ApplicationLogic {

    public SendMessage processWaitingInputCabinetNumber(String textFromUser, TransmittedData transmittedData) throws Exception {
        SendMessage messageToUser = new SendMessage();
        messageToUser.setChatId(transmittedData.getChatId());

        if (!NumberUtil.isNumber(textFromUser)) {
            messageToUser.setText("Ошибка ввода номера кабинета. Вы ввели не арабское число");
            return messageToUser;
        }

        int cabinetNumber = NumberUtil.stringToIntNumber(textFromUser);

        if (!NumberUtil.isNumberInRange(cabinetNumber, 1, 1100)) {
            messageToUser.setText("Ошибка ввода номера кабинета. Повторите ввод");
            return messageToUser;
        }

        transmittedData.getDataStorage().add("cabinetNumber", textFromUser);

        messageToUser.setText("Номер кабинета успешно записан. Теперь введите ФИО");

        transmittedData.setState(State.WaitingInputFullName);

        return messageToUser;
    }


    public SendMessage processWaitingInputFullName(String textFromUser, TransmittedData transmittedData) throws Exception {
        SendMessage messageToUser = new SendMessage();
        messageToUser.setChatId(transmittedData.getChatId());


        if (textFromUser.isEmpty() || textFromUser.length() > 50) {
            messageToUser.setText("Ошибка ввода ФИО. Повторите ввод");
            return messageToUser;
        }

        transmittedData.getDataStorage().add("FIO", textFromUser);
        messageToUser.setText("ФИО успешно записан. Теперь введите номер телефона");
        transmittedData.setState(State.WaitingInputNumberPhone);

        return messageToUser;
    }


    public SendMessage processWaitingInputNumberTelephone(String textFromUser, TransmittedData transmittedData) throws Exception {
        SendMessage messageToUser = new SendMessage();
        messageToUser.setChatId(transmittedData.getChatId());

        if (!NumberUtil.isNumber(textFromUser)) {
            messageToUser.setText("Ошибка ввода номера телефона. Вы ввели не арабское число");
            return messageToUser;
        }
//
//        int numberPhone = NumberUtil.stringToIntNumber(textFromUser);
//
//
//        if (!NumberUtil.isNumberInRange(numberPhone, 9, 12)) {
//            messageToUser.setText("Ошибка ввода номера телефона. Введите номер телефона");
//            return messageToUser;
//        }
//
        transmittedData.getDataStorage().add("numberPhone", textFromUser);

        messageToUser.setText("Номер телефона успешно записан. Теперь опишите проблему");

        transmittedData.setState(State.WaitingInputFullName);

        return messageToUser;
    }


    public SendMessage processWaitingDescriptionProblem(String textFromUser, TransmittedData transmittedData) throws Exception {
        SendMessage messageToUser = new SendMessage();
        messageToUser.setChatId(transmittedData.getChatId());

        boolean description = NumberUtil.isTextWithPunctuation(textFromUser);

        if (textFromUser.isEmpty() || textFromUser.length() > 500) {
            messageToUser.setText("Ошибка описания проблемы. Повторите ввод более 1 символа");
            return messageToUser;
        }

        transmittedData.getDataStorage().add("description", textFromUser);
        messageToUser.setText("Описание проблемы успешно записано. Добавьте фото к описанию проблемы");
        transmittedData.setState(State.WaitingInputNumberPhone);

        return messageToUser;
    }

    public SendMessage processWaitingAddPhoto(String textFromUser, TransmittedData transmittedData) throws Exception {
        SendMessage messageToUser = new SendMessage();


        return messageToUser;
    }
}

