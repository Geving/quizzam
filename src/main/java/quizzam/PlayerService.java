package quizzam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Path("/players/")
public class PlayerService {
    private static Map<String,Player> players = new HashMap<>();

    @GET
    @Path("/{playerID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Player getPlayer(@PathParam("playerID") String playerID) {
        Player returnPlayer = players.get(playerID);
        if(returnPlayer==null){
            throw new javax.ws.rs.NotFoundException();
        }
        return players.get(playerID);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Player> getPlayers() { return players.values(); }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addPlayer(Player player) {
        Player existingPlayer = players.get(player.getID());
        players.put(player.getID(), player);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void updatePlayer(Player player) {
        Player returnPlayer = players.get(player.getID());
        if(returnPlayer==null){
            throw new javax.ws.rs.NotFoundException();
        }
        players.put(player.getID(), player);
    }


    @DELETE
    @Path("/{playerID}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteID(@PathParam("playerID") String playerID){
        //Delete the customer with this ID.
        System.out.println("Removing " + playerID + " from dataset: " + players.keySet());
        players.remove(playerID);
        System.out.println("Updated dataset: " + players.keySet());
    }
}
