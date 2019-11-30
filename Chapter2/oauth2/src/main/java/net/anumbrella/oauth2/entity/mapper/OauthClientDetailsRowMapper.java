package net.anumbrella.oauth2.entity.mapper;

import net.anumbrella.oauth2.entity.OauthClientDetails;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OauthClientDetailsRowMapper implements RowMapper<OauthClientDetails> {


    public OauthClientDetailsRowMapper() {
    }

    @Override
    public OauthClientDetails mapRow(ResultSet rs, int i) throws SQLException {
        OauthClientDetails clientDetails = new OauthClientDetails();

        clientDetails.setClientId(rs.getString("client_id"));
        clientDetails.setResourceIds(rs.getString("resource_ids"));
        clientDetails.setClientSecret(rs.getString("client_secret"));

        clientDetails.setScope(rs.getString("scope"));
        clientDetails.setAuthorizedGrantTypes(rs.getString("authorized_grant_types"));
        clientDetails.setWebServerRedirectUri(rs.getString("web_server_redirect_uri"));

        clientDetails.setAuthorities(rs.getString("authorities"));
        clientDetails.setAccessTokenValidity(getInteger(rs, "access_token_validity"));
        clientDetails.setRefreshTokenValidity(getInteger(rs, "refresh_token_validity"));

        clientDetails.setAdditionalInformation(rs.getString("additional_information"));

        clientDetails.setEnable(rs.getBoolean("enable"));
        clientDetails.setAutoApprove(rs.getString("autoapprove"));

        return clientDetails;
    }


    private Integer getInteger(ResultSet rs, String columnName) throws SQLException {
        final Object object = rs.getObject(columnName);
        if (object != null) {
            return (Integer) object;
        }
        return null;
    }

}