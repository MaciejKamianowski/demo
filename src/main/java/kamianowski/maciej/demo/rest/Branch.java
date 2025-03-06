package kamianowski.maciej.demo.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Branch {
	@JsonProperty("name")
    private String branchName;

    public Branch(String branchName) {
        this.branchName = branchName;
    }

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
}
