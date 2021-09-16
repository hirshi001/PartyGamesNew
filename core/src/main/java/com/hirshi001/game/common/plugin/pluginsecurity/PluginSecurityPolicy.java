package com.hirshi001.game.common.plugin.pluginsecurity;

import com.badlogic.gdx.Gdx;
import org.xeustechnologies.jcl.JarClassLoader;

import javax.sound.sampled.AudioPermission;
import java.io.FilePermission;
import java.security.*;

public class PluginSecurityPolicy extends Policy {

    @Override
    public PermissionCollection getPermissions(ProtectionDomain domain) {
        PermissionCollection permissions = new Permissions();

        if(isPluginLoader(domain.getClassLoader())){
            permissions.add(new FilePermission(Gdx.files.getLocalStoragePath() + "*", "read"));
            permissions.add(new AudioPermission("play"));
            //permissions.add(new Permission);
        }
        else{
            permissions.add(new AllPermission());
        }


        return super.getPermissions(domain);
    }

    private static boolean isPluginLoader(ClassLoader loader){
        return loader instanceof JarClassLoader;
    }


}

