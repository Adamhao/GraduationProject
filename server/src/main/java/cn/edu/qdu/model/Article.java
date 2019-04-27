package cn.edu.qdu.model;

/**
 * Created by Adam on 2019/4/27 14:20.
 */
public class Article {

    private String title;
    private String content;
    private String url;

    public Article() {
    }

    public Article(String title, String content, String url) {
        this.title = title;
        this.content = content;
        this.url = url;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
