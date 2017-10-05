package quizzam;

import java.util.ArrayList;

public class QuizzGame {
    private String ID;
    private String title;
    private String timeBegin;
    private String timeEnd;
    private int secsPrQuestion;
    private int minPlayers;
    private int maxPlayers;
    private String[] questions;
    private ArrayList<Player> players;
    private Scoreboard scoreboard;
    private Question currentQuestion;
    private int remainingTime;
    private String status;

    public QuizzGame() {
        this.players = new ArrayList<Player>();
        this.scoreboard = new Scoreboard();
        //this.scoreboard.resizeScoreboard(this.questions.length);
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

    public String[] getQuestions() {
        return questions;
    }

    public void setQuestions(String[] questions) {
        this.questions = questions;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    public void setScoreboard(Scoreboard scoreboard) {
        this.scoreboard = scoreboard;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void addSinglePlayer(Player player){
        System.out.println("----------------------------------------------");
        System.out.println(player.toString());
        System.out.println(player.getID());
        System.out.println(player.getNick());
        System.out.println("==============================================");
        System.out.println(getPlayers());
        System.out.println(this.players.isEmpty());
        System.out.println(this.players.size());
        System.out.println("----------------------------------------------");
        this.players.add(player);
    }

}
