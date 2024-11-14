package org.example.service;

import org.example.service.logic.ApplicationLogic;
import org.example.service.logic.FaqLogic;
import org.example.service.logic.HistoryApplicationLogic;
import org.example.service.logic.StartLogic;
import org.example.statemachine.State;
import org.example.statemachine.TransmittedData;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.HashMap;
import java.util.Map;

public class ServiceManager {
    private Map<String, Service> methods;

    private StartLogic startLogic;
    private FaqLogic faqLogic;
    private ApplicationLogic applicationLogic;
    private HistoryApplicationLogic historyApplicationLogic;



    public ServiceManager() {

        methods = new HashMap<>();
        startLogic = new StartLogic();
        faqLogic = new FaqLogic();
        applicationLogic = new ApplicationLogic();

        //начало работы бота
        methods.put(State.WaitingCommandStart, startLogic::processWaitingCommandStart);
        methods.put(State.WaitingQuestionsOrApplicationOrHistory, startLogic::processWaitingQuestionsOrApplicationOrHistory);

        //просмотр FAQ
//        methods.put(State.WaitingViewFAQ, faqLogic::processWaitingViewFAQ);
//        methods.put(State.WaitingViewProblemComputer, faqLogic::processWaitingViewProblemComputer);
//        methods.put(State.WaitingViewProblemPrinter, faqLogic::processWaitingViewProblemPrinter);
//        methods.put(State.WaitingViewProblemProjector, faqLogic::processWaitingViewProblemProjector);

        //работа в заявками
//        methods.put(State.WaitingSubmitApplication, applicationLogic::processWaitingSubmitApplication);
//        methods.put(State.WaitingInputAddress, applicationLogic::processWaitingInputAddress);
//        methods.put(State.WaitingInputCabinetNumber, applicationLogic::processWaitingInputCabinetNumber);
//        methods.put(State.WaitingInputFullName, applicationLogic::processWaitingInputFullName);
//        methods.put(State.WaitingDescriptionProblem, applicationLogic::processWaitingDescriptionProblem);
//        methods.put(State.WaitingDataVerification, applicationLogic::processWaitingDataVerification);

        //история заявок
//        methods.put(State.WaitingViewApplicationHistory, historyApplicationLogic::processWaitingViewApplicationHistory);
//        methods.put(State.WaitingFirstViewCommands, historyApplicationLogic::processWaitingFirstViewCommands);
//        methods.put(State.WaitingMiddleViewCommands, historyApplicationLogic::processWaitingMiddleViewCommands);
//        methods.put(State.WaitingLastViewCommands, historyApplicationLogic::processWaitingLastViewCommands);
    }

    public SendMessage callLogicMethod(String textFromUser, TransmittedData transmittedData) throws Exception {
        String state = transmittedData.getState();
        return methods.get(state).process(textFromUser, transmittedData);
    }
}
