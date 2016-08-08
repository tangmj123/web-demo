package com.tangmj.demo.mybatis.plugin;

import java.util.List;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.PrimitiveTypeWrapper;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

public class PaginationPlugin extends PluginAdapter{

    @Override  
    public boolean modelExampleClassGenerated(TopLevelClass topLevelClass,  
            IntrospectedTable introspectedTable) {  
//        addLimit(topLevelClass, introspectedTable, "pageClause");  
//        FullyQualifiedJavaType fqjt = new FullyQualifiedJavaType("com.tangmj.base4j.base.example.BaseExample");
//    	topLevelClass.setSuperClass(fqjt);
    	addSetPageClause(topLevelClass, introspectedTable);
        return super.modelExampleClassGenerated(topLevelClass,  
                introspectedTable);  
    }  
    @Override  
    public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(  
            XmlElement element, IntrospectedTable introspectedTable) {  
        XmlElement isNotNullElement = new XmlElement("if");  
        isNotNullElement.addAttribute(new Attribute("test", "pageClause != null ")); 
        isNotNullElement.addElement(new TextElement(  
                "${pageClause}"));  
        element.addElement(isNotNullElement);  
        return super.sqlMapUpdateByExampleWithoutBLOBsElementGenerated(element,  
                introspectedTable);  
    }  
    
    private void addSetPageClause(TopLevelClass topLevelClass,  
            IntrospectedTable introspectedTable){
    	CommentGenerator commentGenerator = super.context.getCommentGenerator();
    	Field field = new Field();  
        field.setVisibility(JavaVisibility.PROTECTED);  
        field.setType(PrimitiveTypeWrapper.getStringInstance());  
        field.setName("pageClause");  
        commentGenerator.addFieldComment(field, introspectedTable);  
        topLevelClass.addField(field);  
    	 Method method = new Method();  
         method.setVisibility(JavaVisibility.PUBLIC);  
         method.setName("setPageClause");  
         method.addParameter(new Parameter(PrimitiveTypeWrapper.getIntegerInstance(), "currentPage"));  
         method.addParameter(new Parameter(PrimitiveTypeWrapper.getIntegerInstance(), "pageSize"));  
         method.addBodyLine("if(currentPage <= 0) currentPage = 1;");
         method.addBodyLine("if(   pageSize <= 0) pageSize    = 10;");
         method.addBodyLine("this.pageClause = \"limit \" + (currentPage-1)*pageSize +\" , \" +pageSize;");
         commentGenerator.addGeneralMethodComment(method, introspectedTable);  
         topLevelClass.addMethod(method);  
    }
    
    @SuppressWarnings("")
    private void addLimit(TopLevelClass topLevelClass,  
            IntrospectedTable introspectedTable, String name) {  
        CommentGenerator commentGenerator = context.getCommentGenerator();  
        Field field = new Field();  
        field.setVisibility(JavaVisibility.PROTECTED);  
        field.setType(PrimitiveTypeWrapper.getIntegerInstance());  
        field.setName(name);  
        commentGenerator.addFieldComment(field, introspectedTable);  
        topLevelClass.addField(field);  
        char c = name.charAt(0);  
        String camel = Character.toUpperCase(c) + name.substring(1);  
        Method method = new Method();  
        method.setVisibility(JavaVisibility.PUBLIC);  
        method.setName("set" + camel);  
        method.addParameter(new Parameter(PrimitiveTypeWrapper.getIntegerInstance(), name));  
        method.addBodyLine("this." + name + "=" + name + ";");  
        commentGenerator.addGeneralMethodComment(method, introspectedTable);  
        topLevelClass.addMethod(method);  
        method = new Method();  
        method.setVisibility(JavaVisibility.PUBLIC);  
        method.setReturnType(PrimitiveTypeWrapper.getIntegerInstance());  
        method.setName("get" + camel);  
        method.addBodyLine("return " + name + ";");  
        commentGenerator.addGeneralMethodComment(method, introspectedTable);  
        topLevelClass.addMethod(method);  
    }  
    /** 
     * This plugin is always valid - no properties are required 
     */  
    public boolean validate(List<String> warnings) {  
        return true;  
    }  
}
