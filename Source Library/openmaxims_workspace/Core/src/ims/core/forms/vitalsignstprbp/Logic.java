//#############################################################################
//#                                                                           #
//#  Copyright (C) <2014>  <IMS MAXIMS>                                       #
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
//#############################################################################
//#EOH
// This code was generated by Daniel Laffan using IMS Development Environment (version 1.20 build 40806.900)
// Copyright (C) 1995-2004 IMS MAXIMS plc. All rights reserved.

package ims.core.forms.vitalsignstprbp;

import ims.core.vo.VitalSignsVo;
import ims.core.vo.VitalSignsVoCollection;
import ims.domain.exceptions.DomainRuntimeException;
import ims.framework.exceptions.FormOpenException;
import ims.framework.exceptions.PresentationLogicException;
import ims.framework.utils.Date;
import ims.framework.utils.DateFormat;
import ims.framework.utils.graphing.GraphingBloodPressure;
import ims.framework.utils.graphing.GraphingHighlight;
import ims.framework.utils.graphing.GraphingPulse;
import ims.framework.utils.graphing.GraphingRespiration;
import ims.framework.utils.graphing.GraphingTemperature;

public class Logic extends BaseLogic
{
	protected void onFormOpen() throws FormOpenException 
	{
		form.grpShowBy().setValue(GenForm.grpShowByEnumeration.rdoDates); 
		try {
			onRadioButtongrpShowByValueChanged();
		} catch (PresentationLogicException e) {
			throw new DomainRuntimeException();
		}
		
		form.grhRespPulseBP().graphingOptions().bloodPressure.setDisplayLying(true);
		form.grhRespPulseBP().graphingOptions().bloodPressure.setDisplaySitting(true);
		form.grhRespPulseBP().graphingOptions().bloodPressure.setDisplayStanding(true);
		form.grhRespPulseBP().showLegend(true);//WDEV-16422

		form.dteFrom().setValue(new Date().addDay(-7));
		form.dteTo().setValue(new Date());
		
		listTPRBP();			
		
		form.chkLegend().setValue(form.grhRespPulseBP().legendIsShown());
	}

	protected void onBtnViewClick() throws PresentationLogicException 
	{		
		if(form.grpShowBy().getValue().equals(GenForm.grpShowByEnumeration.rdoDay))
		{
			if(form.dteShowByDay().getValue() == null)
			{
				engine.showMessage("Please enter a date to show by");
				return;
			}
		}
		else
		{
			if(form.dteFrom().getValue() == null || form.dteTo().getValue() == null)
			{
				engine.showMessage("Please enter a date range (From and To) to show by");
				return;				
			}
			if(form.dteFrom().getValue().isGreaterThan(form.dteTo().getValue()))
			{
				engine.showMessage("The date From entered cannot be greater than the date To entered");
				return;
			}
		}

		listTPRBP();	
	}

	protected void onRadioButtongrpShowByValueChanged() throws PresentationLogicException 
	{
		if(form.grpShowBy().getValue().equals(GenForm.grpShowByEnumeration.rdoDay))
		{
			form.dteShowByDay().setEnabled(true);
			form.dteShowByDay().setValue(new Date());
			form.dteFrom().setEnabled(false);
			form.dteTo().setEnabled(false);
			form.dteFrom().setValue(null);
			form.dteTo().setValue(null);
		
		}
		else
		{
			form.dteShowByDay().setEnabled(false);
			form.dteShowByDay().setValue(null);
			form.dteFrom().setEnabled(true);
			form.dteTo().setEnabled(true);
			form.dteFrom().setValue(new Date());
			form.dteTo().setValue(new Date());
		}			
	}

	protected void onBtnPrintClick() throws PresentationLogicException 
	{	
		
	}
	
