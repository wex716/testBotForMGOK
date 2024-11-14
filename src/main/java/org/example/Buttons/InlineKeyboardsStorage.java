package org.example.Buttons;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;


public class InlineKeyboardsStorage {

    public static InlineKeyboardMarkup getStartKeyboard() {
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        List<InlineKeyboardButton> row = null;
        InlineKeyboardButton button = null;

        row = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText(InlineButtonsStorage.ShowQuestionsStart.getTitle());
        button.setCallbackData(InlineButtonsStorage.ShowQuestionsStart.getCallBackData());
        row.add(button);
        keyboard.add(row);

        row = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText(InlineButtonsStorage.SubmitApplication.getTitle());
        button.setCallbackData(InlineButtonsStorage.SubmitApplication.getCallBackData());
        row.add(button);
        keyboard.add(row);
        row = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText(InlineButtonsStorage.SubmitHistory.getTitle());
        button.setCallbackData(InlineButtonsStorage.SubmitHistory.getCallBackData());
        row.add(button);
        keyboard.add(row);

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(keyboard);

        return inlineKeyboardMarkup;
    }


    public static InlineKeyboardMarkup getProblemSystemShowKeyboard() {
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        List<InlineKeyboardButton> row = null;
        InlineKeyboardButton button = null;

        row = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText(InlineButtonsStorage.ViewProblemComputer.getTitle());
        button.setCallbackData(InlineButtonsStorage.ViewProblemComputer.getCallBackData());
        row.add(button);
        keyboard.add(row);

        row = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText(InlineButtonsStorage.ViewProblemPrinter.getTitle());
        button.setCallbackData(InlineButtonsStorage.ViewProblemPrinter.getCallBackData());
        row.add(button);
        keyboard.add(row);

        row = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText(InlineButtonsStorage.ViewProblemProjector.getTitle());
        button.setCallbackData(InlineButtonsStorage.ViewProblemProjector.getCallBackData());
        row.add(button);
        keyboard.add(row);

        row = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText(InlineButtonsStorage.BackToMenu.getTitle());
        button.setCallbackData(InlineButtonsStorage.BackToMenu.getCallBackData());
        row.add(button);
        keyboard.add(row);

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(keyboard);

        return inlineKeyboardMarkup;
    }

    public static InlineKeyboardMarkup getProblemComputerKeyboard() {
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        List<InlineKeyboardButton> row = null;
        InlineKeyboardButton button = null;

        row = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText(InlineButtonsStorage.First.getTitle());
        button.setCallbackData(InlineButtonsStorage.First.getCallBackData());
        row.add(button);
        keyboard.add(row);

        row = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText(InlineButtonsStorage.Second.getTitle());
        button.setCallbackData(InlineButtonsStorage.Second.getCallBackData());
        row.add(button);
        keyboard.add(row);

        row = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText(InlineButtonsStorage.Third.getTitle());
        button.setCallbackData(InlineButtonsStorage.Third.getCallBackData());
        row.add(button);
        keyboard.add(row);

        row = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText(InlineButtonsStorage.MovePrevShow.getTitle());
        button.setCallbackData(InlineButtonsStorage.MovePrevShow.getCallBackData());
        row.add(button);
        keyboard.add(row);

        row = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText(InlineButtonsStorage.BackToMenu.getTitle());
        button.setCallbackData(InlineButtonsStorage.BackToMenu.getCallBackData());
        row.add(button);
        keyboard.add(row);

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(keyboard);

        return inlineKeyboardMarkup;
    }
    public static InlineKeyboardMarkup getBackKeyboard() {
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        List<InlineKeyboardButton> row = null;
        InlineKeyboardButton button = null;

        row = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText(InlineButtonsStorage.MovePrevShow.getTitle());
        button.setCallbackData(InlineButtonsStorage.MovePrevShow.getCallBackData());
        row.add(button);
        keyboard.add(row);

        row = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText(InlineButtonsStorage.BackToMenu.getTitle());
        button.setCallbackData(InlineButtonsStorage.BackToMenu.getCallBackData());
        row.add(button);
        keyboard.add(row);

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(keyboard);

        return inlineKeyboardMarkup;
    }


}
