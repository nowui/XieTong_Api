package com.hongluomeng.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hongluomeng.common.Const;
import com.hongluomeng.common.MyPoiRender;
import com.hongluomeng.common.Utility;
import com.hongluomeng.dao.StudentDao;
import com.hongluomeng.model.Grade;
import com.hongluomeng.model.Student;
import com.hongluomeng.model.User;
import com.hongluomeng.model.UserRole;
import com.hongluomeng.type.UserEnum;
import com.jfinal.upload.UploadFile;

public class StudentService {

	private StudentDao studentDao = new StudentDao();
	private UserService userService = new UserService();
	private RoleService roleService = new RoleService();
	private GradeService gradeService = new GradeService();
	private AuthorizationService authorizationService = new AuthorizationService();

	public Map<String, Object> list(JSONObject jsonObject) {
		Student studentMap = jsonObject.toJavaObject(Student.class);

		Integer count = studentDao.count(studentMap.getGrade_id(), studentMap.getStudent_name());

		List<Student> studentList = studentDao.list(studentMap.getGrade_id(), studentMap.getStudent_name(), Utility.getStarNumber(jsonObject), Utility.getEndNumber(jsonObject));

		Map<String, Object> resultMap = Utility.setResultMap(count, studentList);

		return resultMap;
	}

	public Student find(JSONObject jsonObject) {
		Student studentMap = jsonObject.toJavaObject(Student.class);

		Student student;

		if (Utility.isNullOrEmpty(studentMap.getStudent_id())) {
			student = new Student();
		} else {
			student = studentDao.findByStudent_id(studentMap.getStudent_id());

			User user = userService.findByUser_id(student.getUser_id());

			student.setUser_account(user.getUser_account());
		}

		List<Grade> gradeList = gradeService.listAll();
		student.setGradeList(gradeList);

		return student;
	}

	public Student findByUser_id(String user_id) {
		return studentDao.findByUser_id(user_id);
	}

	public void save(JSONObject jsonObject) {
		Student studentMap = jsonObject.toJavaObject(Student.class);

		User userMap = jsonObject.toJavaObject(User.class);

		String request_user_id = jsonObject.getString(Const.KEY_REQUEST_USER_ID);

		/*String user_id = userService.saveByAccount(userMap.getUser_account(), userMap.getUser_password(), UserEnum.STUDENT.getKey(), request_user_id);

		studentMap.setUser_id(user_id);

		studentDao.save(studentMap, request_user_id);

		userService.updateObject_idByUser_id(studentMap.getStudent_id(), user_id);*/

		save(studentMap.getGrade_id(), studentMap.getStudent_number(), studentMap.getStudent_name(), studentMap.getStudent_sex(), userMap.getUser_account(), userMap.getUser_password(), request_user_id);
	}

	private void save(String grade_id, String student_number, String student_name, String student_sex, String user_account, String user_password, String request_user_id) {
		Student student = new Student();
		student.setGrade_id(grade_id);
		student.setStudent_number(student_number);
		student.setStudent_name(student_name);
		student.setStudent_sex(student_sex);

		String user_id = userService.saveByAccount(user_account, user_password, UserEnum.STUDENT.getKey(), request_user_id);

		student.setUser_id(user_id);

		studentDao.save(student, request_user_id);

		userService.updateObject_idByUser_id(student.getStudent_id(), user_id);
	}

	public void update(JSONObject jsonObject) {
		Student studentMap = jsonObject.toJavaObject(Student.class);

		User userMap = jsonObject.toJavaObject(User.class);

		String request_user_id = jsonObject.getString(Const.KEY_REQUEST_USER_ID);

		studentDao.update(studentMap, request_user_id);

		userService.updateUser_account(jsonObject);

		userService.updateUser_passwordByUser_id(userMap.getUser_id(), userMap.getUser_password(), request_user_id);
	}

	public void delete(JSONObject jsonObject) {
		Student studentMap = jsonObject.toJavaObject(Student.class);

		String request_user_id = jsonObject.getString(Const.KEY_REQUEST_USER_ID);

		studentDao.delete(studentMap.getStudent_id(), request_user_id);

		userService.deleteByObject_id(studentMap.getStudent_id(), request_user_id);
	}

	public void delete2(JSONObject jsonObject) {
		JSONArray jsonArray = jsonObject.getJSONArray(Student.KEY_STUDENT_ID);

		String request_user_id = jsonObject.getString(Const.KEY_REQUEST_USER_ID);

		for(int i = 0; i < jsonArray.size(); i++) {
			String student_id = jsonArray.getString(i);

			studentDao.delete(student_id, request_user_id);

			userService.deleteByObject_id(student_id, request_user_id);
		}
	}

	public List<Map<String, Object>> listRole(JSONObject jsonObject) {
		Student studentMap = jsonObject.toJavaObject(Student.class);

		List<Map<String, Object>> list  = roleService.listByUser_idAndUser_type(studentMap.getUser_id(), UserEnum.STUDENT.getKey());

        return list;
	}

