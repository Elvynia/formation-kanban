package fr.formation.kanban.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Kanban {

	private Integer id;

	private List<Category> categories;

	private Date openedOn;

	private Date closedOn;
	
	public Kanban() {
		this.openedOn = new Date();
		this.closedOn = new Date();
		this.categories = new ArrayList<>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public Date getOpenedOn() {
		return openedOn;
	}

	public void setOpenedOn(Date openedOn) {
		this.openedOn = openedOn;
	}

	public Date getClosedOn() {
		return closedOn;
	}

	public void setClosedOn(Date closedOn) {
		this.closedOn = closedOn;
	}

}
