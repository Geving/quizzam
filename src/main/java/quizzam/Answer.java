package quizzam;

public class Answer {
    private String ID;
    private String playerID;
    private String gameID;
    private String questionID;
    private int answerAlt;

    public int getAnswerAlt() {
        return answerAlt;
    }

    public void setAnswerAlt(int answerAlt) {
        this.answerAlt = answerAlt;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPlayerID() {
        return playerID;
    }

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    public String getGameID() {
        return gameID;
    }

    public void setGameID(String gameID) {
        this.gameID = gameID;
    }

    public String getQuestionID() {
        return questionID;
    }

    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }
}
