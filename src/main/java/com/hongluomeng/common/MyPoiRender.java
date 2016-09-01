package com.hongluomeng.common;

import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.jfinal.render.Render;

public class MyPoiRender extends Render {

	private HSSFWorkbook wb;
    private String name;

    public  MyPoiRender(HSSFWorkbook wb, String name){
        this.wb = wb;
        this.name = name;
    }

    public void render() {
        try {
	    	response.reset();
	        response.addHeader("Content-disposition", "attachment; filename=" + java.net.URLEncoder.encode(name, "UTF-8") + ".xls");
	        response.setContentType("application/x-msdownload");

            wb.write(response.getOutputStream());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
