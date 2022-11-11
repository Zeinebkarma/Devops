package com.esprit.examen.controllers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.esprit.examen.entities.PostDto;
import com.esprit.examen.entities.SecteurActivite;
import com.esprit.examen.services.ISecteurActiviteService;

import io.swagger.annotations.Api;

@RestController
@Api(tags = "Gestion des secteurs activites")
@RequestMapping("/secteurActivite")
@CrossOrigin("*")
public class SecteurActiviteController {

	
	 @Autowired
	    ISecteurActiviteService secteurService;
	    @Autowired
	    private ModelMapper modelMapper;
	
	public SecteurActiviteController(ISecteurActiviteService secteurService) {
		super();
		this.secteurService = secteurService;
	}

	
	@Autowired
	ISecteurActiviteService secteurActiviteService;
	
	// http://localhost:8089/SpringMVC/secteurActivite/retrieve-all-secteurActivite
	@GetMapping("/retrieve-all-secteurActivite")
	@ResponseBody
	public List<SecteurActivite> getSecteurActivite() {
		return secteurActiviteService.retrieveAllSecteurActivite();
	}

	// http://localhost:8089/SpringMVC/secteurActivite/retrieve-secteurActivite/8
	@GetMapping("/retrieve-secteurActivite/{secteurActivite-id}")
	@ResponseBody
	public SecteurActivite retrieveSecteurActivite(@PathVariable("secteurActivite-id") Long secteurActiviteId) {
		return secteurActiviteService.retrieveSecteurActivite(secteurActiviteId);
	}

	// http://localhost:8089/SpringMVC/secteurActivite/add-secteurActivite
	  @PostMapping("/add-secteurActivite")
	    @ResponseBody
	public ResponseEntity<PostDto> addSecteurActivite(@RequestBody PostDto s) {
		SecteurActivite op = modelMapper.map(s, SecteurActivite.class);
		SecteurActivite secteur = secteurService.addSecteurActivite(op);
		PostDto secteurResponse = modelMapper.map(secteur, PostDto.class);
		return new ResponseEntity<>(secteurResponse, HttpStatus.CREATED);
    }

	@DeleteMapping("/remove-secteurActivite/{secteurActivite-id}")
	@ResponseBody
	public void removeSecteurActivite(@PathVariable("secteurActivite-id") Long secteurActiviteId) {
		secteurActiviteService.deleteSecteurActivite(secteurActiviteId);
	}


	public SecteurActivite modifySecteurActivite(@RequestBody SecteurActivite secteurActivite) {
		return secteurActiviteService.updateSecteurActivite(secteurActivite);
	}
	// http://localhost:8089/SpringMVC/secteurActivite/modify-secteurActivite
	@PutMapping("/modify-secteurActivite")
	@ResponseBody
	public ResponseEntity<PostDto> modifySecteurActivite(@RequestBody PostDto s) {
		
		SecteurActivite op = modelMapper.map(s, SecteurActivite.class);
		SecteurActivite secteur = secteurService.addSecteurActivite(op);
		PostDto secteurResponse = modelMapper.map(secteur, PostDto.class);
		return new ResponseEntity<>(secteurResponse, HttpStatus.ACCEPTED);
	}

	
}
