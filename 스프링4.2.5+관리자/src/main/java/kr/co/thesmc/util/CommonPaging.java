package kr.co.thesmc.util;



public class CommonPaging {

	final int pageSize = 10;
	final int group_per_page_cnt = 10;
	int cpg = 0; 
	int pageTotal = 0;
	int totalCount = 0;

	public CommonPaging(String pg, int totalCount) {
		//현재 page 세팅
		pg = CommonUtil.nullCheck(pg);
		if(pg.equals("")) {
			pg = "1";
		}
		try { 
			this.cpg = Integer.parseInt(pg); 
		}catch(NumberFormatException e){ 
			this.cpg = 1;
		}finally{
			//페이지 전체 사이즈 세팅
			this.totalCount = totalCount;
			this.pageTotal = (int)(this.totalCount / this.pageSize) + (this.totalCount % this.pageSize >0 ? 1 : 0);

			//현재page오류 체크
			if (this.cpg > this.pageTotal) this.cpg= this.pageTotal;
			if (this.cpg < 1) this.cpg=1;
		}
	}

	
	public int getCpg() {
		return cpg;
	}

	public void setCpg(int cpg) {
		this.cpg = cpg;
	}

	public int getPageTotal() {
		return pageTotal;
	}

	public void setPageTotal(int pageTotal) {
		this.pageTotal = pageTotal;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getGroup_per_page_cnt() {
		return group_per_page_cnt;
	}


	public String getAdmPageSet(String root , String url, String sg, String sw){
		int group_per_page_cnt = this.getGroup_per_page_cnt();
		int total              = this.getPageTotal();
		int cpg                = this.getCpg();

		int group_no = cpg/group_per_page_cnt+( cpg%group_per_page_cnt>0 ? 1:0);
		int page_eno = group_no*group_per_page_cnt;
		int page_sno = page_eno-(group_per_page_cnt-1);
		if(page_eno>total){
			page_eno=total;
		}
		int prev_pg = page_sno-group_per_page_cnt;
		int next_pg = page_sno+group_per_page_cnt;
		if(prev_pg<1){
			prev_pg=1;
		}
		if(next_pg>total){
			next_pg=total/group_per_page_cnt*group_per_page_cnt+1;
		}

		StringBuffer pgSet = new StringBuffer();
		pgSet.append("<a href=\""+url+"?pg=1&sg="+sg+"&sw="+sw+"\" class=\"btn\"><img src=\""+root+"/resources/adm_super/images/common/btn_first.gif\" alt=\"처음페이지로 이동\"></a>");
		if (prev_pg >= 1) {
			pgSet.append("<a href=\""+url+"?pg="+prev_pg+"&sg="+sg+"&sw="+sw+"\" class=\"btn btn_prev\"><img src=\""+root+"/resources/adm_super/images/common/btn_prev.gif\" alt=\"이전 10개 페이지로 이동\"></a>");
		}else{
			pgSet.append("<a href=\"#none\" class=\"btn btn_prev\"><img src=\""+root+"/resources/adm_super/images/common/btn_prev.gif\" alt=\"이전 10개 페이지로 이동\"></a>");
		}
		
			String lastPg = "";
			for(int i =page_sno;i<=page_eno;i++){
				if (i == page_eno){
					lastPg = "class=\"last\"";
				}
				if(cpg == i){
					pgSet.append("<em "+lastPg+">"+i+"</em>");
					
				}else{
					pgSet.append("<a "+lastPg+" href=\""+url+"?pg="+i+"&sg="+sg+"&sw="+sw+"\"> "+i+" </a>");
					
				}
			
			}
		
		if (next_pg < total && next_pg != 1) {	
			pgSet.append("<a href=\""+url+"?pg="+next_pg+"&sg="+sg+"&sw="+sw+"\" class=\"btn btn_next\"><img src=\""+root+"/resources/adm_super/images/common/btn_next.gif\" alt=\"다음 10개 페이지로 이동\"></a>");
		}else{
			pgSet.append("<a href=\"#none\" class=\"btn btn_next\"><img src=\""+root+"/resources/adm_super/images/common/btn_next.gif\" alt=\"다음 10개 페이지로 이동\"></a>");
		}
		pgSet.append("<a href=\""+url+"?pg="+total+"&sg="+sg+"&sw="+sw+"\" class=\"btn\"><img src=\""+root+"/resources/adm_super/images/common/btn_end.gif\" alt=\"끝페이지로 이동\"></a>");
	
		return pgSet.toString();
	}
	
}
