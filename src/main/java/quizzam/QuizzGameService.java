package quizzam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Path("/quizzgames/")
public class QuizzGameService {
    private static Map<String,QuizzGame> quizzGames = new HashMap<>();

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

        QuestionService qs = new QuestionService(); //QUESTION: Can I actually do this? It doesn't seem to work.

        String[] myQuestionIDs = returnQuizzGame.getQuestions();
        int numberOfQuestions = 0;
        int totalQuestionTime = 0;
        if(myQuestionIDs != null) {
             numberOfQuestions = myQuestionIDs.length;

            for(int i = 0;i<numberOfQuestions;i++){
                Question question = qs.getQuestion(myQuestionIDs[i]);
                totalQuestionTime += question.getSecondsAllotted();
            }
        } else {
            //  This means something must be wrong...
            returnQuizzGame.setStatus("error");

        }

        // Is the game active?
        String myDate =  returnQuizzGame.getTimeBegin();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = sdf.parse(myDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long timeDiff =  date.getTime()-System.currentTimeMillis();
        System.out.println("TimeDiff: " + timeDiff);

        if ((timeDiff > (1000 * 60 * 2))){ // There is more than two minutes to start
            returnQuizzGame.setStatus("later");
        }

        if ((timeDiff < (1000 * 60 * 2)) && (timeDiff > 0)){ // There is less than two minutes to start
            returnQuizzGame.setStatus("soon");
        }
        if ((timeDiff < 0) && (timeDiff > totalQuestionTime)){ // The quiz is active!
            returnQuizzGame.setStatus("active");

            // Find out what question we are at, by looking at the time.
            int currentQuestionTime = 0;
            for(int i = 0;i<numberOfQuestions;i++){
                Question question = qs.getQuestion(myQuestionIDs[i]);
                long timeQuestionStart = currentQuestionTime;
                currentQuestionTime += question.getSecondsAllotted();
                long timeQuestionEnd = currentQuestionTime;
                if(System.currentTimeMillis() > timeQuestionStart && System.currentTimeMillis() < timeQuestionEnd){
                    // We have the correct question!
                    int timeRemaining = (int)(timeQuestionEnd - System.currentTimeMillis());
                    returnQuizzGame.setCurrentQuestion(question);
                    returnQuizzGame.setRemainingTime(timeRemaining); //This doesn't need to be a long, because we've already checked stuff earlier.
                    System.out.println("Serving question " + (i+1) + " with " + timeRemaining + " seconds to go...");
                    break;
                }
            }
        }

        if (timeDiff < totalQuestionTime){ // The quiz is over!
            returnQuizzGame.setStatus("done");
        }

        System.out.println("Game status: " + returnQuizzGame.getStatus());
        return quizzGames.get(quizzGameID);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<QuizzGame> getQuizzGames() { return quizzGames.values(); }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addQuizzGame(QuizzGame quizzGame) {
        QuizzGame existingQuizzGame = quizzGames.get(quizzGame.getID());
        quizzGames.put(quizzGame.getID(), quizzGame);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateQuizzGame(QuizzGame quizzGame) {
        QuizzGame returnQuizzGame = quizzGames.get(quizzGame.getID());
        if(returnQuizzGame==null){
            throw new NotFoundException();
        }
        quizzGames.put(quizzGame.getID(), quizzGame);
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
