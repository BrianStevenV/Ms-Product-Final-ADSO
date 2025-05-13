package com.example.demo.config.security.utils;

import org.springframework.stereotype.Service;

import static com.example.demo.config.security.utils.SecurityUtils.getIdFromInfrastructure;

@Service
public class AuthenticationContextServiceImpl implements IAuthenticationContextService{
    @Override
    public long getAuthenticationUserId() {
        return getIdFromInfrastructure();
    }
}
