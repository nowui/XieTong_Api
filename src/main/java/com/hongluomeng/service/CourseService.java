package com.hongluomeng.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hongluomeng.common.Const;
import com.hongluomeng.common.MyPoiRender;
import com.hongluomeng.common.Utility;
import com.hongluomeng.dao.CourseDao;
import com.hongluomeng.model.Course;
import com.hongluomeng.model.CourseApply;
import com.hongluomeng.model.CourseApplyHistory;
import com.hongluomeng.model.Grade;
import com.hongluomeng.model.Student;
import com.hongluomeng.model.Teacher;
import com.hongluomeng.model.WebConfig;

public class CourseService {

	private CourseDao courseDao = new CourseDao();
	private GradeService gradeService = new GradeService();
	private TeacherService teacherService = new TeacherService();
	private StudentService studentService = new StudentService();
	private CourseApplyService courseApplyService = new CourseApplyService();
	private CourseApplyHistoryService courseApplyHistoryService = new CourseApplyHistoryService();
	private WebConfigService webConfigService = new WebConfigService();

	public Map<String, Object> list(JSONObject jsonObject) {
		//Course courseMap = jsonObject.toJavaObject(Course.class);

		Integer count = courseDao.count();

		List<Course> courseList = courseDao.list(Utility.getStarNumber(jsonObject), Utility.getEndNumber(jsonObject));

		Map<String, Object> resultMap = Utility.setResultMap(count, courseList);

		return resultMap;
	}

	public List<Course> listForApply(JSONObject jsonObject) {
		String request_user_id = jsonObject.getString(Const.KEY_REQUEST_USER_ID);

		Student student = studentService.findByUser_id(request_user_id);

		Grade grade = gradeService.findByGrade_id(student.getGrade_id());

		List<Course> courseList = courseDao.listByGrade_id(grade.getGrade_id());

		List<CourseApply> courseApplyList = courseApplyService.listByUser_id(request_user_id);

		for(Course course : courseList) {
			course.setIsApply(false);

			for(CourseApply courseApply : courseApplyList) {
				if(course.getCourse_id().equals(courseApply.getCourse_id())) {
					course.setIsApply(true);

					break;
				}
			}
		}

		return courseList;
	}

	public Course find(JSONObject jsonObject) {
		Course courseMap = jsonObject.toJavaObject(Course.class);

		Course course;

		if (Utility.isNullOrEmpty(courseMap.getCourse_id())) {
			course = new Course();
		} else {
			course = courseDao.findByCourse_id(courseMap.getCourse_id());
		}

		List<Grade> gradeList = gradeService.listAll();
		course.setGradeList(gradeList);

		List<Teacher> teacherList = teacherService.ListAll();
		course.setTeacherList(teacherList);

		return course;
	}

	public Course findForApply(JSONObject jsonObject) {
		Course courseMap = jsonObject.toJavaObject(Course.class);

		String request_user_id = jsonObject.getString(Const.KEY_REQUEST_USER_ID);

		Course course = courseDao.findByCourse_id(courseMap.getCourse_id());

		JSONArray jsonArray = course.getCourse_teacher();

		JSONArray teacherArray = new JSONArray();

		for(int i = 0; i < jsonArray.size(); i++) {
			String teacher_id = jsonArray.getString(i);

			Teacher teacher = teacherService.findByTeacher_id(teacher_id);

			teacherArray.add(teacher.getTeacher_name());
		}

		course.setCourse_teacher(teacherArray.toJSONString());

		Integer courseStudentCourseApplyCount = courseApplyService.countByUser_idAndCourse_id(request_user_id, course.getCourse_id());

		if(courseStudentCourseApplyCount == 0) {
			course.setIsApply(false);
		} else {
			course.setIsApply(true);
		}

		return course;
	}

	public void save(JSONObject jsonObject) {
		Course courseMap = jsonObject.toJavaObject(Course.class);

		String request_user_id = jsonObject.getString(Const.KEY_REQUEST_USER_ID);

		courseDao.save(courseMap, request_user_id);
	}

	public void update(JSONObject jsonObject) {
		Course courseMap = jsonObject.toJavaObject(Course.class);

		String request_user_id = jsonObject.getString(Const.KEY_REQUEST_USER_ID);

		courseDao.update(courseMap, request_user_id);
	}

