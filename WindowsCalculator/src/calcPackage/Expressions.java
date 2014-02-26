package calcPackage;

//Helps the PQueue handle order of operations
public class Expressions {
	private String expression;
	private Integer priority;
	
	public Expressions(String expression, Integer priority) {
		this.expression = expression;
		this.priority = priority;
	}
	public Integer getPriority() {
		return priority;
	}
	public String getExpression() {
		return expression;
	}
}