package com.hirshi001.game.common.plugin.pluginutil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class ResourceUtil {

    public static Path resourceToFile(ClassLoader loader, String filePath) throws IOException {
        InputStream is = loader.getResourceAsStream(filePath);
        if(is==null) throw new FileNotFoundException(filePath);
        Path path = Files.createTempFile(filePath, "");
        Files.write(path, is.readAllBytes());
        return path;
    }


}
