package com.care.component;

import com.care.model.users.User;
import org.springframework.context.ApplicationEvent;

public class OnPasswordResetRequestEvent extends ApplicationEvent {

    private User user;
    private String appUrl;

    public OnPasswordResetRequestEvent(User user,
                                       String appUrl){
        super(user);
        this.user = user;
        this.appUrl = appUrl;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }
}
