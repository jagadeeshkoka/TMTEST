package com.tm.tmtest.dataporcess;


import java.io.BufferedReader;
import java.io.File;

import java.io.FileReader;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tm.tmtest.model.Task;
import com.tm.tmtest.model.Team;
import com.tm.tmtest.model.TeamSkill;
import com.tm.tmtest.repository.TaskRepository;
import com.tm.tmtest.repository.TeamRepository;
import com.tm.tmtest.repository.TeamSkillRepository;
import com.tm.tmtest.utils.IConstants;

@Component
public class ParseFiles implements Runnable {

	public  ParseFiles() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	TeamRepository teamRepo;
	
	@Autowired
	TaskRepository taskRepo;
	
	@Autowired
	TeamSkillRepository teamSkillRepo;
	
	private String fileName;
	/*private String fileName;
	
	 *//**
	 * @return the fileName
	 *//*
	public String getFileName() {
		return fileName;
	}

	*//**
	 * @param fileName the fileName to set
	 *//*
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}*/

	public ParseFiles(String fileName) {
		// TODO Auto-generated constructor stub
		this.fileName = fileName;
	}
	
	void process(String fileName) throws IOException,Exception 
	{
		File file = new File("C:/Users/Jags/Downloads/csv_files/csv_files/"+fileName);
		Thread.sleep(1000);
		FileReader bs = new FileReader(file);
		try(BufferedReader br = new BufferedReader(bs)){
			
			
			String str = null;
			int cnt =0;
			while((str = br.readLine())!=null)
			{
				System.out.println("String : "+str);
				
				String[] lines = str.split(",");
				if(cnt>0){
				
					if(fileName.equals(IConstants.TASK_FILE_NAME))
					{
						taskRepo.save(new Task(lines[0],lines[1]));	
						
					}else if(fileName.equals(IConstants.TEAM_FILE_NAME))
					{
						teamRepo.save(new Team(lines[0]));
					}else if(fileName.equals(IConstants.TEAM_SKILL_FILE_NAME))
					{
						teamSkillRepo.save(new TeamSkill(lines[0],lines[1]));
					}
					
				}
				
				cnt++;
			}	
			
		}
		catch(IOException ioe)
		{
			throw ioe;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		
	}
	
	@Override
	public void run(){
		// TODO Auto-generated method stub
		
		System.out.println("FileNmae"+this.fileName);
		if(this.fileName==null) return;
		try{
			process(fileName);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
	}

	
	
	
	
	
	
}
