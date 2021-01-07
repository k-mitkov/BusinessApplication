package javaproject.BusinessApplication.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().disable()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/js/**", "/css/**").permitAll()
                .antMatchers("/","/index", "/user/login").anonymous()
                .antMatchers("/merchant/sale","/merchant/customer", "/merchant/add/customer",
                        "/merchant/delete/customer", "/merchant/update/customer",
                        "/merchant/update/customer/address", "/merchant/update/customer/phoneNumber",
                        "/merchant/update/customer/addressAndPhoneNumber",
                        "/merchant/view/customer").access("hasAuthority('USER')")
                .antMatchers("/administrator/sales","/administrator/admins",
                        "/administrator/products", "/administrator/merchants",
                        "/administrator/add/merchants","/administrator/delete/merchants",
                        "/administrator/search/merchants","/administrator/view/merchants",
                        "/administrator/view/sales","/administrator/view/administrators",
                        "/administrator/add/administrators","/administrator/search/date",
                        "/administrator/add/products","/administrator/delete/products",
                        "/administrator/update/products","/administrator/update/product/priceAndQuantity",
                        "/administrator/update/product/price",
                        "/administrator/update/product/quantity").access("hasAuthority('ADMIN')")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/user/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/home")
                .and()
                .logout()
                .logoutSuccessUrl("/");
    }
}