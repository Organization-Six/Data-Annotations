package Model;

public class Percent {
	private String label;
	private int percent;
	
	public Percent(String label, int percent) {
		this.label = label;
		this.percent = percent;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getPercent() {
		return percent;
	}

	public void setPercent(int percent) {
		this.percent = percent;
	}
}
