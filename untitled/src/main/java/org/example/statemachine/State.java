package org.example.statemachine;

public class State {
    //начало бота
    public final static String WaitingCommandStart = "WaitingCommandStart";

    //просмотреть FAQ
    public final static String WaitingViewFAQ = "WaitingViewFAQ";

    public final static String WaitingViewProblemComputer = "WaitingViewProblemComputer";

    public final static String WaitingViewProblemPrinter = "WaitingViewProblemPrinter";

    public final static String WaitingViewProblemProjector = "WaitingViewProblemProjector";

    //оставить заявку
    public final static String WaitingSubmitApplication = "WaitingSubmitApplication";

    //посмотреть история оставленных заявок
    public final static String WaitingViewApplicationHistory = "WaitingViewApplicationHistory";
}
