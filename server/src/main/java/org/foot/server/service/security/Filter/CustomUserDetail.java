package org.foot.server.service.security.Filter;

import lombok.Getter;
import lombok.Setter;
import org.foot.server.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


@Getter
@Setter
public class CustomUserDetail implements UserDetails {
    private User user=new User();

    public CustomUserDetail(User user){
        this.user.setPassword(user.getPassword());
        this.user.setEmail(user.getEmail());
        this.user.setName(user.getName());
        this.user.setId(user.getId());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {

        return this.user.getPassword();

    }

    @Override
    public String getUsername() {
        return this.user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
