package com.starter.kopring.config

import com.starter.kopring.security.AdminAuthProvider
import com.starter.kopring.security.AdminLoginProcessingFilter
import com.starter.kopring.security.AdminUserDetailsService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler


@Configuration
@EnableWebSecurity
class SecurityConfig: WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http.csrf().disable()
            .authorizeRequests()
            .antMatchers("/api/whitelist/**").hasRole("ADMIN")
            .anyRequest().permitAll()

        http.addFilterBefore(adminLoginProcessingFilter(), UsernamePasswordAuthenticationFilter::class.java)
            .logout()
            .logoutUrl("/logout")
            .logoutSuccessHandler(LogoutSuccessHandler { _, response, authentication ->
                response.status = HttpStatus.OK.value()
                response.writer.write(authentication.name)
            })
    }

    override fun configure(web: WebSecurity) {
        web.ignoring()
            .antMatchers("/swagger-ui/**", "/swagger-resources/**")
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.authenticationProvider(adminAuthProvider())
            .userDetailsService(adminUserDetailsService())
    }

    @Bean
    fun adminLoginProcessingFilter(): AdminLoginProcessingFilter {
        val filter = AdminLoginProcessingFilter("/login")
        filter.setAuthenticationManager(authenticationManagerBean())
        filter.setAuthenticationSuccessHandler { _, response, authentication ->
            response.status = HttpStatus.OK.value()
            response.writer.write(authentication.name + " login success")
        }
        filter.setAuthenticationFailureHandler { _, response, exception ->
            response.status = HttpStatus.BAD_REQUEST.value()
            response.writer.write(exception.message + " login fail")
        }

        return filter
    }

    @Bean
    fun adminAuthProvider(): AdminAuthProvider {
        return AdminAuthProvider(adminUserDetailsService())
    }

    @Bean
    fun adminUserDetailsService(): AdminUserDetailsService {
        return AdminUserDetailsService()
    }
}
