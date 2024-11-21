package org.example.service.logic;

import org.example.Buttons.InlineButtonsStorage;
import org.example.Buttons.InlineKeyboardsStorage;
import org.example.statemachine.State;
import org.example.statemachine.TransmittedData;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;


public class StartLogic {

    //region нажатие на кнопку старт
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
    //endregion

    //region проверка на нажатие кнопки (частые вопросы, оставить заявку, история заявок)

    public SendMessage processWaitingQuestionsOrApplicationOrHistory(String textFromUser, TransmittedData transmittedData) {
        SendMessage messageToUser = new SendMessage();
        messageToUser.setChatId(transmittedData.getChatId());

        if (!textFromUser.equals(InlineButtonsStorage.ShowQuestionsStart.getCallBackData()) && !textFromUser.equals(InlineButtonsStorage.SubmitApplication.getCallBackData()) && !textFromUser.equals(InlineButtonsStorage.SubmitHistory.getCallBackData())) {
            messageToUser.setText("Ошибка. Нажмите на кнопку.");

            return messageToUser;
        }

        if (textFromUser.equals(InlineButtonsStorage.ShowQuestionsStart.getCallBackData())) {

            transmittedData.setState(State.WaitingQuestions);
            messageToUser.setText("Выберите, с чем возникла проблема");

            messageToUser.setReplyMarkup(InlineKeyboardsStorage.getProblemSystemShowKeyboard());

            return messageToUser;

        } else if (textFromUser.equals(InlineButtonsStorage.SubmitApplication.getCallBackData())) {

            transmittedData.setState(State.WaitingApplication);
            messageToUser.setText("Пожалуйста, выберите адрес площадки.");

            messageToUser.setReplyMarkup(InlineKeyboardsStorage.getAddressKeyboard());

            return messageToUser;
        }

        return null;
    }
    //endregion

    //region вопросы (компьютер, принтер, проектор)

    public SendMessage processWaitingQuestions(String textFromUser, TransmittedData transmittedData) {
        SendMessage messageToUser = new SendMessage();
        messageToUser.setChatId(transmittedData.getChatId());

        if (!textFromUser.equals(InlineButtonsStorage.ViewProblemComputer.getCallBackData()) && !textFromUser.equals(InlineButtonsStorage.ViewProblemPrinter.getCallBackData()) && !textFromUser.equals(InlineButtonsStorage.ViewProblemProjector.getCallBackData()) && !textFromUser.equals(InlineButtonsStorage.BackToMenu.getCallBackData())) {
            messageToUser.setText("Ошибка. Нажмите на кнопку.");

            return messageToUser;
        }

        if (textFromUser.equals(InlineButtonsStorage.ViewProblemComputer.getCallBackData())) {

            messageToUser.setText("Список проблем: \n1. Отсутствует подключение к сети Интернет \n2. Не включается компьютер \n3. Проблема с монитором.");

            transmittedData.setState(State.WaitingViewProblemComputer);

            messageToUser.setReplyMarkup(InlineKeyboardsStorage.getProblemFiveButtonsKeyboard());

            return messageToUser;

        } else if (textFromUser.equals(InlineButtonsStorage.ViewProblemPrinter.getCallBackData())) {

            messageToUser.setText("Список проблем: \n1. Не подключается к компьютеру \n2. Замятие бумаги");

            transmittedData.setState(State.WaitingViewProblemPrinter);

            messageToUser.setReplyMarkup(InlineKeyboardsStorage.getProblemFoursButtonsKeyboard());

            return messageToUser;

        } else if (textFromUser.equals(InlineButtonsStorage.ViewProblemProjector.getCallBackData())) {

            messageToUser.setText("Список проблем: \n1. Не выводится изображение \n2. Проектор не включается \n3. Слишком тусклое изображение");

            transmittedData.setState(State.WaitingViewProblemProjector);

            messageToUser.setReplyMarkup(InlineKeyboardsStorage.getProblemFiveButtonsKeyboard());

            return messageToUser;

        } else if (textFromUser.equals(InlineButtonsStorage.BackToMenu.getCallBackData())) {

            messageToUser.setText("Выберите то что вы хотите");
            messageToUser.setReplyMarkup(InlineKeyboardsStorage.getStartKeyboard());
            transmittedData.setState(State.WaitingQuestionsOrApplicationOrHistory);

            return messageToUser;

        }
        return null;
    }
    //endregion

    //region обработка нажатия кнопок адресов при нажатие на кнопку "оставить запрос"
    public SendMessage processWaitingApplication(String textFromUser, TransmittedData transmittedData) {
        SendMessage messageToUser = new SendMessage();
        messageToUser.setChatId(transmittedData.getChatId());

        if (!textFromUser.equals(InlineButtonsStorage.FirstAddressPlace.getCallBackData()) && !textFromUser.equals(InlineButtonsStorage.SecondAddressPlace.getCallBackData()) && !textFromUser.equals(InlineButtonsStorage.ThirdAddressPlace.getCallBackData()) && !textFromUser.equals(InlineButtonsStorage.FourAddressPlace.getCallBackData()) && !textFromUser.equals(InlineButtonsStorage.FiveAddressPlace.getCallBackData()) && !textFromUser.equals(InlineButtonsStorage.SixAddressPlace.getCallBackData()) && !textFromUser.equals(InlineButtonsStorage.BackToMenu.getCallBackData())) {

            messageToUser.setText("Ошибка. Нажмите на кнопку.");

            return messageToUser;
        }

        if (textFromUser.equals(InlineButtonsStorage.FirstAddressPlace.getCallBackData())) {

            messageToUser.setText("Введите номер кабинета");
            transmittedData.setState(State.WaitingInputCabinetNumber);

            return messageToUser;
        } else if (textFromUser.equals(InlineButtonsStorage.SecondAddressPlace.getCallBackData())) {

            messageToUser.setText("Введите номер кабинета");
            transmittedData.setState(State.WaitingInputCabinetNumber);

            return messageToUser;
        } else if (textFromUser.equals(InlineButtonsStorage.ThirdAddressPlace.getCallBackData())) {

            messageToUser.setText("Введите номер кабинета");
            transmittedData.setState(State.WaitingInputCabinetNumber);

            return messageToUser;
        } else if (textFromUser.equals(InlineButtonsStorage.FourAddressPlace.getCallBackData())) {

            messageToUser.setText("Введите номер кабинета");
            transmittedData.setState(State.WaitingInputCabinetNumber);

            return messageToUser;
        } else if (textFromUser.equals(InlineButtonsStorage.FiveAddressPlace.getCallBackData())) {

            messageToUser.setText("Введите номер кабинета");
            transmittedData.setState(State.WaitingInputCabinetNumber);

            return messageToUser;
        } else if (textFromUser.equals(InlineButtonsStorage.SixAddressPlace.getCallBackData())) {

            messageToUser.setText("Введите номер кабинета");
            transmittedData.setState(State.WaitingInputCabinetNumber);

            return messageToUser;
        } else if (textFromUser.equals(InlineButtonsStorage.BackToMenu.getCallBackData())) {

            messageToUser.setText("Выберите то что вы хотите");
            messageToUser.setReplyMarkup(InlineKeyboardsStorage.getStartKeyboard());
            transmittedData.setState(State.WaitingQuestionsOrApplicationOrHistory);

            return messageToUser;
        }

        return messageToUser;
    }
    //endregion

}