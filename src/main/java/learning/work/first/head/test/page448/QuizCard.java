package learning.work.first.head.test.page448;

/**
 * Created by qiong.liu on 2018/11/19.
 */
public class QuizCard {

    private String question;
    private String answer;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public QuizCard(String question, String answer) {
        this.question=question;
        this.answer=answer;

    }
}
