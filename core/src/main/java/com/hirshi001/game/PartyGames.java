package com.hirshi001.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.hirshi001.game.common.game.Player;
import com.hirshi001.game.common.plugin.plugins.GamePlugin;
import com.hirshi001.game.common.plugin.plugins.partygame.PartyGamePlugin;
import com.hirshi001.game.common.plugin.pluginsecurity.PluginApplicationWrapper;
import com.hirshi001.game.common.plugin.pluginsecurity.PluginManager;
import com.hirshi001.game.common.plugin.pluginsecurity.PluginSecurityManager;
import com.hirshi001.game.common.plugin.pluginsecurity.PluginSecurityPolicy;
import com.hirshi001.game.common.util.GdxSave;
import com.hirshi001.game.common.util.IpAddress;
import com.hirshi001.game.server.Server;
import org.xeustechnologies.jcl.JarClassLoader;
import org.xeustechnologies.jcl.JclObjectFactory;

import java.io.*;
import java.util.*;

public class PartyGames implements ApplicationListener {

	public static Server server;
	public static Map<UUID, Player> playerSet;

	private final PluginManager manager;
	private GdxSave pluginSave, originalSave;

	private JarClassLoader jcl;

	public static final String EXTERNAL_PATH = ".PartyGames/";




	public PartyGames() {
		super();
		manager = new PluginManager()
				.setSecurityManager(new PluginSecurityManager())
				.setSecurityPolicy(new PluginSecurityPolicy());
	}
	@Override
	public void create () {
		originalSave = new GdxSave();
		originalSave.save();

		Gdx.app = new PluginApplicationWrapper(Gdx.app);

		pluginSave = new GdxSave();
		pluginSave.save();

		playerSet = new HashMap<>();
		jcl = new JarClassLoader();


		Box2D.init();
		IpAddress.loadAddress();

		System.out.println("Hi");
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		Gdx.app.log("create log", "test log");
		Gdx.app.error("create error", "test error");
		Gdx.app.debug("create debug", "test debug");
		Gdx.app.log("create log", "test log");
		System.out.println(Gdx.app.getApplicationLogger());

		File file = Gdx.files.external(EXTERNAL_PATH + "JarPlugins/PartyGamesPluginTest-1.0-SNAPSHOT.jar").file();


		try {
			jcl.add(new FileInputStream(file));
			JclObjectFactory factory = JclObjectFactory.getInstance();
			manager.start(()->(GamePlugin)factory.create(jcl, "MainPlugin"));
		} catch (FileNotFoundException e) {
			pluginSave.set();
			manager.start(PartyGamePlugin::new);
			e.printStackTrace();
		}



/*

		pluginSave.set();
		manager.start(PartyGamePlugin::new);

 */


	}

	@Override
	public void render () {
		manager.runPluginMethod(plugin -> {
			if(plugin.isFinished()){
				plugin.dispose();
				pluginSave.set();
				manager.start(PartyGamePlugin::new);
				List<String> classesToRemove = new ArrayList<>(jcl.getLoadedClasses().keySet());
				for(String name:classesToRemove){
					jcl.unloadClass(name);
				}
				System.out.println(jcl.getLoadedClasses().size());
			}else {
				plugin.render();
			}
		});

	}
	
	@Override
	public void dispose () {
		manager.runPluginMethod(GamePlugin::dispose);
	}

	@Override
	public void pause() {
		manager.runPluginMethod(GamePlugin::pause);
	}

	@Override
	public void resume() {
		manager.runPluginMethod(GamePlugin::dispose);
	}

	@Override
	public void resize(int width, int height) {
		manager.runPluginMethod(plugin -> plugin.resize(width, height));
	}

}
