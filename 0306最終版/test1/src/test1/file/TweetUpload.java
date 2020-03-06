package test1.file;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import test1.RequestContext;

//@MultipartConfig(location="/tmp", maxFileSize=58576000)
public class TweetUpload extends Upload{
    public String upload(RequestContext reqc,String partname) {
    	String name="";
		String newName = "";
		String fullPath="";		//画像を保存するパスを指定する変数
		String writePath ="";
    	try {
    		HttpServletRequest req = (HttpServletRequest)reqc.getRequest();
			Part part = req.getPart(partname);	//投稿された画像を取得
			System.out.println(part);

			if(part.getSize()>0) {		//ツイートに画像が含まれるか判定
				 name = this.getFileName(part);	//画像の名前を取得

				 System.out.println("画像の名前さんだよ！！！"+name);

				 newName = ChangeImageName.changeImageName(name);

				 String pathString ="C:/pleiades/workspace/test1/WebContent/images/uploaded/";

				 //String pathString ="C:/eclipse/pleiades/workspace/test1/image";

				 fullPath = pathString+newName;

				 part.write(fullPath);		//画像を書き込む

				 writePath = "/test1/images/uploaded/"+newName;

			}
    	}catch (Exception e) {
    		e.printStackTrace();
		}
    	return writePath;
    }
}