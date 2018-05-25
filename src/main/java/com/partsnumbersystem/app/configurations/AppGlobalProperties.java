package com.partsnumbersystem.app.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by Zeeshan on 26/08/2017.
 */
@Component
@PropertySource("classpath:global.properties")
@ConfigurationProperties
public class AppGlobalProperties {

    @Value("${app.ldap.urls}")
    private String ldapUrl;

    @Value("${app.ldap.port}")
    private int ldapPort;

    @Value("${app.ldap.baseDn}")
    private String ldapBaseDn;

    @Value("${app.ldap.password}")
    private String ldapBasePassword;

    @Value("${app.ldap.userSearchFilter}")
    private String ldapUserSearchFilter;

    @Value("${app.ldap.userSearchBase}")
    private String ldapUserSearchBase;

    @Value("${app.ldap.groupSearchBase}")
    private String ldapGroupSearchBase;

    @Value("${app.ldap.groupSearchFilter}")
    private String ldapGroupSearchFilter;

    public String getLdapUrl() {
        return ldapUrl;
    }

    public void setLdapUrl(String ldapUrl) {
        this.ldapUrl = ldapUrl;
    }

    public int getLdapPort() {
        return ldapPort;
    }

    public void setLdapPort(int ldapPort) {
        this.ldapPort = ldapPort;
    }

    public String getLdapBaseDn() {
        return ldapBaseDn;
    }

    public void setLdapBaseDn(String ldapBaseDn) {
        this.ldapBaseDn = ldapBaseDn;
    }

    public String getLdapBasePassword() {
        return ldapBasePassword;
    }

    public void setLdapBasePassword(String ldapBasePassword) {
        this.ldapBasePassword = ldapBasePassword;
    }

    public String getLdapUserSearchFilter() {
        return ldapUserSearchFilter;
    }

    public void setLdapUserSearchFilter(String ldapUserSearchFilter) {
        this.ldapUserSearchFilter = ldapUserSearchFilter;
    }

    public String getLdapUserSearchBase() {
        return ldapUserSearchBase;
    }

    public void setLdapUserSearchBase(String ldapUserSearchBase) {
        this.ldapUserSearchBase = ldapUserSearchBase;
    }

    public String getLdapGroupSearchBase() {
        return ldapGroupSearchBase;
    }

    public void setLdapGroupSearchBase(String ldapGroupSearchBase) {
        this.ldapGroupSearchBase = ldapGroupSearchBase;
    }

    public String getLdapGroupSearchFilter() {
        return ldapGroupSearchFilter;
    }

    public void setLdapGroupSearchFilter(String ldapGroupSearchFilter) {
        this.ldapGroupSearchFilter = ldapGroupSearchFilter;
    }
}
