package org.gocom.components.coframe.dict.impl;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Vector;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.gocom.components.coframe.dict.EosDictEntry;
import org.gocom.components.coframe.dict.EosDictType;
import org.gocom.components.coframe.tools.DasEntityHelper;

import com.eos.das.entity.ExpressionHelper;
import com.eos.das.entity.IDASCriteria;
import com.eos.data.xpath.XPathLocator;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.eoscommon.ConfigurationUtil;
import com.eos.runtime.core.ApplicationContext;
import org.gocom.components.coframe.tools.CoframeDASDaoSupport;
import com.primeton.data.sdo.impl.TypeReference;
import com.primeton.data.sdo.impl.types.BooleanType;
import com.primeton.data.sdo.impl.types.DateTimeType;
import com.primeton.data.sdo.impl.types.DateType;
import com.primeton.data.sdo.impl.types.DecimalType;
import com.primeton.data.sdo.impl.types.FloatType;
import com.primeton.data.sdo.impl.types.IntType;
import com.primeton.data.sdo.impl.types.IntegerType;
import com.primeton.data.sdo.impl.types.LongType;
import commonj.sdo.DataObject;
import commonj.sdo.Property;
import commonj.sdo.Type;

/**
 * <pre>
 *         Title: 业务字典项服务类
 *         Description: 业务字典项服务类
 * </pre>
 * 
 * @author 陈鹏
 * @version 1.00.00
 * 
 */
public class DictService extends CoframeDASDaoSupport {
	public static final String CONTRIBUTION_COFRAME_UTILS = "org.gocom.components.coframe.dict";
	public static final String MODULE_COFRAME = "coframe-config";
	public static final String GROUP_EXCEL = "excel-config";
	public static final String EXCEL_TEMPLATE_PATH = "excel_template_path";

	public DictService() {
	}

	public static byte[] dictExport(DataObject[] dictdata, DataObject exportInfo) throws Exception {
		String templateFilename = "DictTemplate";
		return dictExport(dictdata, exportInfo, templateFilename);
	}
	
	public static int dictImport(String excelFile) throws Exception {
		String templateFilename = "DictTemplate";
		ExcelTemplate template=getImportTemplateFile(templateFilename);
		template.initialize();
		return template.importDictData(excelFile, 500);
	}

	public static byte[] dictExport(DataObject[] dictdata, DataObject exportInfo, String templateFilename) throws Exception {
		ExcelTemplate template = getTemplateFile(templateFilename);
		template.initialize();
		if (template.getStartRow() == -1)
			return null;

		// 先填充标题
		if (exportInfo != null)
			template.generateTitleDatas(exportInfo);
		// 生成数据内容
		template.generateDictDatas(Arrays.asList(dictdata));
		return template.writeToStream();

	}

	private static ExcelTemplate getTemplateFile(String templateName) throws Exception {
		if (templateName.indexOf(".xls") == -1) {
			templateName += ".xls";
		}

		String templateDir = ApplicationContext.getInstance().getWarRealPath()
				+ ConfigurationUtil.getContributionConfig(CONTRIBUTION_COFRAME_UTILS, MODULE_COFRAME, GROUP_EXCEL, EXCEL_TEMPLATE_PATH);

		if (!templateDir.endsWith("/")) {
			templateDir += "/";
		}
		String templateFile = templateDir + templateName;
		
		return new ExcelTemplate(templateFile);
	}
	
	/**
	 * 生成导入的模板
	 * @param templateName
	 * @param inoutfile
	 * @return
	 * @throws Exception
	 */
	private static ExcelTemplate getImportTemplateFile(String templateName) throws Exception {
		return getTemplateFile(templateName);
	}

	/**
	 * 检查叶子节点
	 * 
	 * @param data
	 */
	public void checkLeaf(DataObject[] data) {
		for (DataObject obj : data) {
			EosDictEntry dict = (EosDictEntry) obj;
			IDASCriteria dasCriteria = getDASTemplate().createCriteria(EosDictEntry.QNAME);
			dasCriteria.add(ExpressionHelper.eq("parentid", dict.getDictid()));
			dasCriteria.add(ExpressionHelper.like("seqno", dict.getSeqno() + "%"));
			EosDictEntry[] children = getDASTemplate().queryEntitiesByCriteriaEntity(EosDictEntry.class, dasCriteria);

			boolean flag = false;
			if (children.length > 0) {
				for (EosDictEntry child : children) {
					String parent_dict_seqno = dict.getEosDictType().getSeqno();
					String child_dict_seqno = child.getEosDictType().getSeqno();
					if (child_dict_seqno.indexOf(parent_dict_seqno)!=0)
						continue;
					IDASCriteria dasCriteria2 = getDASTemplate().createCriteria(EosDictType.QNAME);
					dasCriteria2.add(ExpressionHelper.eq("dicttypeid", child.getEosDictType().getDicttypeid()));
					EosDictType[] types = getDASTemplate().queryEntitiesByCriteriaEntity(EosDictType.class, dasCriteria2);
					if (types[0].getParentid().equals(dict.getEosDictType().getDicttypeid())) {
						flag = true;
						break;
					}
				}
			}
			if (flag) {
				dict.setBoolean("isLeaf", false);
				dict.setBoolean("expanded", false);
			}
		}
	}
}

class ExcelTemplate {

	/**
	 * 模板文件名
	 */
	private String templateFile;

	/**
	 * Excel模板定义的输出字段名数组
	 */
	private String[] fieldNames;

	/**
	 * 输出的起始行,默认为-1,不输出
	 */
	private int startRow = -1;

	/**
	 * 默认字体大小
	 */
	private int fontSize = 10;

	/**
	 * 默认字体
	 */
	private String fontName = "宋体";

	/**
	 * 是否设置信息标题栏边框,默认情况不设置边框
	 */
	private boolean titleCellBold = false;

	/**
	 * 是否设置空白栏边框，默认情况不设置边框
	 */
	private boolean blankCellBold = false;

	private HSSFCellStyle borderStyle = null;

	private HSSFCellStyle noneStyle = null;

	/**
	 * 关键字 &-表示模版信息内容字段 #-表示模版明细内容字段
	 */
	private final String TITLE_FLAG = "&";

	private final String CONTENT_FLAG = "#";

	private final String FIELD_AUTO_ID = "_id";

	private final String ENTRY = "org.gocom.components.coframe.dict.dict.EosDictEntry";

	private final String TYPE = "org.gocom.components.coframe.dict.dict.EosDictType";

	// 默认行号
	private int autoRowId = 1;

	private POIFSFileSystem fs = null;

	private HSSFWorkbook wb = null;

	private HSSFSheet sheet = null;

	// private HSSFRow sourceRow = null;

	/**
	 * 默认构造函数
	 * 
	 */
	public ExcelTemplate() {

	}

