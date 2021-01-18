package ntua.softeng28.evcharge.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import ntua.softeng28.evcharge.user.User;

public class ApplicationUserDetails implements UserDetails {

    private static final long serialVersionUID = 502243307650925624L;
   
    private String username;
    private String password;
    private boolean isLoggedIn;
    private List<GrantedAuthority> authorities;

    public ApplicationUserDetails(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.isLoggedIn = user.getLoggedIn();
        this.authorities = Arrays.asList(new SimpleGrantedAuthority(user.getRole()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
        return isLoggedIn;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
}
