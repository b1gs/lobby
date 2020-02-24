package com.example.lobby.security;

import com.example.lobby.domain.Player;
import com.example.lobby.repo.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DatabaseAuthenticationProvider implements AuthenticationProvider {


    private final PlayerRepository playerRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken authToken = (UsernamePasswordAuthenticationToken) authentication;

        Player player = playerRepository.getPlayerByUsername(authToken.getPrincipal().toString());

        if (player == null) {
            return null;
        } else if (player.getPassword().equals(authToken.getCredentials())) {
            List<GrantedAuthority> authorities = null;
            if ("admin".equals(authToken.getCredentials())) {
                authorities = AuthorityUtils.createAuthorityList("ROLE_ADMIN");
            } else {
                authorities = AuthorityUtils.createAuthorityList("ROLE_PLAYER");
            }
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(authToken.getName(), authToken.getCredentials(), authorities);
            token.setDetails(createPlayerDetails(player));
            return token;
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    private PlayerDetails createPlayerDetails(Player player) {
        PlayerDetails details = new PlayerDetails();
        details.setId(player.getId());
        return details;
    }
}
