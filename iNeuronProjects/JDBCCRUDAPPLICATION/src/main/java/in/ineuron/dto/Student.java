package in.ineuron.dto;

import java.io.Serializable;

public class Student implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String sid;
	private String sage;
	private String sname;
	private String sadd;
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getSage() {
		return sage;
	}
	public void setSage(String sage) {
		this.sage = sage;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSadd() {
		return sadd;
	}
	public void setSadd(String sadd) {
		this.sadd = sadd;
	}
	@Override
	public String toString() {
		return "Student [sage=" + sage + ", sname=" + sname + ", sadd=" + sadd + "]";
	}
	
	
	
	
	

}
