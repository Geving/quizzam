package quizzam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Path("/scoreboards/")
public class ScoreboardService {
    private static Map<String,Scoreboard> scoreboards = new HashMap<>();

    @GET
    @Path("/{scoreboardID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Scoreboard getScoreboard(@PathParam("scoreboardID") String scoreboardID) {
        Scoreboard returnScoreboard = scoreboards.get(scoreboardID);
        if(returnScoreboard==null){
            throw new NotFoundException();
        }
        return scoreboards.get(scoreboardID);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Scoreboard> getScoreboards() { return scoreboards.values(); }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addScoreboard(Scoreboard scoreboard) {
        //Scoreboard existingScoreboard = scoreboards.get(scoreboard.getID());
        scoreboards.put(scoreboard.getID(), scoreboard);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateScoreboard(Scoreboard scoreboard) {
        Scoreboard returnScoreboard = scoreboards.get(scoreboard.getID());
        if(returnScoreboard==null){
            //throw new NotFoundException();
            addScoreboard(scoreboard);
        }
        scoreboards.put(scoreboard.getID(), scoreboard);
    }


    @DELETE
    @Path("/{scoreboardID}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteID(@PathParam("scoreboardID") String scoreboardID){
        //Delete the customer with this ID.
        System.out.println("Removing " + scoreboardID + " from dataset.");
        scoreboards.remove(scoreboardID);
    }
}
