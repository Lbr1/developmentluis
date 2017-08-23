package POJO;

import java.util.ArrayList;
import java.util.List;

import twitter4j.Status;

public class Tuple {

	
	private String index;
	private List<Status> list= new ArrayList<>();
	private long lastID;
	
	public long getLastID() {
		return lastID;
	}
	public void setLastID(long lastID) {
		this.lastID = lastID;
	}
	public Tuple() {
		this.index = "";
		this.list = null;
	}
	public Tuple(String index, List<Status> list) {
		this.index = index;
		this.list = list;
	}
	public String getIndex() {
		return index;
	}
	
	
	public void setIndex(String index) {
		this.index = index;
	}
	public List<Status> getList() {
		return list;
	}
	public void setList(List<Status> list) {
		this.list = list;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((index == null) ? 0 : index.hashCode());
		result = prime * result + ((list == null) ? 0 : list.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tuple other = (Tuple) obj;
		if (index == null) {
			if (other.index != null)
				return false;
		} else if (!index.equals(other.index))
			return false;
		if (list == null) {
			if (other.list != null)
				return false;
		} else if (!list.equals(other.list))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Tuple [index=" + index + ", list=" + list + ", getIndex()=" + getIndex() + ", getList()=" + getList()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() 
				+ "]";
	}
	
	

}
