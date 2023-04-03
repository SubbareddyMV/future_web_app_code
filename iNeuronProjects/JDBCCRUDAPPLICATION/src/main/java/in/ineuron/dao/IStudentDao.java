package in.ineuron.dao;

import in.ineuron.dto.Student;

public interface IStudentDao {

	String save(Student student);
	Student findById(String id);
	String updateById(Student student);
	String deleteById(String id);
}
