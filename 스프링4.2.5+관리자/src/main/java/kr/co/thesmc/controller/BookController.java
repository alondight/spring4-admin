package kr.co.thesmc.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.co.thesmc.model.Book;
import kr.co.thesmc.model.JsonResult;
import kr.co.thesmc.service.BookService;
import kr.co.thesmc.util.CommonExcel;
import kr.co.thesmc.util.CommonFile;
import kr.co.thesmc.util.CommonUtil;

@Controller
public class BookController {

	@Autowired
	BookService bookService;

	@Value("#{config['server.file.dir']}")
	String dir = "/";




	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) throws Exception {
		return "book/home";
	}
	@RequestMapping(value = "/home2", method = RequestMethod.GET)
	public String home2(Model model) throws Exception {
		return "book/home2";
	}
	@RequestMapping(value = "/home3", method = RequestMethod.GET)
	public String home3(Model model) throws Exception {
		return "book/home3";
	}




	@SuppressWarnings("finally")
	@ResponseBody
	@RequestMapping(value = "/api", produces = "application/json", method=RequestMethod.GET)
	public Object getList(Model model, @RequestParam Map<String , String> filter) throws Exception {
		JsonResult result =  new JsonResult();

		try{
			result.setStatus("ok");
			result.setData(bookService.getBookList(filter));
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			return result;
		}
		
	}

	@SuppressWarnings("finally")
	@ResponseBody
	@RequestMapping(value = "/api", produces = "application/json", method=RequestMethod.POST)
	public Object insert(Model model, @RequestBody Book vo) throws Exception {
		JsonResult result =  new JsonResult();
		result.setStatus("no");

		try{
			bookService.insertBook(vo);
			result.setStatus("ok");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			return result;
		}
	}

	@SuppressWarnings("finally")
	@ResponseBody
	@RequestMapping(value = "/api/{id}", produces = "application/json", method=RequestMethod.PUT)
	public Object update(Model model, @PathVariable String id, @RequestBody Book vo) throws Exception {
		JsonResult result =  new JsonResult();
		result.setStatus("no");

		//id NULL??????
		id = CommonUtil.nullCheck(id);
		if("".equals(id)) {
			return result;
		}

		try{
			bookService.updateBook(vo);
			result.setStatus("ok");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			return result;
		}
	}

	@SuppressWarnings("finally")
	@ResponseBody
	@RequestMapping(value = "/api/{id}", produces = "application/json", method=RequestMethod.DELETE)
	public Object delete(Model model, @PathVariable String id) throws Exception {
		JsonResult result =  new JsonResult();
		result.setStatus("no");

		//id NULL??????
		id = CommonUtil.nullCheck(id);
		if("".equals(id)) {
			return result;
		}

		try{
			bookService.deleteBook(id);
			result.setStatus("ok");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			return result;
		}
	}




	//??????????????????
	@ResponseBody
	@RequestMapping(value = "/excel", method = RequestMethod.GET, produces = "application/octet-stream")
	public void excel(  HttpServletResponse response, Book vo, @RequestParam Map<String , String> filter) throws IOException, Exception {

		String fileName =  "bookList.xlsx";

		CommonExcel excel = new CommonExcel();

		//?????? ?????? ??? ????????? ??????
		List<String> header            = bookService.getBookExcelListHeader();
		List<Map<String, String>> data = bookService.getBookExcelList(filter);

		//????????? ????????????????????? String?????? ??????
		for(int i=0; i < data.size(); i++) {
			data.get(i).put("id", String.valueOf(data.get(i).get("id")));
			data.get(i).put("price", String.valueOf(data.get(i).get("price")));
			data.get(i).put("cnt", String.valueOf(data.get(i).get("cnt")));
		}

		//????????????
		excel.setHeaders(header);
		excel.setData(data);
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", String.format("inline; filename=\"" + fileName + "\""));
		ServletOutputStream outputStream = response.getOutputStream();
		excel.download(outputStream);
		outputStream.close();
	}




	//????????? ?????????
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Model model) throws Exception {
		return "book/edit";
	}

	// ?????????????????????
	@ResponseBody
	@RequestMapping(value = "/file_uploader_html5.file", method = RequestMethod.POST)
	public String file_uploader_html5(HttpServletRequest request) {
		// ????????????
		StringBuffer sb = new StringBuffer();
		try {
			// ???????????? ????????? - ?????? ???????????????
			String oldName = request.getHeader("file-name");

			// ?????? ???????????? _ ????????????
			String filePath = request.getSession().getServletContext().getRealPath("/")+"resources/upload/edit/";
			String saveName = sb.append(new SimpleDateFormat("yyyyMMddHHmmss")
						.format(System.currentTimeMillis()))
						.append("_"+UUID.randomUUID().toString().replace("-", ""))
						.append(oldName.substring(oldName.lastIndexOf("."))).toString();
			InputStream is = request.getInputStream();
			OutputStream os = new FileOutputStream(filePath + saveName);
			int numRead;
			byte b[] = new byte[Integer.parseInt(request.getHeader("file-size"))];
			while ((numRead = is.read(b, 0, b.length)) != -1) {
				os.write(b, 0, numRead);
			}
			os.flush();
			os.close();

			// ?????? ??????
			sb = new StringBuffer();
			sb.append("&bNewLine=true").append("&sFileName=").append(oldName).append("&sFileURL=").append(request.getContextPath()+"/resources/upload/edit/").append(saveName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	



	//???????????????
	@RequestMapping(value = "/file", method = RequestMethod.GET)
	public String file(Model model) throws Exception {
		return "book/file";
	}

	// ?????????????????????
	@RequestMapping(value = "/upload.file", method = RequestMethod.POST)
	public String fileUpload(HttpServletRequest request, MultipartFile upFile) {
		CommonFile uploader = new CommonFile();
		uploader.setUploadPath(request.getSession().getServletContext().getRealPath("/")+"resources/upload/files/");
		String name = uploader.save(upFile);

		System.out.println(name);
		return "redirect:file";
	}




	//FORM ???????????????
	@RequestMapping(value = "/formAdd", method=RequestMethod.POST)
	public String formAdd(HttpServletRequest request,
			Model model,
			String email,
			String password,
			String selectOption,
			String contents,
			String optionsRadios
			) throws Exception {

		System.out.println(email);
		System.out.println(password);
		System.out.println(selectOption);
		System.out.println(contents);
		System.out.println(optionsRadios);

		return "redirect:home2";
	}

	//???????????? ???????????????
	@RequestMapping(value = "/formAdd.file", method=RequestMethod.POST)
	public String formAdd_file(HttpServletRequest request,
			Model model,
			String email,
			String password,
			String selectOption,
			String contents,
			String optionsRadios,
			@RequestParam("file") MultipartFile file
			) throws Exception {

		System.out.println(email);
		System.out.println(password);
		System.out.println(selectOption);
		System.out.println(contents);
		System.out.println(optionsRadios);

		if(file.getSize() > 0) {
			CommonFile uploader = new CommonFile();
			uploader.setUploadPath(request.getSession().getServletContext().getRealPath("/")+"resources/upload/files/");
			String name = uploader.save(file);
			String name2 = uploader.getUploadPath();

			System.out.println(name);
			System.out.println(name2);
		}

		return "redirect:home3";
	}

}
