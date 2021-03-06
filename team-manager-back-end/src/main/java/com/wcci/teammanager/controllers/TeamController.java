package com.wcci.teammanager.controllers;

import java.util.Collection;

import javax.annotation.Resource;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wcci.teammanager.models.Conference;
import com.wcci.teammanager.models.Division;
import com.wcci.teammanager.models.Team;
import com.wcci.teammanager.repositories.ConferenceRepository;
import com.wcci.teammanager.repositories.DivisionRepository;
import com.wcci.teammanager.repositories.TeamRepository;

@RestController
@RequestMapping("/teams")
public class TeamController {
	
	@Resource
	TeamRepository teamRepo;
	@Resource
	DivisionRepository divisionRepo;
	@Resource
	ConferenceRepository conferenceRepo;
	
	@GetMapping("")
	public Collection<Team> getTeams() {
		return (Collection<Team>) teamRepo.findAll();
	}
	
	@DeleteMapping("delete/{id}")
	public Collection<Conference> delTeam(@PathVariable Long id) {
		teamRepo.deleteById(id);
		return (Collection<Conference>) conferenceRepo.findAll();
	}
	
	@PostMapping("/add/{id}")
	public Collection<Team> addTeam(@PathVariable Long id, @RequestBody String body) throws JSONException {
		JSONObject newTeam = new JSONObject(body);
		String teamName = newTeam.getString("teamName");
		String teamLogo = newTeam.getString("teamLogo");
		String location = newTeam.getString("location");
		Division division = divisionRepo.findById(id).get();
		teamRepo.save(new Team(teamName, teamLogo, location, division));
		return (Collection<Team>) teamRepo.findAll();
	}

}
