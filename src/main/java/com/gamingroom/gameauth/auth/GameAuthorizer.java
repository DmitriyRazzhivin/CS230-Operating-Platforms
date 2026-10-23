package com.gamingroom.gameauth.auth;

import io.dropwizard.auth.Authorizer;

public class GameAuthorizer implements Authorizer<GameUser>
{
    @Override
    public boolean authorize(GameUser user, String role) {
        // Validate user session and check role assignment
        return user.getRoles() != null && user.getRoles().contains(role);
    }
}