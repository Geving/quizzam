package quizzam;

public class Scoreboard {

    private String ID;
    private Player[] players[];

    public Scoreboard() {
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Player[][] getPlayers() {
        return players;
    }

    public void setPlayers(Player[][] players) {
        this.players = players;
    }
}
