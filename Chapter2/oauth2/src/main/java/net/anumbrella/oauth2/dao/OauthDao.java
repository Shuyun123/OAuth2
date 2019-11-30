package net.anumbrella.oauth2.dao;

import net.anumbrella.oauth2.entity.OauthClientDetails;

public interface OauthDao extends BaseDao {

    OauthClientDetails findOauthClientDetails(String clientId);
}
