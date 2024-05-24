package kr.db.vo;

public class HardSkillVO {
	private int hs_code;
	private String hs_name;
	private int hs_category; //1:프론트엔드, 2:백엔드, 3:데이터, 4:모바일, 5:인프라
	private String hs_photo;
	
	public int getHs_code() {
		return hs_code;
	}
	public void setHs_code(int hs_code) {
		this.hs_code = hs_code;
	}
	public String getHs_name() {
		return hs_name;
	}
	public void setHs_name(String hs_name) {
		this.hs_name = hs_name;
	}
	public int getHs_category() {
		return hs_category;
	}
	public void setHs_category(int hs_category) {
		this.hs_category = hs_category;
	}
	public String getHs_photo() {
		return hs_photo;
	}
	public void setHs_photo(String hs_photo) {
		this.hs_photo = hs_photo;
	}
}
