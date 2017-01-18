package ims.rules.types;

import java.io.Serializable;

import ims.framework.exceptions.CodingRuntimeException;

public final class RulesEngineField implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	protected String id;
	protected String name;
	protected String description;
	protected boolean isCollection;
	protected RulesEngineFieldType type;	
	protected String businessObjectFieldName;	
	protected RulesEngineEntity typeEntity;	
	protected int typeLookupId;
	protected RulesEngineEntity parentEntity;
	protected String fieldGetter;
	
	public RulesEngineField(String id, String name, String description, boolean isCollection, RulesEngineFieldType type, RulesEngineEntity typeEntity, int typeLookupId, String businessObjectFieldName, RulesEngineEntity parentEntity)
	{
		this.id = id;
		this.name = name;
		this.description = description;		
		this.isCollection = isCollection;
		this.type = type;				
		this.typeEntity = typeEntity;
		this.typeLookupId = typeLookupId;
		this.businessObjectFieldName = businessObjectFieldName;
		this.parentEntity = parentEntity;
		
		this.fieldGetter = "get";		
		if(this.type == RulesEngineFieldType.BOOLEAN)
			this.fieldGetter = "is";
		
		this.fieldGetter += businessObjectFieldName.substring(0, 1).toUpperCase() + businessObjectFieldName.substring(1) + "()";
	}
	
	public String getId()
	{
		return id;
	}
	public String getName()
	{
		return name;
	}
	public String getFieldGetter()
	{	
		return fieldGetter;
	}
	public String getDescription()
	{
		return description;
	}
	public RulesEngineFieldType getType()
	{
		return type;
	}
	public boolean isCollection()
	{
		return isCollection;
	}
	public RulesEngineEntity getTypeEntity()
	{
		if(type != RulesEngineFieldType.ENTITY)
			throw new CodingRuntimeException("The rules engine field type is not an entity, this method should not be called");
		
		return typeEntity;
	}
	public int getTypeLookupId()
	{
		if(type != RulesEngineFieldType.LOOKUP)
			throw new CodingRuntimeException("The rules engine field type is not a lookup, this method should not be called");
		
		return typeLookupId;
	}
	public String getBusinessObjectFieldName()
	{
		return businessObjectFieldName;
	}
	public RulesEngineEntity getParentEntity()
	{
		return parentEntity;
	}
	@Override
	public boolean equals(Object obj)
	{
		if(obj instanceof RulesEngineField)
		{
			RulesEngineField cast = (RulesEngineField)obj;
			return id.equals(cast.id) && typeEntity.equals(cast.typeEntity);
		}
		return false;
	}
}
