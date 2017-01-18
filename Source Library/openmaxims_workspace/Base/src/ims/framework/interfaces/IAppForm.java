package ims.framework.interfaces;

public interface IAppForm
{
	public int getFormId();
	public String getName();
	public String getAliasName();
	public String getCaption();
	public boolean isDialog();
	public boolean isComponent();
	public boolean isAlias();
	public boolean isSystem();
	public String getLogicClass();
	public String getAccessClass();
	public String getDomainImpl();
	public String getHelpLink();
	public String getRieBoClassName();
	public boolean canBeInNavigation();	
	public boolean canBeInTopButtons();
	public String getGenFormPackageName();
	public String getModule();
	public boolean isInformationBarVisible();
	public String getDescription();
	public IAppImage getImage();
	
	public void setCaption(String caption);
	public void setHelpLink(String helpUrl);
	//public IAppFormCtxVariable[] getFormCtxVariables();
}
