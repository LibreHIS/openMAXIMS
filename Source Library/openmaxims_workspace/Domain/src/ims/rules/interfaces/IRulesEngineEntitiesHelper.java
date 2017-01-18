package ims.rules.interfaces;

import ims.rules.types.RulesEngineEntity;

import java.io.Serializable;
import java.util.List;

public interface IRulesEngineEntitiesHelper extends Serializable
{
	public List<RulesEngineEntity> getAllPublicEntities();
	public RulesEngineEntity getEntityById(String id);
}
