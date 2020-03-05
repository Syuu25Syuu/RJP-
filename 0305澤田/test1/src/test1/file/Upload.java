package test1.file;
import javax.servlet.http.Part;

import test1.RequestContext;

//@MultipartConfig(location="/tmp", maxFileSize=58576000)
public abstract class Upload {
    public abstract String upload(RequestContext reqc,String partname);

    protected String getFileName(Part part) {
        String name = null;
        for (String dispotion : part.getHeader("Content-Disposition").split(";")) {
            if (dispotion.trim().startsWith("filename")) {
                name = dispotion.substring(dispotion.indexOf("=") + 1).replace("\"", "").trim();
                System.out.println("1段階"+name);
                name = name.substring(name.lastIndexOf("\\") + 1);
                System.out.println("2段階"+name);
                break;
            }
        }
        return name;
    }
}