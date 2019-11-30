package net.anumbrella.oauth2.service.impl;

import net.anumbrella.oauth2.dao.UserDao;
import net.anumbrella.oauth2.entity.UserCredentials;
import net.anumbrella.oauth2.entity.UserInfo;
import net.anumbrella.oauth2.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);


    @Autowired
    private UserDao userDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo user = userDao.findByUsername(username);
        if (user == null || user.getEnable() != 0 ) {
            throw new UsernameNotFoundException("Not found any user for username[" + username + "]");
        }
        return new UserCredentials(user);
    }

}