	private void listTPRBP()
	{
		VitalSignsVoCollection voCollVitals = null;
		String subTitle_temp = "";
		String subTitle_rpbp = "";

		if(form.grpShowBy().getValue().equals(GenForm.grpShowByEnumeration.rdoDay))
		{
			if(form.getGlobalContext().Core.getCurrentCareContext() == null)
				voCollVitals = domain.listTPRBPbyEpisodeofCare(form.dteShowByDay().getValue(),form.getGlobalContext().Core.getEpisodeofCareShort());
			else
				voCollVitals = domain.listTPRBP(form.dteShowByDay().getValue(),form.getGlobalContext().Core.getCurrentCareContext());
			subTitle_temp = "Temperature: " + form.dteShowByDay().getValue().toString(DateFormat.STANDARD);
			subTitle_rpbp = "Respiration / Pulse / BP: " + form.dteShowByDay().getValue().toString(DateFormat.STANDARD);
		}
		else
		{
			if(form.getGlobalContext().Core.getCurrentCareContext() == null)
				voCollVitals = domain.listTPRBPbyEpisodeofCare(form.dteFrom().getValue(),form.dteTo().getValue(),form.getGlobalContext().Core.getEpisodeofCareShort());
			else
				voCollVitals = domain.listTPRBP(form.dteFrom().getValue(),form.dteTo().getValue(),form.getGlobalContext().Core.getCurrentCareContext());
			subTitle_temp = "Temperature: " + form.dteFrom().getValue().toString(DateFormat.STANDARD) + " - " +  form.dteTo().getValue().toString(DateFormat.STANDARD);
			subTitle_rpbp = "Respiration / Pulse / BP: " + form.dteFrom().getValue().toString(DateFormat.STANDARD) + " - " +  form.dteTo().getValue().toString(DateFormat.STANDARD);
		}
 
		form.lblTemperature().setValue(subTitle_temp);
		form.grhTemp().clearAllPoints();
		form.lblRPBP().setValue(subTitle_rpbp);
		form.grhRespPulseBP().clearAllPoints();
		
		form.grhTemp().showYAxisValues();
		form.grhRespPulseBP().showYAxisValues();
		
		VitalSignsVo voVitalSign;
		
		GraphingTemperature pointTemp;
		GraphingPulse pointPulse;
		GraphingRespiration pointResp;
		GraphingBloodPressure pointBP;
		
		GraphingHighlight highlight = new GraphingHighlight(37);
		form.grhTemp().addHighlightedY(highlight);		
		
		form.grhTemp().setPrintHeaderInfo(subTitle_temp + "\r\n" + form.getGlobalContext().Core.getPatientShort().getPatientInfo());		
		form.grhRespPulseBP().setPrintHeaderInfo(subTitle_rpbp + "\r\n" + form.getGlobalContext().Core.getPatientShort().getPatientInfo());		

		if(voCollVitals != null)
		{
			for(int i=0;i<voCollVitals.size();i++)
			{
				voVitalSign = voCollVitals.get(i);
				
				if(voVitalSign.getTemperature() != null)
				{
					pointTemp = new GraphingTemperature(voVitalSign.getVitalsTakenDateTime(),  voVitalSign.getTemperature().getTemperature(),  voVitalSign);
					form.grhTemp().addPoint(pointTemp);
				}
				
				if(voVitalSign.getBloodPressure() != null)
				{
					pointBP = new GraphingBloodPressure(voVitalSign.getVitalsTakenDateTime(),voVitalSign.getBloodPressure().getBPStandingSys(), voVitalSign.getBloodPressure().getBPStandingDias(), voVitalSign.getBloodPressure().getBPSittingSys(), voVitalSign.getBloodPressure().getBPSittingDias(), voVitalSign.getBloodPressure().getBPLyingSys(),voVitalSign.getBloodPressure().getBPLyingDias(),  voVitalSign);
					form.grhRespPulseBP().addPoint(pointBP);
					form.grhRespPulseBP().addPoint(pointBP);
				}
				
				
				if(voVitalSign.getPulse() != null)
				{
					pointPulse = new GraphingPulse(voVitalSign.getVitalsTakenDateTime(),  voVitalSign.getPulse().getPulseRateRadial(), voVitalSign.getPulse().getPulseRateApex(), voVitalSign.getPulse().getIsIrregular(),voVitalSign);
					form.grhRespPulseBP().addPoint(pointPulse);		
				}
				
				if(voVitalSign.getRespiratory() != null)
				{
					pointResp = new GraphingRespiration(voVitalSign.getVitalsTakenDateTime(),  voVitalSign.getRespiratory().getRespRate(),  voVitalSign);
					form.grhRespPulseBP().addPoint(pointResp);	
				}
				
			}
		}	
	}
	
	protected void onChkLegendValueChanged() throws PresentationLogicException
	{
		form.grhRespPulseBP().showLegend(form.chkLegend().getValue());
	}
}