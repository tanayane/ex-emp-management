package jp.co.sample.form;

import javax.validation.constraints.NotBlank;

public class ModifyAdministratorForm {
	/** id*/
	private Integer id;
	/**  名前*/
	@NotBlank(message="氏名を入力してください")
	private String name;
	/**  メールアドレス*/
	@NotBlank(message="メールアドレスを入力してください")
	private String mailAddress;
	/**  パスワード*/
	@NotBlank(message="パスワードを入力してください")
	private String password;
	/**  確認用パスワード*/
	private String validatedPassword;

	
	@Override
	public String toString() {
		return "Administrator [name=" + name + ", mailAddress=" + mailAddress + ", password=" + password
				+ ", validatedPassword="+validatedPassword+"]";
	}

	//get&set
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getValidatedPassword() {
		return validatedPassword;
	}

	public void setValidatedPassword(String validatedPassword) {
		this.validatedPassword = validatedPassword;
	}

}
