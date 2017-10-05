package quizzam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

//public class AnswerService {
    @Path("/answers/")
    public class AnswerService {
    private static Map<String,Answer> answers = new HashMap<>();
    private static QuizzamGame QG = new QuizzamGame();


//        @GET
//        @Path("/{answerID}")
//        @Produces(MediaType.APPLICATION_JSON)
//        public Answer getAnswer(@PathParam("answerID") String answerID) {
//            Answer returnAnswer = answers.get(answerID);
//            if(returnAnswer==null){
//                throw new NotFoundException();
//            }
//            return answers.get(answerID);
//        }

//        @GET
//        @Produces(MediaType.APPLICATION_JSON)
//        public Collection<Answer> getAnswers() { return answers.values(); }

        @POST
        @Consumes(MediaType.APPLICATION_JSON)
        public void addAnswer(Answer answer){
            Answer existingAnswer = answers.get(answer.getID());

            // Validate the data before doing anything with it!
            if(answer.getQuestionID().isEmpty() || answer.getPlayerID().isEmpty() || answer.getGameID().isEmpty()){
                throw new IllegalArgumentException();
            }

            // We now know that the data contains all of the necessary information.
            // Next step is to evaluate and score the input.
            QG.evaluateAnswer(answer.getGameID(),answer.getQuestionID(),answer.getPlayerID(),answer.getAnswerAlt());
            //answers.put(answer.getID(), answer);
        }


//        //CONSIDER: Maybe not support PUT, but just use POST for both kinds?
//        @PUT
//        @Consumes(MediaType.APPLICATION_JSON)
//        public void updateAnswer(Answer answer) {
//            Answer returnAnswer = answers.get(answer.getID());
//            if(returnAnswer==null){
//                throw new NotFoundException();
//            }
//            answers.put(answer.getID(), answer);
//        }


//        @DELETE
//        @Path("/{answerID}")
//        @Consumes(MediaType.APPLICATION_JSON)
//        public void deleteID(@PathParam("answerID") String answerID){
//            //Delete the customer with this ID.
//            System.out.println("Removing " + answerID + " from dataset");
//            Answer returnAnswer = answers.get(answerID);
//            if(returnAnswer==null){
//                throw new NotFoundException();
//            }
//            answers.remove(answerID);
//
//        }

    }
