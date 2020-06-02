package com.wg.vhr.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wg.vhr.model.Hr;
import com.wg.vhr.model.ResponseBean;
import com.wg.vhr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.lang.model.element.VariableElement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * author:insane
 * Date:2020/5/1620:04
 **/
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserService userService;
    @Autowired
    PermissionDecisionManager permissionDecisionManager;
    @Autowired
    PermissionFilter permissionFilter;
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/login");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // .anyRequest().authenticated()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                        object.setAccessDecisionManager(permissionDecisionManager);
                        object.setSecurityMetadataSource(permissionFilter);
                        return object;
                    }
                })
                .and()
                .formLogin()
                .loginProcessingUrl("/doLogin")
                .loginPage("/login")
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        response.setContentType("application/json;charset=utf-8");
                        PrintWriter out = response.getWriter();
                        Hr hr = (Hr) authentication.getPrincipal();
                        hr.setPassword(null);
                        ResponseBean success = ResponseBean.success("登录成功！", hr);
                        String s = new ObjectMapper().writeValueAsString(success);
                        out.write(s);
                        out.flush();
                        out.close();
                    }
                })
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
                        response.setContentType("application/json;charset=utf-8");
                        PrintWriter out = response.getWriter();
                        ResponseBean responseBean = ResponseBean.error("登陆失败！");
                        if (e instanceof LockedException) {
                            responseBean.setMsg("账户被锁定，请联系管理员！");
                        } else if (e instanceof AccountExpiredException) {
                            responseBean.setMsg("账户过期，请联系管理员！");
                        } else if (e instanceof CredentialsExpiredException) {
                            responseBean.setMsg("密码过期，请联系管理员！");
                        } else if (e instanceof BadCredentialsException) {
                            responseBean.setMsg("账号密码错误，请重新输入！");
                        } else if (e instanceof DisabledException) {
                            responseBean.setMsg("账户被禁用，请联系管理员！");
                        } else {
                            responseBean.setMsg("登录失败！");
                        }
                        out.write(new ObjectMapper().writeValueAsString(responseBean));
                        out.flush();
                        out.close();
                    }
                })
                .permitAll()
                .and()
                .logout()
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        response.setContentType("application/json;charset=utf-8");
                        PrintWriter out = response.getWriter();
                        ResponseBean success = ResponseBean.success("注销登录成功！");
                        String s = new ObjectMapper().writeValueAsString(success);
                        out.write(s);
                        out.flush();
                        out.close();
                    }
                })
                .and()
                .csrf().disable().exceptionHandling()
                //当没有登录认证时，在这里进行结果处理
                .authenticationEntryPoint(new AuthenticationEntryPoint() {
            @Override
            public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
                response.setContentType("application/json;charset=utf-8");
                PrintWriter out = response.getWriter();
                ResponseBean responseBean = ResponseBean.error("访问失败！");
                if (exception instanceof InsufficientAuthenticationException) {
                    responseBean.setMsg("请求失败，请联系管理员！");
                }
                out.write(new ObjectMapper().writeValueAsString(responseBean));
                out.flush();
                out.close();
            }
        });

    }
}
