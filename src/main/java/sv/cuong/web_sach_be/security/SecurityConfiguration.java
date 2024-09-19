package sv.cuong.web_sach_be.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import sv.cuong.web_sach_be.service.UserService;

@Configuration
public class SecurityConfiguration {

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }

    @Bean
@Autowired
    public DaoAuthenticationProvider authenticationProvider(UserService userService){
        //SD xác thực quyền hạn của đối tương
        DaoAuthenticationProvider dap = new DaoAuthenticationProvider();
        dap.setUserDetailsService(userService);
        dap.setPasswordEncoder(passwordEncoder());
        return dap;
    }


    @Bean//cấu hình truy cập
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
          config -> config
                  .requestMatchers(HttpMethod.GET , "/sach").permitAll()
                  .requestMatchers(HttpMethod.GET , "/nguoi-dung").hasAnyAuthority("ADMIN")
                  .requestMatchers(HttpMethod.POST , "/tai-khoan/dang-ky").permitAll()

        );

        http.httpBasic(Customizer.withDefaults());
        http.csrf(csrf -> csrf.disable());
        return  http.build();

    }
}
