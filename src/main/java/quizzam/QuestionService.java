package quizzam;

import com.sun.net.httpserver.Authenticator;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Path("/questions/")
public class QuestionService {
    private static Map<String,Question> questions = new HashMap<>();

    @GET
    @Path("/{questionID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Question getQuestion(@PathParam("questionID") String questionID) {
        Question returnQuestion = questions.get(questionID);
        if(returnQuestion==null){
            throw new NotFoundException();
        }
        return questions.get(questionID);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Question> getQuestions() { return questions.values(); }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addQuestion(Question question) {
        Question existingQuestion = questions.get(question.getID());

        // Validate the data before saving it!
        if(question.getID().isEmpty() || question.getAlternatives().length==0){
            throw new IllegalArgumentException();
        }

        for(int i = 0;i<question.getAlternatives().length;i++){

        }

        questions.put(question.getID(), question);
    }


    //CONSIDER: Maybe not support PUT, but just use POST for both kinds?
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateQuestion(Question question) {
        Question returnQuestion = questions.get(question.getID());
        if(returnQuestion==null){
            throw new NotFoundException();
        }
        questions.put(question.getID(), question);
    }


    @DELETE
    @Path("/{questionID}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteID(@PathParam("questionID") String questionID){
        //Delete the customer with this ID.
        System.out.println("Removing " + questionID + " from dataset");
        Question returnQuestion = questions.get(questionID);
        if(returnQuestion==null){
            throw new NotFoundException();
        }
        questions.remove(questionID);

    }
}
