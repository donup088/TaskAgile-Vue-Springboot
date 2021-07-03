package com.taskagile.service.user;

import com.taskagile.domain.user.User;
import com.taskagile.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

@Service
@RequiredArgsConstructor
public class OAuth2ClientService implements OAuth2AuthorizedClientService {
    private final UserRepository userRepository;

    @Override
    public void saveAuthorizedClient(OAuth2AuthorizedClient oAuth2AuthorizedClient, Authentication authentication) {
        OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
        Long id = Long.valueOf(oauth2User.getAttributes().get("id").toString());
        if (!userRepository.existsUserByKakaoId(id)) {
            String nickname = (String)
                    ((LinkedHashMap)
                            ((LinkedHashMap) oauth2User.getAttribute("kakao_account"))
                                    .get("profile"))
                            .get("nickname");

            userRepository.save(User.createKakao(id, nickname));
        }
    }

    @Override
    public <T extends OAuth2AuthorizedClient> T loadAuthorizedClient(String clientRegistrationId, String principalName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void removeAuthorizedClient(String clientRegistrationId, String principalName) {
        throw new UnsupportedOperationException();
    }
}