	public void updateRole(JSONObject jsonObject) {
		Student studentMap = jsonObject.toJavaObject(Student.class);
		JSONArray jsonArray = jsonObject.getJSONArray(UserRole.KEY_USER_ROLE);

		List<UserRole> userRoleList = new ArrayList<UserRole>();

		for(int i = 0; i < jsonArray.size(); i++) {
			JSONObject object = jsonArray.getJSONObject(i);

			UserRole userRole = object.toJavaObject(UserRole.class);
			userRole.setUser_id(studentMap.getUser_id());
			userRole.setUser_type(UserEnum.STUDENT.getKey());

			userRoleList.add(userRole);
		}

		roleService.updateUserRole(userRoleList, studentMap.getUser_id(), UserEnum.STUDENT.getKey());
	}

	public Map<String, Object> login(JSONObject jsonObject) {
		User userMap = jsonObject.toJavaObject(User.class);

		User user = userService.loginByUser_accountAndUser_password(userMap.getUser_account(), userMap.getUser_password());

		if(user == null) {
			return null;
		} else {
			Map<String, Object> resultMap = new HashMap<String, Object>();

			String token = authorizationService.saveByUser_id(user.getUser_id());

			Student student = studentDao.findByUser_id(user.getUser_id());

			resultMap.put(Const.KEY_TOKEN, token);
			resultMap.put(Student.KEY_STUDENT_NAME, student.getStudent_name());

			return resultMap;
		}
	}

	public MyPoiRender export() {
		HSSFWorkbook wb = new HSSFWorkbook();

		HSSFSheet sheet = wb.createSheet("学生信息");
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		HSSFRow row = sheet.createRow(0);
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
        cell = row.createCell(4);
        cell.setCellValue("密码");
        cell.setCellStyle(style);

        MyPoiRender myPoiRender = new MyPoiRender(wb, "学生信息");

        return myPoiRender;
	}

	public void upload(UploadFile uploadFile, String request_user_id) {
		String suffix = uploadFile.getFileName().substring(uploadFile.getFileName().lastIndexOf(".") + 1);

		if (!".xls.xlsx".contains(suffix)) {
			uploadFile.getFile().delete();

			throw new RuntimeException("上传文件格式不正确!");
		} else {
			try {
				InputStream is = new FileInputStream(uploadFile.getFile());
				POIFSFileSystem fs = new POIFSFileSystem(is);
				@SuppressWarnings("resource")
				HSSFWorkbook wb = new HSSFWorkbook(fs);
				HSSFSheet sheet = wb.getSheetAt(0);
				int trLength = sheet.getLastRowNum();

				List<Grade> gradeList = gradeService.listAll();

				for(int i = 1; i <= trLength; i++) {
					HSSFRow row = sheet.getRow(i);

					HSSFCell gradeCell = row.getCell(0);
					gradeCell.setCellType(Cell.CELL_TYPE_STRING);

					HSSFCell numberCell = row.getCell(1);
					numberCell.setCellType(Cell.CELL_TYPE_STRING);

					HSSFCell nameCell = row.getCell(2);
					nameCell.setCellType(Cell.CELL_TYPE_STRING);

					HSSFCell sexCell = row.getCell(3);
					sexCell.setCellType(Cell.CELL_TYPE_STRING);

					HSSFCell passwordCell = row.getCell(4);
					passwordCell.setCellType(Cell.CELL_TYPE_STRING);

					String student_grade = gradeCell.getStringCellValue();
					String student_number = gradeCell.getStringCellValue() + (numberCell.getStringCellValue().length() == 1 ? "0" : "") + numberCell.getStringCellValue();
					String student_name = nameCell.getStringCellValue();
					String student_sex = sexCell.getStringCellValue();
					String user_password = passwordCell.getStringCellValue();

					if(Utility.isNullOrEmpty(student_number) || Utility.isNullOrEmpty(student_name) || Utility.isNullOrEmpty(student_sex)) {

					} else {
						String grade_id = "";

						for(Grade grade : gradeList) {
							if(grade.getGrade_name().equals(student_grade)) {
								grade_id = grade.getGrade_id();

								break;
							}
						}

						if(grade_id != "") {
							Integer count = userService.countByUser_idAndUser_account("", student_number);

							if(count == 0) {
								save(grade_id, student_number, student_name, student_sex, student_number, user_password, request_user_id);
							}
						}
					}
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			uploadFile.getFile().delete();
		}
	}

	public void updatePassword(JSONObject jsonObject) {
		User userMap = jsonObject.toJavaObject(User.class);

		String request_user_id = jsonObject.getString(Const.KEY_REQUEST_USER_ID);

		userService.updateUser_passwordByUser_id(request_user_id, userMap.getUser_password(), request_user_id);
	}

}
