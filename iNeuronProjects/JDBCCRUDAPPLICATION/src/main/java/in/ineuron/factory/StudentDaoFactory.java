package in.ineuron.factory;

import in.ineuron.dao.IStudentDao;
import in.ineuron.dao.StudentDaoImpl;

public class StudentDaoFactory {

	static IStudentDao stdDao;
	
	private StudentDaoFactory() {
		
	}
	
	public static IStudentDao getStudentDao() {
		if (stdDao == null) 
			stdDao = new StudentDaoImpl();
		
		return stdDao;
	}
	
}
