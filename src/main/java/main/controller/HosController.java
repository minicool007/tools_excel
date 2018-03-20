package main.controller;

import main.dao.HosMapper;
import main.pojo.HospitalFamilyVo;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Controller
public class HosController {

    @Autowired
    private HosMapper hosMapper;

    @RequestMapping(value = "/hostoexcel")
    public String hostoexcel() throws IOException {

        String xlsFile = "f:/data/家庭医生数据.xlsx";     //输出文件
        SXSSFWorkbook wb = new SXSSFWorkbook(100);
        Sheet sheet = null;     //工作表对象
        Row nRow = null;        //行对象
        Cell nCell = null;      //列对象

        long  startTime = System.currentTimeMillis();

        List<HospitalFamilyVo> items = hosMapper.selectHosByCutom();
        int columns = 13;
        int cnt = 0;
        int pageRowNo = 0;  //页行号

        while( cnt < items.size() ){
            //每20万数据就分sheet
            if(cnt % 200000 == 0){
                sheet = wb.createSheet("第"+(cnt/200000)+"数据");
                sheet = wb.getSheetAt(cnt/200000);
                pageRowNo = 0;      //每当新建了工作表就将当前工作表的行号重置为0
                fillsheet(nRow, sheet, nCell);
            }

            nRow = sheet.createRow(++pageRowNo);    //新建行对象
            // 填充一行数据
            nCell = nRow.createCell(0);
            nCell.setCellValue(items.get(cnt).getPROVINCE());
            nCell = nRow.createCell(1);
            nCell.setCellValue(items.get(cnt).getCITY());
            nCell = nRow.createCell(2);
            nCell.setCellValue(items.get(cnt).getDISTRICT());
            nCell = nRow.createCell(3);
            nCell.setCellValue(items.get(cnt).getHOSPITAL_NAME());
            nCell = nRow.createCell(4);
            nCell.setCellValue(items.get(cnt).getALIAS());
            nCell = nRow.createCell(5);
            if(items.get(cnt).getPOINT_LAT().equals("")){
                nCell.setCellValue("");
            }else{
                nCell.setCellValue(items.get(cnt).getPOINT_LAT()+","+items.get(cnt).getPOINT_LNG());
            }

            nCell = nRow.createCell(6);
            nCell.setCellValue(items.get(cnt).getADDRESS());
            nCell = nRow.createCell(7);
            nCell.setCellValue(items.get(cnt).getTEL());
            nCell = nRow.createCell(8);
            nCell.setCellValue(items.get(cnt).getTYPE());
            nCell = nRow.createCell(9);
            nCell.setCellValue(items.get(cnt).getRATE());
            nCell = nRow.createCell(10);
            nCell.setCellValue(items.get(cnt).getLINK());
            nCell = nRow.createCell(11);
            nCell.setCellValue(items.get(cnt).getIS_HOS());
            nCell = nRow.createCell(12);
            nCell.setCellValue(items.get(cnt).getNATURE());
            cnt++;
        }
        long finishedTime = System.currentTimeMillis(); //处理完成时间
        System.out.println("finished execute  time: " + (finishedTime - startTime)/1000 + "m");

        FileOutputStream fOut = new FileOutputStream(xlsFile);
        wb.write(fOut);
        fOut.flush();
        fOut.close();
        long stopTime = System.currentTimeMillis();
        System.out.println("write xlsx file time: " + (stopTime - startTime)/1000 + "m");

        return "success!";


    }


    public void fillsheet(Row nRow, Sheet sheet, Cell nCell){
        nRow = sheet.createRow(0);
        // 填充一行数据
        nCell = nRow.createCell(0);
        nCell.setCellValue("省");
        nCell = nRow.createCell(1);
        nCell.setCellValue("城市");
        nCell = nRow.createCell(2);
        nCell.setCellValue("城区");
        nCell = nRow.createCell(3);
        nCell.setCellValue("医院名称");
        nCell = nRow.createCell(4);
        nCell.setCellValue("别名");
        nCell = nRow.createCell(5);
        nCell.setCellValue("坐标");
        nCell = nRow.createCell(6);
        nCell.setCellValue("医院地址");
        nCell = nRow.createCell(7);
        nCell.setCellValue("医院电话");
        nCell = nRow.createCell(8);
        nCell.setCellValue("医院类型");
        nCell = nRow.createCell(9);
        nCell.setCellValue("医院等级");
        nCell = nRow.createCell(10);
        nCell.setCellValue("医院链接");
        nCell = nRow.createCell(11);
        nCell.setCellValue("是否医保");
        nCell = nRow.createCell(12);
        nCell.setCellValue("医院性质");
    }
}
