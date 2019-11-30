package net.anumbrella.oauth2.service;

import net.anumbrella.oauth2.entity.OauthClientDetails;

public interface OauthService {

    OauthClientDetails loadOauthClientDetails(String clientId);
}
