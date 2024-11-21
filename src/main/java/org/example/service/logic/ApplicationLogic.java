package org.example.service.logic;

import org.example.Buttons.InlineButtonsStorage;
import org.example.Buttons.InlineKeyboardsStorage;
import org.example.statemachine.DataStorage;
import org.example.statemachine.State;
import org.example.statemachine.TransmittedData;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;

public class ApplicationLogic {

    public SendMessage processWaitingInputCabinetNumber(String textFromUser, TransmittedData transmittedData) throws Exception {
        SendMessage messageToUser = new SendMessage();
        messageToUser.setChatId(transmittedData.getChatId());

        if (textFromUser.isEmpty() || textFromUser.length() > 1000) {
            messageToUser.setText("Ошибка ввода номера кабинета. Повторите ввод.");
            return messageToUser;
        }


        transmittedData.getDataStorage().add("cabinetNumber", textFromUser);
        messageToUser.setText("Номер кабинета успешно записан. Теперь введите ФИО.");
        transmittedData.setState(State.WaitingInputFullName);
        return messageToUser;
    }

    public SendMessage processWaitingInputFullName(String textFromUser, TransmittedData transmittedData) throws Exception {
        SendMessage messageToUser = new SendMessage();
        messageToUser.setChatId(transmittedData.getChatId());


        if (textFromUser.isEmpty() || textFromUser.length() > 100) {
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

        if (textFromUser.isEmpty()) {
            messageToUser.setText("Ошибка ввода номера телефона. Введите номер телефона");
            return messageToUser;
        }

        if (textFromUser.length() < 9) {
            messageToUser.setText("Ошибка ввода номера телефона. Номер должен содержать минимум 11 символов");
            return messageToUser;
        }

        transmittedData.getDataStorage().add("phoneNumber", textFromUser);

        messageToUser.setText("Номер телефона успешно записан. Теперь опишите проблему");

        transmittedData.setState(State.WaitingDescriptionProblem);

        return messageToUser;
    }


    public SendMessage processWaitingDescriptionProblem(String textFromUser, TransmittedData transmittedData) throws Exception {
        SendMessage messageToUser = new SendMessage();
        messageToUser.setChatId(transmittedData.getChatId());

        if (textFromUser.isEmpty()) {
            messageToUser.setText("Ошибка. Не может быть пустое сообщение.");
            return messageToUser;
        }

        transmittedData.getDataStorage().add("description", textFromUser);
        messageToUser.setText("Описание проблемы успешно записано. Хотите добавить фото?");
        transmittedData.setState(State.WaitingQuestionAddPhoto);
        messageToUser.setReplyMarkup(InlineKeyboardsStorage.getQuestionKeyboard());

        return messageToUser;
    }

    public SendMessage processWaitingQuestionAddPhoto(String textFromUser, TransmittedData transmittedData) throws Exception {
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

        if (textFromUser.equals(InlineButtonsStorage.SendApplication.getCallBackData())) {

            messageToUser.setText("Заявка №222 успешно создана! Вам придет уведомление, когда статус заявки будет изменен"); // тянется с бд

            transmittedData.setState(State.WaitingReadApplication);
            messageToUser.setReplyMarkup(InlineKeyboardsStorage.getMenuKeyboard());
            transmittedData.getDataStorage().clear();

            return messageToUser;
        } else if (textFromUser.equals(InlineButtonsStorage.CancelApplication.getCallBackData())) {


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

        if (!textFromUser.equals(InlineButtonsStorage.BackToMenu.getCallBackData())) {
            messageToUser.setText("Ошибка. Нажмите на кнопку.");

            return messageToUser;
        }

        if (textFromUser.equals(InlineButtonsStorage.BackToMenu.getCallBackData())) {

            messageToUser.setText("Выберите то что вы хотите");
            messageToUser.setReplyMarkup(InlineKeyboardsStorage.getStartKeyboard());
            transmittedData.setState(State.WaitingQuestionsOrApplicationOrHistory);

            return messageToUser;
        }

        return messageToUser;
    }


}

