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

        if (!textFromUser.equals(InlineButtonsStorage.First.getCallBackData()) && !textFromUser.equals(InlineButtonsStorage.Second.getCallBackData()) && !textFromUser.equals(InlineButtonsStorage.Third.getCallBackData()) && !textFromUser.equals(InlineButtonsStorage.MovePrevShow.getCallBackData()) && !textFromUser.equals(InlineButtonsStorage.BackToMenu.getCallBackData())) {
            messageToUser.setText("Ошибка. Нажмите на кнопку.");

            return messageToUser;
        }

        if (textFromUser.equals(InlineButtonsStorage.First.getCallBackData())) {

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

            transmittedData.setState(State.WaitingFirstInfoProblemComputer);


            return messageToUser;

        } else if (textFromUser.equals(InlineButtonsStorage.Second.getCallBackData())) {

            messageToUser.setText("1. Проверьте кабель питания (подключен ли он к компьютеру и вставлена ли вилка в розетку);\n" +
                    "2. Проверьте кнопку на блоке питания (должна быть в режиме \"I\");\n" +
                    "3. Если Ваш компьютер подключен к сети электропитания через удлинитель, проверьте включен ли он;\n");

            messageToUser.setReplyMarkup(InlineKeyboardsStorage.getBackKeyboard());

            transmittedData.setState(State.WaitingSecondInfoProblemComputer);

            return messageToUser;

        } else if (textFromUser.equals(InlineButtonsStorage.Third.getCallBackData())) {

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

            transmittedData.setState(State.WaitingThirdInfoProblemComputer);

            return messageToUser;

        } else if (textFromUser.equals(InlineButtonsStorage.MovePrevShow.getCallBackData())) {

            transmittedData.setState(State.WaitingQuestionsOrApplicationOrHistory);

            messageToUser.setText("Выберите, с чем возникла проблема");

            messageToUser.setReplyMarkup(InlineKeyboardsStorage.getProblemSystemShowKeyboard());

            transmittedData.setState(State.WaitingQuestions);

            return messageToUser;

        } else if (textFromUser.equals(InlineButtonsStorage.BackToMenu.getCallBackData())) {

            messageToUser.setText("Здравстуйте! Это теханическая поддержка МГОК.\n \nДанный бот призван упростить взаимодействие преподавателей и Сис админов\n \nБудем рады помочь решить проблему, которая у вас возникла\n \nДля того, чтобы бот начал работу, нажмите /start");

            transmittedData.setState(State.WaitingCommandStart);

            return messageToUser;
        }
        return messageToUser;
    }


    public SendMessage processWaitingFirstInfoProblemComputer(String textFromUser, TransmittedData transmittedData) throws
            Exception {
        SendMessage messageToUser = new SendMessage();
        messageToUser.setChatId(transmittedData.getChatId());

        if (!textFromUser.equals(InlineButtonsStorage.First.getCallBackData()) && !textFromUser.equals(InlineButtonsStorage.Second.getCallBackData()) && !textFromUser.equals(InlineButtonsStorage.Third.getCallBackData()) && !textFromUser.equals(InlineButtonsStorage.MovePrevShow.getCallBackData()) && !textFromUser.equals(InlineButtonsStorage.BackToMenu.getCallBackData())) {
            messageToUser.setText("Ошибка. Нажмите на кнопку.");

            return messageToUser;
        }


        if (textFromUser.equals(InlineButtonsStorage.MovePrevShow.getCallBackData())) {

            transmittedData.setState(State.WaitingQuestionsOrApplicationOrHistory);

            messageToUser.setText("Выберите, с чем возникла проблема");

            messageToUser.setReplyMarkup(InlineKeyboardsStorage.getProblemSystemShowKeyboard());

            transmittedData.setState(State.WaitingQuestions);

            return messageToUser;

        } else if (textFromUser.equals(InlineButtonsStorage.BackToMenu.getCallBackData())) {

            messageToUser.setText("Здравстуйте! Это теханическая поддержка МГОК.\n \nДанный бот призван упростить взаимодействие преподавателей и Сис админов\n \nБудем рады помочь решить проблему, которая у вас возникла\n \nДля того, чтобы бот начал работу, нажмите /start");

            transmittedData.setState(State.WaitingCommandStart);

            messageToUser.setText("Выберите то что вы хотите");
            messageToUser.setReplyMarkup(InlineKeyboardsStorage.getStartKeyboard());
            transmittedData.setState(State.WaitingQuestionsOrApplicationOrHistory);

            return messageToUser;

        }
        return messageToUser;
    }

    public SendMessage processWaitingSecondInfoProblemComputer(String textFromUser, TransmittedData transmittedData) throws
            Exception {
        SendMessage messageToUser = new SendMessage();
        messageToUser.setChatId(transmittedData.getChatId());

        if (!textFromUser.equals(InlineButtonsStorage.First.getCallBackData()) && !textFromUser.equals(InlineButtonsStorage.Second.getCallBackData()) && !textFromUser.equals(InlineButtonsStorage.Third.getCallBackData()) && !textFromUser.equals(InlineButtonsStorage.MovePrevShow.getCallBackData()) && !textFromUser.equals(InlineButtonsStorage.BackToMenu.getCallBackData())) {
            messageToUser.setText("Ошибка. Нажмите на кнопку.");

            return messageToUser;
        }

        if (textFromUser.equals(InlineButtonsStorage.MovePrevShow.getCallBackData())) {

            transmittedData.setState(State.WaitingQuestionsOrApplicationOrHistory);

            messageToUser.setText("Выберите, с чем возникла проблема");

            messageToUser.setReplyMarkup(InlineKeyboardsStorage.getProblemSystemShowKeyboard());

            transmittedData.setState(State.WaitingQuestions);

            return messageToUser;

        } else if (textFromUser.equals(InlineButtonsStorage.BackToMenu.getCallBackData())) {

            messageToUser.setText("Здравстуйте! Это теханическая поддержка МГОК.\n \nДанный бот призван упростить взаимодействие преподавателей и Сис админов\n \nБудем рады помочь решить проблему, которая у вас возникла\n \nДля того, чтобы бот начал работу, нажмите /start");

            transmittedData.setState(State.WaitingCommandStart);

            messageToUser.setText("Выберите то что вы хотите");
            messageToUser.setReplyMarkup(InlineKeyboardsStorage.getStartKeyboard());
            transmittedData.setState(State.WaitingQuestionsOrApplicationOrHistory);

            return messageToUser;
        }
        return messageToUser;
    }

    public SendMessage processWaitingThirdInfoProblemComputer(String textFromUser, TransmittedData transmittedData) throws
            Exception {
        SendMessage messageToUser = new SendMessage();
        messageToUser.setChatId(transmittedData.getChatId());

        if (!textFromUser.equals(InlineButtonsStorage.First.getCallBackData()) && !textFromUser.equals(InlineButtonsStorage.Second.getCallBackData()) && !textFromUser.equals(InlineButtonsStorage.Third.getCallBackData()) && !textFromUser.equals(InlineButtonsStorage.MovePrevShow.getCallBackData()) && !textFromUser.equals(InlineButtonsStorage.BackToMenu.getCallBackData())) {
            messageToUser.setText("Ошибка. Нажмите на кнопку.");

            return messageToUser;
        }

        if (textFromUser.equals(InlineButtonsStorage.MovePrevShow.getCallBackData())) {

            transmittedData.setState(State.WaitingQuestionsOrApplicationOrHistory);

            messageToUser.setText("Выберите, с чем возникла проблема");

            messageToUser.setReplyMarkup(InlineKeyboardsStorage.getProblemSystemShowKeyboard());

            transmittedData.setState(State.WaitingQuestions);

            return messageToUser;

        } else if (textFromUser.equals(InlineButtonsStorage.BackToMenu.getCallBackData())) {

            messageToUser.setText("Здравстуйте! Это теханическая поддержка МГОК.\n \nДанный бот призван упростить взаимодействие преподавателей и Сис админов\n \nБудем рады помочь решить проблему, которая у вас возникла\n \nДля того, чтобы бот начал работу, нажмите /start");

            transmittedData.setState(State.WaitingCommandStart);

            messageToUser.setText("Выберите то что вы хотите");
            messageToUser.setReplyMarkup(InlineKeyboardsStorage.getStartKeyboard());
            transmittedData.setState(State.WaitingQuestionsOrApplicationOrHistory);

            return messageToUser;

        }
        return messageToUser;
    }

    public SendMessage processWaitingViewProblemPrinter(String textFromUser, TransmittedData transmittedData) throws
            Exception {
        SendMessage messageToUser = new SendMessage();
        messageToUser.setChatId(transmittedData.getChatId());

        if (!textFromUser.equals(InlineButtonsStorage.First.getCallBackData()) && !textFromUser.equals(InlineButtonsStorage.Second.getCallBackData()) && !textFromUser.equals(InlineButtonsStorage.MovePrevShow.getCallBackData()) && !textFromUser.equals(InlineButtonsStorage.BackToMenu.getCallBackData())) {
            messageToUser.setText("Ошибка. Нажмите на кнопку.");

            return messageToUser;
        }

        if (textFromUser.equals(InlineButtonsStorage.First.getCallBackData())) {

            messageToUser.setText("1. Перейдите в настройки Windows;\n" +
                    "2. Зайдите в параметры устройств и выберите категорию с принтерами;\n" +
                    "3. Если в списке подключенных принтеров нет вашего, то запустите поиск;\n" +
                    "3.1. В списке найденных устройств выбери ваш принтер и ожидайте подключения;\n" +
                    "3. Если Ваш принтер подключен к компьютеру, то удалите его и подключите заново.\n");

            messageToUser.setReplyMarkup(InlineKeyboardsStorage.getBackKeyboard());

            transmittedData.setState(State.WaitingSecondInfoProblemPrinter);

            return messageToUser;

        } else if (textFromUser.equals(InlineButtonsStorage.Second.getCallBackData())) {

            messageToUser.setText("1. При замятии бумаги посмотрите на панель управления принтера и следуйте инструкции;\n" +
                    "2. Откройте заднюю панель принтера;\n" +
                    "3. Вытащите замятый лист и закройте панель;\n");

            messageToUser.setReplyMarkup(InlineKeyboardsStorage.getBackKeyboard());

            transmittedData.setState(State.WaitingSecondInfoProblemPrinter);

            return messageToUser;

        } else if (textFromUser.equals(InlineButtonsStorage.MovePrevShow.getCallBackData())) {

            transmittedData.setState(State.WaitingQuestionsOrApplicationOrHistory);

            messageToUser.setText("Выберите, с чем возникла проблема");

            messageToUser.setReplyMarkup(InlineKeyboardsStorage.getProblemSystemShowKeyboard());

            transmittedData.setState(State.WaitingQuestions);

            return messageToUser;

        } else if (textFromUser.equals(InlineButtonsStorage.BackToMenu.getCallBackData())) {

            messageToUser.setText("Здравстуйте! Это теханическая поддержка МГОК.\n \nДанный бот призван упростить взаимодействие преподавателей и Сис админов\n \nБудем рады помочь решить проблему, которая у вас возникла\n \nДля того, чтобы бот начал работу, нажмите /start");

            transmittedData.setState(State.WaitingCommandStart);

            return messageToUser;
        }

        return messageToUser;
    }

    public SendMessage processWaitingFirstInfoProblemPrinter(String textFromUser, TransmittedData
            transmittedData) throws
            Exception {
        SendMessage messageToUser = new SendMessage();
        messageToUser.setChatId(transmittedData.getChatId());

        if (!textFromUser.equals(InlineButtonsStorage.First.getCallBackData()) && !textFromUser.equals(InlineButtonsStorage.Second.getCallBackData()) && !textFromUser.equals(InlineButtonsStorage.MovePrevShow.getCallBackData()) && !textFromUser.equals(InlineButtonsStorage.BackToMenu.getCallBackData())) {
            messageToUser.setText("Ошибка. Нажмите на кнопку.");

            return messageToUser;
        }

        if (textFromUser.equals(InlineButtonsStorage.MovePrevShow.getCallBackData())) {

            transmittedData.setState(State.WaitingQuestionsOrApplicationOrHistory);

            messageToUser.setText("Выберите, с чем возникла проблема");

            messageToUser.setReplyMarkup(InlineKeyboardsStorage.getProblemSystemShowKeyboard());

            transmittedData.setState(State.WaitingQuestions);

            return messageToUser;

        } else if (textFromUser.equals(InlineButtonsStorage.BackToMenu.getCallBackData())) {

            messageToUser.setText("Здравстуйте! Это теханическая поддержка МГОК.\n \nДанный бот призван упростить взаимодействие преподавателей и Сис админов\n \nБудем рады помочь решить проблему, которая у вас возникла\n \nДля того, чтобы бот начал работу, нажмите /start");

            transmittedData.setState(State.WaitingCommandStart);

            messageToUser.setText("Выберите то что вы хотите");
            messageToUser.setReplyMarkup(InlineKeyboardsStorage.getStartKeyboard());
            transmittedData.setState(State.WaitingQuestionsOrApplicationOrHistory);

            return messageToUser;

        }
        return messageToUser;
    }

    public SendMessage processWaitingSecondInfoProblemPrinter(String textFromUser, TransmittedData
            transmittedData) throws
            Exception {
        SendMessage messageToUser = new SendMessage();
        messageToUser.setChatId(transmittedData.getChatId());

        if (!textFromUser.equals(InlineButtonsStorage.First.getCallBackData()) && !textFromUser.equals(InlineButtonsStorage.Second.getCallBackData()) && !textFromUser.equals(InlineButtonsStorage.MovePrevShow.getCallBackData()) && !textFromUser.equals(InlineButtonsStorage.BackToMenu.getCallBackData())) {
            messageToUser.setText("Ошибка. Нажмите на кнопку.");

            return messageToUser;
        }

        else if (textFromUser.equals(InlineButtonsStorage.MovePrevShow.getCallBackData())) {

            transmittedData.setState(State.WaitingQuestionsOrApplicationOrHistory);

            messageToUser.setText("Выберите, с чем возникла проблема");

            messageToUser.setReplyMarkup(InlineKeyboardsStorage.getProblemSystemShowKeyboard());

            transmittedData.setState(State.WaitingQuestions);

            return messageToUser;

        } else if (textFromUser.equals(InlineButtonsStorage.BackToMenu.getCallBackData())) {

            messageToUser.setText("Здравстуйте! Это теханическая поддержка МГОК.\n \nДанный бот призван упростить взаимодействие преподавателей и Сис админов\n \nБудем рады помочь решить проблему, которая у вас возникла\n \nДля того, чтобы бот начал работу, нажмите /start");

            transmittedData.setState(State.WaitingCommandStart);

            messageToUser.setText("Выберите то что вы хотите");
            messageToUser.setReplyMarkup(InlineKeyboardsStorage.getStartKeyboard());
            transmittedData.setState(State.WaitingQuestionsOrApplicationOrHistory);

            return messageToUser;

        }
        return messageToUser;
    }

    public SendMessage processWaitingViewProblemProjector(String textFromUser, TransmittedData transmittedData) throws
            Exception {
        SendMessage messageToUser = new SendMessage();
        messageToUser.setChatId(transmittedData.getChatId());

        if (!textFromUser.equals(InlineButtonsStorage.First.getCallBackData()) && !textFromUser.equals(InlineButtonsStorage.Second.getCallBackData()) && !textFromUser.equals(InlineButtonsStorage.Third.getCallBackData()) && !textFromUser.equals(InlineButtonsStorage.MovePrevShow.getCallBackData()) && !textFromUser.equals(InlineButtonsStorage.BackToMenu.getCallBackData())) {
            messageToUser.setText("Ошибка. Нажмите на кнопку.");

            return messageToUser;
        }

        if (textFromUser.equals(InlineButtonsStorage.First.getCallBackData())) {

            messageToUser.setText("1. Проверьте, включен ли проектор/интерактивная доска;\n" +
                    "2. Проверьте кабель вывода изображения;\n" +
                    "3. На компьютере нажмите комбинацию клавиш WIN + P и в открывшейся панели выберите \"Повторяющийся\".\n");

            messageToUser.setReplyMarkup(InlineKeyboardsStorage.getBackKeyboard());

            transmittedData.setState(State.WaitingFirstInfoProblemProjector);

            return messageToUser;

        } else if (textFromUser.equals(InlineButtonsStorage.Second.getCallBackData())) {

            messageToUser.setText("1. Проверьте кабель питания, вставлен ли он в розетку;\n" +
                    "2. Проверьте рубильник в щитке:\n" +
                    "2.1 Если включен, но при этом нет электричества, обратитесь к заведующему хозяйству по зданию.\n");

            messageToUser.setReplyMarkup(InlineKeyboardsStorage.getBackKeyboard());

            transmittedData.setState(State.WaitingSecondInfoProblemProjector);

            return messageToUser;

        } else if (textFromUser.equals(InlineButtonsStorage.Third.getCallBackData())) {

            messageToUser.setText("1. Попробуйте выключить свет в кабинете;\n" +
                    "2. Возьмите пульт и перейдите в настройки проектора, затем перейдите во вкладку \"Изображение\" и измените уровень яркости.\n");

            messageToUser.setReplyMarkup(InlineKeyboardsStorage.getBackKeyboard());

            transmittedData.setState(State.WaitingThirdInfoProblemProjector);

            return messageToUser;

        } else if (textFromUser.equals(InlineButtonsStorage.MovePrevShow.getCallBackData())) {

            transmittedData.setState(State.WaitingQuestionsOrApplicationOrHistory);

            messageToUser.setText("Выберите, с чем возникла проблема");

            messageToUser.setReplyMarkup(InlineKeyboardsStorage.getProblemSystemShowKeyboard());

            transmittedData.setState(State.WaitingQuestions);

            return messageToUser;

        } else if (textFromUser.equals(InlineButtonsStorage.BackToMenu.getCallBackData())) {

            messageToUser.setText("Здравстуйте! Это теханическая поддержка МГОК.\n \nДанный бот призван упростить взаимодействие преподавателей и Сис админов\n \nБудем рады помочь решить проблему, которая у вас возникла\n \nДля того, чтобы бот начал работу, нажмите /start");

            transmittedData.setState(State.WaitingCommandStart);

            return messageToUser;
        }

        return messageToUser;
    }

    public SendMessage processWaitingFirstInfoProblemProjector(String textFromUser, TransmittedData
            transmittedData) throws
            Exception {
        SendMessage messageToUser = new SendMessage();
        messageToUser.setChatId(transmittedData.getChatId());

        if (!textFromUser.equals(InlineButtonsStorage.First.getCallBackData()) && !textFromUser.equals(InlineButtonsStorage.Second.getCallBackData()) && textFromUser.equals(InlineButtonsStorage.Third.getCallBackData()) && !textFromUser.equals(InlineButtonsStorage.MovePrevShow.getCallBackData()) && !textFromUser.equals(InlineButtonsStorage.BackToMenu.getCallBackData())) {
            messageToUser.setText("Ошибка. Нажмите на кнопку.");

            return messageToUser;
        }

        if (textFromUser.equals(InlineButtonsStorage.MovePrevShow.getCallBackData())) {

            transmittedData.setState(State.WaitingQuestionsOrApplicationOrHistory);

            messageToUser.setText("Выберите, с чем возникла проблема");

            messageToUser.setReplyMarkup(InlineKeyboardsStorage.getProblemSystemShowKeyboard());

            transmittedData.setState(State.WaitingQuestions);

            return messageToUser;

        } else if (textFromUser.equals(InlineButtonsStorage.BackToMenu.getCallBackData())) {

            messageToUser.setText("Здравстуйте! Это теханическая поддержка МГОК.\n \nДанный бот призван упростить взаимодействие преподавателей и Сис админов\n \nБудем рады помочь решить проблему, которая у вас возникла\n \nДля того, чтобы бот начал работу, нажмите /start");

            transmittedData.setState(State.WaitingCommandStart);

            messageToUser.setText("Выберите то что вы хотите");
            messageToUser.setReplyMarkup(InlineKeyboardsStorage.getStartKeyboard());
            transmittedData.setState(State.WaitingQuestionsOrApplicationOrHistory);

            return messageToUser;

        }
        return messageToUser;
    }

    public SendMessage processWaitingSecondInfoProblemProjector(String textFromUser, TransmittedData
            transmittedData) throws
            Exception {
        SendMessage messageToUser = new SendMessage();
        messageToUser.setChatId(transmittedData.getChatId());

        if (!textFromUser.equals(InlineButtonsStorage.First.getCallBackData()) && !textFromUser.equals(InlineButtonsStorage.Second.getCallBackData()) && textFromUser.equals(InlineButtonsStorage.Third.getCallBackData()) && !textFromUser.equals(InlineButtonsStorage.MovePrevShow.getCallBackData()) && !textFromUser.equals(InlineButtonsStorage.BackToMenu.getCallBackData())) {
            messageToUser.setText("Ошибка. Нажмите на кнопку.");

            return messageToUser;
        }

        if (textFromUser.equals(InlineButtonsStorage.MovePrevShow.getCallBackData())) {

            transmittedData.setState(State.WaitingQuestionsOrApplicationOrHistory);

            messageToUser.setText("Выберите, с чем возникла проблема");

            messageToUser.setReplyMarkup(InlineKeyboardsStorage.getProblemSystemShowKeyboard());

            transmittedData.setState(State.WaitingQuestions);

            return messageToUser;

        } else if (textFromUser.equals(InlineButtonsStorage.BackToMenu.getCallBackData())) {

            messageToUser.setText("Здравстуйте! Это теханическая поддержка МГОК.\n \nДанный бот призван упростить взаимодействие преподавателей и Сис админов\n \nБудем рады помочь решить проблему, которая у вас возникла\n \nДля того, чтобы бот начал работу, нажмите /start");

            transmittedData.setState(State.WaitingCommandStart);

            messageToUser.setText("Выберите то что вы хотите");
            messageToUser.setReplyMarkup(InlineKeyboardsStorage.getStartKeyboard());
            transmittedData.setState(State.WaitingQuestionsOrApplicationOrHistory);

            return messageToUser;

        }
        return messageToUser;
    }

    public SendMessage processWaitingThirdInfoProblemProjector(String textFromUser, TransmittedData
            transmittedData) throws
            Exception {
        SendMessage messageToUser = new SendMessage();
        messageToUser.setChatId(transmittedData.getChatId());

        if (!textFromUser.equals(InlineButtonsStorage.First.getCallBackData()) && !textFromUser.equals(InlineButtonsStorage.Second.getCallBackData()) && textFromUser.equals(InlineButtonsStorage.Third.getCallBackData()) && !textFromUser.equals(InlineButtonsStorage.MovePrevShow.getCallBackData()) && !textFromUser.equals(InlineButtonsStorage.BackToMenu.getCallBackData())) {
            messageToUser.setText("Ошибка. Нажмите на кнопку.");

            return messageToUser;
        }


        if (textFromUser.equals(InlineButtonsStorage.MovePrevShow.getCallBackData())) {

            transmittedData.setState(State.WaitingQuestionsOrApplicationOrHistory);

            messageToUser.setText("Выберите, с чем возникла проблема");

            messageToUser.setReplyMarkup(InlineKeyboardsStorage.getProblemSystemShowKeyboard());

            transmittedData.setState(State.WaitingQuestions);

            return messageToUser;

        } else if (textFromUser.equals(InlineButtonsStorage.BackToMenu.getCallBackData())) {

            messageToUser.setText("Здравстуйте! Это теханическая поддержка МГОК.\n \nДанный бот призван упростить взаимодействие преподавателей и Сис админов\n \nБудем рады помочь решить проблему, которая у вас возникла\n \nДля того, чтобы бот начал работу, нажмите /start");

            transmittedData.setState(State.WaitingCommandStart);

            messageToUser.setText("Выберите то что вы хотите");
            messageToUser.setReplyMarkup(InlineKeyboardsStorage.getStartKeyboard());
            transmittedData.setState(State.WaitingQuestionsOrApplicationOrHistory);

            return messageToUser;

        }
        return messageToUser;
    }
}
