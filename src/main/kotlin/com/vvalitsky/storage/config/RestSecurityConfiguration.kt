package com.vvalitsky.storage.config

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

/**
 * Created by Vladislav Valitsky at 28.07.2020
 */

@Configuration
class RestSecurityConfiguration : WebSecurityConfigurerAdapter() {

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http
            .csrf().disable()
            .authorizeRequests().anyRequest().permitAll()
    }

    override fun configure(web: WebSecurity?) {
        web!!.ignoring().antMatchers(
                "/actuator/**",
                "/v2/api-docs",
                "/swagger-resources",
                "/swagger-ui.html",
                "/resources/**"
        )
    }
}
