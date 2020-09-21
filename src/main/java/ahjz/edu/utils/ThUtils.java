package ahjz.edu.utils;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ThUtils {
    private static TemplateEngine te;
    static {
        te = new TemplateEngine();
        //创建解析器 使用这个解析器会自动查找resources目录下的html文件
        ClassLoaderTemplateResolver r = new ClassLoaderTemplateResolver();
        //设置字符集
        r.setCharacterEncoding("UTF-8");
        //将解析器和模板引擎对象关联
        te.setTemplateResolver(r);
    }

    public static void print(String fileName, Context context,
                             HttpServletResponse response) throws IOException {
        //让html模板页面和Context里面的数据整合到一起得到一个新的html
        String html = te.process(fileName,context);
        //把得到的html返回给客户端浏览器
        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();//异常抛出
        pw.print(html);
        pw.close();
    }

}
