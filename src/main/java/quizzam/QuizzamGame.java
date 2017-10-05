package quizzam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class QuizzamGame
{
    private static QuizzGameService qgs = new QuizzGameService();
    private static QuestionService qs = new QuestionService();
    private static ScoreboardService sbs = new ScoreboardService();

    public String evaluateAnswer(String GameID, String QuestionID, String PlayerID, int AnswerAlt){
        System.out.println("Evaluating answer ("+ AnswerAlt +") from " + PlayerID + " for question " + QuestionID + " in game " + GameID);

        QuizzGame thisGame = qgs.getQuizzGame(GameID);
        if (thisGame==null){return  "Game not found!";}

        Question currentQuestion = thisGame.getCurrentQuestion();
        Question verifyQuestion = qs.getQuestion(QuestionID);
        String verifyAnswer = (AnswerAlt+1) + "";

        if (thisGame.getCurrentQuestion().getID().equalsIgnoreCase(QuestionID)){
            System.out.println("The answer is to a valid question in a valid game!");
            boolean playerOK = false;
            if (thisGame.getPlayers().isEmpty()) {
                System.out.println("There are no players in this game!");
                return "No players in game!";
            }
            for(Player p:thisGame.getPlayers()){
                if (p.getID().equalsIgnoreCase(PlayerID)){
                    playerOK=true;
                }
            }
            if (!playerOK){return "Player not in this game!";}

            //ScoreboardService sbs = new ScoreboardService();
            //sbs.addScoreboard(this);
            //String sbID = thisGame.getScoreboard().getID();
            Scoreboard sb = thisGame.getScoreboard(); //sbs.getScoreboard(sbID);
//            if(sb==null){
//                sbs.updateScoreboard(thisGame.getScoreboard());
//            }

            if(verifyQuestion.getSolution().equalsIgnoreCase(verifyAnswer)){
                // Correct!
                System.out.println("Increased score!");
                sb.setSingleScore(PlayerID,1);
            } else {
                // Wrong!
                // Don't do anything...
                System.out.println("Wrong answer!");
                sb.setSingleScore(PlayerID,0);
            }
            sbs.updateScoreboard(sb);
        } else {
            System.out.println(thisGame.getCurrentQuestion().getID() + " != " + QuestionID);
        }

        return "ok";
    }

//    private boolean isQuestionTimeValid(Question question, QuizzGame quizzGame){
//        // Is the game active?
//        String myDate =  quizzGame.getTimeBegin();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date quizzStartTimeAsDate = null;
//        try {
//            quizzStartTimeAsDate = sdf.parse(myDate);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        String[] myQuestionIDs = quizzGame.getQuestions();
//        int numberOfQuestions = 0;
//        int totalQuestionTime = 0;
//        if(myQuestionIDs != null && myQuestionIDs.length>0) {
//            numberOfQuestions = myQuestionIDs.length;
//
//            for(int i = 0;i<numberOfQuestions;i++){
//                Question questionLoop = qs.getQuestion(myQuestionIDs[i]);
//                //System.out.println("Question " + (i+1) + ": " + myQuestionIDs[i] + "\n" + question.getQuestionText() + "\n");
//                totalQuestionTime += questionLoop.getSecondsAllotted();
//            }
//        } else {
//            //  This means something must be wrong...
//            return false;
//        }
//
//        long timeDiff =  (quizzStartTimeAsDate.getTime()-System.currentTimeMillis()) / 1000; // Let's handle this in seconds instead of ms
//
//
//        if ((timeDiff < 0) && (timeDiff > (0-totalQuestionTime))){ // The quiz is active!
//            // Find out what question we are at, by looking at the time.
//            long currentSystemTime = System.currentTimeMillis();
//            long currentQuestionTime = quizzStartTimeAsDate.getTime(); // The first question starts when the quizz starts
//            for(int i = 0;i < numberOfQuestions;i++){
//                Question questionLoop = qs.getQuestion(myQuestionIDs[i]);
//                //System.out.println("Checking question #" + (i+1));
//                long timeQuestionStart = currentQuestionTime;
//                currentQuestionTime += (questionLoop.getSecondsAllotted()*1000);
//                long timeQuestionEnd = currentQuestionTime;
//                System.out.println("Question #" + (i+1) + " should live from " + timeQuestionStart + " to " + timeQuestionEnd + ". Current time: " + currentSystemTime);
//                System.out.println((currentSystemTime - timeQuestionStart) + "\t\t" + (currentSystemTime - timeQuestionEnd));
//                if(currentSystemTime > timeQuestionStart && currentSystemTime < timeQuestionEnd){
//                    // We have the correct question!
//                    int timeRemaining = (int)(timeQuestionEnd - currentSystemTime)/1000;
//                    returnQuizzGame.setCurrentQuestion(questionLoop);
//                    returnQuizzGame.setRemainingTime(timeRemaining); //This doesn't need to be a long, because we've already checked stuff earlier.
//                    System.out.println("Serving question " + (i+1) + " with " + timeRemaining + " seconds to go...");
//                    break;
//                }
//            }
//        }
//    }

}
