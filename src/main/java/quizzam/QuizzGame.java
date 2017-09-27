package quizzam;

import java.util.Date;
import java.util.List;

public class QuizzGame {
    private String ID;
    private String title;
    private String timeBegin;
    private String timeEnd;
    private int secsPrQuestion;
    private int minPlayers;
    private int maxPlayers;
    //private List<Question> questions;
    private String[] questions;
    private List<Player> players;
    private Scoreboard scoreboard;
    private Question currentQuestion;
    private int remainingTime;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Question getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(Question currentQuestion) {
        this.currentQuestion = currentQuestion;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(int remainingTime) {
        this.remainingTime = remainingTime;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTimeBegin() {
        return timeBegin;
    }

    public void setTimeBegin(String timeBegin) {
        this.timeBegin = timeBegin;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public int getSecsPrQuestion() {
        return secsPrQuestion;
    }

    public void setSecsPrQuestion(int secsPrQuestion) {
        this.secsPrQuestion = secsPrQuestion;
    }

    public int getMinPlayers() {
        return minPlayers;
    }

    public void setMinPlayers(int minPlayers) {
        this.minPlayers = minPlayers;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

//    public List<Question> getQuestions() {
//        return questions;
//    }
//
//    public void setQuestions(List<Question> questions) {
//        this.questions = questions;
//    }


    public String[] getQuestions() {
        return questions;
    }

    public void setQuestions(String[] questions) {
        this.questions = questions;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    public void setScoreboard(Scoreboard scoreboard) {
        this.scoreboard = scoreboard;
    }
}
