package org.example.statemachine;

public class State {

    public final static String WaitingCommandStart = "WaitingCommandStart";

    //region выбор что надо (вопросы, заявка, история)
    public final static String WaitingQuestionsOrApplicationOrHistory = "WaitingQuestionsOrApplicationOrHistory";
    public final static String WaitingQuestions = "WaitingQuestions";

    public final static String WaitingSubmitApplication = "WaitingSubmitApplication";
    public final static String WaitingApplication = "WaitingApplication";
//endregion

    //region компьютер
    public final static String WaitingViewProblemComputer = "WaitingViewProblemComputer";

    public final static String WaitingFirstInfoProblemComputer = "WaitingFirstInfoProblemComputer";
    public final static String WaitingSecondInfoProblemComputer = "WaitingSecondInfoProblemComputer";
    public final static String WaitingThirdInfoProblemComputer = "WaitingThirdInfoProblemComputer";

//endregion

    //region принтер
    public final static String WaitingViewProblemPrinter = "WaitingViewProblemPrinter";

    public final static String WaitingFirstInfoProblemPrinter = "WaitingFirstInfoProblemPrinter";
    public final static String WaitingSecondInfoProblemPrinter = "WaitingSecondInfoProblemPrinter";

    //endregion

    //region проектор
    public final static String WaitingViewProblemProjector = "WaitingViewProblemProjector";

    public final static String WaitingFirstInfoProblemProjector = "WaitingFirstInfoProblemProjector";

    public final static String WaitingSecondInfoProblemProjector = "WaitingSecondInfoProblemProjector";

    public final static String WaitingThirdInfoProblemProjector = "WaitingThirdInfoProblemProjector";

    //endregion

    //region заявка
    public final static String WaitingInputCabinetNumber = "WaitingInputCabinetNumber";
    public final static String WaitingInputFullName = "WaitingInputFullName";
    public final static String WaitingInputNumberPhone = "WaitingInputNumberTelephone";
    public final static String WaitingDescriptionProblem = "WaitingDescriptionProblem";
    public final static String WaitingAddPhoto = "WaitingAddPhoto";
    public final static String WaitingDataVerification = "WaitingDataVerification";
    public final static String WaitingReadApplication = "WaitingReadApplication";

    //endregion
}
