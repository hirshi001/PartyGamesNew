package com.hirshi001.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.hirshi001.game.common.game.Player;
import com.hirshi001.game.common.plugin.plugins.GamePlugin;
import com.hirshi001.game.common.plugin.plugins.partygame.PartyGamePlugin;
import com.hirshi001.game.common.plugin.pluginsecurity.PluginManager;
import com.hirshi001.game.common.util.GdxSave;
import com.hirshi001.game.common.util.IpAddress;
import com.hirshi001.game.server.Server;
import org.xeustechnologies.jcl.JarClassLoader;
import org.xeustechnologies.jcl.JclObjectFactory;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PartyGames implements ApplicationListener {

	public static Server server;
	public static Map<UUID, Player> playerSet;

	private final PluginManager manager;
	private GdxSave gdxSave;

	private JarClassLoader jcl;

	public static final String EXTERNAL_PATH = ".PartyGames/";




	public PartyGames(PluginManager manager) {
		super();
		this.manager = manager;

	}

	@Override
	public void create () {
		gdxSave = new GdxSave();
		playerSet = new HashMap<>();
		jcl = new JarClassLoader();

		File file = Gdx.files.external(EXTERNAL_PATH + "JarPlugins/hi.jar").file();

		try {
			jcl.add(new FileInputStream(file));
			JclObjectFactory factory = JclObjectFactory.getInstance();
			GamePlugin plugin = (GamePlugin)factory.create(jcl, "hi");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}


		System.out.println(file.toURI());
		if(!file.exists()) {
			try {
				file.getParentFile().mkdirs();
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}


		Box2D.init();
		IpAddress.loadAddress();
		System.setOut((PrintStream) Gdx.app.getApplicationLogger());

		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		Gdx.app.log("create log", "test log");
		Gdx.app.error("create error", "test error");
		Gdx.app.debug("create debug", "test debug");
		Gdx.app.log("create log", "test log");

		gdxSave.save();
		manager.start(PartyGamePlugin::new);
	}

	@Override
	public void render () {
		manager.runPluginMethod(plugin -> {
			if(plugin.isFinished()){
				plugin.dispose();
				gdxSave.set();
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
