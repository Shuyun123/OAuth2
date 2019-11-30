package net.anumbrella.oauth2.dao.impl;

import net.anumbrella.oauth2.dao.UserDao;
import net.anumbrella.oauth2.entity.Privilege;
import net.anumbrella.oauth2.entity.UserInfo;
import net.anumbrella.oauth2.entity.mapper.UserRowMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Repository("userDaoJdbc")
public class UserDaoJdbc implements UserDao {

    private static UserRowMapper userRowMapper = new UserRowMapper();


    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public UserInfo findByGuid(String guid) {
        final String sql = " select * from user where  guid = ? ";
        final List<UserInfo> list = this.jdbcTemplate.query(sql, new Object[]{guid}, userRowMapper);

        UserInfo user = null;
        if (!list.isEmpty()) {
            user = list.get(0);
            user.getPrivileges().addAll(findPrivileges(user.getId()));
        }

        return user;
    }

    private Collection<Privilege> findPrivileges(int userId) {
        final String sql = " select privilege from user_privilege where user_id = ? ";
        final List<String> strings = this.jdbcTemplate.queryForList(sql, new Object[]{userId}, String.class);
        List<Privilege> privileges = new ArrayList<>(strings.size());
        privileges.addAll(strings.stream().map(Privilege::valueOf).collect(Collectors.toList()));
        return privileges;
    }

    @Override
    public void saveUser(UserInfo user) {
        final String sql = " insert into user(guid,email,password,username,phone) " +
                " values (?,?,?,?,?) ";
        this.jdbcTemplate.update(sql, ps -> {
            ps.setString(1, user.getGuid());
            ps.setString(2, user.getEmail());

            ps.setString(3, user.getPassword());
            ps.setString(4, user.getUsername());

            ps.setString(5, user.getPhone());
        });

        //get user id
        final Integer id = this.jdbcTemplate.queryForObject("select id from user where guid = ?", new Object[]{user.getGuid()}, Integer.class);

        //update privileges
        for (final Privilege privilege : user.getPrivileges()) {
            this.jdbcTemplate.update("insert into user_privilege(user_id, privilege) values (?,?)", ps -> {
                ps.setInt(1, id);
                ps.setString(2, privilege.name());
            });
        }

    }

    @Override
    public void updateUser(UserInfo user) {
        final String sql = " update user set username = ?, password = ?, phone = ?,email = ? where guid = ? ";
        this.jdbcTemplate.update(sql, ps -> {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());

            ps.setString(3, user.getPhone());
            ps.setString(4, user.getEmail());

            ps.setString(5, user.getGuid());
        });

    }

    @Override
    public UserInfo findByUsername(String username) {
        final String sql = " select * from user where username = ? ";
        final List<UserInfo> list = this.jdbcTemplate.query(sql, new Object[]{username}, userRowMapper);

        UserInfo user = null;
        if (!list.isEmpty()) {
            user = list.get(0);
            user.getPrivileges().addAll(findPrivileges(user.getId()));
        }

        return user;
    }

    @Override
    public List<UserInfo> findUsersByUsername(String username) {
        String sql = " select * from user where enable = 1 ";
        Object[] params = new Object[]{};
        if (StringUtils.isNotEmpty(username)) {
            sql += " and username like ?";
            params = new Object[]{"%" + username + "%"};
        }
        sql += " order by create_time desc ";

        final List<UserInfo> list = this.jdbcTemplate.query(sql, params, userRowMapper);
        for (UserInfo user : list) {
            user.getPrivileges().addAll(findPrivileges(user.getId()));
        }
        return list;
    }


}
