package com.tm.unittests;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import com.tm.tmtest.repository.TeamRepository;
import com.tm.tmtest.utils.FolderMonitor;

import ch.qos.logback.core.net.SyslogOutputStream;

@RunWith(SpringRunner.class)
//@ComponentScan(basePackages={"com.tm.tmtest.model.*", "com.tm.tmtest.repository.*"})
@SpringBootTest(classes={com.tm.tmtest.utils.FolderMonitor.class, com.tm.tmtest.EasyNotesApplication.class})

public class EasyNotesApplicationTests {

	
	
	@Test
	public void contextLoads() throws Exception {
		Path path = Paths.get("C:/Users/Jags/Downloads/csv_files/csv_files");
		new FolderMonitor(path).processEvents();
		
	}

}
