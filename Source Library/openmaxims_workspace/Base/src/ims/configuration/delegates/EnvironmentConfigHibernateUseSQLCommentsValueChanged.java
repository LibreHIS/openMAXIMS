package ims.configuration.delegates;

import java.io.Serializable;

public interface EnvironmentConfigHibernateUseSQLCommentsValueChanged extends Serializable
{
	public void handle(boolean value);
}
