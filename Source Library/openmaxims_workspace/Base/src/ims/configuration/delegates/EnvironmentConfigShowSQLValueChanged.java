package ims.configuration.delegates;

import java.io.Serializable;

public interface EnvironmentConfigShowSQLValueChanged extends Serializable
{
	public void handle(boolean value);
}
