package jepara.app.kinar.model;

public class content_unverifmonth {
    private String title, content;


    public content_unverifmonth(String name, String content){
        this.title = name;
        this.content = content;
    }

    public void setTitle(String name){
        this.title = name;
    }

    public String getTitle(){
        return title;
    }

    public void setContent(double amount){
        this.content = content;
    }

    public String getContent(){
        return content;
    }
}
