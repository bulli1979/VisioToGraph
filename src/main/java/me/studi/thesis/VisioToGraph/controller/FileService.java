package me.studi.thesis.VisioToGraph.controller;

import org.springframework.web.multipart.MultipartFile;


public class FileService {

	
	public FileService() {}
	
	public void execute(MultipartFile file) {
		if (file != null) {
			System.out.println(file.getName());
		}
		
	}
	
	public void init() {
		// TODO Auto-generated method stub
		
	}

}
