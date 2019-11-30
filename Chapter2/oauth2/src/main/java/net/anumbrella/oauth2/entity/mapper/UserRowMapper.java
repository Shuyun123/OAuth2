
package net.anumbrella.oauth2.entity.mapper;


import net.anumbrella.oauth2.entity.UserInfo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UserRowMapper implements RowMapper<UserInfo> {


    public UserRowMapper() {
    }

    @Override
    public UserInfo mapRow(ResultSet rs, int i) throws SQLException {
        UserInfo user = new UserInfo();
        user.setId(rs.getInt("id"));
        user.setGuid(rs.getString("guid"));

        user.setEmail(rs.getString("email"));
        user.setPhone(rs.getString("phone"));

        user.setPassword(rs.getString("password"));
        user.setUsername(rs.getString("username"));
        user.setLastLoginTime(rs.getTimestamp("last_login_time"));
        return user;
    }
}
