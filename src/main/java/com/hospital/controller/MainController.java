package com.hospital.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.model.DoctorModel;
import com.hospital.service.DoctorService;

@RestController
@RequestMapping("/doctor")
public class MainController {

	private final DoctorService service;

	public MainController(DoctorService service) {
		this.service = service;
	}

	@GetMapping
	private ResponseEntity<List<DoctorModel>> getAll() {
		return ResponseEntity.ok().body(service.getAll());
	}

	@GetMapping("/{id}")
	private ResponseEntity<DoctorModel> getDoc(@PathVariable("id") String id) {
		return ResponseEntity.ok().body(service.getById(id));
	}

	@PostMapping
	private ResponseEntity<Boolean> saveDoc(@Valid @RequestBody DoctorModel doc) {
		return ResponseEntity.ok().body(service.createDoc(doc));
	}

	@PutMapping
	private ResponseEntity<Boolean> updateDoc(@Valid @RequestBody DoctorModel doc) {
		return ResponseEntity.ok().body(service.updateDoc(doc));
	}

	@DeleteMapping("/{id}")
	private ResponseEntity<Boolean> deleteDoc(@PathVariable("id") String id) {
		return ResponseEntity.ok().body(service.detele(id));
	}

}
