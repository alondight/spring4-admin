package kr.co.thesmc.util;

import org.springframework.web.multipart.MultipartFile;

public class CommonUtil {

	public static String nullCheck(String stringVar) { 
		if(stringVar == null  || stringVar.length() == 0) {
			stringVar = "";
		}
		return stringVar;
	}

	public static int intCheck(String stringVar) {
		int intVar = -1;
		try {
			intVar = Integer.parseInt(stringVar);
		}catch(NumberFormatException e) {
			intVar = -1;
		}
		return intVar;
	}
	
	public static boolean fileCheck(MultipartFile photo) {

		boolean fileSizeChk = true;
		//파일 사이즈 검사 3MB 이하의 파일만 등록
		if(photo.getSize() > 3*1000*1000) {
			fileSizeChk = false;
		}

		//확장자 검사
		String filename = photo.getOriginalFilename();
		String filename_ext = filename.substring(filename.lastIndexOf(".")+1).toLowerCase();
		if(!"".equals(filename_ext)) {
			if( !"jpg".equalsIgnoreCase(filename_ext) && 
					!"jpeg".equalsIgnoreCase(filename_ext) && 
					!"gif".equalsIgnoreCase(filename_ext)  && 
					!"bmp".equalsIgnoreCase(filename_ext)  &&
					!"png".equalsIgnoreCase(filename_ext) ){
				fileSizeChk = false;  	
			}
		}else {
			fileSizeChk = false;
		}
		return fileSizeChk;
	}
}
