package TeamProjectWithDemo;

import java.io.*;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * Handles the Serialization and Deserialization of Company Objects as well as
 * some folder creation logic
 *
 * @author Daniel Musser & Brittany Pruneau
 */
public class FileHandler
{
	/**
	 * Checks to make sure that the save directory exists and creates it if it
	 * didn't
	 */
	public static void checkForSaveDirectory()
	{
		File folderWeNeed = new File("savedCompanies");
		if (!folderWeNeed.exists())
			folderWeNeed.mkdir();
	}

	/*
	 * Saves the resolution as a PDF. 
	 */
	public static void saveAsPDF(String resolution, String nameOfCompany, String fileName)
	{
		// String fileName = "C:\\Users\\prune\\Desktop\\PDF_Files\\LLCResolution3.pdf";

		Document document = new Document();

		try
		{
			Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
			Font smallCatFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
			PdfWriter.getInstance(document, new FileOutputStream(fileName));
			document.open();
			Paragraph para = new Paragraph(resolution, smallCatFont);

			Paragraph preface = new Paragraph();
			Paragraph preface2 = new Paragraph();
			Paragraph preface3 = new Paragraph(" ");

			preface.add(new Paragraph("Certified Resolution of", catFont));
			preface2.add(new Paragraph(nameOfCompany, catFont));
			preface2.setAlignment(Element.ALIGN_CENTER);
			preface.setAlignment(Element.ALIGN_CENTER);

			document.add(preface);
			document.add(preface2);
			document.add(preface3);
			document.add(para);
			document.close();
			System.out.println("finished");
		} catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Reads in a serialized Company object and returns it
	 *
	 * @param chosenFile the path to the serialized object
	 * @return the deserialized Company Object
	 */
	public static Company deserializeCompany(File chosenFile)
	{
		try (FileInputStream fileInputStream = new FileInputStream(chosenFile);
				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream))
		{
			return (Company) objectInputStream.readObject();
		} 
		catch (IOException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Writes a serialized Company object to the hard drive
	 *
	 * @param chosenFile         the path to the serialized object
	 * @param companyToSerialize the company object to write to the hard drive
	 */
	public static void serializeCompany(String chosenFile, Company companyToSerialize)
	{
		try (FileOutputStream fileOutputStream = new FileOutputStream(chosenFile);
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream))
		{
			objectOutputStream.writeObject(companyToSerialize);
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