	public void delete(JSONObject jsonObject) {
		Course courseMap = jsonObject.toJavaObject(Course.class);

		String request_user_id = jsonObject.getString(Const.KEY_REQUEST_USER_ID);

		courseDao.delete(courseMap.getCourse_id(), request_user_id);
	}

	public void apply(JSONObject jsonObject) {
		WebConfig webConfig = webConfigService.find();

		if(webConfig == null) {
			throw new RuntimeException("已经过期，停止申请!");
		} else {
			Date startDate = Utility.getDateTime(webConfig.getWeb_config_apply_start_time());
			Date nowDate = new Date();
			Date endDate = Utility.getDateTime(webConfig.getWeb_config_apply_end_time());

			if(nowDate.before(startDate)) {
				throw new RuntimeException(webConfig.getWeb_config_apply_start_time() + "正式开放申请!");
			}

			if(nowDate.after(endDate)) {
				throw new RuntimeException("已经过期，停止申请!");
			}
		}

		Course courseMap = jsonObject.toJavaObject(Course.class);

		String request_user_id = jsonObject.getString(Const.KEY_REQUEST_USER_ID);

		Course course = courseDao.findByCourse_id(courseMap.getCourse_id());

		Student student = studentService.findByUser_id(request_user_id);

		JSONArray jsonArray = course.getCourse_grade();

		Boolean isExit = false;

		for(int i = 0; i < jsonArray.size(); i++) {
			String grade_id = jsonArray.getString(i);

			if(grade_id.equals(student.getGrade_id())) {
				isExit = true;

				break;
			}
		}

		if(isExit) {
			if(courseMap.getIsApply()) {
				Integer courseApplyCount = courseApplyService.countByCourse_id(courseMap.getCourse_id());

				if(courseApplyCount < course.getCourse_apply_limit()) {
					Integer courseClassCount = courseApplyService.countByUser_idAndCourse_class(request_user_id, course.getCourse_class());

					if(courseClassCount == 0) {
						Grade grade = gradeService.findByGrade_id(student.getGrade_id());

						Integer studentCourseApplyCount = courseApplyService.countByUser_id(request_user_id);

						if(grade.getGrade_course_apply_limit() > studentCourseApplyCount) {
							Integer courseStudentCourseApplyCount = courseApplyService.countByUser_idAndCourse_id(request_user_id, course.getCourse_id());

							if(courseStudentCourseApplyCount == 0) {
								courseApplyService.save(course.getCourse_id(), request_user_id);

								courseApplyHistoryService.save(courseMap.getCourse_id(), request_user_id, true);
							} else {
								throw new RuntimeException("这门课程已经申请过,不能再申请!");
							}
						} else {
							throw new RuntimeException("已经申请了" + grade.getGrade_course_apply_limit() + "门课程,不能再申请!");
						}
					} else {
						throw new RuntimeException("这天已经申请过课程,不能再申请!");
					}

				} else {
					throw new RuntimeException("已经到达限制人数,不能再申请!");
				}
			} else {
				courseApplyService.delete(courseMap.getCourse_id(), request_user_id);

				courseApplyHistoryService.save(courseMap.getCourse_id(), request_user_id, false);
			}
		} else {
			throw new RuntimeException("班级不同,不能再申请!");
		}
	}

	public List<CourseApplyHistory> history(JSONObject jsonObject) {
		Course courseMap = jsonObject.toJavaObject(Course.class);

		String request_user_id = jsonObject.getString(Const.KEY_REQUEST_USER_ID);

		return courseApplyHistoryService.listByCourse_idAndUser_id(courseMap.getCourse_id(), request_user_id);
	}

