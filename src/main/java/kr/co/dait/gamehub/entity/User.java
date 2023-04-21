package kr.co.dait.gamehub.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import kr.co.dait.gamehub.constant.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name="TB_USER")
@Getter @Setter
@ToString
public class User implements UserDetails{

    @Id
    @Column(name="USER_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @Column(name="USER_NAME")
    private String userName;

    @Column(name="USER_EMAIL", unique = true)
    private String userEmail;

    @Column(name="USER_PASSWORD")
    private String userPassword;

    @Column(name="USER_ADDRESS")
    private String userAddress;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collectors = new ArrayList<>();
		collectors.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return "USER";
            }
        }); 
		return collectors;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public String getPassword() {
        return this.userPassword;
    }

    @Override
    public boolean isAccountNonExpired() {
        //throw new UnsupportedOperationException("Unimplemented method 'isAccountNonExpired'");
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        //throw new UnsupportedOperationException("Unimplemented method 'isAccountNonLocked'");
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        //throw new UnsupportedOperationException("Unimplemented method 'isCredentialsNonExpired'");
        return true;
    }

    @Override
    public boolean isEnabled() {
        //throw new UnsupportedOperationException("Unimplemented method 'isEnabled'");
        return true;
    }

}
