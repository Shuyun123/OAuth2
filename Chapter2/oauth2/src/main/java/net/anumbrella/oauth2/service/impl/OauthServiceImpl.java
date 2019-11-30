package net.anumbrella.oauth2.service.impl;

import net.anumbrella.oauth2.dao.OauthDao;
import net.anumbrella.oauth2.entity.OauthClientDetails;
import net.anumbrella.oauth2.service.OauthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("oauthService")
public class OauthServiceImpl implements OauthService {

    private static final Logger LOG = LoggerFactory.getLogger(OauthServiceImpl.class);

    @Autowired
    private OauthDao oauthDao;

    @Override
    @Transactional(readOnly = true)
    public OauthClientDetails loadOauthClientDetails(String clientId) {
        return oauthDao.findOauthClientDetails(clientId);
    }
}
