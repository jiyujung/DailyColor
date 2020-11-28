package org.techtown.dailycolorproject;

public class AddInfo {
    private String title;
    private String content;
    private String date;
    private String showImg;

    public AddInfo(String title, String content, String date, String showImg) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.showImg = showImg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getShowImg() {
        return showImg;
    }

    public void setShowImg(String showImg) {
        this.showImg = showImg;
    }
}
