package jp.co.sample.form;

public class UpdateEmployeeForm {
	/**  ID　主キー*/
	private Integer id;
	/**  扶養人数*/
	private Integer dependentsCount;

	
	
	@Override
	public String toString() {
		return "UpdateEmployeeForm [id="+id+",dependentsCount=" + dependentsCount + "]";
	}
	
	//set&get
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDependentsCount() {
		return dependentsCount;
	}
	public void setDependentsCount(Integer dependentsCount) {
		this.dependentsCount = dependentsCount;
	}

}
