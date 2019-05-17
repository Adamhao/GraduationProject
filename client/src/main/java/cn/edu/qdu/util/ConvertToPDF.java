package cn.edu.qdu.util;

import org.apache.commons.io.FileUtils;
import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
import org.artofsolving.jodconverter.office.OfficeManager;

import java.io.File;

public class ConvertToPDF implements Runnable{
    private  OfficeManager officeManager;
    public static String OFFICE_HOME = "C:\\Program Files (x86)\\OpenOffice 4";//本机OpenOffice安装目录
    private int port = 8100;//默认使用端口
    private String inputFile ="";//待转化文件的完整路径
    public ConvertToPDF(String inputFile, int port){
        this.port = port;
        this.inputFile = inputFile;
    }

    public void run() {
        orgconvert2PDF(inputFile);
    }

    public  void orgconvert2PDF(String inputFile) {
        //pdfFile是目标文件  odtFile是中间文件
        String pdfFile = inputFile.substring(0, inputFile.lastIndexOf(".")) + ".pdf";
        String odtFile = inputFile.substring(0, inputFile.lastIndexOf("."))+ ".odt";
        File odt = new File(odtFile);
        if (odt.exists()) {
            System.out.println("odt文件已存在！");
            inputFile = odtFile;
        } else {
            try {
                File input = new File(inputFile);
                FileUtils.copyFile(input, odt);
                inputFile = odtFile;
            } catch (Exception e) {
                System.out.println("文档不存在！");
                e.printStackTrace();
            }
        }
        try {
            long now = System.currentTimeMillis();
            startService(port);
            System.out.println( "  进行文档转换转换:" + inputFile + " --> " + pdfFile);
            OfficeDocumentConverter converter = new OfficeDocumentConverter(officeManager);
            converter.convert(new File(inputFile), new File(pdfFile));
            //转化完成关闭服务
            stopService();
            odt.delete();
            long old = System.currentTimeMillis();
            System.out.println("ConvertSuccess Start = "+ old + ", end = "+ now +  ", "+ " UsedTime = " + (old - now) / 1000.0 + " S");
            System.out.println(" 文档转换成功....end");
        } catch (Exception e) {
            stopService();
            System.out.println(  "文档转换失败....error");
        }
    }

    public  void startService(int i) {
        DefaultOfficeManagerConfiguration configuration = new DefaultOfficeManagerConfiguration();
        try {
            //先关闭再启动
           // System.out.println("准备启动服务....");
            configuration.setOfficeHome(OFFICE_HOME);//设置OpenOffice.org安装目录
            configuration.setPortNumbers(i); //设置转换端口，默认为8100
            configuration.setTaskExecutionTimeout(1000 * 60 * 5L);//设置任务执行超时30分钟
            configuration.setTaskQueueTimeout(1000 * 60 * 60 * 24L);//设置任务队列超时24小时
            officeManager = configuration.buildOfficeManager();
            officeManager.start();    //启动服务
            System.out.println("office转换服务启动成功!");
        } catch (Exception ce) {
            System.out.println("office转换服务启动失败!详细信息:" + ce);
        }
    }
    public  void stopService() {
      //  System.out.println("关闭office转换服务....");
        if (officeManager != null) {
            officeManager.stop();
        }
        System.out.println("关闭office转换成功!");
   }
}
