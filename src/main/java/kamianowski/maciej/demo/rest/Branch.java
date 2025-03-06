package kamianowski.maciej.demo.rest;

import com.google.gson.annotations.SerializedName;

public class Branch {
	@SerializedName("name")
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
