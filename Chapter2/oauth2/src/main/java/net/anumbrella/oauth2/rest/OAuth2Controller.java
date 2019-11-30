package net.anumbrella.oauth2.rest;

import net.anumbrella.oauth2.entity.UserCredentials;
import net.anumbrella.oauth2.entity.UserInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther anumbrella
 */
@Controller
public class OAuth2Controller {

    /**
     * 资源API
     *
     * @return
     */
    @RequestMapping("/api/userinfo")
    public ResponseEntity<UserInfo> getUserInfo() {
        UserCredentials user = (UserCredentials) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        String email = user.getUsername() + "@anumbrella.net";

        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(user.getUsername());
        userInfo.setEmail(email);

        return ResponseEntity.ok(userInfo);
    }

    @GetMapping(value = {"/login"})
    public String login(Model model) {
        return "login";
    }
}
