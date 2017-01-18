package ims.configuration.delegates;

import java.io.Serializable;

public interface EnvironmentConfigPatientMergeValueChanged  extends Serializable
{
	public void handle(boolean value);
}
