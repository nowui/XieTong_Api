package com.hongluomeng.controller;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.hongluomeng.common.Const;
import com.hongluomeng.common.MyPoiRender;
import com.hongluomeng.common.Utility;
import com.hongluomeng.model.Student;
import com.hongluomeng.service.StudentService;
import com.hongluomeng.type.CodeEnum;
import com.hongluomeng.validator.StudentValidator;

public class StudentController extends BaseController {

	private StudentService studentService = new StudentService();

	@Before(StudentValidator.class)
	@ActionKey(Const.URL_STUDENT_LIST)
	public void list() {
		JSONObject jsonObject = getAttr(Const.KEY_REQUEST);

		List<Student> studentList = studentService.list(jsonObject);

		Integer count = studentService.count(jsonObject);

        renderJson(Utility.setResponse(CodeEnum.CODE_200, "", count, studentList));
    }

	@Before(StudentValidator.class)
	@ActionKey(Const.URL_STUDENT_FIND)
	public void find() {
		JSONObject jsonObject = getAttr(Const.KEY_REQUEST);

		Student student = studentService.find(jsonObject);

        renderJson(Utility.setResponse(CodeEnum.CODE_200, "", student));
    }

	@Before(StudentValidator.class)
	@ActionKey(Const.URL_STUDENT_SAVE)
	public void save() {
		JSONObject jsonObject = getAttr(Const.KEY_REQUEST);

		studentService.save(jsonObject);

        renderJson(Utility.setResponse(CodeEnum.CODE_200, "", null));
	}

	@Before(StudentValidator.class)
	@ActionKey(Const.URL_STUDENT_UPDATE)
	public void update() {
		JSONObject jsonObject = getAttr(Const.KEY_REQUEST);

		studentService.update(jsonObject);

        renderJson(Utility.setResponse(CodeEnum.CODE_200, "", null));
	}

	@Before(StudentValidator.class)
	@ActionKey(Const.URL_STUDENT_DELETE)
	public void delete() {
		JSONObject jsonObject = getAttr(Const.KEY_REQUEST);

		studentService.delete(jsonObject);

        renderJson(Utility.setResponse(CodeEnum.CODE_200, "", null));
	}

	@Before(StudentValidator.class)
	@ActionKey(Const.URL_STUDENT_LOGIN)
	public void login() {
		JSONObject jsonObject = getAttr(Const.KEY_REQUEST);

		Map<String, Object> map = studentService.login(jsonObject);

		if (map == null) {
	        renderJson(Utility.setResponse(CodeEnum.CODE_400, "用户名或者密码不正确", null));
		} else {
	        renderJson(Utility.setResponse(CodeEnum.CODE_200, "", map));
		}
	}

	@Before(StudentValidator.class)
	@ActionKey(Const.URL_STUDENT_EXPORT)
	public void export() {
		//JSONObject jsonObject = getAttr(Const.KEY_REQUEST);

		HSSFWorkbook wb = new HSSFWorkbook();

		HSSFSheet sheet = wb.createSheet("test");
		HSSFRow row = sheet.createRow((int) 0);
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		HSSFCell cell = row.createCell(0);
        cell.setCellValue("班别");
        cell.setCellStyle(style);
        cell = row.createCell(1);
        cell.setCellValue("学号");
        cell.setCellStyle(style);
        cell = row.createCell(2);
        cell.setCellValue("姓名");
        cell.setCellStyle(style);
        cell = row.createCell(3);
        cell.setCellValue("性别");
        cell.setCellStyle(style);

        MyPoiRender myPoiRender = new MyPoiRender(wb, "学生信息");

        render(myPoiRender);
	}

}
