package jp.co.sample.form;


import javax.validation.constraints.NotNull;


public class UpdateEmployeeForm {
	/**  ID　主キー*/
	private Integer id;
	/**  扶養人数*/
	@NotNull(message="扶養人数を入力してください")
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
