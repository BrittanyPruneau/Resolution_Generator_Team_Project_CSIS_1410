package TeamProjectWithDemo;

import java.util.ArrayList;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

/**
 * Generates LLC resolution wording and can create a saved file with that
 * resolution in it
 *
 * @author Daniel Musser & Brittany Pruneau
 * 
 */
public class ResolutionGenerator
{
	private Company company;
	private final String signatureLine;
	private String nameOfLLC;
	private List<Member> members;
	private String managedBy;
	private List<String> authorizedReps;

	/**
	 * Creates an object for ResolutionGenerator to hold information about a
	 * company, its members, and their information.
	 */
	public ResolutionGenerator(Company company)
	{
		this.company = company;

		// Separating out the properties of company for convenience
		nameOfLLC = convertToTitleCase(company.getCompanyName());
		members = company.getMembers();
		managedBy = company.getManagementStyle().toString().toLowerCase();
		authorizedReps = createArrayOfAuthorizedReps();
		signatureLine = "______________________________";

		// We don't want individuals who aren't members to be in the members list
		members.removeIf(member -> !member.isOwner());
	}
	
	/**
	 * Generates the string which can be written to a file or copied from the main
	 * interface.
	 */
	public String generateString()
	{
		LocalDate date = LocalDate.now();

		// extra spacing lines have been added to the string below to improve
		// readability
		String resolutionContent = singleOrPlural() + ", " + createMembersOfTheFirstSentence() + ", " + "the "
				+ decideWhetherMembersHasS() + " of " + nameOfLLC
				+ ", a limited liability company organized and existing under the laws of the State of Utah, hereby certify:"
				+

				"\n\n(I) that " + nameOfLLC + " is managed by its " + managedBy + ";" +

				"\n(II) that the signers below are all current members of the LLC; and the current ownership is as follows:\n\n"
				+

				"% Ownership\t\t\tName\n" + createMemberOwnershipList() +

				"\n(III) that " + createAuthorizedRepGroupWording() + decideOnIsOrAreWording()
				+ "authorized to sign all related loan documents for the LLC; and" +

				"\n\n(IV) that no Operating Agreement exists for the company." +

				"\n\nIN WITNESS WHEREOF, the undersigned has affixed his/her signature this " + date.getDayOfMonth()
				+ " of " + date.getMonth() + ", " + date.getYear() + "." +

				"\n\n\n\n\n" + createSignatureLines();

		return resolutionContent;
	}

	/*
	 * Creates the proper title casing. 
	 */
	private String convertToTitleCase(String inputString)
	{
		if (inputString.isEmpty())
		{
			return "";
		}

		String[] lowerCaseString = inputString.toLowerCase().split(" ");

		for (int i = 0; i < lowerCaseString.length; i++)
		{
			if (lowerCaseString[i].equals("llc") || lowerCaseString[i].equals("lc"))
			{
				lowerCaseString[i] = lowerCaseString[i].toUpperCase();
			} else
			{
				lowerCaseString[i] = lowerCaseString[i].substring(0, 1).toUpperCase() + lowerCaseString[i].substring(1);
			}
		}

		String convertedString = String.join(" ", lowerCaseString);
		return convertedString;
	}

	/*
	 * Decides if the company is more than one person, and if so use the string "we" or "I" if owned by one person. 
	 */
	private String singleOrPlural()
	{
		if (members.size() > 1)
		{
			return "We";
		} else
		{
			return "I";
		}
	}

	/*
	 * Creates member ownership list. 
	 */
	private String createMemberOwnershipList()
	{
		String theMemberOwnershipList = "";
		for (Member member : members)
		{
			theMemberOwnershipList += member.getOwnershipPercentage() + "\t\t\t" + convertToTitleCase(member.getName())
					+ "\n";
		}

		return theMemberOwnershipList;
	}

	/*
	 * Generates the proper wording, "is" or "are" depending on the number of owners.
	 */
	private String decideOnIsOrAreWording()
	{
		if (authorizedReps.size() > 1)
		{
			return " are the LLC " + managedBy + " of " + nameOfLLC + ", who are ";
		} else
		{
			return " is the LLC " + managedBy.substring(0, managedBy.length() - 1) + " of " + nameOfLLC + ", who is ";
		}
	}

	/*
	 * Creates a list of authorized representatives.
	 */
	private List<String> createArrayOfAuthorizedReps()
	{
		List<String> authorizedReps = new ArrayList<>();

		for (Member member : members)
		{
			if (managedBy.equals("members") || member.isManager() == true)
			{
				authorizedReps.add(convertToTitleCase(member.getName()));
			}
		}
		return authorizedReps;
	}

	/*
	 * Generates the proper wording for authorized representatives depending upon the number of representatives.  
	 */
	private String createAuthorizedRepGroupWording()
	{
		String authorizedRepString = "";

		if (authorizedReps.size() < 2)
		{
			authorizedRepString = authorizedReps.get(0);
			return authorizedRepString;
		}

		for (String rep : authorizedReps)
		{
			int lastRep = authorizedReps.size() - 1;

			if (rep.equals(authorizedReps.get(lastRep)))
			{
				authorizedRepString += ", and " + rep;
			} else if (rep.equals(authorizedReps.get(0)))
			{
				authorizedRepString += rep;
			} else
			{
				authorizedRepString += ", " + rep;
			}
		}
		return authorizedRepString;
	}

	/*
	 * Generates the signature lines. 
	 */
	private String createSignatureLines()
	{
		String stringToReturn = "";

		for (Member member : members)
		{
			stringToReturn += "\t\t\t\t\t" + signatureLine + "\n\t\t\t\t\t" + convertToTitleCase(member.getName())
					+ "\n\n\n\n\n";
		}
		return stringToReturn;
	}

	/*
	 * Creates the first sentence which includes the members of the company. 
	 */
	private String createMembersOfTheFirstSentence()
	{
		String memberString = "";
		final int numberOfMembers = members.size();

		if (numberOfMembers < 2)
		{
			return members.get(0).getName();
		}

		for (int i = 0; i < numberOfMembers; i++)
		{
			if (i == numberOfMembers - 1)
			{
				memberString += "and " + members.get(i).getName();
			} else
			{
				memberString += "" + members.get(i).getName() + ", ";
			}
		}

		return memberString;
	}

	/*
	 * Returns "members" or "sole member" depending on the number of members. 
	 */
	private String decideWhetherMembersHasS()
	{
		if (members.size() > 1)
		{
			return "members";
		} else
		{
			return "sole member";
		}
	}
}
