package ims.framework.interfaces;

import ims.framework.enumerations.LocalSettingsType;

public interface ILocalSettingsProvider
{
	String getLocalSetting(String uniqueId, LocalSettingsType settingType);
	void setLocalSetting(String uniqueId, LocalSettingsType settingType, String value);
}
