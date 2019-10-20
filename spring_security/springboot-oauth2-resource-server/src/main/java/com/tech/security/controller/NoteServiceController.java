package com.tech.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tech.security.model.AccessTokenMapper;

@RestController
public class NoteServiceController {

	
	@PreAuthorize("hasRole('CREATE_NOTE')")
	@RequestMapping(value="/note", method=RequestMethod.POST)
	public String createNote() {
		
		/*AccessTokenMapper mapper = (AccessTokenMapper) ((OAuth2AuthenticationDetails)SecurityContextHolder.getContext().getAuthentication()).getDecodedDetails();
	    System.out.println("ID:- "+mapper.getId());
	    System.out.println("Name:- "+mapper.getName());*/
		return "Note has been created successfully";
	}
	
	@PreAuthorize("hasRole('EDIT_NOTE')")
	@RequestMapping(value="/note", method=RequestMethod.PUT)
	public String updateNote() {
		
		return "Note has been UPDATED successfully";
	}
	
	@PreAuthorize("hasRole('DELETE_NOTE')")
	@RequestMapping(value="/note", method=RequestMethod.DELETE)
	public String deleteNote() {
		
		return "Note has been deleted successfully";
	}
	
	
	
	
	@PreAuthorize("hasRole('VIEW_ALL_NOTE')")
	@RequestMapping(value="/note/all", method=RequestMethod.GET)
	public String getAllNote() {
		
		return "Getting all note";
	}
	
	
	@PreAuthorize("hasRole('VIEW_NOTE')")
	@RequestMapping(value="/note", method=RequestMethod.GET)
	public String viewNotesById() {
		
		return "View Notes by id";
	}
	
	
	
}
