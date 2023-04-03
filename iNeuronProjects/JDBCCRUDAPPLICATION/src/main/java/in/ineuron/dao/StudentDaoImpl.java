package in.ineuron.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.ineuron.dto.Student;
import in.ineuron.util.JdbcConnection;

public class StudentDaoImpl implements IStudentDao {

	Connection connection = null;
	PreparedStatement pstm = null;
	String status = null;;
	Integer rowsAffected = null;
	ResultSet resultSet = null;
	@Override
	public String save(Student student) {
		
		try {
			String sqlInsertQuery = "insert into student_details(`sname`,`sage`,`sadd`) values(?,?,?)";
			connection = JdbcConnection.getJdbcConnection();
			if(connection != null)
			pstm =connection.prepareStatement(sqlInsertQuery);
			if(pstm != null) {
			pstm.setString(1, student.getSname());
			pstm.setString(2, student.getSage());
			pstm.setString(3, student.getSadd());
			}
			if(pstm != null) {
				rowsAffected =pstm.executeUpdate();
			}
			
			if( rowsAffected == 1) {
				status="success";
			}
			else if(rowsAffected == 0) {
				status = "failed";
			}
			else
				status ="failed";
				
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public Student findById(String id) {
		String sqlSelectQuery = "select sid,sname,sage,sadd from student_details where sid=?";
		Student student = new Student();
		try {
			 connection = JdbcConnection.getJdbcConnection();
			 if(connection != null)
				 pstm=connection.prepareStatement(sqlSelectQuery);
			 if(pstm != null) {
				 pstm.setString(1, id);
				
			 }
			 resultSet = pstm.executeQuery();
			 if(resultSet.next()) {
				 student.setSid(resultSet.getString(1));
				 student.setSname(resultSet.getString(2));
				 student.setSage(resultSet.getString(3));
				 student.setSadd(resultSet.getString(4));
				 
			 }
				 
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return student;
	}

	@Override
	public String updateById(Student student) {
		String sqlUpdateQuery = "update student_details set sname=?,sage=?,sadd=? where sid=?";
		try {
			connection = JdbcConnection.getJdbcConnection();
			if(connection != null)
			  pstm = connection.prepareStatement(sqlUpdateQuery);
			if(pstm != null) {
				pstm.setString(1, student.getSname());
				pstm.setString(2, student.getSage());
				pstm.setString(3, student.getSadd());
				pstm.setString(4, student.getSid());
				rowsAffected = pstm.executeUpdate();
			}
			if(rowsAffected != null)
				status = "success";
			else
				status = "failed";
		} catch (SQLException | IOException e) {
			status="failed";
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public String deleteById(String id) {
		String sqlDeleteQuery = "delete from student_details where sid=?";
		Student studentRecord = findById(id);
		try {
			if (studentRecord.getSid() != null) {
				connection = JdbcConnection.getJdbcConnection();
				if (connection != null)
					pstm = connection.prepareStatement(sqlDeleteQuery);
				if (pstm != null) {
					pstm.setString(1, id);
				    rowsAffected = pstm.executeUpdate();
				}
				if (rowsAffected != null)
					status = "success";
				else
					status="failed";
			}
			else {
				status = "recordNotFound";
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
			status = "failed";
		}
		return status;
	}

}
