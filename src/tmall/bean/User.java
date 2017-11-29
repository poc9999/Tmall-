package tmall.bean;

public class User {

	//�����ݿ����������������Ϊ�ɼ�
	public int id;
	public String name;
	//�û�����
	public String type;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	private String password;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	//���������û�
	public String getAnonymousName(){
		
		if(null==name){
			return null;
		}
		
		if(name.length()<=1){
			return "*";
		}
		if(name.length()==2){
			return name.substring(0,1)+"*";
		}
		
		char []cs=name.toCharArray();
		
		for(int i=0;i<cs.length;i++){
			cs[i]='*';
		}
		return new String(cs);
	}
}
