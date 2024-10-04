package com.nt.service;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.nt.binding.CoTriggersOutput;
import com.nt.entity.CitizenAppRegistartionEntity;
import com.nt.entity.CoTriggersEntity;
import com.nt.entity.DcCasesEntity;
import com.nt.entity.ElgibilityDetailsEntity;
import com.nt.repo.ICitizenAppRepo;
import com.nt.repo.ICoTriggersRepo;
import com.nt.repo.IDcCaseRepo;
import com.nt.repo.IElgibilityDetailsRepo;
import com.nt.utils.EmailUtils;
@Service
public class CoTriggersImpl implements ICoTriggersService {

	@Autowired
	private ICoTriggersRepo ctRepo;
	@Autowired
	private IDcCaseRepo caseRepo;
	@Autowired
	private IElgibilityDetailsRepo elgRepo;
	@Autowired
	private ICitizenAppRepo citizenRepo;
	
	@Autowired
	private EmailUtils email;
	
	int successTriggers = 0;
	int pendingTriggers = 0;
	/*
	 * @Override public CoTriggersOutput checkTriggers() throws Exception {
	 * CitizenAppRegistartionEntity citzenEntity = null; ElgibilityDetailsEntity
	 * elgiEntity = null; int successTriggers = 0; int pendingTriggers = 0; //get
	 * the list of triggers entity List<CoTriggersEntity> list =
	 * ctRepo.findByTriggerStatus("pending"); for(CoTriggersEntity cot:list) {
	 * elgiEntity = elgRepo.findByCaseNo(cot.getCaseNo()); //get the dc case object
	 * with help of the obj property appid e we will get the citizen obj
	 * Optional<DcCasesEntity> caseEntity = caseRepo.findById(cot.getCaseNo());
	 * if(caseEntity.isPresent()) { DcCasesEntity dc = caseEntity.get(); Integer
	 * appId = dc.getAppId(); //with the help of the appsi we will get the
	 * citizenentity object Optional<CitizenAppRegistartionEntity> car =
	 * citizenRepo.findById(appId); if(car.isPresent()) { citzenEntity = car.get();
	 * } }//if
	 * 
	 * //generate a pdf document having the eligible entity details data and send
	 * the pdf attachment to the respective user
	 * 
	 * try { generatePdfDoc(citzenEntity,elgiEntity); successTriggers++; } catch
	 * (Exception e) { pendingTriggers++; e.printStackTrace(); } }//for
	 * 
	 * CoTriggersOutput out = new CoTriggersOutput();
	 * out.setPendingTriggers(pendingTriggers);
	 * out.setSuccessTriggers(successTriggers); out.setTotalTriggers(list.size());
	 * return out; }
	 */
	
	@Override
	public CoTriggersOutput checkTriggers() {
		//get the list of triggers entity
		List<CoTriggersEntity> list = ctRepo.findByTriggerStatus("pending");
		//prepare CoOutput Object
		CoTriggersOutput out = new CoTriggersOutput();
		out.setTotalTriggers(list.size());
		
		//process the triggers in multi threading environment using executor framework
		ExecutorService exeSer = Executors.newFixedThreadPool(5);
		ExecutorCompletionService<Object> pool = new ExecutorCompletionService<Object>(exeSer);
		//process the each trigger pending
		for(CoTriggersEntity entity : list) {
			pool.submit(()->{
				try {
					processTriggers( entity);
					successTriggers++;
				} catch (Exception e) {
					e.printStackTrace();
					pendingTriggers++;
				}
				return null;
			});
		}//for
		
		out.setSuccessTriggers(successTriggers);
		out.setPendingTriggers(pendingTriggers);

		return out;
	}
	
