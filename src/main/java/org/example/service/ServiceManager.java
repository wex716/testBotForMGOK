package org.example.service;

import org.example.service.logic.ApplicationLogic;
import org.example.service.logic.FaqLogic;
import org.example.service.logic.StartLogic;
import org.example.statemachine.State;
import org.example.statemachine.TransmittedData;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.HashMap;
import java.util.Map;

public class ServiceManager {
    private Map<String, Service> methods;

    private StartLogic startLogic;
    private FaqLogic logicFAQ;
    private ApplicationLogic applicationLogic;



    public ServiceManager() {

        methods = new HashMap<>();
        startLogic = new StartLogic();
        logicFAQ = new FaqLogic();
        applicationLogic = new ApplicationLogic();

        //начало работы бота
        methods.put(State.WaitingCommandStart, startLogic::processWaitingCommandStart);

        //просмотр FAQ
        methods.put(State.WaitingViewFAQ, logicFAQ::processWaitingViewFAQ);
        methods.put(State.WaitingViewProblemComputer, logicFAQ::processWaitingViewProblemComputer);
        methods.put(State.WaitingViewProblemPrinter, logicFAQ::processWaitingViewProblemPrinter);
        methods.put(State.WaitingViewProblemProjector, logicFAQ::processWaitingViewProblemProjector);

        //работа в заявками
        methods.put(State.WaitingSubmitApplication, applicationLogic::processWaitingSubmitApplication);
        methods.put(State.WaitingViewApplicationHistory, applicationLogic::processWaitingViewApplicationHistory);






    }

    public SendMessage callLogicMethod(String textFromUser, TransmittedData transmittedData) throws Exception {
        String state = transmittedData.getState();
        return methods.get(state).process(textFromUser, transmittedData);
    }
}
