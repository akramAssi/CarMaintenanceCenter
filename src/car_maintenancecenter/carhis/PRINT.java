/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package car_maintenancecenter.carhis;

import car_maintenancecenter.Car_MaintenanceCenter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
 
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.StageStyle;

public class PRINT 
{
     PRINT()throws IOException, DocumentException
     {
         try {
             String dest="history "+Car_MaintenanceCenter.x+".pdf";
             Document document = new Document();
             Date dNow = new Date( );
             String checkstmt;PreparedStatement stmt;ResultSet rs;
             SimpleDateFormat ft =new SimpleDateFormat ("E yyyy.MM.dd 'at' hh:mm");
             BaseFont bf = BaseFont.createFont(BaseFont.TIMES_ROMAN,BaseFont.CP1252,BaseFont.EMBEDDED);
             Font font = new Font(bf, 11);
             
             
             BaseFont bf1 = BaseFont.createFont(BaseFont.TIMES_ROMAN,BaseFont.CP1252,BaseFont.EMBEDDED);
             Font font1 = new Font(bf1, 18);
             
             PdfWriter writer= PdfWriter.getInstance(document, new FileOutputStream(dest));
             document.open();
             Rectangle rect = new Rectangle(30, 30, 550, 800);
             writer.setBoxSize("art", rect);
             ColumnText.showTextAligned(writer.getDirectContent(),Element.ALIGN_CENTER, new Phrase(ft.format(dNow),new Font(bf, 8)), rect.getRight()-30, rect.getTop(), 0);
             
             document.add( Chunk.NEWLINE );
             Paragraph paragraph1 = new Paragraph(new Phrase("Car_MaintenanceCenter",font1));
             paragraph1.setAlignment(Paragraph.TITLE);
             document.add(paragraph1);
             String ldate="";
             checkstmt = "select  MAX(accessdate) from history1 where CAR_VIN=?";
             stmt = Car_MaintenanceCenter.connection.prepareStatement(checkstmt);
             stmt.setString(1,Car_MaintenanceCenter.x );
             rs = stmt.executeQuery();
             if (rs.next())
             {
                 ldate=rs.getString(1);
             }
             
             String sdate="";
             checkstmt = "select  min(accessdate) from history1 where CAR_VIN=?";
             stmt = Car_MaintenanceCenter.connection.prepareStatement(checkstmt);
             stmt.setString(1,Car_MaintenanceCenter.x );
             rs = stmt.executeQuery();
             if (rs.next())
             {
                 sdate=rs.getString(1);
             }
             
             Paragraph paragraph2 = new Paragraph(new Phrase("Register the vehicle from "+sdate+" to "+ldate,new Font(bf, 13)));
             paragraph2.setAlignment(Paragraph.ALIGN_CENTER);
             document.add(paragraph2);
             
             document.add( Chunk.NEWLINE );
             
             String para1 = "";
             String vin = null,manu = null,model = null,plate = null,fule = null,year = null;
             
             checkstmt = "select  * from car1  where VIN = ? ";
             stmt = Car_MaintenanceCenter.connection.prepareStatement(checkstmt);
             stmt.setString(1,Car_MaintenanceCenter.x );
             rs = stmt.executeQuery();
             if (rs.next())
             {
                 vin=rs.getNString(1);
                 manu=rs.getNString(3);
                 plate=rs.getNString(2);
                 model=rs.getNString(4);
                 fule=rs.getNString(5);
                 year=rs.getNString(6);
                 
             }
             else{return;}
             
             
             PdfPTable table1 = new PdfPTable(6);
             PdfPCell h1 = new PdfPCell(new Phrase("vin :",font));
             h1.setBorder(Rectangle.NO_BORDER);
             table1.addCell(h1);
             PdfPCell h2 = new PdfPCell(new Phrase(vin,font));
             h2.setBorder(Rectangle.NO_BORDER);
             table1.addCell(h2);
             PdfPCell h3 = new PdfPCell(new Phrase("MANUFACTURE",new Font(bf, 10)));
             h3.setBorder(Rectangle.NO_BORDER);
             table1.addCell(h3);
             PdfPCell h4 = new PdfPCell(new Phrase(manu,font));
             h4.setBorder(Rectangle.NO_BORDER);
             table1.addCell(h4);
             PdfPCell h5 = new PdfPCell(new Phrase("MODEL",font));
             h5.setBorder(Rectangle.NO_BORDER);
             table1.addCell(h5);
             PdfPCell h6 = new PdfPCell(new Phrase(model,font));
             h6.setBorder(Rectangle.NO_BORDER);
             table1.addCell(h6);
             PdfPCell h7 = new PdfPCell(new Phrase("fuel type",font));
             h7.setBorder(Rectangle.NO_BORDER);
             table1.addCell(h7);
             PdfPCell h8 = new PdfPCell(new Phrase(fule,font));h8.setBorder(Rectangle.NO_BORDER);
             table1.addCell(h8);
             PdfPCell h9 = new PdfPCell(new Phrase("year",font));h9.setBorder(Rectangle.NO_BORDER);
             table1.addCell(h9);
             PdfPCell h10 = new PdfPCell(new Phrase(year,font));h10.setBorder(Rectangle.NO_BORDER);
             table1.addCell(h10);
             PdfPCell h11 = new PdfPCell(new Phrase("plate number",font));h11.setBorder(Rectangle.NO_BORDER);
             table1.addCell(h11);
             PdfPCell h12 = new PdfPCell(new Phrase(plate,font));h12.setBorder(Rectangle.NO_BORDER);
             table1.addCell(h12);
             table1.setSpacingAfter(30);
             table1.setHorizontalAlignment(Element.ALIGN_MIDDLE);
             table1.setWidths(new float[] { 15f, 17f,22f,20f,20f,15f });
             
             
             
             document.add(table1);
             
             // Creating Paragraphs
//      Paragraph paragraph1 = new Paragraph(para1);
//      document.add(paragraph1);
document.setMarginMirroring(true);
PdfPTable table = new PdfPTable(5);

table.addCell("date");
table.addCell("Customer description");
table.addCell("Technical description");
table.addCell("type");
table.addCell("kilo");

Font ff=new Font(bf, 8);
             checkstmt = "select h.ACCESSDATE ,h.DESOWNER, h.DESTECH,h.TYPEMN,h.KILOMETER from history1 h where h.CAR_VIN = ? and h.DATADELV is not null order by  h.ACCESSDATE";
             stmt = Car_MaintenanceCenter.connection.prepareStatement(checkstmt);
             stmt.setString(1,Car_MaintenanceCenter.x );
             rs = stmt.executeQuery();
             while (rs.next())
             {
                 table.addCell(new Phrase(rs.getNString(1),ff));
                 table.addCell(new Phrase(rs.getNString(2),ff));
                 table.addCell(new Phrase(rs.getNString(3),ff));
                 table.addCell(new Phrase(rs.getNString(4),ff));
                 table.addCell(new Phrase(rs.getNString(5),ff));
                 
                 
             }

//        table.addCell("tech desc2");
//        table.addCell("desc owner3");
//        table.addCell("tech desc4");
//        table.addCell("desc owner5");
//        table.addCell("tech desc6");
//        table.addCell("desc owner7");
//        table.addCell("tech desc8");
//        table.addCell("tech desc9");

//        table.getDefaultCell().getPhrase().setFont(font);
table.setWidths(new float[] { 15f, 35f,35f,8f,10f });
table.setWidthPercentage(100f);
document.add(table);

Image waterMarkImage = Image.getInstance("src/images/logo.jpg");
                  
                  //Get width and height of whole page
                  float pdfPageWidth = document.getPageSize().getWidth();
                  float pdfPageHeight = document.getPageSize().getHeight();
 
                  //Set waterMarkImage on whole page
                  writer.getDirectContentUnder().addImage(waterMarkImage,
                               pdfPageWidth, 0, 0, pdfPageHeight, 0, 0);
document.close(); File file = new File(dest);
        
        //first check if Desktop is supported by Platform or not
        if(!Desktop.isDesktopSupported()){
            System.out.println("Desktop is not supported");
            return;
        }
        
        Desktop desktop = Desktop.getDesktop();
        if(file.exists()) desktop.open(file);
         } catch (SQLException ex) {
             Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setContentText(ex.getMessage());
            errorAlert.initStyle(StageStyle.TRANSPARENT);
            errorAlert.initModality(Modality.APPLICATION_MODAL);
            errorAlert.setResizable(false);
            errorAlert.initStyle(StageStyle.UNDECORATED);
            errorAlert.showAndWait();
         }
         
         
     }
     
     
}
