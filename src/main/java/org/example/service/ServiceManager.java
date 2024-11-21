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

        methods.put(State.WaitingQuestions, startLogic::processWaitingQuestions);

        methods.put(State.WaitingSubmitApplication, startLogic::processWaitingQuestionsOrApplicationOrHistory);
        methods.put(State.WaitingApplication, startLogic::processWaitingApplication);

        methods.put(State.WaitingInputCabinetNumber, applicationLogic::processWaitingInputCabinetNumber);
        methods.put(State.WaitingInputFullName, applicationLogic::processWaitingInputFullName);
        methods.put(State.WaitingInputNumberPhone, applicationLogic::processWaitingInputNumberTelephone);
        methods.put(State.WaitingDescriptionProblem, applicationLogic::processWaitingDescriptionProblem);
        methods.put(State.WaitingQuestionAddPhoto, applicationLogic::processWaitingQuestionAddPhoto);
        methods.put(State.WaitingPhoto, applicationLogic::processWaitingPhoto);
        methods.put(State.WaitingDataVerification, applicationLogic::processWaitingDataVerification);
        methods.put(State.WaitingReadApplication, applicationLogic::processWaitingReadApplication);


        //region просмотр комп
        methods.put(State.WaitingViewProblemComputer, faqLogic::processWaitingViewProblemComputer);

        methods.put(State.WaitingFirstInfoProblemComputer, faqLogic::processWaitingFirstInfoProblemComputer);
        methods.put(State.WaitingSecondInfoProblemComputer, faqLogic::processWaitingSecondInfoProblemComputer);
        methods.put(State.WaitingThirdInfoProblemComputer, faqLogic::processWaitingThirdInfoProblemComputer);
//endregion

        //region принтер
        methods.put(State.WaitingViewProblemPrinter, faqLogic::processWaitingViewProblemPrinter);
        methods.put(State.WaitingFirstInfoProblemPrinter, faqLogic::processWaitingFirstInfoProblemPrinter);
        methods.put(State.WaitingSecondInfoProblemPrinter, faqLogic::processWaitingSecondInfoProblemPrinter);
//endregion

        //region проектор
        methods.put(State.WaitingViewProblemProjector, faqLogic::processWaitingViewProblemProjector);

        methods.put(State.WaitingFirstInfoProblemProjector, faqLogic::processWaitingFirstInfoProblemProjector);
        methods.put(State.WaitingSecondInfoProblemProjector, faqLogic::processWaitingSecondInfoProblemProjector);
        methods.put(State.WaitingThirdInfoProblemProjector, faqLogic::processWaitingThirdInfoProblemProjector);
//endregion

        //заявка

    }

    public SendMessage callLogicMethod(String textFromUser, TransmittedData transmittedData) throws Exception {
        String state = transmittedData.getState();
        return methods.get(state).process(textFromUser, transmittedData);
    }
}
