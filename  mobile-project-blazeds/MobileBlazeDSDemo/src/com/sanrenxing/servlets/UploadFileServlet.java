package com.sanrenxing.servlets;

import java.io.File;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException; 
import javax.servlet.http.HttpServlet; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.sanrenxing.utils.StringUtil;
public class UploadFileServlet extends HttpServlet { 
    private static final long serialVersionUID = 5425836142860976977L;

    /** *//** 
      * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods. 
      * @param request servlet request 
      * @param response servlet response 
      */ 
    
     private String basePath = "http://127.0.0.1:8080/MobileBlazeDSDemo/files/tb/";
     // 定义文件的上传路径 
     private String uploadPath = "D:/DWorks/MobileApp/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/MobileBlazeDSDemo/files/tb/";//"d:\\files\\tb\\"; 
     // 限制文件的上传大小 
     private int maxPostSize = 100 * 1024 * 1024;  //最大100M
     public UploadFileServlet() { 
         super(); 
     } 
     public void destroy() { 
         super.destroy(); 
     } 
     protected void processRequest(HttpServletRequest request, HttpServletResponse response) 
             throws ServletException, IOException { 
         System.out.println("Access !"); 
         response.setContentType("text/html;charset=UTF-8"); 
         //保存文件到服务器中 
         DiskFileItemFactory factory = new DiskFileItemFactory(); 
         factory.setSizeThreshold(4096); 
         ServletFileUpload upload = new ServletFileUpload(factory); 
         upload.setHeaderEncoding("utf-8");
         upload.setSizeMax(maxPostSize); 
         try { 
            List fileItems = upload.parseRequest(request); 
            Iterator iter = fileItems.iterator(); 
            while (iter.hasNext()) { 
                FileItem item = (FileItem) iter.next(); 
                if (!item.isFormField()) { 
                	SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
                	String type = item.getName().substring(item.getName().lastIndexOf("."));
                    String name = StringUtil.gen(10)+formatter.format(new Date(System.currentTimeMillis())) + 
                    		type;//item.getName();
                    System.out.println(name); 
                    
                    //String.substring(fileFileName.indexOf("."));
                    
                    File absFile=new File(uploadPath);
        			if(!absFile.exists()){
        				absFile.mkdirs();
        			}
                    
                    try { 
                        item.write(new File(uploadPath + name)); 
                        response.getWriter().write(basePath + name);
                    } catch (Exception e) { 
                        e.printStackTrace(); 
                        response.getWriter().write(e.getMessage());
                    } 
                } 
            } 
        } catch (FileUploadException e) { 
            e.printStackTrace(); 
            response.getWriter().write(e.getMessage());
            System.out.println(e.getMessage() + "结束"); 
        } 
    } 
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException { 
        processRequest(request, response); 
    } 
    /** *//** 
     * Handles the HTTP <code>POST</code> method. 
     * @param request servlet request 
     * @param response servlet response 
     */ 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
             throws ServletException, IOException { 
         processRequest(request, response); 
     } 
     /** *//** 
      * Returns a short description of the servlet. 
      */ 
     public String getServletInfo() { 
         return "Short description"; 
     } 
}