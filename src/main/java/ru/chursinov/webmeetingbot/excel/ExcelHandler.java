package ru.chursinov.webmeetingbot.excel;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import ru.chursinov.webmeetingbot.model.AnswersInfo;
import ru.chursinov.webmeetingbot.model.UserAnswersModel;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

@Slf4j
public class ExcelHandler {

    private static final int PIXEL_FACTOR = 37;
    private static final int numCol = 6;

    private final Workbook wb = new HSSFWorkbook();

    private List<UserAnswersModel> list;

    public void createExcel(File xlsFile) {
        try (FileOutputStream fileOut = new FileOutputStream(xlsFile)) {
            CreationHelper createHelper = this.wb.getCreationHelper();
            Sheet sheet = this.wb.createSheet("Ответы");
            System.setProperty("java.awt.headless", "true");
            Font titleFont = this.wb.createFont();
            titleFont.setBold(true);
            titleFont.setFontHeight((short) 400);
            CellStyle csTitle = setTableStyle();
            csTitle.setAlignment(HorizontalAlignment.CENTER);
            csTitle.setFont(titleFont);
            CellStyle csTable = setTableStyle();
            Font fontBold = this.wb.createFont();
            fontBold.setBold(true);
            CellStyle csTableTitle = setTableStyle();
            csTableTitle.setFont(fontBold);
            csTableTitle.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
            csTableTitle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
//        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, numCol - 1));
            Row row = sheet.createRow(0);
            row.createCell(0).setCellStyle(csTitle);
            row = sheet.createRow(0);
            row.createCell(0).setCellValue(createHelper.createRichTextString(TableTitle.DATE.getTitle()));
            row.createCell(1).setCellValue(createHelper.createRichTextString(TableTitle.USER_NAME.getTitle()));
            row.createCell(2).setCellValue(createHelper.createRichTextString(TableTitle.YESTERDAY.getTitle()));
            row.createCell(3).setCellValue(createHelper.createRichTextString(TableTitle.TODAY.getTitle()));
            row.createCell(4).setCellValue(createHelper.createRichTextString(TableTitle.IS_PROBLEM.getTitle()));
            row.createCell(5).setCellValue(createHelper.createRichTextString(TableTitle.PROBLEM_DETAILS.getTitle()));
            for (int i = 0; i < numCol; i++) {
                row.getCell(i).setCellStyle(csTableTitle);
            }
            int rowNum = 1;
            if (this.list != null && !this.list.isEmpty()) {
                int size = this.list.size();
                for (int j = 0; j < size; j++) {
                    row = sheet.createRow((short) rowNum + j);
                    UserAnswersModel answersInfo = this.list.get(j);
                    if (answersInfo != null) {
                        row.createCell(0).setCellValue(createHelper.createRichTextString(answersInfo.getDate()));
                        row.createCell(1).setCellValue(createHelper.createRichTextString(answersInfo.getUsername()));
                        row.createCell(2).setCellValue(createHelper.createRichTextString(answersInfo.getYesterday()));
                        row.createCell(3).setCellValue(createHelper.createRichTextString(answersInfo.getToday()));
                        row.createCell(4).setCellValue(createHelper.createRichTextString(answersInfo.getProblem()));
                        row.createCell(5).setCellValue(createHelper.createRichTextString(answersInfo.getProblem_details()));
                        for (int k = 0; k < numCol; k++)
                            row.getCell(k).setCellStyle(csTable);
                    }
                }
            }
            setSheetPreferences(sheet);
            this.wb.write(fileOut);
        } catch (Exception ex) {
            log.info("Some problem in createExcel file: " + ex);
        }
    }

    private CellStyle setTableStyle() {
        return setTableStyle(0);
    }

    private CellStyle setTableStyle(int align) {
        CellStyle cs = this.wb.createCellStyle();
        try {
            cs.setBorderBottom(BorderStyle.THIN);
            cs.setBottomBorderColor(IndexedColors.BLACK.getIndex());
            cs.setBorderLeft(BorderStyle.THIN);
            cs.setLeftBorderColor(IndexedColors.BLACK.getIndex());
            cs.setBorderRight(BorderStyle.THIN);
            cs.setRightBorderColor(IndexedColors.BLACK.getIndex());
            cs.setBorderTop(BorderStyle.THIN);
            cs.setTopBorderColor(IndexedColors.BLACK.getIndex());
            cs.setVerticalAlignment(VerticalAlignment.CENTER);
            cs.setWrapText(true);
            switch (align) {
                case 1:
                    return cs;
                case 2:
                    return cs;
            }
            return cs;
        } finally {
            Exception exception = null;
        }
    }

    private void setSheetPreferences(Sheet sheet) {
        sheet.setColumnWidth(0, PIXEL_FACTOR * 100);
        sheet.setColumnWidth(1, PIXEL_FACTOR * 120);
        sheet.setColumnWidth(2, PIXEL_FACTOR * 300);
        sheet.setColumnWidth(3, PIXEL_FACTOR * 300);
        sheet.setColumnWidth(4, PIXEL_FACTOR * 100);
        sheet.setColumnWidth(5, PIXEL_FACTOR * 300);
        sheet.setMargin((short) 0, 0.25D);
        sheet.setMargin((short) 1, 0.25D);
        sheet.setMargin((short) 2, 0.5D);
        sheet.setMargin((short) 3, 0.5D);
        HSSFPrintSetup ps = (HSSFPrintSetup) sheet.getPrintSetup();
        ps.setHeaderMargin(0.25D);
        ps.setFooterMargin(0.25D);
        ps.setLandscape(true);
        sheet.getWorkbook().setPrintArea(0, 0, numCol - 1, sheet.getFirstRowNum(), sheet.getLastRowNum());
        sheet.getPrintSetup().setPaperSize((short) 9);
        ps.setScale((short) 53);
        Header header = sheet.getHeader();
        header.setRight("Отчёт о работе");
//                Footer footer = sheet.getFooter();
//        footer.setLeft("Страница " + HeaderFooter.page() + " из " + HeaderFooter.numPages());
    }

    public void setList(List<UserAnswersModel> list) {
        this.list = list;
    }
}
