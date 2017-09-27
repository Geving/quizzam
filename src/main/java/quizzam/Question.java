package quizzam;

public class Question {
    private String ID;
    private boolean availableForAll;
    private int category;
    //private int belongsToGame;
    private boolean shufflingAllowed;
    private String questionText;
    private String[] alternatives;
    private String solution;
    private int secondsAllotted;

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public boolean isAvailableForAll() {
        return availableForAll;
    }

    public void setAvailableForAll(boolean availableForAll) {
        this.availableForAll = availableForAll;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String[] getAlternatives() {
        return alternatives;
    }

    public void setAlternatives(String[] alternatives) {
        this.alternatives = alternatives;
    }

    public int getSecondsAllotted() {
        return secondsAllotted;
    }

    public void setSecondsAllotted(int secondsAllotted) {
        this.secondsAllotted = secondsAllotted;
    }

    //    public int getBelongsToGame() {
//        return belongsToGame;
//    }
//
//    public void setBelongsToGame(int belongsToGame) {
//        this.belongsToGame = belongsToGame;
//    }

    public boolean isShufflingAllowed() {
        return shufflingAllowed;
    }

    public void setShufflingAllowed(boolean shufflingAllowed) {
        this.shufflingAllowed = shufflingAllowed;
    }
}
