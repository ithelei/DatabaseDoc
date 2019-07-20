package com.dbdoc.db.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * 
 * @author  
 * 
 */
public class Table {

	private int index;//从1开始

	private String name; // ����

	private String remarks; // ע��

	private String className; // ����

	private String type; // ������

	private String desc;

	private String txt;

	private Set<Column> columns = new LinkedHashSet();// �У�s��

	private List<Column> primaryKeyColumns = new ArrayList();

	/** the name of the owner of the synonym if this table is a synonym */
	private String ownerSynonymName = null;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Set<Column> getColumns() {
		return columns;
	}

	public void setColumns(Set<Column> columns) {
		this.columns = columns;
	}

	public void addColumn(Column column) {
		columns.add(column);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getOwnerSynonymName() {
		return ownerSynonymName;
	}

	public void setOwnerSynonymName(String ownerSynonymName) {
		this.ownerSynonymName = ownerSynonymName;
	}

	public List<Column> getPrimaryKeyColumns() {
		return primaryKeyColumns;
	}

	public void setPrimaryKeyColumns(List<Column> primaryKeyColumns) {
		this.primaryKeyColumns = primaryKeyColumns;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void initPrimaryKeyColumns() {
		if (this.columns.size() > 0) {
			for (Iterator i = columns.iterator(); i.hasNext();) {
				Column column = (Column) i.next();
				if (column.is_isPk()) {
					primaryKeyColumns.add(column);
				}
			}
		}
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getTxt() {
		return txt;
	}

	public void setTxt(String txt) {
		this.txt = txt;
	}
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}


}
