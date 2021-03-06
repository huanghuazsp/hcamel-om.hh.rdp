package com.hh.rdp.dm.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.hh.rdp.model.Column;

@XmlRootElement
public class Table    {
	private String id = "";
	private String text = "";
	private String name = "";
	private String type = "table";
	private List<Column> children = new ArrayList<Column>();
	
	private Map<String, String> map = new HashMap<String, String>();
	private Project parent;

	@XmlElements({ @XmlElement(name = "column", type = Column.class) })
	public List<Column> getChildren() {
		return children;
	}

	public void setChildren(List<Column> children) {
		this.children = children;
	}

	@XmlAttribute
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@XmlAttribute
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@XmlAttribute
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@XmlAttribute
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@XmlTransient
	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}

	@XmlTransient
	public Project getParent() {
		return parent;
	}

	public void setParent(Project parent) {
		this.parent = parent;
	}

}
