package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class notification {

    private int id;
    private int num_placeHolder;
    private String content;
    private String subject;
    private String language;

    public notification(@JsonProperty("id") String id, @JsonProperty("numPH")String num_placeHolder
            , @JsonProperty("content")String content,@JsonProperty("subject") String subject,
                        @JsonProperty("language")String language)
    {

        int x = Integer.parseInt(id);
        int y = Integer.parseInt(num_placeHolder);
        this.id=x;
        this.content=content;
        this.language=language;
        this.num_placeHolder=y;
        this.subject=subject;
    }
    public void setId(int id)
    {
        this.id=id;
    }
    public int getId()
    {
        return id;
    }
    public void setcontent(String con)
    {
        this.content=con;
    }
    public String getcontent()
    {
        return content;
    }

    public void setSubject(String sub)
    {
        this.subject=sub;
    }
    public String getSubject()
    {
        return subject;
    }
    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }
    public int getNum_placeHolder() {
        return num_placeHolder;
    }
    public void setNum_placeHolder(int num_placeHolder) {
        this.num_placeHolder = num_placeHolder;
    }


}
