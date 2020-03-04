package test1.file;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import test1.RequestContext;

@MultipartConfig(location="/tmp", maxFileSize=1048576)

public class FileContext {
	public  static String getFile(RequestContext reqc) {
		String fullPath="";
		try {
		HttpServletRequest request;
		HttpServletResponse response;
		request = (HttpServletRequest)reqc.getRequest();
		Part part = request.getPart("file");





		System.out.println(part+"partだよ");



	     String name = GetFileName.getFileName(part);

	     System.out.println("1段階"+name+":::::"+part);

	     String pathString ="C:/pleiades/workspace/test1/WebContent/WEB-INF/uploaded/";

	     fullPath = pathString+name;



	     part.write(fullPath);



		}catch (Exception e) {
			e.printStackTrace();
		}
		return fullPath;
	}
}
