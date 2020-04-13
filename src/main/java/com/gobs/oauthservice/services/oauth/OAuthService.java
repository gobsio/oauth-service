package com.gobs.oauthservice.services.oauth;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.inject.Inject;
import com.gobs.oauthservice.domain.entities.oauth.OAuthClientAdditionalInformation;
import com.gobs.oauthservice.domain.entities.oauth.OAuthClientDetails;
import com.gobs.oauthservice.repositories.oauth.OAuthClientRepository;
import com.gobs.oauthservice.repositories.users.UsersRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;

@Service
public class OAuthService {

    @Inject
    UsersRepository userRepository;

    @Inject
    OAuthClientRepository authClientRepository;

    @Resource(name = "tokenServices")
    ConsumerTokenServices tokenServices;

    @Resource(name = "tokenStore")
    TokenStore tokenStore;

    public OAuthClientDetails save(OAuthClientAdditionalInformation additionalInformation) throws Exception {
        OAuthClientDetails oauthClientDetails = new OAuthClientDetails();

        String clientId = getRandomHexString(20);
        String clientSecret = getRandomHexString(40);

        additionalInformation.setClientId(clientId);
        additionalInformation.setClientSecret(clientSecret);

        oauthClientDetails.setClientId(clientId);
        oauthClientDetails.setClientSecret(new BCryptPasswordEncoder().encode(clientSecret));
        oauthClientDetails.setAdditionalInformation(additionalInformation);
        oauthClientDetails.setWebServerRedirectUri(additionalInformation.getRedirectUris());
        OAuthClientDetails registered = authClientRepository.save(oauthClientDetails);

        return registered;
    }

    public Page<OAuthClientDetails> fetchAll(Integer pageIndex, Integer pageSize, Principal principal) {
        return authClientRepository.findAll(PageRequest.of(pageIndex, pageSize));
    }

    /**
     * *********************************************************************************
     * * Access Tokens
     * *********************************************************************************
     */
    public List<String> fetchAccessTokensByClientId(String clientId) {
        List<String> tokenValues = new ArrayList<String>();
        Collection<OAuth2AccessToken> tokens = tokenStore.findTokensByClientId(clientId);
        if (tokens != null) {
            for (OAuth2AccessToken token : tokens) {
                tokenValues.add(token.getValue());
            }
        }
        return tokenValues;
    }

    private String getRandomHexString(int numchars) {
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        while (sb.length() < numchars) {
            sb.append(Integer.toHexString(r.nextInt()));
        }
        return sb.toString().substring(0, numchars);
    }

}
