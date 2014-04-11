package cn.internalaudit.audit.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.struts2.ServletActionContext;

public class FileByMake
{
    /**
     * 处理上传的文件
     * @throws IOException 
     */
    public static void inputFile(File file,String fileName,String fileContentType)
        throws IOException
    {        
        if (file != null)
        {
            InputStream is = new FileInputStream(file);//file.get(i)
            
            String realpath = ServletActionContext.getServletContext().getRealPath("/img");
            
            File savefile = new File(realpath);
            if (!savefile.exists())
            {
                savefile.mkdir();
                
            }
            
            FileOutputStream fos = new FileOutputStream(realpath + "\\" + fileName);
            
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = is.read(buffer)) > 0)
            {
                fos.write(buffer, 0, len);
            }
            
            is.close();
            fos.close();
        }
    }
}
