package com.future.netants.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by zhaofeng01 on 2018/11/26.
 */
public class AbstractPropConfig {

    private static final Logger logger = LoggerFactory.getLogger(AbstractPropConfig.class);

    protected static Properties properties;

    public synchronized static Properties loadProperties(String path) {
        if (properties == null) {
            properties = new Properties();
            try {
                FileReader fileReader = new FileReader(new File(path));
                properties.load(fileReader);

            } catch (FileNotFoundException e) {
                logger.info("not found property file {}", path, e);
                throw new RuntimeException("load properties error");
            } catch (IOException e) {
                logger.info("open file failed {}", path, e);
                throw new RuntimeException("load properties error");
            }
        }
        return properties;
    }
}
