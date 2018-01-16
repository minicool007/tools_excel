package main.controller;

import main.dao.DataMapper;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import main.pojo.DataVo;

import java.io.*;
import java.util.List;

@Controller
public class mainController {
    @Autowired
    private DataMapper dataMapper;

    @RequestMapping(value = "/toexcel")
    public String toExcel() throws IOException {
        String xlsFile = "f:/徐州房产数据.xlsx";     //输出文件
        SXSSFWorkbook wb = new SXSSFWorkbook(100);
        Sheet sheet = null;     //工作表对象
        Row nRow = null;        //行对象
        Cell nCell = null;      //列对象

        long  startTime = System.currentTimeMillis();


        //从mysql中读取百万级数据
        List<DataVo> items = dataMapper.selectDataByCutom();
        int columns = 30;
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
            nCell.setCellValue(items.get(cnt).getBUILD_NAME());
            nCell = nRow.createCell(1);
            nCell.setCellValue(items.get(cnt).getDEVELOP_COMPANY());
            nCell = nRow.createCell(2);
            nCell.setCellValue(items.get(cnt).getBUILD_ADDRESS());
            nCell = nRow.createCell(3);
            nCell.setCellValue(items.get(cnt).getDISTRICT_NAME());
            nCell = nRow.createCell(4);
            nCell.setCellValue(items.get(cnt).getALIAS());
            nCell = nRow.createCell(5);
            nCell.setCellValue(items.get(cnt).getLOCATION_LNG());
            nCell = nRow.createCell(6);
            nCell.setCellValue(items.get(cnt).getLOCATION_LAT());
            nCell = nRow.createCell(7);
            nCell.setCellValue(items.get(cnt).getSTART_TIME());
            nCell = nRow.createCell(8);
            nCell.setCellValue(items.get(cnt).getEND_TIME());
            nCell = nRow.createCell(9);
            nCell.setCellValue(items.get(cnt).getUSED_TIME());
            nCell = nRow.createCell(10);
            nCell.setCellValue(items.get(cnt).getGREEN_RATE());
            nCell = nRow.createCell(11);
            nCell.setCellValue(items.get(cnt).getVOLUM_RATE());
            nCell = nRow.createCell(12);
            nCell.setCellValue(items.get(cnt).getMANAGE_FEE());
            nCell = nRow.createCell(13);
            nCell.setCellValue(items.get(cnt).getAVRAGE_PRICE());
            nCell = nRow.createCell(14);
            nCell.setCellValue(items.get(cnt).getBUILD_AREA());
            nCell = nRow.createCell(15);
            nCell.setCellValue(items.get(cnt).getPARK_SPACE());
            nCell = nRow.createCell(16);
            nCell.setCellValue(items.get(cnt).getBUILD_INFO());
            nCell = nRow.createCell(17);
            nCell.setCellValue(items.get(cnt).getRELATIVE_INFO());
            nCell = nRow.createCell(18);
            nCell.setCellValue(items.get(cnt).getFLOOR_NAME());
            nCell = nRow.createCell(19);
            nCell.setCellValue(items.get(cnt).getBUILD_LOCATION());
            nCell = nRow.createCell(20);
            nCell.setCellValue(items.get(cnt).getFOOT_AREA());
            nCell = nRow.createCell(21);
            nCell.setCellValue(items.get(cnt).getUNIT_NAME());
            nCell = nRow.createCell(22);
            nCell.setCellValue(items.get(cnt).getROOM_NAME());
            nCell = nRow.createCell(23);
            nCell.setCellValue(items.get(cnt).getSUIT_PNG());
            nCell = nRow.createCell(24);
            nCell.setCellValue(items.get(cnt).getBUILD_PNG());
            nCell = nRow.createCell(25);
            nCell.setCellValue(items.get(cnt).getUSAGE_MUL());
            nCell = nRow.createCell(26);
            nCell.setCellValue(items.get(cnt).getROOM_STRUCT());
            nCell = nRow.createCell(27);
            nCell.setCellValue(items.get(cnt).getROOM_AREA());
            nCell = nRow.createCell(28);
            nCell.setCellValue(items.get(cnt).getSUIT_AREA());
            nCell = nRow.createCell(29);
            nCell.setCellValue(items.get(cnt).getDISP_AREA());
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
        nCell.setCellValue("项目名称（楼盘名称）");
        nCell = nRow.createCell(1);
        nCell.setCellValue("开发企业（开发公司）");
        nCell = nRow.createCell(2);
        nCell.setCellValue("项目地址（楼盘地址）");
        nCell = nRow.createCell(3);
        nCell.setCellValue("区属（城区）");
        nCell = nRow.createCell(4);
        nCell.setCellValue("推广名称（别名）");
        nCell = nRow.createCell(5);
        nCell.setCellValue("经度");
        nCell = nRow.createCell(6);
        nCell.setCellValue("纬度");
        nCell = nRow.createCell(7);
        nCell.setCellValue("开工时间");
        nCell = nRow.createCell(8);
        nCell.setCellValue("竣工时间");
        nCell = nRow.createCell(9);
        nCell.setCellValue("使用年限");
        nCell = nRow.createCell(10);
        nCell.setCellValue("绿化率");
        nCell = nRow.createCell(11);
        nCell.setCellValue("容积率");
        nCell = nRow.createCell(12);
        nCell.setCellValue("物业费");
        nCell = nRow.createCell(13);
        nCell.setCellValue("均价");
        nCell = nRow.createCell(14);
        nCell.setCellValue("建筑面积（楼盘）");
        nCell = nRow.createCell(15);
        nCell.setCellValue("停车位");
        nCell = nRow.createCell(16);
        nCell.setCellValue("项目信息（楼盘描述）");
        nCell = nRow.createCell(17);
        nCell.setCellValue("相关信息");
        nCell = nRow.createCell(18);
        nCell.setCellValue("包含楼盘（楼幢名称）");
        nCell = nRow.createCell(19);
        nCell.setCellValue("项目坐落");
        nCell = nRow.createCell(20);
        nCell.setCellValue("土地面积");
        nCell = nRow.createCell(21);
        nCell.setCellValue("单元名称");
        nCell = nRow.createCell(22);
        nCell.setCellValue("房间名称");
        nCell = nRow.createCell(23);
        nCell.setCellValue("套型图（图片地址）");
        nCell = nRow.createCell(24);
        nCell.setCellValue("户型图");
        nCell = nRow.createCell(25);
        nCell.setCellValue("房屋用途");
        nCell = nRow.createCell(26);
        nCell.setCellValue("房屋结构");
        nCell = nRow.createCell(27);
        nCell.setCellValue("建筑面积（房屋）");
        nCell = nRow.createCell(28);
        nCell.setCellValue("套内面积（房屋）");
        nCell = nRow.createCell(29);
        nCell.setCellValue("分摊面积（房屋）");
    }

}
