package org.javabase.apps.excel;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.krawler.utils.json.base.JSONArray;
import com.krawler.utils.json.base.JSONException;
import com.krawler.utils.json.base.JSONObject;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.javabase.apps.entity.Batch;
import org.javabase.apps.entity.Course;
import org.javabase.apps.entity.Semester;
import org.javabase.apps.entity.Student;
import org.javabase.apps.entity.StudentAttendance;
import org.javabase.apps.service.StudentService;

/**
 *
 * @author raj.shah
 */
public class ExcelReadHandler {

    private StudentService studentService;

    private int totalDaysInMonth = 0;//need to set via totalcolun
    private int totalHolidaysInMonth = 0;
    private int totalPresent = 0;
    private int totalAbsence = 0;
    private int totalLeavesTakenInMonth = 0;

    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    private Sheet getExcelWorkSheet(String fileName) throws IOException {
        FileInputStream excelFile = new FileInputStream(new File(fileName));
        Workbook workbook = new XSSFWorkbook(excelFile);
        Sheet datatypeSheet = workbook.getSheetAt(0);
        return datatypeSheet;
    }

    public List<StudentAttendance> handleStudentAttendanceFile(String fileName, String filePath, Course course, Batch batch, Semester semester, String month) {
        List<StudentAttendance> studAttendance = new ArrayList<>();
        try {
            Sheet datatypeSheet = this.getExcelWorkSheet(filePath);//"C:\\Users\\raj.shah\\Downloads\\Temp.xlsx"
            Iterator<Row> iterator = datatypeSheet.iterator();

            while (iterator.hasNext()) {
                Row currentRow = iterator.next();
                int currentRowIndex = currentRow.getRowNum();
//                No.	Student Name	Enrollment Number	1	2	3	4	5	6	7	8	9	10	11	12	13	14	15	16	17	18	19	20	21	22	23	24	25	26	27	28	29	30	31

                StudentAttendance sa = new StudentAttendance();
                totalDaysInMonth = 0;//need to set via totalcolun
                totalHolidaysInMonth = 0;
                totalPresent = 0;
                totalAbsence = 0;
                totalLeavesTakenInMonth = 0;

                Student stud = new Student();
                totalDaysInMonth = (datatypeSheet.getRow(0).getLastCellNum()) - 3;

                Iterator<Cell> cellIterator = currentRow.iterator();
                while (cellIterator.hasNext()) {
                    Cell currentCell = cellIterator.next();
                    int currentColumnIndex = currentCell.getColumnIndex();

                    if (currentRowIndex > 0) {
                        //Skip No. and Student Name column
                        if ((currentColumnIndex == 0 || currentColumnIndex == 1)) {
                            continue;
                        } else if (currentColumnIndex == 2) {//from 2nd row and enrollment number column

                            stud = studentService.getStudentByEnrollmentNumber(currentCell.getStringCellValue());

                        } else if (currentColumnIndex == 3) {
                            String val = currentCell.getStringCellValue();
                            sa.setA1(getFinalStringValueForAttendance(val));
                            updateDaysCounterForAttendance(val);
                        } else if (currentColumnIndex == 4) {
                            String val = currentCell.getStringCellValue();
                            sa.setA2(getFinalStringValueForAttendance(val));
                            updateDaysCounterForAttendance(val);
                        } else if (currentColumnIndex == 5) {
                            String val = currentCell.getStringCellValue();
                            sa.setA3(getFinalStringValueForAttendance(val));
                            updateDaysCounterForAttendance(val);
                        } else if (currentColumnIndex == 6) {
                            String val = currentCell.getStringCellValue();
                            sa.setA4(getFinalStringValueForAttendance(val));
                            updateDaysCounterForAttendance(val);
                        } else if (currentColumnIndex == 7) {
                            String val = currentCell.getStringCellValue();
                            sa.setA5(getFinalStringValueForAttendance(val));
                            updateDaysCounterForAttendance(val);
                        } else if (currentColumnIndex == 8) {
                            String val = currentCell.getStringCellValue();
                            sa.setA6(getFinalStringValueForAttendance(val));
                            updateDaysCounterForAttendance(val);
                        } else if (currentColumnIndex == 9) {
                            String val = currentCell.getStringCellValue();
                            sa.setA7(getFinalStringValueForAttendance(val));
                            updateDaysCounterForAttendance(val);
                        } else if (currentColumnIndex == 10) {
                            String val = currentCell.getStringCellValue();
                            sa.setA8(getFinalStringValueForAttendance(val));
                            updateDaysCounterForAttendance(val);
                        } else if (currentColumnIndex == 11) {
                            String val = currentCell.getStringCellValue();
                            sa.setA9(getFinalStringValueForAttendance(val));
                            updateDaysCounterForAttendance(val);
                        } else if (currentColumnIndex == 12) {
                            String val = currentCell.getStringCellValue();
                            sa.setA10(getFinalStringValueForAttendance(val));
                            updateDaysCounterForAttendance(val);
                        } else if (currentColumnIndex == 13) {
                            String val = currentCell.getStringCellValue();
                            sa.setA11(getFinalStringValueForAttendance(val));
                            updateDaysCounterForAttendance(val);
                        } else if (currentColumnIndex == 14) {
                            String val = currentCell.getStringCellValue();
                            sa.setA12(getFinalStringValueForAttendance(val));
                            updateDaysCounterForAttendance(val);
                        } else if (currentColumnIndex == 15) {
                            String val = currentCell.getStringCellValue();
                            sa.setA13(getFinalStringValueForAttendance(val));
                            updateDaysCounterForAttendance(val);
                        } else if (currentColumnIndex == 16) {
                            String val = currentCell.getStringCellValue();
                            sa.setA14(getFinalStringValueForAttendance(val));
                            updateDaysCounterForAttendance(val);
                        } else if (currentColumnIndex == 17) {
                            String val = currentCell.getStringCellValue();
                            sa.setA15(getFinalStringValueForAttendance(val));
                            updateDaysCounterForAttendance(val);
                        } else if (currentColumnIndex == 18) {
                            String val = currentCell.getStringCellValue();
                            sa.setA16(getFinalStringValueForAttendance(val));
                            updateDaysCounterForAttendance(val);
                        } else if (currentColumnIndex == 19) {
                            String val = currentCell.getStringCellValue();
                            sa.setA17(getFinalStringValueForAttendance(val));
                            updateDaysCounterForAttendance(val);
                        } else if (currentColumnIndex == 20) {
                            String val = currentCell.getStringCellValue();
                            sa.setA18(getFinalStringValueForAttendance(val));
                            updateDaysCounterForAttendance(val);
                        } else if (currentColumnIndex == 21) {
                            String val = currentCell.getStringCellValue();
                            sa.setA19(getFinalStringValueForAttendance(val));
                            updateDaysCounterForAttendance(val);
                        } else if (currentColumnIndex == 22) {
                            String val = currentCell.getStringCellValue();
                            sa.setA20(getFinalStringValueForAttendance(val));
                            updateDaysCounterForAttendance(val);
                        } else if (currentColumnIndex == 23) {
                            String val = currentCell.getStringCellValue();
                            sa.setA21(getFinalStringValueForAttendance(val));
                            updateDaysCounterForAttendance(val);
                        } else if (currentColumnIndex == 24) {
                            String val = currentCell.getStringCellValue();
                            sa.setA22(getFinalStringValueForAttendance(val));
                            updateDaysCounterForAttendance(val);
                        } else if (currentColumnIndex == 25) {
                            String val = currentCell.getStringCellValue();
                            sa.setA23(getFinalStringValueForAttendance(val));
                            updateDaysCounterForAttendance(val);
                        } else if (currentColumnIndex == 26) {
                            String val = currentCell.getStringCellValue();
                            sa.setA24(getFinalStringValueForAttendance(val));
                            updateDaysCounterForAttendance(val);
                        } else if (currentColumnIndex == 27) {
                            String val = currentCell.getStringCellValue();
                            sa.setA25(getFinalStringValueForAttendance(val));
                            updateDaysCounterForAttendance(val);
                        } else if (currentColumnIndex == 28) {
                            String val = currentCell.getStringCellValue();
                            sa.setA26(getFinalStringValueForAttendance(val));
                            updateDaysCounterForAttendance(val);
                        } else if (currentColumnIndex == 29) {
                            String val = currentCell.getStringCellValue();
                            sa.setA27(getFinalStringValueForAttendance(val));
                            updateDaysCounterForAttendance(val);
                        } else if (currentColumnIndex == 30) {
                            String val = currentCell.getStringCellValue();
                            sa.setA28(getFinalStringValueForAttendance(val));
                            updateDaysCounterForAttendance(val);
                        } else if (currentColumnIndex == 31) {
                            String val = currentCell.getStringCellValue();
                            sa.setA29(getFinalStringValueForAttendance(val));
                            updateDaysCounterForAttendance(val);
                        } else if (currentColumnIndex == 32) {
                            String val = currentCell.getStringCellValue();
                            sa.setA30(getFinalStringValueForAttendance(val));
                            updateDaysCounterForAttendance(val);
                        } else if (currentColumnIndex == 33) {
                            String val = currentCell.getStringCellValue();
                            sa.setA31(getFinalStringValueForAttendance(val));
                            updateDaysCounterForAttendance(val);
                        }
                    }
                }

                if (currentRowIndex > 0) {
                    sa.setTotalDaysInMonth(totalDaysInMonth);
                    sa.setTotalHolidaysInMonth(totalHolidaysInMonth);
                    sa.setTotalWorkingDaysInMonth(totalDaysInMonth - totalHolidaysInMonth);
                    sa.setTotalAbsentCount(totalAbsence);
                    sa.setTotalPresentCount(totalPresent);
                    sa.setTotalLeaveCount(totalLeavesTakenInMonth);

                    if (stud != null && stud.getId() != null) {
                        sa.setStudentId(stud);
                        sa.setBatchId(batch);
                        sa.setCourseId(course);
                        sa.setSemesterId(semester);
                        sa.setMonthName(month);
                    }

                    studAttendance.add(sa);
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        }
        return studAttendance;
    }

    private void updateDaysCounterForAttendance(String currentValue) {
        if (currentValue != null) {
            if (currentValue.equalsIgnoreCase("P")) {
                totalPresent = totalPresent + 1;
            } else if (currentValue.equalsIgnoreCase("A")) {
                totalAbsence = totalAbsence + 1;
            } else if (currentValue.equalsIgnoreCase("L")) {
                totalLeavesTakenInMonth = totalLeavesTakenInMonth + 1;
            } else if (currentValue.equalsIgnoreCase("H") || "".equals(currentValue)) {
                totalHolidaysInMonth = totalHolidaysInMonth + 1;
            }
        }
    }

    private String getFinalStringValueForAttendance(String cellData) {
        List<String> list = Arrays.asList("H", "h", "P", "p", "A", "a", "l", "L");
        if ("".equals(cellData) || cellData == null) {
            return "H";
        } else if (list.contains(cellData)) {
            return cellData.toUpperCase();
        } else {
            return "H";
        }
    }

    public Map<String, String> handleStudentResultFile(String fileName, String filePath) {
        Map<String, String> finalResultMap = new HashMap<>();
        try {
            Sheet sheet = this.getExcelWorkSheet(filePath);//"C:\\Users\\raj.shah\\Downloads\\Temp.xlsx"
            Iterator<Row> iterator = sheet.iterator();

            List<SubjectArea> subjectArea = new ArrayList<>();
            int finalGradeStartIndex = 0;
            int finalGradeLastIndex = 0;
//            Map<String, List<SubjectArea>> finalResultMap = new HashMap<>();

            while (iterator.hasNext()) {
                Row currentRow = iterator.next();
//                int currentRowIndex = currentRow.getRowNum();
                Iterator<Cell> cellIterator = currentRow.iterator();

                List<SubjectArea> subA = null;
                String studentEnrollmentNo = null;
                outer:
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();

                    int currentRowNo = cell.getRowIndex();
                    int currentColNo = cell.getColumnIndex();

                    //will iterate over the Merged cells
                    for (int i = 0; i < sheet.getNumMergedRegions(); i++) {
                        CellRangeAddress region = sheet.getMergedRegion(i); //Region of merged cells

                        int colIndex = region.getFirstColumn(); //number of columns merged
                        int lastColIndex = region.getLastColumn(); //number of last columns merged
                        int rowNum = region.getFirstRow();      //number of rows merged
                        //check first cell of the region
                        if (rowNum == currentRowNo && colIndex == currentColNo) {
//                            System.out.println("Row  : " + currentRowNo + "   Column : " + currentColNo);
//                            System.out.println(sheet.getRow(rowNum).getCell(colIndex).getStringCellValue());
//                            System.out.println("--------------------------------------------------------------");

                            String cellValue = sheet.getRow(rowNum).getCell(colIndex).getStringCellValue().trim();

                            if (rowNum == 0 && colIndex > 1) {

                                subjectArea.add(new SubjectArea(sheet.getRow(rowNum).getCell(colIndex).getStringCellValue(), colIndex, lastColIndex));

                                if (cellValue.equalsIgnoreCase("FINAL GRADE") && finalGradeStartIndex == 0 && finalGradeLastIndex == 0) {
                                    finalGradeStartIndex = colIndex;
                                    finalGradeLastIndex = lastColIndex;

                                    FinalGrade fg = new FinalGrade();
                                    fg.setStartColumnIndex(colIndex);
                                    fg.setEndColumnIndex(lastColIndex);
                                    Optional<SubjectArea> sa = findSubjectArea(subjectArea, colIndex, lastColIndex);
                                    if (sa.isPresent()) {
                                        sa.get().setFinalGrade(fg);
                                    }
                                }
                            }

                            if (rowNum == 1 && colIndex > 1) {
                                if (cellValue.equalsIgnoreCase("PRACTICAL")) {
                                    Practical p = new Practical();
                                    p.setStartColumnIndex(colIndex);
                                    p.setEndColumnIndex(lastColIndex);

                                    Optional<SubjectArea> sa = findSubjectArea(subjectArea, colIndex, lastColIndex);
                                    if (sa.isPresent()) {
                                        sa.get().setPractical(p);
                                    }
                                } else if (cellValue.equalsIgnoreCase("THEORY")) {
                                    Theory t = new Theory();
                                    t.setStartColumnIndex(colIndex);
                                    t.setEndColumnIndex(lastColIndex);

                                    Optional<SubjectArea> sa = findSubjectArea(subjectArea, colIndex, lastColIndex);
                                    if (sa.isPresent()) {
                                        sa.get().setTheory(t);
                                    }
                                }
                            }

                            if (rowNum == 2 && colIndex > 1) {
                                Optional<SubjectArea> sa = findSubjectArea(subjectArea, colIndex, lastColIndex);
                                if (sa.isPresent()) {
                                    if (isCurrentCellOfTheoryPart(sa, colIndex, lastColIndex)) {
                                        sa.get().getTheory().setExamDate(cellValue);
                                    } else if (isCurrentCellOfPracticalPart(sa, colIndex, lastColIndex)) {
                                        sa.get().getPractical().setExamDate(cellValue);
                                    }
                                }
                            }

                            continue outer;
                        }
                    }

                    /**
                     * *************************************************************************************
                     */
                    if (currentRowNo == 3 && currentColNo > 1) {
                        Optional<SubjectArea> sa = findSubjectArea(subjectArea, currentColNo, currentColNo);

                        if (sa.isPresent()) {
                            String cellValue = cell.getStringCellValue();

                            if (isCurrentCellOfTheoryPart(sa, currentColNo, currentColNo)) {

                                Theory th = sa.get().getTheory();

                                if (isCellTW(cellValue)) {
                                    double d = sheet.getRow(currentRowNo + 1).getCell(currentColNo).getNumericCellValue();
                                    th.setOfTW((int) d);
                                } else if (isCellTH(cellValue)) {
                                    double d = sheet.getRow(currentRowNo + 1).getCell(currentColNo).getNumericCellValue();
                                    th.setOfTH((int) d);
                                } else if (isCellTotal(cellValue)) {
                                    double d = sheet.getRow(currentRowNo + 1).getCell(currentColNo).getNumericCellValue();
                                    th.setOfTotal((int) d);
                                }
                            } else if (isCurrentCellOfPracticalPart(sa, currentColNo, currentColNo)) {

                                Practical p = sa.get().getPractical();

                                if (isCellPR(cellValue)) {
                                    double d = sheet.getRow(currentRowNo + 1).getCell(currentColNo).getNumericCellValue();
                                    p.setOfPR((int) d);
                                } else if (isCellTotal(cellValue)) {
                                    double d = sheet.getRow(currentRowNo + 1).getCell(currentColNo).getNumericCellValue();
                                    p.setOfTotal((int) d);
                                }
                            } else if (isCurrentCellOfFinalGradePart(sa, currentColNo, currentColNo)) {

                                FinalGrade f = sa.get().getFinalGrade();

                                if (isCellTotal(cellValue)) {
                                    double d = sheet.getRow(currentRowNo + 1).getCell(currentColNo).getNumericCellValue();
                                    f.setOfTotal((int) d);
                                }
                            }
                        }
                    }

                    /**
                     * *************************************************************************************
                     */
                    if (currentRowNo >= 5) {

//                        if (currentColNo >= 49 && currentColNo <= 51) {
//                            System.out.println("This is Final Grade Part");
//                        }
                        subA = subjectArea;

                        /* Here searched for subA instead of subjectArea*/
                        Optional<SubjectArea> sa = findSubjectArea(subA, currentColNo, currentColNo);

                        if (currentColNo == 1 && cell != null && cell.getCellTypeEnum() != CellType.BLANK && studentEnrollmentNo == null) {
                            studentEnrollmentNo = getCellStringConvertedValue(cell);
                        } else if (currentColNo > 1 && cell != null && cell.getCellTypeEnum() != CellType.BLANK) {

                            if (sa.isPresent() && sa.get() != null) {
                                String row3CellStringVal = sheet.getRow(3).getCell(currentColNo).getStringCellValue().trim();

                                if (isCurrentCellOfTheoryPart(sa, currentColNo, currentColNo)) {
                                    if (isCellTW(row3CellStringVal)) {
                                        sa.get().getTheory().setTW((int) cell.getNumericCellValue());
                                    } else if (isCellTH(row3CellStringVal)) {
                                        sa.get().getTheory().setTH(getCellStringConvertedValue(cell));
                                    } else if (isCellTotal(row3CellStringVal)) {
                                        sa.get().getTheory().setTotal((int) cell.getNumericCellValue());
                                    } else if (isCellPercentage(row3CellStringVal)) {
                                        sa.get().getTheory().setPercentage((int) Math.round((cell.getNumericCellValue())));
                                    } else if (isCellGrade(row3CellStringVal)) {
                                        sa.get().getTheory().setGrade(getCellStringConvertedValue(cell));
                                    }
                                } else if (isCurrentCellOfPracticalPart(sa, currentColNo, currentColNo)) {
                                    if (isCellPR(row3CellStringVal)) {
                                        sa.get().getPractical().setPR(getCellStringConvertedValue(cell));
                                    } else if (isCellTotal(row3CellStringVal)) {
                                        sa.get().getPractical().setTotal((int) cell.getNumericCellValue());
                                    } else if (isCellPercentage(row3CellStringVal)) {
                                        sa.get().getPractical().setPercentage((int) Math.round((cell.getNumericCellValue())));
                                    } else if (isCellGrade(row3CellStringVal)) {
                                        sa.get().getPractical().setGrade(getCellStringConvertedValue(cell));
                                    }
                                } else if (isCurrentCellOfFinalGradePart(sa, currentColNo, currentColNo)) {
                                    if (isCellTotal(row3CellStringVal)) {
                                        sa.get().getFinalGrade().setTotal((int) cell.getNumericCellValue());
                                    } else if (isCellPercentage(row3CellStringVal)) {
                                        sa.get().getFinalGrade().setPercentage((int) Math.round((cell.getNumericCellValue())));
                                    } else if (isCellGrade(row3CellStringVal)) {
                                        sa.get().getFinalGrade().setGrade(getCellStringConvertedValue(cell));
                                    }
                                }
                            }

                        }
                    }

                    //the data in merge cells is always present on the first cell. All other cells(in merged region) are considered blank
                    /*            System.out.println("Row No : "+cell.getRowIndex() +"   Column No: "+cell.getColumnIndex());
                    System.out.println("--------------------------------------------------------------");
                    if (cell.getCellTypeEnum() == CellType.BLANK || cell == null) {
                    }else if (cell.getCellTypeEnum() == CellType.STRING){
                        System.out.println(cell.getStringCellValue());
                    }else if (cell.getCellTypeEnum() == CellType.NUMERIC){
                        System.out.println(cell.getNumericCellValue());
                    }
                     */
                }
                if (subA != null && studentEnrollmentNo != null) {
                    ObjectMapper mapper = new ObjectMapper();
                    String newJsonData = mapper.writeValueAsString(subA);
//                    System.out.println("**************** SA : " + subA.get(7).finalGrade.grade);
                    finalResultMap.put(studentEnrollmentNo, newJsonData);
                }
//                System.out.println("sa : " + subA);
            }
//            System.out.println("Total Student Result : " + finalResultMap.size());
            finalResultMap = makeMeaningfulResultJson(finalResultMap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        }
        return finalResultMap;
    }

    private Map<String, String> makeMeaningfulResultJson(Map<String, String> finalResultMap) throws IOException, JSONException {
        Map<String, String> newJsonMap = new HashMap();
        if (finalResultMap != null && finalResultMap.size() > 0) {
            for (Map.Entry<String, String> entry : finalResultMap.entrySet()) {
                String enrollmentNo = entry.getKey();
                String oldVal = entry.getValue();
                if (oldVal != null && !oldVal.isEmpty()) {
                    JSONArray ja = new JSONArray(oldVal);
                    JSONArray newJArr= new JSONArray();

                    for (int i = 0; i < ja.length(); i++) {
                        JSONObject newObj = new JSONObject();
                        JSONObject jobj = ja.getJSONObject(i);
                        if (jobj != null) {
                            if (jobj.has("subjectName")) {
                                String subject = jobj.getString("subjectName");
                                if (subject.equalsIgnoreCase("FINAL GRADE")) {
                                    if (jobj.has("finalGrade") && jobj.get("finalGrade") != null && !"null".equalsIgnoreCase(jobj.optString("finalGrade"))) {
                                        JSONObject fgObject = jobj.getJSONObject("finalGrade");
                                        if (fgObject != null) {
                                            JSONObject newFinalGradeObj = new JSONObject();
                                            if (fgObject.has("ofTotal")) {
                                                newFinalGradeObj.put("ofTotal", fgObject.get("ofTotal"));
                                            }
                                            if (fgObject.has("total")) {
                                                newFinalGradeObj.put("total", fgObject.get("total"));
                                            }
                                            if (fgObject.has("percentage")) {
                                                newFinalGradeObj.put("percentage", fgObject.get("percentage"));
                                            }
                                            if (fgObject.has("grade")) {
                                                newFinalGradeObj.put("grade", fgObject.get("grade"));
                                            }
                                            newObj.put("finalGrade", newFinalGradeObj);
                                        }
                                    }
                                } else {
                                    newObj.put("subject", subject);
                                }
                            }
                            if (jobj.has("theory") && jobj.get("theory") != null && !"null".equalsIgnoreCase(jobj.optString("theory"))) {
                                JSONObject thObject = jobj.getJSONObject("theory");
                                if (thObject != null) {
                                    JSONObject newthObj = new JSONObject();
                                    if (thObject.has("examDate")) {
                                        newthObj.put("examDate", thObject.get("examDate"));
                                    }
                                    if (thObject.has("ofTW")) {
                                        newthObj.put("ofTW", thObject.get("ofTW"));
                                    }
                                    if (thObject.has("ofTH")) {
                                        newthObj.put("ofTH", thObject.get("ofTH"));
                                    }
                                    if (thObject.has("ofTotal")) {
                                        newthObj.put("ofTotal", thObject.get("ofTotal"));
                                    }
                                    if(thObject.has("total")) {
                                        newthObj.put("total", thObject.get("total"));
                                    }
                                    if (thObject.has("percentage")) {
                                        newthObj.put("percentage", thObject.get("percentage"));
                                    }
                                    if (thObject.has("grade")) {
                                        newthObj.put("grade", thObject.get("grade"));
                                    }
                                    if (thObject.has("tw")) {
                                        newthObj.put("tw", thObject.get("tw"));
                                    }
                                    if (thObject.has("th")) {
                                        newthObj.put("th", thObject.get("th"));
                                    }
                                    newObj.put("theory", newthObj);
                                }
                            }
                            if (jobj.has("practical") && jobj.get("practical") != null && !"null".equalsIgnoreCase(jobj.optString("practical"))) {
                                JSONObject prObject = jobj.getJSONObject("practical");
                                if (prObject != null) {
                                    JSONObject newprObj = new JSONObject();
                                    if (prObject.has("examDate")) {
                                        newprObj.put("examDate", prObject.get("examDate"));
                                    }
                                    if (prObject.has("ofPR")) {
                                        newprObj.put("ofPR", prObject.get("ofPR"));
                                    }
                                    if (prObject.has("ofTotal")) {
                                        newprObj.put("ofTotal", prObject.get("ofTotal"));
                                    }
                                    if (prObject.has("total")) {
                                        newprObj.put("total", prObject.get("total"));
                                    }
                                    if (prObject.has("percentage")) {
                                        newprObj.put("percentage", prObject.get("percentage"));
                                    }
                                    if (prObject.has("grade")) {
                                        newprObj.put("grade", prObject.get("grade"));
                                    }
                                    if (prObject.has("pr")) {
                                        newprObj.put("pr", prObject.get("pr"));
                                    }
                                    newObj.put("practical", newprObj);
                                }
                            }
                        }
                        newJArr.put(newObj);
                    }
                    if (newJArr != null && newJArr.length() > 0) {
                        newJsonMap.put(enrollmentNo, newJArr.toString());
                    }
                }
            }
        }
        return newJsonMap;
    }

    private Optional<SubjectArea> findSubjectArea(List<SubjectArea> subjectArea, int startColNo, int endColNo) {
        return subjectArea.stream().filter(s -> (startColNo >= s.getStartColumnIndex()
                && endColNo <= s.getEndColumnIndex())).findFirst();
    }

    private boolean isCurrentCellOfTheoryPart(Optional<SubjectArea> sa, int startColNo, int endColNo) {
        return sa.get().getTheory() != null && startColNo >= sa.get().getTheory().getStartColumnIndex()
                && endColNo <= sa.get().getTheory().getEndColumnIndex();
    }

    private boolean isCurrentCellOfPracticalPart(Optional<SubjectArea> sa, int startColNo, int endColNo) {
        return sa.get().getPractical() != null && startColNo >= sa.get().getPractical().getStartColumnIndex()
                && endColNo <= sa.get().getPractical().getEndColumnIndex();
    }

    private boolean isCurrentCellOfFinalGradePart(Optional<SubjectArea> sa, int startColNo, int endColNo) {
        return sa.get().getFinalGrade() != null && startColNo >= sa.get().getFinalGrade().getStartColumnIndex()
                && endColNo <= sa.get().getFinalGrade().getEndColumnIndex();
    }

    private boolean isCellTW(String cellValue) {
        return "TW".equalsIgnoreCase(cellValue) || "TW.".equalsIgnoreCase(cellValue);
    }

    private boolean isCellTH(String cellValue) {
        return "TH".equalsIgnoreCase(cellValue) || "TH.".equalsIgnoreCase(cellValue);
    }

    private boolean isCellPR(String cellValue) {
        return "PR".equalsIgnoreCase(cellValue) || "PR.".equalsIgnoreCase(cellValue);
    }

    private boolean isCellTotal(String cellValue) {
        return "Total".equalsIgnoreCase(cellValue);
    }

    private boolean isCellGrade(String cellValue) {
        return "GRD".equalsIgnoreCase(cellValue) || "GR".equalsIgnoreCase(cellValue) || "GR.".equalsIgnoreCase(cellValue);
    }

    private boolean isCellPercentage(String cellValue) {
        return "%".equalsIgnoreCase(cellValue);
    }

    private String getCellStringConvertedValue(Cell cell) {
        String cellVal = null;
        if (null != cell.getCellTypeEnum()) {
            switch (cell.getCellTypeEnum()) {
                case STRING:
                    cellVal = cell.getStringCellValue().trim();
                    break;
                case NUMERIC:
                    cellVal = String.valueOf(cell.getNumericCellValue());
                    cellVal = cellVal.endsWith(".0") ? cellVal.substring(0, cellVal.length() - 2) : cellVal.trim();
                    break;
                case FORMULA:
                    cellVal = cell.getRichStringCellValue().getString().trim();
                    break;
                default:
                    break;
            }
        }
        return cellVal;
    }

    public static boolean isCellEmpty(final XSSFCell cell) {
        if (cell == null) { // use row.getCell(x, Row.CREATE_NULL_AS_BLANK) to avoid null cells
            return true;
        }

        if (cell.getCellTypeEnum() == CellType.BLANK) {
            return true;
        }

        if (cell.getCellTypeEnum() == CellType.STRING && cell.getStringCellValue().trim().isEmpty()) {
            return true;
        }

        return false;
    }

    class SubjectArea implements Cloneable {

        private String subjectName;
        private int startColumnIndex;
        private int endColumnIndex;
        private Theory theory;
        private Practical practical;
        private FinalGrade finalGrade;

        public SubjectArea() {

        }

        public SubjectArea(String subjectName, int startColumnIndex, int endColumnIndex) {
            this.subjectName = subjectName;
            this.startColumnIndex = startColumnIndex;
            this.endColumnIndex = endColumnIndex;
        }

        public String getSubjectName() {
            return subjectName;
        }

        public void setSubjectName(String subjectName) {
            this.subjectName = subjectName;
        }

        public int getStartColumnIndex() {
            return startColumnIndex;
        }

        public void setStartColumnIndex(int startColumnIndex) {
            this.startColumnIndex = startColumnIndex;
        }

        public int getEndColumnIndex() {
            return endColumnIndex;
        }

        public void setEndColumnIndex(int endColumnIndex) {
            this.endColumnIndex = endColumnIndex;
        }

        public Theory getTheory() {
            return theory;
        }

        public void setTheory(Theory theory) {
            this.theory = theory;
        }

        public Practical getPractical() {
            return practical;
        }

        public void setPractical(Practical practical) {
            this.practical = practical;
        }

        public FinalGrade getFinalGrade() {
            return finalGrade;
        }

        public void setFinalGrade(FinalGrade finalGrade) {
            this.finalGrade = finalGrade;
        }

        public Object clone() throws CloneNotSupportedException {
            return super.clone();
        }

//        @Override
//        public String toString() {
//            return getSubjectName() + " ---> " + getStartColumnIndex() + " ---> " + getEndColumnIndex() + " ---> Theory : "
//                    + (getTheory() != null ? getTheory().toString() : null) + " ---> Practical : " + (getPractical() != null ? getPractical().toString() : null);
//        }
    }

    class Theory {

        private String examDate;
        private int TW;
        private int ofTW;
        private String TH;
        private int ofTH;
        private int total;
        private int ofTotal;
        private int percentage;
        private String grade;
        private int startColumnIndex;
        private int endColumnIndex;

        public String getExamDate() {
            return examDate;
        }

        public void setExamDate(String examDate) {
            this.examDate = examDate;
        }

        public int getTW() {
            return TW;
        }

        public void setTW(int TW) {
            this.TW = TW;
        }

        public int getOfTW() {
            return ofTW;
        }

        public void setOfTW(int ofTW) {
            this.ofTW = ofTW;
        }

        public String getTH() {
            return TH;
        }

        public void setTH(String TH) {
            this.TH = TH;
        }

        public int getOfTH() {
            return ofTH;
        }

        public void setOfTH(int ofTH) {
            this.ofTH = ofTH;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getOfTotal() {
            return ofTotal;
        }

        public void setOfTotal(int ofTotal) {
            this.ofTotal = ofTotal;
        }

        public int getPercentage() {
            return percentage;
        }

        public void setPercentage(int percentage) {
            this.percentage = percentage;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public int getStartColumnIndex() {
            return startColumnIndex;
        }

        public void setStartColumnIndex(int startColumnIndex) {
            this.startColumnIndex = startColumnIndex;
        }

        public int getEndColumnIndex() {
            return endColumnIndex;
        }

        public void setEndColumnIndex(int endColumnIndex) {
            this.endColumnIndex = endColumnIndex;
        }

//        @Override
//        public String toString() {
//            return getStartColumnIndex() + " ---> " + getEndColumnIndex() + " ---> ExamDate : " + getExamDate()
//                    + " ---> ofTW : " + getOfTW() + " ---> ofTH : " + getOfTH() + " ---> ofTotal : " + getOfTotal();
//        }
    }

    class Practical {

        private String examDate;
        private String PR;
        private int ofPR;
        private int total;
        private int ofTotal;
        private int percentage;
        private String grade;
        private int startColumnIndex;
        private int endColumnIndex;

        public String getExamDate() {
            return examDate;
        }

        public void setExamDate(String examDate) {
            this.examDate = examDate;
        }

        public String getPR() {
            return PR;
        }

        public void setPR(String PR) {
            this.PR = PR;
        }

        public int getOfPR() {
            return ofPR;
        }

        public void setOfPR(int ofPR) {
            this.ofPR = ofPR;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getOfTotal() {
            return ofTotal;
        }

        public void setOfTotal(int ofTotal) {
            this.ofTotal = ofTotal;
        }

        public int getPercentage() {
            return percentage;
        }

        public void setPercentage(int percentage) {
            this.percentage = percentage;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public int getStartColumnIndex() {
            return startColumnIndex;
        }

        public void setStartColumnIndex(int startColumnIndex) {
            this.startColumnIndex = startColumnIndex;
        }

        public int getEndColumnIndex() {
            return endColumnIndex;
        }

        public void setEndColumnIndex(int endColumnIndex) {
            this.endColumnIndex = endColumnIndex;
        }

//        @Override
//        public String toString() {
//            return getStartColumnIndex() + " ---> " + getEndColumnIndex() + " ---> ExamDate : " + getExamDate()
//                    + " ---> ofPR : " + getOfPR() + " ---> ofTotal : " + getOfTotal();
//        }
    }

    class FinalGrade {

        private int total;
        private int ofTotal;
        private int percentage;
        private String grade;
        private int startColumnIndex;
        private int endColumnIndex;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getOfTotal() {
            return ofTotal;
        }

        public void setOfTotal(int ofTotal) {
            this.ofTotal = ofTotal;
        }

        public int getPercentage() {
            return percentage;
        }

        public void setPercentage(int percentage) {
            this.percentage = percentage;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public int getStartColumnIndex() {
            return startColumnIndex;
        }

        public void setStartColumnIndex(int startColumnIndex) {
            this.startColumnIndex = startColumnIndex;
        }

        public int getEndColumnIndex() {
            return endColumnIndex;
        }

        public void setEndColumnIndex(int endColumnIndex) {
            this.endColumnIndex = endColumnIndex;
        }
    }
}
