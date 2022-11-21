package com.hospital.service;

import java.util.List;

import com.hospital.model.DoctorModel;

public interface DoctorService {

	List<DoctorModel> getAll();

	DoctorModel getById(String id);

	boolean createDoc(DoctorModel doc);

	boolean updateDoc(DoctorModel doc);

	boolean detele(String id);

}
