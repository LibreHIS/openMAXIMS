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
// This code was generated by Barbara Worwood using IMS Development Environment (version 1.80 build 5589.25814)
// Copyright (C) 1995-2015 IMS MAXIMS. All rights reserved.
// WARNING: DO NOT MODIFY the content of this file

package ims.RefMan.vo;

/**
 * Linked to RefMan.StagingCodeExport business object (ID: 1096100073).
 */
public class StageCodeExportVo extends ims.RefMan.vo.StagingCodeExportRefVo implements ims.vo.ImsCloneable, Comparable
{
	private static final long serialVersionUID = 1L;

	public StageCodeExportVo()
	{
	}
	public StageCodeExportVo(Integer id, int version)
	{
		super(id, version);
	}
	public StageCodeExportVo(ims.RefMan.vo.beans.StageCodeExportVoBean bean)
	{
		this.id = bean.getId();
		this.version = bean.getVersion();
		this.exportdatetime = bean.getExportDateTime() == null ? null : bean.getExportDateTime().buildDateTime();
		this.dataexport = bean.getDataExport();
		this.dataimport = bean.getDataImport();
		this.pushedcodingitems = ims.RefMan.vo.ReferralCodingItemVoCollection.buildFromBeanCollection(bean.getPushedCodingItems());
		this.importdatetime = bean.getImportDateTime() == null ? null : bean.getImportDateTime().buildDateTime();
		this.receivedcodingitems = ims.RefMan.vo.ReferralCodingItemVoCollection.buildFromBeanCollection(bean.getReceivedCodingItems());
		this.receivedpatientid = bean.getReceivedPatientId();
		this.receivedfceid = bean.getReceivedFCEId();
		this.codingstatus = bean.getCodingStatus() == null ? null : ims.core.vo.lookups.CodingStatus.buildLookup(bean.getCodingStatus());
		this.receivedcodingstatus = bean.getReceivedCodingStatus();
	}
	public void populate(ims.vo.ValueObjectBeanMap map, ims.RefMan.vo.beans.StageCodeExportVoBean bean)
	{
		this.id = bean.getId();
		this.version = bean.getVersion();
		this.exportdatetime = bean.getExportDateTime() == null ? null : bean.getExportDateTime().buildDateTime();
		this.dataexport = bean.getDataExport();
		this.dataimport = bean.getDataImport();
		this.pushedcodingitems = ims.RefMan.vo.ReferralCodingItemVoCollection.buildFromBeanCollection(bean.getPushedCodingItems());
		this.importdatetime = bean.getImportDateTime() == null ? null : bean.getImportDateTime().buildDateTime();
		this.receivedcodingitems = ims.RefMan.vo.ReferralCodingItemVoCollection.buildFromBeanCollection(bean.getReceivedCodingItems());
		this.receivedpatientid = bean.getReceivedPatientId();
		this.receivedfceid = bean.getReceivedFCEId();
		this.codingstatus = bean.getCodingStatus() == null ? null : ims.core.vo.lookups.CodingStatus.buildLookup(bean.getCodingStatus());
		this.receivedcodingstatus = bean.getReceivedCodingStatus();
	}
	public ims.vo.ValueObjectBean getBean()
	{
		return this.getBean(new ims.vo.ValueObjectBeanMap());
	}
	public ims.vo.ValueObjectBean getBean(ims.vo.ValueObjectBeanMap map)
	{
		ims.RefMan.vo.beans.StageCodeExportVoBean bean = null;
		if(map != null)
			bean = (ims.RefMan.vo.beans.StageCodeExportVoBean)map.getValueObjectBean(this);
		if (bean == null)
		{
			bean = new ims.RefMan.vo.beans.StageCodeExportVoBean();
			map.addValueObjectBean(this, bean);
			bean.populate(map, this);
		}
		return bean;
	}
	public Object getFieldValueByFieldName(String fieldName)
	{
		if(fieldName == null)
			throw new ims.framework.exceptions.CodingRuntimeException("Invalid field name");
		fieldName = fieldName.toUpperCase();
		if(fieldName.equals("EXPORTDATETIME"))
			return getExportDateTime();
		if(fieldName.equals("DATAEXPORT"))
			return getDataExport();
		if(fieldName.equals("DATAIMPORT"))
			return getDataImport();
		if(fieldName.equals("PUSHEDCODINGITEMS"))
			return getPushedCodingItems();
		if(fieldName.equals("IMPORTDATETIME"))
			return getImportDateTime();
		if(fieldName.equals("RECEIVEDCODINGITEMS"))
			return getReceivedCodingItems();
		if(fieldName.equals("RECEIVEDPATIENTID"))
			return getReceivedPatientId();
		if(fieldName.equals("RECEIVEDFCEID"))
			return getReceivedFCEId();
		if(fieldName.equals("CODINGSTATUS"))
			return getCodingStatus();
		if(fieldName.equals("RECEIVEDCODINGSTATUS"))
			return getReceivedCodingStatus();
		return super.getFieldValueByFieldName(fieldName);
	}
	public boolean getExportDateTimeIsNotNull()
	{
		return this.exportdatetime != null;
	}
	public ims.framework.utils.DateTime getExportDateTime()
	{
		return this.exportdatetime;
	}
	public void setExportDateTime(ims.framework.utils.DateTime value)
	{
		this.isValidated = false;
		this.exportdatetime = value;
	}
	public boolean getDataExportIsNotNull()
	{
		return this.dataexport != null;
	}
	public String getDataExport()
	{
		return this.dataexport;
	}
	public static int getDataExportMaxLength()
	{
		return 10000;
	}
	public void setDataExport(String value)
	{
		this.isValidated = false;
		this.dataexport = value;
	}
	public boolean getDataImportIsNotNull()
	{
		return this.dataimport != null;
	}
	public String getDataImport()
	{
		return this.dataimport;
	}
	public static int getDataImportMaxLength()
	{
		return 10000;
	}
	public void setDataImport(String value)
	{
		this.isValidated = false;
		this.dataimport = value;
	}
	public boolean getPushedCodingItemsIsNotNull()
	{
		return this.pushedcodingitems != null;
	}
	public ims.RefMan.vo.ReferralCodingItemVoCollection getPushedCodingItems()
	{
		return this.pushedcodingitems;
	}
	public void setPushedCodingItems(ims.RefMan.vo.ReferralCodingItemVoCollection value)
	{
		this.isValidated = false;
		this.pushedcodingitems = value;
	}
	public boolean getImportDateTimeIsNotNull()
	{
		return this.importdatetime != null;
	}
	public ims.framework.utils.DateTime getImportDateTime()
	{
		return this.importdatetime;
	}
	public void setImportDateTime(ims.framework.utils.DateTime value)
	{
		this.isValidated = false;
		this.importdatetime = value;
	}
	public boolean getReceivedCodingItemsIsNotNull()
	{
		return this.receivedcodingitems != null;
	}
	public ims.RefMan.vo.ReferralCodingItemVoCollection getReceivedCodingItems()
	{
		return this.receivedcodingitems;
	}
	public void setReceivedCodingItems(ims.RefMan.vo.ReferralCodingItemVoCollection value)
	{
		this.isValidated = false;
		this.receivedcodingitems = value;
	}
	public boolean getReceivedPatientIdIsNotNull()
	{
		return this.receivedpatientid != null;
	}
	public String getReceivedPatientId()
	{
		return this.receivedpatientid;
	}
	public static int getReceivedPatientIdMaxLength()
	{
		return 50;
	}
	public void setReceivedPatientId(String value)
	{
		this.isValidated = false;
		this.receivedpatientid = value;
	}
	public boolean getReceivedFCEIdIsNotNull()
	{
		return this.receivedfceid != null;
	}
	public String getReceivedFCEId()
	{
		return this.receivedfceid;
	}
	public static int getReceivedFCEIdMaxLength()
	{
		return 50;
	}
	public void setReceivedFCEId(String value)
	{
		this.isValidated = false;
		this.receivedfceid = value;
	}
	public boolean getCodingStatusIsNotNull()
	{
		return this.codingstatus != null;
	}
	public ims.core.vo.lookups.CodingStatus getCodingStatus()
	{
		return this.codingstatus;
	}
	public void setCodingStatus(ims.core.vo.lookups.CodingStatus value)
	{
		this.isValidated = false;
		this.codingstatus = value;
	}
	public boolean getReceivedCodingStatusIsNotNull()
	{
		return this.receivedcodingstatus != null;
	}
	public String getReceivedCodingStatus()
	{
		return this.receivedcodingstatus;
	}
	public static int getReceivedCodingStatusMaxLength()
	{
		return 1;
	}
	public void setReceivedCodingStatus(String value)
	{
		this.isValidated = false;
		this.receivedcodingstatus = value;
	}
	public boolean isValidated()
	{
		if(this.isBusy)
			return true;
		this.isBusy = true;
	
		if(!this.isValidated)
		{
			this.isBusy = false;
			return false;
		}
		if(this.pushedcodingitems != null)
		{
			if(!this.pushedcodingitems.isValidated())
			{
				this.isBusy = false;
				return false;
			}
		}
		if(this.receivedcodingitems != null)
		{
			if(!this.receivedcodingitems.isValidated())
			{
				this.isBusy = false;
				return false;
			}
		}
		this.isBusy = false;
		return true;
	}
	public String[] validate()
	{
		return validate(null);
	}
	public String[] validate(String[] existingErrors)
	{
		if(this.isBusy)
			return null;
		this.isBusy = true;
	
		java.util.ArrayList<String> listOfErrors = new java.util.ArrayList<String>();
		if(existingErrors != null)
		{
			for(int x = 0; x < existingErrors.length; x++)
			{
				listOfErrors.add(existingErrors[x]);
			}
		}
		if(this.exportdatetime == null)
			listOfErrors.add("ExportDateTime is mandatory");
		if(this.pushedcodingitems != null)
		{
			String[] listOfOtherErrors = this.pushedcodingitems.validate();
			if(listOfOtherErrors != null)
			{
				for(int x = 0; x < listOfOtherErrors.length; x++)
				{
					listOfErrors.add(listOfOtherErrors[x]);
				}
			}
		}
		if(this.receivedcodingitems != null)
		{
			String[] listOfOtherErrors = this.receivedcodingitems.validate();
			if(listOfOtherErrors != null)
			{
				for(int x = 0; x < listOfOtherErrors.length; x++)
				{
					listOfErrors.add(listOfOtherErrors[x]);
				}
			}
		}
		if(this.receivedpatientid != null)
			if(this.receivedpatientid.length() > 50)
				listOfErrors.add("The length of the field [receivedpatientid] in the value object [ims.RefMan.vo.StageCodeExportVo] is too big. It should be less or equal to 50");
		if(this.receivedfceid != null)
			if(this.receivedfceid.length() > 50)
				listOfErrors.add("The length of the field [receivedfceid] in the value object [ims.RefMan.vo.StageCodeExportVo] is too big. It should be less or equal to 50");
		if(this.receivedcodingstatus != null)
			if(this.receivedcodingstatus.length() > 1)
				listOfErrors.add("The length of the field [receivedcodingstatus] in the value object [ims.RefMan.vo.StageCodeExportVo] is too big. It should be less or equal to 1");
		int errorCount = listOfErrors.size();
		if(errorCount == 0)
		{
			this.isBusy = false;
			this.isValidated = true;
			return null;
		}
		String[] result = new String[errorCount];
		for(int x = 0; x < errorCount; x++)
			result[x] = (String)listOfErrors.get(x);
		this.isBusy = false;
		this.isValidated = false;
		return result;
	}
	public void clearIDAndVersion()
	{
		this.id = null;
		this.version = 0;
	}
	public Object clone()
	{
		if(this.isBusy)
			return this;
		this.isBusy = true;
	
		StageCodeExportVo clone = new StageCodeExportVo(this.id, this.version);
		
		if(this.exportdatetime == null)
			clone.exportdatetime = null;
		else
			clone.exportdatetime = (ims.framework.utils.DateTime)this.exportdatetime.clone();
		clone.dataexport = this.dataexport;
		clone.dataimport = this.dataimport;
		if(this.pushedcodingitems == null)
			clone.pushedcodingitems = null;
		else
			clone.pushedcodingitems = (ims.RefMan.vo.ReferralCodingItemVoCollection)this.pushedcodingitems.clone();
		if(this.importdatetime == null)
			clone.importdatetime = null;
		else
			clone.importdatetime = (ims.framework.utils.DateTime)this.importdatetime.clone();
		if(this.receivedcodingitems == null)
			clone.receivedcodingitems = null;
		else
			clone.receivedcodingitems = (ims.RefMan.vo.ReferralCodingItemVoCollection)this.receivedcodingitems.clone();
		clone.receivedpatientid = this.receivedpatientid;
		clone.receivedfceid = this.receivedfceid;
		if(this.codingstatus == null)
			clone.codingstatus = null;
		else
			clone.codingstatus = (ims.core.vo.lookups.CodingStatus)this.codingstatus.clone();
		clone.receivedcodingstatus = this.receivedcodingstatus;
		clone.isValidated = this.isValidated;
		
		this.isBusy = false;
		return clone;
	}
	public int compareTo(Object obj)
	{
		return compareTo(obj, true);
	}
	public int compareTo(Object obj, boolean caseInsensitive)
	{
		if (obj == null)
		{
			return -1;
		}
		if(caseInsensitive); // this is to avoid eclipse warning only.
		if (!(StageCodeExportVo.class.isAssignableFrom(obj.getClass())))
		{
			throw new ClassCastException("A StageCodeExportVo object cannot be compared an Object of type " + obj.getClass().getName());
		}
		if (this.id == null)
			return 1;
		if (((StageCodeExportVo)obj).getBoId() == null)
			return -1;
		return this.id.compareTo(((StageCodeExportVo)obj).getBoId());
	}
	public synchronized static int generateValueObjectUniqueID()
	{
		return ims.vo.ValueObject.generateUniqueID();
	}
	public int countFieldsWithValue()
	{
		int count = 0;
		if(this.exportdatetime != null)
			count++;
		if(this.dataexport != null)
			count++;
		if(this.dataimport != null)
			count++;
		if(this.pushedcodingitems != null)
			count++;
		if(this.importdatetime != null)
			count++;
		if(this.receivedcodingitems != null)
			count++;
		if(this.receivedpatientid != null)
			count++;
		if(this.receivedfceid != null)
			count++;
		if(this.codingstatus != null)
			count++;
		if(this.receivedcodingstatus != null)
			count++;
		return count;
	}
	public int countValueObjectFields()
	{
		return 10;
	}
	protected ims.framework.utils.DateTime exportdatetime;
	protected String dataexport;
	protected String dataimport;
	protected ims.RefMan.vo.ReferralCodingItemVoCollection pushedcodingitems;
	protected ims.framework.utils.DateTime importdatetime;
	protected ims.RefMan.vo.ReferralCodingItemVoCollection receivedcodingitems;
	protected String receivedpatientid;
	protected String receivedfceid;
	protected ims.core.vo.lookups.CodingStatus codingstatus;
	protected String receivedcodingstatus;
	private boolean isValidated = false;
	private boolean isBusy = false;
}
