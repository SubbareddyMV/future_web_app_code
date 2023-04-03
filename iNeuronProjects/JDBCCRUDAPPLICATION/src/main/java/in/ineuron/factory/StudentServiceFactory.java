package in.ineuron.factory;

import in.ineuron.service.IStudentService;
import in.ineuron.service.StudentServiceImpl;

public class StudentServiceFactory {

	static IStudentService stdService;
	
	private StudentServiceFactory() {
		
	}
	public static IStudentService getStudentConnection() {
		if(stdService == null)
		      stdService = new StudentServiceImpl();
		
		return stdService;
	}
}
