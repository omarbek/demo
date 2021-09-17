package kz.omarbek.demo.shared;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.EnumSet;

public enum ErrorMessages {

    WRONG_USERNAME_OR_PASSWORD_OR_TOKEN("Wrong username or password or wrong token"),
    THIS_SHOULD_NOT_BE_CALLED("This method should not be called. This method is implemented by Spring Security"),
    ;

    @Setter
    private String errorMessage;

    ErrorMessages(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
