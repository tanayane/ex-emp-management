package jp.co.sample.form;

public class UpdateEmployeeForm {

	/**  扶養人数*/
	private Integer dependentsCount;

	
	
	@Override
	public String toString() {
		return "UpdateEmployeeForm [dependentsCount=" + dependentsCount + "]";
	}
	
	//set&get
	public Integer getDependentsCount() {
		return dependentsCount;
	}
	public void setDependentsCount(Integer dependentsCount) {
		this.dependentsCount = dependentsCount;
	}

}
