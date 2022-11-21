package com.hospital.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hospital.entity.Doctor;
import com.hospital.exception.UserAlreadyExist;
import com.hospital.exception.UserNotExist;
import com.hospital.model.DoctorModel;
import com.hospital.repository.DoctorRepo;
import com.hospital.service.DoctorService;

@Service
public class DocServiceImpl implements DoctorService {

	final private DoctorRepo docRepo;

	public DocServiceImpl(DoctorRepo docRepo) {
		this.docRepo = docRepo;
	}

	@Override
	public List<DoctorModel> getAll() {
		List<Doctor> list = docRepo.findAll();
		List<DoctorModel> docList = new ArrayList<>();
		for (Doctor doc : list) {
			DoctorModel docMod = new DoctorModel();
			docMod.setName(doc.getName());
			docMod.setId(doc.getId());
			docMod.setEmail(doc.getEmail());
			docMod.setGender(doc.getGender());
			docMod.setNumber(doc.getNumber());
			docMod.setSpecialization(doc.getSpecialization());
			docList.add(docMod);
		}
		return docList;
	}

	@Override
	public DoctorModel getById(String id) {
		Optional<Doctor> list = docRepo.findById(id);
		Doctor doc;
		if (list.isPresent()) {
			doc = list.get();
			DoctorModel docMod = new DoctorModel();
			docMod.setName(doc.getName());
			docMod.setId(doc.getId());
			docMod.setEmail(doc.getEmail());
			docMod.setGender(doc.getGender());
			docMod.setNumber(doc.getNumber());
			docMod.setSpecialization(doc.getSpecialization());
			return docMod;
		}

		else
			throw new UserNotExist("User doesn't exist with id : " + id);
	}

	@Override
	public boolean createDoc(DoctorModel doc) {
		Doctor d = docRepo.findByEmail(doc.getEmail());
		Doctor dd = docRepo.findByNumber(doc.getNumber());
		if (Objects.nonNull(d))
			throw new UserAlreadyExist("User Alredy Exist with email : " + d.getEmail());
		else if(Objects.nonNull(dd))
			throw new UserAlreadyExist("User Alredy Exist with number : " + dd.getNumber());
		try {
			Doctor doctor = new Doctor();
			doctor.setName(doc.getName());
			doctor.setNumber(doc.getNumber());
			doctor.setSpecialization(doc.getSpecialization());
			doctor.setGender(doc.getGender());
			doctor.setEmail(doc.getEmail());
			docRepo.save(doctor);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean updateDoc(DoctorModel doc) {
		Optional<Doctor> list = docRepo.findById(doc.getId());
		if (list.isPresent()) {
			Doctor doctor = list.get();
			doctor.setName(doc.getName());
			doctor.setNumber(doc.getNumber());
			doctor.setSpecialization(doc.getSpecialization());
			doctor.setGender(doc.getGender());
			doctor.setEmail(doc.getEmail());
			docRepo.save(doctor);
			return true;
		} else
			throw new UserNotExist("User doesn't exist with id : " + doc.getId());

	}

	@Override
	public boolean detele(String id) {
		if (docRepo.findById(id).isPresent()) {
			docRepo.deleteById(id);
			return true;
		} else
			throw new UserNotExist("User doesn't exist with id : " + id);
	}

}
