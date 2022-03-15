package kr.co.thesmc.controller.adm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.thesmc.model.Notice;
import kr.co.thesmc.service.NoticeService;
import kr.co.thesmc.util.CommonPaging;
import kr.co.thesmc.util.CommonUtil;


@Controller
@RequestMapping("/adm_super")
public class NoticeController {

	@Autowired
	NoticeService noticeService;


	@SuppressWarnings("finally")
	@RequestMapping(value = "/notice_list", method = RequestMethod.GET)
	public String list(
			String sg,
			String sw,
			String pg,
			Model model,
			HttpServletRequest request
			)throws Exception {
		try{

			//sg = CommonUtil.nullCheck(sg);
			//sw = CommonUtil.nullCheck(sw);

			int totalCount = noticeService.getNoticeTotal();
			CommonPaging pgSet = new CommonPaging(pg,totalCount);

			int dpNum = (pgSet.getTotalCount()) - (((pgSet.getCpg()) - 1) * pgSet.getPageSize());
			model.addAttribute("dpNum",dpNum);
			model.addAttribute("list", noticeService.getNoticeList( (pgSet.getCpg() -1 )*pgSet.getPageSize() , pgSet.getPageSize() ));
			model.addAttribute("pgSet", pgSet.getAdmPageSet(request.getContextPath(),"notice_list","",""));

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			return "/adm_super/notice/notice_list";
		}
	}

	@RequestMapping("/notice_view")
	public String notice_view(String id,
			Model model) throws Exception {
		if ("".equals(CommonUtil.nullCheck(id)) ){
			return "/adm_super/notice/notice_view";
		}else{
			String sno = CommonUtil.nullCheck(id);
			model.addAttribute("notice",noticeService.getNoticeView(sno));
			return "/adm_super/notice/notice_view";
		}
		
	}

	@SuppressWarnings("finally")
	@RequestMapping(value="/notice_write", method=RequestMethod.POST)
	public String notice_write(
			Notice n,
			HttpSession session,
			Model model) throws Exception {
		try{
			noticeService.insertNotice(n);
			model.addAttribute("msg", "정상적으로 등록되었습니다.");
			model.addAttribute("url", "/adm_super/notice_list");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			return "redirect";
		}
	}

	@SuppressWarnings("finally")
	@RequestMapping(value="/notice_delete", method=RequestMethod.POST)
	public String deletePost(
			String [] delChk,
			HttpSession session,
			Model model) throws Exception {
		try{
			noticeService.deleteNotice(delChk);
			model.addAttribute("msg", "정상적으로 삭제되었습니다.");
			model.addAttribute("url", "/adm_super/notice_list");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			return "redirect";
		}
	}

	@SuppressWarnings("finally")
	@RequestMapping(value="/notice_update", method=RequestMethod.POST)
	public String notice_update(
			Notice n,
			HttpSession session,
			Model model) throws Exception {
		try{
			noticeService.updateNotice(n);
			model.addAttribute("msg", "정상적으로 변경되었습니다.");
			model.addAttribute("url", "/adm_super/notice_list");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			return "redirect";
		}
	}

}