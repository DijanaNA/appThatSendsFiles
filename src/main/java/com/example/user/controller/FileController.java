package com.example.user.controller;

import java.io.IOException;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.user.entity.Document;
import com.example.user.entity.User;
import com.example.user.repository.DocumentRepository;
import com.example.user.repository.UserRepository;


@RestController
@RequestMapping("/file")
public class FileController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	DocumentRepository docRepository;
	
	@PostMapping("/upload/{username}")
	public ResponseEntity uploadFileInDb(@PathParam("file")MultipartFile file, @PathParam("username")String username) {
		Document doc = new Document();
		User user = userRepository.findByUsername(username);
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		doc.setFileName(fileName);
		doc.setUser(user);
		
		try {
			doc.setFile(file.getBytes());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		docRepository.save(doc);
		
		String fileDownloadURI = ServletUriComponentsBuilder.fromCurrentContextPath().path("/file/download/").path(fileName).toUriString();
		return ResponseEntity.ok(fileDownloadURI);
		
	}

}
