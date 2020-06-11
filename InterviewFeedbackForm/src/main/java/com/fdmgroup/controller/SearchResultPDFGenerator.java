package com.fdmgroup.controller;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Stream;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fdmgroup.model.SearchCriteria;
import com.fdmgroup.model.SearchResult;
import com.fdmgroup.model.Tag;
import com.fdmgroup.model.Trainer;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class SearchResultPDFGenerator {
	private static Logger logger = LoggerFactory.getLogger(SearchResultPDFGenerator.class);
	  

	  public static ByteArrayInputStream searchResultPDFReport(SearchResult searchResult, SearchCriteria searchCriteria){
	    Document document = new Document();
	        ByteArrayOutputStream out = new ByteArrayOutputStream();
	        
	        try {
	          
	          PdfWriter.getInstance(document, out);
	            document.open();
	          
	            // Add Text to PDF file ->
	          Font font = FontFactory.getFont(FontFactory.COURIER, 12, BaseColor.BLACK);
	          Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
	          //Add title line
	          Paragraph title = new Paragraph( "The Search crateria is:", headFont);
	          title.setAlignment(Element.ALIGN_LEFT);
	          Paragraph p1 = new Paragraph( "Client Name : " +searchCriteria.getClientName(), font);
	          p1.setAlignment(Element.ALIGN_LEFT);
	          Paragraph p2 = new Paragraph( "Client Division : "+ searchCriteria.getClientDivision(), font);
	          p2.setAlignment(Element.ALIGN_LEFT);
	          Paragraph p3 = new Paragraph( "Tag Name : "+ searchCriteria.getTagName(), font);
	          p2.setAlignment(Element.ALIGN_LEFT);
	          Paragraph p4 = new Paragraph( "Question : "+ searchCriteria.getQuestionBody(), font);
	          p2.setAlignment(Element.ALIGN_LEFT);
	          Paragraph p5 = new Paragraph( "Role Name : "+ searchCriteria.getRoleName(), font);
	          p2.setAlignment(Element.ALIGN_LEFT);
	          Paragraph p6 = new Paragraph( "Sort by : " + searchCriteria.getSortResult(), font);
	          p2.setAlignment(Element.ALIGN_LEFT);
	          //add text to the document
	          document.add(title);
	          document.add(p1);	   
	          document.add(p2);	 
	          document.add(p3);	 
	          document.add(p4);	 
	          document.add(p5);	 
	          document.add(p6);	 
	          //add a line of space
	          document.add(Chunk.NEWLINE);
	          
	          PdfPTable table = new PdfPTable(5);
	          // Add PDF Table Header ->
	            Stream.of("Client", "Client Division", "Tag(s)","Role","Question")
	              .forEach(headerTitle -> {
	                  PdfPCell header = new PdfPCell();	                
	                  header.setBackgroundColor(BaseColor.LIGHT_GRAY);
	                  header.setHorizontalAlignment(Element.ALIGN_CENTER);
	                  header.setBorderWidth(1);
	                  header.setPhrase(new Phrase(headerTitle, headFont));
	                  table.addCell(header);
	              });
	            	          
	            for( int i=0; i<searchResult.getResultQuestions().size(); i++) {
	            	//1st column
	            	PdfPCell clientCell = new PdfPCell(new Phrase(searchResult.getResultClients().get(i)));
	            	clientCell.setPaddingLeft(1);
	            	clientCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	            	clientCell.setHorizontalAlignment(Element.ALIGN_CENTER);	            	
		            table.addCell(clientCell);	
		            //2nd column
	            	PdfPCell clientDivisionCell = new PdfPCell(new Phrase(searchResult.getResultDivisions().get(i)));
	            	clientDivisionCell.setPaddingLeft(1);
	            	clientDivisionCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	            	clientDivisionCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		            table.addCell(clientDivisionCell);	
		            //3rd column
		            String tagCellContent="";
		            for(Tag eachTag : searchResult.getResultTags().get(i)) {
		            	tagCellContent += eachTag.getTagName() + " ";
		            }
	            	PdfPCell tagCell = new PdfPCell(new Phrase(tagCellContent));
	            	tagCell.setPaddingLeft(1);
	            	tagCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	            	tagCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		            table.addCell(tagCell);	
		            //4th column
		            PdfPCell roleCell = new PdfPCell(new Phrase(searchResult.getResultRoles().get(i)));
		            roleCell.setPaddingLeft(1);
		            roleCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		            roleCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		            table.addCell(roleCell);
		            //5th column
		            PdfPCell questionCell = new PdfPCell(new Phrase(searchResult.getResultQuestions().get(i).getQuestionBody()));
		            questionCell.setPaddingLeft(4);
		            questionCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		            questionCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		            table.addCell(questionCell);
		                       
	            }
	            
	            document.add(table);
	            
	            document.close();
	        }catch(DocumentException e) {
	          logger.error(e.toString());
	        }
	        
	    return new ByteArrayInputStream(out.toByteArray());
	  }

	
}
