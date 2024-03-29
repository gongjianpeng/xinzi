package cn.internalaudit.audit.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.internalaudit.audit.dao.IBrandDao;
import cn.internalaudit.audit.entitys.Brand;

public class FileUploadServletBrand extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8;");
		doPost(request, response);
	}

	@SuppressWarnings({ "unchecked", "unused" })
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8;");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		 
		
		//by zhangzhengyang
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		IBrandDao brandDao = (IBrandDao)ctx.getBean("brandDao");
	//by zhangzhengyang
		
		
		// 设置接收的编码格式
		request.setCharacterEncoding("UTF-8");
		Date date = new Date();// 获取当前时间
		SimpleDateFormat sdfFileName = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat sdfFolder = new SimpleDateFormat("yyMM");
		String newfileName = sdfFileName.format(date);// 文件名称
		String fileRealPath = "";// 文件存放真实地址

		String fileRealResistPath = "";// 文件存放真实相对路径

		// 名称 界面编码 必须 和request 保存一致..否则乱码
		String name = request.getParameter("name");
		String ID=request.getParameter("ID");
		System.out.println("前台传过来的"+ID);

		//Brand  brand=new Brand();
		Brand  brand=brandDao.findDatabrandById(Long.valueOf(ID));
     //  System.out.println("-----------------------------------------------------------"+dealer.getDename());
		String firstFileName = "";
		// 获得容器中上传文件夹所在的物理路径
		String savePath = this.getServletConfig().getServletContext() .getRealPath("/") + "brandpic\\" + newfileName + "\\";
		System.out.println("路径" + savePath + "; name:" + name);
		File file = new File(savePath);
		if (!file.isDirectory()) {
			file.mkdirs();
		}

		try {
			DiskFileItemFactory fac = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(fac);
			upload.setHeaderEncoding("UTF-8");
			// 获取多个上传文件
			List fileList = fileList = upload.parseRequest(request);
			// 遍历上传文件写入磁盘
			Iterator it = fileList.iterator();
			while (it.hasNext()) {
				Object obit = it.next();
				if (obit instanceof DiskFileItem) {
					System.out.println("xxxxxxxxxxxxx");
					DiskFileItem item = (DiskFileItem) obit;

					// 如果item是文件上传表单域
					// 获得文件名及路径
					String fileName = item.getName();
					if (fileName != null) {
						firstFileName = item.getName().substring(
								item.getName().lastIndexOf("\\") + 1);
						String formatName = firstFileName
								.substring(firstFileName.lastIndexOf("."));// 获取文件后缀名
						fileRealPath = savePath + newfileName + formatName;// 文件存放真实地址
                       String   endurl= newfileName +"\\"+newfileName+ formatName;
                       System.out.println(" 最终获得的后缀路径为"+endurl);
						BufferedInputStream in = new BufferedInputStream(item
								.getInputStream());// 获得文件输入流
						BufferedOutputStream outStream = new BufferedOutputStream(
								new FileOutputStream(new File(fileRealPath)));// 获得文件输出流
						Streams.copy(in, outStream, true);// 开始把文件写到你指定的上传文件夹
						// 上传成功，则插入数据库
						if (new File(fileRealPath).exists()) {
							// 虚拟路径赋值
							fileRealResistPath = sdfFolder.format(date)
									+ "/"
									+ fileRealPath.substring(fileRealPath
											.lastIndexOf("\\") + 1);
							// 保存到数据库
							System.out.println("保存到数据库:");
							System.out.println("name:" + name);
							System.out.println("虚拟路径:" + fileRealResistPath);
							if (brand != null) {
								String updateUrl = "";
								if (!Utils.nullOrEmpty(brand.getFileUrl())) {
									updateUrl =endurl;
								} else {
									updateUrl = endurl;
								}
								brand.setFileUrl(updateUrl);
								brand = brandDao.save(brand);
							}
						}
					}
				}
			}
		} catch (org.apache.commons.fileupload.FileUploadException ex) {
			ex.printStackTrace();
			System.out.println("没有上传文件");
			return;
		}
		response.getWriter().write("1");
		out.flush();
		out.close();

	}
}
