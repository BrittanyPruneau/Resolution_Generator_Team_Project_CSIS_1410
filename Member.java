package TeamProjectWithDemo;

import java.io.Serializable;

/**
 * Holds information about members such as name, ownership, and ownership percentage. 
 * 
 * @author Daniel Musser & Brittany Pruneau
 * 
 */
public class Member implements Serializable
{
	private String name; 
	private boolean isManager; 
	private boolean isOwner;
	private int ownershipPercentage;
	
	/**
	 * Creates a new member; which includes the members name, if they are a manager or not, 
	 * an owner or not, and their percentage of ownership. 
	 * 
	 * @param name 					The name of the member. 
	 * @param isManager				Is the member a Manager.
	 * @param isOwner				Is the member an owner.
	 * @param ownershipPercenage	Percentage of ownership of the company. 
	 */
	public Member(String name, boolean isManager, boolean isOwner, int ownershipPercenage)
	{
		this.name = name;
		this.isManager = isManager;
		this.isOwner = isOwner;
		this.ownershipPercentage = ownershipPercenage;
	}

	/**
	 * Gets the name of the member. 
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name the name of the member.
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * Lets the user know if the member is a manager or not. 
	 */
	public boolean isManager()
	{
		return isManager;
	}

	/**
	 * Sets the member as a manager, should this field change. 
	 */
	public void setManager(boolean isManager)
	{
		this.isManager = isManager;
	}

	/**
	 * Lets the user know if the member is an owner or not. 
	 */
	public boolean isOwner()
	{
		return isOwner;
	}

	/**
	 * Sets the member as an owner, should this field change. 
	 */
	public void setOwner(boolean isOwner)
	{
		this.isOwner = isOwner;
	}

	/**
	 * Lets the user know the percentage owned by this member. 
	 */
	public int getOwnershipPercentage()
	{
		return ownershipPercentage;
	}

	/**
	 * Set the percentage owned by this member. 
	 */
	public void setOwnershipPercentage(int ownershipPercentage)
	{
		this.ownershipPercentage = ownershipPercentage;
	} 
}
