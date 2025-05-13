package com.example.demo.domain.usecase.utils;

import com.example.demo.config.security.utils.IAuthenticationContextService;
import com.example.demo.domain.exceptions.ProductBelongsOtherUserException;

public class UseCaseUtils {

    private final IAuthenticationContextService authenticationContextService;

    public UseCaseUtils(IAuthenticationContextService authenticationContextService) {
        this.authenticationContextService = authenticationContextService;
    }

    public long getUserIdFromAuthenticationContextService(){
        return authenticationContextService.getAuthenticationUserId();
    }

    public void validationProductBelongsToUserProviderId(long userIdFromProduct, long productId){
        long userId = getUserIdFromAuthenticationContextService();
        if(userIdFromProduct != userId) throw new ProductBelongsOtherUserException(productId);
    }
}
