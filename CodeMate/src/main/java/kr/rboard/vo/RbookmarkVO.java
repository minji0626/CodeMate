package kr.rboard.vo;

public class RbookmarkVO {
	private int rb_num;
	private int mem_num;
	
	public RbookmarkVO() {}
	
	public RbookmarkVO(int rb_num, int mem_num) {
		super();
		this.rb_num = rb_num;
		this.mem_num = mem_num;
	}
	public int getRb_num() {
		return rb_num;
	}
	public void setRb_num(int rb_num) {
		this.rb_num = rb_num;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	
	
}
