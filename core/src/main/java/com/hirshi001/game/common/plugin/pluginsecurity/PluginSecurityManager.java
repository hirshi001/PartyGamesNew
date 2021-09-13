package com.hirshi001.game.common.plugin.pluginsecurity;

import java.security.Permission;

public class PluginSecurityManager extends SecurityManager{

    @Override
    public void checkPermission(Permission perm) {
    }

    @Override
    public void checkPermission(Permission perm, Object context) {

    }

    public void check(Permission perm){

    }
}
