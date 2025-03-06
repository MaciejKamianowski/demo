package kamianowski.maciej.demo.rest;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RepositoryDto {

	@JsonProperty("repositoryName")
	private String repositoryName;
	@JsonProperty("ownerName")
    private String ownerName;
	@JsonProperty("branches")
    private List<Branch> branches;

	public RepositoryDto() {}

	public RepositoryDto(String repository, String ownerName, List<Branch> branches) {
		this.repositoryName = repository;
		this.ownerName = ownerName;
		this.branches = branches;
	}

	public String getRepositoryName() {
		return repositoryName;
	}

	public void setRepositoryName(String repositoryName) {
		this.repositoryName = repositoryName;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public List<Branch> getBranches() {
		return branches;
	}

	public void setBranches(List<Branch> branches) {
		this.branches = branches;
	}
	
}
