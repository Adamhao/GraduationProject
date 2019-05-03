package cn.edu.qdu.email;

import java.net.URL;

/**
 * Created by Adam on 2019/4/30 15:07.
 */
public class EmailTask {

    private int level = 5;//优先级
    private String subject;//邮件标题
    private String recipient;//收件邮箱
    private String content;//邮件内容
    private boolean html;//是否是html邮件
    private URL[] attachURLFile;//URL附件
    private long timestamp = System.currentTimeMillis();//创建时间，以后可能用于判断优先级

    public EmailTask() {
    }

    public EmailTask(String subject, String recipient, String content) {
        this(subject, recipient, content, false);
    }

    public EmailTask(String subject, String recipient, String content, boolean html) {
        this(subject, recipient, content, html, null);
    }

    public EmailTask(String subject, String recipient, String content, boolean html, URL[] attachURLFile) {
        this.subject = subject;
        this.recipient = recipient;
        this.content = content;
        this.html = html;
        this.attachURLFile = attachURLFile;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isHtml() {
        return html;
    }

    public void setHtml(boolean html) {
        this.html = html;
    }

    public URL[] getAttachURLFile() {
        return attachURLFile;
    }

    public void setAttachURLFile(URL[] attachURLFile) {
        this.attachURLFile = attachURLFile;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

}
