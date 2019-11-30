package net.anumbrella.oauth2.dao;

import net.anumbrella.oauth2.entity.UserInfo;

import java.util.List;

public interface UserDao extends BaseDao{

    UserInfo findByGuid(String guid);

    void saveUser(UserInfo user);

    void updateUser(UserInfo user);

    UserInfo findByUsername(String username);

    List<UserInfo> findUsersByUsername(String username);

}
