package kamianowski.maciej.demo.rest;

import com.google.gson.annotations.SerializedName;


import java.util.List;

public class AuthMsg {
	
    @SerializedName("name")
    private String repository; // Repository name

    @SerializedName("owner")
    private Owner owner; // Nested owner object

    @SerializedName("fork")
    private boolean isFork; // Whether the repository is a fork
    
    private List<Branch> branches; // List of branches (not directly in the JSON, needs to be fetched separately)

    public static class Owner {
        @SerializedName("login")
        private String ownerName; // Owner's login/username

		public String getOwnerName() {
			return ownerName;
		}

		public void setOwnerName(String ownerName) {
			this.ownerName = ownerName;
		}
        
    }
//
//    public static class Branch {
//        @SerializedName("name")
//        private String branchName; // Branch name
//    }

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



//@Getter
//@Setter
//public class AuthMsg {
//	//"name" which is in json what we get from url
//	@SerializedName("name")
//	private String repository;
//
//	@SerializedName("name")
//	private String owner;
//	/**
//	 * @return the repository
//	 */
////	public String getRepository() {
////	    return repository;
////	}
////
////	/**
////	 * @param repository the repository to set
////	 */
////	public void setRepository(String repository) {
////	    this.repository = repository;
////	}
//	
//	
//	
//
//}
