package in.ineuron.service;

import in.ineuron.dto.Student;

public interface IStudentService {
	
	String save(Student student);
	Student findById(String id);
	String updateById(Student student);
	String deleteById(String id);

}
