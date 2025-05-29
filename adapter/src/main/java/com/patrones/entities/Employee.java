package com.patrones.entities;

public class Employee {
    private Long id;
    private String name;
    private Integer experience;
    private String company;

    public Employee() {}

    public Employee(Long id, String name, Integer experience, String company) {
        this.id = id;
        this.name = name;
        this.experience = experience;
        this.company = company;
    }

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getExperience() {
		return experience;
	}
	public void setExperience(Integer experience) {
		this.experience = experience;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}

    @Override
    public String toString() {
        return "Employee{ id= " + id + ", name= " + name + ", experience= " + experience + ", company= " + company + "}";
    };
}
