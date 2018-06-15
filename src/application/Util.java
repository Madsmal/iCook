/**
 * @author Saadman Haq s160081
 */

package application;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.IOUtils;

public class Util {

    private static String cacheName = "cache";

    public static String getId() {
        String id = null;// = UUID.randomUUID().toString();
        File cache = new File("cache");
        try {
            if (cache.exists()) {
                id = new String(Files.readAllBytes(Paths.get(cacheName))).trim();
            } else {
                id = "0";
                Files.write(Paths.get(cacheName), id.getBytes(), StandardOpenOption.CREATE);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return id;
    }
    
    public static void storeId(String id) {
    	
         File cache = new File("cache");
         try {
             Files.write(Paths.get(cacheName), id.getBytes(), StandardOpenOption.CREATE);

         } catch (IOException e) {
             e.printStackTrace();
         }
    }

}


