package kamianowski.maciej.demo.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AuthMsg {
	
	@JsonProperty("name")
    private String repository;

    @JsonProperty("owner")
    private Owner owner;

    @JsonProperty("branches")
    private List<Branch> branches;
    
    @JsonProperty("fork")
    private boolean isFork;
    
    public AuthMsg(String repository, Owner owner, List<Branch> branches, boolean isFork) {
		this.repository = repository;
		this.owner = owner;
		this.branches = branches;
		this.isFork = isFork;
	}

	public static class Owner {
		@JsonProperty("login")
        private String ownerName; 

		public String getOwnerName() {
			return ownerName;
		}

		public void setOwnerName(String ownerName) {
			this.ownerName = ownerName;
		}

		public Owner(String ownerName) {
			this.ownerName = ownerName;
		} 
    }

	public String getRepository() {
		return repository;
	}

	public void setRepository(String repository) {
		this.repository = repository;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public boolean isFork() {
		return isFork;
	}

	public void setFork(boolean isFork) {
		this.isFork = isFork;
	}

	public List<Branch> getBranches() {
		return branches;
	}

	public void setBranches(List<Branch> branches) {
		this.branches = branches;
	}	
}
