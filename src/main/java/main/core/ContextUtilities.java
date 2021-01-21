package main.core;

import main.data.UserPrincipal;
import main.model.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class ContextUtilities {
    private ContextUtilities() {
        throw new IllegalStateException("Utility class");
    }

    public static int getCurrentUserId() {
        return ((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                .getUser().getId();
    }

    public static User getCurrentUser() {
        return ((UserPrincipal) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal()).getUser();
    }
}
