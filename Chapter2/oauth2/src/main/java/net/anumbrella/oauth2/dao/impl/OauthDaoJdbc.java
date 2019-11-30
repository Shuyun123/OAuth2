package net.anumbrella.oauth2.dao.impl;

import net.anumbrella.oauth2.dao.OauthDao;
import net.anumbrella.oauth2.entity.OauthClientDetails;
import net.anumbrella.oauth2.entity.mapper.OauthClientDetailsRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("oauthDaoJdbc")
public class OauthDaoJdbc implements OauthDao {

    private static OauthClientDetailsRowMapper oauthClientDetailsRowMapper = new OauthClientDetailsRowMapper();

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public OauthClientDetails findOauthClientDetails(String clientId) {
        final String sql = " select * from oauth_client_details where  client_id = ? ";
        final List<OauthClientDetails> list = this.jdbcTemplate.query(sql, new Object[]{clientId}, oauthClientDetailsRowMapper);
        return list.isEmpty() ? null : list.get(0);
    }
}
