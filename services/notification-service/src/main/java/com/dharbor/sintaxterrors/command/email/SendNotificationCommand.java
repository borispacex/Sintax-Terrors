package com.dharbor.sintaxterrors.command.email;

public class SendNotificationCommand {
    private final String title;
    private final String content;

    public SendNotificationCommand(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}