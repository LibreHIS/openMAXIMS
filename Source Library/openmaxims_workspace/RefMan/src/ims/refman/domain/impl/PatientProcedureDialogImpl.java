//#############################################################################
//#                                                                           #
//#  Copyright (C) <2015>  <IMS MAXIMS>                                       #
//#                                                                           #
//#  This program is free software: you can redistribute it and/or modify     #
//#  it under the terms of the GNU Affero General Public License as           #
//#  published by the Free Software Foundation, either version 3 of the       #
//#  License, or (at your option) any later version.                          # 
//#                                                                           #
//#  This program is distributed in the hope that it will be useful,          #
//#  but WITHOUT ANY WARRANTY; without even the implied warranty of           #
//#  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the            #
//#  GNU Affero General Public License for more details.                      #
//#                                                                           #
//#  You should have received a copy of the GNU Affero General Public License #
//#  along with this program.  If not, see <http://www.gnu.org/licenses/>.    #
//#                                                                           #
//#  IMS MAXIMS provides absolutely NO GUARANTEE OF THE CLINICAL SAFTEY of    #
//#  this program.  Users of this software do so entirely at their own risk.  #
//#  IMS MAXIMS only ensures the Clinical Safety of unaltered run-time        #
//#  software that it builds, deploys and maintains.                          #
//#                                                                           #
//#############################################################################
//#EOH
// This code was generated by Cristian Belciug using IMS Development Environment (version 1.80 build 4084.19189)
// Copyright (C) 1995-2011 IMS MAXIMS. All rights reserved.

package ims.RefMan.domain.impl;

import ims.RefMan.domain.ClinicalNotesCustomControl;
import ims.RefMan.domain.base.impl.BasePatientProcedureDialogImpl;
import ims.RefMan.vo.CatsReferralForClinicalNotesVo;
import ims.RefMan.vo.CatsReferralRefVo;
import ims.RefMan.vo.ReferralClinicalNotesVo;
import ims.core.vo.ProcedureLiteVo;
import ims.core.vo.ProcedureLiteVoCollection;
import ims.domain.DomainFactory;
import ims.domain.exceptions.StaleObjectException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PatientProcedureDialogImpl extends BasePatientProcedureDialogImpl
{
	private static final long serialVersionUID = 1L;

	public Boolean saveReferralClinicalNotes(ReferralClinicalNotesVo referralClinicalNotes) throws StaleObjectException
	{
		ClinicalNotesCustomControl impl = (ClinicalNotesCustomControl) getDomainImpl(ClinicalNotesCustomControlImpl.class);
		return impl.saveReferralClinicalNotes(referralClinicalNotes);
	}

	public CatsReferralForClinicalNotesVo getReferral(CatsReferralRefVo referral)
	{
		ClinicalNotesCustomControl impl = (ClinicalNotesCustomControl) getDomainImpl(ClinicalNotesCustomControlImpl.class);
		return impl.getReferral(referral);
	}

	public ProcedureLiteVoCollection listProcedures(String searchProcedure, String listOfExistingProcedures)
	{
		if(searchProcedure == null)
			return null;
		
		searchProcedure = searchProcedure.trim().toUpperCase();
		
		if(searchProcedure == null || searchProcedure.length() == 0)
			return null;
		
		DomainFactory factory = getDomainFactory();
        Connection conection = factory.getJdbcConnection();
        
        StringBuffer sql = new StringBuffer();

		/* TODO MSSQL case - sql.append("select p.id, p.proceduren from core_procedure p LEFT OUTER JOIN core_procedure_keywords k ON p.id = k.id where (k.keyword like ? or p.proceduren like ?) and p.isactive = 1 "); */
        sql.append("select p.id, p.proceduren from core_procedure p LEFT OUTER JOIN core_procedure_keywords k ON p.id = k.id where (k.keyword like ? or p.proceduren like ?) and p.isactive = true ");
        
        if (listOfExistingProcedures != null && listOfExistingProcedures.trim().length() > 0)
		{
        	sql.append(" and p.id not in ("+listOfExistingProcedures+")");
		}
        
        sql.append(" order by upper(p.proceduren)");
        
        ProcedureLiteVoCollection procedureColl = new ProcedureLiteVoCollection();
        PreparedStatement ps;
        
        try 
        {
        	ps = conection.prepareCall(sql.toString());
        	ps.setString(1, searchProcedure + "%");
        	ps.setString(2, searchProcedure + "%");
        
        	ResultSet rs = ps.executeQuery();
        
        	while(rs.next())
        	{
        		ProcedureLiteVo vo = new ProcedureLiteVo();
        		vo.setID_Procedure(Integer.valueOf(rs.getString(1)));
        		vo.setProcedureName(rs.getString(2));
        		procedureColl.add(vo);
        	}
        }
        catch (SQLException e) 
        {
        	e.printStackTrace();
        }
        
        return procedureColl;
	}

	public ReferralClinicalNotesVo getReferralClinicalNotes(CatsReferralRefVo referral)
	{
		ClinicalNotesCustomControl impl = (ClinicalNotesCustomControl) getDomainImpl(ClinicalNotesCustomControlImpl.class);
		return impl.getReferralClinicalNotes(referral);
	}
}
