package kamianowski.maciej.demo.rest;

import java.util.List;

public class RepositoryDto {

	private String repositoryName;
    private String ownerName;
    private List<Branch> branches;

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

	@Override
    public String toString() {
        return "RepositoryDto{" +
                "repositoryName='" + repositoryName + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", branches=" + branches +
                '}';
    }

}
