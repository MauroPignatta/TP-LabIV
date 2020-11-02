package edu.labIV.mail;

public enum MailContentType {

    PLAIN_TEXT("text/plain"),
    HTML("text/html"),
    CSS("text/css");

    private String type;

    MailContentType(String content) {
        this.type = content;
    }

    public String getType() {
        return type;
    }
}
