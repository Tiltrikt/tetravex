package dev.tiltrikt.security.service.security;

import dev.tiltrikt.security.model.User;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class UserDetailsImpl implements UserDetails {

  @NotNull User user;

  @Override
  public @NotNull @Unmodifiable Collection<? extends GrantedAuthority> getAuthorities() {
    log.info("authorities list");
    return user.getAuthoritiesList();
  }

  @Override
  public @NotNull String getPassword() {
    log.info("password");
    return user.getPassword();
  }

  @Override
  public @NotNull String getUsername() {
    log.info("username");
    return user.getUsername();
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
