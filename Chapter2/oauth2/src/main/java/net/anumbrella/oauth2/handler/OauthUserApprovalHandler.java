package net.anumbrella.oauth2.handler;

import net.anumbrella.oauth2.entity.OauthClientDetails;
import net.anumbrella.oauth2.service.OauthService;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler;

public class OauthUserApprovalHandler extends TokenStoreUserApprovalHandler {

    private OauthService oauthService;

    public OauthUserApprovalHandler() {
    }


    @Override
    public boolean isApproved(AuthorizationRequest authorizationRequest, Authentication userAuthentication) {
        if (super.isApproved(authorizationRequest, userAuthentication)) {
            return true;
        }
        if (!userAuthentication.isAuthenticated()) {
            return false;
        }

        // 自定义方法，根据请求内容的clientId 获取数据库中client中的详情信息
        OauthClientDetails clientDetails = oauthService.loadOauthClientDetails(authorizationRequest.getClientId());
        return clientDetails != null && clientDetails.isEnable();

    }

    public void setOauthService(OauthService oauthService) {
        this.oauthService = oauthService;
    }
}

