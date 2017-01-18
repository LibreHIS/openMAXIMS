/*
 * Created on May 24, 2004
 *
 */
package ims.dto.config;

import ims.crypto.EncryptionType;

/**
 * @author gcoghlan
 *
 */
public interface ConnectionConfiguration {
	public static final String BLOCK_ACTION = "block";
	public static final String FAIL_ACTION = "fail";
	public static final String GROW_ACTION = "grow";

	public EncryptionType getEncryptionType();
	public String getPassword();
	public ims.dto.ServerCompatibility getServerCompatibility();
	public String getServerName();
	public int getServerPort();
	public String getUsername();
	public boolean isConnectionPooling();
	public boolean isSharedLogin();
	/** null if not set */
	public Integer getMaxActive();
	/** null if not set */
	public Integer getMaxIdle();
	/** null if not set */
	public String getWhenExhaustedAction();
	/** null if not set */
	public Integer getMaxWait();
}