	public MyPoiRender export() {
		List<CourseApply> courseApplyListOrderByCourse_id = courseApplyService.listOrderByCourse_id();
		List<CourseApply> courseApplyListOrderByGrade_idAndStudent_id = courseApplyService.listOrderByGrade_idAndStudent_id();

		List<Teacher> teacherList = teacherService.ListAll();

		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		HSSFSheet sheet = wb.createSheet("总-按科目");

		HSSFRow row = sheet.createRow(0);
		HSSFCell cell = row.createCell(0);
        cell.setCellValue("姓名");
        cell.setCellStyle(style);
        cell = row.createCell(1);
        cell.setCellValue("班级");
        cell.setCellStyle(style);
        cell = row.createCell(2);
        cell.setCellValue("学号");
        cell.setCellStyle(style);
        cell = row.createCell(3);
        cell.setCellValue("性别");
        cell.setCellStyle(style);
        cell = row.createCell(4);
        cell.setCellValue("课程名称");
        cell.setCellStyle(style);
        cell = row.createCell(5);
        cell.setCellValue("上课时间");
        cell.setCellStyle(style);
        cell = row.createCell(6);
        cell.setCellValue("上课老师");
        cell.setCellStyle(style);
        cell = row.createCell(7);
        cell.setCellValue("上课地点");
        cell.setCellStyle(style);

        for(int i = 0; i < courseApplyListOrderByCourse_id.size(); i++) {
        	CourseApply courseApply = courseApplyListOrderByCourse_id.get(i);

        	String teacher_name = getTeacherName(courseApply.getCourse_teacher(), teacherList);

        	String class_name = getClassName(courseApply.getCourse_class());

    		row = sheet.createRow(i + 1);
    		cell = row.createCell(0);
            cell.setCellValue(courseApply.getStudent_name());
            cell.setCellStyle(style);
            cell = row.createCell(1);
            cell.setCellValue(courseApply.getGrade_name());
            cell.setCellStyle(style);
            cell = row.createCell(2);
            cell.setCellValue(courseApply.getStudent_number());
            cell.setCellStyle(style);
            cell = row.createCell(3);
            cell.setCellValue(courseApply.getStudent_sex());
            cell.setCellStyle(style);
            cell = row.createCell(4);
            cell.setCellValue(courseApply.getCourse_name());
            cell.setCellStyle(style);
            cell = row.createCell(5);
            cell.setCellValue(class_name);
            cell.setCellStyle(style);
            cell = row.createCell(6);
            cell.setCellValue(teacher_name);
            cell.setCellStyle(style);
            cell = row.createCell(7);
            cell.setCellValue(courseApply.getCourse_address());
            cell.setCellStyle(style);
        }

        sheet = wb.createSheet("总-按班级");

		row = sheet.createRow(0);
		cell = row.createCell(0);
        cell.setCellValue("姓名");
        cell.setCellStyle(style);
        cell = row.createCell(1);
        cell.setCellValue("班级");
        cell.setCellStyle(style);
        cell = row.createCell(2);
        cell.setCellValue("学号");
        cell.setCellStyle(style);
        cell = row.createCell(3);
        cell.setCellValue("性别");
        cell.setCellStyle(style);
        cell = row.createCell(4);
        cell.setCellValue("课程名称");
        cell.setCellStyle(style);
        cell = row.createCell(5);
        cell.setCellValue("上课时间");
        cell.setCellStyle(style);
        cell = row.createCell(6);
        cell.setCellValue("上课老师");
        cell.setCellStyle(style);
        cell = row.createCell(7);
        cell.setCellValue("上课地点");
        cell.setCellStyle(style);

        for(int i = 0; i < courseApplyListOrderByGrade_idAndStudent_id.size(); i++) {
        	CourseApply courseApply = courseApplyListOrderByGrade_idAndStudent_id.get(i);

        	String teacher_name = getTeacherName(courseApply.getCourse_teacher(), teacherList);

        	String class_name = getClassName(courseApply.getCourse_class());

    		row = sheet.createRow(i + 1);
    		cell = row.createCell(0);
            cell.setCellValue(courseApply.getStudent_name());
            cell.setCellStyle(style);
            cell = row.createCell(1);
            cell.setCellValue(courseApply.getGrade_name());
            cell.setCellStyle(style);
            cell = row.createCell(2);
            cell.setCellValue(courseApply.getStudent_number());
            cell.setCellStyle(style);
            cell = row.createCell(3);
            cell.setCellValue(courseApply.getStudent_sex());
            cell.setCellStyle(style);
            cell = row.createCell(4);
            cell.setCellValue(courseApply.getCourse_name());
            cell.setCellStyle(style);
            cell = row.createCell(5);
            cell.setCellValue(class_name);
            cell.setCellStyle(style);
            cell = row.createCell(6);
            cell.setCellValue(teacher_name);
            cell.setCellStyle(style);
            cell = row.createCell(7);
            cell.setCellValue(courseApply.getCourse_address());
            cell.setCellStyle(style);
        }

        String course_id = "";
        int index = 0;
        for(int i = 0; i < courseApplyListOrderByCourse_id.size(); i++) {
        	CourseApply courseApply = courseApplyListOrderByCourse_id.get(i);

        	if(! course_id.equals(courseApply.getCourse_id())) {
        		course_id = courseApply.getCourse_id();

        		index = 0;

        		sheet = wb.createSheet(i + 1 + "、" + courseApply.getCourse_name());

        		row = sheet.createRow(0);
        		cell = row.createCell(0);
                cell.setCellValue("姓名");
                cell.setCellStyle(style);
                cell = row.createCell(1);
                cell.setCellValue("班级");
                cell.setCellStyle(style);
                cell = row.createCell(2);
                cell.setCellValue("学号");
                cell.setCellStyle(style);
                cell = row.createCell(3);
                cell.setCellValue("性别");
                cell.setCellStyle(style);
                cell = row.createCell(4);
                cell.setCellValue("课程名称");
                cell.setCellStyle(style);
                cell = row.createCell(5);
                cell.setCellValue("上课时间");
                cell.setCellStyle(style);
                cell = row.createCell(6);
                cell.setCellValue("上课老师");
                cell.setCellStyle(style);
                cell = row.createCell(7);
                cell.setCellValue("上课地点");
                cell.setCellStyle(style);
        	}

        	String teacher_name = getTeacherName(courseApply.getCourse_teacher(), teacherList);

        	String class_name = getClassName(courseApply.getCourse_class());

    		row = sheet.createRow(index + 1);
    		cell = row.createCell(0);
            cell.setCellValue(courseApply.getStudent_name());
            cell.setCellStyle(style);
            cell = row.createCell(1);
            cell.setCellValue(courseApply.getGrade_name());
            cell.setCellStyle(style);
            cell = row.createCell(2);
            cell.setCellValue(courseApply.getStudent_number());
            cell.setCellStyle(style);
            cell = row.createCell(3);
            cell.setCellValue(courseApply.getStudent_sex());
            cell.setCellStyle(style);
            cell = row.createCell(4);
            cell.setCellValue(courseApply.getCourse_name());
            cell.setCellStyle(style);
            cell = row.createCell(5);
            cell.setCellValue(class_name);
            cell.setCellStyle(style);
            cell = row.createCell(6);
            cell.setCellValue(teacher_name);
            cell.setCellStyle(style);
            cell = row.createCell(7);
            cell.setCellValue(courseApply.getCourse_address());
            cell.setCellStyle(style);

            index++;
        }

        MyPoiRender myPoiRender = new MyPoiRender(wb, "选课信息");

        return myPoiRender;
	}

