package com.hirshi001.game.common.plugin.pluginutil;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ResourceUtil {

    public static byte[] resourceToByteArray(ClassLoader loader, String filePath) throws IOException {
        InputStream is = loader.getResourceAsStream(filePath);
        if(is==null) throw new FileNotFoundException(filePath);
        return is.readAllBytes();
    }


}
