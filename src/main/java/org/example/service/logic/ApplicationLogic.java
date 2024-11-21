package org.example.service.logic;

import org.example.Buttons.InlineButtonsStorage;
import org.example.Buttons.InlineKeyboardsStorage;
import org.example.statemachine.DataStorage;
import org.example.statemachine.State;
import org.example.statemachine.TransmittedData;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class ApplicationLogic {

    public SendMessage processWaitingInputCabinetNumber(String textFromUser, TransmittedData transmittedData) throws Exception {
        SendMessage messageToUser = new SendMessage();
        messageToUser.setChatId(transmittedData.getChatId());

        int minLength = 3;
        int maxLength = 6;
        String cleanedNumber = textFromUser.replaceAll("[^0-9]", "");

        if (cleanedNumber.isEmpty()) {
            messageToUser.setText("Ошибка ввода номера кабинета. Введите целое число.");
            return messageToUser;
        }

        if (cleanedNumber.length() < minLength || cleanedNumber.length() > maxLength) {
            messageToUser.setText("Ошибка ввода номера кабинета. Номер должен содержать от " + minLength + " до " + maxLength + " цифр.");
            return messageToUser;
        }

        try {
            int cabinetNumber = Integer.parseInt(cleanedNumber);
            transmittedData.getDataStorage().add("cabinetNumber", cabinetNumber);
            messageToUser.setText("Номер кабинета успешно записан. Теперь введите ФИО.");
            transmittedData.setState(State.WaitingInputFullName);
            return messageToUser;
        } catch (NumberFormatException e) {
            messageToUser.setText("Ошибка ввода номера кабинета. Пожалуйста, введите целое число.");
            return messageToUser;
        }
    }


    public SendMessage processWaitingInputFullName(String textFromUser, TransmittedData transmittedData) throws Exception {
        SendMessage messageToUser = new SendMessage();
        messageToUser.setChatId(transmittedData.getChatId());


        if (textFromUser.isEmpty() || textFromUser.length() > 50) {
            messageToUser.setText("Ошибка ввода ФИО. Повторите ввод");
            return messageToUser;
        }

        if (!textFromUser.matches("[А-Яа-яЁё\\-\\']+")) {
            messageToUser.setText("Ошибка ввода ФИО. Введите ФИО на русском языке (только кириллица, дефисы и апострофы).");
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


        String phoneNumber = textFromUser.replaceAll("[^0-9]", "");
        if (phoneNumber.isEmpty()) {
            messageToUser.setText("Ошибка ввода номера телефона. Введите номер телефона");
            return messageToUser;
        }

        int minLength = 10;
        int maxLength = 12;

        if (phoneNumber.length() <= minLength || phoneNumber.length() > maxLength) {
            messageToUser.setText("Ошибка ввода номера телефона. Номер должен содержать от " + minLength + " до " + maxLength);
            return messageToUser;
        }

        transmittedData.getDataStorage().add("phoneNumber", phoneNumber);

        messageToUser.setText("Номер телефона успешно записан. Теперь опишите проблему");

        transmittedData.setState(State.WaitingDescriptionProblem);

        return messageToUser;
    }


    public SendMessage processWaitingDescriptionProblem(String textFromUser, TransmittedData transmittedData) throws Exception {
        SendMessage messageToUser = new SendMessage();
        messageToUser.setChatId(transmittedData.getChatId());

        String textRegex = "^[\\w\\s.,!?;:'\"-]+";

        if (textFromUser.isEmpty() || textFromUser.matches(textRegex)) {
            messageToUser.setText("Ошибка. Пожалуйста, опишите проблему более подробно.");
            return messageToUser;
        }

        transmittedData.getDataStorage().add("description", textFromUser);
        messageToUser.setText("Описание проблемы успешно записано. Хотите добавить фото?");
        transmittedData.setState(State.WaitingQuestionAddPhoto);
        messageToUser.setReplyMarkup(InlineKeyboardsStorage.getQuestionKeyboard());

        return messageToUser;
    }

    public SendMessage processWaitingAddPhoto(String textFromUser, TransmittedData transmittedData) throws Exception {
        SendMessage messageToUser = new SendMessage();
        messageToUser.setChatId(transmittedData.getChatId());

        if (!textFromUser.equals(InlineButtonsStorage.YesSendPhoto.getCallBackData()) && !textFromUser.equals(InlineButtonsStorage.NoSendPhoto.getCallBackData())) {

            messageToUser.setText("Ошибка. Нажмите на кнопку.");

            return messageToUser;
        }


        if (textFromUser.equals(InlineButtonsStorage.YesSendPhoto.getCallBackData())) {

            messageToUser.setText("Отправьте фото, чтобы прикрепить его к заявке");

            transmittedData.setState(State.WaitingPhoto);

            return messageToUser;
        } else if (textFromUser.equals(InlineButtonsStorage.NoSendPhoto.getCallBackData())) {

            DataStorage data = transmittedData.getDataStorage();

            StringBuilder messageText = new StringBuilder("Проверьте данные\n\n");

            //messageText.append("Адрес площадки: ").append(data.get("cabinetNumber")).append("\n"); - тянется с бд
            messageText.append("Номер кабинета: ").append(data.get("cabinetNumber")).append("\n");
            messageText.append("ФИО: ").append(data.get("FIO")).append("\n");
            messageText.append("Номер телефона: ").append(data.get("phoneNumber")).append("\n");
            messageText.append("Проблема: ").append(data.get("description")).append("\n");

            messageToUser.setText(String.valueOf(messageText));

            transmittedData.setState(State.WaitingDataVerification);

            messageToUser.setReplyMarkup(InlineKeyboardsStorage.getVerificationDataKeyboard());

            return messageToUser;
        }
        return messageToUser;
    }

    public SendMessage processWaitingPhoto(String textFromUser, TransmittedData transmittedData) throws Exception {
        SendMessage messageToUser = new SendMessage();
        messageToUser.setChatId(transmittedData.getChatId());


        messageToUser.setText("Фото успешно прикреплено!");
        DataStorage data = transmittedData.getDataStorage();

        StringBuilder messageText = new StringBuilder("Проверьте данные\n\n");

        //messageText.append("Адрес площадки: ").append(data.get("cabinetNumber")).append("\n"); - тянется с бд
        messageText.append("Номер кабинета: ").append(data.get("cabinetNumber")).append("\n");
        messageText.append("ФИО: ").append(data.get("FIO")).append("\n");
        messageText.append("Номер телефона: ").append(data.get("phoneNumber")).append("\n");
        messageText.append("Проблема: ").append(data.get("description")).append("\n");

        messageToUser.setText(String.valueOf(messageText));

        transmittedData.setState(State.WaitingDataVerification);

        messageToUser.setReplyMarkup(InlineKeyboardsStorage.getVerificationDataKeyboard());

        return messageToUser;
    }

    public SendMessage processWaitingDataVerification(String textFromUser, TransmittedData transmittedData) throws Exception {
        SendMessage messageToUser = new SendMessage();
        messageToUser.setChatId(transmittedData.getChatId());

        if (!textFromUser.equals(InlineButtonsStorage.SendApplication.getCallBackData()) && !textFromUser.equals(InlineButtonsStorage.CancelApplication.getCallBackData())) {

            messageToUser.setText("Ошибка. Нажмите на кнопку.");

            return messageToUser;
        }

        if(textFromUser.equals(InlineButtonsStorage.SendApplication.getCallBackData())){

            messageToUser.setText("Заявка №222 успешно создана! Вам придет уведомление, когда статус заявки будет изменен"); // тянется с бд

            transmittedData.setState(State.WaitingReadApplication);
            messageToUser.setReplyMarkup(InlineKeyboardsStorage.getMenuKeyboard());
            transmittedData.getDataStorage().clear();

            return messageToUser;
        }else if(textFromUser.equals(InlineButtonsStorage.CancelApplication.getCallBackData())){


            transmittedData.setState(State.WaitingApplication);
            messageToUser.setText("Пожалуйста, выберите адрес площадки.");

            messageToUser.setReplyMarkup(InlineKeyboardsStorage.getAddressKeyboard());
            return messageToUser;
        }

        return messageToUser;
    }

    public SendMessage processWaitingReadApplication(String textFromUser, TransmittedData transmittedData) throws Exception {
        SendMessage messageToUser = new SendMessage();
        messageToUser.setChatId(transmittedData.getChatId());

        if (!textFromUser.equals(InlineButtonsStorage.BackToMenu.getCallBackData())){
            messageToUser.setText("Ошибка. Нажмите на кнопку.");

            return messageToUser;
        }

        if (textFromUser.equals(InlineButtonsStorage.BackToMenu.getCallBackData())){

            messageToUser.setText("Выберите то что вы хотите");
            messageToUser.setReplyMarkup(InlineKeyboardsStorage.getStartKeyboard());
            transmittedData.setState(State.WaitingQuestionsOrApplicationOrHistory);

            return messageToUser;
        }

        return messageToUser;
    }


}