	private String getTeacherName(JSONArray teacherArray, List<Teacher> teacherList) {
		String teacher_name = "";

        for(int j = 0; j < teacherList.size(); j++) {
        	Teacher teacher = teacherList.get(j);

        	for(int k = 0; k < teacherArray.size(); k++) {
        		String teacher_id = teacherArray.getString(k);

        		if(teacher_id.equals(teacher.getTeacher_id())) {
        			teacher_name += "," + teacher.getTeacher_name();
        		}
        	}
        }
        if(teacher_name != "") {
        	teacher_name = teacher_name.substring(1);
        }

        return teacher_name;
	}

	private String getClassName(String course_class) {
		String class_name = "";

        if(course_class.equals("17")) {
        	class_name = "星期一第七节";
        } else if(course_class.equals("27")) {
        	class_name = "星期二第七节";
        } else if(course_class.equals("28")) {
        	class_name = "星期二第八节";
        } else if(course_class.equals("47")) {
        	class_name = "星期四第七节";
        } else if(course_class.equals("48")) {
        	class_name = "星期四第八节";
        } else if(course_class.equals("56")) {
        	class_name = "星期五第六节";
        }

        return class_name;
	}

	public List<CourseApply> my(JSONObject jsonObject) {
		String request_user_id = jsonObject.getString(Const.KEY_REQUEST_USER_ID);

		return courseApplyService.listMyCourseApply(request_user_id);
	}

}
