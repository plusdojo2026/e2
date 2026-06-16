package dto;

import java.io.Serializable;

public class BudgetDto implements Serializable {
	private int id;
	private int budget_amount;
	private int goal_amount;
	private String user_id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public int getBudget_amount() {
		return budget_amount;
	}

	public void setBudget_amount(int budget_amount) {
		this.budget_amount = budget_amount;
	}

	public int getGoal_amount() {
		return goal_amount;
	}

	public void setGoal_amount(int targetamount) {
		this.goal_amount = targetamount;
	}

	
	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public BudgetDto() {
		super();
		this.id = 0;
		this.budget_amount = 0;
		this.goal_amount = 0;
		this.user_id = "";
	}
	
	public BudgetDto(int id, int budget_amount, int goal_amount, String user_id) {
		super();
		this.id = id;
		this.budget_amount = budget_amount;
		this.goal_amount = goal_amount;
		this.user_id = user_id;
	}
	
}