	private CitizenAppRegistartionEntity processTriggers(CoTriggersEntity entity) throws Exception {
		CitizenAppRegistartionEntity citzenEntity = null;
		//get the list of triggers entity
			ElgibilityDetailsEntity elgiEntity = elgRepo.findByCaseNo(entity.getCaseNo());
			//get the dc case object with help of the obj property appid e we will get the citizen obj
			Optional<DcCasesEntity> caseEntity = caseRepo.findById(entity.getCaseNo());
			if(caseEntity.isPresent()) {
				DcCasesEntity dc = caseEntity.get();
				Integer appId = dc.getAppId();
				//with the help of the appsi we will get the citizenentity object
				Optional<CitizenAppRegistartionEntity> car = citizenRepo.findById(appId);
				if(car.isPresent()) {
					citzenEntity = car.get();
				}
			}
			//generate a pdf document having the eligible entity details data and send the pdf attachment to the respective user
			generatePdfDoc(citzenEntity, elgiEntity);
			return citzenEntity;
	}
	
	
	private void generatePdfDoc(CitizenAppRegistartionEntity citzenEntity,ElgibilityDetailsEntity elgiEntity) throws Exception {
		 //create document obj(OpenPdf)
		Document doc = new Document(PageSize.A4);
		//create a file write content to it
		File file = new File(elgiEntity.getCaseNo()+".pdf");
		FileOutputStream fos = new FileOutputStream(file);
		//get pdf writer to write to the document and response object
		PdfWriter.getInstance(doc, fos);
		//open the document
		doc.open();
		 // Setting up the font
		 // Setting up the custom font using OpenPDF
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(16);
        font.setColor(Color.RED);
		
		//create the para having content and above font style
		Paragraph para = new Paragraph(" Search Report of Course",font);
		para.setAlignment(Paragraph.ALIGN_CENTER);
		//add para to the document
		doc.add(para);
		
		//dispaly the search results as pdf table
		PdfPTable tab= new PdfPTable(10);
		float[] columnWidths = {3f, 3f, 3f, 3f, 3f, 3f, 3f, 3f, 3f, 3f};
        tab.setWidths(columnWidths);

		
		//prepare heading row cells int he pdf table
		PdfPCell cell= new PdfPCell();
		cell.setBackgroundColor(Color.GRAY);
		cell.setPadding(5);
		Font cellFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		cellFont.setColor(Color.BLACK);
		
		
		cell.setPhrase(new Phrase("EdId",cellFont));
		tab.addCell(cell);
		cell.setPhrase(new Phrase("CaseNo",cellFont));
		tab.addCell(cell);
		cell.setPhrase(new Phrase("HolderName",cellFont));
		tab.addCell(cell);
		cell.setPhrase(new Phrase("HolderSsn",cellFont));
		tab.addCell(cell);
		cell.setPhrase(new Phrase("PlanName",cellFont));
		tab.addCell(cell);
		cell.setPhrase(new Phrase("PlanStatus",cellFont));
		tab.addCell(cell);
		cell.setPhrase(new Phrase("BenfitAmount",cellFont));
		tab.addCell(cell);
		cell.setPhrase(new Phrase("DenialReason",cellFont));
		tab.addCell(cell);
		cell.setPhrase(new Phrase("PlanStartDate",cellFont));
		tab.addCell(cell);
		cell.setPhrase(new Phrase("PlanEndDate",cellFont));
		tab.addCell(cell);
		
		
		//add data cells to pdf cells
			tab.addCell(String.valueOf(elgiEntity.getEdId()));
			tab.addCell(String.valueOf(elgiEntity.getCaseNo()));
			tab.addCell(elgiEntity.getHolderName());
			tab.addCell(String.valueOf(elgiEntity.getHolderSsn()));
			tab.addCell(elgiEntity.getPlanName());
			tab.addCell(elgiEntity.getPlanStatus());
			tab.addCell(String.valueOf(elgiEntity.getBenfitAmount()));
			tab.addCell(elgiEntity.getDenialReason());
			tab.addCell(String.valueOf(elgiEntity.getPlanStartDate()));
			tab.addCell(String.valueOf(elgiEntity.getPlanEndDate()));
		
		//add table to document
		doc.add(tab);
		doc.close();
		//send the pdf to the custerms mail
		String subject = " Plan Approval/denial mail";
		String body = " Hello Mr/Mrs/Miss . "+citzenEntity.getFullName()+" This mail contains complete details about Insurance Policy you appied in the ISH Portal";
		email.senMail(citzenEntity.getEmail(), subject, body,file);
		updateTrigger(elgiEntity.getCaseNo(),file);
	}
	
	
	private void updateTrigger(Integer caseno,File file) throws Exception {
		CoTriggersEntity co = ctRepo.findByCaseNo(caseno);
		byte[] pdfContent = new byte[(int) file.length()];
		FileInputStream fis = new FileInputStream(file);
				fis.read(pdfContent);
		if(co!=null) {
			co.setCoNoticePdf(pdfContent);
			co.setTriggerStatus("completed");
			ctRepo.save(co);
		}
		fis.close();
	}

}
