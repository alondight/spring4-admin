package kr.co.thesmc.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

public class CommonFile {
	private String uploadPath = "/";
	
	public String save(MultipartFile upFile) {
		String filePath = this.getSavableFilePath( upFile.getOriginalFilename() );
		try {
			FileCopyUtils.copy(upFile.getBytes(), new FileOutputStream( filePath ));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return filePath.replace(uploadPath, "");
	}
	
	private String getSavableFilePath(String fileName) {
		String filePath = this.uploadPath + fileName;
		File file = new File(filePath);
		String ext = getExtension(file);
		String fileBodyName = getBodyName(file);
		
		int i = 1;
		while( file.exists() ) {
			++i;
			filePath = this.uploadPath + fileBodyName + "_" + i + "." + ext;
			file = new File(filePath);
		}
		return filePath;
	}

	private String getBodyName(File file) {
		String name = file.getName();

		try {
			return name.substring(0, name.lastIndexOf("."));
		} catch (Exception e) {
			return "";
		}
	}
	
	private String getExtension(File file) {
		String name = file.getName();

		try {
			return name.substring(name.lastIndexOf(".") + 1);
		} catch (Exception e) {
			return "";
		}
	}
	
	public String getUploadPath() {
		return uploadPath;
	}
	
	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}
	
}