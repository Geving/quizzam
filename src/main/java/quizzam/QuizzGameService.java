package quizzam;

import javax.validation.constraints.Null;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Path("/quizzgames/")
public class QuizzGameService {
    private static Map<String,QuizzGame> quizzGames = new HashMap<>();
    private static ScoreboardService sbs = new ScoreboardService();

    @GET
    @Path("/{quizzGameID}")
    @Produces(MediaType.APPLICATION_JSON)
    public QuizzGame getQuizzGame(@PathParam("quizzGameID") String quizzGameID) {
        System.out.println("Processing " + quizzGameID);
        QuizzGame returnQuizzGame = quizzGames.get(quizzGameID);
        if(returnQuizzGame==null){
            System.out.println("This game doesn't exist!");
            throw new NotFoundException();
        }

        QuestionService qs = new QuestionService();

        String[] myQuestionIDs = returnQuizzGame.getQuestions();
        int numberOfQuestions = 0;
        int totalQuestionTime = 0;
        if(myQuestionIDs != null && myQuestionIDs.length>0) {
             numberOfQuestions = myQuestionIDs.length;

            for(int i = 0;i<numberOfQuestions;i++){
                Question question = qs.getQuestion(myQuestionIDs[i]);
                //System.out.println("Question " + (i+1) + ": " + myQuestionIDs[i] + "\n" + question.getQuestionText() + "\n");
                totalQuestionTime += question.getSecondsAllotted();
            }
        } else {
            //  This means something must be wrong...
            returnQuizzGame.setStatus("error");
        }

        // Is the game active?
        String myDate =  returnQuizzGame.getTimeBegin();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date quizzStartTimeAsDate = null;
        try {
            quizzStartTimeAsDate = sdf.parse(myDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long timeDiff = (quizzStartTimeAsDate.getTime()-System.currentTimeMillis()) / 1000; // Let's handle this in seconds instead of ms
        System.out.println("Time: " + (quizzStartTimeAsDate.getTime()/ 1000) + "-" + (System.currentTimeMillis() / 1000) + "=" + timeDiff);

        if ((timeDiff > (60 * 2))){ // There is more than two minutes to start
            returnQuizzGame.setStatus("later");
        }

        if ((timeDiff < (60 * 2)) && (timeDiff > 0)){ // There is less than two minutes to start
            returnQuizzGame.setStatus("soon");
            returnQuizzGame.setRemainingTime((int)(timeDiff));

        }
        if ((timeDiff < 0) && (timeDiff > (0-totalQuestionTime))){ // The quiz is active!
            returnQuizzGame.setStatus("active");
            // Find out what question we are at, by looking at the time.
            long currentSystemTime = System.currentTimeMillis();
            long currentQuestionTime = quizzStartTimeAsDate.getTime(); // The first question starts when the quizz starts
            for(int i = 0;i < numberOfQuestions;i++){
                Question question = qs.getQuestion(myQuestionIDs[i]);
                //System.out.println("Checking question #" + (i+1));
                long timeQuestionStart = currentQuestionTime;
                currentQuestionTime += (question.getSecondsAllotted()*1000);
                long timeQuestionEnd = currentQuestionTime;
                //System.out.println("Question #" + (i+1) + " should live from " + timeQuestionStart + " to " + timeQuestionEnd + ". Current time: " + currentSystemTime);
                //System.out.println((currentSystemTime - timeQuestionStart) + "\t\t" + (currentSystemTime - timeQuestionEnd));
                if(currentSystemTime > timeQuestionStart && currentSystemTime < timeQuestionEnd){
                    // We have the correct question!
                    int timeRemaining = (int)(timeQuestionEnd - currentSystemTime)/1000;
                    returnQuizzGame.setCurrentQuestion(question);
                    returnQuizzGame.setRemainingTime(timeRemaining); //This doesn't need to be a long, because we've already checked stuff earlier.
                    System.out.println("Serving question " + (i+1) + " with " + timeRemaining + " seconds to go...");
                    break;
                }
            }
        }

        if ((timeDiff < 0) && (timeDiff < (0-totalQuestionTime))) { // The quiz is over!
            returnQuizzGame.setStatus("done");
            returnQuizzGame.setCurrentQuestion(null); // TODO: Is this a good solution?
        }

        System.out.println("Game status: " + returnQuizzGame.getStatus() + " (" + returnQuizzGame.getRemainingTime() + " sec) Number of questions: " + numberOfQuestions + "  Total time: " + totalQuestionTime);
        return quizzGames.get(quizzGameID);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<QuizzGame> getQuizzGames() { return quizzGames.values(); }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addQuizzGame(QuizzGame quizzGame) {
        //QuizzGame existingQuizzGame = quizzGames.get(quizzGame.getID());
        quizzGames.put(quizzGame.getID(), quizzGame);
    }

    //@Path("/{xquizzGameID,playerID}")

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateQuizzGame(@QueryParam("quizzGameID") String quizzGameID, @QueryParam("playerID") String playerID){
        System.out.println("Adding player " + playerID + " to game " + quizzGameID);
        QuizzGame qg = getQuizzGame(quizzGameID);
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        PlayerService ps = new PlayerService();
        if(qg!=null){
            Player p = ps.getPlayer(playerID);
            if (p!=null) {
                ArrayList<Player> tmpList =qg.getPlayers();
                tmpList.add(p);
                qg.setPlayers(tmpList);
                return;
            }
        }
        throw new NotAcceptableException();
    }

    @DELETE
    @Path("/{quizzGameID}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteID(@PathParam("quizzGameID") String quizzGameID){
        //Delete the customer with this ID.
        System.out.println("Removing " + quizzGameID + " from dataset.");
        quizzGames.remove(quizzGameID);
    }
}
