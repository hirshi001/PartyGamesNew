package com.hirshi001.game.common.plugin.pluginutil;

import com.badlogic.gdx.ai.FileSystem;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.*;

public class PluginFileSystem {

    private static final byte[] data = new byte[16384];

    private static FileAttribute<Set<PosixFilePermission>> attributes = PosixFilePermissions.asFileAttribute(new TreeSet<PosixFilePermission>(){{
        add(PosixFilePermission.OWNER_READ);
        add(PosixFilePermission.GROUP_READ);
        add(PosixFilePermission.OTHERS_READ);
        add(PosixFilePermission.OWNER_WRITE);
    }});

    public static synchronized File create(ClassLoader loader, String filePath) throws IOException {
        InputStream is = loader.getResourceAsStream(filePath);
        if(is==null) throw new FileNotFoundException(filePath);

        Path path = Files.createTempFile(filePath, null, attributes);

        File file = path.toFile();
        file.deleteOnExit();

        FileOutputStream outputStream = new FileOutputStream(file);

        int nRead;
        while ((nRead = is.read(data, 0, data.length)) != -1) {
            outputStream.write(data, 0, nRead);
        }
        outputStream.flush();
        outputStream.close();
        return file;
    }


}
