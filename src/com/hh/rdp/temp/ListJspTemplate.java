package com.hh.rdp.temp;

import java.util.*;
import com.hh.rdp.util.Convert;
import com.hh.rdp.model.*;
import com.hh.rdp.util.*;

public class ListJspTemplate
{
  protected static String nl;
  public static synchronized ListJspTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    ListJspTemplate result = new ListJspTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + "<html>" + NL + "<head>" + NL + "<title>数据列表</title>";
  protected final String TEXT_3 = NL;
  protected final String TEXT_4 = NL + "<script type=\"text/javascript\">" + NL + "\tfunction doDelete() {" + NL + "\t\t$.hh.pagelist.deleteData({" + NL + "\t\t\tpageid : 'pagelist'," + NL + "\t\t\taction : '";
  protected final String TEXT_5 = "-";
  protected final String TEXT_6 = "-deleteByIds'" + NL + "\t\t});" + NL + "\t}" + NL + "\tfunction doAdd() {" + NL + "\t\tDialog.open({" + NL + "\t\t\turl : 'jsp-";
  protected final String TEXT_7 = "-";
  protected final String TEXT_8 = "-";
  protected final String TEXT_9 = "Edit'," + NL + "\t\t\tparams : {" + NL + "\t\t\t\tcallback : function() {" + NL + "\t\t\t\t\t$(\"#pagelist\").loadData();" + NL + "\t\t\t\t}" + NL + "\t\t\t}" + NL + "\t\t});" + NL + "\t}" + NL + "\tfunction doEdit() {" + NL + "\t\t$.hh.pagelist.callRow(\"pagelist\", function(row) {" + NL + "\t\t\tDialog.open({" + NL + "\t\t\t\turl : 'jsp-";
  protected final String TEXT_10 = "-";
  protected final String TEXT_11 = "-";
  protected final String TEXT_12 = "Edit'," + NL + "\t\t\t\turlParams : {" + NL + "\t\t\t\t\tid : row.id" + NL + "\t\t\t\t}," + NL + "\t\t\t\tparams : {" + NL + "\t\t\t\t\tcallback : function() {" + NL + "\t\t\t\t\t\t$(\"#pagelist\").loadData();" + NL + "\t\t\t\t\t}" + NL + "\t\t\t\t}" + NL + "\t\t\t});" + NL + "\t\t});" + NL + "\t}" + NL + "\tfunction doQuery() {" + NL + "\t\t$('#pagelist').loadData({" + NL + "\t\t\tparams : $('#queryForm').getValue()" + NL + "\t\t});" + NL + "\t}" + NL + "</script>" + NL + "</head>" + NL + "<body>" + NL + "\t<div xtype=\"toolbar\" config=\"type:'head'\">" + NL + "\t\t<span xtype=\"button\" config=\"onClick:doAdd,text:'添加' , itype :'add' \"></span>" + NL + "\t\t<span xtype=\"button\"" + NL + "\t\t\tconfig=\"onClick:doEdit,text:'修改' , itype :'edit' \"></span> <span" + NL + "\t\t\txtype=\"button\" config=\"onClick:doDelete,text:'删除' , itype :'delete' \"></span>" + NL + "\t\t<!--  <span" + NL + "\t\t\txtype=\"button\" config=\"onClick: doQuery ,text:'查询' , itype :'query' \"></span> --> <span" + NL + "\t\t\txtype=\"button\"" + NL + "\t\t\tconfig=\"onClick: $.hh.pagelist.doUp , params:{ pageid :'pagelist',action:'";
  protected final String TEXT_13 = "-";
  protected final String TEXT_14 = "-order'}  ,  icon : 'hh_up' \"></span>" + NL + "\t\t<span xtype=\"button\"" + NL + "\t\t\tconfig=\"onClick: $.hh.pagelist.doDown , params:{ pageid :'pagelist',action:'";
  protected final String TEXT_15 = "-";
  protected final String TEXT_16 = "-order'} , icon : 'hh_down' \"></span>" + NL + "\t</div>" + NL + "\t<!-- <table xtype=\"form\" id=\"queryForm\" style=\"width:600px;\">" + NL + "\t\t<tr>" + NL + "\t\t\t<td xtype=\"label\">test：</td>" + NL + "\t\t\t<td><span xtype=\"text\" config=\" name : 'test'\"></span></td>" + NL + "\t\t</tr>" + NL + "\t</table> -->" + NL + "\t<div id=\"pagelist\" xtype=\"pagelist\"" + NL + "\t\tconfig=\" url: '";
  protected final String TEXT_17 = "-";
  protected final String TEXT_18 = "-queryPagingData' ,column : [" + NL + "\t\t" + NL + "\t\t";
  protected final String TEXT_19 = NL + "\t\t";
  protected final String TEXT_20 = NL + "\t\t\t{" + NL + "\t\t\t\tname : '";
  protected final String TEXT_21 = "' ," + NL + "\t\t\t\ttext : '";
  protected final String TEXT_22 = "'" + NL + "\t\t\t}";
  protected final String TEXT_23 = NL + "\t\t";
  protected final String TEXT_24 = NL + "\t]\">" + NL + "\t</div>" + NL + "</body>" + NL + "</html>";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
 	JetModel jetModel =(JetModel) argument;
 	String className = jetModel.getClassName2();
 	String lowClassName = jetModel.getClassName2().toLowerCase();
 	String modelName = jetModel.getModelName();
 
    
 	String topStr="&lt;%@page language=\"java\" import=\"java.util.*\" pageEncoding=\"UTF-8\"%&gt;\n";
 	topStr+="&lt;%@page import=\"com.hh.system.util.SystemUtil\"%&gt;\n";
  	topStr+="&lt;%=SystemUtil.getBaseDoctype()%&gt;\n";
  	
  	String jsStr = "&lt;%=SystemUtil.getBaseJs()%&gt;\n";
  	
 
    stringBuffer.append(TEXT_1);
    stringBuffer.append(topStr);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(jsStr);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(modelName);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(modelName);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(lowClassName);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(modelName);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(lowClassName);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(modelName);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(modelName);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(modelName);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_18);
     List<Column> columnList = jetModel.getColumnList(); 
    stringBuffer.append(TEXT_19);
    	for (int i =0;i<columnList.size();i++) {
				    String douhao = ","; 
					if(columnList.size()-1==i){
						douhao="";
					}
					Column column = columnList.get(i);
					String name = column.getName();
					String text = column.getText();
					String length = column.getLength();
					String nameUpper =name.substring(0, 1).toUpperCase()+name.substring(1);
					String databaseColumnName = AppUtil.classNameTodataBaseName(name);
					String type = column.getType();
		
    stringBuffer.append(TEXT_20);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(text);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(douhao);
    stringBuffer.append(TEXT_23);
    }
    stringBuffer.append(TEXT_24);
    return stringBuffer.toString();
  }
}
