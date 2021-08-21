package TeamProjectWithDemo;

import java.io.Serializable;
import java.util.*;

/**
 * 
 * @author Brittany Pruneau and Daniel Musser 
 *
 */
public class Company implements Serializable
{
	private String companyName;
	private List<Member> members = new ArrayList<>();
	private Enum ManagedBy;

	/**
	 * Creates a new company, which includes the company name and a list of company
	 * members.
	 * 
	 * @param companyName Defines the company name.
	 * @param members     Holds a list of members in the company.
	 */
	public Company(String companyName, List<Member> members, Enum managementStyle)
	{
		this.companyName = companyName;
		this.members = members;
		this.ManagedBy = managementStyle;
	}

	/**
	 * Gets a list of members of the company.
	 */
	public List<Member> getMembers()
	{
		return members;
	}

	/**
	 * Adds a new member to the company.
	 */
	public void AddNewMember(Member member)
	{
		members.add(member);
	}

	/**
	 * Gets the company name.
	 */
	public String getCompanyName()
	{
		return companyName;
	}

	/**
	 * Sets the company name, if the company changes its name.
	 */
	public void setCompanyName(String companyName)
	{
		this.companyName = companyName;
	}

	/**
	 * Retrieves one of two different LLC management types
	 */
	public Enum getManagementStyle()
	{
		return ManagedBy;
	}
}
