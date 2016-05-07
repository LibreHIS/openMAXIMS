// This code was generated by Cornel Ventuneac using IMS Development Environment (version 1.71 build 3742.24882)
// Copyright (C) 1995-2010 IMS MAXIMS. All rights reserved.

package ims.RefMan.domain.impl;

import java.util.ArrayList;
import java.util.List;

import ims.RefMan.domain.base.impl.BasePrintPrescriptionsDialogImpl;
import ims.RefMan.domain.objects.CatsReferral;
import ims.RefMan.domain.objects.Prescription;
import ims.RefMan.domain.objects.ReferralOutcome;
import ims.RefMan.vo.CatsReferralRefVo;
import ims.RefMan.vo.CatsReferralReportsVo;
import ims.RefMan.vo.PrescriptionsVo;
import ims.RefMan.vo.PrescriptionsVoCollection;
import ims.RefMan.vo.ReferralOutcomeVo;
import ims.RefMan.vo.domain.CatsReferralReportsVoAssembler;
import ims.RefMan.vo.domain.PrescriptionsVoAssembler;
import ims.RefMan.vo.domain.ReferralOutcomeVoAssembler;
import ims.core.documents.domain.objects.PatientDocument;
import ims.core.vo.PatientDocumentVo;
import ims.core.vo.domain.PatientDocumentVoAssembler;
import ims.domain.DomainFactory;
import ims.domain.exceptions.DomainRuntimeException;
import ims.domain.exceptions.StaleObjectException;
import ims.framework.enumerations.SortOrder;
import ims.framework.exceptions.CodingRuntimeException;

public class PrintPrescriptionsDialogImpl extends BasePrintPrescriptionsDialogImpl
{

	private static final long serialVersionUID = 1L;

	/**
	* listPrescriptions
	*/
	public ims.RefMan.vo.PrescriptionsVoCollection listPrescriptions(ims.RefMan.vo.CatsReferralRefVo catsRefVo)
	{
		if(catsRefVo == null || catsRefVo.getID_CatsReferral() == null) {
			throw new CodingRuntimeException("CatsReferralVo is null or id not provided");
		}
		
		DomainFactory factory = getDomainFactory();
		StringBuffer hql = new StringBuffer("select prescriptions from ReferralOutcome as ro join ro.catsReferral as cats join ro.prescriptions as prescriptions ");
		
		ArrayList<String> names = new ArrayList<String>();
		ArrayList<Integer> values = new ArrayList<Integer>();
		
		hql.append("  where cats.id = :idCatsRefferal");
		names.add("idCatsRefferal");
		values.add(catsRefVo.getID_CatsReferral());	
				
		List result = factory.find(hql.toString(),names,values);
						
		if (result != null && result.size() > 0) 
		{
			PrescriptionsVoCollection voColl = PrescriptionsVoAssembler.createPrescriptionsVoCollectionFromPrescription(result);
			if(voColl != null && voColl.size() > 0) 
			{
				voColl.sort(SortOrder.DESCENDING);
				return voColl;
			}						 
		}
		
		return null;
	}

	
	public String[] getSystemReportAndTemplate(Integer imsId) 
	{
		String[] result = null;		
		DomainFactory factory = getDomainFactory();
		
		List lst = factory.find("select r1_1.reportXml, t1_1.templateXml, r1_1.reportName, r1_1.reportDescription, t1_1.name, t1_1.description from ReportBo as r1_1 left join r1_1.templates as t1_1 where (r1_1.imsId= :imsid) order by t1_1.name", new String[] {"imsid"}, new Object[] {imsId});
		
		if(lst.iterator().hasNext())
		{
			Object[] obj = (Object[])lst.iterator().next();
			
			result = new String[] {(String)obj[0], (String)obj[1], (String)obj[2], (String)obj[3], (String)obj[4], (String)obj[5]};
		}
		
		return result;
	}

	
	public CatsReferralReportsVo getCatsReferral(CatsReferralRefVo voCatsRef) 
	{
		return CatsReferralReportsVoAssembler.create((CatsReferral)getDomainFactory().getDomainObject(CatsReferral.class, voCatsRef.getID_CatsReferral()));
	}

	
	public void savePatientDocument(PatientDocumentVo document,
			CatsReferralReportsVo catReferral, ReferralOutcomeVo voOutcome, PrescriptionsVo selectedPrescription)
			throws StaleObjectException 
	{
		if (document != null)
		{
			if (!document.isValidated())
				throw new DomainRuntimeException("PatientDocumentVo not validated");
		}
		
		DomainFactory factory = getDomainFactory();
		
		//WDEV-13956
		Prescription prescription = PrescriptionsVoAssembler.extractPrescription(factory, selectedPrescription);
		factory.save(prescription);
		//end
		
		PatientDocument doc = PatientDocumentVoAssembler.extractPatientDocument(factory, document);
		factory.save(doc);				

		CatsReferral doCatsReferral = CatsReferralReportsVoAssembler.extractCatsReferral(factory, catReferral);
		if (catReferral != null) 
			doCatsReferral.getReferralDocuments().add(doc);						
		
		factory.save(doCatsReferral);
		
		//--------------wdev-14193
		prescription = (Prescription) factory.getDomainObject(Prescription.class, selectedPrescription.getID_Prescription());
		PrescriptionsVo temppre = PrescriptionsVoAssembler.create(prescription);
		if(voOutcome != null)
		{
			if(voOutcome.getPrescriptions().contains(temppre))
			{
				voOutcome.getPrescriptions().remove(temppre);
				voOutcome.getPrescriptions().add(temppre);
				
			}
		}
		//-----------
		
		ReferralOutcome doRef = ReferralOutcomeVoAssembler.extractReferralOutcome(factory, voOutcome);
		factory.save(doRef);
		
		
	}

}