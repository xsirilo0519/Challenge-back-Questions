package co.com.sofka.questions.model;

public class FavoriteDTO {
    private String id;
    private String questionid;
    private String uid;

    public FavoriteDTO() {
    }

    public FavoriteDTO(String id, String questionid, String uid) {
        this.id = id;
        this.questionid = questionid;
        this.uid = uid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestionid() {
        return questionid;
    }

    public void setQuestionid(String questionid) {
        this.questionid = questionid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
