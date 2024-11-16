package org.example.service.logic;

import org.example.Buttons.InlineButtonsStorage;
import org.example.Buttons.InlineKeyboardsStorage;
import org.example.statemachine.State;
import org.example.statemachine.TransmittedData;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;


public class FaqLogic {

    public SendMessage processWaitingViewProblemComputer(String textFromUser, TransmittedData transmittedData) throws Exception {
        SendMessage messageToUser = new SendMessage();
        messageToUser.setChatId(transmittedData.getChatId());

        messageToUser.setText("Список проблем: \n1. Отсутствует подключение к сети Интернет \n2. Не включается компьютер \n3. Проблема с монитором.");
        messageToUser.setReplyMarkup(InlineKeyboardsStorage.getProblemComputerKeyboard());

        if (!textFromUser.equals(InlineButtonsStorage.First.getCallBackData()) && !textFromUser.equals(InlineButtonsStorage.Second.getCallBackData()) && !textFromUser.equals(InlineButtonsStorage.Third.getCallBackData()) && !textFromUser.equals(InlineButtonsStorage.MovePrevShow.getCallBackData()) && !textFromUser.equals(InlineButtonsStorage.BackToMenu.getCallBackData())) {
            messageToUser.setText("Ошибка. Нажмите на кнопку.");
            return messageToUser;
        }



        if (textFromUser.equals(InlineButtonsStorage.First.getCallBackData())) {
            transmittedData.setState(State.WaitingFirstInfoProblemComputer);
        }
        if (textFromUser.equals(InlineButtonsStorage.Second.getCallBackData())) {
            transmittedData.setState(State.WaitingSecondInfoProblemComputer);
        }
        if (textFromUser.equals(InlineButtonsStorage.Third.getCallBackData())) {
            transmittedData.setState(State.WaitingThirdInfoProblemComputer);
        }
        if (textFromUser.equals(InlineButtonsStorage.MovePrevShow.getCallBackData())) {
            transmittedData.setState(State.WaitingQuestionsOrApplicationOrHistory);
        }
        if (textFromUser.equals(InlineButtonsStorage.BackToMenu.getCallBackData())) {
            transmittedData.setState(State.WaitingCommandStart);
        }
        return messageToUser;
    }

    public SendMessage processWaitingFirstInfoProblemComputer(String textFromUser, TransmittedData transmittedData) throws
            Exception {
        SendMessage messageToUser = new SendMessage();
        messageToUser.setChatId(transmittedData.getChatId());

        messageToUser.setText("Отсутствует подключение к сети Интернет\n" +
                "При отсутствии подключения к сети Интернет на компьютере:\n" +
                "1. Проверьте кабель сети в компьютере и розетке для кабеля сети;\n" +
                "2. Если Вы подключены через кабель сети и проверив его концы не обнаружили проблему, то оставьте запрос техническому специалисту.\n" +
                "\n" +
                "При отсутствии подключения к сети Интернет на ноутбуке:\n" +
                "1. Нажмите на значок настроек (шестерня);\n" +
                "2. Перейдите в раздел сети (WI-FI);\n" +
                "3. Найдите ближайшую рабочую WI-FI сеть;\n" +
                "4. Нажмите на кнопку \"Подключиться\".\n");

        messageToUser.setReplyMarkup(InlineKeyboardsStorage.getBackKeyboard());

        if (textFromUser.equals(InlineButtonsStorage.MovePrevShow.getCallBackData())) {
            transmittedData.setState(State.WaitingViewProblemComputer);
        } else if (textFromUser.equals(InlineButtonsStorage.BackToMenu.getCallBackData())) {
            transmittedData.setState(State.WaitingCommandStart);
            messageToUser.setText("Вы вернулись в главное меню...");
            return messageToUser;
        }
        return messageToUser;
    }

    public SendMessage processWaitingSecondInfoProblemComputer(String textFromUser, TransmittedData transmittedData) throws
            Exception {
        SendMessage messageToUser = new SendMessage();
        messageToUser.setChatId(transmittedData.getChatId());

        messageToUser.setText("1. Проверьте кабель питания (подключен ли он к компьютеру и вставлена ли вилка в розетку);\n" +
                "2. Проверьте кнопку на блоке питания (должна быть в режиме \"I\");\n" +
                "3. Если Ваш компьютер подключен к сети электропитания через удлинитель, проверьте включен ли он;\n");

        messageToUser.setReplyMarkup(InlineKeyboardsStorage.getBackKeyboard());

        if (textFromUser.equals(InlineButtonsStorage.MovePrevShow.getCallBackData())) {
            transmittedData.setState(State.WaitingViewProblemComputer);
        } else if (textFromUser.equals(InlineButtonsStorage.BackToMenu.getCallBackData())) {
            transmittedData.setState(State.WaitingCommandStart);
            messageToUser.setText("Вы вернулись в главное меню...");
            return messageToUser;
        }
        return messageToUser;
    }

    public SendMessage processWaitingThirdInfoProblemComputer(String textFromUser, TransmittedData transmittedData) throws
            Exception {
        SendMessage messageToUser = new SendMessage();
        messageToUser.setChatId(transmittedData.getChatId());

        messageToUser.setText("Монитор выводит изображения с помехами;\n" +
                "\n" +
                "1. Попробуйте переставить кабель вывода изображения в мониторе\n" +
                "2. Если монитор мигает, то:\n" +
                "2.1 Проверьте, включен ли компьютер;\n" +
                "2.2 Выключите и снова включите монитор.\n" +
                "\n" +
                "Монитор не выводит изображение;\n" +
                "\n" +
                "1. Проверьте кабель вывода изображения с компьютера на монитор;\n" +
                "2. Проверьте кабель питания на мониторе;\n" +
                "3. Проверьте включен ли монитор;\n");


        messageToUser.setReplyMarkup(InlineKeyboardsStorage.getBackKeyboard());

        if (textFromUser.equals(InlineButtonsStorage.MovePrevShow.getCallBackData())) {
            transmittedData.setState(State.WaitingViewProblemComputer);
        } else if (textFromUser.equals(InlineButtonsStorage.BackToMenu.getCallBackData())) {
            transmittedData.setState(State.WaitingCommandStart);
            messageToUser.setText("Вы вернулись в главное меню...");
            return messageToUser;
        }
        return messageToUser;
    }

    public SendMessage processWaitingViewProblemPrinter(String textFromUser, TransmittedData transmittedData) throws
            Exception {
        SendMessage messageToUser = new SendMessage();
        messageToUser.setChatId(transmittedData.getChatId());
        messageToUser.setText("dada");


        return messageToUser;
    }

    public SendMessage processWaitingViewProblemProjector(String textFromUser, TransmittedData transmittedData) throws
            Exception {
        SendMessage messageToUser = new SendMessage();
        messageToUser.setChatId(transmittedData.getChatId());
        messageToUser.setText("asdadadada");

        return messageToUser;
    }


}
