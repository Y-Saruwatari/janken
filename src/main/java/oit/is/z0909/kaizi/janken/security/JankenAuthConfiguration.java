package oit.is.z0909.kaizi.janken.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class JankenAuthConfiguration {

  /**
   * 認証処理に関する設定（誰がどのようなロールでログインできるか）
   *
   * @return
   */
  @Bean
  public InMemoryUserDetailsManager userDetailsService() {
    UserBuilder users = User.builder();
    // $ sshrun htpasswd -nbBC 10 user1/user2 isdev
    UserDetails user1 = users
        .username("user1")
        .password("$2y$10$yLvUDYwm0zvELtrUUGO/9Ot4o3Bzs/MzEmYHudwa0lTDpr01Ec2rK")
        .roles("USER")
        .build();
    UserDetails user2 = users
        .username("user2")
        .password("$2y$10$PvSxn0/3I3CL.8vWf1W8uuUC1XGORw1MUWRfYbSN/CC/U.IkN.0t.")
        .roles("USER")
        .build();
    UserDetails ほんだ = users
        .username("ほんだ")
        .password("$2y$10$K8BfodSfABEHoatKn/gQ/eRKhMFwc05ZSBpz/gyp4OnR3zJmqx/Zq")
        .roles("USER")
        .build();
    // 生成したユーザをImMemoryUserDetailsManagerに渡す（いくつでも良い）
    return new InMemoryUserDetailsManager(user1, user2, ほんだ);
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.formLogin();
    http.authorizeHttpRequests()
        .mvcMatchers("/janken").authenticated();

    http.logout().logoutSuccessUrl("/");
    http.csrf().disable();
    http.headers().frameOptions().disable();
    return http.build();
  }

  /**
   * @return BCryptPasswordEncoderを返す
   */
  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
