package com.hirshi001.game.common.plugin.pluginsecurity;

import com.hirshi001.game.common.plugin.plugins.GamePlugin;

import java.security.Policy;
import java.util.function.Supplier;

public class PluginManager {

    private GamePlugin plugin;
    private PluginSecurityManager securityManager;
    private PluginSecurityPolicy securityPolicy;

    private Policy oldPolicy;
    private SecurityManager oldManager;


    public PluginManager(){

    }

    public void start(Supplier<GamePlugin> pluginSupplier){
        enablePluginMode();
        plugin = pluginSupplier.get();
        plugin.create();
        disablePluginMode();
    }

    public void runPluginMethod(PluginRunnable pluginCallable){
        enablePluginMode();
        pluginCallable.run(plugin);
        disablePluginMode();
    }

    public void enablePluginMode(){
        oldPolicy = Policy.getPolicy();
        oldManager = System.getSecurityManager();


        Policy.setPolicy(securityPolicy);
        System.setSecurityManager(securityManager);
    }

    public void disablePluginMode(){
        Policy.setPolicy(oldPolicy);
        System.setSecurityManager(oldManager);

    }

    public GamePlugin getPlugin(){
        return plugin;
    }

    public PluginManager setSecurityManager(PluginSecurityManager securityManager) {
        this.securityManager = securityManager;
        return this;
    }

    public PluginSecurityManager getSecurityManager() {
        return securityManager;
    }


    public PluginManager setSecurityPolicy(PluginSecurityPolicy securityPolicy) {
        this.securityPolicy = securityPolicy;
        return this;
    }


    public PluginSecurityPolicy getSecurityPolicy() {
        return securityPolicy;
    }

}