	/**
	 * 构造器
	 * 
	 * @param templateFile
	 *            模版文件
	 */
	public ExcelTemplate(String template) {
		this.templateFile = template;
		try {
			this.setFs(templateFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return Returns the templateFile.
	 */
	public String getTemplateFile() {
		return templateFile;
	}

	/**
	 * @param templateFile
	 *            The templateFile to set.
	 */
	public void setTemplateFile(String templateFile) {
		this.templateFile = templateFile;
	}

	public POIFSFileSystem getFs() {
		return fs;
	}

	/**
	 * 设置fs后会使用 new HSSFWorkbook(fs)初始化 workbook
	 * 
	 * @param fs
	 * @throws Exception
	 */
	public void setFs(POIFSFileSystem fs) throws Exception {
		this.fs = fs;
		setWb(new HSSFWorkbook(fs));
	}

	public void setFs(String fs) throws Exception {
		setFs(new POIFSFileSystem(new FileInputStream(fs)));
		setWb(new HSSFWorkbook(getFs()));
	}

	public HSSFWorkbook getWb() {
		return wb;
	}

	public void setWb(HSSFWorkbook wb) {
		this.wb = wb;
		borderStyle = getBorderStyle(wb);
		noneStyle = getNoneStyle(wb);
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public HSSFSheet getSheet() {
		return sheet;
	}

	public void setSheet(HSSFSheet sheet) {
		this.sheet = sheet;
	}

	/**
	 * 设置标题栏是否需要边框
	 * 
	 * @param b
	 */
	public void setTitleCellBold(boolean titleCellBold) {
		this.titleCellBold = titleCellBold;
	}

	/**
	 * 设置空白行是否需要显示边框
	 * 
	 * @param blankCellBold
	 */
	public void setBlankCellBold(boolean blankCellBold) {
		this.blankCellBold = blankCellBold;
	}

	/**
	 * 设置字体大小，默认10号字体
	 * 
	 * @param size
	 */
	public void setFontSize(int size) {
		this.fontSize = size;
	}

	public void setFontName(String fontName) {
		this.fontName = fontName;
	}

	/**
	 * 初始化工作模版，获取模版配置起始行(start)以及对应字段填充位置(fieldNames) 调用此方法前需要设置fs 和 sheet 如果
	 * sheet为空则取 设置sheet=wb.getSheetAt(0)
	 * 
	 * @param sheet
	 */
	public boolean initialize() {
		if (sheet == null) {
			setSheet(wb.getSheetAt(0));
		}
		return initialize(sheet);

	}

	/**
	 * 初始化工作模版，获取模版配置起始行(start)以及对应字段填充位置(fieldNames)
	 * 
	 * @param sheet
	 */
	private boolean initialize(HSSFSheet sheet) {
		boolean setStart = false;
		try {
			if (sheet != null) {
				int rows = sheet.getPhysicalNumberOfRows();
				for (int r = 0; r < rows; r++) {
					HSSFRow row = sheet.getRow(r);

					if (row != null) {
						int cells = row.getPhysicalNumberOfCells();
						for (short c = 0; c < cells; c++) {
							HSSFCell cell = row.getCell(c);
							if (cell != null) {
								String value = null;
								if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
									value = "" + cell.getNumericCellValue();
								} else if (cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {
									value = "" + cell.getBooleanCellValue();
								} else {
									value = cell.getRichStringCellValue().getString();
								}
								if (value != null && !"".equals(value)) {
									value = value.trim();
									// 内容数据
									if (value.startsWith(CONTENT_FLAG)) {
										if (!setStart) {
											this.startRow = r;// 设置内容填充起始行，从字段设置行的下一行开始
											this.fieldNames = new String[cells];
											setStart = true;
										}
										this.fieldNames[c] = value.substring(1);// 初始化内容字段
									}

								}

							}

						}
					}
				}
			}
		} catch (Exception e) {
			setStart = false;
		}
		return setStart;
	}

	/**
	 * 生成填充模版标题数据
	 * 
	 * @param titleMap
	 * @param wb
	 * @param sheet
	 * @throws Exception
	 */
	public void generateTitleDatas(DataObject exportInfo) throws Exception {
		HSSFSheet sheet = getSheet();
		int rows = sheet.getPhysicalNumberOfRows();

		for (int r = 0; r < rows; r++) {
			HSSFRow row = sheet.getRow(r);
			if (row != null) {

				int cells = row.getPhysicalNumberOfCells();
				for (short c = 0; c < cells; c++) {
					HSSFCell cell = row.getCell(c);
					if (cell != null) {
						if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
							String value = cell.getRichStringCellValue().getString();
							if (value != null) {
								value = value.trim();
								if (value.startsWith(TITLE_FLAG)) {
									value = value.substring(1);
									// 获取对应的值，支持XPATH取值
									Object obj = XPathLocator.newInstance().getValue(exportInfo, value);
									String content = obj + "";

									// String
									// content=exportInfo.getString(value);
									if (content == null)
										content = "";
									// 重建Cell，填充标题值
									cell = row.createCell((short) c);
									cell.setCellType(HSSFCell.CELL_TYPE_STRING);
									cell.setCellValue(new HSSFRichTextString(content));

									if (!titleCellBold) {
										cell.setCellStyle(noneStyle);
									} else {
										cell.setCellStyle(borderStyle);
									}
								}
							}
						}
					}

				}
			}
		}
	}

	/**
	 * 将指定的对象数组resulset输出到指定的Excel位置
	 * 
	 * @param resultset
	 *            List<DataObject>对象数组
	 * @param wb
	 *            HSSFWorkbook
	 * @param sheet
	 *            HSSFSheet
	 */
	public void generateContentDatas(List<DataObject> resultset) {
		HSSFSheet sheet = getSheet();

		for (Iterator it = resultset.iterator(); it.hasNext(); autoRowId++) {
			DataObject content = (DataObject) it.next();
			startRow++;
			HSSFRow row = sheet.createRow(startRow);
			genOneLine(row, content);
			if (it.hasNext()) {
				shiftDown(sheet, startRow, sheet.getLastRowNum(), 1);
			}

		}

	}

	/**
	 * 将指定的对象数组resulset输出到指定的Excel位置
	 * 
	 * @param resultset
	 *            List<DataObject>对象数组
	 * @param wb
	 *            HSSFWorkbook
	 * @param sheet
	 *            HSSFSheet
	 */
	public void generateDictDatas(List<DataObject> resultset) {
		HSSFSheet sheet = getSheet();

		for (Iterator it = resultset.iterator(); it.hasNext(); autoRowId++) {
			DataObject content = (DataObject) it.next();
			HSSFRow row = sheet.createRow(startRow);
			// String dictid = (String)
			// XPathLocator.newInstance().getValue(content, "dictid");
			// if (dictid != null) {
			// content.set("dicttypeid", content.get("eosDictType/dicttypeid"));
			// content.set("dicttypename",
			// content.get("eosDictType/dicttypename"));
			// }
			genOneLine(row, content);
			if (it.hasNext()) {
				shiftDown(sheet, startRow, sheet.getLastRowNum(), 1);
			}
			startRow++;
		}

	}

	/**
	 * 生成一行excel数据
	 * 
	 * @param row
	 * @param content
	 */
	private void genOneLine(HSSFRow row, DataObject content) {
		for (int i = 0; i < fieldNames.length; i++) {
			// 输出自动生成的行号
			if (fieldNames[i] != null && fieldNames[i].equals(FIELD_AUTO_ID)) {
				HSSFCell cell = row.createCell((short) i);
				cell.setCellStyle(borderStyle);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellValue(autoRowId);
				continue;
			}

			if (fieldNames[i] != null) {
				HSSFCell cell = row.createCell((short) i);
				cell.setCellStyle(borderStyle);
				if (content != null) {
					// 字段名支持xpath取值
					Object value = XPathLocator.newInstance().getValue(content, fieldNames[i]);

					// Object value=content.get(fieldNames[i]);
					if (value != null) {
						if (value instanceof Double || value instanceof BigDecimal) {
							cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
							cell.setCellValue(Double.parseDouble(value.toString()));
						} else {
							cell.setCellType(HSSFCell.CELL_TYPE_STRING);
							cell.setCellValue(new HSSFRichTextString(value.toString()));
						}
					} else {
						cell.setCellType(HSSFCell.CELL_TYPE_BLANK);
					}

				} else {

					cell.setCellType(HSSFCell.CELL_TYPE_BLANK);
					if (!blankCellBold) {
						cell.setCellStyle(noneStyle);
					} else {
						cell.setCellStyle(borderStyle);
					}
				}
			}
		}
	}

	public byte[] writeToStream() throws Exception {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		getWb().write(out);
		byte[] data = out.toByteArray();
		out.close();
		
		return data;
	}

	private HSSFCellStyle getBorderStyle(HSSFWorkbook wb) {
		HSSFCellStyle style = wb.createCellStyle();
		HSSFFont font = wb.createFont();
		font.setFontHeightInPoints((short) fontSize);
		font.setFontName(fontName);
		style.setFont(font);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		return style;
	}

	private HSSFCellStyle getNoneStyle(HSSFWorkbook wb) {
		HSSFCellStyle style = wb.createCellStyle();
		HSSFFont font = wb.createFont();
		font.setFontHeightInPoints((short) fontSize);
		font.setFontName(fontName);
		style.setFont(font);
		style.setBorderBottom(HSSFCellStyle.BORDER_NONE);
		style.setBorderLeft(HSSFCellStyle.BORDER_NONE);
		style.setBorderRight(HSSFCellStyle.BORDER_NONE);
		style.setBorderTop(HSSFCellStyle.BORDER_NONE);
		return style;
	}

	/**
	 * 向下平推表格，并复制格式与内容
	 * 
	 * @param thisrow：当前行号
	 * @param lastrow：最后行号
	 * @param shiftcount：平推量
	 */
	private void shiftDown(HSSFSheet sheet, int thisrow, int lastrow, int shiftcount) {
		sheet.shiftRows(thisrow, lastrow, shiftcount);

		for (int z = 0; z < shiftcount; z++) {
			HSSFRow row = sheet.getRow(thisrow);
			HSSFRow oldrow = sheet.getRow(thisrow + shiftcount);
			// 将各行的行高复制
			oldrow.setHeight(row.getHeight());
			// 将各个单元格的格式复制
			for (short i = 0; i <= oldrow.getPhysicalNumberOfCells(); i++) {

				HSSFCell cell = row.createCell(i);
				HSSFCell oldcell = oldrow.getCell(i);

				if (oldcell != null) {
					switch (oldcell.getCellType()) {
					case HSSFCell.CELL_TYPE_STRING:
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						cell.setCellValue(oldcell.getRichStringCellValue());
						break;
					case HSSFCell.CELL_TYPE_NUMERIC:
						cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
						cell.setCellValue(oldcell.getNumericCellValue());
						break;
					default:
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						cell.setCellValue(oldcell.getRichStringCellValue());

					}
					cell.setCellStyle(oldcell.getCellStyle());
				}
			}

			// 将有列跨越的复制
			Vector regs = findRegion(sheet, oldrow);
			if (regs.size() != 0) {
				for (int i = 0; i < regs.size(); i++) {
					Region reg = (Region) regs.get(i);
					reg.setRowFrom(row.getRowNum());
					reg.setRowTo(row.getRowNum());
					sheet.addMergedRegion(reg);
				}
			}
			thisrow++;
		}
	}

	/**
	 * 查找所有的合并单元格
	 * 
	 * @param oldrow
	 * @return
	 */
	private Vector findRegion(HSSFSheet sheet, HSSFRow oldrow) {
		Vector<Region> regs = new Vector<Region>();
		int num = sheet.getNumMergedRegions();
		int curRowid = oldrow.getRowNum();
		for (int i = 0; i < num; i++) {
			Region reg = sheet.getMergedRegionAt(i);
			if (reg.getRowFrom() == reg.getRowTo() && reg.getRowFrom() == curRowid) {
				regs.add(reg);
			}
		}
		return regs;
	}

	/**
	 * 将目标Excel文件的内容导入到默认数据源数据表
	 * 
	 * @param targetFile
	 *            Excel文件路径
	 * @param entityName
	 *            SDO数据实体全名
	 * @return 返回1 导入成功
	 * 
	 * @throws Exception
	 */
	public int importDictData(String excelFile, int submitCount) throws Exception {		
		int ret = 0;
		HSSFWorkbook source = new HSSFWorkbook(new POIFSFileSystem(new FileInputStream(excelFile)));

		for (int sheetCount = 0; sheetCount < wb.getNumberOfSheets(); sheetCount++) {
			// 设置模板
			HSSFSheet sheet = wb.getSheetAt(sheetCount);
			setSheet(sheet);
			// 如果无法初始化，跳过当前sheet
			if (!initialize())
				continue;
			// 设置输入文件信息
			HSSFSheet srcsheet = source.getSheetAt(sheetCount);
			int rows = srcsheet.getPhysicalNumberOfRows();

			List<DataObject> typelist = new ArrayList<DataObject>();
			Set<String> typeset = new HashSet<String>();
			List<DataObject> entrylist = new ArrayList<DataObject>();
			Set<String> entryset = new HashSet<String>();
			// 从以#开头的的字段名行开始
			int cellno = getDictCellNo(this.fieldNames);
			for (int rowCount = startRow; rowCount < rows; rowCount++) {
				DataObject[] objs = getDictLine(srcsheet.getRow(rowCount), cellno);
				if (objs.length > 0) {
					String dicttype = objs[0].getString("dicttypeid");
					if (!typeset.contains(dicttype) && dicttype != null) {
						typelist.add(objs[0]);
						typeset.add(dicttype);
					}
					if (objs.length > 1) {
						String dictid = dicttype + objs[1].getString("dictid");
						if (!entryset.contains(dictid) && objs[1].getString("dictid") != null) {
							entrylist.add(objs[1]);
							entryset.add(dictid);
						}
					}
				}
				// 如果数据批量操作上限执行数据库提交
				if ((entrylist.size() + typelist.size()) == submitCount) {
					ret += saveData(typelist);
					ret += saveData(entrylist);
				}
			}
			// 如果dataObjects中还有数据，进行持久化。
			if ((entrylist.size() + typelist.size()) > 0) {
				ret += saveData(typelist);
				ret += saveData(entrylist);
			}
		}
		return ret;
	}

	/**
	 * 批量保存数据
	 * 
	 * @param dsname
	 * @param list
	 * @return
	 */
	private int saveData(List<DataObject> list) {
		int ret = list.size();
		
		DasEntityHelper.saveEntities(list.toArray(new DataObject[ret]));
		list.clear();

		return ret;
	}

	/**
	 * 返回dictid所在列的序号 -1表示不存在
	 * 
	 * @param fields
	 * @return
	 */
	private int getDictCellNo(String[] fields) {
		for (int i = 0; i < fields.length; i++) {
			if ("dictid".equals(fields[i])) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 获取字典记录
	 * 
	 * @param sourceRow
	 * @param cellno
	 * @return
	 */
	private DataObject[] getDictLine(HSSFRow sourceRow, int cellno) {
		if (sourceRow != null) {
			DataObject importType = DataObjectUtil.createDataObject(TYPE);
			DataObject importEntity = DataObjectUtil.createDataObject(ENTRY);
			// 以下构造导入的实体对象，并根据Excel单元格的内容填充实体属性值
			for (int cellCount = 0; cellCount < fieldNames.length; cellCount++) {
				String propertyName = fieldNames[cellCount];
				HSSFCell cell = sourceRow.getCell((short) cellCount);

				setDataValue(importType, propertyName, cell);
				if ("dicttypeid".equals(propertyName)) {
					propertyName = "eosDictType/dicttypeid";
				}
				setDataValue(importEntity, propertyName, cell);
			}
			return new DataObject[] { importType, importEntity };
		} else {
			return new DataObject[0];
		}
	}

	/**
	 * 设置一个属性值
	 * 
	 * @param importEntity
	 * @param propertyName
	 * @param cell
	 * @return
	 */
	private boolean setDataValue(DataObject importEntity, String propertyName, HSSFCell cell) {
		if (cell == null || cell.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
			return false;
		} else {

			String value = null;

			if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					value = dateFormat.format((cell.getDateCellValue()));

				} else {
					value = String.valueOf((long) cell.getNumericCellValue());
				}
			} else if (cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {
				value = cell.getBooleanCellValue() + "";
			} else {
				value = cell.getRichStringCellValue().getString();
			}

			String[] names = propertyName.split("\\/");
			TypeReference typeReference = null;
			if (names.length == 2) {
				Property property = importEntity.getType().getProperty(names[0]);
				// 如果没有这个属性，直接返回。
				if (property == null) {
					return false;
				}
				typeReference = (TypeReference) property.getType().getProperty(names[1]).getType();
			} else if (names.length == 1) {
				if(propertyName.startsWith("type") && "EosDictType".equals(importEntity.getType().getName())){
					propertyName = propertyName.replace("type", "").toLowerCase();
				}
				if(propertyName.startsWith("entry") && "EosDictEntry".equals(importEntity.getType().getName())){
					propertyName = propertyName.replace("entry", "").toLowerCase();
				}
				
				Property property = importEntity.getType().getProperty(propertyName);
				if (property == null) {
					return false;
				}
				typeReference = (TypeReference) property.getType();
			} else {
				return false;
			}

			Type propertyType = typeReference.getActualType();

			if (propertyType instanceof IntType || propertyType instanceof IntegerType) {
				// 防止可能出现的Excel表格读取自动加.号
				if (value.indexOf(".") != -1)
					value = value.substring(0, value.indexOf("."));
				importEntity.set(propertyName, ChangeUtil.toInteger(value));

			} else if (propertyType instanceof BooleanType) {
				importEntity.set(propertyName, ChangeUtil.toBoolean(Boolean.valueOf(value)));

			} else if (propertyType instanceof FloatType) {
				importEntity.set(propertyName, ChangeUtil.toFloat(value));

			} else if (propertyType instanceof LongType) {
				if (value.indexOf(".") != -1)
					value = value.substring(0, value.indexOf("."));

				importEntity.set(propertyName, ChangeUtil.toLong(value));

			} else if (propertyType instanceof DecimalType) {
				importEntity.set(propertyName, ChangeUtil.toBigDecimal(value));

			} else if (propertyType instanceof DateType) {
				importEntity.set(propertyName, ChangeUtil.changeToDBDate(value));

			} else if (propertyType instanceof DateTimeType) {
				importEntity.set(propertyName, ChangeUtil.toTimestamp(value));

			} else {
				importEntity.set(propertyName, "".equals(value) ? null : value);
			}
		}

		return true;
	}
}

class ChangeUtil {
	/**
	 * 时间格式(年月日）
	 */
	public static final String DATE_FORMAT_YMD = "yyyyMMdd";

	/**
	 * 时间格式（年月）
	 */
	public static final String DATE_FORMAT_YM = "yyyyMM";

	/**
	 * 时间格式（年）
	 */
	public static final String DATE_FORMAT_Y = "yyyy";

	public static final String DATE_FORMAT_YMD_HMS = "yyyy-MM-dd HH:mm:ss";

	/**
	 * ChangeUtil类的缺省构造器。
	 */
	private ChangeUtil() {
	}

	/**
	 * 将一个以','分割的字符串，转换为一个Vector对象。这是changeStringToVector(String str, String
	 * token)的简化版本。
	 * 
	 * @param _str
	 *            需要转换的字符串
	 * @return 包含了字符串中元素的Vector对象。
	 * @see #changeStringToVector
	 */
	public static Vector changeStringToVector(String _str) {
		return changeStringToVector(_str, ",");
	}

	/**
	 * 将一个以字符串token分割的字符串，转换为一个Vector对象。如"姓名[token]年龄"被转换为一个Vector，该Vector包含两个元素，第一个是"姓名"，第二个是"年龄"。
	 * 
	 * @param _str
	 *            需要转换的字符串
	 * @param _token
	 *            字符串中分割的token。如空格" "，或":"等。
	 * @return 包含了字符串中元素的Vector对象。
	 */
	public static Vector changeStringToVector(String _str, String _token) {
		if (_str == null) {
			return null;
		}

		Vector<String> temp = new Vector<String>();
		StringTokenizer st = new StringTokenizer(_str, _token);
		while (st.hasMoreTokens()) {
			temp.add(st.nextToken());
		}
		return temp;
	}

	/**
	 * 将一个Vector对象中保存的字符串元素使用","分隔符转换为一个字符串，这是public static Vector
	 * changeStringToVector(String str)的逆操作。
	 * 
	 * @param _v
	 *            包含了字符串数据元素的Vector对象
	 * @return 一个以","为分隔符的字符串
	 */
	public static String changeVectorToString(Vector _v) {
		return changeVectorToString(_v, ",");
	}

	/**
	 * 将一个Vector对象中保存的字符串元素使用token分隔符转换为一个字符串，这是public static Vector
	 * changeStringToVector(String str, String token)的逆操作。
	 * 
	 * @param _v
	 *            包含了字符串数据元素的Vector对象
	 * @param _token
	 *            字符串中分割的token。如空格" "，或":"等。
	 * @return 一个以token为分隔符的字符串
	 */
	public static String changeVectorToString(Vector _v, String _token) {
		if (_v == null) {
			return null;
		}
		Enumeration enumeration = _v.elements();
		String str = "";
		while (enumeration.hasMoreElements()) {
			str = str + (String) (enumeration.nextElement()) + _token;
		}
		str = str.substring(0, str.length() - 1);
		return str;
	}

	/**
	 * 将一个字符串数组中保存的字符串元素使用","分隔符转换为一个字符串。
	 * 
	 * @param _strArray
	 *            包含了字符串数据元素的字符串数组
	 * @return 一个以","为分隔符的字符串
	 * @see #changeArrayToString
	 */
	public static String changeArrayToString(String[] _strArray) {
		return changeArrayToString(_strArray, ",");
	}

	/**
	 * 将一个字符串数组中保存的字符串元素使用token分隔符转换为一个字符串，这是public static Vector
	 * changeStringToVector(String str, String token)的逆操作。
	 * 
	 * @param _strArray
	 *            包含了字符串数据元素的字符串数组
	 * @param _token
	 *            分隔字符串使用的分隔符。
	 * @return 一个以token为分隔符的字符串
	 */
	public static String changeArrayToString(String[] _strArray, String _token) {
		if (_strArray == null) {
			return null;
		}

		int size = _strArray.length;
		if (size == 0) {
			return null;
		} else if (size == 1) {
			return _strArray[0];
		} else {
			String temp = _strArray[0];
			for (int i = 1; i < size; i++) {
				temp = temp + _token + _strArray[i];
			}
			return temp;
		}
	}

	/**
	 * 将一个使用","分隔符分隔的字符串，转变为一个字符串数组。
	 * 
	 * @param _str
	 *            用token分隔符分隔的字符串
	 * @return 字符串数组
	 */
	public static String[] changeStringToArray(String _str) {
		return changeStringToArray(_str, ",");
	}

	/**
	 * 将一个使用token分隔符分隔的字符串，转变为一个字符串数组。
	 * 
	 * @param _str
	 *            用token分隔符分隔的字符串
	 * @param _token
	 *            字符串的分隔符
	 * @return 字符串数组
	 */
	public static String[] changeStringToArray(String _str, String _token) {
		if (_str == null) {
			return null;
		}

		Vector v = changeStringToVector(_str, _token);
		String[] strArray = new String[v.size()];
		int i = 0;
		for (Enumeration em = v.elements(); em.hasMoreElements(); i++) {
			strArray[i] = (String) em.nextElement();
		}
		return strArray;
	}

	/**
	 * 获得以参数_fromDate为基数的年龄
	 * 
	 * @param _birthday
	 *            生日
	 * @param _fromDate
	 *            起算时间
	 * @return 年龄（起算年－出生年）
	 */
	public static int getAgeFromBirthday(java.util.Date _birthday, java.util.Date _fromDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(_birthday);
		int birthdayYear = calendar.get(Calendar.YEAR);
		int birthdayMonth = calendar.get(Calendar.MONTH);
		int birthdayDay = calendar.get(Calendar.DAY_OF_MONTH);
		calendar.clear();
		calendar.setTime(_fromDate);
		int currentYear = calendar.get(Calendar.YEAR);
		int currentMonth = calendar.get(Calendar.MONTH);
		int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
		calendar.clear();
		int age = currentYear - birthdayYear;
		if (!((currentMonth >= birthdayMonth) && (currentDay >= birthdayDay))) {
			age--;
		}
		return age;
	}

	/**
	 * 获得当前年龄
	 * 
	 * @param _birthday
	 *            生日
	 * @return 年龄（起算年－出生年）
	 */
	public static int getAgeFromBirthday(java.util.Date _birthday) {
		return getAgeFromBirthday(_birthday, new java.util.Date(System.currentTimeMillis()));
	}

	/**
	 * 获得当前年龄
	 * 
	 * @param _birthday
	 *            生日
	 * @return 年龄（起算年－出生年）
	 */
	public static int getAgeFromBirthday(java.sql.Timestamp _birthday) {
		return getAgeFromBirthday(new java.util.Date(_birthday.getTime()), new java.util.Date(System.currentTimeMillis()));
	}

	/**
	 * 使用格式{@link #DATE_FORMAT_YMD}格式化日期输出
	 * 
	 * @param _date
	 *            日期对象
	 * @return 格式化后的日期
	 */
	public static String formatDate(java.util.Date _date) {
		return formatDate(_date, DATE_FORMAT_YMD);
	}

	/**
	 * 使用格式<b>_pattern</b>格式化日期输出
	 * 
	 * @param _date
	 *            日期对象
	 * @param _pattern
	 *            日期格式
	 * @return 格式化后的日期
	 */
	public static String formatDate(java.util.Date _date, String _pattern) {
		if (_date == null) {
			return null;
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(_pattern);
		String stringDate = simpleDateFormat.format(_date);
		return stringDate;
	}

	/**
	 * 使用中文字符以简单的形式（"年 月 日"）格式化时间串
	 * 
	 * @param _date
	 *            日期对象
	 * @return 格式化后的日期
	 */
	public static String simplefFormatChineseDate(java.util.Date _date) {
		if (_date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(_date);
		StringBuffer sb = new StringBuffer();
		sb.append(calendar.get(Calendar.YEAR)).append("年").append(calendar.get(Calendar.MONTH) + 1).append("月").append(Calendar.DAY_OF_MONTH).append("日");
		calendar.clear();
		return sb.toString();
	}

	/**
	 * 使用中文字符以复杂的形式（"年 月 日 上午 时 分 秒"）格式化时间串
	 * 
	 * @param _date
	 *            日期对象
	 * @return 格式化后的日期
	 */
	public static String complexFormatChineseDate(java.util.Date _date) {
		if (_date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(_date);
		StringBuffer sb = new StringBuffer();
		sb.append(calendar.get(Calendar.YEAR)).append("年").append(calendar.get(Calendar.MONTH) + 1).append("月").append(Calendar.DAY_OF_MONTH).append("日")
				.append(Calendar.HOUR_OF_DAY).append("时").append(Calendar.MINUTE).append("分").append(Calendar.SECOND).append("秒");
		calendar.clear();
		return sb.toString();
	}

	/**
	 * 将时间串转变为时间对象，输入参数<b>_dateStr</b>必须遵循格式{@link #DATE_FORMAT_YMD}
	 * 
	 * @param _dateStr
	 *            时间串
	 * @return 时间对象
	 */
	public static java.util.Date changeToDate(String _dateStr) throws IllegalArgumentException {
		return changeToDate(_dateStr, DATE_FORMAT_YMD);
	}

	/**
	 * 将时间串转变为时间对象
	 * 
	 * @param _dateStr
	 *            时间串
	 * @param _pattern
	 *            时间串使用的模式
	 * @return 时间对象
	 * @throws ParamValidateException
	 *             当输入的时间串和它使用的模式不匹配时掷出
	 */
	public static java.util.Date changeToDate(String _dateStr, String _pattern) throws IllegalArgumentException {
		if (_dateStr == null || _dateStr.trim().equals("")) {
			return null;
		}
		java.util.Date date = null;
		SimpleDateFormat format = new SimpleDateFormat(_pattern);
		try {
			date = format.parse(_dateStr);
		} catch (java.text.ParseException pe) {
			throw new IllegalArgumentException("不能使用模式:[" + _pattern + "]格式化时间串:[" + _dateStr + "]");
		}
		return date;
	}

	/**
	 * 将时间串转变为数据库时间对象，输入参数<b>_dateStr</b>必须遵循格式{@link #DATE_FORMAT_YMD}
	 * 
	 * @param _dateStr
	 *            时间串
	 * @return 数据库时间对象
	 */
	public static java.sql.Date changeToDBDate(String _dateStr) throws IllegalArgumentException {
		return changeForDBDate(changeToDate(_dateStr, DATE_FORMAT_YMD));
	}

	/**
	 * 将时间串转变为数据库时间对象
	 * 
	 * @param _dateStr
	 *            时间串
	 * @param _pattern
	 *            时间串使用的模式
	 * @return 时间对象
	 * @throws ParamValidateException
	 *             当输入的时间串和它使用的模式不匹配时掷出
	 */
	public static java.sql.Date changeToDBDate(String _dateStr, String _pattern) throws IllegalArgumentException {
		return changeForDBDate(changeToDate(_dateStr, _pattern));
	}

	/**
	 * 将java.util.Date对象转换为java.sql.Date对象
	 * 
	 * @param _date
	 *            待转化的java.util.Date 对象
	 * @return java.sql.Date对象
	 */
	public static java.sql.Date changeForDBDate(java.util.Date _date) {
		if (_date == null) {
			return null;
		}
		return new java.sql.Date(_date.getTime());
	}

	/**
	 * 将java.sql.Date对象转换为java.util.Date对象
	 * 
	 * @param _date
	 *            待转化的java.sql.Date对象
	 * @return java.util.Date对象
	 */
	public static java.util.Date changFromDBDate(java.sql.Date _date) {
		return (java.util.Date) _date;
	}

	/**
	 * 将java.util.Date对象转换为java.sql.Timestamp对象
	 * 
	 * @param _date
	 *            待转化的java.util.Date 对象
	 * @return java.sql.Timestamp对象
	 */
	public static java.sql.Timestamp changeToTimestamp(java.util.Date _date) {
		if (_date == null) {
			return null;
		}
		return new java.sql.Timestamp(_date.getTime());
	}

	/**
	 * 将java.sql.Timestamp对象转换为java.util.Date对象
	 * 
	 * @param _date
	 *            待转化的java.sql.Timestamp 对象
	 * @return java.util.Date 对象
	 */
	public static java.util.Date changeFromTimestamp(java.sql.Timestamp _date) {
		return (java.util.Date) _date;
	}

	/**
	 * 改变字符串的编码方式(ISO8859_1)为(GBK)，以支持中文
	 * 
	 * @param _str
	 *            待转变的字符串
	 * @return 采用GBK编码的字符串
	 */
	public static String changeToGB(String _str) throws Exception {
		if (_str == null) {
			return null;
		}
		String gbStr = null;
		try {
			gbStr = new String(_str.getBytes("ISO8859_1"), "GBK");
		} catch (Exception e) {
			throw e;
		}
		return gbStr;
	}

	/**
	 * 改变字符串的编码方式(GBK)为(ISO8859_1)
	 * 
	 * @param _str
	 *            待转变的字符串
	 * @return 采用ISO8859_1编码的字符串
	 */
	public static String changeFromGB(String _str) throws Exception {
		if (_str == null) {
			return null;
		}
		String isoStr = null;
		try {
			isoStr = new String(_str.getBytes("GBK"), "ISO8859_1");
		} catch (Exception e) {
			throw e;
		}
		return isoStr;
	}

	/**
	 * 获得日期的年
	 * 
	 * @param _date
	 *            日期对象
	 * @return 日期的年
	 */
	public static int getYear(java.util.Date _date) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(_date);
		int year = calendar.get(Calendar.YEAR);
		calendar.clear();
		return year;
	}

	/**
	 * 获得日期的月
	 * 
	 * @param _date
	 *            日期对象
	 * @return 日期的月
	 */
	public static int getMonth(java.util.Date _date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(_date);
		// 以0开始
		int month = calendar.get(Calendar.MONTH);
		calendar.clear();
		return (month + 1);
	}

	/**
	 * 获得日期的天，以月为基
	 * 
	 * @param _date
	 *            日期对象
	 * @return 日期的天
	 */
	public static int getDay(java.util.Date _date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(_date);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		calendar.clear();
		return day;
	}

	/**
	 * 获得日期的小时
	 * 
	 * @param _date
	 *            日期对象
	 * @return 日期的小时
	 */
	public static int getHours(java.util.Date _date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(_date);
		int value = calendar.get(Calendar.HOUR);
		calendar.clear();
		return value;
	}

	/**
	 * 获得日期的分钟
	 * 
	 * @param _date
	 *            日期对象
	 * @return 日期的分钟
	 */
	public static int getMinutes(java.util.Date _date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(_date);
		int value = calendar.get(Calendar.MINUTE);
		calendar.clear();
		return value;
	}

	/**
	 * 获得日期的小秒
	 * 
	 * @param _date
	 *            日期对象
	 * @return 日期的秒
	 */
	public static int getSeconds(java.util.Date _date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(_date);
		int value = calendar.get(Calendar.SECOND);
		calendar.clear();
		return value;
	}

	/**
	 * 计算两个日期间相隔的天数
	 * 
	 * @param _startDate
	 *            起始日期
	 * @param _endDate
	 *            终止日期
	 * @return 相隔天数, 如果结果为正表示<b>_endDate</b>在<b>_startDate</b>之后；如果结果为负表示<b>_endDate</b>在<b>_startDate</b>之前；如果结果为0表示<b>_endDate</b>和<b>_startDate</b>是同一天。
	 */
	public static int getDayCount(java.util.Date _startDate, java.util.Date _endDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(_startDate);
		int startDay = calendar.get(Calendar.DAY_OF_YEAR);
		int startYear = calendar.get(Calendar.YEAR);
		calendar.clear();
		calendar.setTime(_endDate);
		int endDay = calendar.get(Calendar.DAY_OF_YEAR);
		int endYear = calendar.get(Calendar.YEAR);
		calendar.clear();
		return (endYear - startYear) * 365 + (endDay - startDay);
	}

	/**
	 * 获得两个Date间的月数, 天数超过14天算1个月
	 * 
	 * @param _startDate
	 *            开始时间
	 * @param _endDate
	 *            结束时间
	 * @return 两个Date间的月数
	 */
	public static int getMonthAmount(java.sql.Date _startDate, java.sql.Date _endDate) {
		int nYear = 0;
		int nMonth = 0;
		int nDay = 0;
		int nMonthAmount = 0;
		Calendar cldStart = Calendar.getInstance();
		Calendar cldEnd = Calendar.getInstance();
		cldStart.setTime(_startDate);
		cldEnd.setTime(_endDate);
		nYear = cldEnd.get(Calendar.YEAR) - cldStart.get(Calendar.YEAR);
		nMonth = cldEnd.get(Calendar.MONTH) - cldStart.get(Calendar.MONTH);
		nDay = cldEnd.get(Calendar.DATE) - cldStart.get(Calendar.DATE);
		if (nDay > 14) {
			nMonthAmount = nYear * 12 + nMonth + 1;
		} else {
			nMonthAmount = nYear * 12 + nMonth;
		}
		return nMonthAmount;
	}

	/**
	 * 格式化长整形数
	 * 
	 * @param _inStrObj
	 *            长整形字串对象
	 * @return 长整形数
	 */
	public static Long toLong(Object _inStrObj) {
		if (_inStrObj == null || _inStrObj.toString().trim().equals("")) {
			return null;
		} else {
			return Long.valueOf(_inStrObj.toString()).longValue();
		}
	}

	/**
	 * 格式化整形数
	 * 
	 * @param _inStrObj
	 *            整形数字串对象
	 * @return 整形数
	 */
	public static Integer toInteger(Object _inStrObj) {
		if (_inStrObj == null || _inStrObj.toString().trim().equals("")) {
			return null;
		} else {
			return new Integer(_inStrObj.toString()).intValue();
		}
	}

	/**
	 * 格式化双精浮点数
	 * 
	 * @param _inStrObj
	 *            双精浮点数字串对象
	 * @return 双精度浮点数，
	 */
	public static double toDouble(Object _inStrObj) {
		if (_inStrObj == null || _inStrObj.toString().trim().equals("")) {
			return 0;
		} else {
			return Double.valueOf(_inStrObj.toString()).doubleValue();
		}
	}

	/**
	 * 格式化浮点数
	 * 
	 * @param _inStrObj
	 *            浮点数字串对象
	 * @return 浮点数，如果数据格式错误，或字串为空，这返回0
	 */
	public static Float toFloat(Object _inStrObj) {
		if (_inStrObj == null || _inStrObj.toString().trim().equals("")) {
			return null;
		} else {
			return Float.valueOf(_inStrObj.toString()).floatValue();
		}
	}

	/**
	 * 将字节数组采用编码<b>_encoding</b>转化为字符串
	 * 
	 * @param _bytes
	 *            字节数组
	 * @param _encoding
	 *            编码方式
	 * @throws ParamValidateException
	 *             如果编码方式不支持时掷出
	 * @return 字符串
	 */
	public static String toStr(byte[] _bytes, String _encoding) throws IllegalArgumentException {
		if (_bytes == null) {
			return null;
		}

		String s = null;
		try {
			s = new String(_bytes, _encoding);
		} catch (Exception e) {
			throw new IllegalArgumentException("不支持的编码方式:" + _encoding);
		}
		return s;
	}

	/**
	 * 格式化布尔对象
	 * 
	 * @param _boolean
	 *            布尔对象
	 * @return 布尔对象的值，如果<b>_boolean</b>为null, 返回false
	 */
	public static Boolean toBoolean(Boolean _boolean) {
		if (_boolean == null) {
			return null;
		} else {
			return _boolean.booleanValue();
		}
	}

	/**
	 * 获得对象的字符串表示， 当<b>_obj</b>为null时用<b>_replaceStr</b>替代
	 * 
	 * @param _obj
	 *            对象
	 * @param _replaceStr
	 *            替代null值的字符串
	 * @return 处理后的字符串
	 */
	public static String toStr(Object _obj, String _replaceStr) {
		if (_obj == null) {
			return _replaceStr;
		} else {
			return _obj.toString();
		}
	}

	/**
	 * 字符串处理， 当<b>_str</b>为null时用<b>_replaceStr</b>替代
	 * 
	 * @param _str
	 *            原始字符串
	 * @param _replaceStr
	 *            替代null值的字符串
	 * @return 处理后的字符串
	 */
	public static String toStr(String _str, String _replaceStr) {
		if (_str == null || _str.equals("null")) {
			return _replaceStr;
		} else {
			return _str;
		}
	}

	/**
	 * 字符串处理， 当<b>_str</b>为null时用<b>""</b>替代
	 * 
	 * @param _str
	 *            原始字符串
	 * @return 处理后的字符串
	 */
	public static String toStr(String _str) {
		return toStr(_str, "");
	}

	/**
	 * 获得对象的字符串表示， 当<b>_obj</b>为null时用<b>""</b>替代
	 * 
	 * @param _obj
	 *            对象
	 * @return 获得对象的字符串
	 */
	public static String toStr(Object _obj) {
		if (_obj == null) {
			return "";
		} else {
			return toStr(_obj.toString());
		}
	}

	/**
	 * 将字符串采用编码<b>_encoding</b>转化为字节数组
	 * 
	 * @param _str
	 *            字符串
	 * @param _encoding
	 *            编码方式
	 * @throws ParamValidateException
	 *             如果编码方式不支持时掷出
	 * @return 字节数组
	 */
	public static byte[] toBytes(String _str, String _encoding) throws IllegalArgumentException {
		if (_str == null) {
			return null;
		}
		byte[] b = null;
		try {
			b = _str.getBytes(_encoding);
		} catch (Exception e) {
			throw new IllegalArgumentException("不支持的编码方式:" + _encoding);
		}
		return b;
	}

	/**
	 * 将双精浮点数代表的金额转化中文大写形式
	 * 
	 * @param _dMoney
	 *            代表双精浮点数的金额
	 * @return 金额的中文大写形式，如果输入参数<b>dMoney</b>大于10^8或小于0.01返回空串。
	 */
	public static String toChinese(double _dMoney) {
		String[] strArr = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
		String[] strArr1 = { "分", "角", "圆", "拾", "佰", "仟", "万", "拾", "佰", "仟" };
		String[] strArr2 = new String[10];
		String sRtn = "";
		int iTmp;
		double dTmp;
		try {
			_dMoney += 0.001;
			if ((_dMoney >= 100000000) || (_dMoney < 0.01)) {
				sRtn = "";
			} else {
				for (int i = 0; i < 10; i++) {
					dTmp = _dMoney / Math.pow(10, 7 - i);
					iTmp = (new Double(dTmp)).intValue();
					_dMoney -= iTmp * Math.pow(10, 7 - i);
					if (iTmp != 0) {
						strArr2[i] = strArr[iTmp] + strArr1[9 - i];
					} else {
						strArr2[i] = "";
					}
				}
				boolean bFlag = false;
				for (int i = 0; i < 10; i++) {
					if (!"".equals(strArr2[i])) {
						sRtn += strArr2[i];
						bFlag = true;
					} else {
						if (i == 3) {
							sRtn += "万";
							bFlag = true;
						} else if (i == 7) {
							sRtn += "圆";
							bFlag = true;
						} else if (bFlag) {
							sRtn += "零";
							bFlag = false;
						}
					}
				}
				if (sRtn.startsWith("万")) {
					sRtn = sRtn.substring(1, sRtn.length());
				}
				if (sRtn.startsWith("圆")) {
					sRtn = sRtn.substring(1, sRtn.length());
				}
				while (sRtn.startsWith("零")) {
					sRtn = sRtn.substring(1, sRtn.length());
				}
				if (sRtn.lastIndexOf("零") == (sRtn.length() - 1)) {
					sRtn = sRtn.substring(0, sRtn.length() - 1);
				}
				if (sRtn.startsWith("圆")) {
					sRtn = sRtn.substring(1, sRtn.length());
				}
				iTmp = sRtn.indexOf("圆");
				if (iTmp != -1) {
					if ("零".equals(sRtn.substring(iTmp - 1, iTmp))) {
						sRtn = sRtn.substring(0, iTmp - 1) + sRtn.substring(iTmp, sRtn.length());
					}
				}
				iTmp = sRtn.indexOf("万");
				if (iTmp != -1) {
					if ("零".equals(sRtn.substring(iTmp - 1, iTmp))) {
						sRtn = sRtn.substring(0, iTmp - 1) + sRtn.substring(iTmp, sRtn.length());
					}
				}
				while (sRtn.startsWith("零")) {
					sRtn = sRtn.substring(1, sRtn.length());
				}
				sRtn += "整";
			}
		} catch (Exception ex) {
		}
		return sRtn;
	}

	/**
	 * 根据输入的String返回BigDecimal，或者若String非数字串，返回null
	 * 
	 * @param _str
	 *            待转化的字符串
	 * @return BigDecimal对象
	 */
	public static BigDecimal toBigDecimal(String _str) {
		BigDecimal bd = null;
		if (_str != null) {
			try {
				bd = new BigDecimal(_str);
			} catch (Exception e) {
				return null;
			}
		}
		return bd;
	}

	/**
	 * 根据年，月，日，转化为Timestamp类型,便于DB插入处理
	 * 
	 * @param _sDate
	 *            格式为：yyyy-mm-dd
	 * @return Timestamp的时间格式
	 */
	public static Timestamp toTimestamp(String _sDate) {
		Timestamp ts = null;
		if (_sDate == null || "".equals(_sDate)) {
			return null;
		}
		ts = Timestamp.valueOf(_sDate + " 00:00:00.000000000");
		return ts;
	}

	/**
	 * 替换Html文档中的"&nbsp"为" ", "&lt"为"<", "&gt"为">"，"<br>
	 * "为"\r\n"
	 * 
	 * @param _rawStr
	 *            原始Html文档
	 * @return 替换后的Html文档
	 */
	public static String changeHtmlStr(String _rawStr) {
		String str = null;
		if (_rawStr != null) {
			str = replaceString("&nbsp;", " ", _rawStr);
			str = replaceString("&lt;", "<", str);
			str = replaceString("&gt;", ">", str);
			str = replaceString("&amp;", "&", str);
			str = replaceString("&quot;", "\"", str);
			str = replaceString("<br>", "\r\n", str);
		}
		return str;
	}

	/**
	 * 使用新串替换原有字符串中老串
	 * 
	 * @param _oldStr
	 *            待替换的字符串
	 * @param _newStr
	 *            新字符串
	 * @param _wholeStr
	 *            整个字符串
	 * @return 替换后新串
	 */
	public static String replaceString(String _oldStr, String _newStr, String _wholeStr) {
		if (_wholeStr == null) {
			return null;
		}
		if (_newStr == null) {
			return _wholeStr;
		}

		int start = 0, end = 0;
		StringBuffer result = new StringBuffer();
		result = result.append(_wholeStr);
		while (result.indexOf(_oldStr, start) > -1) {
			start = result.indexOf(_oldStr, start);
			end = start + _oldStr.length();
			result.replace(start, end, _newStr);
			start += _newStr.length();
		}
		return result.toString();
	}

	/**
	 * 如果是正向替换，使用新串替换原有字符串中第一个老串；如果是逆向替换，使用新串替换原有字符串中最后一个老串
	 * 
	 * @param _oldStr
	 *            待替换的字符串
	 * @param _newStr
	 *            新字符串
	 * @param _wholeStr
	 *            整个字符串
	 * @param _reverse
	 *            替换方向，如果为false正向替换，否则逆向替换
	 * @return 替换后新串
	 */
	public static String replaceFirstString(String _oldStr, String _newStr, String _wholeStr, boolean _reverse) {
		if (_wholeStr == null) {
			return null;
		}
		if (_newStr == null) {
			return _wholeStr;
		}
		StringBuffer result = new StringBuffer(_wholeStr);
		int start = 0, end = 0;
		if (!_reverse) {
			if (result.indexOf(_oldStr) > -1) {
				start = result.indexOf(_oldStr);
				end = start + _oldStr.length();
				result.replace(start, end, _newStr);
			}
		} else {
			if (result.lastIndexOf(_oldStr) > -1) {
				start = result.lastIndexOf(_oldStr);
				end = start + _oldStr.length();
				result.replace(start, end, _newStr);
			}
		}
		return result.toString();
	}

	/**
	 * 将字符串转换为HTML形式，以便在JavaScript中使用
	 * 
	 * @param _sourceStr
	 *            原字符串
	 * @return 转换后的字符串
	 */
	public static String changeToHTMLStr(String _sourceStr) {
		if (_sourceStr == null) {
			return null;
		}
		StringBuffer buff = new StringBuffer(1024);
		int n = _sourceStr.length();
		char c;
		for (int i = 0; i < n; i++) {
			c = _sourceStr.charAt(i);
			if (c == '"') {
				buff.append('\\');
				buff.append(c);
			} else if (c == '\\') {
				buff.append('\\');
				buff.append(c);
			} else if (c == '\r') {
				buff.append("\\r");
			} else if (c == '\n') {
				buff.append("\\n");
			} else {
				buff.append(c);
			}
		}
		return buff.toString();
	}

	/**
	 * 得到 _value截取小数点后_len位 以后的值
	 * 
	 * @param _value
	 *            原值
	 * @param _len
	 *            小数点后的位数
	 * @return 截取以后的值
	 */
	public static float roundFloat(float _value, int _len) throws IllegalArgumentException {
		int iLen = _len;
		checkParamPositive("_len", _len);
		float d = (float) Math.pow(10, iLen);
		float fValue = _value * d;
		return Math.round(fValue) / d;
	}

	/**
	 * 获得float的字符串表示，首先对_value按_len进行四舍五入截位，如果截位后小数点后位数小于_len，则使用0补齐。
	 * 
	 * @param _value
	 *            原值
	 * @param _len
	 *            小数点后的位数
	 * @return float的字符串
	 */
	public static String formatFloat(float _value, int _len) throws IllegalArgumentException {
		String fStr = String.valueOf(roundFloat(_value, _len));
		StringBuffer sb = new StringBuffer(fStr);
		int leftBit = fStr.length() - fStr.indexOf(".") - 1;
		if (leftBit < _len) {
			for (int i = 0; i < (_len - leftBit); i++) {
				sb.append("0");
			}
		}
		return sb.toString();
	}

	/**
	 * 得到 _value截取小数点后_len位 以后的值
	 * 
	 * @param _value
	 *            原值
	 * @param _len
	 *            小数点后的位数
	 * @return 截取以后的值
	 */
	public static double roundDouble(double _value, int _len) throws IllegalArgumentException {
		int iLen = _len;
		checkParamPositive("_len", _len);
		double d = Math.pow(10, iLen);
		double dValue = _value * d;
		return Math.round(dValue) / d;
	}

	/**
	 * 获得double的字符串表示，首先对_value按_len进行四舍五入截位，如果截位后小数点后位数小于_len，则使用0补齐。
	 * 
	 * @param _value
	 *            原值
	 * @param _len
	 *            小数点后的位数
	 * @return double的字符串
	 */
	public static String formatDouble(double _value, int _len) throws IllegalArgumentException {
		String fStr = String.valueOf(roundDouble(_value, _len));
		StringBuffer sb = new StringBuffer(fStr);
		int leftBit = fStr.length() - fStr.indexOf(".") - 1;
		if (leftBit < _len) {
			for (int i = 0; i < (_len - leftBit); i++) {
				sb.append("0");
			}
		}
		return sb.toString();
	}

	/**
	 * 获得字符串的左边
	 * <p>
	 * _len
	 * </p>
	 * 个字符串
	 * 
	 * @param _str
	 *            字符串
	 * @param _len
	 *            长度
	 * @return
	 *            <p>
	 *            _len
	 *            </p>
	 *            个字符串
	 */
	public static String leftString(String _str, int _len) {
		if (_str == null) {
			return null;
		}
		if (_len < 0) {
			return "";
		}
		if (_str.length() <= _len) {
			return _str;
		} else {
			return _str.substring(0, _len);
		}
	}

	/**
	 * 获得字符串的右边
	 * <p>
	 * _len
	 * </p>
	 * 个字符串
	 * 
	 * @param _str
	 *            字符串
	 * @param _len
	 *            长度
	 * @return 字符串的右边
	 *         <p>
	 *         _len
	 *         </p>
	 *         个字符串
	 */
	public static String rightString(String _str, int _len) {
		if (_str == null) {
			return null;
		}
		if (_len < 0) {
			return "";
		}
		if (_str.length() <= _len) {
			return _str;
		} else {
			return _str.substring(_str.length() - _len);
		}
	}

	/**
	 * 右填充字符
	 * <p>
	 * _padChar
	 * </p>
	 * ，使整个字符串长度为
	 * <p>
	 * _size
	 * </p>
	 * 
	 * @param _str
	 *            原始字符串
	 * @param _size
	 *            添充后字符的总长度
	 * @param _padChar
	 *            待填充字符
	 * @return 右填充后的字符串，如:rightPad('hell', 3, '0')=hell;rightPad('hell', 10,
	 *         '0')=hell000000
	 */
	public static String rightPad(String _str, int _size, char _padChar) {
		if (_str == null) {
			return null;
		}
		int pads = _size - _str.length();
		if (pads <= 0) {
			return _str; // returns original String when possible
		}
		return _str.concat(padding(pads, _padChar));
	}

	/**
	 * 左填充字符
	 * <p>
	 * _padChar
	 * </p>
	 * ，使得填充后的字符串总长为
	 * <p>
	 * _size
	 * </p>
	 * 
	 * @param _str
	 *            原始字符串
	 * @param _size
	 *            添充后字符的总长度
	 * @param _padChar
	 *            待填充字符
	 * @return 左填充后的字符串，如:leftPad('hell', 10, '0')=000000hell;leftPad('hell', 3,
	 *         '0')=hell
	 */
	public static String leftPad(String _str, int _size, char _padChar) {
		if (_str == null) {
			return null;
		}
		int pads = _size - _str.length();
		if (pads <= 0) {
			return _str; // returns original String when possible
		}
		return padding(pads, _padChar).concat(_str);
	}

	/**
	 * 字符串
	 * <p>
	 * padChar
	 * </p>
	 * 重复
	 * <p>
	 * repeat
	 * </p>
	 * 位
	 * 
	 * @param _repeat
	 *            重复次数
	 * @param _padChar
	 *            待重复字符
	 * @return 重复后的结果字串，如:padding(5, 'a')=aaaaa;padding(0, 'a'):""
	 */
	private static String padding(int _repeat, char _padChar) {
		String value = "";
		String padStr = String.valueOf(_padChar);
		if (_repeat > 0) {
			for (int i = 0; i < _repeat; i++) {
				value = value.concat(padStr);
			}
		}
		return value;
	}

	private static void checkParamPositive(String _str, int _value) throws IllegalArgumentException {
		if (_value <= 0) {
			throw new IllegalArgumentException("参数:" + _str + "不能小于等于0");
		}
	}

}