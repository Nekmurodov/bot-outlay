package org.example.botoutlay.dbConfig.valid;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.example.botoutlay.dbConfig.entityAndService.entity.User;
import org.example.botoutlay.dbConfig.exception.ForbiddenException;
import org.example.botoutlay.dbConfig.util.MessageKey;
import org.example.botoutlay.dbConfig.util.MessageService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class CheckPermissionExecutor {

    @Before(value = "@annotation(checkPermission)")
    public void beforeCheckPermission(CheckPermission checkPermission) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        boolean exist = false;
        for (GrantedAuthority authority : principal.getAuthorities()) {
            if (authority.getAuthority().equals(checkPermission.value())) {
                exist = true;
                break;
            }
        }
        if (!exist) throw new ForbiddenException(checkPermission.value(), MessageService.getMessage(MessageKey.FORBIDDEN));
    }

}
