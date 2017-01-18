package ims.configuration;

import ims.framework.interfaces.IAppForm;
import ims.framework.interfaces.IAppFormCtxVariable;
import ims.framework.interfaces.IAppImage;

import java.io.Serializable;
import java.util.StringTokenizer;

public class RegForm implements Serializable,IAppForm
{
	private static final long serialVersionUID = 1L;
	
	private int formID;
	private int	version;
	private RegNamespace namespace;
	private String name;
	private String aliasName;
	private boolean dialog;
	private boolean canBeInNavigation;
	private boolean canBeInTopButtons;
	private boolean isComponent;
	private boolean	isRIE;
	private boolean isAlias;
	private boolean isSystem;
	private String caption;
	private String logic;
	private String accessLogic;
	private String domainImplementation;
	private String helpLink;
	private String rieBoClassName;
	private boolean isInformationBarVisible;
	private String description; 
	private IAppImage image;

	public RegForm()
	{		
	}	
	public RegForm(int id, RegNamespace namespace, String name, boolean dialog, String caption, String logic, String domain, String help, String rie, boolean isAlias, boolean canBeInNavigation, boolean canBeInTopButtons, boolean isComponent, String aliasName, String accessLogic, boolean isInformationBarVisible, String description, IAppImage image)
	{
		this.formID = id;
		this.namespace = namespace;
		this.name = name;
		this.aliasName = aliasName;
		this.dialog = dialog;
		this.canBeInNavigation = canBeInNavigation;
		this.canBeInTopButtons = canBeInTopButtons;
		this.isComponent = isComponent;
		this.isAlias = isAlias;
		this.caption = caption;
		this.logic = logic;
		this.domainImplementation = domain;
		this.helpLink = help;
		this.rieBoClassName = rie;
		this.accessLogic = accessLogic;
		this.isInformationBarVisible = isInformationBarVisible;
		this.description = description;	
		this.image = image;
	}

	public RegNamespace getNamespace()
	{
		return this.namespace;
	}
	public String getName()
	{
		return this.name;
	}
	public String getDescription()
	{
		return this.description;
	}
	public boolean isDialog()
	{
		return this.dialog;
	}
	public String getCaption()
	{
		return this.caption;
	}
	public int getModuleID()
	{
		//return this.namespace.getNamespaceID() * 1000 + this.formID;
		return this.formID;
	}
	public String getModule()
	{
		StringBuffer sb = new StringBuffer(this.namespace.getName().length() + 1 + this.name.length());
		sb.append(this.namespace.getName());
		sb.append('.');
		sb.append(this.name);
		return sb.toString();
	}
	
	public String getGenFormPackageName()
	{
		String moduleName = this.getModule();
		StringTokenizer st = new StringTokenizer(moduleName,".");
		String nspace = st.nextToken();
		String formName = st.nextToken();
		String formsPackage = "ims." + nspace.toLowerCase() + ".forms." +  formName.toLowerCase(); 
		return formsPackage;
		
	}
	public String getRIEBoClassName()
	{
		return this.rieBoClassName;
	}
	public void setRIEBoClassName(String value)
	{
		this.rieBoClassName = value;
	}
	
	public boolean equals(Object obj)
	{
		if (obj == null)
			return false;
		return this.getModuleID() == ((RegForm)obj).getModuleID();
	}

	public int hashCode()
	{
		return this.getModuleID();
	}	
	
	public void setCaption(String caption)
	{
		this.caption = caption;
	}

	public void setDialog(boolean dialog)
	{
		this.dialog = dialog;
	}

	public void setFormID(int formID)
	{
		this.formID = formID;
	}

	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setDescription(String description)
	{
		this.description = description;
	}

	public void setNamespace(RegNamespace namespace)
	{
		this.namespace = namespace;
	}

	public String getLogic() {
		return this.logic;
	}
	
	public void setLogic(String logic) {
		this.logic = logic;
	} 
	
	public String getHelpLink() {
		return this.helpLink;
	}
	
	public void setHelpLink(String helpLink){
		this.helpLink = helpLink;
	}
	/**
	 * Get the java classname for the domain implementation
	 * to obtain for this form.
	 * @return
	 */
	public String getDomainImplementation() {
		return this.domainImplementation;
	}

	/**
	 * Set the java classname for the domain implementation
	 * to obtain for this form.
	 * @param string
	 */
	public void setDomainImplementation(String string) {
		this.domainImplementation = string;
	}

	/**
	 * @return
	 */
	public int getFormID() {
		return this.formID;
	}
	
	public RegForm getLightCopy()
	{
		RegForm ret = new RegForm(this.getFormID(), this.getNamespace(), this.getName(), this.isDialog(), this.getCaption(), this.getLogic(), this.getDomainImplementation(), this.getHelpLink(), this.getRIEBoClassName(), this.isAlias, this.canBeInNavigation, this.canBeInTopButtons, this.isComponent, this.aliasName, this.accessLogic, this.isInformationBarVisible, this.description, this.image);
		return ret;		
	}

	public int getVersion()
	{
		return version;
	}

	public void setVersion(int version)
	{
		this.version = version;
	}

	public boolean isCanBeInNavigation()
	{
		return canBeInNavigation;
	}

	public void setCanBeInNavigation(boolean canBeInNavigation)
	{
		this.canBeInNavigation = canBeInNavigation;
	}
	
	public boolean isCanBeInTopButtons()
	{
		return canBeInTopButtons;
	}

	public void setCanBeInTopButtons(boolean canBeInTopButtons)
	{
		this.canBeInTopButtons = canBeInTopButtons;
	}

	public boolean isRIE()
	{
		return isRIE;
	}

	public void setRIE(boolean isRIE)
	{
		this.isRIE = isRIE;
	}

	public int getFormId()
	{
		return this.getFormID();
	}

	public String getLogicClass()
	{
		return this.getLogic();
	}

	public String getDomainImpl()
	{
		return this.getDomainImplementation();
	}

	public String getRieBoClassName()
	{
		return this.getRIEBoClassName();
	}

	public boolean canBeInNavigation()
	{
		return this.isCanBeInNavigation();
	}
	
	public boolean canBeInTopButtons()
	{
		return this.isCanBeInTopButtons();
	}

	public IAppFormCtxVariable[] getFormCtxVariables()
	{
		return null;
	}

	public boolean isComponent()
	{
		return isComponent;
	}

	public String getAliasName()
	{
		return this.aliasName;
	}

	public boolean isAlias()
	{
		return this.isAlias;
	}

	public String getAccessClass()
	{
		return this.accessLogic;
	}

	public boolean isSystem()
	{
		return this.isSystem;
	}
	
	public IAppImage getImage()
	{
		return this.image;
	}
	public void setImage(IAppImage image)
	{
		this.image = image;
	}
	
	public boolean isInformationBarVisible()
	{
		return this.isInformationBarVisible;	
	}
	public void setInformationBarVisible(boolean value)
	{
		this.isInformationBarVisible = value;
	}
}
