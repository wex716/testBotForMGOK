package org.example.statemachine;

public class State {
    //начало бота
    public final static String WaitingCommandStart = "WaitingCommandStart";
    public final static String WaitingQuestionsOrApplicationOrHistory = "WaitingQuestionsOrApplicationOrHistory";

    //просмотреть FAQ
    public final static String WaitingViewFAQ = "WaitingViewFAQ";

    public final static String WaitingViewProblemComputer = "WaitingViewProblemComputer";
    public final static String WaitingViewProblemPrinter = "WaitingViewProblemPrinter";
    public final static String WaitingViewProblemProjector = "WaitingViewProblemProjector";

    //оставить заявку
    public final static String WaitingSubmitApplication = "WaitingSubmitApplication";

    public final static String WaitingInputAddress = "WaitingInputAddress";
    public final static String WaitingInputCabinetNumber = "WaitingInputCabinetNumber";
    public final static String WaitingInputFullName = "WaitingInputFullName";
    public final static String WaitingInputPhoneNumber = "WaitingInputPhoneNumber";
    public final static String WaitingDescriptionProblem = "WaitingDescriptionProblem";
    public final static String WaitingDataVerification = "WaitingDataVerification";


    //хотите добавить фото и тд

    //посмотреть история оставленных заявок
    public final static String WaitingViewApplicationHistory = "WaitingViewApplicationHistory";

    public final static String WaitingFirstViewCommands = "WaitingFirstViewCommands";
    public final static String WaitingMiddleViewCommands = "WaitingMiddleViewCommands";
    public final static String WaitingLastViewCommands = "WaitingLastViewCommands";


}
