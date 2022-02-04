package ro.tuc.ds2020.entities.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ro.tuc.ds2020.entities.Person;

import java.util.Collection;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

public class PersonPrincipal implements UserDetails {
    private Person person;

    public PersonPrincipal(Person person) {
        this.person = person;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return stream(this.person.getAuthorities()).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return null;
    }

    @Override
    public String getPassword() {
//        return this.person.getPassword();
        return null;
    }

    @Override
    public String getUsername() {
        return this.person.getUsername();
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
