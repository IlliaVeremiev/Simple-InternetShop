package veil.internetshop.simple.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import veil.internetshop.simple.models.User;
import veil.internetshop.simple.view.IdentificationView;

import java.util.Collection;

public class DefaultUserDetails implements UserDetails {

	private User user;

	public DefaultUserDetails(User user) {
		this.user = user;
	}

	@Override
	@JsonView(IdentificationView.class)
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return user.getAuthorities();
	}

	@Override
	@JsonIgnore
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	@JsonView(IdentificationView.class)
	public String getUsername() {
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return ! user.isLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return user.isEnabled();
	}

	public User getUser() {
		return user;
	}
}
