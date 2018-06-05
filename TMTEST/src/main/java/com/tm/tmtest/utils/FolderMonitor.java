package com.tm.tmtest.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringBootConfiguration;

import com.tm.tmtest.dataporcess.ParseFiles;
@SpringBootConfiguration
public class FolderMonitor {
	private  WatchService watcher;
    private  Path dir;
    public FolderMonitor()
    {
    	
    }
 static Map<String, Integer> threds = new HashMap<String, Integer>();
    /**
     * Creates a WatchService and registers the given directory
     */
    public FolderMonitor(Path dir) throws IOException {
        this.watcher = FileSystems.getDefault().newWatchService();
        dir.register(watcher,StandardWatchEventKinds.ENTRY_CREATE);
        this.dir = dir;
    }
 
    /**
     * Process all events for the key queued to the watcher.
     */
    public void processEvents() {
        for (;;) {
 
            // wait for key to be signaled
            WatchKey key;
            try {
                key = watcher.take();
            } catch (InterruptedException x) {
                return;
            }
 
            for (WatchEvent<?> event: key.pollEvents()) {
                WatchEvent.Kind kind = event.kind();
 
                if (kind == StandardWatchEventKinds.OVERFLOW) {
                    continue;
                }
 
                //The filename is the context of the event.
                WatchEvent<Path> ev = (WatchEvent<Path>)event;
                Path filename = ev.context();
 
                //Verify that the new file is a text file.
                try {
                    Path child = dir.resolve(filename);
                    if (Files.probeContentType(child).equals("application/vnd.ms-excel")        		||
                    		Files.probeContentType(child).equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")	
                    		) {
                    	
                    if(!threds.containsKey("taskThread")){
                    	
                    	File file = filename.toFile();
                    	String name = file.getName();
                    	
                    	Thread thread1 = new Thread(new ParseFiles(name));
                        thread1.setName("taskThread");
                        thread1.start();
                     
                        threds.put("taskThread", 1);
                        break;
                    }
                       
                       
                       
                     /*  thread2.start();
                       thread3.start();*/
                    }else {
                    	break;
                    }
                    
                    
                } catch (IOException x) {
                    System.err.println(x);
                    continue;
                }
                catch(Exception d)
                {
                	d.printStackTrace();
                }
 
                //Email the file to the specified email alias.
                System.out.format("Emailing file %s%n", filename);
                //Details left to reader....
            }
 
            //Reset the key -- this step is critical if you want to receive
            //further watch events. If the key is no longer valid, the directory
            //is inaccessible so exit the loop.
            boolean valid = key.reset();
            if (!valid) {
                    break;
            }
        }
    }
 
    static void usage() {
        System.err.println("usage: java Email dir");
        System.exit(-1);
    }
    
    public static void main(String[] a)throws IOException
    {
    	
    	Path path = Paths.get(a[0]);
    	new FolderMonitor(path).processEvents();
    	
    }
	
	
}
